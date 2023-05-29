package projlab.graphics;

import projlab.sivatag.Pump;
import projlab.sivatag.WaterFlow;

/**
 * Felelős a játékban megtalálható pumpák kirajzolásáért.
 */
public class PumpGraphic extends JunctionGraphic {
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

	@Override
	public boolean objectMatch(WaterFlow object) {
		return object == pump;
	}

	@Override
	public WaterFlow objectGet() {
		return pump;
	}
	@Override
	public boolean canSetInput() {
		return true;
	}
	@Override
	public boolean canSetOutput() {
		return true;
	}
	@Override
	public boolean canRepair() {
		return pump.isBroken();
	}
	@Override
	public boolean canBreak() {
		return false;
	}
	@Override
	public boolean canSticky() {
		return false;
	}
	@Override
	public boolean canSlippery() {
		return false;
	}
	@Override
	public boolean canPickup() {
		return true;
	}
	@Override
	public boolean canPlace() {
		return true;
	}
}
