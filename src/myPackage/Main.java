// Group 5 
// Assignment 1 
// Machine Learning With Java 
// Winter 2024 
// Main Java File 

package myPackage;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		
		// create new ArrayList to store coordinates from the trainingData file 
		ArrayList<Coordinate> coordinateList = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		// create the 3 classifiers
		NNClassifier test = new NNClassifier();
		test.learn(coordinateList);

		KNNClassifier KNNTest = new KNNClassifier();
		KNNTest.learn(coordinateList);

		AnotherClassifier dummyTest = new AnotherClassifier();

		try { // use try catch for invalid input handling 
			
			// print out the startup menu
			System.out.println("Welcome To HiPhone! Please choose classifier method: ");
			System.out.println("1. Nearest Neighbour Classifier");
			System.out.println("2. K-Nearest Neightbour Classifier");
			System.out.println("3. Another Classifier");
			System.out.print("\nInput: ");

			// get user input for which classifier they wish to use
			int userClassifier = scanner.nextInt();

			if (userClassifier == 1) // 1 = NN
			{
				System.out.println("1. Read from a text file");
				System.out.println("2. Input data manually");
				System.out.print("\nInput: ");

				int userMethodInput = scanner.nextInt();
				ArrayList<Coordinate> unknownDataList = new ArrayList<>();

				if (userMethodInput == 1) // the user wishes to enter a txt file
				{
					test.predictTextFile(unknownDataList, coordinateList);
				}

				else if (userMethodInput == 2) // manual input of coordinates is desired
				{
					test.predictManualInput(coordinateList);
				}

				else 
				{
					System.out.println("Invalid input!");
				}
			}

			// same as above, only difference is using KNN
			else if (userClassifier == 2) // 2 = KNN
			{
				System.out.println("1. Read from a text file");
				System.out.println("2. Input data manually");
				System.out.print("\nInput: ");

				int userMethodInput = scanner.nextInt();
				ArrayList<Coordinate> unknownDataList = new ArrayList<>();

				if (userMethodInput == 1) 
				{
					KNNTest.predictTextFile(unknownDataList, coordinateList);
				}

				else if (userMethodInput == 2) 
				{
					KNNTest.predictManualInput(coordinateList);
				}

				else 
				{
					System.out.println("Invalid input!\n");
					return;
				}
			}

			else if (userClassifier == 3) // 3 = dummy classifier
			{
				System.out.println("1. Read from a text file");
				System.out.println("2. Input data manually");
				System.out.print("\nInput: ");
				
				ArrayList<Coordinate> dummyList1 = new ArrayList<>();
				ArrayList<Coordinate> dummyList2 = new ArrayList<>();


				int userMethodInput = scanner.nextInt();

				if (userMethodInput == 1) {
					dummyTest.predictTextFile(dummyList1, dummyList2);
				}

				else if (userMethodInput == 2) 
				{
					dummyTest.predictManualInput(dummyList1);
				}

				else 
				{
					System.out.println("Invalid Input!\n");
				}
			}

			else 
			{
				System.out.println("Invalid Classifier");
			}
		} 
		
		catch (InputMismatchException e) 
		{
			System.out.println("Invalid input - catched");
		}

		finally 
		{
			scanner.close();
		}
	}
}
