package projlab.skeleton;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Skeleton osztály, ami felelős a tesztelésben használt hívási lista kiírásáért.<br>
 * A futtatás során egy belső pufferbe írja az információt amint az elérhetővé várik, majd kérésre kiírja sdt kimenetre.
 * @author fgreg
 *
 */
public class CallHierarchyWriter {
	/**
	 * Egy adott szekvenciában szereplő business objektumok, és a hozzájuk tartozó olvasható azonosítók listája.
	 */
	private static HashMap<Object, String> identifiers = new HashMap<Object, String>();
	
	/**
	 * A legutóbbi függvényhívást kezdeményező objektumokat tartalmazó stack. Szükséges, hogy a kimenet az előírt mintának megfelelően legyen kiírva.
	 */
	private static LinkedList<Object> callers = new LinkedList<Object>();
	/**
	 * Hívási sor jelenlegi mélysége, a kimenet indentálásához szükséges.
	 */
	private static int callDepth = 0;
	/**
	 * A már kiírt kimenetet tartalmazó puffer objektum.
	 */
	private static StringWriter callHierarchyOutput = new StringWriter();
	
	
	/**
	 * Hozzáadja az adott objektumot a függvényhívó stack-hez.
	 * @param caller Referencia a szekvenciában szereplő objektumra.
	 */
	public static void PushCaller(Object caller) {
		callers.push(caller);
	}
	/**
	 * Hozzáad egy objektum-azonosító párt a névfordító listához.
	 * @param object Referencia a szekvenciában szereplő objektumra.
	 * @param id Az objektumhoz tartozó olvasható név.
	 */
	public static void PushIdentifier(Object object, String id) {
		identifiers.put(object, id);
	}
	/**
	 * Visszaadja az adott objektumhoz tartozó olvasható nevet.
	 * @param object Referencia a szekvenciában szereplő objektumra.
	 * @return Az objektumhoz tartozó olvasható név.
	 */
	public static String GetIdentifier(Object object) {
		return identifiers.get(object);
	}
	
	
	/**
	 * Kiír egy függvényhívásnak megfelelő sort a kimeneti pufferbe.
	 * @param callee Referencia a meghívott objektumra.
	 * @param function Az elosztó karakter után kiírandó, a meghívott függvényt azonosító szöveg.
	 */
	public static void EnterFunction(Object callee, String function) {
		if (identifiers.isEmpty())
			return;
		
		String callerS = identifiers.get(callers.peek());
		String calleeS = identifiers.get(callee);
		
		for (int i = 0; i < callDepth; i++) {
			callHierarchyOutput.append("  ");
		}
		callHierarchyOutput.write(callerS + " >> " + calleeS + "\t| " + function + "\n");
		callDepth++;
	}
	/**
	 * Kiír egy függvényből való visszatérésnek megfelelő sort a kimeneti pufferbe.
	 * @param callee Referencia a meghívott objektumra.
	 */
	public static void ExitFunction(Object callee, String returnValue) {
		if (identifiers.isEmpty())
			return;

		String callerS = identifiers.get(callers.pop());
		String calleeS = identifiers.get(callee);
		
		callDepth--;
		if (callDepth < 0)
			callDepth = 0;
		
		for (int i = 0; i < callDepth; i++) {
			callHierarchyOutput.append("  ");
		}
		callHierarchyOutput.write(callerS + " << " + calleeS + "\t| " + returnValue + "\n");
	}
	
	/**
	 * Kiírja a pufferbe írt szöveget a std kimenetre.
	 */
	public static void PrintToStandardOutput() {
		System.out.println(callHierarchyOutput.toString());
	}
	/**
	 * Alaphelyzetbe állítja a kiíró rendszert.
	 */
	public static void Clear() {
		callHierarchyOutput = new StringWriter();
		callDepth = 0;
		
		callers.clear();
		identifiers.clear();
	}
}
