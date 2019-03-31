// Programmer 1 : Krupa Patel
// Programmer 2 : Visarg Patel
// Programmer 3 : Purvin Patel
// CSCI 470
// ASSIGNMENT 2
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MilageRedemptionApp extends JFrame implements ActionListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L; 
	
	// Class Variable
	private String[] cities;
	private ArrayList<JTextArea> textAreaPool = new ArrayList<JTextArea>();
	private MilesRedeemer service = new MilesRedeemer();
	private int lastIndex = -1;
	private JSpinner spinner;
	
	//Month array
	private final String[] MONTHS = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	
	public static void main(String[] args) {
		// Starting the application
        EventQueue.invokeLater(() -> {
        	new MilageRedemptionApp("C:\\Users\\Purvin\\eclipse-workspace\\milesredeemer.java\\src\\TestData.txt");
        });
	}
	
	/**
	 * Main driver program for the Mileage Redemption Application
	 * filePath path you want to use for the file
	 */
	public MilageRedemptionApp(String filePath) {
		super("Ticket Redeemer");
		File file = new File(filePath);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {}
		this.service.readDestinations(scanner);
		
		// Set up the window size.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        // Layout set.
        setupLayout();

        //Window displayer
        setMinimumSize(new Dimension(950, 350));
        setLocationRelativeTo(null);
        setVisible(true);
	}
	

    private void setupLayout() {
    	// Initializer
    	JPanel content = new JPanel();
    	JPanel panel1 = new JPanel();
    	JPanel panel2 = new JPanel();
    	JPanel panel3 = new JPanel();
    	JPanel panel4 = new JPanel();
    	JPanel panel5 = new JPanel();
    	JPanel panel6 = new JPanel();
    	JPanel panel7 = new JPanel();
    	JPanel panel8 = new JPanel();
    	panel1.setBackground(new Color(210,68,215));
    	panel2.setBackground(Color.GREEN);
    	panel3.setBackground(new Color(176,224,230));
    	panel4.setBackground(Color.PINK);
    	panel5.setBackground(Color.magenta);
    	panel6.setBackground(Color.orange);
    	panel7.setBackground(Color.gray);
    	panel8.setBackground(Color.yellow);
    	
    	// Layouts for the app
    	content.setLayout(new BorderLayout());
    	panel1.setLayout(new GridLayout(2,1,11,11));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel3.setLayout(new GridLayout(4,2,10,7));
    	panel4.setLayout(new GridLayout(3,1));
        panel5.setLayout(new FlowLayout());
        panel6.setLayout(new FlowLayout());
        panel7.setLayout(new FlowLayout());
    	
    	// Extra Setup
    	cities = this.service.getCityNames();
        JList<String> list = new JList<String>(cities);
        list.addListSelectionListener(this);
        list.setFixedCellWidth(300);
        list.setVisibleRowCount(9);
        String[] MONTHStrings = MONTHS; //getting the month names
        SpinnerListModel monthModel = new SpinnerListModel(MONTHStrings);
        spinner = new JSpinner(monthModel);
        JButton button = new JButton("Redeem tickets >>>");
        button.addActionListener(this);

        //TextPool Setup for application
        textAreaPool.add(new JTextArea());
        textAreaPool.add(new JTextArea());
        textAreaPool.add(new JTextArea());
        textAreaPool.add(new JTextArea());
        textAreaPool.add(new JTextArea(1,8));
        textAreaPool.add(new JTextArea(1,8));
        textAreaPool.add(new JTextArea(8,40));
        textAreaPool.get(0).setEditable(false);
        textAreaPool.get(1).setEditable(false);
        textAreaPool.get(2).setEditable(false);
        textAreaPool.get(3).setEditable(false);
        textAreaPool.get(5).setEditable(false);
        textAreaPool.get(6).setEditable(false);
        
        // Border Setup
        panel1.setBorder(BorderFactory.createTitledBorder("List of destination cities"));
        panel2.setBorder(BorderFactory.createTitledBorder("Redeem Tickets"));
        panel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 8, 8));
        
        // Connecting content
        panel3.add(new JLabel("Required Miles"));
        panel3.add(textAreaPool.get(0));
        panel3.add(new JLabel("Miles For Upgrading"));
        panel3.add(textAreaPool.get(1));
        panel3.add(new JLabel("Miles For SuperSaver"));
        panel3.add(textAreaPool.get(2));
        panel3.add(new JLabel("MONTHS For SuperSaver"));
        panel3.add(textAreaPool.get(3));
        panel5.add(new JLabel("Your Accumulated Miles: "));
        panel5.add(textAreaPool.get(4));
        panel6.add(new JLabel("Month Of Depature: "));
        panel8.add(new JLabel("Your Remaining Miles: "));
        panel8.add(textAreaPool.get(5));
        panel6.add(spinner);
        panel7.add(button);
        panel2.add(panel4,BorderLayout.PAGE_START);
        panel1.add(list, BorderLayout.PAGE_START);
        panel1.add(panel3,BorderLayout.CENTER);
        panel2.add(panel5);
        panel2.add(panel6);
        panel2.add(panel7);
        panel2.add(textAreaPool.get(6), BorderLayout.CENTER);
        panel2.add(panel8, BorderLayout.PAGE_END);
        content.add(panel1, BorderLayout.LINE_START);
        content.add(panel2, BorderLayout.CENTER);
        
        add(content);
    }
    /**
     * This action button looks at redeem button option
     * Whenever is it pressed, the redeemMiles function is called
     * and appropriate output is generated 
     */
    public void actionPerformed(ActionEvent e) {
    	int amount = 0;
    	// Check if an amount was entered, if no valid amount was entered, input equals to 0
    	try {
    		amount = Integer.parseInt(this.textAreaPool.get(4).getText());
    	}
    	catch (Exception e1) {}
    	// redeem miles function.
    	ArrayList<String> temp= service.redeemMiles(amount,java.util.Arrays.asList(MONTHS).indexOf((String)spinner.getValue()) + 1);
    	String out = "\nYour Accumulated miles can be usedto redeem the following air tickets: \n\n";
    	for(int i = 0; i < temp.size(); i ++) {
    		out += temp.get(i) + "\n";
    	}
    	textAreaPool.get(6).setText(out);
    	textAreaPool.get(5).setText(service.getRemainingMiles() + "");
    }

	/**
	 * This waits for the listener and gives cities name
	 * when a city is selected, its information is displayed below
	 */
	public void valueChanged(ListSelectionEvent e) {
		// Check for a new value
		if(e.getValueIsAdjusting()) {
			// Use correct index
			int index = e.getLastIndex();
			if(lastIndex == index) {
				index = e.getFirstIndex();
			}
			lastIndex = index;
			
			//gets and displays city information
			Destination d = service.getDestinationByName(cities[index]);
			textAreaPool.get(0).setText(String.valueOf(d.getNormalMileage()));
			textAreaPool.get(1).setText(String.valueOf(d.getAdditionalMileage()));
			textAreaPool.get(2).setText(String.valueOf(d.getSupersaverMileage()));
			textAreaPool.get(3).setText(MONTHS[d.getStartMonth() -1 ] + " to " + MONTHS[d.getEndMonth() - 1]);
			
		}
	}

}