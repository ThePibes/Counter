package com.cosa.Utils;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Created by root on 21/01/17.
 */
@Service
public class UtilsMsj {
    public void setTempData(String msj, String type, Model model){
        model.addAttribute("message", msj);
        model.addAttribute("type",type);
    }
    public void errorMessage(String msj, Exception e, Model model){
        setTempData(msj,"error", model);
    }
    public void successMessage(String msj, Model model){
        setTempData(msj,"success", model);
    }

}
