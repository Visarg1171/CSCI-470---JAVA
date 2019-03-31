import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class AlbumGui extends JFrame implements ActionListener{
    
	private static final long serialVersionUID = 1L;
	float[] columnWidthPercentage = {20.0f, 55.0f, 10.0f, 5.0f, 5.0f, 5.0f};
    private String[] columnNames = {"Name", "Artist", "Genre", "Album Cover"};
    private JTable table;

    private JButton getAlbumsBtn;
    private XMLDownloadTask XMLstuff;
    private JMenuBar menuBar;
    private JMenu typeMenu;

    private JRadioButtonMenuItem new_music_MenuItem;
    private JRadioButtonMenuItem recent_releases_MenuItem;
    private JRadioButtonMenuItem top_albums_MenuItem;

    private JRadioButtonMenuItem menuItem10;
    private JRadioButtonMenuItem menuItem25;
    private JRadioButtonMenuItem menuItem50;
    private JRadioButtonMenuItem menuItem100;

    private JCheckBoxMenuItem yes_menuItem;
    private JCheckBoxMenuItem no_menuItem;

    private JPanel resultsPane;


    public AlbumGui(){
        super("iTunes Store Album");
    }



    public void createAndShowGUI(){

        setLayout(new BorderLayout());
        setBounds(100, 100, 1020, 550);
        setResizable(false);
        createMenu();
        addListeners();

        XMLstuff = new XMLDownloadTask();

        setJMenuBar(menuBar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    private void createMenu(){

        menuBar = new JMenuBar();
        typeMenu = new JMenu("Type");
        add(menuBar);
        add(typeMenu);

        new_music_MenuItem = new JRadioButtonMenuItem("New Music");
        new_music_MenuItem.setSelected(true);
        recent_releases_MenuItem = new JRadioButtonMenuItem("Recent Releases");
        top_albums_MenuItem = new JRadioButtonMenuItem("Top Albums");
        ButtonGroup typeButtonGroup = new ButtonGroup();
        typeButtonGroup.add(new_music_MenuItem);
        typeButtonGroup.add(recent_releases_MenuItem);
        typeButtonGroup.add(top_albums_MenuItem);
        JMenu limitMenu = new JMenu("Limit");

        menuItem10 = new JRadioButtonMenuItem("10");
        menuItem10.setSelected(true);
        menuItem25 = new JRadioButtonMenuItem("25");
        menuItem50 = new JRadioButtonMenuItem("50");
        menuItem100 = new JRadioButtonMenuItem("100");
        ButtonGroup itemsButtonGroup = new ButtonGroup();
        itemsButtonGroup.add(menuItem10);
        itemsButtonGroup.add(menuItem25);
        itemsButtonGroup.add(menuItem50);
        itemsButtonGroup.add(menuItem100);

        JMenu explicitMenu = new JMenu("Explicit");

        yes_menuItem = new JCheckBoxMenuItem("Yes");
        yes_menuItem.setSelected(true);

        no_menuItem = new JCheckBoxMenuItem("No");
        ButtonGroup explicitGroup = new ButtonGroup();
        explicitGroup.add(yes_menuItem);
        explicitGroup.add(no_menuItem);


        getAlbumsBtn = new JButton("Get Albums");
        getAlbumsBtn.setPreferredSize(new Dimension(100,30));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        resultsPane = new JPanel(new FlowLayout());
        resultsPane.setPreferredSize(new Dimension(1020, 500));
        

        menuBar.add(typeMenu);
        typeMenu.add(new_music_MenuItem);
        typeMenu.add(recent_releases_MenuItem);
        typeMenu.add(top_albums_MenuItem);
        menuBar.add(limitMenu);
        limitMenu.add(menuItem10);
        limitMenu.add(menuItem25);
        limitMenu.add(menuItem50);
        limitMenu.add(menuItem100);
        menuBar.add(explicitMenu);
        explicitMenu.add(yes_menuItem);
        explicitMenu.add(no_menuItem);

        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(resultsPane, BorderLayout.CENTER);
        buttonPanel.add(getAlbumsBtn);


    }

    private void addListeners(){
        getAlbumsBtn.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Get Albums")) {
            resultsPane.removeAll();
            XMLstuff.clearAlbumList();
            DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
            tableModel.setRowCount(0);

            String typeSelection;
            String itemsNum;
            String explicitYN;

            if (top_albums_MenuItem.isSelected()) {
                typeSelection = "top-albums";
            } else if (recent_releases_MenuItem.isSelected()) {
                typeSelection = "recent-releases";
            } else {
                typeSelection = "new-music";
            }
            System.out.println(typeSelection);


            if (menuItem100.isSelected()) {
                itemsNum = "100";
            } else if (menuItem25.isSelected()) {
                itemsNum = "25";
            } else if (menuItem50.isSelected()) {
                itemsNum = "50";
            } else {
                itemsNum = "10";
            }
            System.out.println(itemsNum);


            if (no_menuItem.isSelected()) {
                explicitYN = "nonexplicit";
            } else {
                explicitYN = "explicit";
            }
            System.out.println(explicitYN);

            XMLstuff.setURL("https://rss.itunes.apple.com/api/v1/us/itunes-music/" + typeSelection + "/all/" + itemsNum + "/" + explicitYN + ".atom");
            System.out.println(this.XMLstuff.getUrl());



            this.XMLstuff.getAlbumList();
 

              for (Album a: this.XMLstuff.getAlbumList()) {
                tableModel.addRow(new Object[]{
                        a.getName(),
                        a.getArtistName(),
                        a.getGenre(),
                        "fehwjkfwehcke"
                });
            }
            table = new JTable(tableModel);
            Dimension tableSize =  resultsPane.getPreferredSize();
            table.getColumnModel().getColumn(0).setPreferredWidth(Math.round(tableSize.width*0.50f));
            table.getColumnModel().getColumn(1).setPreferredWidth(Math.round(tableSize.width*0.30f));
            table.getColumnModel().getColumn(2).setPreferredWidth(Math.round(tableSize.width*0.10f));
            table.getColumnModel().getColumn(3).setPreferredWidth(Math.round(tableSize.width*0.10f));

            JScrollPane resultsPaneScroll = new JScrollPane(table);
            resultsPaneScroll.setPreferredSize(new Dimension(1020, 500));
            resultsPane.add(resultsPaneScroll);
        
            resultsPane.updateUI();
            System.out.println("should print now");

        }
    }


    
	private static void main(final String[] args){
        EventQueue.invokeLater(() -> {
            AlbumGui frame = new AlbumGui();
            frame.createAndShowGUI();
        });
    }
}