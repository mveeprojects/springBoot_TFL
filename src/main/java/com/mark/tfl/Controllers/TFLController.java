package com.mark.tfl.Controllers;

import com.mark.tfl.Services.TFLStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TFLController {

    private static final Logger log = LoggerFactory.getLogger(TFLController.class);

    @Autowired
    private TFLStatusService tflStatusService;

    public void lastScheduledRuntime(String time) {
        log.info("TFLController - " + time);
        tflStatusService.scheduleAPICall();
    }

    @RequestMapping("/")
    public String homeController(Model model) {
        model.addAttribute("title", "Home");
        model.addAttribute("tablecontent", tflStatusService.getLineStatuses());
        model.addAttribute("dropdowncontent", tflStatusService.getLineStatuses());
        return "index";
    }

    @RequestMapping("/issues")
    public String linesWithIssues(Model model) {
        model.addAttribute("title", "Issues");
        model.addAttribute("tablecontent", tflStatusService.getLineIssues());
        model.addAttribute("dropdowncontent", tflStatusService.getLineStatuses());
        return "index";
    }

    @RequestMapping("/linehistory")
    public String lineHistory(@RequestParam("linename") String lineName, Model model){
        model.addAttribute("heading", "Status history of the " + lineName + " line");
        model.addAttribute("history", tflStatusService.streamLineStatusHistoryFromMongo(lineName));
        model.addAttribute("total_count", "Total number of searches: " + tflStatusService.historyCount());
        model.addAttribute("good_count", "Good Service: " + tflStatusService.goodHistoryCount());
        model.addAttribute("not_good_count", "Other: " + tflStatusService.notGoodHistoryCount());
        model.addAttribute("percentage_uptime", "Percentage uptime: " + tflStatusService.percentageUptime(tflStatusService.historyCount(), tflStatusService.goodHistoryCount()) + "%");
        model.addAttribute("lineName", lineName);
        return "line_history";
    }
}