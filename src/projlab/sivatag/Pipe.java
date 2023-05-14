package projlab.sivatag;

import java.util.Random;

/**
 * A csőhálózat eleme. Lehetővé teszi a víz folyását pumpák, források és ciszternák között.
 * @author fgreg
 *
 */
public class Pipe extends WaterFlow {
	/**
	 * Igaz, ha a cső lyukas és kifolyik belőle a víz, hamis, ha nem.
	 */
	private boolean punctured = false;
	/**
	 * Igaz, ha a cső ragadós, hamis, ha nem az.
	 */
	private boolean sticky = false;
	/**
	 * Igaz, ha a cső csúszós, hamis, ha nem az.
	 */
	private boolean slippery = false;
	/**
	 * Ennyi ideig ragadós vagy csúszós még a cső.
	 */
	private int countDown = 0;
	/**
	 * Ennyi ideig nem lyukasztható újra a cső.
	 */
	private int notPuncturableCountDown = 0;

	/**
	 * Létrehoz egy Pipe objektumot az alapértelmezett kapacitásokkal.
	 */
	public Pipe(){

	}
	/**
	 * Létrehoz egy Pipe objektumot a megadott kapacitásokkal.
	 * @param bufferCapacity Az objektum által tárolható víz maximális mennyisége.
	 *                       0-nál kisebb érték esetén az alapértelmezett értéket állítja be.
	 * @param transferCapacity Az elem által egy FlowTick hívás alatt a következő elembe továbbított víz mennyisége.
	 */
	public Pipe(int bufferCapacity, int transferCapacity) {
		if(bufferCapacity >= 0)
			this.bufferCapacity = bufferCapacity;
		if (transferCapacity >= 0)
			this.transferCapacity = transferCapacity;
	}
	/**
	 * Megpróbál lehelyezni egy játékost a csőre.
	 * Ha a cső ragadós, akkor lebénítja a játékost, és beállítja a countDown értékét.
	 * Ha a cső csúszós, akkor a játékost valamelyik szomszédjára dobja.
	 * @param player A lehelyezendő játékos.
	 * @return Igazzal tér vissza ha a csövön nem áll játékos, hamissal, ha a csövön állnak, vagy a cső csúszós.
	 */
	@Override
	public WaterFlow putPlayer(Player player) {
		Random rand = new Random();
		if(!players.isEmpty())
			return null;
		else{
			if(sticky){
				players.add(player);
				player.paralyze();
				countDown = rand.nextInt();
				return this;
			}
			else if(slippery){
				int index = rand.nextInt(2);
				return neighbors.get(index).putPlayer(player);
			}
			else {
				players.add(player);
				return this;
			}
		}
	}
	/**
	 * Megjavítja a tönkrement csövet, valamint beállítja a notPuncturableCountDown értékét egy véletlen számra.
	 * @return Igazzal tér vissza, ha a javítás sikeres, hamissal, ha nem.
	 */
	@Override
	public boolean repairObject() {
		if(punctured){
			Random rand = new Random();
			notPuncturableCountDown = rand.nextInt();
			punctured = false;
			return true;
		}
		else
			return false;
	}
	/**
	 * Meghívódik, ha az adott csövet tönkreteszik.
	 * Csak akkor teszi tönkre a csövet, ha a paraméter hamis, és a notPuncturableCountDown értéke <= 0.
	 * @param controller Igaz, ha a kontroller hívja meg a metódust, hamis, ha a játékos hívja meg.
	 * @return Igazzal tér vissza, ha a tönkretétel sikeres, hamissal, ha nem.
	 */
	@Override
	public boolean breakObject(boolean controller) {
		if(!controller && notPuncturableCountDown <= 0 && !punctured){
			punctured = true;
			return true;
		}
		else
			return false;
	}
	/**
	 * Ha a cső lyukas, törli a benne lévő vizet, különben továbbítja a kimeneti elemének.
	 */
	@Override
	public void flowTick() {
		if(punctured)
			buffer = 0;
		else
			buffer -= neighbors.get(output).receiveWater(this, transferCapacity);
	}
	/**
	 * Ragadóssá teszi a csövet.
	 * @return Igazzal tér vissza, ha a ragadóssá tétel sikeres, hamissal, ha nem.
	 */
	@Override
	public boolean turnSticky(){
		if(sticky || slippery)
			return false;
		else{
			sticky = true;
			return true;
		}
	}
	/**
	 * Csúszóssá teszi a csövet.
	 * @return Igazzal tér vissza, ha a csúszóssá tétel sikeres, hamissal, ha nem.
	 */
	@Override
	public boolean turnSlippery(){
		if(sticky || slippery)
			return false;
		else{
			slippery = true;
			return true;
		}
	}
	/**
	 * A kontroller hívja meg bizonyos időközönként.
	 * Ha a countDown és notPuncturableCountDown értéke nem nulla 1-gyel csökkenti azokat.
	 * Ha a countDown értéke 0 hamisra állítja a sticky-t vagy a slippery-t.
	 * Ha a sticky igazról hamisra váltott, akkor meghívja a rajta álló játékos Unparalyze() metódusát.
	 */
	public void countDown(){
		if(countDown > 0)
			countDown--;
		if(notPuncturableCountDown > 0)
			notPuncturableCountDown--;
		if(countDown <= 0){
			if(slippery)
				slippery = false;
			if(sticky) {
				players.get(0).unparalyze();
				sticky = false;
			}
		}
	}
	/**
	 * Meghívódik, ha a játékos az adott csövet megkísérli felvenni.
	 * @param oldNeighbor azt a szomszédot jelöli, amelyik mellől az elem fel lett véve.
	 * @return Igazzal tér vissza, ha a felvétel sikeres, hamissal, ha nem.
	 */
	@Override
	public boolean pickUp(WaterFlow oldNeighbor){
		if (players.isEmpty()) {
			oldNeighbor.removeNeighbor(this);
			return true;
		}
		else
			return false;
	}
}
