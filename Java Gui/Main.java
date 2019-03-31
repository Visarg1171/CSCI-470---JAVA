/********************************************
 * 1. Krupa Patel
 * 2. Visarg Patel
 * 3. Purvin Patel
 * Assignment 3 
 * CSCI 470
 * Kurt Mcmahon
 */

import java.awt.*;
        import javax.swing.*;
        import javax.swing.border.Border;
        import javax.swing.border.EtchedBorder;
        import javax.swing.border.TitledBorder;
        import java.awt.event.ActionEvent;
        import java.awt.event.KeyEvent;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.text.DateFormatSymbols;
        import java.util.Scanner;
        import java.util.ArrayList;

public abstract class Main {

    public static void main(String[] args){
        EventQueue.invokeLater(() ->{
            AlbumGui gui = new AlbumGui();
            gui.createAndShowGUI();
        });

    }

}
