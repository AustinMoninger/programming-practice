import java.util.Arrays;

/**
 * This class contains both slow and fast algorithms
 * for computing Fibonacci numbers. These algorithms
 * are both recursive, but the fast version uses memoization
 * to avoid duplicating computation efforts. There are
 * less memory-intensive ways for quickly computing
 * Fibonacci numbers, but this approach serves as a good
 * example of how one should create memoized versions
 * of the bioinformatics algorithms.
 */
public class Bioinformatics {

	/** 
	 * Simple recursive method for defining the Fibonacci sequence,
	 * but very inefficient.
	 * 
	 * @param n Fibonacci number to determine
	 * @return n-th Fibonacci number
	 */
	public static int slowFib(int n) { 
		if(n == 0 || n == 1) return 1;
		else return slowFib(n - 1) + slowFib(n - 2);
	}
	
	/**
	 * A faster method for defining the Fibonacci sequence
	 * that saves and remembers every value ever calculated
	 * in an array, so that each value is only ever calculated
	 * once.
	 * 
	 * @param n Fibonacci number to determine
	 * @return n-th Fibonacci number
	 */
	public static int fastFib(int n) { 
		int[] known = new int[n+1];
		Arrays.fill(known, -1);
		return fastFib(known, n);
	}
	
	/**
	 * This method behaves in essentially the same
	 * way as the standard Fibonacci definition, but
	 * a reference to an array is passed to each method
	 * call. Because the array parameter is merely a 
	 * reference, it functions like a global variable
	 * visible to all recursive calls. When a known
	 * Fibonacci number is to be calculated, it is
	 * immediately returned from the array. If the value
	 * is not know, then it is calculated and stored in
	 * the array before being returned.
	 * 
	 * Note: Although this approach is fast, it is inefficient
	 * in terms of memory usage. There are fast ways to compute
	 * any Fibonacci number using much less memory.
	 * 
	 * @param known Array that stores known Fibonacci
	 * 				numbers. Unknown values are represented
	 * 				with the value -1.
	 * @param n Fibonacci number to determine
	 * @return n-th Fibonacci number
	 */
	private static int fastFib(int[] known, int n) {
		if(known[n] == -1) { 
			if(n == 0 || n == 1) {
				known[n] = 1;
			} else { 
				known[n] = fastFib(known, n-1) + fastFib(known, n-2);
			}
		}
		return known[n];
	}

	/**
	 * Determines the length of the longest common subsequence
	 * between two DNA strands. Note that valid DNA strings can
	 * only contain the letters A, T, C, and G. This slow
	 * version of the method simply uses the recursive score
	 * calculation, which makes it very inefficient.
	 * 
	 * @param dna1 A DNA string containing only the letters A, T, C, and G.
	 * @param dna2 Another DNA string containing only the letters A, T, C, and G.
	 * @return Length of the longest common subsequence in a global alignment
	 *         of the two DNA strands.
	 */
	public static int slowDNAScore(String dna1, String dna2) {
		return slowDNAScoreHelper(dna1, dna2, dna1.length(), dna2. length());
	}
	
	
	/**
	 * Recursive helper method for slowDNAScore. 
	 * @param dna1 A DNA string containing only the letters A, T, C, and G.
	 * @param dna2 Another DNA string containing only the letters A, T, C, and G.
	 * @param i Starting index of dna1 (starts at dna1.length())
	 * @param j Starting index of dna2 (starts at dna2.length())
	 * @return Length of the longest common subsequence in a global alignment
	 *         of the two DNA strands.
	 */
	private static int slowDNAScoreHelper(String dna1, String dna2, int i, int j) {
		int score = 0;
		if(i == 0 || j == 0) return 0; 
		else if(dna1.charAt(i - 1) == dna2.charAt(j - 1)) { 
			score = 1 + slowDNAScoreHelper(dna1, dna2, i-1, j-1); 
		} else {
			int ignoreLastOfdna1 = slowDNAScoreHelper(dna1, dna2, i-1, j); 
			int ignoreLastOfdna2 = slowDNAScoreHelper(dna1, dna2, i, j-1); 
			score = Math.max(ignoreLastOfdna1, ignoreLastOfdna2);
		}
		return score;
	}

	/**
	 * This method returns the exact same answers as the
	 * slowDNAScore method. However, it uses memoization to
	 * perform faster. 
	 * 
	 * @param dna1 A DNA string containing only the letters A, T, C, and G.
	 * @param dna2 Another DNA string containing only the letters A, T, C, and G.
	 * @return Length of the longest common subsequence in a global alignment
	 *         of the two DNA strands.
	 */
	public static int fastDNAScore(String dna1, String dna2) {
		int[][] known = new int[dna1.length()][dna2.length()]; 
		for(int i = 0; i < dna1.length(); i++) 
			for(int j = 0; j < dna2.length(); j++) 
				known[i][j] = -1; // fill it with -1's to indicate unmodified elements
		return fastDNAScoreHelper(known, dna1, dna2, dna1.length(), dna2.length());
	}

	/**
	 * Recursive helper method for fastDNAScore. Uses memoization to
	 * increase efficiency.
	 * 
	 * Note: Indices are all minus one because the helper is called with .length()
	 * and not .length() - 1.
	 * 
	 * @param known 2D array that indicates whether or not
	 *        a value is known (-1 if unknown, other value if known)
	 * @param dna1 A DNA string containing only the letters A, T, C, and G.
	 * @param dna2 Another DNA string containing only the letters A, T, C, and G.
	 * @param i Starting index of dna1 (starts at dna1.length())
	 * @param j Starting index of dna2 (starts at dna2.length())
	 * @return Length of the longest common subsequence in a global alignment
	 *         of the two DNA strands.
	 */
	private static int fastDNAScoreHelper(int[][] known, String dna1, String dna2, int i, int j) {
		int score = 0; 
		if(i == 0 || j == 0) { 
			return 0;
		} else if(known[i-1][j-1] != -1) { // if already modified
			return known[i-1][j-1];
		} else if(dna1.charAt(i-1) == dna2.charAt(j-1)) { // if the last chars in each String are the same
			score = 1 + fastDNAScoreHelper(known, dna1, dna2, i-1, j-1); 
			known[i-1][j-1] = score; 
		} else {
			int ignoreLastOfdna1 = fastDNAScoreHelper(known, dna1, dna2, i-1, j); 
			int ignoreLastOfdna2 = fastDNAScoreHelper(known, dna1, dna2, i, j-1); 
			score = Math.max(ignoreLastOfdna1, ignoreLastOfdna2);
			known[i-1][j-1] = score; 
		}
		return known[i-1][j-1];
	}

	/**
	 * Method determines the maximum number of base pair matches 
	 * in a folded RNA strand with no pseudo-knots (bases that
	 * are part of different loops cannot pair up). This slow
	 * version of the method simply uses the recursive score
	 * calculation, which makes it very inefficient.
	 * 
	 * @param rna String representing RNA strand. Can only contain
	 *            the letters A, U, C, and G.
	 * @return Maximum number of base pairings in folded strand
	 *         with no pseudo-knots.
	 */
	public static int slowRNAScore(String rna) {
		return slowRNAScoreHelper(rna, 0, rna.length() - 1);
	}	

	/**
	 * Recursive helper method for slowRNAScore.
	 * 
	 * @param rna String representing RNA strand. Can only contain
	 *            the letters A, U, C, and G.
	 * @param i Starting index of rna (starts at 0)
	 * @param j Ending index of rna (starts at rna.length() - 1)
	 * @return Maximum number of base pairings in folded strand
	 *         with no pseudo-knots.
	 */
	private static int slowRNAScoreHelper(String rna, int i, int j) {
		if(i >= j) return 0; 
		else { 
			int scoreWithPairing = 0;
			if(canPair(rna.charAt(i), rna.charAt(j))) { 
				scoreWithPairing = 1 + slowRNAScoreHelper(rna, i+1, j-1); 
			}
			int ignoreLeft = slowRNAScoreHelper(rna, i+1, j); 
			int ignoreRight = slowRNAScoreHelper(rna, i, j-1); 
			
			int maxSoFar = Math.max(ignoreLeft, ignoreRight);
			maxSoFar = Math.max(maxSoFar, scoreWithPairing); 
			
			for(int k = i + 1; k < j; k++) { // accounts for every possible way of splitting into substrings
				int splitScore = slowRNAScoreHelper(rna,i,k) + slowRNAScoreHelper(rna,k+1,j);
				maxSoFar = Math.max(maxSoFar, splitScore); 
			}
			return maxSoFar;
		}
	}

	/**
	 * Determines whether or not two base pairings on an RNA
	 * strand can pair up.
	 * 
	 * @param base1 Character at index i
	 * @param base2 Character at index j
	 * @return Whether or not the two base pairings can match
	 */
	private static boolean canPair(char base1, char base2) {
		if((base1 == 'A' && base2 == 'U') || (base1 == 'U' && base2 == 'A')
				|| (base1 == 'C' && base2 == 'G') || (base1 == 'G' && base2 == 'C')
				|| (base1 == 'A' && base2 == 'T') || (base1 == 'T' && base2 == 'A')) 
			return true;
		else
			return false;
	}

	/**
	 * This method returns the exact same answers as the
	 * slowRNAScore method. However, it uses memoization to
	 * perform faster.
	 * 
	 * @param rna String representing RNA strand. Can only contain
	 *            the letters A, U, C, and G.
	 * @return Maximum number of base pairings in folded strand
	 *         with no pseudo-knots.
	 */
	public static int fastRNAScore(String rna) {	
		int[][] known = new int[rna.length()][rna.length()]; 
		for(int i = 0; i < rna.length(); i++) 
			for(int j = 0; j < rna.length(); j++) 
				known[i][j] = -1; // fill it with -1's to indicate unmodified elements
		return fastRNAScoreHelper(known, rna, 0, rna.length() - 1);	
	}
	
	/**
	 * Recursive helper method for fastRNAScore. Uses memoization to
	 * increase efficiency.
	 * 
	 * @param known 2D array that indicates whether or not
	 *        a value is known (-1 if unknown, other value if known)
	 * @param rna String representing RNA strand. Can only contain
	 *            the letters A, U, C, and G.
	 * @param i Starting index of rna (starts at 0)
	 * @param j Ending index of rna (starts at rna.length() - 1)
	 * @return Maximum number of base pairings in folded strand
	 *         with no pseudo-knots.
	 */
	private static int fastRNAScoreHelper(int[][] known, String rna, int i, int j) {
		if(i >= j) { 
			known[i][j] = 0;
		} else if(known[i][j] != -1) { // if already modified
			return known[i][j];
		} else { 
			int scoreWithPairing = 0;
			if(canPair(rna.charAt(i), rna.charAt(j))) 
				scoreWithPairing = 1 + fastRNAScoreHelper(known, rna, i+1, j-1); 
			
			int ignoreLeft = fastRNAScoreHelper(known, rna, i+1, j); 
			int ignoreRight = fastRNAScoreHelper(known, rna, i, j-1); 
			
			int maxSoFar = Math.max(ignoreLeft, ignoreRight);
			maxSoFar = Math.max(maxSoFar, scoreWithPairing); 
			
			for(int k = i+1; k < j; k++) { // accounts for every possible way of splitting into substrings
				int splitScore = fastRNAScoreHelper(known, rna, i, k) + fastRNAScoreHelper(known, rna, k+1, j);
				maxSoFar = Math.max(maxSoFar, splitScore); 
			}
			known[i][j] = maxSoFar; 
		}
		return known[i][j];
	}
}
