// Group 5 
// Assignment 1 
// Machine Learning With Java 
// Winter 2024 
// Dummy AnotherClassifier Java File 

package myPackage;

import java.util.ArrayList;

public class AnotherClassifier extends Classifier
{
	// functions prints out the function name 
	
	public void learn(ArrayList <Coordinate> coordinateList)
	{
		System.out.println("predictTextFile from AnotherClassifier!\n");
	}
	
	public void predictTextFile(ArrayList<Coordinate> unknownDataArray, ArrayList<Coordinate> coordinateArray)
	{
		System.out.println("predictTextFile from AnotherClassifier!\n");
	}
	
	public void predictManualInput(ArrayList <Coordinate> coordinateList)
	{
		System.out.println("predictManualInput from AnotherClassifier!\n");
	}
	
}
