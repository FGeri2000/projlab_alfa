package projlab.skeleton;

import projlab.sivatag.*;

public class Repair {
		
	public static void Test_PlumberRepairPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Repairs Pipe");
		
		//Initialization
		Pipe position = new Pipe();
		Plumber p = new Plumber(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(p);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "Pipe");
		CallHierarchyWriter.PushIdentifier(p, "Plumber");
		
		//Run simulation
		p.InputCallback_Repair();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_PlumberRepairPump() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Repairs Pump");
		
		//Initialization
		Pump position = new Pump();
		Plumber p = new Plumber(position);

		ConditionQuerier.EnableDefaults();
		position.PutPlayer(p);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "Pump");
		CallHierarchyWriter.PushIdentifier(p, "Plumber");
				
		//Run simulation
		p.InputCallback_Repair();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_PlumberRepairCistern() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Repairs Cistern");
		
		//Initialization
		Cistern position = new Cistern();
		Plumber p = new Plumber(position);

		ConditionQuerier.EnableDefaults();
		position.PutPlayer(p);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "Cistern");
		CallHierarchyWriter.PushIdentifier(p, "Plumber");
		
		//Run simulation
		p.InputCallback_Repair();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
		
	}
	public static void Test_PlumberRepairSource() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Plumber Repairs Source");
		
		//Initialization
		Source position = new Source();
		Plumber p = new Plumber(position);
		
		ConditionQuerier.EnableDefaults();
		position.PutPlayer(p);
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(position, "Source");
		CallHierarchyWriter.PushIdentifier(p, "Plumber");
		
		//Run simulation
		p.InputCallback_Repair();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
}
