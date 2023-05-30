package projlab.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

    private BufferedImage image;
    
    /**
     * Létrehoz egy új CisternGraphic objektumot, a paraméterben megadott ciszternára hivatkozva.
     * @param cistern A ciszterna, amire az objektum hivatkozik.
     */
    public CisternGraphic(Cistern cistern){
        this.cistern = cistern;

        if (image == null)
			try {
				image = ImageIO.read(new File("cistern.jpg"));
			}
			catch (IOException e) {
				return;
			}
        this.changeImage(image);
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
