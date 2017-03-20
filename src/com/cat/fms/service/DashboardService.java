package com.cat.fms.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface DashboardService {

	JSONObject bookRide(String mobileNumber, String lat, String lng, String carType) throws JSONException;

	JSONObject checkRide(String mobileNumber, String lat, String lng, String carType) throws JSONException;

	JSONObject cancelRide(String driver_mobno) throws JSONException;

	JSONObject completeRide(String rideId, String driver_mobno, String d_lat, String d_lng, String carType) throws JSONException;
}
