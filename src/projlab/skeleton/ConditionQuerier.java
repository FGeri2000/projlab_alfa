package projlab.skeleton;

/**
 * Skeleton osztály. Olyan esetekben, amikor a modell nem tartalmazza a szükséges információt egy use-case lefutásához, ez az osztály felelős a felhasználói bemenet feldolgozására.
 * Mivel az egyes use-case-eket inicializáló kommunikációs modellek is a tesztelendő függvényeket használják, és szükséges hogy az inicializáció a felhasználó hozzászólása
 * nélkül megtörténjen, képes alapértelmezett válaszokat nyújtani a felé irányúló kérdésekre.
 * @author fgreg
 *
 */
public class ConditionQuerier {

	/**
	 * Ha igaz, a felhasználó felé irányuló kérdések egy alapértelmezett választ kapnak.
	 */
	private static boolean useDefaults = false;
	/**
	 * Az igaz/hamis kérdésekre való alapértelmezett válasz.
	 */
	private static boolean defBoolean = false;
	/**
	 * A numerikus kérdésekre való alapértelmezett válasz.
	 */
	private static int defInteger = 0;
	
	
	/**
	 * Engedélyezi az alapértelmezett válaszok használatát felhasználói bemenet helyett.
	 */
	public static void EnableDefaults() {
		useDefaults = true;
	}
	/**
	 * Kikapcsolja az alapértelmezett válaszok használatát.
	 */
	public static void DisableDefaults() {
		useDefaults = false;
	}

	/**
	 * Beállítja az alapértelmezett választ az igaz/hamis kérdésekre.
	 * @param bool
	 */
	public static void SetDefaultBoolean(boolean bool) {
		defBoolean = bool;
	}
	/**
	 * Beállítja az alapértelmezett választ az numerikus kérdésekre.
	 * @param integer
	 */
	public static void SetDefaultInteger(int integer) {
		defInteger = integer;
	}
	

	/**
	 * Feltesz egy igaz/hamis kérdést a felhasználónak.
	 * @param message A felhasználónak feltett szöveges kérdés.
	 * @return A felhasználó válasza, vagy az alapértelmezett válasz.
	 */
	public static boolean QueryUserForBoolean(String message) {
		if (useDefaults)
			return defBoolean;
						
		while (true) {
			System.out.print(message + " ");
			String line = Main.InputScanner.nextLine();

			switch (line.trim().toLowerCase()) {
			case "i":
			case "igaz":
			case "igen":
			case "y":
			case "yes":
			case "t":
			case "true":
				return true;
			case "n":
			case "nem":
			case "h":
			case "hamis":
			case "no":
			case "f":
			case "false":
				return false;
			}
		}		
	}
	/**
	 * Feltesz egy numerikus válasz váró kérdést a felhasználónak.
	 * @param message A felhasználónak feltett szöveges kérdés.
	 * @return A felhasználó válasza, vagy az alapértelmezett válasz.
	 */
	public static int QueryUserForInteger(String message) {
		if (useDefaults)
			return defInteger;
				
		while (true) {
			System.out.print(message + " ");
			String line = Main.InputScanner.nextLine();
			
			try {
				int val = Integer.parseInt(line);
				return val;
			}
			catch (RuntimeException e) { }
		}		
	}
}
