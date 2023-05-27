package projlab.graphics;

import projlab.sivatag.Cistern;

/**
 * Felelős a játékban megtalálható ciszternák kirajzolásáért.
 */
public class CisternGraphic extends PiplineGraphic{
    /**
     * A ciszterna, amit az objektum reprezentál.
     */
    private Cistern cistern;

    /**
     * Létrehoz egy új CisternGraphic objektumot, a paraméterben megadott ciszternára hivatkozva.
     * @param cistern A ciszterna, amire az objektum hivatkozik.
     */
    public CisternGraphic(Cistern cistern){
        this.cistern = cistern;
    }
}
