# Phone Orientation Detector
Detects a phone orientation using machine learning

## Technologies Used 
Java, Eclipse, Machine Learning (NNClassifier, attempted KNN classifier) 

## Screenshots: 
Reference Table: 
<p align ="center">
<img src = "https://github.com/n-automata/Phone-Orientation-Detector/assets/148803386/84d1a727-4999-49aa-9835-f7c567ba89cc" width="600" height ="300">
<img src = "https://github.com/n-automata/Phone-Orientation-Detector/assets/148803386/70de36b4-e6fd-49cb-b9b8-2d733b676d12" width="600" height ="350">
</p>

## What does it do? 
This Phone Orientation detector is designed to classify data by training from pre-existing data and learning from it. Using the reference table for known orientations, the application learns from the set  "trainingData". By using the nearest-neighbour classifier, coordinate data with unknown orientation is passed in and the orientation is detected using basic machine learning. For example, if coordinates (0.01, 0.01, 0.99) were passed in then the application would set the label to 2 with orientation face down since it is the closest match to the coordinates. 

## Additional Info 
Downloading this entire repository should allow you to run the application inside Eclipse. Curious about the source code and how it was written? All source code files can be found in the **src** folder. 
