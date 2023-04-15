package projlab.skeleton;

import projlab.sivatag.*;

public class SetPump {
	public static void Test_PlumberSetPumpInput() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Sets Pump Input");
		
		//Initialization
		Pump position = new Pump();
		Pipe p1 = new Pipe();
		Pipe p2 = new Pipe();
		Plumber pl = new Plumber(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(pl);
		position.AddNeighbor(p1);
		position.AddNeighbor(p2);
		p1.AddNeighbor(position);
		p2.AddNeighbor(position);		
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(p1, "p1");
		CallHierarchyWriter.PushIdentifier(p2, "p2");
		
		//Run simulation
		pl.InputCallback_SetInput(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra állítsa a bemenetet a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_PlumberSetPumpOutput() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Sets Pump Output");
		
		//Initialization
		Pump position = new Pump();
		Pipe p1 = new Pipe();
		Pipe p2 = new Pipe();
		Plumber pl = new Plumber(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(pl);
		position.AddNeighbor(p1);
		position.AddNeighbor(p2);
		p1.AddNeighbor(position);
		p2.AddNeighbor(position);		
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(pl, "pl");
		CallHierarchyWriter.PushIdentifier(p1, "p1");
		CallHierarchyWriter.PushIdentifier(p2, "p2");
		
		//Run simulation
		pl.InputCallback_SetInput(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra állítsa a kimenetet a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_SaboteurSetPumpInput() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Sets Pump Input");
		
		//Initialization
		Pump position = new Pump();
		Pipe p1 = new Pipe();
		Pipe p2 = new Pipe();
		Saboteur s = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(s);
		position.AddNeighbor(p1);
		position.AddNeighbor(p2);
		p1.AddNeighbor(position);
		p2.AddNeighbor(position);		
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(s, "s");
		CallHierarchyWriter.PushIdentifier(p1, "p1");
		CallHierarchyWriter.PushIdentifier(p2, "p2");
		
		//Run simulation
		s.InputCallback_SetInput(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra állítsa a bemenetet a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_SaboteurSetPumpOutput() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Sets Pump Output");
		
		//Initialization
		Pump position = new Pump();
		Pipe p1 = new Pipe();
		Pipe p2 = new Pipe();
		Saboteur s = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(s);
		position.AddNeighbor(p1);
		position.AddNeighbor(p2);
		p1.AddNeighbor(position);
		p2.AddNeighbor(position);		
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "position");
		CallHierarchyWriter.PushIdentifier(s, "s");
		CallHierarchyWriter.PushIdentifier(p1, "p1");
		CallHierarchyWriter.PushIdentifier(p2, "p2");
		
		//Run simulation
		s.InputCallback_SetInput(ConditionQuerier.QueryUserForInteger("Hanyadik szomszédra állítsa a kimenetet a játékos?"));
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
}
