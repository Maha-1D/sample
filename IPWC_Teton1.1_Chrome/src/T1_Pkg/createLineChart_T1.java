package T1_Pkg;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;





import com.csvreader.CsvWriter;

public class createLineChart_T1 extends InitializeTest_T1{

	static String Name,Status ;
	static String series1 = "User : " + shortElementOwner;
	static String Time ;
	int seconds ;
	static String execTime;
	//static String[][] dataSet = new String[54][54];
	DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	CsvWriter writer = new CsvWriter(new FileWriter (filePath.concat("" +instName+"SeleniumScriptsReports/FF_"+instName+"_" + DateTime + "/"+instName+"_" + DateTime + "_ExecutionTime.csv")), ','); // windows machine
	CategoryPlot plot;
	PrintStream o = new PrintStream(new File(filePath.concat("" + instName+"SeleniumScriptsReports/FF_"+instName+"_" + DateTime + "/"+instName+"_" + DateTime + "ReportSummary.txt" )));
	PrintStream console = System.out;

    
	public createLineChart_T1() throws IOException, ParseException {
		
		
		JFreeChart lineChart = ChartFactory.createLineChart(
		         "Line Graph",
		         "Testcase Name","Execution Time",
		         acreateDataset(),
		         PlotOrientation.VERTICAL,
		         true,true,false);
		
		// Cross labels for X axis values
		Font font3 = new Font("Dialog", Font.PLAIN, 30); 
		Font font4 = new Font("Dialog", Font.PLAIN, 45); 
		
		CategoryPlot plot = (CategoryPlot)lineChart.getPlot();
		
		
        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
        ValueAxis yAxis = (ValueAxis)plot.getRangeAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
    
        xAxis.setLabelFont(font4);
        xAxis.setTickLabelFont(font3);
        
        yAxis.setLabelFont(font4);
        yAxis.setTickLabelFont(font3);
        // Get values on the points.
        LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) plot.getRenderer();
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
	        
		      int width = 3500; /* Width of the image */
		      int height = 2000; /* Height of the image */ 
		      File chartFile = new File(filePath.concat("" + instName+"SeleniumScriptsReports/FF_"+instName+"_" + DateTime + "/"+instName+"_" + DateTime + "_LineChart.jpeg" )); 
		      ChartUtilities.saveChartAsJPEG(chartFile ,lineChart, width ,height); 
	}

	public DefaultCategoryDataset acreateDataset( ) throws ParseException, IOException {
		
		 // DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		 driver.get("file://" + filePath.concat("" + instName+"SeleniumScriptsReports/FF_"+instName+"_" + DateTime + "/QLOTest_" + DateTime+ ".html"));
		 System.out.println((filePath.concat("" +instName+"_" + DateTime + "/QLOTest_" + DateTime+ ".html").replace('/', '\\')));
		  
		  writer.write("Test case Name");
		  writer.write("Status");
  		  writer.write("Execution Time");
  		 writer.endRecord();
	     //System.out.println("The test case details are :" + driver.findElement(By.id("test-view")).getText());
	      List<WebElement> testCaseName = driver.findElements(By.xpath("//div[@id = 'test-view']//ul[@id = 'test-collection']/li/div[1]/span[1]"));
	     for (WebElement span : testCaseName)
	      {
	    	      	 
	    	 Name = span.getText();
	    	 System.out.println("Name is : " +Name);
	    	 Status = span.findElement(By.xpath("./following-sibling::span/following-sibling::span")).getText();
	    	 System.out.println("Status is : " + Status);
	    	  Time = span.findElement(By.xpath("./ancestor::div[@class = 'test-heading']/following-sibling::div/div[1]/span[3]")).getAttribute("innerHTML");
	    	  System.out.println("Time is : "+ Time);
	    	  
	    	
	    	  // Assign o to output stream
	    	  System.setOut(o);
	    	  int min = Integer.valueOf(Time.substring(3, 5).replaceAll("[^0-9]", ""));
	    	  int sec = Integer.valueOf(Time.substring(6, 8).replaceAll("[^0-9]", ""));
	    	  int ms = Integer.valueOf(Time.substring(9).replaceAll("[^0-9]", ""));
	    	  Float timeOfExe = (min*60) + sec + (ms/1000f);
	    	System.out.println("Min : " + min + " sec : " +sec+ " ms : " + ms);
	    	System.out.println("The time of test case execution is : "+timeOfExe);
	    	
	    	 dataset.addValue(timeOfExe, "Test execution time", Name);
	    	  writer.write(Name);
	    	  writer.write(Status);
	    	  writer.write(Time);
	    	  writer.endRecord();
	    	  
	    	  System.out.println(Name +','+ Status + ',' + Time + ';' );
	    	  // Use stored value for output stream
	    	    System.setOut(console);
	    	   
	    	 	    	  
	    }
	     writer.close();
	     
	     return dataset;
	     
	      }
	
	public static void main(String[] args){
		
		try {
			createLineChart_T1 lineObj = new createLineChart_T1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The chart is generated");
		}
		
		 driver.close();
		 driver.quit();
	}

}
