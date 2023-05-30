package projlab.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

    private static BufferedImage image;
    
    /**
     * Létrehoz egy új PumpGraphic objektumot, a paraméterben megadott pumpára hivatkozva.
     * @param pump A pumpa amire az objetum hivatkozik.
     */
    public PumpGraphic(Pump pump){
        this.pump = pump;

        if (image == null)
			try {
				image = ImageIO.read(new File("pump.jpg"));
			}
			catch (IOException e) {
				return;
			}
        this.changeImage(image);
    }
    
    public void draw(Graphics graphics) {
    	if (pump.isBroken()) {
    		graphics.setColor(Color.red);
    		graphics.drawRect(get_x()-getWidth()/2-5, get_y()-getHeight()/2-5, getWidth()+10, getHeight()+10);
    		graphics.setColor(Color.black);
    	}
    	super.draw(graphics);
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
