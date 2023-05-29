package projlab.graphics;
import projlab.sivatag.*;
import projlab.sivatag.WaterFlow;
import projlab.controller.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class BreakButton extends JButton {
    /**
     * A gombra klikkelve indított műveletek az itt
     * hivatkozott vízhálózati elemet reprezentáló grafikus elemen végrehajtandók.
     */
    private Graphic targetObject;

    /**
     * A gombot létrehozó konstruktor.
     * @param targetObject, a paraméter az a hivatkozott vízhálózati elem,
     *                      a min gombnyomásra a műveletek végrehajtandók.
     */
    public BreakButton (Graphic targetObject)
    {
        this.targetObject = targetObject;
        updateButtonState();
        addActionListener(new BreakActionListener());
    }

    /**
     * Visszaadja a targetObject értékét.
     * @return targetObject attribútum értéke
     */
    public Graphic getTargetObject()
    {
        return targetObject;
    }

    /**
     * Beállítja a targetObject értékét a megadott paraméterre.
     * @param targetObject
     */
    public void setTargetObject(Graphic targetObject)
    {
        if(targetObject != null)
            this.targetObject = targetObject;
    }

    /**
     * A függvény segítségével figyeljük az egér kattintást,
     * és tudjuk megvalósítani a Break metódust.
     */
    private class BreakActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	synchronized (Controller.lock)
        	{
	            if (e.getModifiers() == MouseEvent.BUTTON1_MASK)
	            {
	                if (targetObject != null && targetObject instanceof Graphic)
	                {
	                    PipeGraphic element = (Graphic) targetObject;
	                    element.breakObject(true);
	                    updateButtonState();
	                }
	                
	                notify();
	            }
        	}
        }
    }

    /**
     * A függvény segítségével tudjuk beállítani,
     * hogy a gomb elérhető legyen, vagy sem.
     */
    private void updateButtonState()
    {
        if(targetObject != null && targetObject instanceof Graphic)
        {
            Graphic element = (Graphic) targetObject;
            setEnabled(!element.breakObject(true));
        }

        else
            setEnabled(false);
    }
}
