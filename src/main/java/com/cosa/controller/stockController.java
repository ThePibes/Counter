package com.cosa.controller;

import com.cosa.pojo.Stock;
import com.cosa.service.StockService;
import com.cosa.Utils.UtilsMsj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

/**
 * Created by root on 18/01/17.
 */
@Controller
public class stockController {

    @Autowired
    public UtilsMsj utils;

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/stock", method= RequestMethod.GET)
    public String listProduct(Model model){
        model.addAttribute("stock", stockService.findAllStock());
        return "layout/_layout :: mainPage(page='stock/list', fragment='listStock')";
    }

    @RequestMapping(value = "/stockActualizado", method= RequestMethod.GET)
    public String listProductUpdate(Model model, @ModelAttribute(name = "resultado") boolean resultado ){

        if(Objects.nonNull(resultado) && resultado ){
            utils.successMessage("Se guardo correctamente",model);
        }else if(!resultado){
            utils.errorMessage("Fallo",new Exception(), model);
        }
        model.addAttribute("stock", stockService.findAllStock());
        return "layout/_layout :: mainPage(page='stock/list', fragment='listStock')";
    }

    @RequestMapping(value = "/stock/nuevo", method = RequestMethod.GET)
    public String makeProduct(){
        return "layout/_layout :: mainPage(page='stock/create', fragment='createStock')";
    }

    @RequestMapping(value = "/stock", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute Stock stockForm, RedirectAttributes ra){
        if(stockService.save(stockForm)) {
            ra.addFlashAttribute("resultado", true);
        }else{
            ra.addFlashAttribute("resultado", false);
        }
        return "redirect:stockActualizado";
    }

}
