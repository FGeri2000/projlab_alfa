package projlab.skeleton;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.IOException;

public class Main {

	public static Scanner InputScanner = null; 
	
	public static void main(String[] args) throws IOException {
		InputScanner = new Scanner(System.in);
		String input;
		InputStreamReader source = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(source);

		System.out.println("Valasszon a skeleton tesztesetei kozul:\n" +
				"1. Teszt - Plumber repair pipe \n" +
				"2. Teszt - Plumber repair pump \n" +
				"3. Teszt - Plumber repair cistern \n" +
				"4. Teszt - Plumber repair source \n" +
				"5. Teszt - Saboteur break pipe \n" +
				"6. Teszt - Saboteur break pump \n" +
				"7. Teszt - Saboteur break cistern \n" +
				"8. Teszt - Saboteur break source \n" +
				"9. Teszt - Saboteur steps from pipe to pump \n" +
				"10. Teszt - Saboteur steps from pipe to source \n" +
				"11. Teszt - Saboteur steps from pipe to cistern \n" +
				"12. Teszt - Saboteur steps from pump to pipe \n" +
				"13. Teszt - Saboteur steps from source to pipe \n" +
				"14. Teszt - Saboteur steps from cistern to pipe \n" +
				"15. Teszt - Plumber steps from pipe to pump \n" +
				"16. Teszt - Plumber steps from pipe to source \n" +
				"17. Teszt - Plumber steps from pipe to cistern \n" +
				"18. Teszt - Plumber steps from pump to pipe \n" +
				"19. Teszt - Plumber steps from source to pipe \n" +
				"20. Teszt - Plumber steps from cistern to pipe \n" +
				"21. Teszt - Plumber set pump input \n" +
				"22. Teszt - Plumber set pump output  \n" +
				"23. Teszt - Saboteur set pump input \n" +
				"24. Teszt - Saboteur set pump output \n" +
				"25. Teszt - Water flow from source to pipe \n" +
				"26. Teszt - Water flow from pipe to pump \n" +
				"27. Teszt - Water flow from pump to pipe \n" +
				"28. Teszt - Water flow from pipe to cistern \n" +
				"29. Teszt - Place pipe to cistern \n" +
				"30. Teszt - Place pipe to source \n" +
				"31. Teszt - Place pipe to pump \n" +
				"32. Teszt - Place pump by splitting \n" +
				"33. Teszt - Place pump to end of pipe \n" +
				"34. Teszt - Pickup cistern from pipe \n" +
				"35. Teszt - Pickup source from pipe \n" +
				"36. Teszt - Pickup pump from pipe \n" +
				"37. Teszt - Pickup pipe from cistern \n" +
				"38. Teszt - Pickup pipe from source \n" +
				"39. Teszt - Pickup pipe from pump \n");

		System.out.println("A valasztott teszteset: ");
		input = br.readLine();
		System.out.println("\n");

		switch (input)
		{
			case "1": {
				System.out.println("Az 1. Tesztet valasztotta!");
				Repair.Test_PlumberRepairPipe();
			}
			break;

			case "2": {
				System.out.println("Az 2. Tesztet valasztotta!");
				Repair.Test_PlumberRepairPump();
			}
			break;

			case "3": {
				System.out.println("Az 3. Tesztet valasztotta!");
				Repair.Test_PlumberRepairCistern();
			}
			break;

			case "4": {
				System.out.println("Az 4. Tesztet valasztotta!");
				Repair.Test_PlumberRepairSource();
			}
			break;

			case "5": {
				System.out.println("Az 5. Tesztet valasztotta!");
				Break.Test_SaboteurBreakPipe();
			}
			break;

			case "6": {
				System.out.println("Az 6. Tesztet valasztotta!");
				Break.Test_SaboteurBreakPump();
			}
			break;

			case "7": {
				System.out.println("Az 7. Tesztet valasztotta!");
				Break.Test_SaboteurBreakCistern();
			}
			break;

			case "8": {
				System.out.println("Az 8. Tesztet valasztotta!");
				Break.Test_SaboteurBreakSource();
			}
			break;

			case "9": {
				System.out.println("Az 9. Tesztet valasztotta!");
				Move.Test_SaboteurStepsFromPipeToPump();
			}
			break;

			case "10": {
				System.out.println("Az 10. Tesztet valasztotta!");
				Move.Test_SaboteurStepsFromPipeToSource();
			}
			break;

			case "11": {
				System.out.println("Az 11. Tesztet valasztotta!");
				Move.Test_SaboteurStepsFromPipeToCistern();
			}
			break;

			case "12": {
				System.out.println("Az 12. Tesztet valasztotta!");
				Move.Test_SaboteurStepsFromPumpToPipe();
			}
			break;

			case "13": {
				System.out.println("Az 13. Tesztet valasztotta!");
				Move.Test_SaboteurStepsFromSourceToPipe();
			}
			break;

			case "14": {
				System.out.println("Az 14. Tesztet valasztotta!");
				Move.Test_SaboteurStepsFromCisternToPipe();
			}
			break;

			case "15": {
				System.out.println("Az 15. Tesztet valasztotta!");
				Move.Test_PlumberStepsFromPipeToPump();
			}
			break;

			case "16": {
				System.out.println("Az 16. Tesztet valasztotta!");
				Move.Test_PlumberStepsFromPipeToSource();
			}
			break;

			case "17": {
				System.out.println("Az 17. Tesztet valasztotta!");
				Move.Test_PlumberStepsFromPipeToCistern();
			}
			break;

			case "18": {
				System.out.println("Az 18. Tesztet valasztotta!");
				Move.Test_PlumberStepsFromPumpToPipe();
			}
			break;

			case "19": {
				System.out.println("Az 19. Tesztet valasztotta!");
				Move.Test_PlumberStepsFromSourceToPipe();
			}
			break;

			case "20": {
				System.out.println("Az 20. Tesztet valasztotta!");
				Move.Test_PlumberStepsFromCisternToPipe();
			}
			break;

			case "21": {
				System.out.println("Az 21. Tesztet valasztotta!");
				SetPump.Test_PlumberSetPumpInput();
			}
			break;

			case "22": {
				System.out.println("Az 22. Tesztet valasztotta!");
				SetPump.Test_PlumberSetPumpOutput();
			}
			break;

			case "23": {
				System.out.println("Az 23. Tesztet valasztotta!");
				SetPump.Test_SaboteurSetPumpInput();
			}
			break;

			case "24": {
				System.out.println("Az 24. Tesztet valasztotta!");
				SetPump.Test_SaboteurSetPumpOutput();
			}
			break;

			case "25": {
				System.out.println("Az 25. Tesztet valasztotta!");
				Flow.Test_WaterFlowFromSourceToPipe();
			}
			break;

			case "26": {
				System.out.println("Az 26. Tesztet valasztotta!");
				Flow.Test_WaterFlowFromPipeToPump();
			}
			break;

			case "27": {
				System.out.println("Az 27. Tesztet valasztotta!");
				Flow.Test_WaterFlowFromPumpToPipe();
			}
			break;

			case "28": {
				System.out.println("Az 28. Tesztet valasztotta!");
				Flow.Test_WaterFlowFromPipeToCistern();
			}
			break;

			case "29": {
				System.out.println("Az 29. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPlacesPipeToCistern();
			}
			break;

			case "30": {
				System.out.println("Az 30. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPlacesPipeToSource();
			}
			break;

			case "31": {
				System.out.println("Az 31. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPlacesPipeToPump();
			}
			break;

			case "32": {
				System.out.println("Az 32. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPlacesPumpBySplittingPipe();
			}
			break;

			case "33": {
				System.out.println("Az 33. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPlacesPumpToEndOfPipe();
			}
			break;

			case "34": {
				System.out.println("Az 34. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPicksUpCisternFromPipe();
			}
			break;

			case "35": {
				System.out.println("Az 35. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPicksUpSourceFromPipe();
			}
			break;

			case "36": {
				System.out.println("Az 36. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPicksUpPumpFromPipe();
			}
			break;

			case "37": {
				System.out.println("Az 37. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPicksUpPipeFromCistern();
			}
			break;

			case "38": {
				System.out.println("Az 38. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPicksUpPipeFromSource();
			}
			break;

			case "39": {
				System.out.println("Az 39. Tesztet valasztotta!");
				PickupPlace.Test_PlumberPicksUpPipeFromPump();
			}
			break;
		}
		
		InputScanner.close();
	}
		
}
