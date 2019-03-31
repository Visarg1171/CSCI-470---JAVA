
// Programmer1: Purvin patel 
//Programmer2: Visarg patel 
//Programmer3: krupa patel 
// CSCI 470 
// Assignment 1


import java.util.Scanner; 		//User Input
import java.io.File; 			//Use files 
import java.io.IOException; 	//For input output 

public class miles
{
    public static void main(String[] args) throws IOException {
	
    
    {
        int numMiles, 	//It store number of miles 
                month; //It store the month of departure 
        char cont; 	   //It store the data 
        cont = 'y'; //initialized to y
        Scanner inputScan = new Scanner(System.in); //Scanner object created 
        
        //Didn't need a try catch block because main function is throwing IOException
        //Also ran into a weird error if the 4 lines below were inside the try block
        System.out.println("Enter the name of the file with extension"); // user enter the filename
        String fileName = inputScan.nextLine(); // user enter the input 
        Scanner fileInput = new Scanner(new File(fileName)); // reads the content of file
        MileRedeemer redeemerObj = new MileRedeemer(); // Create an object of MileRedeemer class
             
        do // do while loop started 
        {
            try  
            {
            	cont = 'n'; //value n
              
                redeemerObj.readDestinations(fileInput); // call function to readDestinations in MileRedeemer
                System.out.println("\n---------------------------------------------");
                System.out.println("List of destination cities you can travel to: ");
                for(String str: redeemerObj.getCityNames()) 
                {
                    System.out.println(str); //it print each city name 
                }
                System.out.println("\n---------------------------------------------");
                System.out.print("\nPlease input your total accumulated miles: "); 
                numMiles = inputScan.nextInt(); //input

                System.out.print("\nPlease input your month of departure (1-12): ");
                month = inputScan.nextInt(); // Stores input

                System.out.println("");
                for(String str:redeemerObj.redeemMiles(numMiles, month)) //print values return by redeemMiles.
                {
                    System.out.println(str);
                }
                System.out.println("\nYour remaining miles: "+redeemerObj.getRemainingMiles()); // It display the remaining miles 
                
            }
            catch(Exception e)
            {
                System.err.println("\nEncountered wrong input "+e.getMessage());
            }
            finally
            {
                System.out.print("\nDo you want to continue:(yes/no)?"); 
                String str = inputScan.next(); // it store the user input
                cont = str.charAt(0); //it convert string to char
            }

        }while(cont=='y' || cont=='Y'); // If enter yes then it continues 
        System.out.println("\nEnd of application!"); 
        inputScan.close(); //It close the inputScan
        fileInput.close();

    }
    }
}
