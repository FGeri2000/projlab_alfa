package projlab.graphics;

import projlab.sivatag.Pipe;
import projlab.sivatag.Pump;

import java.awt.*;
import java.awt.image.*;

public abstract class Graphic {
	/**
	 * A kirajzolt objektum szélessége pixelben.
	 */
	private int width;
	/**
	 * A kirajzolt objektum magassága pixelben.
	 */
	private int height;
	/**
	 * A kép, ami az ablakon kirajzolásra kerül.
	 */
	private BufferedImage image;
	
	/**
	 * Kirajzolja az objektumot.
	 * @param g A rajzfelület, amire az objektumot rajzolja.
	 */
	public void draw(Graphics g)
	{
		g.drawImage(image, get_x(), get_y(), getWidth(), getHeight(), null);
	}
	/**
	 * Módosítja a tárolt méretet.
	 * @param width Az objektum új szélessége.
	 * @param height Az objektum új magassága.
	 */
	public void changeSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	/**
	 * Módosítja a tárolt képet.
	 * @param image Az új kép.
	 */
	public void changeImage(BufferedImage image)
	{
		this.image = image;
	}
	/**
	 * Visszatér az objektum x koordinátájával.
	 */
	public abstract int get_x();
	/**
	 * Visszatér az objektum y koordinátájával.
	 */
	public abstract int get_y();
	/**
	 * Visszatér az objektum szélességével.
	 */
	public int getWidth()
	{
		return width;
	}
	/**
	 * Visszatér az objektum magasságával.
	 */
	public int getHeight()
	{
		return height;
	}
}
