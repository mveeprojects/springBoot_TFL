package com.mark.tfl.Controllers;

import com.mark.tfl.Models.TFLLineHistoryObject;
import com.mark.tfl.Services.TFLStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mark.tfl.Utils.MathUtils.getPercentage;

@Controller
public class TFLController {

    private static final Logger log = LoggerFactory.getLogger(TFLController.class);
    private long historyCount, goodHistoryCount, notGoodHistoryCount;
    private double percentageUptime;

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
    public String lineHistory(@RequestParam("linename") String lineName, Model model) {
        List<TFLLineHistoryObject> lineHistory = tflStatusService.getLineStatusHistoryFromMongo(lineName);
        historyCount = tflStatusService.getHistoryCount();
        goodHistoryCount = tflStatusService.getGoodHistoryCount();
        notGoodHistoryCount = tflStatusService.getNotGoodHistoryCount();
        percentageUptime = getPercentage(historyCount, goodHistoryCount);
        model.addAttribute("heading", "Status history of the " + lineName + " line");
        model.addAttribute("history", lineHistory);
        model.addAttribute("total_count", "Total number of searches: " + historyCount);
        model.addAttribute("good_count", "Good Service: " + goodHistoryCount);
        model.addAttribute("not_good_count", "Other: " + notGoodHistoryCount);
        model.addAttribute("percentage_uptime", "Percentage uptime: " + percentageUptime + "%");
        model.addAttribute("lineName", lineName);
        return "line_history";
    }

    @RequestMapping("/chart")
    public String exampleChartModel (Model model){
        List<Map<String, Integer>> mapsList = new ArrayList<>();

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("UK", 12);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("Germany", 32);
        Map<String, Integer> map3 = new HashMap<>();
        map2.put("USA", 16);

        mapsList.add(map1);
        mapsList.add(map2);
        mapsList.add(map3);

        model.addAttribute("mapsList", mapsList);

        return "charttest";
    }

//    @Test
//    public void test(){
//        Map<String, Integer> someData = new HashMap<>();
//        someData.put("UK", 12);
//        someData.put("Canada", 7);
//        someData.put("USA", 32);
//        System.out.println(someData.toString());
//    }
}