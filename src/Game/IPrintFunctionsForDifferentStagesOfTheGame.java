package Game;

public interface IPrintFunctionsForDifferentStagesOfTheGame {
    void printStartingMessage();
    void printStageName(String stageName) throws InterruptedException;
}
