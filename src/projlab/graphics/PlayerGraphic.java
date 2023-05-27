package projlab.graphics;

import projlab.sivatag.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
	 * Igaz, a ez a játékos a jelenleg soron következő.
	 */
	private boolean selected = false;
	/**
	 * A kirajzolandó kép.
	 */
	private Image img = null;
	
	/**
	 * Létrehoz egy új játékos grafikai elemet egy szerelőnek.
	 * @param plumber A szerelő játékos.
	 * @param altImage Vált a kettő kirajzolható kép között.
	 */
	public PlayerGraphic(Plumber plumber, boolean altImage) {
		isSaboteur = false;
		player = plumber;

		if (altImage) {
			img = ImageIO.read(new File("plumber2.jpg"));
		}
		else {
			img = ImageIO.read(new File("plumber1.jpg"));	
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

		img = ImageIO.read(new File("saboteur.jpg"));

		positionLookup();
	}
	/**
	 * Automatikusan beállítja az elem pozícióját a modell alapján.
	 */
	private void positionLookup() {
		for (PipelineGraphic g : Controller.pipelineObjects) {
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
	//@Override
	public int get_x() {
		if (position == null)
			return -1;
		return position.get_x();
	}
	/**
	 * Visszaadja az elem y pozícióját.
	 * @return
	 */
	//@Override
	public int get_y() {
		if (position == null)
			return -1;
		return position.get_y();
	}
		
	/**
	 * Kirajzolja az elemet az adott rajzfelületre. Csak akkor rajzol, ha az elem pozíciója be van állítva.
	 * @param graphics A rajzfelület.
	 */
	//@Override
	public void draw(Graphics graphics) {
		if (position == null)
			return;
		graphics.drawImage(img, get_x(), get_y(), getWidth(), getHeight(), null);
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
		Controller.setInputButton.setEnabled(position.canSetInput());
		Controller.setOutputButton.setEnabled(position.canSetOutput());
		Controller.repairButton.setEnabled(position.canRepair() && !isSaboteur);
		Controller.breakButton.setEnabled(position.canBreak());
		Controller.stickyButton.setEnabled(position.canSticky());
		Controller.slipperyButton.setEnabled(position.canSlippery() && isSaboteur);
		Controller.pickupButton.setEnabled(position.canPickup() && !isSaboteur);
		Controller.placeButton.setEnabled(position.canPlace() && !isSaboteur);
		
		{
			Controller.objectDropdown.removeAll();
			for (WaterFlow obj : position.objectGet().getNeighbors()) {
				Controller.objectDropdown.addItem(Controller.game.getKeyFromPipeElements(obj));
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
		
		int index = Controller.objectDropdown.getSelectedIndex();

		selected = false;
		return player.InputCallback_Move(index) != null;
	}
	/**
	 * Beállítja a játékos pozícióján a bemenetet a meghatározott szomszédos elemre.
	 * @return Igaz ha sikeres, hamis ha nem.
	 */
	public boolean setInput() {
		if (!selected)
			return false;
		
		int index = Controller.objectDropdown.getSelectedIndex();

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
		
		int index = Controller.objectDropdown.getSelectedIndex();

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
		
		int index = Controller.objectDropdown.getSelectedIndex();
		
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
