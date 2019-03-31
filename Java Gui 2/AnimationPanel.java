import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnimationPanel extends JPanel implements Runnable {
    private ArrayList<Ball> arrayListOfBallObjects;
    Dimension dimension;
    Thread animationThread;

    public AnimationPanel() {
        super();
        setLayout(new GridLayout());
        setPreferredSize(new Dimension(300, 500));
        arrayListOfBallObjects = new ArrayList<>(); // array list of all objects is equal to null 
        dimension = null;
        animationThread = null;
    }

    public void start() {
        
        if (animationThread == null)  {
            animationThread = new Thread(this);
            animationThread.start();
        }


    }

    public void stop() {
        
        animationThread.interrupt();
        animationThread = null;
    }

   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 			
        System.out.println("paint component running");
    Dimension size = getSize();
       if (size==null) {
           setSize(new Dimension(300,500));
       }

        
        if (dimension == null) {
            dimension = getSize();
            Ball ball1 = new Ball(Color.cyan, 20, (dimension.width * 4/3), (dimension.height - 10), -2, -4);	// ball 1 is color cyan 
            Ball ball2 = new Ball(Color.black, 20, 0, (dimension.height - 1), -2, -4);							// ball 2 is color black
            Ball ball3 = new Ball(Color.orange, 20, (dimension.width * 1), (dimension.height - 45), -2, -4);	// ball 3 is color orange 
            arrayListOfBallObjects.add(ball1);
            arrayListOfBallObjects.add(ball2);
            arrayListOfBallObjects.add(ball3);
        }
      
       
            dimension = new Dimension(300,500);			//  Dimension = getSize()

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, dimension.width, dimension.height);	// dimension width and height 

        for (Ball b : arrayListOfBallObjects) {
            b.move(dimension);
            b.draw(g);

        }
    }
    public void run() {


  

        System.out.println("run running");

        while (Thread.currentThread() == animationThread) {
            try {
                animationThread.sleep(25);
            } catch (InterruptedException e) {
                return;
            }
       
            repaint();
        }
    }

}
