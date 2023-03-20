package Game;

public class PrintFunctionsForDifferentStagesOfTheGame {
    static void printStartingMessage(){

        System.out.println("Hello! Welcome to the FootballCup game!");
        System.out.println("Minimum players size: 2");
        System.out.println("Maximum players size: 6");
        System.out.println("Please type how many players are going to play: ");
    }

    static void printStageName(String stageName) throws InterruptedException {

        System.out.println("\n\n");
        String oneRowWithLines = "+--------------------------------" + "-".repeat(stageName.length()) + "+";
        System.out.println(oneRowWithLines);

        String rowForName = "|                " + stageName + "                |" ;
        System.out.println(rowForName);
        System.out.println(oneRowWithLines);
        System.out.println("\n\n");
        Thread.sleep(3000);
    }
}
