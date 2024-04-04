// Group 5 
// Assignment 1 
// Machine Learning With Java 
// Winter 2024 
// KNNClassifier Java File 

package myPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// KNN Classifier class 

public class KNNClassifier extends Classifier
{

	// function to get user input and validate it 
	public double inputAndValidation(Scanner scanner) {
        double input = 0;

        while (true) {
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                break;
            } else {
                System.out.println("Error, please enter a number!");
                scanner.next(); // Clear the invalid input
            }
        }

        while (true) {
            if (input <= 1.0 && input >= -1.0) {
                break;
            } else {
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
            String[] parts = line.split(","); // split the coordinates into x,y,z 
            double x = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);
            double z = Double.parseDouble(parts[2]);
            int label = Integer.parseInt(parts[3]);

            coordinateList.add(new Coordinate(x, y, z, label)); // add the coordinates to the list 
        }
    } 
    
    catch (IOException e) // IO exception handling 
    {
        System.out.println("Could not open the file " + fileName);
    }
}

// this function is similar to the one in the NN classifier but this one uses KNN 
@Override 
public void predictTextFile(ArrayList<Coordinate> unknownDataArray, ArrayList<Coordinate> coordinateArray) 
{
	Scanner scanner = new Scanner(System.in);

	System.out.println("Enter name of text file: ");
	String fileName2 = scanner.nextLine();

	try (Scanner fileIn = new Scanner(new File(fileName2)); FileWriter fout = new FileWriter("resultKNN.txt")) 
	{

		System.out.println("\nFile with name " + fileName2 + " successfully found\nREADING...\n\nResult stored in resultKNN.txt\n");
		System.out.println("[DONE]\n");

		// parse out the x,y,z coordinates from the file and store it in the array list 
		while (fileIn.hasNextLine()) 
		{
			String line = fileIn.nextLine();
			String[] parts = line.split(",");

			double x = Double.parseDouble(parts[0]);
			double y = Double.parseDouble(parts[1]);
			double z = Double.parseDouble(parts[2]);

			unknownDataArray.add(new Coordinate(x, y, z, 0));

		}

		
		
		for (int i = 0; i < unknownDataArray.size(); i++) 
		{
			int[] counter = new int[6]; // used to increment numbers of certain index 

			unknownDataArray.get(i);
			double shortestDistance = Coordinate.distanceBetween(unknownDataArray.get(i),
					coordinateArray.get(0));
			int label = coordinateArray.get(0).getLabel();

			for (int k = 1; k < coordinateArray.size(); k++) 
			{
				unknownDataArray.get(i);
				double distance1 = Coordinate.distanceBetween(unknownDataArray.get(i),
						coordinateArray.get(k));

				if (distance1 < shortestDistance) // find the shortest distance by comparing
				{
					shortestDistance = distance1;
					label = coordinateArray.get(k).getLabel();
					counter[label - 1]++;
				}
				
			}
			
		    
			// calculate the most amount of times a label of x has been assigned to the shortest distance 
			// for example, if "Face Up" with label 1 is the most of amount of neighbours for the unknown data point, 
			// then set the label of unknownData to be 1. 
			
			// we find out the highest number of close neighbours to a point, find it's index, and assign the label accordingly 
			
			int max = counter[0];
			int maxIndex = 0; 

			for (int z = 1; z < counter.length; z++) 
			{
				if (max < counter[z]) 
				{
					max = counter[z];
					maxIndex = z; 
				}
			}
			
			for (int x = 0; x < unknownDataArray.size(); x++) 
			{
			    unknownDataArray.get(x).setLabel(maxIndex + 1); // set the label of unknownData 
			}
			
			String phoneOrientation;
			int orientationDetect = unknownDataArray.get(i).getLabel();

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
				phoneOrientation = "Unknown";
			}

			if (unknownDataArray.get(i).getX() != 0 && unknownDataArray.get(i).getY() != 0
					&& unknownDataArray.get(i).getZ() != 0) // check if empty 
			{
				fout.write(unknownDataArray.get(i).getX() + "," + unknownDataArray.get(i).getY() + ","
						+ unknownDataArray.get(i).getZ() + "," + unknownDataArray.get(i).getLabel() + ","
						+ phoneOrientation + "\n"); // write coordinates to a file 
			}
		}
	}

	// catch error handling 
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
		scanner.close();
	}
}
	

// will only print a statement - users will have to use the text file method 
@Override 
public void predictManualInput(ArrayList<Coordinate> coordinateArray) 
{
    System.out.println("Not implemented yet. Soon!");
}


	
	
}
