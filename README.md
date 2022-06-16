# A5 Image Processor 

## Completed Parts of the Program: 

- `ImageProcessor` class
  - contains `main()`

### Model:

- `ImageModel` interface and implementing class, `ImageModelImpl`
- `ImageModelState` interface and implementing class, `ImageModelStateImpl`
- `Edit` interface, implementing abstract class, `AEdit`, and all extending concrete classes
  - concrete classes: 
    - `BrightenDarken`, `ColorComponent`, `FilterSharpenBlur`, `FlipHorizontal`, `FlipVertical`, `ILV`, `Transform`
- `Pixel` class

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

### Testing :
- Testing classes:
  - `ImageModelImplTest`, `ImageModelStateImplTest`, `EditTest`, `ImageProcessorControllerImplTest`, `ImageProcessorTextViewTest`
- Mocks used:
  - `AppendableMock`, `ImageModelMock`, `ImageProcessorViewMock`, `ReadableMock`
  
### res: 
- jar file, `Group.jar`
- sample script that showcases all commands/functionality, `script.txt`
- updated class diagram 
- pink . png, jpg, bmp, and ppm as its original, blur, sharpen, greyscale, and sepia versions

## Design Changes: 

- We altered the construction of our model so that instead of a 3-D array of integers to represent [row][column][r,g,b] of an image, we are using a 2-D array of `Pixel`s
  - To implement this change, we created the Pixel class, its methods, and refactored our existing code so that each `int[][][]` instead was `Pixel[][]`
  - We chose to change our design in this way to increase efficiency when processing commands. Before, large files took too long to load/edit because the loops running the methods were taking n^3 time. With `Pixel`, the loops only take n^2.

- In model's `Edit`, we abstracted `FlipHorizontal` and `FlipVertical` to reduce repeated code, as pointed our in our A4 feedback. 
  - the replacement class, again extending `AEdit`, is `FlipHZ`, which takes in a String to dictate flip direction. 
  - We also refactored any uses of this class in the test and other classes to fit the change. 