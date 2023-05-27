package projlab.graphics;

import java.awt.*;

/**
 * Ősosztály a csőhálózat csomóponti elemeinek.
 */
public abstract class PiplineGraphic extends Graphic{
    /**
     * A kirajzolandó objektum x koordinátája pixelben
     */
    private int x;
    /**
     * A kirajzolandó objektum y koordinátája pixelben
     */
    private int y;

    /**
     * Megváltoztatja a tárolt koordinátákat a paraméterben megadottakra.
     * @param x Az objektum új x koordinátája pixelben.
     * @param y Az objektum új y koordinátája pixelben.
     */
    public void changeCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * Visszatér az objektum x koordinátájával.
     */
    @Override
    public int get_x() {
        return x;
    }
    /**
     * Visszatér az objektum y koordinátájával.
     */
    @Override
    public int get_y() {
        return y;
    }
    /**
     * Kirajzolja az objektumot a paraméterben megadott rajzfelületre.
     * @param graphics A rajzfelület, amire az objektumot rajzolja.
     */
    public void draw(Graphics graphics){
        graphics.drawImage(image, x, y, getWidth(), getHeight(), null);
    }
}
