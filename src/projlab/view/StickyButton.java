package projlab.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

/**
 * A csövet megjalanítő vízhálózati elemek ragadóssá tételéhez szükséges felhasználói
 * interakcióért felelős gomb. Csak akkor aktív, ha a kiválasztott elem egy csövet reprezentál,
 * ami nem ragadós.
 */
public class StickyButton extends JButton implements ActionListener {
    /**
     * A gombra klikkelve indított műveletek az itt hivatkozott
     * csövet reprezentáló grafikus elemen végrehajtandók.
     */
    private PipeGraphic targetObject;

    /**
     * A gombot létrehozó konstruktor. A
     * paraméter a targetObject attribútum, amin gombnyomásra a műveletek elvégzendők.
     * @param targetObject
     */
    public StickyButton(PipeGraphic targetObject){
        super();
        this.targetObject = targetObject;
        if(targetObject.getBindingObject().isSticky()) setEnabled(false);
    }

    /**
     * A JButton függvényének felülírása. Ha a
     * paraméter bal egérgombbal kattintás által kiváltott esemény, a gomb meghívja a
     * targetObjectben tárolt grafikus elemhez kapcsolt cső “turnSticky” metódusát.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if((e.getModifiers() & InputEvent.BUTTON1_DOWN_MASK) != 0){
            if(targetObject.getBindingObject().turnSticky()){
                //TODO countDown
                setEnabled(false);
            }
        }
    }
    public PipeGraphic getTargetObject(){return targetObject;}
    public void setTargetObject(PipeGraphic targetObject){
        if(targetObject == null) throw new IllegalArgumentException();
        this.targetObject = targetObject;
        if(!targetObject.getBindingObject().isSticky()) setEnabled(true);
    }
}
