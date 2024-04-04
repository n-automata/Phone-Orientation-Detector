// Group 5 
// Assignment 1 
// Machine Learning With Java 
// Winter 2024 
// NNClassifier Java File 

package myPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NNClassifier extends Classifier
{
	// this method gets user input and performs input validation
	 public double inputAndValidation(Scanner scanner) 
	 {
	        double input = 0;

	        while (true) 
	        {
	            if (scanner.hasNextDouble()) // checks if it's a double 
	            {
	                input = scanner.nextDouble();
	                break;
	            } 
	            
	            else 
	            {
	                System.out.println("Error, please enter a number!");
	                scanner.next(); // Clear the invalid input
	            }
	        }

	        while (true) 
	        {
	            if (input <= 1.0 && input >= -1.0) // checks value is between -1.0 and 1.0
	            {
	                break;
	            } 
	            
	            else 
	            {
	                System.out.println("Error, please enter coordinates between -1.0 and 1.0");
	                input = inputAndValidation(scanner);
	            }
	        }

	        return input;
	    }

    
    // learn method will read in the training data and store it in the ArrayList named coordinateList 
	 @Override
    public void learn(ArrayList<Coordinate> coordinateList) 
    {
        String fileName = "trainingData.txt";

        try (BufferedReader fileIn = new BufferedReader(new FileReader(fileName))) 
        {

            String line;
            while ((line = fileIn.readLine()) != null) 
            {
                String[] parts = line.split(","); // splits the line of value into parts via the comma "," 
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                double z = Double.parseDouble(parts[2]);
                int label = Integer.parseInt(parts[3]);

                coordinateList.add(new Coordinate(x, y, z, label)); // add coordinates to array list 
            }
        } 
        catch (IOException e) // catches IO exception
        {
            System.out.println("Could not open the file " + fileName);
        }
    }


    // method that reads in an unknown data text file, finds the nearest neighbour, and assigns a label/orientation in an output result file 
	 @Override
    public void predictTextFile(ArrayList<Coordinate> unknownDataArray, ArrayList<Coordinate> coordinateArray) 
	 {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name of text file: ");
        String fileName2 = scanner.nextLine();

        try (Scanner fileIn = new Scanner(new File(fileName2));
             FileWriter fout = new FileWriter("result.txt")) 
        {

            System.out.println("\nFile with name " + fileName2 + " successfully found\nREADING...\n");
            System.out.println("Output stored in result.txt\n\n[DONE]\n"); 
            
            while (fileIn.hasNextLine()) 
            {
                String line = fileIn.nextLine();
                String[] parts = line.split(",");

                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                double z = Double.parseDouble(parts[2]);

                unknownDataArray.add(new Coordinate(x, y, z, 0)); // add the unknownDataArray coordinates to the list 
            }

            // finds the shortest distance by iterating through the coordinate array
            // label is then set by copying the label of the nearest neighbour 
            for (int i = 0; i < unknownDataArray.size(); i++) 
            {
                unknownDataArray.get(i);
				double shortestDistance = Coordinate.distanceBetween(unknownDataArray.get(i), coordinateArray.get(0));
                
                int label = coordinateArray.get(0).getLabel();
                unknownDataArray.get(i).setLabel(label);

                for (int k = 1; k < coordinateArray.size(); k++) 
                {
                    unknownDataArray.get(i);
					double distance1 = Coordinate.distanceBetween(unknownDataArray.get(i), coordinateArray.get(k));

                    if (distance1 < shortestDistance) // find the shortest distance by comparing 
                    {
                        shortestDistance = distance1;
                        label = coordinateArray.get(k).getLabel();
                        unknownDataArray.get(i).setLabel(label);
                    }
                }

                String phoneOrientation;
                int orientationDetect = unknownDataArray.get(i).getLabel();

                // using the label, assign a phone orientation to a string variable which will be 
                // added in the output result file 
                switch (orientationDetect) {
                    case 1:
                        phoneOrientation = "Face Up";
                        break;
                    case 2:
                        phoneOrientation = "Face Down";
                        break;
                    case 3:
                        phoneOrientation = "Portrait";
                        break;
                    case 4:
                        phoneOrientation = "Portrait Upside Down";
                        break;
                    case 5:
                        phoneOrientation = "Landscape Left";
                        break;
                    case 6:
                        phoneOrientation = "Landscape Right";
                        break;
                    default:
                        phoneOrientation = "Unknown";
                }

                if (unknownDataArray.get(i).getX() != 0 && unknownDataArray.get(i).getY() != 0 && unknownDataArray.get(i).getZ() != 0) // checks if coordinate is all 0
                {
                    fout.write(unknownDataArray.get(i).getX() + "," + unknownDataArray.get(i).getY() + "," + unknownDataArray.get(i).getZ() + "," + unknownDataArray.get(i).getLabel() + "," + phoneOrientation + "\n");
                }
            }
        } 
        
        // catch functions for error handling 
        catch (FileNotFoundException e) 
        {
            System.out.println("Could not open the file " + fileName2);
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            System.out.println("Error writing to result.txt");
            e.printStackTrace();
        }
        
        finally
        {
        	scanner.close(); // close the scanner 
        }
    }
        
    
    // allows the user to enter in coordinates manually 
	 @Override
	public void predictManualInput(ArrayList<Coordinate> coordinateArray) 
	{
		Scanner scanner = new Scanner(System.in);

		double userX = 0;
		double userY = 0;
		double userZ = 0;

		// get user input for the coordinates 
		System.out.println("Please enter coordinates one at a time: ");

		System.out.print("X: ");
		userX = inputAndValidation(scanner);

		System.out.print("Y: ");
		userY = inputAndValidation(scanner);

		System.out.print("Z: ");
		userZ = inputAndValidation(scanner);

		// Create a new Coordinate object using the user's input
		Coordinate test = new Coordinate(userX, userY, userZ, 0);

		int label = 0;

		// Set initial shortest distance to the distance between test and the first
		// element in the array
		double shortestDistance = Coordinate.distanceBetween(test, coordinateArray.get(0));
		label = coordinateArray.get(0).getLabel();
		test.setLabel(label);

		// same function as in the above method 
		for (int k = 1; k < coordinateArray.size(); k++) 
		{
			double distance1 = Coordinate.distanceBetween(test, coordinateArray.get(k));

			if (distance1 < shortestDistance) 
			{
				shortestDistance = distance1;
				label = coordinateArray.get(k).getLabel();
				test.setLabel(label);
			}
		}

		String phoneOrientation;

		int orientationDetect = test.getLabel();

		switch (orientationDetect) 
		{
		case 1:
			phoneOrientation = "Face Up";
			break;
		case 2:
			phoneOrientation = "Face Down";
			break;
		case 3:
			phoneOrientation = "Portrait";
			break;
		case 4:
			phoneOrientation = "Portrait Upside Down";
			break;
		case 5:
			phoneOrientation = "Landscape Left";
			break;
		case 6:
			phoneOrientation = "Landscape Right";
			break;
		default:
			phoneOrientation = "Unknown"; // Handle unexpected label values
		}

		System.out.println("\nThe label is: " + orientationDetect + " with orientation " + phoneOrientation);
		System.out.println("\n[Done]");
	}
         
        // end of class 
}

    
    
    


