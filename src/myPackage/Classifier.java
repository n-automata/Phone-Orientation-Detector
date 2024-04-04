// Group 5 
// Assignment 1 
// Machine Learning With Java 
// Winter 2024 
// Classifier parent Java File 

package myPackage;

import java.util.ArrayList; 

abstract class Classifier // other classifiers will inherit from this abstract class 
{
	public abstract void learn(ArrayList <Coordinate> coordinateList); 
	abstract void predictTextFile(ArrayList<Coordinate> unknownDataArray, ArrayList<Coordinate> coordinateArray); 
	abstract void predictManualInput(ArrayList<Coordinate> coordinateArray); 
}
