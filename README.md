# Image Processor
## Ella Taira and Amelia Yu

Java implementation of an image processor completed over the course of 3 assignments, as documented below, for Object-Oriented Design at Northeastern U. 

# A6 Image Processor with GUI 

## How to use the program
- From `ImageProcessor`'s `main()` method, you can interact with this program through either a GUI or a TextUI
  - From terminal,
    - run the GUI by typing "java -jar Group.jar"
    - run the TextUI on a script by typing "java -jar Group.jar -file [fileName.txt]"
    - run the TextUI with command line inputs by typing "java -jar Group.jar -text" and then continuing to type commands as described below 

### TextUI Instructions

#### command line inputs

##### load
Before making any edits, you must first load an image of type .jpg, .ppm, .png, or .bmp
Enter the following in the console:
load [file] [fileName] \n

- e.g.
    load ././res/pink.txt pink

You can load a new image at any point in the program to change the image you wish to edit.

##### edits
Then, you can edit the loaded image with any of the following commands:
Multiple commands can be used on an image in a row.

brighten [oldFileName] [newFileName] [int increment] \n

  - e.g.
    brighten pink pinkBrighter 20

darken [oldFileName] [newFileName] [int increment] \n

  - e.g.
    darken pink pinkDarker 20

red-component [oldFileName] [newFileName] \n

   - e.g.
    red-component pink pinkRedC

green-component [oldFileName] [newFileName] \n

  - e.g.
    green-component pink pinkGreenC

blue-component [oldFileName] [newFileName] \n

   - e.g.
    blue-component pink pinkBlueC

horizontal-flip [oldFileName] [newFileName] \n

  - e.g.
    horizontal-flip pink pinkHoriz

vertical-flip [oldFileName] [newFileName] \n

  - e.g.
    vertical-flip pink pinkVert

value-component [oldFileName] [newFileName] \n

   -e.g.
    value-component pink pinkValue

luma-component [oldFileName] [newFileName] \n

  - e.g.
    luma-component pink pinkLuma

intensity-component [oldFileName] [newFileName] \n

   - e.g.
    intensity-component pink pinkIntensity

sepia [oldFileName] [newFileName] \n

   - e.g.
    sepia pink pinkSepia

greyscale [oldFileName] [newFileName] \n

  - e.g.
    greyscale pink pinkGreyscale

blur [oldFileName] [newFileName] \n

  - e.g.
    blur pink pinkBlur

sharpen [oldFileName] [newFileName] \n

  - e.g.
    sharpen pink pinkSharpen

##### save
You can save an image as a .ppm, .png, .bmp, .jpg based on the file you enter to save it as.
Enter the following into the console:
save [file] [fileName] \n

e.g.
  - to save as a .bmp
save ././res/pink.bmp pink

  - to save as a .ppm
save ././res/pink.ppm pink

  - to save as a .jpg
save ././res/pink.jpg pink

  - to save as a .png
save ././res/pink.png pink

##### quit
You can quit the program at any time. To quit, enter the following either 'q' or 'Q' in the console

## Completed Parts of the Program (contd. from A5)

- `ImageProcessor` class
  - contains `main()`

### Model:

- `ImageModel` interface and implementing class, `ImageModelImpl`
- `ImageModelState` interface and implementing class, `ImageModelStateImpl`
- `Edit` interface, implementing abstract class, `AEdit`, and all extending concrete classes
  - concrete classes:
    - `BrightenDarken`, `ColorComponent`, `FilterSharpenBlur`, `FlipHorizontal`, `FlipVertical`, `ILV`, `Transform`
- `Pixel` class 
- `ImageProcessorHistogram` class 

### View:

- `ImageProcessorView` interface and implementing class,` ImageProcessorTextView`
- `ImageProcessorGUIView` interface and implementing class, `ImageProcessorGUIViewImpl`
- `ImageProcessorHistogramView` class 

### Controller:

- `ImageProcessorController` interface and implementing class, `ImageProcessorControllerImpl`
- `Command` interface, implementing abstract class, `ACommand`, and all concrete command classes
  - concrete classes:
    - extending `ACommand`:
      - `ColorComponent`, `DarkenBrighten`, `Filter`, `Flip`, `IntensityLumaValue`, `Transformation`
    - JUST implementing `Command` --- not extending `ACommand`
      - `File`, `Load`, `Save`
- `Features` interface and implementing class, `ImageProcessorGUIControllerImpl`

### Testing:
- Testing classes:
  - `ImageModelImplTest`, `ImageModelStateImplTest`, `EditTest`, `ImageProcessorControllerImplTest`, `ImageProcessorTextViewTest`, `ImageProcessorGUIControllerImplTest`, `ImageProcessorHistogramTest`
- Mocks used:
  - `AppendableMock`, `ImageModelMock`, `ImageProcessorViewMock`, `ReadableMock`, `GUIControllerMock`

### res:
- jar file, `Group.jar`
- sample script that showcases all commands/functionality, `script.txt`
- all image files used in testing 

### other:
- README.md
- USEME.md

** all images used are either taken by us, or handmade, tiny, pixel images 

## Design Changes: 

- Removed the File class from the controller commands when writing this assignment's command line arguments. Our past interpretation was to have the "-file" command work the same as all other Edit commands, but we have since reevaluated based on the assignment brief. 
  - Now, the main() method looks for either: 
    - no arguments after loading the jar, which opens the GUI
    - "-file + [fileName]" to run the given script on the textUI (same functionality as A5)
    - "-text" to run the textUI and wait for command line arguments 
- We altered the command messages that are returned after each Command (edit/load/save) is executed to generalize them for both the textUI and GUI
  - Before, the messages included the file names, but we removed that clause because file names are not inputted/changing in the GUI as buttons are clicked. 
- In order to complete this assignment, we added to our past design to implement the new functionality. In brief, we added a second controller for the GUI using a Features interface, wrote classes in the view and model packages to make the histogram and collect data needed, and added another view for the GUI. 



**----------------------------------------------------------------------------------------------------**

# A5 Image Processor, Extended
## Ella Taira and Amelia Yu 

## Completed Parts of the Program: 

- `ImageProcessor` class
  - contains `main()`

### Model:

- `ImageModel` interface and implementing class, `ImageModelImpl`
- `ImageModelState` interface and implementing class, `ImageModelStateImpl`
- `Edit` interface, implementing abstract class, `AEdit`, and all extending concrete classes
  - concrete classes: 
    - `BrightenDarken`, `ColorComponent`, `FilterSharpenBlur`, `FlipHorizontal`, `FlipVertical`, `ILV`, `Transform`
- `Pixel` class **SEE DESIGN CHANGES**

### View: 

- `ImageProcessorView` interface and implementing class,` ImageProcessorTextView`

### Controller: 

- `ImageProcessorController` interface and implementing class, `ImageProcessorControllerImpl`
- `Command` interface, implementing abstract class, `ACommand`, and all concrete command classes 
  - concrete classes: 
    - extending `ACommand`: 
      - `ColorComponent`, `DarkenBrighten`, `Filter`, `Flip`, `IntensityLumaValue`, `Transformation`
    - JUST implementing `Command` --- not extending `ACommand`
      - `File`, `Load`, `Save`

### Testing:
- Testing classes:
  - `ImageModelImplTest`, `ImageModelStateImplTest`, `EditTest`, `ImageProcessorControllerImplTest`, `ImageProcessorTextViewTest`
- Mocks used:
  - `AppendableMock`, `ImageModelMock`, `ImageProcessorViewMock`, `ReadableMock`
  
### res: 
- jar file, `Group.jar`
- sample script that showcases all commands/functionality, `script.txt`
- updated class diagram 
- pink . png, jpg, bmp, and ppm as its original, blur, sharpen, greyscale, and sepia versions

### other: 
- README.md
- USEME.md

## Design Changes: 

- We altered the construction of our model so that instead of a 3-D array of integers to represent [row][column][r,g,b] of an image, we are using a 2-D array of `Pixel`s
  - To implement this change, we created the Pixel class, its methods, and refactored our existing code so that each `int[][][]` instead was `Pixel[][]`
  - We chose to change our design in this way to increase efficiency when processing commands. Before, large files took too long to load/edit because the loops running the methods were taking n^3 time. With `Pixel`, the loops only take n^2.

- In model's `Edit`, we abstracted `FlipHorizontal` and `FlipVertical` to reduce repeated code, as pointed our in our A4 feedback. 
  - the replacement class, again extending `AEdit`, is `FlipHZ`, which takes in a String to dictate flip direction. 
  - We also refactored any uses of this class in the test and other classes to fit the change. 

- In the `ImageModelStateImpl`, we deleted the second constructor that used to hold code that helped load an image. This code became redundant as we added new functionality to load additional image types. As a replacement, we created an additional load method to process non-ppms and moved ppm-load functionality into the original load.

- We changed the image we run the commands on from isec./// to juliet.///. The new image is an original. It is not from the internet (no citation needed)

- We edited the private method `addCommands()` in `ImageProcessorControllerImpl` to include the new commands assigned for A5. We did not change the method design but merely added new `.put()` statements.


**----------------------------------------------------------------------------------------------------**

# Assignment 4: Image Processor
# Ella Taira and Amelia Yu

Our Image Processor program consists of a model, view, and controller, and then an additional main() method to instantiate the program.

**Model: **

Our model package includes the ModelState, Model, and Edit interfaces.

The ImageModel interface includes what will be referenced in the controller, and are therefore directly necessary to execute the program.

The ImageModel interface loads, saves, and edits. We also included a method to generate a string version of a .ppm file in anticipation of future implementations.
ImageModel and its methods are implemented by ImageModelImpl. ImageModelImpl also manages a map of all image processings (i.e. load, edit) to keep track of each change made to a file. The save() method saves an image .ppm file to a given location. The load() method uploads a .ppm to our program so the user can edit it. The editImage() method takes in an editor (e.g. flip, brighten, etc.) and calls an instance of the editor's class to apply the appropriate edit to the loaded image.

When ImageModelImpl edits an image, it calls a concrete class implementing the interface Edit. Edits are the different ways to edit an image (i.e. flip, brighten, darken). Edit's method applyEdit() changes the pixels of an image to the Edit's criteria. This method is partially abstracted in the abstract class, AEdit. This class also has the method to clamp() a color value to a prescribed max value. Within each concrete class of AEdit, the remainder of the editing process is completed by setRGB().
BrightenDarken has both the brightening and darkening functionality depending on if its given degree is positive or negative. ColorComponent visualizes the red, green, or blue component to greyscale the image based on a String input of "red," "blue," or "green." FlipHorizontal flips the image horizontally. FlipVertical flips the image vertically. ILV greyscales the image for intensity, luma, or value based on a String input of "intensity," "luma," or "value." Some of these Edit classes can perform multiple types of edit because such edits are similar in logic/code.

Many of ImageModelImpl's methods reference ImageModelStateImpl to fully execute each objective.

The ImageModelState interface includes methods that pertain to the data of a .ppm file. These methods can get width/height, the color values of a single pixel in an image, the image's max color value, and creates a deep copy of the image's color values/pixels (to prevent mutation of the original file).  
ImageModelState's methods are implemented by ImageModelStateImpl. This class has two fields to store an array-version of all color values, which are in sets of three to make pixels, and the maximum color number (often 255). The second constructor for ImageModelStateImpl is where .ppms are actually loaded to the program and become usable (this constructor is called in ImageModelImpl's load() ).


**View: **

Our view package includes the interface ImageProcessorView.

The ImageProcessorView interface consists of the method, renderMessage(), which is used to present text to the user when called in the controller.

The class, ImageProcessorTextView, implements the ImageProcessorView interface and its methods. This class has a field, destination, which is where the method will render its results. The default destination is System.out, which we use when running the program so feedback is shown through the IntelliJ console for the purpose of our TextUI.

**Controller: **

Our controller package consists of interfaces ImageProcessorController and Command.

The ImageProcessorController has the method processImage(), which is used take in commands from a user when run, refer to appropriate methods, and overall control the image processor program.
The ImageProcessorController is implemented by ImageProcessorControllerImpl. ImageProcessorControllerImpl has fields for a model, view, map of commands for the user to call, scanner, and readable input. We use the command-design pattern to add all of the image processing commands to the controller.
When you run processImage, ther user will first be presented with a welcome screen. Then, the user can type appropriate commands into the console to load, edit, and/or save their .ppm image. Text feedback will be rendered after each user submission. If they submit improper commands, the console will indicate as such. The user can quit the program with case-insensitive "q."

The Command interface is implemented by all commands that are added to the controller in the ImageProcessorControllerImpl's constructor. The interface has the method initCommand(), which applies the appropriate edit to a loaded image, and renderCommandMessage() which is used to render each command's corresponding text feedback to the user.
Many of Command's concrete classes have similar code, which is abstracted in ACommand. Classes ColorComponent, Flip, DarkenBrighten, and IntensityLumaValue all extend the abstract class. Load and Save do not. Inside of each concrete class, initCommand() makes reference to the model's Edit classes to apply the edit/load/save and updates the command message. Concrete classes take in varying inputs to correctly construct the Edits. Once again, similar to Edit, many functionalities are compartmentalized into classes to prevent duplicate code.

**Image Processor/main():  **

The ImageProcessor class exists for the user to actually run the program. The main() method crafts a controller and calls processImage() to satisfy the objective of this assignment.

**Testing: **

All tests for this program all listed in the test folder. We used mocks to test for exceptions in constructors that take in Readables/Appendables. We also made a mock of our model to test that when inputs are given to the controller, it properly receives them. Each class in model, view, and controller and their public methods/constructors are tested for appropriate method functionality and exceptions when appropriate.

**res: **

The res folder contains all .ppm files used for testing and those specified by the assignment brief.

To run our provided script, run main() and type each line into the command line + \n. Each string should be separated by at least one space.

Our selected image, isec.ppm, was taken by us. It is not taken from another source. 
