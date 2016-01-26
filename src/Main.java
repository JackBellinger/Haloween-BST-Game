
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import simplegui.DrwImage;
import simplegui.SimpleGUI;
import simplesound.SoundPlayer;

public class Main {
    static SimpleGUI sg = new SimpleGUI(false);
    static SoundPlayer sp = new SoundPlayer();
    static BST tree;
    static int[] original;
    
    public static void main(String[] args) {
        sg.setTitle("Spooky BST Game");
        sp.addSound("welcome.wav", "welcome");
        sp.addSound("evillaugh4.wav", "laugh");
        sp.addSound("crow.wav", "crow");
        sp.addSound("yay.wav", "yay");
        DrwImage jason = new DrwImage("jason.png");
        DrwImage ghosts = new DrwImage("ghosts.png");
        DrwImage sign = new DrwImage("sign.png");
        
        while(true){
            sg.setBackgroundColor(Color.BLACK);
            sg.drawImage(ghosts, 0, sg.getHeight()-100, 100, 100, null);
            sp.play("welcome");
            JFrame frame = new JFrame("");
            String stringInput = JOptionPane.showInputDialog(frame, "How many nodes do you want", "BST Game", JOptionPane.WARNING_MESSAGE);
            tree = new BST();
            int num = Integer.parseInt(stringInput);
            original = new int[num];
            addRandos(num);
            tree.assignCoordinates(120, 50);
            int[] mCoords = {0, 0};
            int[] shuffled = swapRandos();
            boolean lost = false;
            tree.visualize();
            sg.drawText("The spooky tree contains these values: " + arrayString(shuffled), 2, 20, Color.orange, 1.0, null);
            for(int i = 0; (i < original.length)&&!lost; i++){
                sg.drawText("Find where " + shuffled[i] + " goes in the tree to escape!", 2, 40, Color.orange, 1.0, "numToClick");
                mCoords = sg.waitForMouseClick();
                Node n = tree.getClickedNode(mCoords[0], mCoords[1], tree.root);
                if(n != null){
                    if(n.value == shuffled[i]){
                        sg.drawText(""+n.value, n.x - 3, n.y + 20, Color.black, 1.0, null);
                        sg.eraseSingleDrawable("numToClick");
                        sp.play("crow", 0, true);
                    }
                    else
                        lost = true;
                }else
                    lost = true;
            }
            sp.stopAllSounds();
            sg.eraseAllDrawables();
            //sg.drawFilledBox(sg.getHeight()/2 - 20, sg.getWidth()/2 - 20, 100, 30, Color.WHITE, 1.0, null);
            //sg.drawText(lost?"Loser":"Winner", sg.getHeight()/2, sg.getWidth()/2, lost?Color.red:Color.GREEN, 1.0, null);
            sp.play(lost?"laugh":"yay");
            sg.drawImage(lost?jason:sign, 0, 0, sg.getWidth(), sg.getHeight(), null);
            sg.waitForMouseClick();
            sg.eraseAllDrawables();
        }
    }
    
    public static void addRandos(int n){
        for(int i = 0; i < n; i++){
            int r = (int)(Math.random()*(n*n/2));
            if(tree.add(r))
                original[i] = r;
            else
                i--;
        }
    }
    
    public static int[] swapRandos(){
        int[] newArray = original;
        for(int i = 0; i < 500; i++){
            int r1 = (int)(Math.random() * newArray.length);//random.nextInt(array.length)
            int r2 = (int)(Math.random() * newArray.length);
            int temp = newArray[r1];
            newArray[r1] = newArray[r2];
            newArray[r2] = temp;
        }
        return newArray;
    }
    public static String arrayString(int[] array){
        String s = "" + array[0];
        for(int i = 1; i < array.length; i++)
            s += ", " + array[i];
        return s;
    }
}
