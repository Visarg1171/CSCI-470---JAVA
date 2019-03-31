import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;

public class AlbumHandler extends DefaultHandler{
   
    private boolean alName;		//album Name
    private boolean arName;		//artist name
    private boolean categ;		//category
    private boolean img;		//image

    private XMLDownloadTask xmlDownloader;
    private String xmlResponse;
    private String albumname;
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

  
    public void startElement(String uri, String localName, String nameInfo,
                             Attributes attributes) throws SAXException{
     

        if(nameInfo.equalsIgnoreCase("entry"))
            counter=1;


        if(nameInfo.equalsIgnoreCase("im:name")){
           alName = true;
           albumname = "";
        }

        if(nameInfo.equalsIgnoreCase("im:artist")){
            arName = true;
            artist = "";
        }

        if (nameInfo.equalsIgnoreCase("category")) {
            categ = true;

            genre = attributes.getValue("label");



        }else if(nameInfo.equalsIgnoreCase("im:image")){
            img = true;
            alIconUrl = "";
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (alName) {
            albumname = albumname + new String(ch, start, length);

        } else if (arName) {
            artist = artist + new String(ch, start, length);
        } else if (img) {
                alIconUrl = alIconUrl + new String(ch, start, length);

        }
    }


   public void endElement(String uri, String localName, String nameInfo){
        if(nameInfo.equalsIgnoreCase("im:name")){
            alName = false;
        }

        if(nameInfo.equalsIgnoreCase("im:artist")){
            arName = false;
        }

        if(nameInfo.equalsIgnoreCase("category")) {
            categ = false;
            
            if(finalGenre.equals(""))
                finalGenre = genre;


        }

        if(nameInfo.equalsIgnoreCase("im:image")) {
            img = false;
            try {
                URL url = new URL(alIconUrl);
                BufferedImage bufferedImage = ImageIO.read(url);
                ImageIO.write(bufferedImage, "png", new FileOutputStream("../out/"));
                alIcon = new ImageIcon(bufferedImage);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(nameInfo.equalsIgnoreCase("entry")){


            Album album = new Album(albumname, artist,finalGenre, alIcon);

            this.xmlDownloader.albumList.add(album);
            finalGenre="";
        }
    }

}