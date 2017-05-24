package melbourneweather2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import melbourneweathertimelapse.WeatherTimelapse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.InternalFrameEvent;

public class GUI  {

	/*********Stage1**********/
	static List<String> LocationUpdateStage1= new ArrayList <String>(); 
	static List<String> LocationUpdateStage2= new ArrayList <String>(); 
	static List<Boolean> TemperatureBoo= new ArrayList <Boolean>(); 
	static List<Boolean> RainfallBoo= new ArrayList <Boolean>();
	static List<Boolean> TemperatureBooStage2= new ArrayList <Boolean>(); 
	static List<Boolean> RainfallBooStage2= new ArrayList <Boolean>();
	static List<Object> Both= new ArrayList <Object>(); 
	static List<Object> Temperature= new ArrayList <Object>(); 
	static List<Object> Rainfall= new ArrayList <Object>(); 
	static List<Object> weatherTimeLapse = new ArrayList <Object>(); 
	static Object objectTemp;	
	static JComboBox<String> locationCombo;
	static JFrame mainFrame;
	static Image image;
	static Container pane;
	static JButton viewB, deleteB, addB;
	static JRadioButton rainfall, temp; 
	static JLabel allLocationsLabel, addLabel, rainfallL, tempL,output1,output2,output3,output4,output;
	static JTable allLoctaions;
	static JPanel fstPan, secPan, thirPan, fourPan, fifthPan; 
	static boolean Temp, rain = false; //return for buttons
	static boolean TempTest, rainTest = false;
	static String locationSelected = "0",rainSend ="",TempSend = ""; //locations to monitor
	static boolean Idle = false;
	static int clickCount = 0;
	static int selectedLocation;
	static boolean currentRain = false,currentTemp = false,currentBoth = false, updateArrayOnce = true;


	/******Stage2*********/
	static List<Double> rainTrueArray = new ArrayList <Double>(); 
	static List<Double> TempTrueArray = new ArrayList <Double>(); 
	static List<Double> BothRainTrueArray = new ArrayList <Double>(); 
	static List<Double> BothTempTrueArray = new ArrayList <Double>(); 
	static List<Double> BothTimeTrueArray = new ArrayList <Double>();
	static List<Double> rainTrueArrayUpdate = new ArrayList <Double>(); 
	static List<Double> TempTrueArrayUpdate = new ArrayList <Double>(); 
	static List<Double> BothRainTrueArrayUpdate = new ArrayList <Double>(); 
	static List<Double> BothTempTrueArrayUpdate = new ArrayList <Double>(); 
	static List<Double> BothTimeTrueArrayUpdate = new ArrayList <Double>(); 
	static JFrame StartUpFrame;
	static JPanel stage2Panel1;
	static JPanel stage2Panel1Sub;
	static JPanel stage2Panel1Sub2;
	static JLabel description;
	static JPanel PanelStage1;
	static JPanel displayText;
	static JPanel displayGraph;
	static JLabel displayLabelText;
	static JLabel displayLabelGraph;
	static JRadioButton textDisplay, graphicalDisplay;
	static boolean toText = false, toGraph = false;
	static String rainfallTitle = "",tempTitle = "",BothTitle = "",titel = "", measureMent = "";
	static double time;
	static int RunUpdate = 0;
	static JFreeChart chart;
	static boolean update = false;
	static JLabel imageLabel;


	
	public static void  CreateGUI  (String [] locations) throws IOException{

		/*********Locations from monitor**************/
		locationSelected = locations[0];

		/*************Main frame of GUI***************/
		mainFrame = new JFrame ("Welcome to the Melbourne Weather Monitor");
		mainFrame.setSize(1000,800);
		mainFrame.setLayout(new BorderLayout());

		/**************First Panel*********************/ 
		fstPan = new JPanel();
		fstPan.setLayout(new BorderLayout());
		addLabel = new JLabel("Add a Location");
		fstPan.add(addLabel, BorderLayout.SOUTH);
		//description = new JLabel("");

		/****************RadioButton rainfall***********/
		rainfall = new JRadioButton("Rainfall");
		fstPan.add(rainfall, BorderLayout.CENTER);
		ButtonGroup rain_temp = new ButtonGroup();
		rain_temp.add(rainfall);

		/*************RadioButton temperature***********/
		temp = new JRadioButton("Temperature");
		fstPan.add(temp, BorderLayout.SOUTH);
		ButtonGroup Temp_temp = new ButtonGroup();
		Temp_temp.add(temp);

		/***********Panel five**************************/
		fifthPan = new JPanel();
		fifthPan.setLayout(new BorderLayout());
		fifthPan.setSize(300,300);
		fifthPan.setVisible(true);

		/*********Stage2 panel1 ***********/
		description = new JLabel("Please select the type of display monitor");
		stage2Panel1 = new JPanel();
		stage2Panel1Sub = new JPanel();
		stage2Panel1Sub2 = new JPanel();
		stage2Panel1Sub.setLayout(new FlowLayout());
		stage2Panel1Sub2.setLayout(new FlowLayout());
		stage2Panel1.setLayout(new BorderLayout());
		textDisplay = new JRadioButton("Text display monitor");
		graphicalDisplay = new JRadioButton("Graphical display monitor");
		ButtonGroup monitorType = new ButtonGroup();
		monitorType.add(textDisplay);
		monitorType.add(graphicalDisplay);
		stage2Panel1Sub2.add(description, BorderLayout.NORTH);
		stage2Panel1Sub.add(textDisplay, BorderLayout.NORTH);
		stage2Panel1Sub.add(graphicalDisplay, BorderLayout.SOUTH);
		stage2Panel1.add(stage2Panel1Sub, BorderLayout.SOUTH);
		stage2Panel1.add(stage2Panel1Sub2, BorderLayout.NORTH);

		displayText = new JPanel();
		displayGraph = new JPanel();
		displayText.setLayout(new FlowLayout());
		displayGraph.setLayout(new FlowLayout());
		//InputStream input = GUI.class.getResourceAsStream("Rain_drops.jpg");
		//image.getGraphics(input);
		ImageIcon thisImage = new ImageIcon("Rain_drops.jpg");
		//thisImage.setImage(image);
		//image = ImageIO.read(input);
		JLabel label = new JLabel(thisImage);
		//stage2Panel1Sub.setBackground(label);
		//stage2Panel1Sub2.add(label, BorderLayout.SOUTH);
		//mainFrame.add(label, BorderLayout.SOUTH);
		//imageLabel.add(input);
		//mainFrame.revalidate();

		/****Drop down list for locations****/
		locationCombo = new JComboBox<String>(locations);
		fstPan.add(locationCombo, BorderLayout.NORTH);
		System.out.println("length " + locations.length);
		locationCombo.addActionListener(new ActionListener(){

			/*********Action listener for the drop down box*****************/
			@Override
			public void actionPerformed(ActionEvent e) {
				locationCombo = (JComboBox)e.getSource();
				locationSelected = (String) locationCombo.getSelectedItem();

				/***********Testing************/
				//System.out.println(locationSelected);
				for(int i = 0;i <= locations.length;i++){
					if(locations[i].equals(locationSelected)){
						selectedLocation = i--;
						i = locations.length;
					}
				}
				/*************testing********************/	
				//selectedLocation =   CheckLocation(locationSelected);
				//System.out.println("The location selected" + selectedLocation);

			}

		});

		/**********rainfall action listener******************/

		rainfall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rainfall.isSelected()){
					rain = true;
					rainTest = rain;
				}
				//unchecks the radio button
				if (++clickCount % 2 == 0) {

					rain_temp.clearSelection();
					rain = false;
					rainTest = rain;
				}
			}
		});


		/***********temperature action***************/
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(temp.isSelected() == true){
					Temp = true;
					TempTest = Temp;
				}
				if (++clickCount % 2 == 0) {

					Temp_temp.clearSelection();
					Temp = false;
					TempTest = Temp;
				}
			}
		});

		/********Display choice********/
		/********Text action***********/
		textDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textDisplay.isSelected() == true){
					toText = true;
					toGraph = false;
				}
			}
		});

		/*******Graph action***********/
		graphicalDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphicalDisplay.isSelected() == true){
					toGraph = true;
					toText = false;
				}
			}
		});



		/***************SecondPanel**********************/ 
		secPan = new JPanel();
		addB = new JButton("add");

		addB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					if(toText == true && toGraph == false){

						System.out.println("The value of toText is Stage 1 : " + toText);
						System.out.println("The value of toGraph is Stage 1 : " + toGraph);
						/************testing********************/
						//System.out.println(" RainCheck " + rain + "Location " + locationSelected + "Temperature " + Temp);
						LocationUpdateStage1.add(locationSelected);
						RainfallBoo.add(rain);
						TemperatureBoo.add(Temp);
						/*************Checks if the boolean for temperature and rainfall is checked******************/
						if(rain == true & Temp == false){
							Rainfall rainObject = new Rainfall(locationSelected);
							Rainfall.add(rainObject);
							currentRain = true;

							/****************Testing********************/
							System.out.print("This is rainfall object 1 " + Rainfall.get(0).toString());
						}

						if(Temp == true & rain == false){
							Temperature tempObject = new Temperature(locationSelected);
							Temperature.add(tempObject);

							/****************Testing********************/
							System.out.print("This is temperature object 1 " + Temperature.get(0).toString());
							currentTemp = true;
						}
						if (Temp == true & rain == true){
							Rainfall rainObject1 = new Rainfall(locationSelected);
							Temperature tempObject1 = new Temperature(locationSelected);
							Both.add(rainObject1);
							Both.add(tempObject1);

							/****************Testing********************/
							System.out.print("This is both object 1 " + Both.get(0).toString());
							currentBoth = true;
						}

					}
					if (toGraph == true && toText == false){
						System.out.println("The value of toGraph is Stage 2: " + toGraph);
						System.out.println("The value of toText is Stage 2 : " + toText);	
						if(rain == true & Temp == false){
							LocationUpdateStage2.add(locationSelected);
							RainfallBooStage2.add(rain);
							TemperatureBooStage2.add(Temp);
							WeatherTimelapse tempObject = new WeatherTimelapse(locationSelected,rain,Temp);
							weatherTimeLapse.add(tempObject);
							rainTrueArray.add(WeatherTimelapse.getTimeLapseRainfall());
							if(rainTrueArray.size() == 1){
								rainTrueArrayUpdate.add(rainTrueArray.get(0));
							}
						}
						if(Temp == true & rain == false){
							LocationUpdateStage2.add(locationSelected);
							RainfallBooStage2.add(rain);
							TemperatureBooStage2.add(Temp);
							WeatherTimelapse tempObject = new WeatherTimelapse(locationSelected,rain,Temp);
							weatherTimeLapse.add(tempObject);
							TempTrueArray.add((WeatherTimelapse.getTimeLapseTemperature()));
							if(TempTrueArray.size() == 1){
								TempTrueArrayUpdate.add(TempTrueArray.get(0));
							}
						}
						if (Temp == true & rain == true){
							LocationUpdateStage2.add(locationSelected);
							RainfallBooStage2.add(rain);
							TemperatureBooStage2.add(Temp);
							WeatherTimelapse tempObject = new WeatherTimelapse(locationSelected,rain,Temp);
							weatherTimeLapse.add(tempObject);
							BothRainTrueArray.add((WeatherTimelapse.getTimeLapseRainfall()));
							BothTimeTrueArray.add((WeatherTimelapse.getTimeLapseTime()));
							BothTempTrueArray.add((WeatherTimelapse.getTimeLapseTemperature()));
							if(BothRainTrueArray.size() == 1){
								BothRainTrueArrayUpdate.add(BothRainTrueArray.get(0));
								BothTimeTrueArrayUpdate.add(BothTimeTrueArray.get(0));
								BothTempTrueArrayUpdate.add(BothTempTrueArray.get(0));
							}
						}
						System.out.print("Time Lapse object new : " + weatherTimeLapse.get(0).toString());
						System.out.println("The location is : " + locationSelected);
						System.out.println("The time is : " + melbourneweathertimelapse.WeatherTimelapse.getTimeLapseTime());
						System.out.println("The temperature is : " + melbourneweathertimelapse.WeatherTimelapse.getTimeLapseTemperature());
						System.out.println("The rainfall is : " + melbourneweathertimelapse.WeatherTimelapse.getTimeLapseRainfall());
						System.out.println("Length of arrays : " + "\n" + LocationUpdateStage2.size() + "\n" +  RainfallBooStage2.size() 
						+ "\n" + TemperatureBooStage2.size());
					}

					rain_temp.clearSelection();
					Temp_temp.clearSelection();
					resetButtons();
					monitorType.clearSelection();
				}

			}
		}

				);

		/*****************Labels used for output panels********************/
		output1 = new JLabel();
		output2 = new JLabel();
		output3 = new JLabel();
		output4 = new JLabel();
		output =  new JLabel();

		secPan.add(addB);

		/*******************Third Panel**********************/
		thirPan = new JPanel();
		secPan.add(addB);

		/********************View button listener************/
		allLocationsLabel = new JLabel("Locations");
		thirPan.add(allLocationsLabel);
		viewB = new JButton("view");
		thirPan.add(viewB);
		viewB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(toText == true && toGraph == false){
					createForms();
					toText = false;
				}
				if (toGraph == true && toText == false){
					createGraphs(locationSelected, rainTest,TempTest);
					toGraph = false;	
				}
				rainTest = false;
				TempTest = false;
			}
		});

		/************Fourth Panel*******************/ 
		fourPan = new JPanel();
		fourPan.setLayout(new FlowLayout());
		fourPan.add(fstPan);
		fourPan.add(secPan);
		fourPan.add(thirPan);

		PanelStage1 = new JPanel();
		BorderLayout layout = new BorderLayout();
		layout.setHgap(500);
		PanelStage1.setLayout(layout);
		PanelStage1.add(fourPan, BorderLayout.SOUTH);
		PanelStage1.add(stage2Panel1, BorderLayout.NORTH);

		/************Main Frame*******************/ 
		mainFrame.add(PanelStage1, BorderLayout.NORTH);
		int eb = 10;
		fourPan.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(eb, 30, eb, 30), // outer border
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
		stage2Panel1.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(eb, 30, eb, 30), // outer border
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));

		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*******Resets the buttons**************/
	public static void resetButtons(){

		rain = false;
		Temp = false;
	}

	/************Update of the User interface*********************/
	public static void createUpdate(){
		for(int i = 0;i <= LocationUpdateStage1.size();i++){
			locationSelected = LocationUpdateStage1.get(i);
			rain = RainfallBoo.get(i);
			Temp = TemperatureBoo.get(i);
			if(rain == true & Temp == false){
				Rainfall rainObject = new Rainfall(locationSelected);
				Rainfall.add(rainObject);
				currentRain = true;
				System.out.print("This is rainfall object 1 " + Rainfall.get(0).toString());
				createForms();
			}
			if(Temp == true & rain == false){
				Temperature tempObject = new Temperature(locationSelected);
				Temperature.add(tempObject);
				System.out.print("This is temperature object 1 " + Temperature.get(0).toString());
				currentTemp = true;
				createForms();
			}
			if (Temp == true & rain == true){
				Rainfall rainObject1 = new Rainfall(locationSelected);
				Temperature tempObject1 = new Temperature(locationSelected);
				Both.add(rainObject1);
				Both.add(tempObject1);
				System.out.print("This is both object 1 " + Both.get(0).toString());
				currentBoth = true;
				createForms();
			}
		}
	}

	/****Creates the frames and panels for the graphs*****/
	public static void panelCreationBoth(){
		JPanel panelDisplay = new JPanel();
		JInternalFrame parent = new JInternalFrame(locationSelected, true, true, false);
		panelDisplay.setLayout(new BorderLayout());
		panelDisplay.add(output2, BorderLayout.NORTH);
		panelDisplay.add(output1, BorderLayout.CENTER);
		panelDisplay.add(output, BorderLayout.SOUTH);
		panelDisplay.setSize(300,300);
		panelDisplay.revalidate();
		parent.add(panelDisplay);
		parent.setSize(300, 300);
		parent.setVisible(true);
		parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		parent.revalidate();
		displayText.add(parent);
		fifthPan.add(displayText, BorderLayout.WEST);
	}

	/****Creates the panels for both temp and rain to display for the GUI*****/
	public static void panelCreation(){
		JPanel panelDisplay = new JPanel();
		JInternalFrame parent = new JInternalFrame(locationSelected, true, true, false);
		panelDisplay.setLayout(new BorderLayout());
		panelDisplay.add(output3, BorderLayout.NORTH);
		panelDisplay.add(output2, BorderLayout.CENTER);
		panelDisplay.add(output1, BorderLayout.SOUTH);
		panelDisplay.setSize(300,300);
		panelDisplay.revalidate();
		parent.add(panelDisplay);
		parent.setSize(300, 300);
		parent.setVisible(true);
		parent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		parent.revalidate();
		displayText.add(parent);
		fifthPan.add(displayText, BorderLayout.WEST);
	}

	/*************Creates the panels that are used to display data to user******************/
	public static void createForms(){ 
		if(Both.size() >= 1 & currentBoth == true){
			for(int i = 0;i < 1;i++){
				objectTemp = (Both.get(Both.size()-1));

				/***************Testing*************/
				//System.out.println("Object values " + objectTemp.toString());

				/***************Change labels to output************/
				output = new JLabel ("The rainfall is: " + melbourneweather2.Rainfall.getRainfall() + " MM");
				output1 = new JLabel ("The temperature is: " + melbourneweather2.Temperature.getTemperature() + " °C");
				output2 = new JLabel ("The time is:" + melbourneweather2.Temperature.getTemperatureTime());

				/************panel formating*************/
				panelCreationBoth();
				currentBoth = false;
			}
		}
		if(Rainfall.size() >= 1 & currentRain == true){
			for(int i = 0;i < 1;i++){
				objectTemp = (Rainfall.get(Rainfall.size()-1));

				/***************Testing*************/
				//System.out.println("Object values " + objectTemp.toString());

				/***************Change labels to output************/
				output1 = new JLabel ("The rainfall is: \t" + melbourneweather2.Rainfall.getRainfall() + " MM");
				output2 = new JLabel ("The time is: \t" + melbourneweather2.Rainfall.getRainfallTime());
				output3 = new JLabel ("The location is: " + melbourneweather2.Rainfall.getRainfallLoc());

				/************panel formating*************/
				panelCreation();
				currentRain = false;
			}
		}
		if(Temperature.size() >= 1 & currentTemp == true){
			for (int i = 0;i < 1;i++){
				objectTemp = (Temperature.get(Temperature.size()-1));

				/***************Testing*************/
				//System.out.println("Object values " + objectTemp.toString());

				/***************Change labels to output************/
				output1 = new JLabel ("The temperature is: \t" + melbourneweather2.Temperature.getTemperature() + " °C");
				output2 = new JLabel ("The time is: \t" + melbourneweather2.Temperature.getTemperatureTime());
				output3 = new JLabel ("The location is: " + melbourneweather2.Temperature.getTemperatureLoc());

				/************panel formating*************/
				panelCreation();
				currentTemp = false;
				/*
				JButton close = new JButton( "Close Me!" );  
			    close.addActionListener( new ActionListener() {  
			        public void actionPerformed( ActionEvent e ) {
			                try {
			                	parent.setClosed( true );
			                	//LocationUpdateStage1.get(index);
			                	LocationUpdateStage1.remove(LocationUpdateStage1.indexOf(locationSelected));
			                	System.out.println("The values in the array are: " + LocationUpdateStage1.size());
			                } catch (PropertyVetoException e1) {
			                    e1.printStackTrace();
			                }
			        }  
			    } );  
			    parent.add( close );  */
			}
		}
		TitledBorder Border = new TitledBorder("Text Monitors");
		displayText.setBorder(Border);
		mainFrame.add(displayText, BorderLayout.CENTER);
		fifthPan.setVisible(true);
		mainFrame.revalidate();

		/**************Testing*****************/
		//System.out.println("BLAH BLAH");
		//System.out.println(Both.size() + " T " + Temperature.size() + " R " + Rainfall.size());
		//Created by Wayde and CHi CHI


	}

	/****Creates the internal frame for the graphs*****/
	public static void creatInternalFrame(JFreeChart chart, String title,String location){
		JInternalFrame displayGraphParent = new JInternalFrame(locationSelected, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		displayGraphParent.add(chartPanel);
		displayGraphParent.setTitle(title + locationSelected);
		displayGraphParent.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		displayGraphParent.setVisible(true);
		chartPanel.setPreferredSize(new Dimension (310,310));
		displayGraph.add(displayGraphParent);	
	}

	/**** Creates the dataset for the graph as well as runs the create graphs method******/
	public static void createGraphs(String locationSelected, boolean rain, boolean Temp){{
		/********rain***********/
		String locate = locationSelected;
		XYSeriesCollection dataset = new XYSeriesCollection();

		RunUpdate++;

		if(rain == true & Temp == false){
			System.out.println("This is method 1");
			rainfallTitle = "Rainfall at: ";
			titel = "Rainfall";
			measureMent = " mm";
			System.out.println("The value of the update true/false: " + update);
			if(update == true){
				XYSeries series = new XYSeries("Rainfall");
				System.out.println("The array length is for rain objects : " + rainTrueArrayUpdate.size());
				for(int i = 0;i < rainTrueArrayUpdate.size();i++){
					series.add(WeatherTimelapse.getTimeLapseTime() + i, rainTrueArrayUpdate.get(i));
				}
				dataset.addSeries(series);
				/**************/
				JFreeChart chart = createChart(dataset,titel);
				creatInternalFrame(chart,rainfallTitle,locate);
			}
			else{
				XYSeries series = new XYSeries("Rainfall");
				System.out.println("The array length for rain objects : " + rainTrueArray.size());
				for(int i = 0;i < rainTrueArray.size();i++){
					series.add(WeatherTimelapse.getTimeLapseTime() + i, rainTrueArray.get(i));
				}
				dataset.addSeries(series);
				JFreeChart chart = createChart(dataset,titel);
				creatInternalFrame(chart,rainfallTitle,locate);
			}
		}
		/*******Temperature*********/
		if(rain == false & Temp == true){
			System.out.println("This is method 2");
			tempTitle = "Temperature at: ";
			titel = "temperature";
			measureMent = " °C";
			if(update){
				XYSeries series = new XYSeries("Temperature");
				for(int i = 0;i < TempTrueArrayUpdate.size();i++){
					series.add(WeatherTimelapse.getTimeLapseTime() + i, TempTrueArrayUpdate.get(i));
					System.out.println("This is the temp array for update" +"\n" + TempTrueArrayUpdate.get(i));
				}
				dataset.addSeries(series);
				JFreeChart chart = createChart(dataset,titel);
				creatInternalFrame(chart,tempTitle,locate);  	
			} 
			else{
				XYSeries series = new XYSeries("Temperature");
				for(int i = 0;i < TempTrueArray.size();i++){
					series.add(WeatherTimelapse.getTimeLapseTime() + i, TempTrueArray.get(i));
					System.out.println("This is the temp array for standard " +"\n" + TempTrueArray.get(i));
				}
				/*****************/
				dataset.addSeries(series);
				JFreeChart chart = createChart(dataset,titel);
				creatInternalFrame(chart,tempTitle,locate);
			} 
		}
		/*********Both rain and temp**********/
		if(rain == true & Temp == true){
			System.out.println("This is method 3");
			BothTitle = "Rainfall and temperature at: ";
			titel = "rainfall and temperature";
			measureMent = "(mm)&(°C)";
			if(update){
				XYSeries series = new XYSeries("RainFall for Both");
				XYSeries series2 = new XYSeries("Temperature for Both");

				/******testing***********//*
			for(int i = 0; i< BothRainTrueArray.size();i++){
				System.out.println("Rainfall update data: " + BothRainTrueArray.get(i));
			}
			for(int i = 0; i< BothTimeTrueArray.size();i++){
				System.out.println("Rainfall update data: " + BothTimeTrueArray.get(i));
			}
			for(int i = 0; i< BothTempTrueArray.size();i++){
				System.out.println("Rainfall update data: " + BothTempTrueArray.get(i));
			}*/


				for(int i = 0;i < BothRainTrueArrayUpdate.size();i++){
					series.add(WeatherTimelapse.getTimeLapseTime() + i, BothRainTrueArrayUpdate.get(i));
					System.out.println("\nBoth array rain output" + BothRainTrueArrayUpdate.get(i) + BothTimeTrueArrayUpdate.get(i));
				}
				for(int i = 0;i < BothTempTrueArrayUpdate.size();i++){
					series2.add(WeatherTimelapse.getTimeLapseTime() + i, BothTempTrueArrayUpdate.get(i));
					System.out.println("\nBoth array temperature output update ran" + "\n" +  BothTempTrueArrayUpdate.get(i));
				}

				dataset.addSeries(series);
				dataset.addSeries(series2);
				JFreeChart chart = createChart(dataset,titel);
				creatInternalFrame(chart,BothTitle,locate);
			}
			else{
				XYSeries series = new XYSeries("RainFall for Both");
				XYSeries series2 = new XYSeries("Temperature for Both");
				for(int i = 0;i < BothRainTrueArray.size();i++){
					series.add(WeatherTimelapse.getTimeLapseTime() + i, BothRainTrueArray.get(i));
					System.out.println("standard run of the both time lapse" + BothRainTrueArray.get(i) + BothTimeTrueArray.get(i));
				}
				for(int i = 0;i < BothTempTrueArray.size();i++){
					series2.add(WeatherTimelapse.getTimeLapseTime() + i, BothTempTrueArray.get(i));
					System.out.println("standard run of the both time lapse" + "\n" +  BothTempTrueArray.get(i));
				}
				dataset.addSeries(series);
				dataset.addSeries(series2);
				JFreeChart chart = createChart(dataset,titel);
				creatInternalFrame(chart,BothTitle,locate);
			}
		}
		TitledBorder Border = new TitledBorder("Graphical Monitors");
		displayGraph.setBorder(Border);
		displayGraph.setVisible(true);
		mainFrame.add(displayGraph, BorderLayout.SOUTH);
		mainFrame.revalidate();
		//update = false;
		System.out.print("Update array lenght after runs " + LocationUpdateStage2.size() + " end");

	}
	}

	/**** Creates the data for the update ******/
	public static Boolean displayOutputUpdate(){
		for(int n = 0;n <= LocationUpdateStage2.size() -1;n++){
			String locationSelected = LocationUpdateStage2.get(n);
			Boolean rain = RainfallBooStage2.get(n);
			Boolean Temp = TemperatureBooStage2.get(n);
			System.out.println("THe value of the index :" + n);
			//update = true;
			if(updateArrayOnce){
				rainTrueArrayUpdate.add(rainTrueArray.get(0));
				TempTrueArrayUpdate.add(TempTrueArray.get(0));
				BothRainTrueArrayUpdate.add(BothRainTrueArray.get(0));
				BothTimeTrueArrayUpdate.add(BothTimeTrueArray.get(0));
				BothTempTrueArrayUpdate.add(BothTempTrueArray.get(0));
			}
			if(rain == true & Temp == false){
				WeatherTimelapse tempObject = new WeatherTimelapse(locationSelected,rain,Temp);
				tempObject.Print();
				weatherTimeLapse.add(tempObject);
				time = (WeatherTimelapse.getTimeLapseTime());
				System.out.print("Rain update data " + melbourneweathertimelapse.WeatherTimelapse.getTimeLapseRainfall() + "\n" + " time: " + time);
				System.out.print("Rain update data " + WeatherTimelapse.getTimeLapseRainfall() + "\n" + " time: " + time);
				rainTrueArrayUpdate.add(WeatherTimelapse.getTimeLapseRainfall());
				//update = true;
				System.out.println("This might work" + update);
				createGraphs(locationSelected,rain,Temp);
				System.out.println("This might work2" + update);

			}
			if(Temp == true & rain == false){
				WeatherTimelapse tempObject = new WeatherTimelapse(locationSelected,rain,Temp);
				weatherTimeLapse.add(tempObject);
				tempObject.Print();
				//System.out.println("Testing here " + tempObject.getTimeLapseTemperature());
				time = (WeatherTimelapse.getTimeLapseTime());
				System.out.print("Temperature update data " + melbourneweathertimelapse.WeatherTimelapse.getTimeLapseTemperature() + "\n" + "time: " + melbourneweathertimelapse.WeatherTimelapse.getTimeLapseTime());
				System.out.print("Temperature update data " + WeatherTimelapse.getTimeLapseTemperature() + "\n" + "time: " + time);
				TempTrueArrayUpdate.add((WeatherTimelapse.getTimeLapseTemperature()));
				//update = true;
				System.out.println("This might work" + update);
				createGraphs(locationSelected, rain,Temp);
				System.out.println("This might work2" + update);
			}
			if (Temp == true & rain == true){
				WeatherTimelapse tempObject = new WeatherTimelapse(locationSelected,rain,Temp);
				weatherTimeLapse.add(tempObject);
				tempObject.Print();
				time = (WeatherTimelapse.getTimeLapseTime());
				System.out.print("Both update data " + melbourneweathertimelapse.WeatherTimelapse.getTimeLapseRainfall() + "\n" + WeatherTimelapse.getTimeLapseTemperature() + "/n" + "time: " + melbourneweathertimelapse.WeatherTimelapse.getTimeLapseTime());
				System.out.print("Both update data " + WeatherTimelapse.getTimeLapseRainfall() + "\n" + WeatherTimelapse.getTimeLapseTemperature() + "/n" + "time: " + time);
				BothRainTrueArrayUpdate.add((WeatherTimelapse.getTimeLapseRainfall()));
				BothTimeTrueArrayUpdate.add((WeatherTimelapse.getTimeLapseTime()));
				BothTempTrueArrayUpdate.add((WeatherTimelapse.getTimeLapseTemperature()));
				//update = true;
				System.out.println("This might work" + update);
				createGraphs(locationSelected, rain,Temp);
				System.out.println("This might work2" + update);
			}

		}
		update = false;
		return true;
	}

	/***Method creates the chart itself****/
	public static JFreeChart createChart(XYDataset dataset,String titel){
		JFreeChart chart = ChartFactory.createXYLineChart(
				titel + " against time ", 
				"Time", 
				titel + measureMent, 
				dataset, 
				PlotOrientation.VERTICAL,//was VERTICAL
				true, 
				false, 
				false 
				);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.ORANGE);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		NumberAxis yAxis = new NumberAxis("");
		yAxis.setAutoRangeIncludesZero(false); 
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(false);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(false);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("Average " + titel + " against time",
				new Font("Serif", java.awt.Font.BOLD, 18)
				)
				);

		return chart; 
	}

	/**************Creates an update 5 Minutes ***************/
	public static Runnable Update(){

		Runnable updateMonitor = new Runnable() {
			public void run() {
				displayText.removeAll();
				//fifthPan.removeAll();
				if (Rainfall.size() > 0 ){
					createUpdate();						
				}
				if (Temperature.size() > 0 ){
					createUpdate();
				}
				if (Both.size() > 0 ){
					createUpdate();
				}
				/***********Testing the update runs***********/
				System.out.println("Updated stage 1 text display after 5 minutes");
			}
		};
		return updateMonitor;
	}

	/**************Creates an update 20 seconds ***************/
	public static Runnable UpdateTimeLapse (){

		Runnable updateMonitor = new Runnable() {
			@Override
			public void run () {
				try{
					displayGraph.removeAll();
					mainFrame.remove(displayGraph);
					displayGraph.revalidate();
					if (LocationUpdateStage2.size() > 0 ){
						update = true;
						updateArrayOnce = false;
						displayOutputUpdate();
					}
				}catch( Exception e){
					System.out.println(e);
				}

				/***********Testing the update runs***********/
				System.out.println("Updated stage 2 after 20 seconds");

			}
		};
		return updateMonitor;
	}

}




