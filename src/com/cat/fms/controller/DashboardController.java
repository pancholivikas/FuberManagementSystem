package com.cat.fms.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.fms.service.DashboardService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@RequestMapping(value = "/bookRide", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String getLeavesData(
			@RequestParam("customerId") String customerId,
			@RequestParam("lat") String lat,
			@RequestParam("lng") String lng) throws JSONException {
		JSONObject obj = new JSONObject();
		obj = dashboardService.bookRide(customerId, lat, lng);
		return obj.toString();
	}
	
	@RequestMapping(value = "/getHistory", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String getHistory(
			@RequestParam("psId") String psId,
			@RequestParam("cwsId") String cwsId) throws JSONException {
		JSONObject obj = new JSONObject();
		obj = dashboardService.getHistory(psId, cwsId);
		return obj.toString();
	}
	
	@RequestMapping(value = "/setLeavesData", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String setLeavesData(
			@RequestParam("psId") String psId,
			@RequestParam("cwsId") String cwsId,
			@RequestParam("leaveType") String leaveType,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("noOfDays") String noOfDays) throws JSONException {
		JSONObject obj = new JSONObject();
		obj = dashboardService.setLeavesData(psId, cwsId, leaveType, startDate, endDate, noOfDays);
		return obj.toString();
	}

}
