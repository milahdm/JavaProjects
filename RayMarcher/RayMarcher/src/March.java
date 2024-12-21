import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

public class March implements Drawable
{
    private int p1_X;
    private int p1_Y;
    private int p2_X;
    private int p2_Y;

    public March(int p1_X, int p1_Y, int p2_X, int p2_Y) {
        this.p1_X = p1_X;
        this.p1_Y = p1_Y;
        this.p2_X = p2_X;
        this.p2_Y = p2_Y;

    }

    public int getP1_X() {
        return p1_X;
    }

    public void setP1_X(int p1_X) {
        this.p1_X = p1_X;
    }

    public int getP1_Y() {
        return p1_Y;
    }

    public void setP1_Y(int p1_Y) {
        this.p1_Y = p1_Y;
    }

    public int getP2_X() {
        return p2_X;
    }

    public void setP2_X(int p2_X) {
        this.p2_X = p2_X;
    }

    public int getP2_Y() {
        return p2_Y;
    }

    public void setP2_Y(int p2_Y) {
        this.p2_Y = p2_Y;
    }

    @Override
    public void drawObject(Graphics2D g2d) {
        double radius = Point2D.distance(p1_X, p1_Y, p2_X, p2_Y);
        g2d.drawOval((int)(p1_X - radius), (int)(p1_Y - radius), (int) radius * 2, (int) radius * 2);
        g2d.drawLine(p1_X, p1_Y, p2_X, p2_Y);


    }


}
