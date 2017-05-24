package melbourneweathertimelapse;

import java.rmi.RemoteException;
import melbourneweathertimelapse.MelbourneWeatherTimeLapseStub.GetWeather;
import melbourneweathertimelapse.MelbourneWeatherTimeLapseStub.GetWeatherResponse;

public class WeatherTimelapse {

	private static String[] TimelapseData;
	private static String location1;
	private static boolean temperature1 = false;
	private static boolean rainfall1 = false;

	/******Default constructor******/
	public WeatherTimelapse(String location){
		this.location1 = location;
		try {
			getWeatherData();
		} catch (RemoteException e) {
			// Catches the error when creating a new weatherTimelapseStub object
			e.printStackTrace();
		} catch (melbourneweathertimelapse.ExceptionException e) {
			// Catches the error when creating a new weatherTimelapseStub object
			e.printStackTrace();
		}
	}

	/*****Non-default constructor********/
	public WeatherTimelapse(String location, boolean rainfall, boolean temperature){
		location1 = location;
		rainfall1 = rainfall;
		temperature1 = temperature;
		try {
			getWeatherData();
		} catch (RemoteException e) {
			// Catches the error when creating a new weatherTimelapseStub object
			e.printStackTrace();
		} catch (ExceptionException e) {
			// Catches the error when creating a new weatherTimelapseStub object
			e.printStackTrace();
		}
	}
	
	/*********Obtaining weather data from weatherTimeLapse2Stub********/
	public void getWeatherData() throws RemoteException, melbourneweathertimelapse.ExceptionException {
		final MelbourneWeatherTimeLapseStub MelbourneWeatherSationTimeLapse = new MelbourneWeatherTimeLapseStub();
		GetWeather  WeatherRequest = new GetWeather ();
		WeatherRequest.setLocation(location1);
		GetWeatherResponse WeatherResponse = MelbourneWeatherSationTimeLapse.getWeather (WeatherRequest);
		TimelapseData = WeatherResponse.get_return();
		//System.out.println( "The values for the time lapse are: " + TimelapseData[0]);
	}

	/*****Getter for time******/
	public static Double getTimeLapseTime(){
		String time = TimelapseData[0];
		System.out.println( "Values before altering them time: " + time);
		String[] parts = TimelapseData[0].split(" ");
		String[] parts2 = parts[1].split(":");
		String Attempt = parts2[0];
		double timeSend = Double.parseDouble(Attempt);
		System.out.println("\n" + "Attempt " + Attempt);
		for(int i = 0;i < parts.length;i++){
			System.out.println("\n" + parts[i]);
		}

		return timeSend;
	}

	/****Getter for the temperature******/
	public static Double getTimeLapseTemperature(){
		Double result = 0.0;
		if(temperature1 = true){
			String Temperature = TimelapseData[1];
			String TmpValue = Temperature;
			result = Double.parseDouble(Temperature);
			System.out.println( "The values for the time lapse are: " + result);
			result = (result - 273.15); //Changes it from kelvin to degrees celcius
			System.out.println( "Values before altering them temperature:  " + Temperature);
			TmpValue = result.toString();
			String TmpPrime = TmpValue.substring(0,4);
			result = Double.parseDouble(TmpPrime);
		}
		return result;
	}

	/****Getter for the rainfall*****/
	public static Double getTimeLapseRainfall(){
		Double result = 0.0;
		if(rainfall1 = true){
			String Rainfall = TimelapseData[2];
			String TmpRainfall;
			result = Double.parseDouble(Rainfall);
			result = result*10;
			TmpRainfall = Double.toString(result);
			System.out.println( "Values before altering them rainfall:  " + Rainfall);
		}
		return result;
	}

	/****ToString************/
	public void Print(){
		for (int i = 0;i < TimelapseData.length;i++){
			System.out.println( "The values for the time lapse are: " + "\n" + TimelapseData[i]);
		}

	}
}


