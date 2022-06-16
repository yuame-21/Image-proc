# Program Command Instructions

First, you must run the` main()` method in `ImageProcessor` to instantiate the program. 
Then, you can use the Image Processor with either a .txt or a series of command line inputs 

## .txt script 

To run the program using a .txt file, enter the following in the console : 

-file [fileName.txt] \n 
e.g. 
-file ././res/miniscript.txt 

## command line inputs 

### load 
Before making any edits, you must first load an image of type .jpg, .ppm, .png, or .bmp
Enter the following in the console: 
load [file] [fileName] \n 

e.g. 
load ././res/pink.txt pink 

You can load a new image at any point in the program to change the image you wish to edit. 

### edits
Then, you can edit the loaded image with any of the following commands: 
Multiple commands can be used on an image in a row.

brighten [oldFileName] [newFileName] [int increment] \n

e.g. 
brighten pink pinkBrighter 20 

darken [oldFileName] [newFileName] [int increment] \n

e.g.
darken pink pinkDarker 20

red-component [oldFileName] [newFileName] \n

e.g.
red-component pink pinkRedC

green-component [oldFileName] [newFileName] \n

e.g.
green-component pink pinkGreenC

blue-component [oldFileName] [newFileName] \n

e.g.
blue-component pink pinkBlueC

horizontal-flip [oldFileName] [newFileName] \n

e.g. 
horizontal-flip pink pinkHoriz

vertical-flip [oldFileName] [newFileName] \n

e.g. 
vertical-flip pink pinkVert

value-component [oldFileName] [newFileName] \n

e.g. 
value-component pink pinkValue

luma-component [oldFileName] [newFileName] \n

e.g. 
luma-component pink pinkLuma

intensity-component [oldFileName] [newFileName] \n

e.g. 
intensity-component pink pinkIntensity

sepia [oldFileName] [newFileName] \n 

e.g. 
sepia pink pinkSepia

greyscale [oldFileName] [newFileName] \n

e.g. 
greyscale pink pinkGreyscale

blur [oldFileName] [newFileName] \n

e.g. 
blur pink pinkBlur

sharpen [oldFileName] [newFileName] \n

e.g. 
sharpen pink pinkSharpen

### save 
You can save an image as a .ppm, .png, .bmp, .jpg based on the file you enter to save it as. 
Enter the following into the console: 
save [file] [fileName] \n

e.g. 
to save as a .bmp
save ././res/pink.bmp pink

to save as a .ppm
save ././res/pink.ppm pink

to save as a .jpg
save ././res/pink.jpg pink

to save as a .png
save ././res/pink.png pink

### quit 
You can quit the program at any time. To quit, enter the following either 'q' or 'Q' in the console 



