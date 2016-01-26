
import java.awt.Color;
import simplegui.DrwImage;

public class Node {
    Node left;
    Node right;
    int value;
    double x;
    double y;
    int size;
    DrwImage pumpkin = new DrwImage("pumpkin.png");
    
    public Node(int v){
        value = v;
        x = 0;
        y = 0;
        size = 30;
    }
    
    public void visualize(){
        Main.sg.drawImage(pumpkin, x - (size / 2), y, size, size, null);
        //Main.sg.drawDot(x, y, size, Color.orange, 1.0, null);
    }
    public boolean isInside(int mX, int mY){
        boolean a = mX > x - size; 
        boolean b = mX < x + size;
        boolean c = mY > y - size; 
        boolean d = mY < y + size;
        
        return (a && b && c && d);
    }

    
    
}
