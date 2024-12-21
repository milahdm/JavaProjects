import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class RectangleObject extends CollisionObject
{
    private final int width;
    private final int height;

    public RectangleObject(double x, double y, int width, int height)
    {
        super(x, y);
        this.width = width;
        this.height = height;

    }


    @Override
    public void drawObject(Graphics2D g2d) {
        super.drawObject(g2d);
        Rectangle2D.Double rectangle = new Rectangle2D.Double(getX() - width / 2.0, getY() - height / 2.0, width, height);
        g2d.fill(rectangle);

    }

    @Override
    public double computeDistance(double cameraX, double cameraY) {
        double left = getX() - width / 2.0;
        double right = getX() + width / 2.0;
        double top = getY() - height / 2.0;
        double bottom = getY() + height / 2.0;

        double l1 = Line2D.ptSegDist(left, top, right, top, cameraX, cameraY);
        double l2 = Line2D.ptSegDist(right, top, right, bottom, cameraX, cameraY);
        double l3 = Line2D.ptSegDist(right, bottom, left, bottom, cameraX, cameraY);
        double l4 = Line2D.ptSegDist(left, bottom, left, top, cameraX, cameraY);

        return Math.min(Math.min(l1, l2), Math.min(l3, l4));


    }
}
