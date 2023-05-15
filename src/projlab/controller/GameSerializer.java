package projlab.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import projlab.sivatag.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class GameSerializer {
    private static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder dBuilder;
    static {
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
    private static Document doc = dBuilder.newDocument();

    /**
     * Az első
     * paraméterként megadott játék állapotát (az időzítő kivételével), beleértve a
     * tárolók elemeinek állapotát szerializálja az alábbi metódusok segítségével, és
     * egy TransformerFactory példány segítségével elmenti a második paraméterben
     * megadott elérési úton található XML állományba. Ha a fájl létezik, akkor
     * felülírja, ha nem létezik, akkor létrehozza az állományt. Ha a fájlba írás
     * sikeres, a visszatérési értéke igaz, ellenkező esetben hamis.
     * @param game
     * @param filePath
     * @return
     */
    public static boolean saveGame(Game game, String filePath){
        try{
            doc.appendChild(serializeGame(game));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Ha a megadott elérési út
     * létezik, az ott található XML állományban található XML fát betölti a doc
     * objektumba, az alábbi függvények segítségével deszerializálja a játék elemeit,
     * és visszatér az ezt tartalmazó Game példánnyal. Ha a fájlból olvasás nem
     * sikerül, vagy a fájl nem található, null értékkel tér vissza.
     * @param filePath
     * @return
     */
    public static Game loadGame(String filePath){
        try {
            File file = new File(filePath);
            Document document = dBuilder.parse(file);
            Element rootElement = document.getDocumentElement();
            return deserializeGame(rootElement);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Az alábbi függvények
     * segítségével szerializálja a megadott Game példányt, beleértve a tárolók
     * elemeit is, a timer kivételével. Az ezt reprezentáló XML elemmel tér vissza,
     * ami beilleszthető a doc objektumba. Ha a szerializálás nem sikerül, null
     * értékkel tér vissza
     * @param game
     * @return
     */
    private static Element serializeGame(Game game){
        if(game == null) return null;
        Element rootElement = doc.createElement("game");
        Element pipeElementsElement = serializePipeElements(game.getPipeElements());
        if(pipeElementsElement != null) rootElement.appendChild(pipeElementsElement);
        Element playersElement = serializePlayers(game.getPlayers());
        if(playersElement != null) rootElement.appendChild(playersElement);
        return rootElement;
    }

    /**
     * A megadott XML
     * elemet az alábbi függvények segítségével deszerializálva létrehoz egy Game
     * példányt. Ha a deszerializáció nem sikerül, null értékkel tér vissza.
     * @param element
     * @return
     */
    private static Game deserializeGame(Element element){
        Game game = new Game();
        Element pipeElementsElement = getChildElement(element, "pipeElements");
        if (pipeElementsElement != null) {
            HashMap<String, WaterFlow> pipeElements = deserializePipeElements(pipeElementsElement);
            if(!pipeElements.isEmpty()){
                AtomicInteger pipesCount = new AtomicInteger();
                pipeElements.forEach((key, waterFlow) -> {
                    try{
                        if(key.startsWith("pipe")){
                            game.addPipeElement((Pipe)waterFlow);
                            pipesCount.getAndIncrement();
                        }
                        else if(key.startsWith("pump")) game.addPipeElement((Pump)waterFlow);
                        else if(key.startsWith("source"))game.addPipeElement((Source)waterFlow);
                        else if(key.startsWith("cistern"))game.addPipeElement((Cistern)waterFlow);
                    } catch(InvalidKeyException e){
                        throw new RuntimeException(e.getMessage());
                    }
                });
                try{
                    if(pipesCount.get()>0){
                        game.addPlayer(new Plumber(game.getPipeElement("pipe1")));
                        game.addPlayer(new Plumber(game.getPipeElement("pipe2")));
                        game.addPlayer(new Saboteur(game.getPipeElement("pipe"+(pipesCount.get()-1))));
                        game.addPlayer(new Saboteur(game.getPipeElement("pipe"+(pipesCount.get()-2))));
                    }
                } catch (InvalidKeyException e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return game;
    }

    /**
     * Egy Game példány pipeElements tárolójának
     * elemeit szerializálja az alábbi függvények segítségével. Létrehoz egy, a fenti
     * példában látható “pipeElements” XML elemet, aminek attribútumai a
     * vízhálózat elemei. Visszatérési értéke a doc objektumba beilleszthető Element.
     * Ha a szerializálás nem sikerül, akkor a visszatérési értéke null.
     * @param pipeElements
     * @return
     */
    private static Element serializePipeElements(HashMap<String, WaterFlow> pipeElements){
        if(pipeElements.isEmpty()) return null;
        Element element = doc.createElement("pipeElements");
        pipeElements.forEach((key, waterFlow) -> {
            Element waterFlowElement = null;
            if(key.startsWith("pipe")) {
                waterFlowElement = serializeWaterFlow((Pipe)waterFlow);
            } else if(key.startsWith("pump")) waterFlowElement = serializeWaterFlow((Pump)waterFlow);
            else if(key.startsWith("source")) waterFlowElement = serializeWaterFlow((Source)waterFlow);
            else if(key.startsWith("cistern")) waterFlowElement = serializeWaterFlow((Cistern)waterFlow);
            if(waterFlowElement != null){
                waterFlowElement.setAttribute("output", Integer.toString(waterFlow.getOutput()));
                waterFlowElement.setAttribute("buffer", Integer.toString(waterFlow.getBuffer()));
                waterFlowElement.setAttribute("carried", Boolean.toString(waterFlow.isCarried()));
                if(!waterFlow.getInput().isEmpty()){
                    for(int input : waterFlow.getInput()){
                        Element inputElement = doc.createElement("input");
                        inputElement.setTextContent(Integer.toString(input));
                        waterFlowElement.appendChild(inputElement);
                    }
                }
                Element neighborsElement = serializeNeighbors(waterFlow.getNeighbors());
                if(neighborsElement != null) waterFlowElement.appendChild(neighborsElement);
                element.appendChild(waterFlowElement);
            }
        });
        return element;
    }

    /**
     * Egy
     * Game példány players tárolójának elemeit szerializálja az alábbi függvények
     * segítségével. Létrehoz egy, a fenti példában látható “players” XML elemet,
     * aminek attribútumai a pályán levő játékosok. Visszatérési értéke a doc
     * objektumba beilleszthető Element. Ha a szerializálás nem sikerül, akkor a
     * visszatérési értéke null.
     * @param players
     * @return
     */
    private static Element serializePlayers(HashMap<String, Player> players){
        if(players.isEmpty()) return null;
        Element element = doc.createElement("players");
        players.forEach((key, player) -> {
            Element playerElement = null;
            if(key.startsWith("plumber")) playerElement = serializePlayer((Plumber)player);
            else if(key.startsWith("saboteur")) playerElement = serializePlayer((Saboteur)player);
            if(playerElement != null){
                playerElement.setAttribute("paralyzed", Boolean.toString(player.isParalyzed()));
                element.appendChild(playerElement);
            }
        });
        return element;
    }

    /**
     * Egy WaterFlow példány szomszédjainak szerializálása
     * @param neighbors A szomszédokat reprezentáló lista
     * @return
     */
    private static Element serializeNeighbors(LinkedList<WaterFlow> neighbors){
        if(neighbors.isEmpty()) return null;
        Element element = doc.createElement("neighbors");
        for(WaterFlow waterFlow : neighbors){
            Element neighborElement = null;
            if(waterFlow instanceof Pipe) neighborElement = serializeWaterFlow((Pipe)waterFlow);
            else if(waterFlow instanceof Pump) neighborElement = serializeWaterFlow((Pump)waterFlow);
            else if(waterFlow instanceof Source) neighborElement = serializeWaterFlow((Source)waterFlow);
            else if(waterFlow instanceof Cistern) neighborElement = serializeWaterFlow((Cistern)waterFlow);
            if(neighborElement != null) element.appendChild(neighborElement);
        }
        return element;
    }

    /**
     * Egy cső szerializálásához szükséges metódus
     * @param pipe
     * @return a doc objektumba illeszthető Element
     */
    private static Element serializeWaterFlow(Pipe pipe){
        if(pipe == null) return null;
        Element element = doc.createElement("pipe");
        element.setAttribute("countDown", Integer.toString(pipe.getCountDown()));
        element.setAttribute("punctured", Boolean.toString(pipe.isPunctured()));
        element.setAttribute("sticky", Boolean.toString(pipe.isSticky()));
        element.setAttribute("slippery", Boolean.toString(pipe.isSlippery()));
        return element;
    }

    /**
     * Egy pumpa szerializálásához szükséges metódus
     * @param pump
     * @return a doc objektumba illeszthető Element
     */
    private static Element serializeWaterFlow(Pump pump){
        if(pump == null) return null;
        Element element = doc.createElement("pump");
        element.setAttribute("broken", Boolean.toString(pump.isBroken()));
        element.setAttribute("pickedUpOnce", Boolean.toString(pump.isPickedUpOnce()));
        return element;
    }
    /**
     * Egy forrás szerializálásához szükséges metódus
     * @param source
     * @return a doc objektumba illeszthető Element
     */
    private static Element serializeWaterFlow(Source source){
        if(source == null) return null;
        return doc.createElement("source");
    }
    /**
     * Egy ciszterna szerializálásához szükséges metódus
     * @param cistern
     * @return a doc objektumba illeszthető Element
     */
    private static Element serializeWaterFlow(Cistern cistern){
        if(cistern == null) return null;
        return doc.createElement("cistern");
    }
    /**
     * Egy szerelő szerializálásához szükséges metódus
     * @param player
     * @return a doc objektumba illeszthető Element
     */
    private static Element serializePlayer(Plumber player){
        if(player == null) return null;
        return doc.createElement("plumber");
    }
    /**
     * Egy szabotőr szerializálásához szükséges metódus
     * @param player
     * @return a doc objektumba illeszthető Element
     */
    private static Element serializePlayer(Saboteur player){
        if(player == null) return null;
        return doc.createElement("saboteur");
    }

    /**
     *  A megadott XML elem
     * attribútumait NodeList kollekcióba gyűjti, majd ezeket az alábbi függvények
     * segítségével deszerializálva létrehozza a vízhálózati elemek tárolóját. Ha a
     * deszerializáció nem sikerül, null értékkel tér vissza.
     * @param element
     * @return
     */
    private static HashMap<String, WaterFlow> deserializePipeElements(Element element){
        HashMap<String, WaterFlow> pipeElements = new HashMap<>();
        int pipesCount = 0;
        int pumpsCount = 0;
        int sourcesCount = 0;
        int cisternsCount = 0;

        NodeList pipeElementNodes = element.getChildNodes();
        for(int i=0; i<pipeElementNodes.getLength(); i++){
            Node node = pipeElementNodes.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){

                String key = null;
                WaterFlow waterFlow = null;

                Element childElement = (Element)node;
                String tagName = childElement.getTagName();
                switch (tagName) {
                    case "pipe" -> {
                        waterFlow = deserializePipe(childElement);
                        key = "pipe" + pipesCount;
                        pipesCount++;
                    }
                    case "pump" -> {
                        waterFlow = deserializePump(childElement);
                        key = "pump" + pumpsCount;
                        pumpsCount++;
                    }
                    case "source" -> {
                        waterFlow = deserializeSource(childElement);
                        key = "source" + sourcesCount;
                        sourcesCount++;
                    }
                    case "cistern" -> {
                        waterFlow = deserializeCistern(childElement);
                        key = "cistern" + cisternsCount;
                        cisternsCount++;
                    }
                }
                if(waterFlow != null){
                    waterFlow.setOutput(Integer.parseInt(childElement.getAttribute("output")));
                    int[] input = new int[1];
                    LinkedList<WaterFlow> neighbors=null;

                    NodeList childNodes = childElement.getChildNodes();
                    for(int j=0; j<childNodes.getLength(); j++){
                        Node childNode = childNodes.item(j);
                        if(node.getNodeType()==Node.ELEMENT_NODE){
                            Element neighborsElement = (Element)childNode;
                            String childTagName = neighborsElement.getTagName();
                            if(childTagName.equals("neighbors")) neighbors = deserializeNeighbors(neighborsElement);
                            else if(childTagName.equals("input")) input[0] = Integer.parseInt(neighborsElement.getTextContent());
                        }
                    }
                    waterFlow.setInput(input);
                    if(neighbors != null){
                        for(WaterFlow neighbor : neighbors){
                            waterFlow.addNeighbor(neighbor);
                            neighbor.addNeighbor(waterFlow);
                        }
                    }
                }
            }
        }
        return pipeElements;
    }
    /**
     * Egy WaterFlow példány szomszédjainak deszerializálása
     * @param element A szomszédokat reprezentáló node
     * @return
     */
    private static LinkedList<WaterFlow> deserializeNeighbors(Element element){
        LinkedList<WaterFlow> neighbors = new LinkedList<>();
        NodeList neighborNodes = element.getChildNodes();
        for(int i=0; i<neighborNodes.getLength(); i++){
            Node node = neighborNodes.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                WaterFlow waterFlow = null;
                Element childElement = (Element)node;
                String tagName = childElement.getTagName();
                if(tagName.equals("pipe")) waterFlow = deserializePipe(childElement);
                else if(tagName.equals("pump")) waterFlow = deserializePump(childElement);
                else if(tagName.equals("source")) waterFlow = deserializeSource(childElement);
                else if(tagName.equals("cistern")) waterFlow = deserializeCistern(childElement);
                if(waterFlow != null){
                    neighbors.add(waterFlow);
                }
            }
        }
        return neighbors;
    }
    /**
     * Egy cső szerializálásához szükséges metódus
     * @param element A csövet reprezentáló node
     * @return a deszerializált WaterFlow példány
     */
    private static Pipe deserializePipe(Element element){
        if(element == null) return null;
        return new Pipe();
    }
    /**
     * Egy pumpa szerializálásához szükséges metódus
     * @param element A pumpát reprezentáló node
     * @return a deszerializált WaterFlow példány
     */
    private static Pump deserializePump(Element element){
        if(element == null) return null;
        return new Pump();
    }
    /**
     * Egy forrás szerializálásához szükséges metódus
     * @param element A forrást reprezentáló node
     * @return a deszerializált WaterFlow példány
     */
    private static Source deserializeSource(Element element){
        if(element == null) return null;
        return new Source();
    }

    /**
     * Egy forrás szerializálásához szükséges metódus
     * @param element A forrást reprezentáló node
     * @return a deszerializált WaterFlow példány
     */
    private static Cistern deserializeCistern(Element element){
        if(element == null) return null;
        return new Cistern();
    }
    private static Element getChildElement(Element element, String childTagName) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals(childTagName)) {
                return (Element) childNode;
            }
        }
        return null;
    }
}
