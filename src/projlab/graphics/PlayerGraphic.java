package projlab.graphics;

import projlab.controller.Controller;
import projlab.sivatag.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Egy játékos grafikai megfelelője. Kirajzolja a játékost, és átadja a felhasználói bemeneteket rajta keresztül a modellnek.
 * @author fgreg
 */
public class PlayerGraphic extends Graphic {
	/**
	 * A modell-beli játékos amihez ez a kirajzolás tartozik. 
	 */
	private Player player = null;
	/**
	 * Annak a csőelemnek a grafikai megfelelője, amit a játékos áll. 
	 */
	private PipelineGraphic position = null;
	/**
	 * Igaz, ha a játékos egy szabotőr, hamis ha szerelő.
	 */
	private boolean isSaboteur = false;
	/**
	 * Igaz, ha ez a játékos a jelenleg soron következő.
	 */
	private boolean selected = false;
	
	private BufferedImage imageP1, imageP2, imageS;
	
	/**
	 * Létrehoz egy új játékos grafikai elemet egy szerelőnek.
	 * @param plumber A szerelő játékos.
	 * @param altImage Vált a kettő kirajzolható kép között.
	 */
	public PlayerGraphic(Plumber plumber, boolean altImage) {
		isSaboteur = false;
		player = plumber;

		this.changeSize(40, 40);
		
		if (imageP1 == null)
			try {
				imageP1 = ImageIO.read(new File("plumber1.jpg"));
				imageP2 = ImageIO.read(new File("plumber2.jpg"));
			}
			catch (IOException e) {
				return;
			}

		if (altImage) {
			this.changeImage(imageP2);
		}
		else {
			this.changeImage(imageP1);
		}

		positionLookup();
	}
	/**
	 * Létrehoz egy új játékos grafikai elemet egy szabotőrnek.
	 * @param saboteur A szabotőr játékos.
	 */
	public PlayerGraphic(Saboteur saboteur) {
		isSaboteur = true;
		player = saboteur;

		this.changeSize(40, 40);
		
		if (imageS == null)
			try {
				imageS = ImageIO.read(new File("saboteur.jpg"));
			}
			catch (IOException e) {
				return;
			}
		
		this.changeImage(imageS);

		positionLookup();
	}
	/**
	 * Automatikusan beállítja az elem pozícióját a modell alapján.
	 */
	private void positionLookup() {
		for (JunctionGraphic g : Controller.pipelineObjects) {
			if (g.objectMatch(player.getPosition())) {
				changePosition(g);
				break;
			}
		}
		for (PipeGraphic g : Controller.pipeObjects) {
			if (g.objectMatch(player.getPosition())) {
				changePosition(g);
				break;
			}
		}
	}
	
	/**
	 * Visszaadja az elem x pozícióját.
	 * @return
	 */
	@Override
	public int get_x() {
		if (position == null)
			return -1;
		return position.get_x();
	}
	/**
	 * Visszaadja az elem y pozícióját.
	 * @return
	 */
	@Override
	public int get_y() {
		if (position == null)
			return -1;
		return position.get_y();
	}
		
	/**
	 * Kirajzolja az elemet az adott rajzfelületre. Csak akkor rajzol, ha az elem pozíciója be van állítva.
	 * @param graphics A rajzfelület.
	 */
	@Override
	public void draw(Graphics graphics) {
		if (position == null)
			return;
		if (selected) {
			graphics.setColor(Color.green);
			graphics.drawRect(get_x() - getWidth() /2 - 5, get_y() - getHeight() / 2 - 5, getWidth() + 10, getHeight() + 10);
			graphics.setColor(Color.black);
		}
		super.draw(graphics);
	}
	
	
	/**
	 * Áthelyezi a játékos képét az adott grafikus elemre.
	 * @param position Az új grafikus elem.
	 */
	private void changePosition(PipelineGraphic position) {
		this.position = position;
	}
	/**
	 * Kiválasztja a játékost, és a helyzetének megfelelően beállítja a felhasználói felület elemeit.
	 */
	public void select() {
		selected = true;
		
		Controller.moveButton.setEnabled(true);
		Controller.moveButton.setTargetObject(this);
		Controller.setInputButton.setEnabled(position.canSetInput());
		Controller.setInputButton.setTargetObject(this);
		Controller.setOutputButton.setEnabled(position.canSetOutput());
		Controller.setOutputButton.setTargetObject(this);
		Controller.repairButton.setEnabled(position.canRepair() && !isSaboteur);
		Controller.repairButton.setTargetObject(this);
		Controller.breakButton.setEnabled(position.canBreak());
		Controller.breakButton.setTargetObject(this);
		Controller.stickyButton.setEnabled(position.canSticky());
		Controller.stickyButton.setTargetObject(this);
		Controller.slipperyButton.setEnabled(position.canSlippery() && isSaboteur);
		Controller.slipperyButton.setTargetObject(this);
		Controller.pickupButton.setEnabled(position.canPickup() && !isSaboteur);
		Controller.pickupButton.setTargetObject(this);
		Controller.placeButton.setEnabled(position.canPlace() && !isSaboteur);
		Controller.placeButton.setTargetObject(this);
		
		{
			Controller.objectDropDown.removeAllItems();
			for (WaterFlow obj : position.objectGet().getNeighbors()) {
				Controller.objectDropDown.addItem(Controller.game.getKeyFromPipeElements(obj));
			}
		}
	}
	
	
	/**
	 * Átlépteti a játékost a legördülő menü által meghatározott szomszédos elemre.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean move() {
		if (!selected)
			return false;
		
		int index = Controller.objectDropDown.getSelectedIndex();

		selected = false;
		boolean result =  player.InputCallback_Move(index) != null;

		positionLookup();
		return result;
	}
	/**
	 * Beállítja a játékos pozícióján a bemenetet a meghatározott szomszédos elemre.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean setInput() {
		if (!selected)
			return false;
		
		int index = Controller.objectDropDown.getSelectedIndex();

		selected = false;
		return player.InputCallback_SetInput(index);
	}
	/**
	 * Beállítja a játékos pozícióján a kimenetet a meghatározott szomszédos elemre.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean setOutput() {
		if (!selected)
			return false;
		
		int index = Controller.objectDropDown.getSelectedIndex();

		selected = false;
		return player.InputCallback_SetOutput(index);
	}
	/**
	 * Megjavítja a játékos pozícióját.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean repairObject() {
		if (!selected)
			return false;
		
		selected = false;
		return player.InputCallback_Repair();
	}
	/**
	 * Tönkreteszi a játékos pozícióját.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean breakObject() {
		if (!selected)
			return false;
		
		selected = false;
		return player.InputCallback_Break();
	}
	/**
	 * Felveszi a legördülő menü által meghatározott szomszédos elemet.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean pickupObject() {
		if (!selected)
			return false;
		
		int index = Controller.objectDropDown.getSelectedIndex();
		
		selected = false;
		return player.InputCallback_Pickup(index) != null;
	}
	/**
	 * Lehelyezi a játékos által tartott elemet.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean placeObject() {
		if (!selected)
			return false;
		
		selected = false;
		return player.InputCallback_Place();
	}
	/**
	 * Ragadóssá teszi a játékos pozícióját.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean makeSticky() {
		selected = false;
		return player.InputCallback_MakePipeSticky();
	}
	/**
	 * Csúszóssá teszi a játékos pozícióját.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean makeSlippery() {
		selected = false;
		return player.InputCallback_MakePipeSlippery();
	}
}
