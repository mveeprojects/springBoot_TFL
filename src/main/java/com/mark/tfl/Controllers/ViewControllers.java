package com.mark.tfl.Controllers;

import com.mark.tfl.Services.TFLStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewControllers {

    private TFLStatusService tflStatusService;

    ViewControllers(){
        tflStatusService = RestControllers.getTflStatusService();
    }

    @RequestMapping("/")
    public String homeController(Model model){
        model.addAttribute("title", "Home");
        model.addAttribute("content", tflStatusService.getLineStatuses());
        return "index";
    }
}
