package com.cat.fms.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cat.fms.model.FuberRideDetailsData;

public class DashboardServiceImpl implements DashboardService{
	@Autowired@Qualifier(value = "sessionFactory2")
	private SessionFactory sessionFactory;
	
	
	@Override
	public JSONObject bookRide(String customerMobileNumber, String lat, String lng, String carType)
			throws JSONException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		JSONObject resultObj = new JSONObject();
		Double dLat = Double.parseDouble(lat);
		Double dLng = Double.parseDouble(lng);
		Query query  = session.createQuery("Select name, mobileNumber, lat, lng, carType from FuberDriversData where SQRT(power(lat-:userLat, 2) + power(lng-:userLng, 2)) <= 0.03 and carType=:carType and isBooked=:isBooked");		
		query.setParameter("userLat", dLat);
		query.setParameter("userLng", dLng);
		query.setParameter("carType", carType);
		query.setParameter("isBooked", 0);
		query.setMaxResults(1);
		try {
			List <Object> results = query.list();
			Iterator resultItr = results.iterator();
			JSONObject obj = new JSONObject();
			
			FuberRideDetailsData rideDetailsData = new FuberRideDetailsData();
			Random rand = new Random();
			int randNum = rand.nextInt((10000000 - 0) + 1) + 10000000;
			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateFormatGmt.parse(dateFormatGmt.format(new Date())));
			Timestamp startTime = new Timestamp(cal.getTimeInMillis());
			
			if(resultItr.hasNext()) {
				Object[] row = (Object[]) resultItr.next();
				
				Query updateQuery = session.createQuery("update FuberDriversData set isBooked = 1 where mobileNumber=:mobileNumber");
				updateQuery.setParameter("mobileNumber", String.valueOf(row[1]));
				int result = updateQuery.executeUpdate();
				if(result == 1) {
					obj.put("Name", String.valueOf(row[0]));
					obj.put("MobileNumber", String.valueOf(row[1]));
					obj.put("lat", String.valueOf(row[2]));
					obj.put("lng", String.valueOf(row[3]));
					obj.put("carType", String.valueOf(row[4]));
					obj.put("rideId", String.valueOf(randNum));
					resultObj.put("data", obj);
				}
				else {
					resultObj.put("data", "Please try again");
				}
				rideDetailsData.setCustomerMobileNumber(customerMobileNumber);
				rideDetailsData.setDriverMobileNumber(String.valueOf(row[1]));
				rideDetailsData.setRideId(String.valueOf(randNum));
				rideDetailsData.setStartTime(startTime);
				session.persist(rideDetailsData);
				tx.commit();				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			resultObj.put("data", "Not Found");
		}
		finally{
			session.clear();
			session.flush();
			session.close();
		}
		return resultObj;
	}

	@Override
	public JSONObject checkRide(String customerId, String lat, String lng, String carType)
			throws JSONException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		JSONObject resultObj = new JSONObject();
		
		try {
			Double dLat = Double.parseDouble(lat);
			Double dLng = Double.parseDouble(lng);
			Query query  = session.createQuery("Select name, mobileNumber, lat, lng, carType from FuberDriversData where SQRT(power(lat-:userLat, 2) + power(lng-:userLng, 2)) <= 0.03 and carType=:carType and isBooked=:isBooked");		
			query.setParameter("userLat", dLat);
			query.setParameter("userLng", dLng);
			query.setParameter("carType", carType);
			query.setParameter("isBooked", 0);
			//query.setMaxResults(1);
			List <Object> results = query.list();
			Iterator resultItr = results.iterator();
			JSONArray jsonArr = new JSONArray();
			if(resultItr.hasNext()) {
				while(resultItr.hasNext()) {
					Object[] row = (Object[]) resultItr.next();
					JSONObject obj = new JSONObject();
					obj.put("Name", String.valueOf(row[0]));
					obj.put("MobileNumber", String.valueOf(row[1]));
					obj.put("lat", String.valueOf(row[2]));
					obj.put("lng", String.valueOf(row[3]));
					obj.put("carType", String.valueOf(row[4]));
					jsonArr.put(obj);
				}				
				resultObj.put("data", jsonArr);
			}
			else {
				resultObj.put("data", "Not Found");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			resultObj.put("data", "Not Found");
		}
		finally{
			session.clear();
			session.flush();
			session.close();
		}
		return resultObj;
	}

	@Override
	public JSONObject cancelRide(String driver_mobno)
			throws JSONException {
		// TODO Auto-generated method stub
		JSONObject returnObj = new JSONObject();
		Session session = sessionFactory.openSession();
		try {
			Query query  = session.createQuery("update FuberDriversData set isBooked=0 where mobileNumber=:mobileNumber");		
			query.setParameter("mobileNumber", driver_mobno);
			int result = query.executeUpdate();
			if(result == 1) {
				returnObj.put("data", "Cab Cancelled Successfully");
			}
			else {
				returnObj.put("data", "Please try again");
			}
		}
		catch(Exception e) {
			returnObj.put("data", "Please try again");
			e.printStackTrace();
		}
		finally {
			session.clear();
			session.flush();
			session.close();
		}
		return returnObj;
	}

	@Override
	public JSONObject completeRide(String rideId, String driver_mobno, String d_lat,
			String d_lng, String carType) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject rideChargesObj = new JSONObject();
		Double destination_lat = Double.parseDouble(d_lat);
		Double destination_lng = Double.parseDouble(d_lng);
		Double source_lat=0.0;
		Double source_lng=0.0;
		
		Session session = sessionFactory.openSession();
		JSONObject resultObj = new JSONObject();
		
		try {
			Date endDate = new Date();
			Date startDate = null;
			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateFormatGmt.parse(dateFormatGmt.format(endDate)));
			Timestamp endTime = new Timestamp(cal.getTimeInMillis());
			
			Query query  = session.createQuery("Select d.lat, d.lng, r.startTime from FuberDriversData d, FuberRideDetailsData r where r.driverMobileNumber = d.mobileNumber and d.mobileNumber=:mobileNumber");		
			query.setParameter("mobileNumber", driver_mobno);
			query.setMaxResults(1);
			List <Object> results = query.list();
			Iterator resultItr = results.iterator();

			if(resultItr.hasNext()) {
					Object[] row = (Object[]) resultItr.next();
					JSONObject obj = new JSONObject();
					source_lat =  Double.parseDouble(String.valueOf(row[0]));
					source_lng =  Double.parseDouble(String.valueOf(row[1]));
					Timestamp ts = Timestamp.valueOf(String.valueOf(row[2]));
					startDate = new Date(ts.getTime());
			}
			long diff = endDate.getTime() - startDate.getTime();
	        int diffMinutes =(int) diff / (60 * 1000);
			Double kms = Math.sqrt((Math.pow((destination_lat-source_lat), 2)) + Math.pow((destination_lng-source_lng), 2)) * 10;
			float charges = (float) 0.0;
			if(carType.equalsIgnoreCase("Normal")) {
				charges = (float) ((kms*2) + diffMinutes);
			}
			else {
				charges = (float) ((kms*2) + diffMinutes + 5);
			}
			
			Query updateQuery  = session.createQuery("update FuberDriversData set isBooked=0 where mobileNumber=:mobileNumber");		
			updateQuery.setParameter("mobileNumber", driver_mobno);
			int result = updateQuery.executeUpdate();
			
			if(result == 1) {
				updateQuery = null;
				updateQuery  = session.createQuery("update FuberRideDetailsData set endTime=:endTime, charges=:charges where rideId=:rideId");		
				updateQuery.setParameter("rideId", rideId);
				updateQuery.setParameter("endTime", endTime);
				updateQuery.setParameter("charges", charges);			
				result = updateQuery.executeUpdate();
				if(result == 1) {
					JSONObject obj = new JSONObject();
					obj.put("kms", kms);
					obj.put("minutes", diffMinutes);
					obj.put("charges", charges);
					rideChargesObj.put("data", obj);
				}
				else {
					rideChargesObj.put("data", "Please try again");
				}
			}
			else {
				rideChargesObj.put("data", "Please try again");
			}
			
		}
		catch(Exception ex) {
			rideChargesObj.put("data", "Please try again");
			ex.printStackTrace();
		}
		finally {
			session.clear();
			session.flush();
			session.close();
		}
		return rideChargesObj;
	}

}
