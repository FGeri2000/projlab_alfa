package projlab.tester;

import java.io.*;
import java.security.InvalidKeyException;
import java.util.Scanner;

import projlab.sivatag.*;

public class Main {
	static Game game = null;
	static boolean rcDisable = false; 
	
	private static StringBuffer output = null;
	static void push(String line) {
		if (output != null)
			output.append(line + System.lineSeparator());
		else
			System.out.println(line);
	}
	
	private static boolean exit = false;
	
	public static void main(String[] args) throws InvalidKeyException {
		if (args.length == 0) {
			Scanner scan = new Scanner(System.in);
			
			while (scan.hasNextLine()) {
				String inputLine = scan.nextLine();

				String[] lineSplit = inputLine.split(" ");
				String[] arguments = new String[lineSplit.length - 1];
				for (int i = 1; i < lineSplit.length; i++) {
					arguments[i - 1] = lineSplit[i];
				}
				runCommand(lineSplit[0], arguments);
				if (exit)
					break;
			}
			
			scan.close();
			return;
		}
		else if (args.length != 2) {
			System.out.println("error: incorrect number of parameters");
			return;
		}
		
		output = new StringBuffer();
		
		//Determine files
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		File newOutputFile = new File("new_" + args[1]);
		
		//Error if test files are missing
		if (!inputFile.exists()) {
			System.out.println("error: input file not found");
			return;
		}
		if (!outputFile.exists()) {
			System.out.println("error: expected output file not found");
			return;
		}
		
		try {
			//Run whole test, line by line
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			while (input.ready()) {
				String inputLine = input.readLine();

				String[] lineSplit = inputLine.split(" ");
				String[] arguments = new String[lineSplit.length - 1];
				for (int i = 1; i < lineSplit.length; i++) {
					arguments[i - 1] = lineSplit[i];
				}
				runCommand(lineSplit[0], arguments);
				if (exit)
					break;
			}
			input.close();

			
			//Open up streams for expected output file and actual output string
			BufferedReader output = new BufferedReader(new FileReader(outputFile));
			BufferedReader actualOutputReader = new BufferedReader(new StringReader(Main.output.toString()));

			//Diff outputs line by line, fail if mismatched
			int lineNumber = 1;
			while (output.ready()) {
				String expectedOutput = output.readLine();
				String actualOutput = actualOutputReader.readLine();
				
				if (!expectedOutput.equals(actualOutput)) {
					System.out.println("Test failed on line " + lineNumber);
					System.out.println("Expected output: \"" + expectedOutput + "\"");
					System.out.println("Actual output:   \"" + actualOutput + "\"");
					output.close();
					return;
				}
				lineNumber++;
			}
			output.close();

			//Push actual output to file for debugging purposes
			BufferedWriter newOutput = new BufferedWriter(new FileWriter(newOutputFile));
			newOutput.append(Main.output);
			newOutput.close();
		}
		catch (Exception e) {
			return;
		}
	}
		
	private static void runCommand(String command, String[] args) throws InvalidKeyException {
		switch (command.toLowerCase()) {
		case "list":
			if (args.length != 1)
				return;
			GameTest.runList(args[0]);
			break;
		case "save":
			{
				if (args.length != 0 || args.length != 1)
					return;
				String path = "game.xml";
				if (args.length == 1)
					path = args[0];
				GameTest.runSave(path);
			}
			break;
		case "load":
			{
				if (args.length != 0 && args.length != 1)
					return;
				String path = "game.xml";
				if (args.length == 1)
					path = args[0];
				GameTest.runLoad(path);
			}
			break;
		case "newgame":
			GameTest.runNewGame();
			break;
		case "selectteam":
			if (args.length != 1)
				return;
			if (args[0].equals("-p")) {
				GameTest.runSelectTeam("p");	
			}
			else if (args[0].equals("-s")) {
				GameTest.runSelectTeam("s");
			}
			break;
		case "addneighbor":
			if (args.length != 2)
				return;
			ObjectTest.runAddNeighbor(args[0], args[1]);
			break;
		case "removeneighbor":
			if (args.length != 2)
				return;
			ObjectTest.runRemoveNeighbor(args[0], args[1]);
			break;
		case "removeneighbors":
			if (args.length != 1)
				return;
			ObjectTest.runRemoveNeighbors(args[0]);
			break;
		case "pumprc":
			if (args.length != 0)
				return;
			ObjectTest.runPumpRC();
			break;
		case "step":
			if (args.length != 2)
				return;
			PlayerTest.runStep(args[0], args[1]);
			break;
		case "makepipeslippery":
			if (args.length != 1)
				return;
			PlayerTest.runMakePipeSlippery(args[0]);
			break;
		case "makepipesticky":
			if (args.length != 1)
				return;
			PlayerTest.runMakePipeSticky(args[0]);
			break;
		case "createpump":
			if (args.length == 0) {
				ObjectTest.runCreatePump(null);
			}
			else if (args.length == 2 && args[0].equals("-c")) {
				ObjectTest.runCreatePump(args[1]);
			}
			break;
		case "createpipe":
			if (args.length == 0) {
				ObjectTest.runCreatePipe(null);
			}
			else if (args.length == 2 && args[0].equals("-c")) {
				ObjectTest.runCreatePipe(args[1]);
			}
			break;
		case "setpumpinput":
			if (args.length != 2)
				return;
			{
				int index = 0;
				try { index = Integer.parseInt(args[1]); }
				catch (Exception e) { return; }
				PlayerTest.runSetPumpInput(args[0], index);
			}
			break;
		case "setpumpoutput":
			if (args.length != 2)
				return;
			{
				int index = 0;
				try { index = Integer.parseInt(args[1]); }
				catch (Exception e) { return; }
				PlayerTest.runSetPumpOutput(args[0], index);
			}
			break;
		case "setinput":
			if (args.length != 2)
				return;
			{
				int index = 0;
				try { index = Integer.parseInt(args[1]); }
				catch (Exception e) { return; }
				ObjectTest.runSetInput(args[0], index);
			}
			break;
		case "setoutput":
			if (args.length != 2)
				return;
			{
				int index = 0;
				try { index = Integer.parseInt(args[1]); }
				catch (Exception e) { return; }
				ObjectTest.runSetOutput(args[0], index);
			}
			break;
		case "repairthroughplayer":
			if (args.length != 1)
				return;
			PlayerTest.runRepairThroughPlayer(args[0]);
			break;
		case "breakthroughplayer":
			if (args.length != 1)
				return;
			PlayerTest.runBreakThroughPlayer(args[0]);
			break;
		case "repairobject":
			if (args.length != 1)
				return;
			ObjectTest.runRepairObject(args[0]);
			break;
		case "breakobject":
			if (args.length != 1)
				return;
			ObjectTest.runBreakObject(args[0]);
			break;
		case "pickupobject":
			if (args.length != 2)
				return;
			{
				int index = 0;
				try { index = Integer.parseInt(args[1]); }
				catch (Exception e) { return; }
				PlayerTest.runPickupObject(args[0], index);
			}
			break;
		case "placeobject":
			if (args.length != 1)
				return;
			PlayerTest.runPlaceObject(args[0]);
			break;
		case "disablerc":
			rcDisable = true;
			push("Pump RC has been disabled");
			break;
		case "enablerc":
			rcDisable = false;
			push("Pump RC has been enabled");
			break;
		case "exit":
			exit = true;
			break;
		}
	}

}
