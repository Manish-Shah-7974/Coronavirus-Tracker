package Tracker.CoronaVirus.Controller;

import Tracker.CoronaVirus.Model.LocationStats;
import Tracker.CoronaVirus.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    Services services;

     @GetMapping("/")
     public String home(Model model){
         List<LocationStats> allStats = services.getAllstats();
        int totalCases = allStats.stream().mapToInt(stat-> Integer.parseInt(stat.getLatestTotalCases())).sum();
        int totalnewCases = allStats.stream().mapToInt(stat-> (stat.getDiffFromPrevDay())).sum();
        model.addAttribute("totalNewCases",totalnewCases);
         model.addAttribute("locationStats",allStats);
         model.addAttribute("totalReportedCases",totalCases);
        return "home";
    }
}
