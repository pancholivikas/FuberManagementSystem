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
	String bookRide(
			@RequestParam("mobileNumber") String mobileNumber,
			@RequestParam("lat") String lat,
			@RequestParam("lng") String lng,
			@RequestParam("carType") String carType) throws JSONException {
		JSONObject obj = new JSONObject();
		obj = dashboardService.bookRide(mobileNumber, lat, lng, carType);
		return obj.toString();
	}
	
	@RequestMapping(value = "/checkRide", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String checkRide(
			@RequestParam("mobileNumber") String mobileNumber,
			@RequestParam("lat") String lat,
			@RequestParam("lng") String lng,
			@RequestParam("carType") String carType) throws JSONException {
		JSONObject obj = new JSONObject();
		obj = dashboardService.checkRide(mobileNumber, lat, lng, carType);
		return obj.toString();
	}
	
	@RequestMapping(value = "/cancelRide", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String cancelRide(
			@RequestParam("driver_mobNo") String driver_mobno) throws JSONException {
		JSONObject obj = new JSONObject();
		obj = dashboardService.cancelRide(driver_mobno);
		return obj.toString();
	}
	
	@RequestMapping(value = "/completeRide", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String completeRide(
			@RequestParam("rideId") String rideId,
			@RequestParam("driver_mobNo") String driver_mobno,
			@RequestParam("destinationLat") String d_lat,
			@RequestParam("destinationLng") String d_lng,
			@RequestParam("carType") String carType) throws JSONException {
		JSONObject obj = new JSONObject();
		obj = dashboardService.completeRide(rideId, driver_mobno, d_lat, d_lng, carType);
		return obj.toString();
	}
	
}
