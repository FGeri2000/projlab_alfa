package projlab.graphics;

import projlab.sivatag.Cistern;
import projlab.sivatag.WaterFlow;

/**
 * Felelős a játékban megtalálható ciszternák kirajzolásáért.
 */
public class CisternGraphic extends JunctionGraphic {
    /**
     * A ciszterna, amit az objektum reprezentál.
     */
    private Cistern cistern;

    /**
     * Létrehoz egy új CisternGraphic objektumot, a paraméterben megadott ciszternára hivatkozva.
     * @param cistern A ciszterna, amire az objektum hivatkozik.
     */
    public CisternGraphic(Cistern cistern){
        this.cistern = cistern;
    }

	@Override
	public boolean objectMatch(WaterFlow object) {
		return object == cistern;
	}

	@Override
	public WaterFlow objectGet() {
		return cistern;
	}

	@Override
	public boolean canSetInput() {
		return false;
	}

	@Override
	public boolean canSetOutput() {
		return false;
	}

	@Override
	public boolean canRepair() {
		return false;
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
