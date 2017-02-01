function mostrar_cuit(cuit)
{
    // Si está vaciá, es cero o ya tiene guiones bien devolver el mismo
    if (!cuit)
        return cuit;
    cuit = cuit.toString();
    if (cuit.length == 13 && cuit.charAt(2) == '-' && cuit.charAt(11) == '-')
        return cuit;

    var digits = cuit.split("");
    var cuit_con_guiones = digits[0]+digits[1]+'-'+digits[2]+digits[3]+digits[4]+digits[5]+digits[6]+digits[7]+digits[8]+digits[9]+'-'+digits[10];
    return cuit_con_guiones;
}

function limpiar_cuit(cuit)
{
    return cuit.toString().replace('-' , "").replace('-' , "");
}

function obtener_datos_cuit(cuit, callback)
{
    cuit = limpiar_cuit(cuit);
    $.ajax(
        {
            url: "https://soa.afip.gob.ar/sr-padron/v2/persona/"+cuit,
            type: "get",
            data: "",
            success: function(data) {
                callback(data);
            },
            error:function (e){
                callback(false);
            }
        });
}

function obtener_datos_afip(cuit,dni,callback)
{
    if (cuit != ""){
        obtener_datos_cuit(cuit, callback);
    }
    else{
        if (dni != ""){
            $.ajax(
            {
                url: "https://soa.afip.gob.ar/sr-padron/v2/personas/"+dni,
                type: "get",
                data: "",
                success: function(data) {
                    if (data.success ){
                        $("#cuit").val(mostrar_cuit(data.data));
                        console.log(data.data);
                        obtener_datos_cuit(data.data,callback);
                    }
                    else{
                        callback(false);
                    }
                },
                error:function (e){
                    callback(false);
                }
            });
        }
        else{
            callback(false);
        }
    }
}


function consultar_datos_afip(cuit)
{
    $.ajax({
        url: "https://soa.afip.gob.ar/sr-padron/v2/persona/"+cuit,
        type: "get",
        data: "",
        success: function(data) {
            if (data != false && data.success) {
                alerta_selector('confirmacion', 'CUIT habilitada en la base pÃºblica de AFIP', '#cuit');
                $("input[type=submit]").first().removeAttr("disabled");

            } else if (data != false && !data.success) {
                alerta_selector('alerta', 'No se encontrÃ³ este CUIT habilitado en la base pÃºblica de AFIP', '#cuit', false, false);
                $("input[type=submit]").first().attr("disabled", "disabled");
            }
        }
    });
}

// Para saber la condiciÃ³n de IVA tengo que evaluar lo que vuelve en impuestos
function evaluar_condicion(impuestos, categoriasMonotributo)
{
    if (typeof impuestos == 'object' && impuestos.indexOf(32) != -1)
        var idtipoiva = 3; // Exento
    else if (typeof categoriasMonotributo == 'object')
        var idtipoiva = 2; // Monotributo
    else if (typeof impuestos == 'object' && impuestos.indexOf(30) != -1)
        var idtipoiva = 1; // Responsable Inscripto
    else
        var idtipoiva = 0; // Consumidor Final

    return idtipoiva;
}

function chequear_cuit_callback(data)
{
    if (data != false && data.success) {
        // Ambos campos para completar el texto estÃ¡n vacÃ­os o el usuario acepta cambiarlos
        if ((!$("input[name=razonsocial]").val() && !$("input[name=domicilio]").val())
            || confirm('Se encontraron datos en la base pÃºblica de AFIP, Â¿Desea reemplazar el domicilio, la razÃ³n social y la condiciÃ³n de IVA existentes?')) {
                $("input[name=razonsocial]").val(data.data.nombre);
                idtipoiva = evaluar_condicion(data.data.impuestos, data.data.categoriasMonotributo);
                $("select[name=idtipoiva] option[value="+ idtipoiva +"]").prop('selected', true);
                if (data.data.hasOwnProperty('domicilioFiscal'))
                    $("input[name=domicilio]").val(data.data.domicilioFiscal.direccion);
        }
        // Solo si el nombre estÃ¡ vacÃ­o lo completo
        if (!$("input[name=nombre]").val())
            $("input[name=nombre]").val(data.data.nombre);

    } else if (data != false && !data.success) {
        alerta_selector('alerta', 'No se encontraron datos en la base pÃºblica de AFIP para este NÂº de CUIT o DNI', '#cuit');

    }
    desbloquear();
}

function chequear_cuit()
{
    if ($("input[name=cuit]").val() != "" || $("input[name=dni]").val() != ""){
        bloquear();
        obtener_datos_afip($("input[name=cuit]").val(),$("input[name=dni]").val(),chequear_cuit_callback);

    } else {
        alerta('Complete CUIT o DNI para obtener los datos de la base pÃºblica de AFIP');
    }
    return false;
}

// TODO: Hay que migrar esta funcion a la librerá de validaciones
function validar_cuit(cuit_input)
{
    var cuit = limpiar_cuit(cuit_input.val());
    if (cuit == "") {
    // Si el campo es vació solo me aseguro de que se pueda Aceptar y de borrar si hay alguna alerta
        $("input[type=submit]").first().removeAttr("disabled");
        alerta_selector(false, false, '#cuit');

    } else if (!$.isNumeric(cuit) || cuit.length != 11) {
    // Primero reviso si tiene 11 caracteres numÃ©ricos
        alerta_selector('alerta', 'El CUIT no es correcto y no se guardarÃ¡', '#cuit', false, false);
        $("input[type=submit]").first().removeAttr("disabled");

    } else {
    // DespuÃ©s me fijo si existe en AFIP
        consultar_datos_afip(cuit);
    }
}
function bloquear()
{
    var top = ($(window).height() / 2 - $("#actualizando").outerHeight());
    $("#actualizando").css({
        position:'fixed',
        margin:0,
        top: (top > 0 ? top : 0)+'px',
        width: '100%'
    })
    $("#bloquea").fadeIn(500);
    $("#actualizando").fadeIn(500);
}

function desbloquear()
{
    $("#bloquea").fadeOut(500);
    $("#actualizando").fadeOut(500);
    $(".informacion_especifica").remove();
    $("#marco_flotante").fadeOut(500);
    $("#marco_flotante").html("");
    window.desbloqueable = false;
}


