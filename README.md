# CWProject

This is a drawing system that renders graphics to the scalable vector graphics (SVG) file format created as part of "OBJECT ORIENTED PROGRAMMING" module at uni

Brief:
For this project you will finish implementing a drawing system that renders graphics to the scalable vector graphics (SVG) file format. The class diagram of the entire system is as follows:
![image](https://github.com/user-attachments/assets/33f0ecf3-5b32-4f9f-9709-441b16edc877)
Some of the classes are completed, some are partially completed and some have not been started. It is up to you to get the provided project (see below) to compile and run successfully.

Ultimately, your program must execute two tasks:

1) Replicate the file sketchTestTruth.svg provided in the root directory.

2) Produce a file sketchCustom.svg with custom shapes from a sketch file sketchCustom.txt.


Sketch
The topmost level class is Sketch. It stores a collection of Shapes and is able read a plain-text "sketch" file of high-level shape descriptions (sketch file) and write to an SVG file. The shape descriptions start with the shape name and are followed by its generating parameters.


    Shape    Parameters
    .....................................................
    circle   radius center_x center_y
    arc      radius arc_angle arc_length center_x center_y
    line     start_x start_y end_x end_y
    rect     width height center_x center_y
    square   width center_x center_y
    ngon     sides radius center_x center_y

Following the shape parameters are three optional values: stroke width, stroke color and fill color. Colors are specified as a 4-byte integer in hexadecimal (base 16) encoding RGBA values. For example, the colors red, green and blue are specified as FF0000FF, 00FF00FF and 0000FFFF, respectively. The final value is alpha where FF is fully opaque and 00 is fully transparent. The parser should use an intelligent approach to parse the optional values if only a subset of them are given. For example, if only one hexadecimal value is provided, it should set the stroke color. If only one float value is given, it should set the stroke width.

The position values given in the file are relative to the SVG viewbox. They should lie in the range [-100, 100] where (-100,-100) and (100,100) correspond to the bottom-left and top-right corners, respectively, of the SVG viewbox.

Lines starting with a '#' character are comments and should be ignored.

Lines consisting of whitespace should be ignored.
