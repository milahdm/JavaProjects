import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Camera implements Drawable, MouseMotionListener, MouseListener
{

    private int radius;
    private double x;
    private double y;
    private double angle;

    public Camera(double x, double y, int radius, double angle)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void drawObject(Graphics2D g2d) {
        g2d.setColor(Color.PINK);
        g2d.fillOval((int)getX() - radius, (int)getY() - radius, radius * 2, radius * 2);

        double x2 = x + radius * Math.cos(angle);
        double y2 = y + radius * Math.sin(angle);
        g2d.drawLine((int)x, (int)y, (int)x2, (int)y2);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1) {
            angle++;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            angle--;
        }

        e.getComponent().repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
