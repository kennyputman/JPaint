package view.gui;

import model.ShapeType;
import model.interfaces.IShape;
import view.CommandHistory;
import view.interfaces.IUndoable;

import javax.swing.*;
import java.awt.*;

public class PaintCanvas extends JComponent {

    @Override
    public void paint(Graphics g) {

        Graphics2D graphics2d = (Graphics2D) g;

        // Draw all shapes here

        for (IUndoable command : CommandHistory.getUndoStack()) {
            IShape s = command.getShape();

            if (command.getShapeType() == ShapeType.RECTANGLE) {
                graphics2d.setColor(Color.GREEN);
                graphics2d.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());

                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(Color.BLUE);
                graphics2d.drawRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());

                Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
                graphics2d.setStroke(stroke);
                graphics2d.setColor(Color.BLACK);
                graphics2d.drawRect(s.getX() - 5, s.getY() - 5, s.getWidth() + 10, s.getHeight() + 10);
            }

        }

        this.repaint();
        // Once these are set you can use paintCanvas.Repaint


    }
}
