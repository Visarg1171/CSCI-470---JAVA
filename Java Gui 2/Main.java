/***************************************************
 * Purvin Patel, Visarg Patel, Krupa Patel 
 * CSCI 470
 * Assignment 4
 **************************************************/

import javax.swing.*;
import java.awt.*;

public abstract class Main {

    public static void main(String[] args){
            EventQueue.invokeLater(() -> {
                AlbumGui frame = new AlbumGui();
                frame.createAndShowGUI();
            });
        }

    }


