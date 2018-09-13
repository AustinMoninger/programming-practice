import java.io.*;
import java.util.*;

public class TreeOfErrors {
  public static void main(String args[] ) throws Exception {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    if (correctInput(input)) {
      System.out.println(getSExpression(input));
    } else {
      System.out.println("E1");
    }
  }

  /**
   * Checks that the input format of the parent-child pairs is correct.
   * Example: "(B,D) (D,E) (A,B) (C,F) (E,G) (A,C)"
   */
  private static boolean correctInput(String input) {
    int firstParen = 0;

    // Iterate over each pair
    while (firstParen < input.length()) {
      if ((firstParen != 0 && input.charAt(firstParen - 1) != ' ') ||     // check space before each pair
              input.charAt(firstParen) != '(' ||                              // check for first paren
              !Character.isUpperCase(input.charAt(firstParen + 1)) ||         // check for first uppercase char
              input.charAt(firstParen + 2) != ',' ||                          // check for comma in between
              !Character.isUpperCase(input.charAt(firstParen + 3)) ||         // check for second uppercase char
              input.charAt(firstParen + 1) == input.charAt(firstParen + 3) || // check for parent-child equivalence
              input.charAt(firstParen + 4) != ')') {                          // check for closing paren
        return false;
      }
      firstParen += 6;
    }
    return true;
  }

  /**
   * Returns the lexographically smallest S-expression for the input binary tree.
   * If the input is not valid, returns the errors in this order:
   * E1: Invalid Input Format
   * E2: Duplicate Pair
   * E3: Parent Has More Than Two Children
   * E4: Multiple Roots
   * E5: Tree Contains Cycle
   */
  private static String getSExpression(String input) {
    int distanceToRightChild = 2;
    int distanceBetweenPairs = 6;

    boolean[][] edgeMatrix = new boolean[26][26];
    Set<Character> nodes = new HashSet<>();

    for (int firstChildIndex = 1; firstChildIndex < input.length(); firstChildIndex += distanceBetweenPairs) {
      int secondChildIndex = firstChildIndex + distanceToRightChild;

      // Get number in alphabet from each char
      int parent = input.charAt(firstChildIndex) - 'A';
      int child = input.charAt(secondChildIndex) - 'A';

      // Check for duplicate pairs
      if (edgeMatrix[parent][child]) {
        return "E2";
      }

      // Fill the edgeMatrix with edges
      edgeMatrix[parent][child] = true;

      // Add new nodes to the set of nodes
      nodes.add(input.charAt(firstChildIndex));
      nodes.add(input.charAt(secondChildIndex));
    }

    boolean moreThanTwoChildren = findMoreThanTwoChildren(edgeMatrix);
    if (moreThanTwoChildren) {
      return "E3";
    }

    String cycleOrMultipleRoot = getCycleAndMultipleRoot(edgeMatrix, nodes);
    if (cycleOrMultipleRoot.length() > 1) {
      return cycleOrMultipleRoot; // E4 if cycle, E5 if multiple roots
    }

    return getSExpressionHelper(cycleOrMultipleRoot.charAt(0), edgeMatrix);
  }

  /**
   * Finds if any parent has more than two children (more than two true entries)
   * and returns a boolean.
   */
  private static boolean findMoreThanTwoChildren(boolean[][] edgeMatrix) {
    for (int parent = 0; parent < edgeMatrix.length; parent++) {
      int numOfChildren = 0;
      for (int child = 0; child < edgeMatrix[0].length; child++) {
        if (edgeMatrix[parent][child]) {
          numOfChildren++;
          if (numOfChildren > 2) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Finds if there are any cycles in the tree or if there are multiple roots.
   * Returns E4 if there are multiple roots,
   * E5 if there is a cycle,
   * and the root otherwise.
   */
  private static String getCycleAndMultipleRoot(boolean[][] edgeMatrix, Set<Character> nodes) {
    int numRoots = 0;
    char root = ' ';

    boolean cycle = false;
    for (Character node : nodes) {
      for (int parent = 0; parent < edgeMatrix.length; parent++) {
        if (edgeMatrix[parent][node - 'A']) { // node is not a root
          break;
        }
        if (parent == edgeMatrix.length - 1) { // node is a root
          numRoots++;
          root = node;
          boolean[] visited = new boolean[edgeMatrix.length];
          if (findCycle(edgeMatrix, visited, node)) { // is there a cycle?
            cycle = true;
            break;
          }
        }
      }
    }
    if (numRoots > 1) {
      return "E4";
    } else if (numRoots == 0 || cycle) {
      return "E5";
    } else {
      return Character.toString(root);
    }
  }

  /**
   * Finds whether or not there is a cycle present.
   */
  private static boolean findCycle(boolean[][] edgeMatrix, boolean[] visited, char node) {
    int start = node - 'A';
    if (visited[start]) {
      return true;
    }
    visited[start] = true;

    for (int child = 0; child < edgeMatrix.length; child++) {
      if (edgeMatrix[start][child]) {
        if (findCycle(edgeMatrix, visited, (char)(child + 'A'))) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns the lexographically smallest S-expression for the input binary tree.
   */
  private static String getSExpressionHelper(char root, boolean[][] edgeMatrix) {
    String left = "";
    String right = "";
    for (int parent = 0; parent < edgeMatrix.length; parent++){
      if (edgeMatrix[root - 'A'][parent]) { // if root points to "parent"
        left = getSExpressionHelper((char)(parent + 'A'), edgeMatrix);
        for (int child = parent + 1; child < edgeMatrix[0].length; child++) {
          if (edgeMatrix[root - 'A'][child]) { // if "parent" points to "child"
            right = getSExpressionHelper((char)(child + 'A'), edgeMatrix);
            break;
          }
        }
        break;
      }
    }
    return "(" + root + left + right + ")";
  }
}