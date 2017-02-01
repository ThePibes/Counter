package com.cosa.controller;

import com.cosa.Utils.UtilsMsj;
import com.cosa.pojo.People;
import com.cosa.service.PeopleService;
import com.cosa.service.StockService;
import com.cosa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by kp0cH4 on 28/01/17.
 */
@Controller
public class PeopleController {
    @Autowired
    public UtilsMsj utils;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private UsuarioService usuarioService;

     @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public String list(Model model)
     {
        model.addAttribute("personas", peopleService.listAll());
        return "layout/_layout :: mainPage(page='personas/list', fragment='listPeople')";
     }
    @RequestMapping(value = "/cliente/nuevo", method = RequestMethod.GET)
    public String create(){ return "layout/_layout :: mainPage(page='personas/create', fragment='createPeople')"; }

    @RequestMapping(value = "/cliente/new", method = RequestMethod.POST)
    public String save(@ModelAttribute People people)
    {
        try
        {
            peopleService.save(people);
        }
        catch (Exception e)
        {
            //TODO Agregar el manejo de errores.
        }

        return "redirect:cliente";
    }

}
