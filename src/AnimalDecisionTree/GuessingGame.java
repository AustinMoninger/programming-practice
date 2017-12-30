import java.io.FileNotFoundException;
import TreePackage.DecisionTree;

/**
 * This class manages a guessing game that is launched by the Client class.
 */
public class GuessingGame {
	// Tree stores questions and answers for guessing game
	private DecisionTree tree;
    
	/**
	 * Try loading the decision tree from the specified file.
	 * @param filename File storing saved DecisionTree.
	 * @throws FileNotFoundException If file does not exist.
	 */
    public GuessingGame(String filename) throws FileNotFoundException {
    	tree = DecisionTree.loadTree(filename);
    }
    
    /**
     * Used when no previously saved file exists. A shallow tree
     * with a single question and two answers (one for no and one for yes)
     * is created.
     * 
     * @param question First question to ask in game.
     * @param noAnswer Answer if user response to question is no
     * @param yesAnswer Answer if user response to question is yes
     */
    public GuessingGame (String question, String noAnswer, String yesAnswer) {
        DecisionTree no = new DecisionTree(noAnswer);
        DecisionTree yes = new DecisionTree(yesAnswer);
        tree = new DecisionTree(question, no, yes);
    } 

    /**
     * Play the guessing game once, and learn from the user if
     * the computer loses.
     */
    public void play () {
        tree.reset();
        while (!tree.isAnswer()) {
            // ask current question
            System.out.println (tree.getCurrentData());
            if (Client.isUserResponseYes())
                tree.advanceToYes();
            else
                tree.advanceToNo();
        } 
        assert tree.isAnswer(); // Assertion: leaf is reached
        // make guess
        System.out.println ("My guess is " + tree.getCurrentData() + ". Am I right?");
        if (Client.isUserResponseYes())
            System.out.println("I win.");
        else
            learn();
    } 

    /**
     * Method that learns from the user.
     * The user is first asked what s/he was thinking of,
     * and is then asked to provide a yes/no question 
     * that will distinguish what the user was thinking
     * of from the incorrect answer provided by the decision
     * tree. This information is then placed
     * in the decision tree.
     */
    private void learn() {
    	// the animal the game learns
    	String userAnswer = Client.stringQuery("What were you thinking of?"); 
    	// the game's guess
    	String wrongAnswer = tree.getCurrentData(); 
    	// difference between the game's guess and the animal it learns
    	String newQuestion = Client.stringQuery("What is a yes or no question that distinguishes a(n) " + 
    										userAnswer + " from " + wrongAnswer + "?"); 
    	
    	tree.setCurrentData(newQuestion);
    	tree.setAnswers(wrongAnswer, userAnswer);
    } 
    
    public void save(String filename) {
    	System.out.println("Saving file: " + filename);
    	tree.saveTree(filename);
    }
    
} 