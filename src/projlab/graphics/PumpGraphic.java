package projlab.graphics;

import projlab.sivatag.Pump;

/**
 * Felelős a játékban megtalálható pumpák kirajzolásáért.
 */
public class PumpGraphic extends PipelineGraphic{
    /**
     * A pumpa, amit az objektum reprezentál.
     */
    private Pump pump;

    /**
     * Létrehoz egy új PumpGraphic objektumot, a paraméterben megadott pumpára hivatkozva.
     * @param pump A pumpa amire az objetum hivatkozik.
     */
    public PumpGraphic(Pump pump){
        this.pump = pump;
    }
    /**
     * Megkísérli elrontani a reprezentált pumpát.
     */
    public boolean breakObject(){
        return pump.breakObject(true);
    }
}
