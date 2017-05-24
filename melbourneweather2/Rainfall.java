package melbourneweather2;

import java.rmi.RemoteException;
import melbourneweather2.MelbourneWeather2Stub.*;

public class Rainfall {
	private static String locationToFind;
	private static String[] Rainfall;
	private static String rainfallTime = "";
	private static String time = "";
	private static String rainfall = "";
	private static String rainfallLoc;
	
	/********Constructor***********/
	public Rainfall(String Location){
		locationToFind = Location;
		try {
			getRainfallFromServerStage1();
		} catch (RemoteException e) {
			// Trace for exception
			e.printStackTrace();
		} catch (ExceptionException e) {
			// Trace for exception
			e.printStackTrace();
		}
	}
		
	public Rainfall(String Location, String rainfall,String time){
		this.locationToFind = Location;
		rainfallTime = rainfall;
		this.time = time;
	}
	
	public static String getRainfall(){
		rainfall = Rainfall[1].toString(); 
		return rainfall;
	}
	
	public static String getRainfallTime(){
		rainfallTime = Rainfall[0].toString();
		return rainfallTime;
	}
	
	public static String getRainfallLoc(){
		rainfallLoc = locationToFind;
		return rainfallLoc;
	}
	
	/*************Gets data from the server*************/
	public static String[] getRainfallFromServerStage1() throws RemoteException, ExceptionException{
		
		final MelbourneWeather2Stub MelbourneWeatherSation = new MelbourneWeather2Stub();
		GetRainfall RainfallRequest = new GetRainfall();
		RainfallRequest.setLocation(locationToFind);
		GetRainfallResponse RainfallResponse = MelbourneWeatherSation.getRainfall(RainfallRequest);
		Rainfall = RainfallResponse.get_return();
		/**********Used for testing****************//*
		System.out.println("THis is the size" + Rainfall.length);
		for (int i = 0;i < Rainfall.length;i++){
			System.out.print("Rainfall object values " + Rainfall[i]);
		}*/
		
		return Rainfall;
	}
	
	public String toString(){
		return "The rainfall is " + getRainfall() + "The time is:\t " + getRainfallTime();
	}
}

