package projlab.graphics;

import java.awt.event.*;
import projlab.controller.*;

import javax.swing.JButton;

/**
 * A játékosokon keresztüli csövek ragadóssá tételéért felelős gomb osztálya.
 */
public class StickyButton extends JButton implements ActionListener {
	/**
     * A gombra klikkelve indított műveletek az itt hivatkozott
     * játékost reprezentáló grafikus elemen végrehajtandók.
     */
	private PlayerGraphic targetObject;
	
	/**
     * A gombot létrehozó konstruktor.
     * @param targetObject Az a PlayerGraphic objektum, amin a műveletek elvégzendőek.
     */
	public StickyButton(PlayerGraphic targetObject)
	{
		super("Make sticky");
		this.targetObject = targetObject;
		addActionListener(this);
		setEnabled(false);
	}
	/**
     * Visszaadja a tárolt PlayerGraphic objektumot.
     * @return targetObject A tárolt PlayerGraphic objektum.
     */
	public PlayerGraphic getTargetObject()
	{
		return targetObject;
	}
	/**
     * Módosítja a tárolt PlayerGraphic objektumot a paraméterben megadottra
     * @param targetObject Az új PlayerGraphic objektum.
     */
	public void setTargetObject(PlayerGraphic targetObject)
	{
		if (targetObject == null)
			throw new IllegalArgumentException();
		this.targetObject = targetObject;
	}

	/**
     * Kezdeményezi a játékoson keresztül a cső csúszóssá tételését,
     * ha a felhasználó a gombra kattint.
     * @param e The event to be processed.
     */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		synchronized (Controller.lock)
		{
			targetObject.makeSticky();
			Controller.selectNextPlayer();
		}
	}
}