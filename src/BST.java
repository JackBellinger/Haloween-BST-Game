
import java.awt.Color;

public class BST {
    Node root;
    int depth;

    public void BST() {
        depth = 0;
    }
    
    public Node getClickedNode(int mX, int mY, Node current){
        if(current != null){
            if(current.isInside(mX, mY))
                return current;
            Node n = getClickedNode(mX, mY, current.left);
            if(n != null)
                return n;
            n = getClickedNode(mX, mY, current.right);
            if(n != null)
                return n;
        }
        return null;      
    }
    public boolean add(int v){
        //System.out.println(v);
        if(root == null){
            root = new Node(v);
        }
        else{
            Node current = root;
            while(true){
                if(v < current.value){
                    if(current.left == null){
                        //System.out.println(v + " added left");
                        current.left = new Node(v);
                        return true;
                    }else
                    current = current.left;
                }
                if(v > current.value){
                    if(current.right == null){
                        //System.out.println(v + " added left");
                        current.right = new Node(v);
                        return true;
                    }else
                        current = current.right;
                }
                else if(v == current.value){
                    return false;
                }
            }
        }
        return true;
    }
    public void assignCoordinates(double offsetX, double offsetY) {
        assignCoordinatesRec(root, Main.sg.getWidth()/2, 50, offsetX, offsetY);
    }

    private void assignCoordinatesRec(Node n, double px, double py, double offX, double offY) {
        if (n == null) {
            return;
        } else {
            n.x = px;
            n.y = py;
            assignCoordinatesRec(n.left, n.x - offX, n.y + offY, offX / 2.0, offY);
            assignCoordinatesRec(n.right, n.x + offX, n.y + offY, offX / 2.0, offY);
        }
    }
    public void visualize() {
        visRec(root, null);
    }

    private void visRec(Node n, Node parent){
        if (n==null){
            return;
        } else {
            int offset = Main.sg.getWidth()/2 + n.size/2;
            if (parent != null){
                Main.sg.drawLine(n.x, n.y, parent.x, parent.y+parent.size, Color.orange, 1.0, 0, null);
            }
            n.visualize();
            visRec(n.left, n);
            visRec(n.right, n);
        }
    }

    public void printTree(Node current){
        if(current != null){
            if(current.left != null)
                printTree(current.left);
            System.out.println(current.value);
            if(current.right != null)
                printTree(current.right);
        }
    }
}
