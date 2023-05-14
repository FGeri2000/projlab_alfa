package projlab.sivatag;

import java.security.InvalidKeyException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A játék jelenlegi állapotát tároló struktúra.
 * @author fgreg
 *
 */
public class Game {
	/**
	 * A játék órajelét biztosító Timer objektum.
	 */
	private Timer timer;
	/**
	 * A játékból hátralévő idő.
	 */
	private int gameTimeLeft;
	/**
	 * A csőhálózat elemeit tároló heterogén kollekció.
	 */
	private HashMap<String, WaterFlow> pipeElements;
	/**
	 * A játékosokat tároló heterogén kollekció.
	 */
	private ArrayList<Player> players;
	public Game(){
		players = new ArrayList<>();
		pipeElements = new HashMap<>();
		gameTimeLeft = 240;
		timer = new Timer();
	}
	/**
	 * Ez a metódussal létrehozza az alap map-et, inicializálja a map tárolót és feltölti a szükséges objektumokkal, hogy a játék elkezdődhessen.
	 */
	public void newGame() {
		try {
			if(pipeElements.isEmpty()){
				createDefaultMap();
			}
			TimerTask task = new TimerTask() {
				public void run() {
					pipeElements.forEach((key, waterFlow) -> {
						waterFlow.flowTick();
					});
				}
			};
			timer.scheduleAtFixedRate(task, 0, 1000);

		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * Leállítja a játékot, lezárja a játékos karakterek irányítását, és értesítést küld a játék eredményéről.
	 */
	public int endGame() {
		timer.cancel();
		timer.purge();
		timer = new Timer();
		AtomicInteger cisternsBuffer = new AtomicInteger();
		pipeElements.forEach((key, waterFlow) -> {
			if (key.startsWith("cistern")) {
				cisternsBuffer.addAndGet(waterFlow.buffer);
			}
			waterFlow.removeNeighbors();
			for(Player player : waterFlow.players){
				this.players.remove(player);
			}
			waterFlow.players.clear();
		});
		pipeElements.clear();
		players.clear();
		return cisternsBuffer.get();
	}
	public WaterFlow getPipeElement(String key){
		if(key.isBlank())
			return null;
		String _key = key.trim().toLowerCase();
		WaterFlow pipeElement = pipeElements.get(_key);
		return pipeElement;
	}
	public void addPipeElement(Pipe element) throws InvalidKeyException {
		if(element == null)
			throw new NullPointerException();
		String key = getNextKey("pipe");
		WaterFlow previous = pipeElements.put(key, element);
		if(previous != null)
			throw new InvalidKeyException();
	}
	public void addPipeElement(Pump element) throws InvalidKeyException {
		if(element == null)
			throw new NullPointerException();
		String key = getNextKey("pump");
		WaterFlow previous = pipeElements.put(key, element);
		if(previous != null)
			throw new InvalidKeyException();
	}
	public void addPipeElement(Source element) throws InvalidKeyException {
		if(element == null)
			throw new NullPointerException();
		String key = getNextKey("source");
		WaterFlow previous = pipeElements.put(key, element);
		if(previous != null)
			throw new InvalidKeyException();
	}
	public void addPipeElement(Cistern element) throws InvalidKeyException {
		if(element == null)
			throw new NullPointerException();
		String key = getNextKey("cistern");
		WaterFlow previous = pipeElements.put(key, element);
		if(previous != null)
			throw new InvalidKeyException();
	}
	public boolean setPipeElement(String key, WaterFlow value){
		if(key.isBlank() || value == null)
			return false;
		String _key = key.trim().toLowerCase();
		return pipeElements.replace(_key, value) != null;
	}
	public boolean removePipeElement(String key){
		if(key.isBlank())
			return false;
		String _key = key.trim().toLowerCase();
		return pipeElements.remove(_key) != null;
	}
	public Player getPlayer(int idx){
		try{
			return players.get(idx);
		} catch (IndexOutOfBoundsException e){
			return null;
		}
	}
	public void addPlayer(Plumber player){
		if(player!=null)
			players.add(player);
		else
			throw new NullPointerException();
	}
	public void addPlayer(Saboteur player){
		if(player!=null)
			players.add(player);
		else
			throw new NullPointerException();
	}
	public boolean setPlayer(int idx, Player value){
		try{
			if(value==null)
				return false;
			players.set(idx, value);
			return true;
		} catch (IndexOutOfBoundsException e){
			return false;
		}
	}
	public boolean removePlayer(int idx){
		try{
			players.remove(idx);
			return true;
		} catch (IndexOutOfBoundsException e){
			return false;
		}
	}
	public int getGameTimeLeft(){return gameTimeLeft;}
	public void setGameTimeLeft(int gameTimeLeft) {
		this.gameTimeLeft = gameTimeLeft;
	}
	public HashMap<String, WaterFlow> getPipeElements(){return pipeElements;}
	public ArrayList<Player> getPlayers(){return players;}
	public String getKeyFromPipeElements(WaterFlow element){
		if(element == null)
			return null;
		AtomicReference<String> _key = new AtomicReference<>("");
		pipeElements.forEach((key, waterFlow) -> {
			if(waterFlow.equals(element)){
				_key.set(key);
			}
		});
		String keyOfPipeElement = _key.get();
		if(keyOfPipeElement.isBlank())
			return null;
		return keyOfPipeElement;
	}
	private void createDefaultMap() throws InvalidKeyException {
		try{
			Source source0 = new Source();
			Source source1 = new Source();
			Pipe pipe0 = new Pipe();
			source0.addNeighbor(pipe0);
			pipe0.addNeighbor(source0);
			source1.addNeighbor(pipe0);
			pipe0.addNeighbor(source1);

			Pipe pipe1 = new Pipe();
			Pipe pipe2 = new Pipe();
			source0.addNeighbor(pipe1);
			pipe1.addNeighbor(source0);
			source1.addNeighbor(pipe2);
			pipe2.addNeighbor(source1);
			source0.setOutput(1);
			source1.setOutput(1);
			pipe1.setInput(new int[] {0});
			pipe2.setInput(new int[] {0});

			Pump pump0 = new Pump();
			Pump pump1 = new Pump();
			Pipe pipe3 = new Pipe();
			pump0.addNeighbor(pipe1);
			pipe1.addNeighbor(pump0);
			pump0.addNeighbor(pipe3);
			pump1.addNeighbor(pipe3);
			pipe3.addNeighbor(pump1);
			pipe3.addNeighbor(pump0);
			pump1.addNeighbor(pipe2);
			pipe2.addNeighbor(pump1);
			pipe1.setOutput(1);
			pump0.setInput(new int[] {0});
			pipe2.setOutput(1);
			pump1.setInput(new int[] {0});

			Pump pump2 = new Pump();
			Pipe pipe4 = new Pipe();
			Pipe pipe8 = new Pipe();
			pump0.addNeighbor(pipe4);
			pipe4.addNeighbor(pump0);
			pump2.addNeighbor(pipe4);
			pipe4.addNeighbor(pump2);
			pump2.addNeighbor(pipe8);
			pipe8.addNeighbor(pump2);
			pump0.setOutput(2);
			pipe4.setInput(new int[] {0});
			pipe4.setOutput(1);
			pump2.setInput(new int[] {0});
			pump2.setOutput(1);
			pipe8.setInput(new int[] {0});

			Pump pump3 = new Pump();
			Pipe pipe5 = new Pipe();
			Pipe pipe6 = new Pipe();
			Pipe pipe9 = new Pipe();
			Pipe pipe10 = new Pipe();
			pump3.addNeighbor(pipe5);
			pipe5.addNeighbor(pump3);
			pump0.addNeighbor(pipe5);
			pipe5.addNeighbor(pump0);
			pump3.addNeighbor(pipe6);
			pipe6.addNeighbor(pump3);
			pump1.addNeighbor(pipe6);
			pipe6.addNeighbor(pump1);
			pump3.addNeighbor(pipe9);
			pipe9.addNeighbor(pump3);
			pump3.addNeighbor(pipe10);
			pipe10.addNeighbor(pump3);

			Pump pump4 = new Pump();
			Pipe pipe7 = new Pipe();
			Pipe pipe11 = new Pipe();
			pump1.addNeighbor(pipe7);
			pipe7.addNeighbor(pump1);
			pump4.addNeighbor(pipe7);
			pipe7.addNeighbor(pump4);
			pump4.addNeighbor(pipe11);
			pipe11.addNeighbor(pump4);
			pump1.setOutput(3);
			pipe7.setInput(new int[] {0});
			pipe7.setOutput(1);
			pump4.setInput(new int[] {0});
			pump4.setOutput(1);
			pipe11.setInput(new int[] {0});

			Pump pump5 = new Pump();
			Pipe pipe12 = new Pipe();
			Pipe pipe13 = new Pipe();
			pump5.addNeighbor(pipe8);
			pipe8.addNeighbor(pump5);
			pump5.addNeighbor(pipe9);
			pipe9.addNeighbor(pump5);
			pump5.addNeighbor(pipe12);
			pipe12.addNeighbor(pump5);
			pump5.addNeighbor(pipe13);
			pipe12.addNeighbor(pump5);
			pipe8.setOutput(1);
			pump5.setInput(new int[] {0});
			pump5.setOutput(3);
			pipe13.setInput(new int[] {0});

			Pump pump6 = new Pump();
			Pipe pipe14 = new Pipe();
			pump6.addNeighbor(pipe10);
			pipe10.addNeighbor(pump6);
			pump6.addNeighbor(pipe11);
			pipe11.addNeighbor(pump6);
			pump6.addNeighbor(pipe12);
			pipe12.addNeighbor(pump6);
			pump6.addNeighbor(pipe14);
			pipe14.addNeighbor(pump6);
			pipe11.setOutput(1);
			pump6.setInput(new int[] {1});
			pump6.setOutput(3);
			pipe14.setInput(new int[] {0});

			Cistern cistern0 = new Cistern();
			Pipe pipe15 = new Pipe();
			cistern0.addNeighbor(pipe13);
			pipe13.addNeighbor(cistern0);
			cistern0.addNeighbor(pipe15);
			pipe15.addNeighbor(cistern0);
			pipe13.setOutput(1);
			cistern0.setInput(new int[] {0});

			Cistern cistern1 = new Cistern();
			cistern1.addNeighbor(pipe14);
			pipe14.addNeighbor(cistern1);
			cistern1.addNeighbor(pipe15);
			pipe15.addNeighbor(cistern1);
			pipe14.setOutput(1);
			cistern1.setInput(new int[] {0});

			Plumber plumber0 = new Plumber(pipe0);
			pipe0.putPlayer(plumber0);
			Plumber plumber1 = new Plumber(pipe0);
			pipe0.putPlayer(plumber1);

			Saboteur saboteur0 = new Saboteur(pipe15);
			pipe15.putPlayer(saboteur0);
			Saboteur saboteur1 = new Saboteur(pipe15);
			pipe15.putPlayer(saboteur1);

			addPipeElement(source0);
			addPipeElement(source1);
			addPipeElement(pump0);
			addPipeElement(pump1);
			addPipeElement(pump2);
			addPipeElement(pump3);
			addPipeElement(pump4);
			addPipeElement(pump5);
			addPipeElement(pump6);
			addPipeElement(cistern0);
			addPipeElement(cistern1);
			addPipeElement(pipe0);
			addPipeElement(pipe1);
			addPipeElement(pipe2);
			addPipeElement(pipe3);
			addPipeElement(pipe4);
			addPipeElement(pipe5);
			addPipeElement(pipe6);
			addPipeElement(pipe7);
			addPipeElement(pipe8);
			addPipeElement(pipe9);
			addPipeElement(pipe10);
			addPipeElement(pipe11);
			addPipeElement(pipe12);
			addPipeElement(pipe13);
			addPipeElement(pipe14);
			addPipeElement(pipe15);

			addPlayer(plumber0);
			addPlayer(plumber1);
			addPlayer(saboteur0);
			addPlayer(saboteur1);
		} catch (InvalidKeyException e){
			pipeElements.clear();
			players.clear();
			throw new InvalidKeyException(e.getMessage());
		}
	}
	private String getNextKey(String prefix){
		if(prefix == null)
			throw new NullPointerException();
		int suffix = pipeElements.keySet().stream()
				.filter(key -> key.startsWith(prefix))
				.map(key -> key.substring(prefix.length()))
				.mapToInt(number -> {
					try {
						return Integer.parseInt(number);
					} catch (NumberFormatException e) {
						return 0;
					}
				})
				.max()
				.orElse(0) + 1;
		return prefix+suffix;
	}
}
