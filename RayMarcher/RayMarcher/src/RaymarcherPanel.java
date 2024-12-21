import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Displays and updates the logic for the top-down raymarcher.
 */
public class RaymarcherPanel extends JPanel {
    
    /**
     * We need to keep a reference to the parent swing app for sizing and 
     * other bookkeeping.
     */
    private final RaymarcherRunner raymarcherRunner;

    private final Camera camera;

    private List<CollisionObject> objects;

    private ArrayList<March> marches;

    
    public RaymarcherPanel(RaymarcherRunner raymarcherRunner) {
        this.raymarcherRunner = raymarcherRunner;
        this.setPreferredSize(new Dimension(this.raymarcherRunner.getFrame().getWidth(),
                this.raymarcherRunner.getFrame().getHeight()));
        this.createObjects();
        this.camera = new Camera(30.0, 75.0, 10, 20.0);
        addMouseMotionListener(this.camera);
        addMouseListener(this.camera);

        this.marches = new ArrayList<March>();

        this.raymarcherRunner.getFrame().setBackground(Color.black);

    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (CollisionObject object : objects)
        {
            object.drawObject(g2d);
        }

        double start_X = camera.getX();
        double start_Y = camera.getY();

        double minDistance;
        double radius;
        do
        {
            minDistance = Double.MAX_VALUE;
            for (CollisionObject object : objects)
            {
                minDistance = Math.min(minDistance, object.computeDistance(start_X, start_Y));
            }

            radius = 2.0 * minDistance;
            March march = new March((int)start_X, (int)start_Y, (int)(start_X + minDistance), (int)start_Y);
            //march1.drawObject(g2d);
            marches.add(march);
            double angle = Math.toRadians(camera.getAngle());
            double next_X = start_X + minDistance * Math.cos(angle);
            double next_Y = start_Y + minDistance * Math.sin(angle);

            start_X = next_X;
            start_Y = next_Y;

            // start_X = march.getP1_X() + minDistance * Math.cos(Math.toRadians(camera.getAngle()));
            // start_Y = march.getP1_Y() + minDistance * Math.sin(Math.toRadians(camera.getAngle()));
//            start_X = march.getP2_X();
//            start_Y = march.getP2_Y();
        } while(minDistance > 1.0 && (start_X + radius < 1280));

        for (March m: marches)
        {
            m.drawObject(g2d);

        }
        camera.drawObject(g2d);


        marches.clear();
    }

//    private ArrayList<March> march()
//    {
//        ArrayList<March> marches = new ArrayList<March>();
//        double start_X = camera.getX();
//        double start_Y = camera.getY();
//
//        double end_X;
//        double end_Y;
//
//        double minDistance;
//        double radius;
//        do
//        {
//            minDistance = Double.MAX_VALUE;
//            for (CollisionObject object : objects)
//            {
//                minDistance = Math.min(minDistance, object.computeDistance(start_X, start_Y));
//            }
//
//            radius = 2.0 * minDistance;
//            March march = new March((int)start_X, (int)start_Y, (int)(start_X + minDistance), (int)start_Y);
//            //march1.drawObject(g2d);
//            marches.add(march);
//
//            end_X = march.getP1_X() + minDistance * Math.cos(Math.toRadians(camera.getAngle()));
//            end_Y = march.getP1_Y() + minDistance * Math.sin(Math.toRadians(camera.getAngle()));
//            start_X = march.getP1_X();
//            start_Y = march.getP1_Y();
//        } while(minDistance > 1.0 && (start_X + radius < 1280));
//
//        return marches;
//    }

    private void createObjects(){
        objects = new ArrayList<>(); // array list of collision objects

        // minimum values that the shapes can be, hardcoded my own values
        int minWidth = 15;
        int maxWidth = 300;
        int minHeight = 15;
        int maxHeight = 300;

        // loop to create 15 objects
        for (int i = 0; i < 15; i++)
        {
            int type = (int) (Math.random() * 2) + 1; // determines shape(either 1 or 2)
            int width = (int) (Math.random() * (maxWidth - minWidth + 1)) + minWidth;
            int height = (int) (Math.random() * (maxHeight - minHeight + 1)) + minHeight;

            int x = (int) (Math.random() * this.getPreferredSize().width);
            int y = (int) (Math.random() * this.getPreferredSize().height);

            // adding objects based on random num generated
            if (type == 1)
            {
                objects.add(new RectangleObject(x, y, width, height));
            }
            else
            {
                int radius;
                if (width < height)
                {
                    radius = width / 2;
                }
                else
                {
                    radius = height / 2;
                }
                objects.add(new CircleObject(x, y, radius));
            }
        }
    }
}
