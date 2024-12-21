import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class CircleObject extends CollisionObject
{
   private final int radius;

   public CircleObject(double x, double y, int radius)
   {
      super(x, y);
      this.radius = radius;
   }

   @Override
   public void drawObject(Graphics2D g2d) {
      super.drawObject(g2d);
      g2d.fillOval((int)getX() - radius, (int)getY() - radius, radius * 2, radius * 2);
   }

   @Override
   public double computeDistance(double cameraX, double cameraY) {
      double dx = cameraX - getX();
      double dy = cameraY - getY();
      double distance = Math.sqrt(dx*dx + dy*dy) - radius;
      //System.out.println(distance);
      return distance;

   }
}
