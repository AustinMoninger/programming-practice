package TreePackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class is a binary tree in which each internal
 * node is a question and each leaf node is an answer.
 * When traversing the tree, going left indicates a
 * response of No to the question in that node, and
 * going right indicates a response of Yes. In addition
 * to maintaining the tree, there is also a current node
 * used for traversing the tree.
 */
public class DecisionTree extends BinaryTree<String> implements DecisionTreeInterface<String> {

	// Represents current point in the decision process
	private BinaryNode<String> current;

	/**
	 * Create new tree with root node containing specified text with no children.
	 * @param text Contents of node.
	 */
	public DecisionTree(String text) {
		super(text);
		// Current node starts at root of tree
		current = this.getRootNode();
	}

	/**
	 * Create new tree with given question contained in root node, and
	 * provided left (no) and right (yes) subtrees.
	 * @param question String data in root node
	 * @param no Left subtree to traverse if answer to question is no
	 * @param yes Right subtree to traverse if answer to question is yes
	 */
	public DecisionTree(String question, DecisionTree no, DecisionTree yes) {
		super(question, no, yes);
		// Current node starts at root of tree
		current = this.getRootNode();
	}

	/** 
	 * Gets the data in the current node.
     * @return the data object in the current node, or
     *  	   null if the current node is null 
     */
	@Override
	public String getCurrentData() {
		return current.getData();
	}

	/** 
	 * Sets the data in the current node.
     * Precondition: The current node is not null.
     * @param newData the new data object 
     */
	@Override
	public void setCurrentData(String newData) {
		current.setData(newData);
	}

	/** 
	 * Sets the data in the children of the current node,
     * creating them if they do not exist.
     * Precondition: The current node is not null.
     * @param answerForNo the new data object for the left child
     * @param answerForYes the new data object for the right child 
     */
	@Override
	public void setAnswers(String answerForNo, String answerForYes) {
		current.setLeftChild(new BinaryNode<>(answerForNo));
		current.setRightChild(new BinaryNode<>(answerForYes));
	}

	/** 
	 * Sees whether the current node contains an answer.
     * @return true if the current node is a leaf, or
     * false if it is a nonleaf 
     */
	@Override
	public boolean isAnswer() {
		return current.hasRightChild() || current.hasLeftChild();
	}

	/** 
	 * Sets the current node to its left child.
     * If the child does not exist, sets the current node to null.
     * Precondition: The current node is not null. 
     */
	@Override
	public void advanceToNo() {
		current = current.getLeftChild();
	}

	/** Sets the current node to its right child.
     * If the child does not exist, sets the current node to null.
     * Precondition: The current node is not null. 
     */
	@Override
	public void advanceToYes() {
		current = current.getRightChild();
	}

	/** 
	 * Makes the root of the tree the current node.
	 */
	@Override
	public void reset() {
		current = this.getRootNode();
	}

	/**
	 * Method saves the tree in a file with the specified filename.
	 * @param filename Name of file to save tree to
	 */
	public void saveTree(String filename) {
		try { // Try saving tree
			saveTree(new File(filename));
		} catch (FileNotFoundException e) {
			// Exit if there is an exception
			System.out.println("Could not save to file: " + filename);
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Kick-off for the recursive method that actually saves the tree.
	 * The root node of the tree is sent to the recursive method so that
	 * the tree can be output to file with a pre-order traversal.
	 * 
	 * @param saveFile An instance of File to save the tree to
	 * @throws FileNotFoundException If file cannot be created
	 */
	private void saveTree(File saveFile) throws FileNotFoundException {
		saveTree(new PrintStream(saveFile), getRootNode());
	}

	/**
	 * This method takes in a binary node and writes its contents,
	 * along with the contents of all descendant nodes, to a file
	 * (represented by a print stream) in pre-order. In other words,
	 * the contents of this node are written to one line of the file,
	 * then the contents of the left subtree are recursively written,
	 * followed by the contents of the right subtree being recursively 
	 * written. Whenever the binary node is null, the word "NULL"
	 * should be written to the file. This means that two lines of "NULL"
	 * will appear in the file whenever a leaf is reached, which will 
	 * allow the data to be parsed when reading it back in from file.
	 * 
	 * @param saveStream A PrintStream linked to a save file.
	 * @param current Node in the binary tree.
	 */
	private void saveTree(PrintStream saveStream, BinaryNode<String> current) {
		if(current == null) 
			saveStream.println("NULL");
		else {
			// pre-order traversal: root, left, right
			saveStream.println(current.getData());
			saveTree(saveStream, current.getLeftChild()); 
			saveTree(saveStream, current.getRightChild()); 
		}
	}

	/**
	 * Static method that creates a new instance of DecisionTree filled with
	 * data from the specified input file.
	 * 
	 * @param filename Name of file containing saved DecisionTree output.
	 * @return DecisionTree filled with data from file.
	 * @throws FileNotFoundException File with saved tree could not be found.
	 */
	public static DecisionTree loadTree(String filename) throws FileNotFoundException {
		return loadTree(new File(filename));
	}

	/**
	 * Kick-off method for the recursive method that actually fills the decision
	 * tree with data from the file.
	 * 
	 * @param file Instance of File containing saved DecisionTree
	 * @return DecisionTree constructed from file
	 * @throws FileNotFoundException File with saved tree could not be found.
	 */
	private static DecisionTree loadTree(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		// First line of file is a question at root node
		DecisionTree dt = new DecisionTree(scan.nextLine());
		BinaryNode<String> root = dt.getRootNode();
		fillTree(Branch.LEFT, scan, root); 
		fillTree(Branch.RIGHT, scan, root); 
		return dt;
	}

	/**
	 * Used by fillTree to determine if the LEFT or RIGHT subtree is currently
	 * being filled from the file.
	 */
	private enum Branch{LEFT, RIGHT};

	/**
	 * Recursively fill the decision tree with the file contents by creating
	 * new child nodes beneath the incoming parent. When the side is LEFT, then
	 * the next line read from the Scanner should be the left child of the
	 * parent. When the side is RIGHT, then the next line read from the Scanner 
	 * should be the right child of the Scanner.
	 * 
	 * @param side Which side of the parent node the next line should be the child of.
	 * @param scanner Scans the input file containing the decision tree.
	 * @param parent Node that read file contents should be attached to.
	 */
	private static void fillTree(Branch side, Scanner scanner, BinaryNode<String> parent) {
		String next = scanner.nextLine(); 
		if(next.equals("NULL")) 
			return;
		
		BinaryNode<String> child = new BinaryNode<>(next); 
		// sets the next line to the corresponding node
		if(side.equals(Branch.LEFT))
			parent.setLeftChild(child); 
		else 
			parent.setRightChild(child);
	
		fillTree(Branch.LEFT, scanner, child);
		fillTree(Branch.RIGHT, scanner, child); // must do both sides since each has an answer to the yes/no question
	}
}
