# JPaint
Kenneth Putman <br>
SE 450 Summer 2023

[Github link](https://github.com/kennyputman/JPaint)

## Missing Features and Known Bugs
- Shape selection does not include a visible bounding box.


## Design Patterns
The project uses the following design patterns

#### Abstract Factory Pattern
The creation of shapes is handled by an abstract factory. 
There is an interface `IShapeFactory` which defines methods for creating `IShape` objects.
This interface is implemented by the `ShapeFactory`. The `Rectangle`, `Ellipse`, and `Triangle` shapes
currently implement the `IShape` interface. 

#### Command Pattern
The command pattern is used for the creation of shapes, move commands, and select commands. 
This is handled by the `CreateShapeCommand`, `MoveCommand`, and `SelectCommand` classes.
This allows for the creation and movement of shapes to implement redo/undo functionality found in
the `IUndoable` interface. 

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



