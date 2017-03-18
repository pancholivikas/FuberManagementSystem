package com.cat.fms.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface DashboardService {

	JSONObject getLeavesData(String psId, String cwsId) throws JSONException;

	JSONObject setLeavesData(String psId, String cwsId, String leaveType,
			String startDate, String endDate, String noOfDays) throws JSONException;

	JSONObject getHistory(String psId, String cwsId) throws JSONException;

	JSONObject bookRide(String customerId, String lat, String lng) throws JSONException;
	

}
