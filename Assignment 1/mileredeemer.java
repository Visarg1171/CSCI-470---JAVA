// Programmer1: Purvin patel 
//Programmer2: Visarg patel 
//Programmer3: krupa patel 
// CSCI 470 
// Assignment 1

import java.util.Arrays; 
import java.util.Collections; //sort method
import java.util.Comparator; //Comparator interface
import java.util.Scanner; 
import java.util.ArrayList; //For ArrayList

class MileRedeemer
{

    int remainingMiles=0; // Remaining miles set to zero 
    String record; // It store each line 
    ArrayList<Destination>destinationList = new ArrayList<Destination>(); 

    
    public void readDestinations(Scanner fileScanner)
    {
        try
        {
       //it executes the loop 
            while(fileScanner.hasNext())
            {
                int normalMiles, //it store the miles
                        supersaverMiles, //It store the supersaverMiles 
                        additionalMiles, // It store the additional miles 
                        startMonth, // It store the startMonth
                        endMonth; // It store the endMonth
                String destinationCity; // It store destinationCity as string 
                record = fileScanner.nextLine(); 

                String[] values = record.split(";"); //it put the semicolon in the output 
                String[] values2 = values[4].split("-"); 

          
                destinationCity = values[0];
         
                normalMiles = Integer.parseInt(values[1]);
                supersaverMiles = Integer.parseInt(values[2]);
                additionalMiles = Integer.parseInt(values[3]);
                startMonth = Integer.parseInt(values2[0]);
                endMonth = Integer.parseInt(values2[1]);

        
                Destination obj = new Destination(normalMiles,supersaverMiles,additionalMiles,startMonth,endMonth,destinationCity);

    
                destinationList.add(obj);

            }
        }
        catch(Exception e)
        {
            System.err.println("File input format wrong: "+e.getMessage());
            System.exit(0);
        }


    }

    public String[] getCityNames()
    {
        int i = 0;
        String[] cityNames = new String[destinationList.size()]; // String array with name cityNames
        for(Destination d: destinationList)
        {
            cityNames[i] = d.getDestinationCity();
            i++;
        }
        Arrays.sort(cityNames); // citNames sorted 
        return cityNames; //String array is returned
    }

    public String[] redeemMiles(int miles,int month)
    {
        int tempMiles = miles; // It store miles to tempMiles

        ArrayList<Destination> abc = new ArrayList<>(); //ArrayList of destination is created
        ArrayList<String> def = new ArrayList<>(); //ArrayList of String is created

        Collections.sort(destinationList,new MileageComparator()); 
        for(Destination d: destinationList)
        {
            if(month < d.getStartMonth() || month > d.getEndMonth()) 
            {
                if(tempMiles > d.getNormalMiles())
                {
              
                    abc.add(d);
                    tempMiles-= d.getNormalMiles(); 
                }

            }
            else if(month >= d.getStartMonth() && month <= d.getEndMonth()) 
            {
                if(tempMiles > d.getSuperSaverMiles())
                {
                    abc.add(d);
                    tempMiles -= d.getSuperSaverMiles();
                }
            }

        }

        for(Destination d: abc)
        {
            if(tempMiles > d.getAdditionalMiles())
            {
                def.add("* A trip to "+d.getDestinationCity()+" first class"); 
                tempMiles -= d.getAdditionalMiles();
            }
            else // If miles is less than additional miles then economy class ticket 
            {
                def.add("* A trip to "+d.getDestinationCity()+", economy class"); // it Display economy class with destination city
            }
        }
        remainingMiles = tempMiles; // 
        String[] details = new String[def.size()]; //String array is created
        details = def.toArray(details); 
        return details; 
    }


    public int getRemainingMiles()
    {
        return remainingMiles; // Returns the remaining miles
    }

}