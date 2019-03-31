import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.net.URL;

public class AlbumHandler extends DefaultHandler{

    private boolean alName;
    private boolean arName;
    private boolean categ;
    private boolean img;
    private XMLDownloadTask xmlDownloader;
    private String xmlResponse;
    private String albumName;
    private String artist;
    private String genre;
    private String finalGenre = "";
    private String alIconUrl;
    private ImageIcon alIcon;
    int counter=0;

    public AlbumHandler(){

    }

    public AlbumHandler(XMLDownloadTask passXml){
        xmlDownloader = passXml;
    }

    public AlbumHandler(String strXml, XMLDownloadTask task){
        xmlDownloader = task;
        xmlResponse = strXml;
    }


    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException{
        if(qName.equalsIgnoreCase("entry"))
            counter=1;

        if(qName.equalsIgnoreCase("im:name")){
            alName = true;
            albumName = "";
        }

        if(qName.equalsIgnoreCase("im:artist")){
            arName = true;
            artist = "";
        }

        if (qName.equalsIgnoreCase("category")) {
            categ = true;


            genre = attributes.getValue("label");		// Create a genre in the output 



        }else if(qName.equalsIgnoreCase("im:image")){
            img = true;
            alIconUrl = "";
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (alName) {
            albumName = albumName + new String(ch, start, length);

        } else if (arName) {
            artist = artist + new String(ch, start, length);
        } else if (img) {
            alIconUrl = alIconUrl + new String(ch, start, length);

        }
    }

    public void endElement(String uri, String localName, String qName){
        if(qName.equalsIgnoreCase("im:name")){
            alName = false;
        }

        if(qName.equalsIgnoreCase("im:artist")){
            arName = false;
        }

        if(qName.equalsIgnoreCase("category")) {
            categ = false;
            if(finalGenre.equals(""))
                finalGenre = genre;
        }

        if(qName.equalsIgnoreCase("im:image")) {
            img = false;
            try {
                
                URL url = new URL(alIconUrl);			// It creates a url 
                BufferedImage bufferedImage = ImageIO.read(url);
                ImageIO.write(bufferedImage, "png", new FileOutputStream("../out/"));
                alIcon = new ImageIcon(bufferedImage);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(qName.equalsIgnoreCase("entry")){
            Album album = new Album(albumName, artist,finalGenre, alIcon);
            this.xmlDownloader.albumList.add(album);
            finalGenre="";
        }
    }
}
