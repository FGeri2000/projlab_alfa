package projlab.skeleton;

import projlab.sivatag.*;

public class Break {
	public static void Test_SaboteurBreakPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Breaks Pipe");
		
		//Initialization
		Pipe position = new Pipe();
		Saboteur p = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(p);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "Pipe");
		CallHierarchyWriter.PushIdentifier(p, "Saboteur");
		
		//Run simulation
		p.InputCallback_Break();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_SaboteurBreakPump() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Breaks Pump");
		
		//Initialization
		Pump position = new Pump();
		Saboteur p = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(p);
		ConditionQuerier.DisableDefaults();
	
		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "Pump");
		CallHierarchyWriter.PushIdentifier(p, "Saboteur");
		
		//Run simulation
		p.InputCallback_Break();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_SaboteurBreakCistern() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Breaks Cistern");
		
		//Initialization
		Cistern position = new Cistern();
		Saboteur p = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(p);
		ConditionQuerier.DisableDefaults();
	
		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "Cistern");
		CallHierarchyWriter.PushIdentifier(p, "Saboteur");
		
		//Run simulation
		p.InputCallback_Break();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_SaboteurBreakSource() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Saboteur Breaks Source");
		
		//Initialization
		Source position = new Source();
		Saboteur p = new Saboteur(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(p);
		ConditionQuerier.DisableDefaults();
	
		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "Source");
		CallHierarchyWriter.PushIdentifier(p, "Saboteur");
		
		//Run simulation
		p.InputCallback_Break();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
}
