import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class creates, keeps track of, and calculate mile redemption
 *
 */
public class MilesRedeemer {
	List<Destination> destinations;
	int miles;
	
	/**
	 * Constructor that is called on object creation
	 */
	public MilesRedeemer () {
		this.destinations = new ArrayList<Destination>();
	};
	
	/**
	 * Populate this service with a file scanner
	 * param fileScanner the scanner that is reading the file with relevant information in it
	 */
	public void readDestinations(Scanner fileScanner) {
		// read the files
		while (fileScanner.hasNextLine()) {
			// create a new destination object
			this.destinations.add(new Destination(fileScanner.nextLine()));
		}
		Collections.sort(this.destinations, new MileageComparator());
	}
	
	public Destination getDestinationByName(String name) {
		Destination out = null;
		for(int i = 0; i < this.destinations.size(); i ++) {
			if(this.destinations.get(i).getCity() == name) {
				out = destinations.get(i);
			}
		}
		return out;
	}
	/**
	 * Get city names
	 * return Array of city names in alphabetical order
	 */
	public String[] getCityNames() {
		//create output array
		String[] cityList = new String[this.destinations.size()];
		// Get city names
		for(int i = 0; i < this.destinations.size(); i ++) {
			cityList[i] = this.destinations.get(i).getCity();
		}
		// alphabetically sort names
		Arrays.sort(cityList);
		// Return array
		return cityList;
	}
	
	/**
	 * param miles Miles to be redeemed
	 * param month Month of departure
	 * return ArrayList<String> of ticket information
	 */
	public ArrayList<String> redeemMiles(int miles, int month) {
		// List of final output
		ArrayList<String> output = new ArrayList<String>();
		// list to move data temperorily
		ArrayList<Destination> temp = new ArrayList<Destination>();
		// Get all possible ticket starting with farthest
		for(int i = 0; i < this.destinations.size(); i ++) {
			Destination d  = this.destinations.get(i);
			int cost = d.getNormalMileage() ;
			// supersaver applies if leave in month, apply discount
			if(month >= d.getStartMonth() && month <= d.getEndMonth()) {
				cost = d.getSupersaverMileage();
			}
			// if miles apply, then add flight to temp
			if(miles > cost) {
				miles -= cost;
				temp.add(d);
			}
		}
		// Check for upgrades
		for(int i = 0; i < temp.size(); i++) {
			// upgrade it
			if(miles > temp.get(i).getAdditionalMileage()) {
				miles -= temp.get(i).getAdditionalMileage();
				System.out.println(miles);
				output.add("* A trip to " + temp.get(i).getCity() + ", first class");
			}
			// normal economy ticket
			else {
				output.add("* A trip to " + temp.get(i).getCity() + ", economy class");
			}
		}
		// Set internal miles
		this.miles = miles;
		// Return
		return output;
	}
	
	/**
	 * return Miles left after redemption 
	 */
	public int getRemainingMiles() {return miles;}
}