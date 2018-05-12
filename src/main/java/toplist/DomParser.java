package toplist;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DomParser {

    private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder db;
    private static Element root;
    private static Document doc;
    private static File input;

    public static void writeToTopMineSweeper(Player p){
        try{

            db = dbf.newDocumentBuilder();

            input = new File("topmine.xml");
            if (input.exists()){
                doc = db.parse(input);
            } else{
                doc = db.newDocument();
            }

            if (doc.getDocumentElement() == null){
                root = doc.createElement("players");
                doc.appendChild(root);
            } else {
                root = doc.getDocumentElement();
            }

            Element player = doc.createElement("player");

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(p.getUsername()));
            player.appendChild(name);

            Element score = doc.createElement("score");
            score.appendChild(doc.createTextNode(Integer.toString(p.getPoints())));
            player.appendChild(score);

            root.appendChild(player);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(input);

            t.transform(source,result);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeToTopSwapperino(Player p){
        try{

            db = dbf.newDocumentBuilder();

            input = new File("topswap.xml");
            if (input.exists()){
                doc = db.parse(input);
            } else{
                doc = db.newDocument();
            }

            if (doc.getDocumentElement() == null){
                root = doc.createElement("players");
                doc.appendChild(root);
            } else {
                root = doc.getDocumentElement();
            }

            Element player = doc.createElement("player");

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(p.getUsername()));
            player.appendChild(name);

            Element score = doc.createElement("score");
            score.appendChild(doc.createTextNode(Integer.toString(p.getPoints())));
            player.appendChild(score);

            root.appendChild(player);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(input);

            t.transform(source,result);

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static List<Player> readFromTopMindeSweeper(){
        List<Player> players = new ArrayList<>();
        try{
            db = dbf.newDocumentBuilder();
            input = new File("topmine.xml");
            doc = db.parse(input);

            Element e;
            NodeList nl = doc.getElementsByTagName("player");

            for (int i = 0; i < nl.getLength(); i++){
                e = (Element) nl.item(i);
                players.add(new Player(e.getElementsByTagName("name").item(0).getTextContent(),Integer.parseInt(e.getElementsByTagName("score").item(0).getTextContent())));
            }
        } catch (Exception e) {

        }
        return players.stream().sorted((x,y) -> y.getPoints()-x.getPoints()).limit(10).collect(Collectors.toList());
    }

    public static List<Player> readFromTopSwapperino(){
        List<Player> players = new ArrayList<>();
        try{
            db = dbf.newDocumentBuilder();
            input = new File("topswap.xml");
            doc = db.parse(input);

            Element e;
            NodeList nl = doc.getElementsByTagName("player");

            for (int i = 0; i < nl.getLength(); i++){
                e = (Element) nl.item(i);
                players.add(new Player(e.getElementsByTagName("name").item(0).getTextContent(),Integer.parseInt(e.getElementsByTagName("score").item(0).getTextContent())));
            }
        } catch (Exception e) {

        }
        return players.stream().sorted((x,y) -> y.getPoints()-x.getPoints()).limit(10).collect(Collectors.toList());
    }

}
