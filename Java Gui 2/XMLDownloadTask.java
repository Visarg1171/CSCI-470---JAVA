import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class XMLDownloadTask {
    String Url;
    String strXml; 
    ArrayList<Album> albumList; 

    public XMLDownloadTask(){
        strXml = "";
        albumList = new ArrayList<>();
    }

    public String getUrl(){
        return strXml;
    }

    public void setUrl(String s){
        strXml = s;
    }
    
    public XMLDownloadTask(String Url){
        strXml = Url;
        albumList = new ArrayList<>();
        this.doInBackground();
    }
    public void clearAlbumList() {
        albumList.clear();
    }

    public String setURL(String xmlURL) {
        strXml = xmlURL;
        this.doInBackground();
        return strXml;
    }

    public void doInBackground(){


        HttpURLConnection connection = null;
        try{
            
            URL Url = new URL(getUrl());
            connection = (HttpURLConnection) Url.openConnection();

            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
            	
                StringBuilder xmlResponse = new StringBuilder();

                BufferedReader input = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

                String strLine;
                while((strLine = input.readLine()) != null){
                    xmlResponse.append(strLine);
                }


                strXml = xmlResponse.toString();
                AlbumHandler albumHandler = new AlbumHandler(strXml, this);
     
                saxParser(strXml);

                input.close();
            }


        }catch (MalformedURLException e) {
   
        } catch (IOException e) {
 
        } catch (Exception e) {

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public void saxParser(String xml){
        try{
           
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser betterThanSax = factory.newSAXParser(); //( ͡° ͜ʖ ͡°)

            betterThanSax.parse(new InputSource(new ByteArrayInputStream(
                    strXml.getBytes("utf-8"))), new AlbumHandler(this));

        } catch (ParserConfigurationException e){
            System.out.println("sax config error");
        } catch (SAXException e){
            System.out.println("sax parser error");
        } catch (UnsupportedEncodingException e){
            System.out.println("unsupported enconding error");
        } catch (IOException e){
            System.out.println("IO error");
        }
    }

    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    public void printAlbumList(){
        for (Album a : albumList) {
            System.out.println("Album: " + a.getName());
            System.out.println("Artist: " + a.getArtistName());
            System.out.println("Genre: " + a.getGenre() + "\n");
        }
    }

    public static void main(String[] args){
        String testString = "https://rss.itunes.apple.com/api/v1/us/itunes-music/new-music/all/10/explicit.atom\n";
        XMLDownloadTask xml = new XMLDownloadTask(testString);

        xml.printAlbumList();

    }
}
