package montecarlosimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Adrian
 */
public class MonteCarloSimulation extends JFrame{
  int number;
       public MonteCarloSimulation(int pointNumber) {
        super("MonteCarlo Simulation");
        
        number= pointNumber;
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel();
        container.setLayout(null);
        this.add(container);
      
        JSlider slider = new JSlider(0, 60000);
        slider.setValue(number);
        container.add(slider);
        slider.setBounds(120, 30, 200, 50);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(4000);
        slider.setMajorTickSpacing(20000);
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(20000));
        slider.addChangeListener((ChangeEvent e) -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                int points = (int)source.getValue();
                    new MonteCarloSimulation(points).setVisible(true);    
                    this.dispose();
            }
        });  
        this.setLocationRelativeTo(null);
    }
 
    void drawSimulation(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
 
        Point2D.Double p1 = new Point2D.Double(0, 350);
        Point2D.Double p2 = new Point2D.Double(700, 350);
        
        Point2D.Double p3 = new Point2D.Double(350, 0);
        Point2D.Double p4 = new Point2D.Double(350, 700);
        
        g2d.draw(new Line2D.Double(p1,p2));
        g2d.draw(new Line2D.Double(p3,p4));
   
        g2d.drawString("-1", 135, 345);
        g2d.drawString("1", 555, 345);
        g2d.drawString("1", 355, 140);
        g2d.drawString("-1", 355, 560);
        
        g2d.draw(new Rectangle2D.Double(150,150, 400,400));
        g2d.draw(new Ellipse2D.Double(150,150,400,400));
        
        String N = "Points: "+number;
        g2d.drawString(N, 50, 75);
        
        int n=number;
        int K = points(g2d, n);
       
        g2d.setColor(Color.BLACK);
        double pi = (double)(4*K)/n;
        if(pi>0)
            g2d.drawString("pi= " + String.valueOf(pi).substring(0, 5), 50, 135);
       
        String inside = "In circle= "+K;  
        g2d.drawString(inside, 50, 120);
        
    }
    
    public int points(Graphics2D g, int n){
        Random rand = new Random();
        int total = 0;
        double x;
        double y;
        for(int i=n; i>0; i--){
            x = rand.nextInt(400)+150;
            y = rand.nextInt(400)+150;
            double disc = Math.sqrt(Math.pow((x-350), 2) + Math.pow((y-350), 2));
            if(disc<=200){
                g.setColor(Color.red);
                total++;
            }
            else
                g.setColor(Color.blue);
            g.draw(new Ellipse2D.Double(x,y,1,1));
        }
        return total;
    }
 
       @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawSimulation(g);
    }
    
    public static void main(String[] args) {
        int pointsNumber = 0;
        SwingUtilities.invokeLater(() -> {
            new MonteCarloSimulation(pointsNumber).setVisible(true);
        });
    }  
}
