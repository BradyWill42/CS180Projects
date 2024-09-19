import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.*;

public class ColorMixer {
    
    public static Color[] readFile(String filename) {
        try {
            ArrayList<Color> colors = new ArrayList<Color>();
            int red;
            int green;
            int blue;
            String line;
            Color color;
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            while ((line = br.readLine()) != null) {
                red = Integer.parseInt(line);
                green = Integer.parseInt(br.readLine());
                blue = Integer.parseInt(br.readLine());
                color = new Color(red, green, blue);
                colors.add(color);
            }
            br.close();
            
            return colors.toArray(new Color[0]);
            
        } catch (Exception e) {
            return null;
        }
            
    }
    
    public static boolean writeFile(Color[] colors, String filename) {
        try {
            PrintWriter pw = new PrintWriter(new File(filename));
            for (int i = 0; i < colors.length; i++) {
                pw.println(colors[i]);
            }   
            pw.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileIn;
        String fileOut;
        
        System.out.println("Enter the filename of the color map.");
        fileIn = sc.nextLine();
        
        Color[] colors = readFile(fileIn); 
        
        if (colors != null) {
            System.out.println("Enter the filename to output the colors to.");
            fileOut = sc.nextLine();
            boolean written = writeFile(colors, fileOut);
            if (written) {
                System.out.println("The file was written to!");
            } else {
                System.out.println("There was an error writing to the file.");            
            } 
        } else {
            System.out.println("Either the file doesn't exist or the file is in the wrong format!");   
        }
    }
}