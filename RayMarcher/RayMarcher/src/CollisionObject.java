import java.awt.*;

public abstract class CollisionObject implements Drawable
{
    private double x;
    private double y;

    private Color c;
    private static final Color[] colors = {Color.BLUE, Color.white, Color.MAGENTA, Color.CYAN, Color.RED, Color.DARK_GRAY, Color.GREEN, Color.YELLOW};

    public CollisionObject(double x, double y)
    {
        this.y = y;
        this.x = x;
        this.c = colors[(int) (Math.random() * colors.length)];

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x < 0)
        {
            x = 0;
            throw new IllegalArgumentException("x cannot be negative");
        }
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (y < 0)
        {
            y = 0;
            throw new IllegalArgumentException("y cannot be negative");
        }
        this.y = y;
    }

    @Override
    public void drawObject(Graphics2D g2d) {
        g2d.setColor(this.c);
    }

    public abstract double computeDistance(double cameraX, double cameraY);

}
