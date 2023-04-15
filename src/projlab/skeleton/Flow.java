package projlab.skeleton;

import projlab.sivatag.*;

public class Flow {
	public static void Test_WaterFlowFromSourceToPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Water Flows From Source To Pipe");
		
		//Initialization
		Source source = new Source();
		Pipe pipe = new Pipe();
		
		ConditionQuerier.EnableDefaults();
		source.AddNeighbor(pipe);
		pipe.AddNeighbor(source);		
		source.SetOutput(0);		
		pipe.SetInput(new int[]{0});
		ConditionQuerier.DisableDefaults();
	
		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(source, "Source");
		CallHierarchyWriter.PushIdentifier(pipe, "Pipe");
		
		//Run simulation
		source.FlowTick();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_WaterFlowFromPipeToPump() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Water Flows From Pipe To Pump");
		
		//Initialization
		Pipe pipe = new Pipe();
		Pump pump = new Pump();
		
		ConditionQuerier.EnableDefaults();
		pipe.AddNeighbor(pump);
		pump.AddNeighbor(pipe);
		pipe.SetOutput(0);
		pump.SetInput(new int[]{0});
		ConditionQuerier.DisableDefaults();
	
		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(pipe, "Pipe");
		CallHierarchyWriter.PushIdentifier(pump, "Pump");
		
		//Run simulation
		pipe.FlowTick();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_WaterFlowFromPumpToPipe() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Water Flows From Pump To Pipe");
		
		//Initialization
		Pump pump = new Pump();
		Pipe pipe = new Pipe();
		
		ConditionQuerier.EnableDefaults();
		pump.AddNeighbor(pipe);
		pipe.AddNeighbor(pump);
		pump.SetOutput(0);
		pipe.SetInput(new int[]{0});
		ConditionQuerier.DisableDefaults();
	
		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(pump, "Pump");
		CallHierarchyWriter.PushIdentifier(pipe, "Pipe");
		
		//Run simulation
		pump.FlowTick();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
	public static void Test_WaterFlowFromPipeToCistern() {
		CallHierarchyWriter.Clear();
		System.out.println("Running use-case: Water Flows From Pipe To Cistern");
		
		//Initialization
		Pipe pipe = new Pipe();
		Cistern cistern = new Cistern();
		
		ConditionQuerier.EnableDefaults();
		pipe.AddNeighbor(cistern);
		cistern.AddNeighbor(pipe);		
		pipe.SetOutput(0);		
		cistern.SetInput(new int[]{0});
		ConditionQuerier.DisableDefaults();

		//Passing original caller (controller) to writer
		String ctrl_dummy = "[controller]";
		CallHierarchyWriter.PushCaller(ctrl_dummy);
		
		CallHierarchyWriter.PushIdentifier(ctrl_dummy, ctrl_dummy);
		CallHierarchyWriter.PushIdentifier(pipe, "Pipe");
		CallHierarchyWriter.PushIdentifier(cistern, "Cistern");
		
		//Run simulation
		pipe.FlowTick();
		
		//Print calls to stdout
		CallHierarchyWriter.PrintToStandardOutput();
	}
}
