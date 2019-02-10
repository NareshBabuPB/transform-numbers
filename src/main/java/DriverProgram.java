import com.naresh.sonatype.NumberToWords;

import java.util.Scanner;

public class DriverProgram {

    public static void main(String[] args) {
        while(true) {
            System.out.println("Enter a numerical value to transform or any other input to quit.");

            Scanner sc = new Scanner(System.in);
            if(!sc.hasNextLong()) {
                System.out.println("Terminating program...");
                sc.close();
                System.exit(0);
            }

            Long input = sc.nextLong();

            String output = NumberToWords.transform(input);

            System.out.println(output);
        }
    }
}
