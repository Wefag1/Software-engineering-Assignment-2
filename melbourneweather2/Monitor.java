package melbourneweather2;

import java.rmi.RemoteException;
import melbourneweathertimelapse.ExceptionException;
import melbourneweathertimelapse.MelbourneWeatherTimeLapseStub;
/************Interface***************/
public interface Monitor {
	/*****The locations for Stage1*******/
	public static String [] getlocations() throws RemoteException, ExceptionException, melbourneweather2.ExceptionException{
		String [] locations;
		final MelbourneWeather2Stub MelbourneWeatherSation = new MelbourneWeather2Stub();
		locations = MelbourneWeatherSation.getLocations().get_return();
		return locations;//Returns the locations available
	};
	
	/*****The locations for Stage 2*******/
	public static String [] getlocationsFromLapse() throws RemoteException, ExceptionException {
		String [] locations;
		final MelbourneWeatherTimeLapseStub MelbourneWeatherSationTimeLapse = new MelbourneWeatherTimeLapseStub();
		locations = MelbourneWeatherSationTimeLapse.getLocations().get_return();
		
		/****Testing the locations return*************/
		/*for(int i = 0; i < locations.length;i++){
			System.out.println(locations[i].toString());
		}*/
		
		return locations;//Returns the locations available
	};
	
}
