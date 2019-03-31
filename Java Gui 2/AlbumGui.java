import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

public class AlbumGui extends JFrame implements ActionListener {
    private String[] columnNames = {"Name", "Artist", "Genre", "Album Cover"};
    private JTable table;

   
    private JButton getAlbumsButton;
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
    private JPanel SidePanel = new BouncingBallPanel();
    private BouncingBallPanel bounceBallPanel = new BouncingBallPanel();


    public AlbumGui(){
        super("iTunes Store Album");
    }

    public void createAndShowGUI(){

        setLayout(new BorderLayout());
        setBounds(100, 100, 1120, 550);
        setResizable(false);
        createMenu();// create the menu bar, menus, and menu items, and adds listeners for the menu items
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
        typeMenu.setToolTipText("choose between New Music, Recent Releases, or Top Albums");
        add(menuBar);
        add(typeMenu);

        //choose type of output
        new_music_MenuItem = new JRadioButtonMenuItem("New Music");
        new_music_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        new_music_MenuItem.setSelected(true);
        recent_releases_MenuItem = new JRadioButtonMenuItem("Recent Releases");
        recent_releases_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        top_albums_MenuItem = new JRadioButtonMenuItem("Top Albums");
        top_albums_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        ButtonGroup typeButtonGroup = new ButtonGroup();
        typeButtonGroup.add(new_music_MenuItem);
        typeButtonGroup.add(recent_releases_MenuItem);
        typeButtonGroup.add(top_albums_MenuItem);
        JMenu limitMenu = new JMenu("Limit");
        limitMenu.setToolTipText("choose how many results to show per page");

        //choose how many rows displayed in output
        menuItem10 = new JRadioButtonMenuItem("10");
        menuItem10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItem10.setSelected(true);
        menuItem25 = new JRadioButtonMenuItem("25");
        menuItem25.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItem50 = new JRadioButtonMenuItem("50");
        menuItem50.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItem100 = new JRadioButtonMenuItem("100");
        menuItem100.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        ButtonGroup itemsButtonGroup = new ButtonGroup();
        itemsButtonGroup.add(menuItem10);
        itemsButtonGroup.add(menuItem25);
        itemsButtonGroup.add(menuItem50);
        itemsButtonGroup.add(menuItem100);

        JMenu explicitMenu = new JMenu("Explicit");
        explicitMenu.setToolTipText("Explicit or safe for kids songs");

        //explicit or nonexplicit content preference
        yes_menuItem = new JCheckBoxMenuItem("Yes");
        yes_menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        yes_menuItem.setSelected(true);
        no_menuItem = new JCheckBoxMenuItem("No");
        no_menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        ButtonGroup explicitGroup = new ButtonGroup();
        explicitGroup.add(yes_menuItem);
        explicitGroup.add(no_menuItem);


        getAlbumsButton = new JButton("Get Albums");
        Dimension btnSize = new Dimension(100, 30);
        getAlbumsButton.setPreferredSize(btnSize);
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel1 = new JPanel(new FlowLayout());
            buttonPanel.setPreferredSize(new Dimension(820, 40));
        JPanel buttonPanel2 = new JPanel(new FlowLayout());
            buttonPanel2.setPreferredSize(new Dimension(300,40));
        buttonPanel.add(buttonPanel1, BorderLayout.CENTER);
        buttonPanel.add(buttonPanel2, BorderLayout.LINE_END);
        resultsPane = new JPanel(new FlowLayout());
        SidePanel = new JPanel();
        resultsPane.setPreferredSize(new Dimension(820, 500));
        resultsPane.setBackground(Color.WHITE);
        SidePanel.setPreferredSize(new Dimension(300,500));
        SidePanel.setLayout(new GridLayout());

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
        resultsPane.setLayout(new BorderLayout());

        //bounce ball components
        SidePanel.setPreferredSize(new Dimension(300,500));
        add(SidePanel, BorderLayout.LINE_END);
        bounceBallPanel.setPreferredSize(new Dimension(300, 500));
        bounceBallPanel.setLayout(new GridLayout());
        AnimationPanel anime = new AnimationPanel();
        bounceBallPanel.add(anime);
        SidePanel.add(bounceBallPanel);
        buttonPanel1.add(getAlbumsButton);
        buttonPanel2.add(bounceBallPanel.getstartButton());
        buttonPanel2.add(bounceBallPanel.getstopButton());
        SidePanel.setVisible(true);

    }
    private void addListeners(){
        getAlbumsButton.addActionListener(this);
        bounceBallPanel.getstartButton().addActionListener(this);
        bounceBallPanel.getstopButton().addActionListener(this);
    }

   
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Get Albums")) {

            resultsPane.removeAll();

            XMLstuff.clearAlbumList();
            DefaultTableModel tableModel = new DefaultTableModel(columnNames,0) {
                
                public Class getColumnClass(int column)
                {
                    if (column == 3) return ImageIcon.class;
                    return Object.class;
                }
            };
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


            if (menuItem100.isSelected()) {
                itemsNum = "100";
            } else if (menuItem25.isSelected()) {
                itemsNum = "25";
            } else if (menuItem50.isSelected()) {
                itemsNum = "50";
            } else {
                itemsNum = "10";
            }


            if (no_menuItem.isSelected()) {
                explicitYN = "nonexplicit";
            } else {
                explicitYN = "explicit";
            }

            XMLstuff.setURL("https://rss.itunes.apple.com/api/v1/us/itunes-music/" + typeSelection + "/all/" + itemsNum + "/" + explicitYN + ".atom");
           

            this.XMLstuff.getAlbumList();

          
            for (Album a: this.XMLstuff.getAlbumList()) {
                tableModel.addRow(new Object[]{
                        a.getName(),
                        a.getArtistName(),
                        a.getGenre(),
                        new ImageIcon(a.getIcon())
                });
            }

            // start the table
            table = new JTable(tableModel) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setRowHeight(50);

            Dimension tableSize =  new Dimension(820, 500); // for the tale size 
           
            table.getColumnModel().getColumn(0).setPreferredWidth(Math.round(tableSize.width*0.50f));
            
            table.getColumnModel().getColumn(1).setPreferredWidth(Math.round(tableSize.width*0.20f));
            
            table.getColumnModel().getColumn(2).setPreferredWidth(Math.round(tableSize.width*0.20f));
           
            table.getColumnModel().getColumn(3).setPreferredWidth(Math.round(tableSize.width*0.10f));

            JScrollPane resultsPaneScroll = new JScrollPane(table);
            resultsPaneScroll.setPreferredSize(new Dimension(820, 500));
            resultsPane.add(resultsPaneScroll, BorderLayout.CENTER);

            resultsPane.updateUI();

        } else if ( cmd.equals("Start")){
            System.out.println("start");
        } else if ( cmd.equals("Stop")){
            System.out.println("stop");
        }
    }


    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            try {
                JPanel panel = new JPanel(new BorderLayout());
            
                panel.setPreferredSize(new Dimension(500, 500));
                AnimationPanel animation = new AnimationPanel();
                panel.add(animation, BorderLayout.CENTER);
                panel.setVisible(true);
            } catch (Exception e ){
                System.out.println(e);
            }
        });
    }
}