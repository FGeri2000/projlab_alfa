package projlab.sivatag;

/**
 * Ősosztály a játékosok karaktereinek, amely tárolja a tartózkodási helyét és lehetővé teszi a játékosi bemenetek fogadását.
 * @author fgreg
 *
 */
public abstract class Player {
	/**
	 * Létrehoz egy új játékost, elhelyezve az adott csőhálózati elemen.
	 * @param position A csőhálózati elem, amire az új játékost kívánjuk helyezni.
	 */
	public Player(WaterFlow position) {
		this.position = position;
	}
	
	/**
	 * A csőhálózati elem, amin a karakter jelenleg tartózkodik.
	 */
	protected WaterFlow position = null;
	/**
	 * A csőhálózat azon eleme, amelyet a játékos jelenleg mozgat.
	 */
	protected WaterFlow heldObject = null;
	/**
	 * Igaz, ha a játékos egy ragadós csőre lépett és nem tud elmozdulni.
	 */
	private boolean paralyzed = false;
	
	/**
	 * Játékosi bemenetre meghívódik, és a karakter (amennyiben lehetséges) áthelyeződik a tartózkodási helyének a `neighbor` index-el meghatározott szomszédjára.
	 * @param neighbor A jelenlegi tartózkodási elem szomszédaira érvényes index, ami meghatározza a játékos új kívánt pozícióját.
	 * @return Az új pozíció, ha az áthelyezés sikeres, a régi pozíció, ha sikertelen.
	 */
	public WaterFlow InputCallback_Move(int neighbor) {		
		if (paralyzed)
		{
			return position;
		}
		
		position = position.movePlayer(this, neighbor);
		
		return position;
	}
	/**
	 * Játékosi bemenetre meghívódik, és a tartózkodási helyén (amennyiben az pumpa) beállítja a kimenei csövet az index által meghatározottra.
	 * @param inPipe A szomszédos elem indexe, amit a pumpa bemenetének kívánunk beállítani.
	 * @return Igaz, ha a beállítás sikeres volt, hamis, ha a valamilyen hiba folytán a beállítás változatlan maradt.
	 */
	public boolean InputCallback_SetInput(int inPipe) {
		return position.setInput(new int[] { inPipe });
	}
	/**
	 * Játékosi bemenetre meghívódik, és a tartózkodási helyén (amennyiben az pumpa) beállítja a kimenei csövet az index által meghatározottra.
	 * @param outPipe A szomszédos elem indexe, amit a pumpa kimenetének kívánunk beállítani.  
	 * @return Igaz, ha a beállítás sikeres volt, hamis, ha a valamilyen hiba folytán a beállítás változatlan maradt.
	 */
	public boolean InputCallback_SetOutput(int outPipe) {		
		return position.setOutput(outPipe);
	}
	
	/**
	 * Játékosi bemenetre meghívódik, és megjavítja a tartózkodási helyének megfelelő elemet, amennyiben az engedi és a játékos tud szerelni.
	 * @return Igaz, ha a javítás sikeres, hamis ha sikertelen vagy az elem nem törött.
	 */
	public abstract boolean InputCallback_Repair();

	/**
	 * Játékosi bemenetre meghívódik, és tönkreteszi a tartózkodási helyének megfelelő elemet, amennyiben az engedi és a játékos tud tönkretenni.
	 * @return Igaz, ha a tönkretétel sikeres, hamis ha sikertelen vagy az elem már törött.
	 */
	public boolean InputCallback_Break() {
		return position.breakObject(false);
	}
	/**
	 * Játékosi bemenetre meghívódik, és felveszi a tartózkodási helyéhez csatlakozó, az adott index-el meghatározott elemét.
	 * @param neighbor A tartózkodási elemre szomszédaira érvényes index, ami a felvevendő elemre mutat.
	 * @return Az elem, ha a felvétel sikeres, null, ha sikertelen.
	 */
	public abstract WaterFlow InputCallback_Pickup(int neighbor);
	/**
	 * Játékosi bemenetre meghívódik, lehelyezi és hozzáilleszti a tartózkodási helyéhez a hordott elemet.
	 * @return Igaz, ha a tárgy el lett helyezve, hamis, ha a játékos nem fog semmit, vagy a tárgy nem lett elhelyezve és a játékos még mindig fogja.
	 */
	public abstract boolean InputCallback_Place();
	/**
	 * Meghívódik, ha egy ragadós csőre lépett a játékos. A lebénult állapotot igazzá teszi.
	 */
	public void paralyze()
	{
		paralyzed = true;
	}
	/**
	 * Megszűnteti a játékos lebénult állapotát.
	 */
	public void unparalyze()
	{
		paralyzed = false;
	}
	/**
	 * Játékosi bemenetre meghívódik, és megkísérli a WaterFlow objektumot ragadóssá tenni, amin a játékos áll.
	 * @return Igaz, ha sikerült ragadóssá tenni az objektumot, hamis, ha nem.
	 */
	public boolean InputCallback_MakePipeSticky()
	{
		return position.turnSticky();
	}
	/**
	 * Játékosi bemenetre meghívódik, és megkísérli a WaterFlow objektumot csúszóssá tenni, amin a játékos áll.
	 * @return Igaz, ha sikerült csúszóssá tenni az objektumot, hamis, ha nem.
	 */
	public boolean InputCallback_MakePipeSlippery()
	{
		return position.turnSlippery();
	}
	
	/**
	 * Visszaadja a játékos tartózkodási helyét.
	 * @return A WaterFlow amin a játékos áll.
	 */
	public WaterFlow getPosition() {
		return position;
	}
	/**
	 * Azonnal áthelyezi a játékost az adott pozícióra, bénítást figyelmen kívül hagyva, de csúszós csöveket és játékoshatárokat figyelmebe véve.
	 * @param newPosition A WaterFlow, amire a játékost kívánjuk ráhelyezni.
	 * @return Az új elem, amin a játékos áll.
	 */
	public WaterFlow movePlayer(WaterFlow newPosition) {
		WaterFlow n = newPosition.putPlayer(this);
		if (n != null) {
			position.players.remove(this);
			position = n;
		}
		return position;
	}
	public boolean isParalyzed(){return paralyzed;}
}
