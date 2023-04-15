package projlab.sivatag;

public class Source extends WaterFlow {
	/**
	 * @implNote Forrás típusú elemen érvénytelen, tehát hamissal tér vissza.
	 * @return Hamis. 
	 */
	@Override
	public boolean Repair() {
		System.out.print("boolean Source.Repair()");
		
		return false;
	}
	/**
	 * @implNote Forrás típusú elemen érvénytelen, tehát hamissal tér vissza.
	 * @return Hamis. 
	 */
	@Override
	public boolean Break(boolean controller) {
		System.out.print("boolean Source.Break(boolean)");
		
		return false;
	}
	/**
	 * @implNote Forrás típusú elemen érvénytelen, tehát hamissal tér vissza.
	 * @return Hamis.
	 */
	@Override
	public boolean SetInput(int[] inputs) {
		System.out.print("boolean Source.SetInput(int[])");

		return false;
	}
	/**
	 * Végtelen (buffer által nem korlátozott) mennyiségű vizet továbbít a forrás kimeneti elemébe.
	 */
	@Override
	public void FlowTick() {
		System.out.print("void Source.FlowTick()");
	}
}
