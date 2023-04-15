package projlab.skeleton;

import projlab.sivatag.*;

public class Move {

	public static void Test_SaboteurStepsFromPipeToPump() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Steps From Pipe To Pump");
		
		//Initialization
		Pipe position = new Pipe();
		Pump neighbor = new Pump();
		Saboteur pl = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);		
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_SaboteurStepsFromPipeToSource() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Steps From Pipe To Source");
		
		//Initialization
		Pipe position = new Pipe();
		Source neighbor = new Source();
		Saboteur pl = new Saboteur(position);

		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");

		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_SaboteurStepsFromPipeToCistern() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Steps From Pipe To Cistern");
		
		//Initialization
		Pipe position = new Pipe();
		Cistern neighbor = new Cistern();
		Saboteur pl = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	
	public static void Test_SaboteurStepsFromPumpToPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Steps From Pump To Pipe");
		
		//Initialization
		Pump position = new Pump();
		Pipe neighbor = new Pipe();
		Saboteur pl = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_SaboteurStepsFromSourceToPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Steps From Source To Pipe");
		
		//Initialization
		Source position = new Source();
		Pipe neighbor = new Pipe();
		Saboteur s = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(s);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(s, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		s.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_SaboteurStepsFromCisternToPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Steps From Cistern To Pipe");
		
		//Initialization
		Cistern position = new Cistern();
		Pipe neighbor = new Pipe();
		Saboteur s = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(s);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(s, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		s.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	
	
	public static void Test_PlumberStepsFromPipeToPump() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Steps From Pipe To Pump");
		
		//Initialization
		Pipe position = new Pipe();
		Pump neighbor = new Pump();
		Plumber pl = new Plumber(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);		
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_PlumberStepsFromPipeToSource() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Steps From Pipe To Source");
		
		//Initialization
		Pipe position = new Pipe();
		Source neighbor = new Source();
		Plumber pl = new Plumber(position);

		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");

		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_PlumberStepsFromPipeToCistern() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Steps From Pipe To Cistern");
		
		//Initialization
		Pipe position = new Pipe();
		Cistern neighbor = new Cistern();
		Plumber pl = new Plumber(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	
	public static void Test_PlumberStepsFromPumpToPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Steps From Pump To Pipe");
		
		//Initialization
		Pump position = new Pump();
		Pipe neighbor = new Pipe();
		Plumber pl = new Plumber(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_PlumberStepsFromSourceToPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Steps From Source To Pipe");
		
		//Initialization
		Source position = new Source();
		Pipe neighbor = new Pipe();
		Plumber pl = new Plumber(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_PlumberStepsFromCisternToPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Steps From Cistern To Pipe");
		
		//Initialization
		Cistern position = new Cistern();
		Pipe neighbor = new Pipe();
		Plumber pl = new Plumber(position);
		
		ConditionQuerier.EnableDefaults();
		position.AddNeighbor(neighbor);
		position.PutPlayer(pl);
		neighbor.AddNeighbor(position);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(neighbor, "neighbor");
		
		//Run simulation
		pl.InputCallback_Move(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra lépjen a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
}
