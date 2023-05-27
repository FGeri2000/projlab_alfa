package projlab.graphics;

import projlab.sivatag.Source;

/**
 * Felelős a játékban megtalálható források kirajzolásáért.
 */
public class SourceGraphic extends PiplineGraphic{
    /**
     * A forrás, amit az objektum reprezentál.
     */
    private Source source;

    /**
     * Létrehoz egy új SourceGraphic objektumot, a paraméterben megadott forrásra hivatkozva.
     * @param source A forrás, amire az objektum hivatkozik.
     */
    public SourceGraphic(Source source){
        this.source = source;
    }
}
