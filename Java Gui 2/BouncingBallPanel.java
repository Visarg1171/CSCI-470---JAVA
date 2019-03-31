import javafx.scene.paint.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingBallPanel extends JPanel {

    Dimension buttonSize = new Dimension(100, 30);
    JButton startButton;
    JButton stopButton;

    public AnimationPanel BallAnimationPanel;

    public BouncingBallPanel() {
        BallAnimationPanel = new AnimationPanel();
        BallAnimationPanel.setPreferredSize(new Dimension(300,500));
        BallAnimationPanel.setLayout(new GridLayout());
        setBounds(new Rectangle(100,100));
        startButton = new JButton("Start");
        startButton.setPreferredSize(buttonSize);
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(buttonSize);
        stopButton.setEnabled(false);

        setVisible(true);
       startButton.addActionListener(e -> {
            startAnimation(); 			// It begin the animation 
            startButton.setEnabled(false); // It enable the false button 
            stopButton.setEnabled(true);   // it enable the true button 
           actionPerformed(e);});
        startButton.addActionListener(e -> {
                startAnimation(); 		// It start the animation 
            startButton.setEnabled(false); 
            stopButton.setEnabled(true); 
            });

        stopButton.addActionListener(e -> {
            stopAnimation(); 
            startButton.setEnabled(true); 
            stopButton.setEnabled(false); 
            });
           ActionEvent e;
           
        startButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                startAnimation();
                BallAnimationPanel.start();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });


        stopButton.addActionListener(new ActionListener() {
       
            public void actionPerformed(ActionEvent e) {
                stopAnimation(); 
               stopButton.setEnabled(false);  // stop it
                startButton.setEnabled(true); // Start it
            }
        });
    add(BallAnimationPanel);
    }

    private void actionPerformed(ActionEvent e) {
	}

	public void startAnimation() {

        BallAnimationPanel.start();
    }

    public void actionPerformed1(ActionEvent cmd){
        if(cmd.equals("Start")){
            startAnimation();
            BallAnimationPanel.start();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
       } else if (cmd.equals("Stop")){
            stopAnimation();
            BallAnimationPanel.stop();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    public void stopAnimation() {
        BallAnimationPanel.stop();
    }

    public JButton getstartButton() {
        return startButton;
    }

    public JButton getstopButton() {
        return stopButton;
    }

    public AnimationPanel getAnimationPanel() { return BallAnimationPanel; }
}
