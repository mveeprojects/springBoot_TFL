package com.mark.tfl.Controllers;

import com.mark.tfl.Services.TFLStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    private TFLStatusService tflStatusService;

    @Autowired
    ViewController(){
        tflStatusService = Dashboard.getTflStatusService();
    }

    @RequestMapping("/")
    public String homeController(Model model){
        model.addAttribute("title", "Home");
        model.addAttribute("content", tflStatusService.getLineStatuses());
        return "index";
    }
}
