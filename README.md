# JPaint
Kenneth Putman <br>
SE 450 Summer 2023

[Github link](https://github.com/kennyputman/JPaint)

## Missing Features and Known Bugs
No known missing features or bugs.

## Design Patterns
The project uses the following design patterns

#### Abstract Factory Pattern
The creation of shapes is handled by an abstract factory. 
There is an interface `IShapeFactory` which defines methods for creating `IShape` objects.
This interface is implemented by the `ShapeFactory`. The `Rectangle`, `Ellipse`, `Triangle`, and 
`Group` shapes currently implement the `IShape` interface. 

#### Command Pattern
The command pattern is used for the creation of shapes, move commands, select commands, Group Commands, and Copy/Paste
Commands. This is handled by the `CreateShapeCommand`, `MoveCommand`,`SelectCommand`, etc... classes.
This allows for the creation and movement of shapes to implement redo/undo functionality found in the `IUndoable` interface. 

#### Strategy Pattern
The strategy pattern is used to implement the drawing of shapes. This is implemented in the 
`PaintCanvas` class using the `DrawStrategy` interface. This interface is implemented by the 
`DrawEllipse`, `DrawRectangle`, and `DrawTriangle` classes. This allows for the easy addition or
altering of draw functionality for different shapes. 

#### Observer Pattern
The move functionality is handled by the observer pattern. `ShapeStore` implements the `ISubject` interface
and handles the storage of all the shapes. This includes shapes currently displayed, and shapes awaiting (observing)
notifications for move events. When the `MoveCommand` executes it calls the `moveObservers` command 
in the `ShapeStore`, triggering a notification to be sent to all observers to update their x,y coordinates. 
Observers are registered from the select command . Anytime a select command is called to `execute`, 
it clears the observers list, and repopulates it using an axis-aligned bounding box (AABB) collision algorithm. 

#### Composite Pattern
The composite pattern is implemented for groups. The `Group` class and all shapes `Rectangle`,
`Triangle`, `Ellipse`, all implement the common interface to IShape. Any calls to methods in the 
`IShape` interface, such as `Move` work for both the `Group` or any shape classes. There can 
be any `IShape` within the `Group` including other groups. 

#### Singleton Pattern
The singleton pattern in implemented for the `ShapeStore` class. The `ShapeStore` class handles
persistence and management for all shapes including clipboard for copy/paste and the observers
for implementing move functionality. The singleton is implemented with a public accessor `getInstance`
and a private constructor that ensures only a single instance will be created in the application.
