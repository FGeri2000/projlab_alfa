package projlab.graphics;

import projlab.sivatag.Pipe;

import java.awt.*;
import java.util.Random;

/**
 * A modell egy “Pipe” objektumpéldányának, azaz a vízhálózatban egy csőnek a
 * megjelenítéséért, és az azon végzett műveletek közvetítéséért felelős osztály.
 */
public class PipeGraphic extends Graphic{
    /**
     * Az a csövet reprezentáló “Pipe” példány, aminek a
     * megjelenítéséért ez a példány felelős.
     */
    private Pipe bindingObject;
    /**
     * A cső által összekötött, vízhálózati elemeket
     * megjelenítő objektumpéldányokat tároló, alapértelmezetten 2 elemű tömb. Ezeknek az elemeknek a
     * koordinátái szükségesek a csövet megjelenítő vonal kirajzolásához.
     */
    private PipelineGraphic[] connectedObjects;

    /**
     * Új PipeGraphic példányt létrehozó konstruktor
     * @param pipe A modell megjelenítendő “Pipe” példánya
     */
    public PipeGraphic(Pipe pipe){
        bindingObject = pipe;
        connectedObjects = new PipelineGraphic[2];
    }

    /**
     * Kirajzolja a csövet a két megadott végpontja közé
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics){
        if(connectedObjects[0] == null && connectedObjects[1] == null)
            return;
        int[][] coordinates = new int[2][2];
        for(int i=0; i<coordinates.length; i++){
            Random random = new Random();
            if(connectedObjects[i] != null){
                coordinates[i][0] = connectedObjects[i].get_x();
                coordinates[i][1] = connectedObjects[i].get_y();
            }
            else{
                //Ha a cső vége szabad, behúzzuk random helyre
                coordinates[i][0] = random.nextInt(100);
                coordinates[i][1] = random.nextInt(100);
            }
        }
        graphics.drawLine(coordinates[0][0], coordinates[0][1], coordinates[1][0], coordinates[1][1]);
    }

    public Pipe getBindingObject(){return bindingObject;}
    public PipelineGraphic[] getConnectedObjects(){return connectedObjects;}
    public PipelineGraphic getConnectedObject(int idx){
        if(idx<0 || idx>=connectedObjects.length) return null;
        return connectedObjects[idx];
    }
    public PipelineGraphic getConnectedObject(PipelineGraphic object){
        if(object == null) return null;
        for(PipelineGraphic connectedObject : connectedObjects)
            if(connectedObject.equals(object)) return connectedObject;
        return null;
    }

    /**
     * A connectedObjects tároló első
     * elemének módosítása a paraméterként megadott objektumra
     * @param object A befolyó vizet áramoltató vízhálózati elem
     */
    public void setFirstObject(PipelineGraphic object){
        if(object==null) throw new NullPointerException();
        connectedObjects[0] = object;
    }

    /**
     * A connectedObjects tároló második
     * elemének módosítása a paraméterként megadott objektumra.
     * @param object A kifolyó vizet áramoltató vízhálózati elem
     */
    public void setSecondObject(PipelineGraphic object){
        if(object==null) throw new NullPointerException();
        connectedObjects[1] = object;
    }

    /**
     * Eltávolítja a paraméterrel
     * egyenlő elemet a connectedObjects tárolóból, és visszatér vele. Ha a tárolóban nincs
     * ilyen elem, a visszatérési értéke null.
     * @param object
     * @return
     */
    public PipelineGraphic removeObject(PipelineGraphic object) {
        if (object == null) return null;
        PipelineGraphic removedObject = null;
        for (int i = 0; i < connectedObjects.length; i++) {
            if (connectedObjects[i].equals(object)) {
                removedObject = connectedObjects[i];
                connectedObjects[i] = null;
            }
        }
        return removedObject;
    }
    /**
     * Visszatér az objektum x koordinátájával.
     */
    @Override
    public int get_x() {throw new UnsupportedOperationException();}

    /**
     * Visszatér az objektum y koordinátájával.
     */
    @Override
    public int get_y() {throw new UnsupportedOperationException();}
}
