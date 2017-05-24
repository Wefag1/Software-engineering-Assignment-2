package melbourneweather2;
import java.lang.Exception;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Driver implements Monitor {

	private static String[] locations;
	//private static String[] locationsTimeLapse;

	/************Main driver********************/
	public static void main(String[] args) throws Exception{
		/*
		locationsTimeLapse = Monitor.getlocationsFromLapse();
		for(int i=0;i<locationsTimeLapse.length;i++){
			System.out.println(locationsTimeLapse[i]);
		}
		 */
		locations = Monitor.getlocations();

		/*******Testing**************/
		//System.out.println(locations.length);

		/***********Creates the GUI*****************/
		GUI.CreateGUI(locations);

		/**********Runs the update after 5 minutes***************/
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(GUI.Update(), 0, 300, TimeUnit.SECONDS);
		//GUI.LineChartEx.run();

		ScheduledExecutorService executorTimeLapse = Executors.newScheduledThreadPool(2);
		executorTimeLapse.scheduleAtFixedRate(GUI.UpdateTimeLapse(), 0, 30, TimeUnit.SECONDS);
	}
}


/******Principals used***********//*
//Open/Closed 
 * The classes are not able to be modified however they are able to send data which cant be changed 
 * so they send a copy of the data. This is done through the use of getters and setters. Here this is done in 
 * the weatherTimeLapse class and the GUI By creating objects of the class to obtain access to it methods.
//Liskov Substitution
 * The GUI must be able to use objects of the weather time lapse class without the class knowing that they 
 * are being used. Thus we have created objects TempObject of the weathertime lapse class, however 
 * we never reference or change the object within the weathertime lapse class.
 *
//Dependency inversion
 * High level modules should not depend on low level modules. 
 * The entire project depends on the monitor which is a interface without it the classes would throw a 
 * null pointer exception
 * 
 *
//Reuse/Release
 *This principal is implemented in the GUI class as well as the other classes. The methods can be reused and released
 *Such as the createinternalFrames method that creates the basic internal features however it is used several times 
 *After each use it is released and can be used again by another classes method.
 *
//Common reuse
 *The packages that are used within the classes are all reused if you use one of them 
 *This is obtained and maintain within this program as only the necessary packages are called and are reused 
 *when the methods are called once more. There are no unnecessary packages being called or implemented.
 *
/*Stable dependencies (The dependencies between packages should be in the direction of the stability of the packages. 
 *A package should only depend upon packages that are more stable than it is. )
 * Both the weathertimeLapse and the weatherMonitor Stage1 and 2 are only dependent on these two packages.  
 * The weather service itself is a stable package.
 *
//Interface segregation ( states that no client should be forced to depend on methods it does not use)
 * The methods that implement the interface are not affected by unused methods at all 
 * All the classes are independent and only have and use the methods they require in order to complete their tasks.
 *
 *
 *
//Common closure(Classes within a released component should share common closure. That is, if one needs to be changed, 
 * they all are likely to need to be changed. What affects one, affects all. ) 
 * 
 *
//Acyclic dependencies (the dependency graph of packages or components should have no cycles)
 * The construction of the graph all end at the display point and don't form any cycles between classes.
 *
 *
//stable abstractions (Packages that are maximally stable should be maximally abstract. Unstable packages should be concrete)
 * Most classes that were created are concrete classes 
 * The interface is inherently abstract
 * 
 *
//Observer monitor (Define a one-to-many dependency between objects so that when one object changes state, 
 * all its dependents are notified and updated automatically.)
 * 
 * 
 * 
 *
 */
