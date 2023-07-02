package view.gui;

import model.interfaces.IShape;
import view.CommandHistory;
import view.IUndoable;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PaintCanvas extends JComponent {

    @Override
    public void paint(Graphics g) {

        Graphics2D graphics2d = (Graphics2D)g;

        // Draw all shapes here

        for (IUndoable command: CommandHistory.getUndoStack()){
            IShape s = command.getShape();
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

        // Once these are set you can use paintCanvas.Repaint


    }
}
