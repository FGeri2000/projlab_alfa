package projlab.skeleton;

import projlab.sivatag.*;

public class PickupPlace {
	//pick up pipe
    public static void Test_PlumberPicksUpPipeFromPump(){
        CallHierarchyWriter.Clear();
        System.out.println("Running use-case: Plumber Picks Up Pipe From Pump");

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
        pl.InputCallback_Pickup(ConditionQuerier.QueryUserForInteger("Hányadik szomszédot vegye fel a játékos?"));

        //Print calls to stdout
        CallHierarchyWriter.PrintToStandardOutput();
    }

    public static void Test_PlumberPicksUpPipeFromCistern(){
        CallHierarchyWriter.Clear();
        System.out.println("Running use-case: Plumber Picks Up Pipe From Cistern");

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
        pl.InputCallback_Pickup(ConditionQuerier.QueryUserForInteger("Hányadik szomszédot vegye fel a játékos?"));

        //Print calls to stdout
        CallHierarchyWriter.PrintToStandardOutput();
    }

    public static void Test_PlumberPicksUpPipeFromSource(){
        CallHierarchyWriter.Clear();
        System.out.println("Running use-case: Plumber Picks Up Pipe From Source");

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
        pl.InputCallback_Pickup(ConditionQuerier.QueryUserForInteger("Hányadik szomszédot vegye fel a játékos?"));

        //Print calls to stdout
        CallHierarchyWriter.PrintToStandardOutput();
    }
    
    public static void Test_PlumberPicksUpPumpFromPipe(){
        CallHierarchyWriter.Clear();
        System.out.println("Running use-case: Plumber Picks Up Pump From Pipe");

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
        pl.InputCallback_Pickup(ConditionQuerier.QueryUserForInteger("Hányadik szomszédot vegye fel a játékos?"));

        //Print calls to stdout
        CallHierarchyWriter.PrintToStandardOutput();
    }
    
    public static void Test_PlumberPicksUpCisternFromPipe(){
        CallHierarchyWriter.Clear();
        System.out.println("Running use-case: Plumber Picks Up Cistern From Pipe");

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
        pl.InputCallback_Pickup(ConditionQuerier.QueryUserForInteger("Hányadik szomszédot vegye fel a játékos?"));

        //Print calls to stdout
        CallHierarchyWriter.PrintToStandardOutput();
    }
    
    public static void Test_PlumberPicksUpSourceFromPipe(){
        CallHierarchyWriter.Clear();
        System.out.println("Running use-case: Plumber Picks Up Source From Pipe");

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
        pl.InputCallback_Pickup(ConditionQuerier.QueryUserForInteger("Hányadik szomszédot vegye fel a játékos?"));

        //Print calls to stdout
        CallHierarchyWriter.PrintToStandardOutput();
    }
	//place pipe from p/s/c
	//place pump by splitting pipe
	//place pump on end of pipe
}
