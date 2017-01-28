package com.cosa.controller;

import com.cosa.pojo.Role;
import com.cosa.pojo.Stock;
import com.cosa.pojo.Usuario;
import com.cosa.service.StockService;
import com.cosa.Utils.UtilsMsj;
import com.cosa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by root on 18/01/17.
 */
@Controller
public class StockController {

    @Autowired
    public UtilsMsj utils;

    @Autowired
    private StockService stockService;

    @Autowired
    private UsuarioService usuarioService;


    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String index(Model model){
        return "home/hello";
      //  Usuario usuario = new Usuario();
      //usuario.setNombre("kpocha");
        //usuario.setPassword("123");
       //usuario.setRole(Role.ROLE_USER);
        //usuarioService.saveUsuario(usuario);
    }

    @RequestMapping(value = "/stock", method= RequestMethod.GET)
    public String listProduct(Model model){
        model.addAttribute("stock", stockService.listAll());
        return "layout/_layout :: mainPage(page='stock/list', fragment='listStock')";
    }

    @RequestMapping(value = "/stockActualizado", method= RequestMethod.GET)
    public String listProductUpdate(Model model, @ModelAttribute(name = "resultado") boolean resultado ){

        if(Objects.nonNull(resultado) && resultado ){
            utils.successMessage("Acción correctamente",model);
        }else if(!resultado){
            utils.errorMessage("Fallo",new Exception(), model);
        }
        model.addAttribute("stock", stockService.listAll());
        return "layout/_layout :: mainPage(page='stock/list', fragment='listStock')";
    }

    @RequestMapping(value = "/stock/nuevo", method = RequestMethod.GET)
    public String makeProduct(){
        return "layout/_layout :: mainPage(page='stock/create', fragment='createStock')";
    }

    @RequestMapping(value = "/stock", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute Stock stockForm, RedirectAttributes ra){
        try{
            stockService.save(stockForm);
            ra.addFlashAttribute("resultado", true);
        }catch (Exception e){
            ra.addFlashAttribute("resultado", false);
        }
        return "redirect:stockActualizado";
    }
    @RequestMapping(value = "/stock/details/{id}", method = RequestMethod.GET)
    public String detailsProduct(@ModelAttribute Stock stockForm, @PathVariable("id") int id, Model model)
    {
        Stock stock = stockService.findById(id);
        if(stock != null)
        {
            model.addAttribute("stock",stock);
            return "layout/_layout :: mainPage(page='stock/details', fragment='detailsStock')";
        }
        //TODO: Aplicar mensaje de error por no encontrar el stock
        return "redirect:../";
    }
    @RequestMapping(value = "/stock/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@ModelAttribute Stock stockForm, @PathVariable("id") int id, Model model)
    {
        Stock stock = stockService.findById(id);
        if(stock != null)
        {
            model.addAttribute("stock",stock);
            return "layout/_layout :: mainPage(page='stock/edit', fragment='editStock')";
        }
        //TODO: Aplicar mensaje de error por no encontrar el stock
        return "redirect:../";
    }
    @RequestMapping(value = "/stock/edit", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute Stock stockForm)
    {
        //TODO: Aplicar mensaje de error o success
        stockService.update(stockForm);
        return "redirect:../stock";

    }
    @RequestMapping(value = "/stock/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(Model model, @ModelAttribute Stock stockForm, RedirectAttributes ra, @PathVariable("id") int id){
        try{
            stockService.remove(id);
            ra.addFlashAttribute("resultado", true);
        }catch (Exception e){
            ra.addFlashAttribute("resultado", false);
        }
        return "redirect:../";
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/stock";
    }
}
