# JPaint
Kenneth Putman <br>
SE 450 Summer 2023

[Github link](https://github.com/kennyputman/JPaint)

## Design Patterns
The project uses the following design patterns

#### Abstract Factory Pattern
The creation of shapes is handled by an abstract factory. 
There is an interface `IShapeFactory` which defines methods for creating `IShape` objects.
This interface is implemented by the `ShapeFactory`. Different shapes such as `Rectangle`
implement the `IShape` interface. 

#### Command Pattern
The creation of shapes is handled by a `CreateShapeCommand` class. This allows for undo/redo
behavior by adding each `CreateShapeCommand` to the `CommandHistory` class. 


