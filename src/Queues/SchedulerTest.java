import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the process scheduler simulation.
 * One example input file is created and
 * the resulting output file is checked for
 * correct contents.
 */
public class SchedulerTest {

	// Inputs for first test case
	public static final String INPUT_NAME1 = "InputExample1.txt";
	public static final int TIMEOUT1 = 100;
	public static final String OUTPUT_NAME1 = "ExpectedOutput1.txt";

	public static final String INPUT_NAME2 = "InputExample2.txt";
	public static final int TIMEOUT2 = 100;
	public static final String OUTPUT_NAME2 = "ExpectedOutput2.txt";

	public static final String INPUT_NAME3 = "InputExample3.txt";
	public static final int TIMEOUT3 = 50;
	public static final int TIMEOUT3v2 = 100;
	public static final String OUTPUT_NAME3 = "ExpectedOutput3.txt";

	public static final String INPUT_NAME4 = "InputExample4.txt";
	public static final int TIMEOUT4 = 250;
	public static final String OUTPUT_NAME4 = "ExpectedOutput4.txt";

	public static final String INPUT_NAME5 = "InputExample5.txt";
	public static final int TIMEOUT5 = 75;
	public static final String OUTPUT_NAME5 = "ExpectedOutput5.txt";

	public static final String INPUT_NAME6 = "InputExample6.txt";
	public static final int TIMEOUT6 = 150;
	public static final String OUTPUT_NAME6 = "ExpectedOutput6.txt";

	/**
	 * This setup method creates several input files
	 * for use with the simulator.
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrintStream ps1 = new PrintStream(new File(INPUT_NAME1));
		ps1.println("0      100  Initialization");
		ps1.println("0      200  Background service");
		ps1.println("10     150  Compiler");
		ps1.println("600    300  Virus scan");
		ps1.println("600    10   Quick process");
		ps1.println("610    150  Browser");
		ps1.println("1630   400  Movie clip");
		ps1.println("1890   350  Email");
		ps1.println("2100   100  Data processing");
		ps1.close();

		PrintStream ps2 = new PrintStream(new File(INPUT_NAME2));
		ps2.println("0      10   Initialization");
		ps2.println("0      10   Browser");
		ps2.println("0      101  iTunes");
		ps2.println("300    210  Safari");
		ps2.println("500    40   Email");
		ps2.close();

		PrintStream ps3 = new PrintStream(new File(INPUT_NAME3));
		ps3.println("75      75   Initialization");
		ps3.println("75      76   Data processing");
		ps3.println("75      77   Email");
		ps3.println("75      78   Virus scan");
		ps3.println("75      79   Compiler");
		ps3.close();

		PrintStream ps4 = new PrintStream(new File(INPUT_NAME4));
		ps4.println("0        100   Initialization");
		ps4.println("251      251   Email");
		ps4.println("252      502   Virus scan");
		ps4.println("500      78    Spotify");
		ps4.println("1000     300   Chrome");
		ps4.close();

		PrintStream ps5 = new PrintStream(new File(INPUT_NAME5));
		ps5.println("0      74   Initialization");
		ps5.println("0      500  Background service");
		ps5.println("0      150  Compiler");
		ps5.println("1200   200  Virus scan");
		ps5.println("1200   100  Quick process");
		ps5.println("1500   5    Browser");
		ps5.println("1630   5    Movie clip");
		ps5.println("2000   75   Email");
		ps5.println("2000   150  Data processing");
		ps5.println("3000   300  Browser");
		ps5.println("3000   75   Movie clip");
		ps5.println("3500   10   Email");
		ps5.println("3500   10   Data processing");
		ps5.close();

		PrintStream ps6 = new PrintStream(new File(INPUT_NAME6));
		ps6.println("0      100  Initialization");
		ps6.println("10     200  Background service");
		ps6.println("20     150  iTunes");
		ps6.println("30     300  Virus scan");
		ps6.println("40     10   Email");
		ps6.println("50     150  Browser");
		ps6.println("60     400  Movie clip");
		ps6.println("70     350  Sound clip");
		ps6.println("80     100  Chrome");
		ps6.println("90     150  Browser");
		ps6.println("100    400  Movie clip");
		ps6.println("500    350  Email");
		ps6.println("5000   100  Data processing");
		ps6.close();
	}

	/**
	 * After testing, all input files and output files must be deleted.
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		new File(INPUT_NAME1).delete();
		new File(OUTPUT_NAME1).delete();
		new File(INPUT_NAME2).delete();
		new File(OUTPUT_NAME2).delete();
		new File(INPUT_NAME3).delete();
		new File(OUTPUT_NAME3).delete();
		new File(INPUT_NAME4).delete();
		new File(OUTPUT_NAME4).delete();
		new File(INPUT_NAME5).delete();
		new File(OUTPUT_NAME5).delete();
		new File(INPUT_NAME6).delete();
		new File(OUTPUT_NAME6).delete();
	}

	/**
	 * Test the simulateProcessor method of the Scheduler class.
	 * 
	 * @throws FileNotFoundException If input or output files
	 *                               are not found/created.
	 */
	@Test
	public void testSimulateProcessor() throws FileNotFoundException {
		
		// Simulate scheduler and processor
		Scheduler.simulateProcessor(INPUT_NAME1, OUTPUT_NAME1, TIMEOUT1);
		// Reads the output file and verifies correct contents of each line.
		// Some interesting aspects of the output are pointed out in comments below.
		Scanner scan1 = new Scanner(new File(OUTPUT_NAME1));
		// When processes have the same start time, the first in the file is launched first
		assertEquals(scan1.nextLine(), "At time 0: run \"Initialization\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 100: run \"Background service\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 200: run \"Compiler\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 300: run \"Background service\" for 100 time units");
		// Process completes before timeout
		assertEquals(scan1.nextLine(), "At time 400: run \"Compiler\" for 50 time units");
		// One idle message between last completed process and next available process
		assertEquals(scan1.nextLine(), "CPU idle from time 450 until time 600");
		assertEquals(scan1.nextLine(), "At time 600: run \"Virus scan\" for 100 time units");
		// Process completes before timeout
		assertEquals(scan1.nextLine(), "At time 700: run \"Quick process\" for 10 time units");
		// Causes offset in when next process starts
		assertEquals(scan1.nextLine(), "At time 710: run \"Browser\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 810: run \"Virus scan\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 910: run \"Browser\" for 50 time units");
		assertEquals(scan1.nextLine(), "At time 960: run \"Virus scan\" for 100 time units");
		// Another idle period
		assertEquals(scan1.nextLine(), "CPU idle from time 1060 until time 1630");
		// Even if there is only one process, it runs for multiple separate timeout periods
		assertEquals(scan1.nextLine(), "At time 1630: run \"Movie clip\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 1730: run \"Movie clip\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 1830: run \"Movie clip\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 1930: run \"Email\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 2030: run \"Movie clip\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 2130: run \"Email\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 2230: run \"Data processing\" for 100 time units");
		// Last process repeats until it is finished
		assertEquals(scan1.nextLine(), "At time 2330: run \"Email\" for 100 time units");
		assertEquals(scan1.nextLine(), "At time 2430: run \"Email\" for 50 time units");
		scan1.close(); // close file after reading

		
		
		Scheduler.simulateProcessor(INPUT_NAME2, OUTPUT_NAME2, TIMEOUT2);
		Scanner scan2 = new Scanner(new File(OUTPUT_NAME2));
		assertEquals(scan2.nextLine(), "At time 0: run \"Initialization\" for 10 time units");
		assertEquals(scan2.nextLine(), "At time 10: run \"Browser\" for 10 time units");
		assertEquals(scan2.nextLine(), "At time 20: run \"iTunes\" for 100 time units");
		// finish iTunes that has 1 left
		assertEquals(scan2.nextLine(), "At time 120: run \"iTunes\" for 1 time units");
		// nothing until 300
		assertEquals(scan2.nextLine(), "CPU idle from time 121 until time 300");
		// break up Safari because of timeouts
		assertEquals(scan2.nextLine(), "At time 300: run \"Safari\" for 100 time units");
		assertEquals(scan2.nextLine(), "At time 400: run \"Safari\" for 100 time units");
		assertEquals(scan2.nextLine(), "At time 500: run \"Safari\" for 10 time units");
		assertEquals(scan2.nextLine(), "At time 510: run \"Email\" for 40 time units");
		scan2.close();

		Scheduler.simulateProcessor(INPUT_NAME3, OUTPUT_NAME3, TIMEOUT3);
		Scanner scan3 = new Scanner(new File(OUTPUT_NAME3));
		// starts with idle time
		assertEquals(scan3.nextLine(), "CPU idle from time 0 until time 75");
		// everything has start time of 75
		assertEquals(scan3.nextLine(), "At time 75: run \"Initialization\" for 50 time units");
		assertEquals(scan3.nextLine(), "At time 125: run \"Data processing\" for 50 time units");
		assertEquals(scan3.nextLine(), "At time 175: run \"Email\" for 50 time units");
		assertEquals(scan3.nextLine(), "At time 225: run \"Virus scan\" for 50 time units");
		assertEquals(scan3.nextLine(), "At time 275: run \"Compiler\" for 50 time units");
		// everything ran for 50, then must finish leftovers
		assertEquals(scan3.nextLine(), "At time 325: run \"Initialization\" for 25 time units");
		assertEquals(scan3.nextLine(), "At time 350: run \"Data processing\" for 26 time units");
		assertEquals(scan3.nextLine(), "At time 376: run \"Email\" for 27 time units");
		assertEquals(scan3.nextLine(), "At time 403: run \"Virus scan\" for 28 time units");
		assertEquals(scan3.nextLine(), "At time 431: run \"Compiler\" for 29 time units");
		scan3.close();
		
		
		
		// test case 3 with different timeout time (100)
		Scheduler.simulateProcessor(INPUT_NAME3, OUTPUT_NAME3, TIMEOUT3v2);
		Scanner scan3v2 = new Scanner(new File(OUTPUT_NAME3));
		// because of larger timeout, each process completes first time it runs
		assertEquals(scan3v2.nextLine(), "CPU idle from time 0 until time 75");
		assertEquals(scan3v2.nextLine(), "At time 75: run \"Initialization\" for 75 time units");
		assertEquals(scan3v2.nextLine(), "At time 150: run \"Data processing\" for 76 time units");
		assertEquals(scan3v2.nextLine(), "At time 226: run \"Email\" for 77 time units");
		assertEquals(scan3v2.nextLine(), "At time 303: run \"Virus scan\" for 78 time units");
		assertEquals(scan3v2.nextLine(), "At time 381: run \"Compiler\" for 79 time units");
		scan3v2.close();

		Scheduler.simulateProcessor(INPUT_NAME4, OUTPUT_NAME4, TIMEOUT4);
		Scanner scan4 = new Scanner(new File(OUTPUT_NAME4));
		assertEquals(scan4.nextLine(), "At time 0: run \"Initialization\" for 100 time units");
		// idle time (next start is 251)
		assertEquals(scan4.nextLine(), "CPU idle from time 100 until time 251");
		// stops at timeout
		assertEquals(scan4.nextLine(), "At time 251: run \"Email\" for 250 time units");
		assertEquals(scan4.nextLine(), "At time 501: run \"Virus scan\" for 250 time units");
		assertEquals(scan4.nextLine(), "At time 751: run \"Spotify\" for 78 time units");
		// finish last of email
		assertEquals(scan4.nextLine(), "At time 829: run \"Email\" for 1 time units");
		assertEquals(scan4.nextLine(), "At time 830: run \"Virus scan\" for 250 time units");
		assertEquals(scan4.nextLine(), "At time 1080: run \"Chrome\" for 250 time units");
		// has to run virus scan a third time because of timeout
		assertEquals(scan4.nextLine(), "At time 1330: run \"Virus scan\" for 2 time units");
		assertEquals(scan4.nextLine(), "At time 1332: run \"Chrome\" for 50 time units");
		scan4.close();

		
		
		Scheduler.simulateProcessor(INPUT_NAME5, OUTPUT_NAME5, TIMEOUT5);
		Scanner scan5 = new Scanner(new File(OUTPUT_NAME5));
		assertEquals(scan5.nextLine(), "At time 0: run \"Initialization\" for 74 time units");
		assertEquals(scan5.nextLine(), "At time 74: run \"Background service\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 149: run \"Compiler\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 224: run \"Background service\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 299: run \"Compiler\" for 75 time units");
		// intermittent background service due to timeout
		assertEquals(scan5.nextLine(), "At time 374: run \"Background service\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 449: run \"Background service\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 524: run \"Background service\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 599: run \"Background service\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 674: run \"Background service\" for 50 time units");
		// long idle time
		assertEquals(scan5.nextLine(), "CPU idle from time 724 until time 1200");
		assertEquals(scan5.nextLine(), "At time 1200: run \"Virus scan\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 1275: run \"Quick process\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 1350: run \"Virus scan\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 1425: run \"Quick process\" for 25 time units");
		assertEquals(scan5.nextLine(), "At time 1450: run \"Virus scan\" for 50 time units");
		assertEquals(scan5.nextLine(), "CPU idle from time 1500 until time 1500");
		assertEquals(scan5.nextLine(), "At time 1500: run \"Browser\" for 5 time units");
		assertEquals(scan5.nextLine(), "CPU idle from time 1505 until time 1630");
		assertEquals(scan5.nextLine(), "At time 1630: run \"Movie clip\" for 5 time units");
		assertEquals(scan5.nextLine(), "CPU idle from time 1635 until time 2000");
		assertEquals(scan5.nextLine(), "At time 2000: run \"Email\" for 75 time units");
		// intermittent data processing
		assertEquals(scan5.nextLine(), "At time 2075: run \"Data processing\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 2150: run \"Data processing\" for 75 time units");
		assertEquals(scan5.nextLine(), "CPU idle from time 2225 until time 3000");
		assertEquals(scan5.nextLine(), "At time 3000: run \"Browser\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 3075: run \"Movie clip\" for 75 time units");
		// must finish browser
		assertEquals(scan5.nextLine(), "At time 3150: run \"Browser\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 3225: run \"Browser\" for 75 time units");
		assertEquals(scan5.nextLine(), "At time 3300: run \"Browser\" for 75 time units");
		// idle time
		assertEquals(scan5.nextLine(), "CPU idle from time 3375 until time 3500");
		assertEquals(scan5.nextLine(), "At time 3500: run \"Email\" for 10 time units");
		assertEquals(scan5.nextLine(), "At time 3510: run \"Data processing\" for 10 time units");
		scan5.close();

		
		
		Scheduler.simulateProcessor(INPUT_NAME6, OUTPUT_NAME6, TIMEOUT6);
		Scanner scan6 = new Scanner(new File(OUTPUT_NAME6));
		// each should start one after the other, start times spaced out by 10
		assertEquals(scan6.nextLine(), "At time 0: run \"Initialization\" for 100 time units");
		assertEquals(scan6.nextLine(), "At time 100: run \"Background service\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 250: run \"iTunes\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 400: run \"Virus scan\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 550: run \"Email\" for 10 time units");
		assertEquals(scan6.nextLine(), "At time 560: run \"Browser\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 710: run \"Movie clip\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 860: run \"Sound clip\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 1010: run \"Chrome\" for 100 time units");
		// starts to re-run processes
		assertEquals(scan6.nextLine(), "At time 1110: run \"Browser\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 1260: run \"Movie clip\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 1410: run \"Background service\" for 50 time units");
		assertEquals(scan6.nextLine(), "At time 1460: run \"Email\" for 150 time units");
		// lots of cycling through processes due to timeout
		assertEquals(scan6.nextLine(), "At time 1610: run \"Virus scan\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 1760: run \"Movie clip\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 1910: run \"Sound clip\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 2060: run \"Movie clip\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 2210: run \"Email\" for 150 time units");
		assertEquals(scan6.nextLine(), "At time 2360: run \"Movie clip\" for 100 time units");
		assertEquals(scan6.nextLine(), "At time 2460: run \"Sound clip\" for 50 time units");
		assertEquals(scan6.nextLine(), "At time 2510: run \"Movie clip\" for 100 time units");
		assertEquals(scan6.nextLine(), "At time 2610: run \"Email\" for 50 time units");
		// large idle time
		assertEquals(scan6.nextLine(), "CPU idle from time 2660 until time 5000");
		// finally runs data processing
		assertEquals(scan6.nextLine(), "At time 5000: run \"Data processing\" for 100 time units");
		scan6.close();
	}
}
