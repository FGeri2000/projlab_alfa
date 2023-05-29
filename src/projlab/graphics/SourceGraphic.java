package projlab.graphics;

import projlab.sivatag.Source;
import projlab.sivatag.WaterFlow;

/**
 * Felelős a játékban megtalálható források kirajzolásáért.
 */
public class SourceGraphic extends JunctionGraphic {
    /**
     * A forrás, amit az objektum reprezentál.
     */
    private Source source;

    /**
     * Létrehoz egy új SourceGraphic objektumot, a paraméterben megadott forrásra hivatkozva.
     * @param source A forrás, amire az objektum hivatkozik.
     */
    public SourceGraphic(Source source){
        this.source = source;
    }

	@Override
	public boolean objectMatch(WaterFlow object) {
		return object == source;
	}

	@Override
	public WaterFlow objectGet() {
		return source;
	}

	@Override
	public boolean canSetInput() {
		return false;
	}

	@Override
	public boolean canSetOutput() {
		return true;
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
