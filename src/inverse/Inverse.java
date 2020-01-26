package inverse;

import java.util.Scanner;

/**
 * Created by acer on 13-Jan-18.
 */
public class Inverse {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double y,x,z;
        while (true) {
            System.out.println("Y = cos(x) and y=sin(x) ; Find x using y: Enter Y?");
            y = scanner.nextDouble();
            x = Math.acos(y)*630/11;
            z = Math.asin(y)*630/11;
            System.out.println(x+"\t"+z+"\t"+(x+z)+"\t"+(90-(x+z)));
        }
    }
}
