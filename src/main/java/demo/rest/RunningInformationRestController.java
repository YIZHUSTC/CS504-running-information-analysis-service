package demo.rest;

import demo.domain.InfoAnalysis;
import demo.service.InfoAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//  RestController is for url routing, the "value" in the address of browser corresponds a specified "method"
//  Responsible for interaction with client, returns a respond when a request is received
public class RunningInformationRestController {

    private InfoAnalysisService infoAnalysisService;

    @Autowired
    public RunningInformationRestController(InfoAnalysisService infoAnalysisService) {
        this.infoAnalysisService = infoAnalysisService;
    }

    @RequestMapping(value = "/running", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<InfoAnalysis> upload(@RequestBody List<InfoAnalysis> locations) {
        return this.infoAnalysisService.saveRunningInformation(locations);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void purge() {
        this.infoAnalysisService.deleteAll();
    }

    @RequestMapping(value = "/delete/{runningId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("runningId") String runningId) {
        this.infoAnalysisService.deleteByRunningId(runningId);
    }

//    @RequestMapping(value = "/running/health/{heartRate}", method = RequestMethod.GET)
//    public Page<InfoAnalysis> findAllByOrderHeartRateDesc(@PathVariable int heartRate, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
//        return this.infoAnalysisService.findAllByOrderByHeartRateDesc(heartRate, new PageRequest(page, size));
//    }

    @RequestMapping(value = "/running", method = RequestMethod.GET)
    public Page<InfoAnalysis> findAllByOrderHeartRateDesc(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return this.infoAnalysisService.findAllByOrderByHeartRateDesc(new PageRequest(page, size));
    }

    @RequestMapping(value = "/running/{userId}", method = RequestMethod.GET)
    public Page<InfoAnalysis> findByUserId(@PathVariable Long userId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return this.infoAnalysisService.findByUserId(userId, new PageRequest(page, size));
    }

    @RequestMapping(value = "/running/runningId/{runningId}", method = RequestMethod.GET)
    public Page<InfoAnalysis> findByRunningId(@PathVariable String runningId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return this.infoAnalysisService.findByRunningId(runningId, new PageRequest(page, size));
    }

//    @RequestMapping(value = "/running/runningId/{runningId}", method = RequestMethod.GET)
//    public Page<InfoAnalysis> findAllByRunningId(@PathVariable(name = "runningId") String runningId, @RequestParam(name = "page") int page, @RequestParam(name = "size", required = false) int size) {
//        PageRequest request = new PageRequest(page, size == null ? 2 : size, Sort.Direction.DESC, "healthWarningLevel");
//        return this.infoAnalysisService.findAllByRunningId(runningId, request);
//    }

}