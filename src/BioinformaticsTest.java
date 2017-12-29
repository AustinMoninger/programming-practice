import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests out both the slow and fast
 * versions of the different recursive algorithms.
 */
public class BioinformaticsTest {

	@Test
	public void testSlowFib() {
		assertEquals(Bioinformatics.slowFib(0),1);
		assertEquals(Bioinformatics.slowFib(1),1);
		assertEquals(Bioinformatics.slowFib(2),2);
		assertEquals(Bioinformatics.slowFib(3),3);
		assertEquals(Bioinformatics.slowFib(4),5);
		assertEquals(Bioinformatics.slowFib(5),8);
		assertEquals(Bioinformatics.slowFib(6),13);
		assertEquals(Bioinformatics.slowFib(7),21);
		assertEquals(Bioinformatics.slowFib(8),34);
		assertEquals(Bioinformatics.slowFib(9),55);
		
		assertEquals(Bioinformatics.slowFib(10),89);
		assertEquals(Bioinformatics.slowFib(11),144);
		assertEquals(Bioinformatics.slowFib(12),233);
		assertEquals(Bioinformatics.slowFib(13),377);
		assertEquals(Bioinformatics.slowFib(14),610);
		assertEquals(Bioinformatics.slowFib(15),987);
		assertEquals(Bioinformatics.slowFib(16),1597);
		assertEquals(Bioinformatics.slowFib(17),2584);
		assertEquals(Bioinformatics.slowFib(18),4181);
		assertEquals(Bioinformatics.slowFib(19),6765);
		assertEquals(Bioinformatics.slowFib(20),10946);
		assertEquals(Bioinformatics.slowFib(21),17711);
		assertEquals(Bioinformatics.slowFib(22),28657);
		assertEquals(Bioinformatics.slowFib(23),46368);
		assertEquals(Bioinformatics.slowFib(24),75025);
		assertEquals(Bioinformatics.slowFib(25),121393);
		assertEquals(Bioinformatics.slowFib(26),196418);
		assertEquals(Bioinformatics.slowFib(27),317811);
		assertEquals(Bioinformatics.slowFib(28),514229);
		assertEquals(Bioinformatics.slowFib(29),832040);
		assertEquals(Bioinformatics.slowFib(30),1346269);
		assertEquals(Bioinformatics.slowFib(31),2178309);
		assertEquals(Bioinformatics.slowFib(32),3524578);
		assertEquals(Bioinformatics.slowFib(33),5702887);
		assertEquals(Bioinformatics.slowFib(34),9227465);
		assertEquals(Bioinformatics.slowFib(35),14930352);
		assertEquals(Bioinformatics.slowFib(36),24157817);
		assertEquals(Bioinformatics.slowFib(37),39088169);
		assertEquals(Bioinformatics.slowFib(38),63245986);
		assertEquals(Bioinformatics.slowFib(39),102334155);
		assertEquals(Bioinformatics.slowFib(40),165580141);
		assertEquals(Bioinformatics.slowFib(41),267914296);
		assertEquals(Bioinformatics.slowFib(42),433494437);
		assertEquals(Bioinformatics.slowFib(43),701408733);
		assertEquals(Bioinformatics.slowFib(44),1134903170);
		assertEquals(Bioinformatics.slowFib(45),1836311903);
		
		/**
		 * slowFib(46) does not work properly because the answer,
		 * 2971215073, is outside of the range of type int. It continues
		 * past the max value, 2147483647, producing a negative
		 * number, -1323752223.
		 */
	}

	@Test
	public void testFastFib() {
		assertEquals(Bioinformatics.fastFib(0),1);
		assertEquals(Bioinformatics.fastFib(1),1);
		assertEquals(Bioinformatics.fastFib(2),2);
		assertEquals(Bioinformatics.fastFib(3),3);
		assertEquals(Bioinformatics.fastFib(4),5);
		assertEquals(Bioinformatics.fastFib(5),8);
		assertEquals(Bioinformatics.fastFib(6),13);
		assertEquals(Bioinformatics.fastFib(7),21);
		assertEquals(Bioinformatics.fastFib(8),34);
		assertEquals(Bioinformatics.fastFib(9),55);
		
		assertEquals(Bioinformatics.fastFib(10),89);
		assertEquals(Bioinformatics.fastFib(11),144);
		assertEquals(Bioinformatics.fastFib(12),233);
		assertEquals(Bioinformatics.fastFib(13),377);
		assertEquals(Bioinformatics.fastFib(14),610);
		assertEquals(Bioinformatics.fastFib(15),987);
		assertEquals(Bioinformatics.fastFib(16),1597);
		assertEquals(Bioinformatics.fastFib(17),2584);
		assertEquals(Bioinformatics.fastFib(18),4181);
		assertEquals(Bioinformatics.fastFib(19),6765);
		assertEquals(Bioinformatics.fastFib(20),10946);
		assertEquals(Bioinformatics.fastFib(21),17711);
		assertEquals(Bioinformatics.fastFib(22),28657);
		assertEquals(Bioinformatics.fastFib(23),46368);
		assertEquals(Bioinformatics.fastFib(24),75025);
		assertEquals(Bioinformatics.fastFib(25),121393);
		assertEquals(Bioinformatics.fastFib(26),196418);
		assertEquals(Bioinformatics.fastFib(27),317811);
		assertEquals(Bioinformatics.fastFib(28),514229);
		assertEquals(Bioinformatics.fastFib(29),832040);
		assertEquals(Bioinformatics.fastFib(30),1346269);
		assertEquals(Bioinformatics.fastFib(31),2178309);
		assertEquals(Bioinformatics.fastFib(32),3524578);
		assertEquals(Bioinformatics.fastFib(33),5702887);
		assertEquals(Bioinformatics.fastFib(34),9227465);
		assertEquals(Bioinformatics.fastFib(35),14930352);
		assertEquals(Bioinformatics.fastFib(36),24157817);
		assertEquals(Bioinformatics.fastFib(37),39088169);
		assertEquals(Bioinformatics.fastFib(38),63245986);
		assertEquals(Bioinformatics.fastFib(39),102334155);
		assertEquals(Bioinformatics.fastFib(40),165580141);
		assertEquals(Bioinformatics.fastFib(41),267914296);
		assertEquals(Bioinformatics.fastFib(42),433494437);
		assertEquals(Bioinformatics.fastFib(43),701408733);
		assertEquals(Bioinformatics.fastFib(44),1134903170);
		assertEquals(Bioinformatics.fastFib(45),1836311903);
		
		/**
		 * fastFib(46) does not work properly because the answer,
		 * 2971215073, is outside of the range of type int. It continues
		 * past the max value, 2147483647, producing a negative
		 * number, -1323752223.
		 */
	}

	@Test
	public void testSlowRNAScore() {
		assertEquals(Bioinformatics.slowRNAScore("ACCCCCU"), 1);
		assertEquals(Bioinformatics.slowRNAScore("ACCCCGU"), 2);
		assertEquals(Bioinformatics.slowRNAScore("ACUGAGCCCU"), 3);
		assertEquals(Bioinformatics.slowRNAScore("AAUUGCGC"), 4);
		
		assertEquals(Bioinformatics.slowRNAScore("AAGAGCACUGUCU"), 5);
		assertEquals(Bioinformatics.slowRNAScore("ACGUGAAUGCCCCUA"), 6);
		assertEquals(Bioinformatics.slowRNAScore("UUGCGCCAAUUGGCGAC"), 7);
		
		assertEquals(Bioinformatics.slowRNAScore("ACUGAGCCCUGUUAGCUAA"), 8);
	}	
	
	@Test
	public void testFastRNAScore() {
		assertEquals(Bioinformatics.fastRNAScore("ACCCCCU"), 1);
		assertEquals(Bioinformatics.fastRNAScore("ACCCCGU"), 2);
		assertEquals(Bioinformatics.fastRNAScore("AAUUGCGC"), 4);
		assertEquals(Bioinformatics.fastRNAScore("ACUGAGCCCU"), 3);
		assertEquals(Bioinformatics.fastRNAScore("ACUGAGCCCUGUUAGCUAA"), 8);
		assertEquals(Bioinformatics.fastRNAScore("GGAUACGGCCAUACUGCGCAGAAAGCACCGCUUCCCAUCCGAACAGCGAAGUUAAGCUGCGCCAGGCGGUGUUAGUACUGGGGUGGGCGACCACCCGGGAAUCCACCGUGCCGUAUCCU"), 52);
		assertEquals(Bioinformatics.fastRNAScore("AAAGAUCGGGUGAGAUAGUAGAGAUAGUAUGUGUCUCUCAUCUACUAUCGGGUAGAUUUCAUCUACUAUCGGGUAUAUCGGGUAAAAUCGGGUAAGAUUCUCUCUCAUCUACUGUGUCUCUCAUCUACUAUCGGGUAUAUCGGGUAAAAUCGGGUAA"),68);

		assertEquals(Bioinformatics.fastRNAScore("AAGAGCACUGUCU"), 5);
		assertEquals(Bioinformatics.fastRNAScore("ACGUGAAUGCCCCUA"), 6);
		assertEquals(Bioinformatics.fastRNAScore("UUGCGCCAAUUGGCGAC"), 7);
	}

	@Test
	public void testSlowDNAScore() {
		assertEquals(Bioinformatics.slowDNAScore("ATCTGAT","TGCATA"), 4);
		assertEquals(Bioinformatics.slowDNAScore("ATGTTAT","ATCGTAC"), 5);
		assertEquals(Bioinformatics.slowDNAScore("ATATATAT","TATATATA"), 7);
		
		assertEquals(Bioinformatics.slowDNAScore("GCTGGTACCGT","GCCTGGTCG"), 8);
		assertEquals(Bioinformatics.slowDNAScore("TGCGCGTAAATT","GCGCAAATT"), 9);
		assertEquals(Bioinformatics.slowDNAScore("AAACATGGCTTCAC","AATGGCTTCA"), 10);
	}

	@Test
	public void testFastDNAScore() {
		assertEquals(Bioinformatics.fastDNAScore("ATCTGAT","TGCATA"), 4);
		assertEquals(Bioinformatics.fastDNAScore("ATGTTAT","ATCGTAC"), 5);
		assertEquals(Bioinformatics.fastDNAScore("ATATATAT","TATATATA"), 7);
		
		assertEquals(Bioinformatics.fastDNAScore("TCCCAGTTATGTCAGGGGACACGAGAATGCAGAGAC","AATTGCCGCCGTCGTTTTCAGCAGTTATGTCAGATC"), 23);
		assertEquals(Bioinformatics.fastDNAScore("GCGCGTGCGCGGAAGGAGCCAAGGTGAAGTTGTAGCAGTGTGTCAGAAGAGGTGCGTGGCACCATGCTGTCCCCCGAGGCGGAGCGGGTGCTGCGGTACCTGGTCGAAGTAGAGGAGTTG","GACTTGTGGAACCTACTTCCTGAAAATAACCTTCTGTCCTCCGAGCTCTCCGCACCCGTGGATGACCTGCTCCCGTACACAGATGTTGCCACCTGGCTGGATGAATGTCCGAATGAAGCG"), 75);

		assertEquals(Bioinformatics.fastDNAScore("GCTGGTACCGT","GCCTGGTCG"), 8);
		assertEquals(Bioinformatics.fastDNAScore("TGCGCGTAAATT","GCGCAAATT"), 9);
		assertEquals(Bioinformatics.fastDNAScore("AAACATGGCTTCAC","AATGGCTTCA"), 10);
		
		assertEquals(Bioinformatics.fastDNAScore("ACGGCATGTGTGCACGTTGTTGTGACCACGGCATATGCG","CAGTGTGCTATGTGTGCACACGTGTCGATGCAT"), 26);
		assertEquals(Bioinformatics.fastDNAScore("GTGTGTACCATGCGACTGCATCGATGCATGCATACGTATATATA","GGATCGATGCATAAATTGCTAGTATTTTTTTTTGCATTTATTAGCGCAGT"), 28);
		assertEquals(Bioinformatics.fastDNAScore("TATAGCGGCAAGTCAGAGATCGGGGTTTCTCTCT","AGTCAGTTATTATAGCGCGGAGAGAGATCTCTC"), 21);
		assertEquals(Bioinformatics.fastDNAScore("CGTCGCTTCGAAAACGTCGTTAAGCGCGTTA","GAATATCTCTCGGGGAGAGCTCTGAGTA"), 17);
		assertEquals(Bioinformatics.fastDNAScore("GGAGACTCTGGGTAGAGTG","GGATTCGGGCTTAGAGATTCGTA"), 15);
	}
}
