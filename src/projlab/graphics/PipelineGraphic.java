package projlab.graphics;

import projlab.sivatag.WaterFlow;

public abstract class PipelineGraphic extends Graphic {

    /**
     * Ellenőrzi, hogy a PipelineGraphic a paraméterben megadott objektumot reprezentálja-e.
     * @param object Modellbeli WaterFlow objektum.
     * @return Igaz, ha a PipelineGraphic a paraméterben megadott objektumot reprezentálja.
     */
    public abstract boolean objectMatch(WaterFlow object);
    /**
     * Visszaadja az objektumot amit ez a PipelineGraphic reprezentál.
     * @return Az objektum amit ez a PipelineGraphic reprezentál.
     */
    public abstract WaterFlow objectGet();
	
	/**
	 * Visszaadja, hogy lehetséges-e ezen az objektumon bemenetet beállítani.
	 * @return Igaz, ha lehetséges ezen az objektumon bemenetet beállítani.
	 */
    public abstract boolean canSetInput();
	/**
	 * Visszaadja, hogy lehetséges-e ezen az objektumon kimenetet beállítani.
	 * @return Igaz, ha lehetséges ezen az objektumon kimenetet beállítani.
	 */
    public abstract boolean canSetOutput();
	/**
	 * Visszaadja, hogy lehetséges-e ezt az objektumot megjavítani.
	 * @return Igaz, ha lehetséges ezt az objektumot megjavítani.
	 */
    public abstract boolean canRepair();
	/**
	 * Visszaadja, hogy lehetséges-e ezt az objektumot tönkretenni.
	 * @return Igaz, ha lehetséges ezt az objektumot tönkretenni.
	 */
    public abstract boolean canBreak();
	/**
	 * Visszaadja, hogy lehetséges-e ezt az objektumot ragadóssá tenni.
	 * @return Igaz, ha lehetséges ezt az objektumot ragadóssá tenni.
	 */
    public abstract boolean canSticky();
	/**
	 * Visszaadja, hogy lehetséges-e ezt az objektumot csúszóssá tenni.
	 * @return Igaz, ha lehetséges ezt az objektumot csúszóssá tenni.
	 */
    public abstract boolean canSlippery();
	/**
	 * Visszaadja, hogy lehetséges-e ezen az objektumon elemet felvenni.
	 * @return Igaz, ha lehetséges ezen az objektumon elemet felvenni.
	 */
    public abstract boolean canPickup();
	/**
	 * Visszaadja, hogy lehetséges-e ezen az objektumon elemet letenni.
	 * @return Igaz, ha lehetséges ezen az objektumon elemet letenni.
	 */
    public abstract boolean canPlace();
}
