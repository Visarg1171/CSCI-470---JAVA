import java.awt.*;

public class Ball {
    private Color color;  	//color for the ball 
    private int radius;		 // radius as int 
    private int x,y; 
    private int dx, dy; // int as derivative x and y

   
    public Ball(){

    }

    public Ball(Color c, int r, int x, int y, int dx, int dy){
        color = c;
        radius = r;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void move(Dimension d){
        if( x <= radius || d.width <= x )   
            dx *= -1;

        if( y <= radius || d.height <= y)
            dy *= -1;
        x += dx;
        y += dy;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x,y,radius * 2, radius * 2);
    }
}
