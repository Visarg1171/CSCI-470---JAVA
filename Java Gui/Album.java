import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Album {
    // Itunes library variables
    private String name; //album name
    private String artistName;
    private String genre;
    private ImageIcon imgIcon;


    //default constructor.
    public Album(){
        name = "";
        artistName = "";
        genre = "";
        imgIcon = null;
    }

    public Album(String al, String ar, String ge, ImageIcon ic){
        setName(al);
        setArtistName(ar);
        setGenre(ge);
        setIcon(ic);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Image getIcon() {
        return getScaledImage(imgIcon.getImage());
    }

    private Image getScaledImage(Image sourceImage) {

        BufferedImage resizedImage = new BufferedImage(50, 50,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(sourceImage, 0, 0, 50, 50, null); g2.dispose();
        return resizedImage;
    }

    public void setIcon(ImageIcon imgIcon) {
        this.imgIcon = imgIcon;
    }

    public String toString(){
        return "Album: " + getName() + "; Artists name: " + getArtistName() + "; genre: " + getGenre() + "\n";
    }
}
