package com.mark.tfl.Controllers;

import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Services.TFLStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewControllers {

    private static TFLStatusService tflStatusService;

    @Autowired
    ViewControllers(){
        tflStatusService = Dashboard.getTflStatusService();
    }

    @RequestMapping("/test")
    public String test(Model model){
        List<LineStatus> lineStatusList = tflStatusService.getLineStatuses();

        StringBuilder stringBuilder = new StringBuilder();
        for (LineStatus lineStatus : lineStatusList) {
            stringBuilder.append(lineStatus.toString());
        }

        model.addAttribute("statuses", stringBuilder);
        return "test";
    }

}
