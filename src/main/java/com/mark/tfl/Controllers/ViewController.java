package com.mark.tfl.Controllers;

import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Services.TFLStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewController {

    private TFLStatusService tflStatusService;
    private String testString;

    @Autowired
    ViewController(){
        tflStatusService = Dashboard.getTflStatusService();
    }

    @RequestMapping("/test")
    public String test(Model model){
        testString = listStatusesToString(tflStatusService.getLineStatuses());
        model.addAttribute("statuses", testString);
        return "thymeleaftest";
    }

    private String listStatusesToString(List<LineStatus> lineStatuses){
        StringBuilder stringBuilder = new StringBuilder();
        for (LineStatus lineStatus : lineStatuses) {
            stringBuilder.append(lineStatus.toString());
        }
        return stringBuilder.toString();
    }
}
