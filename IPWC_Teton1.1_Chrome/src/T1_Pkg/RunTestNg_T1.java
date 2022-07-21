package T1_Pkg;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class RunTestNg_T1 {

	public static String instName = new String();
	public static String username = new String();
	public static String password = new String();
	public static String ElementOwner = new String();
	public static String shortElementOwner = new String(); 
	public static String partialName = new String();
	public static String expVersion = new String();
	public static String expBuild = new String();
	public static int sleepMultiple ;
	
	static String filePath = ClassLoader.getSystemClassLoader().getResource(".").getPath();

	public static void main(String[] args) {
        
		instName = args[0];
		username = args[1];	
		password = args[2]; 
		ElementOwner = args[3];
        shortElementOwner = args[4]; 
        partialName = args[5];	
        expVersion = args[6];
        expBuild = args[7];
        sleepMultiple = Integer.parseInt(args[8]);
        
      String geckoLoc = filePath.concat("Selenium_Resource/chromedriver.exe");
      System.setProperty("webdriver.chrome.driver", geckoLoc);
        
		// Create object of TestNG Class
		TestNG runner = new TestNG();

		// Create a list of String
		List<String> suitefiles = new ArrayList<String>();
		System.out.println(filePath);

		// Add xml file which you have to execute
		suitefiles.add(filePath.concat("/Selenium_Resource/SelScript_T1.xml"));

		// now set xml file for execution
		runner.setTestSuites(suitefiles);

		// finally execute the runner using run method
		runner.run();
		
		System.out.println("Creating line chart diagram for the test result");
		try {
			createLineChart_T1 lineObj1 = new createLineChart_T1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The execution is complete. The Line graph is generated");
	}

}
