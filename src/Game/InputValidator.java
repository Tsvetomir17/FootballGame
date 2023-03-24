package Game;

import java.util.Scanner;

public class InputValidator {

    private static final InputValidator instance = new InputValidator();

    public static InputValidator getInstance() {
        return instance;
    }

    public static int choiceMadeByTheUserValidation(int minimumValue, int maximumValue){

        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        while (choice < minimumValue || choice > maximumValue) {

            System.out.println("Bad input! Try again: ");
            choice = scanner.nextInt();
        }

        return choice;
    }
}
