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

public class XMLDownloadTask{
    
   // This is a subclass of SwingWorker that will be used to download the XML data in a background thread.
    
    String Url;     // A String to hold the URL string passed to the constructor.
    String strXml; //A delegate variable to hold the object reference passed to the constructor.
    ArrayList<Album> albumList; //An empty ArrayList of Album objects (optional)

    // constructor
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
    //string constructor
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
            //create a Url object from a String that contains a valid URL
            URL Url = new URL(getUrl());
            
            //openConnection for Url
            connection = (HttpURLConnection) Url.openConnection();

            //set request method
            connection.setRequestMethod("GET");

            // check connection sucessful
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                //use a String to store the downloaded text
                StringBuilder xmlResponse = new StringBuilder();

                //create a buffered reader to read the lines of xml from the connection's input stream
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

                // read lines of xml and append
                String strLine;
                while((strLine = input.readLine()) != null){
                    xmlResponse.append(strLine);
                }

                //convert the stringbuilder object to a string
                strXml = xmlResponse.toString();
                AlbumHandler albumHandler = new AlbumHandler(strXml, this);
                
                saxParser(strXml);
                
                // close input
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

    //xml has xml downloaded from itunes.
    public void saxParser(String xml){
        try{
            // an instance of the class
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser betterThanSax = factory.newSAXParser();

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
