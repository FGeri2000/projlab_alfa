package projlab.sivatag;

import java.util.LinkedList;

/**
 * A csőhálózat eleme. Mozgatja a vizet a csőhálózatban. Beállítható a bemenete és kimenete.
 * @author fgreg
 *
 */
public class Pump extends WaterFlow {
	/**
	 * Ha igaz, a pumpán nem folyik keresztül a víz, ha hamis, akkor igen.
	 */
    protected boolean broken = false;
	/**
	 * Igaz, ha a pumpát egyszer már áthelyezték.
	 */
	protected boolean pickedUpOnce = false;

	/**
	 * Létrehoz egy Pump objektumot az alapértelmezett kapacitásokkal.
	 */
	public Pump(){

	}
	/**
	 * Létrehoz egy Pump objektumot a megadott kapacitásokkal.
	 * @param bufferCapacity Az objektum által tárolható víz maximális mennyisége.
	 *                       0-nál kisebb érték esetén az alapértelmezett értéket állítja be.
	 * @param transferCapacity Az elem által egy FlowTick hívás alatt a következő elembe továbbított víz mennyisége.
	 */
	public Pump(int bufferCapacity, int transferCapacity) {
		if(bufferCapacity >= 0)
			this.bufferCapacity = bufferCapacity;
		if (transferCapacity >= 0)
			this.transferCapacity = transferCapacity;
	}
	/**
	 * Megjavítja a tönkrement pumpát.
	 * @return Igazzal tér vissza, ha a javítás sikeres, hamissal, ha nem.
	 */
	@Override
	public boolean repairObject() {
		if(broken){
			broken = false;
			return true;
		}
		else
			return false;
	}
	/**
	 * Meghívódik, ha az adott pumpát tönkreteszik.
	 * Csak akkor teszi tönkre a pumpát, ha a paraméter igaz.
	 * @param controller Igaz, ha a kontroller hívja meg a metódust, hamis, ha a játékos hívja meg.
	 * @return Igazzal tér vissza, ha a tönkretétel sikeres, hamissal, ha nem.
	 */
	@Override
	public boolean breakObject(boolean controller) {
		if(controller && !broken){
			broken = true;
			return true;
		}
		else
			return false;
	}
	/**
	 * Ha nincs tönkremenve a pumpa, áthelyezi a benne található vizet a kimeneti elemébe.
	 */
	@Override
	public void flowTick() {
		if(!broken && output != -1) {
			buffer -= neighbors.get(output).receiveWater(this, Math.min(this.transferCapacity, buffer));
		}
	}
	/**
	 * Meghívódik, ha a játékos az adott pumpát megkísérli felvenni.
	 * A pumpa csak egyszer áthelyezhető.
	 * @param oldNeighbor azt a szomszédot jelöli, amelyik mellől az elem fel lett véve.
	 * @return Igazzal tér vissza, ha a felvétel sikeres, hamis, ha nem.
	 */
	@Override
	public boolean pickUp(WaterFlow oldNeighbor) {
		if(pickedUpOnce)
			return false;
		for(WaterFlow neighbor : this.neighbors){
			neighbor.removeNeighbor(this);
		}
		pickedUpOnce = true;
		return true;
	}
	/**
	 * Meghívódik, ha egy játékos megkisérli letenni a pumpát egy cső objektumhoz.
	 * Ha a cső egyik vége szabad a pumpa a szabad véghez kerül.
	 * Ha a csőnek nincs szabad vége, akkor létrehoz egy új csövet és a két cső közé helyezi le a pumpát.
	 * @param newNeighbor A cső, amihez a pumpát megkiséreljük letenni.
	 * @return Igazzal tér vissza, ha a letétel sikeres, hamis, ha nem.
	 */
	@Override
	public boolean putDown(WaterFlow newNeighbor){
		LinkedList<WaterFlow> pipeNeighbors = newNeighbor.getNeighbors();
		if(pipeNeighbors.size() == 1) {
			newNeighbor.addNeighbor(this);
			return true;
		}
		else if(pipeNeighbors.size() == 2){
			WaterFlow newPipe = new Pipe();
			newNeighbor.removeNeighbor(pipeNeighbors.get(1));
			newNeighbor.addNeighbor(this);
			newPipe.addNeighbor(pipeNeighbors.get(1));
			newPipe.addNeighbor(this);
			return true;
		}
		return false;
	}
	public boolean isBroken(){return broken;}
	public boolean isPickedUpOnce(){return pickedUpOnce;}
}
