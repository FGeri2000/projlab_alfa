package projlab.sivatag;

import java.util.LinkedList;
import java.util.Timer;

/**
 * A játék jelenlegi állapotát tároló struktúra.
 * @author fgreg
 *
 */
public class Game {
	/**
	 * A játék órajelét biztosító Timer objektum.
	 */
	private Timer timer = new Timer();
	/**
	 * A játékból hátralévő idő.
	 */
	private int gameTimeLeft = 240;
	/**
	 * A csőhálózat elemeit tároló heterogén kollekció.
	 */
	private LinkedList<WaterFlow> pipeElements = new LinkedList<WaterFlow>();
	/**
	 * A játékosokat tároló heterogén kollekció.
	 */
	private Player[] players = new Player[4];
	
	/**
	 * Ez a metódussal létrehozza az alap map-et, inicializálja a map tárolót és feltölti a szükséges objektumokkal, hogy a játék elkezdődhessen.
	 */
	public void NewGame() {
		System.out.print("void Game.NewGame()");
	}
	/**
	 * Leállítja a játékot, lezárja a játékos karakterek irányítását, és értesítést küld a játék eredményéről.
	 */
	public void EndGame() {
		System.out.print("void Game.EndGame()");
	}
}
