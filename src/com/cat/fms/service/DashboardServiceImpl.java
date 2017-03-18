package com.cat.fms.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class DashboardServiceImpl implements DashboardService{

	@Override
	public JSONObject getLeavesData(String psId, String cwsId)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("psId", psId);
		obj.put("cwsId", cwsId);
		obj.put("slAvailable", 7);
		obj.put("slTaken", 5);
		obj.put("plAvailable", 12);
		obj.put("plTaken", 12);
		obj.put("ohAvailable", 2);
		obj.put("ohTaken", 0);
		return obj;
	}

	@Override
	public JSONObject setLeavesData(String psId, String cwsId,
			String leaveType, String startDate, String endDate, String noOfDays)
			throws JSONException {
		JSONObject obj = new JSONObject();
		if(leaveType.equalsIgnoreCase("slLeave")){
			obj.put("leaveType", "Sick Leave");
		}
		else if(leaveType.equalsIgnoreCase("plLeave")){
			obj.put("leaveType", "Previlage Leave");
		}
		else{
			obj.put("leaveType", "Optional Holiday");
		}
		
		obj.put("startDate", startDate);
		obj.put("endDate", endDate);
		obj.put("noOfDays", noOfDays);
		obj.put("requestStatus", "Pending");
		JSONArray objArr = new JSONArray();
		objArr.put(obj);
		JSONObject ob = new JSONObject();
		ob.put("leaveArray", objArr);
		return ob;
	}

	@Override
	public JSONObject getHistory(String psId, String cwsId)
			throws JSONException {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		obj.put("leaveType", "Sick Leave");
		obj.put("startDate", "2016-11-22");
		obj.put("endDate", "2016-11-23");
		obj.put("noOfDays", 2);
		obj.put("requestStatus", "Pending");
		JSONArray objArr = new JSONArray();
		objArr.put(obj);
		objArr.put(obj);
		JSONObject ob = new JSONObject();
		ob.put("leaveArray", objArr);
		return ob;
	}

	@Override
	public JSONObject bookRide(String customerId, String lat, String lng)
			throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}
