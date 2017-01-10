
package assignment3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class A3_Tester {
	private static Set<String> dict;
	private static  ByteArrayOutputStream outContent;
	@BeforeClass
	public static void setUp() {
		Main.initialize();
		dict = Main.makeDictionary();
	outContent = new ByteArrayOutputStream();
	 System.setOut(new PrintStream(outContent));
	}

	@Test(timeout = 300)
	public void testParse() {
		String input = "hello world";
		Scanner scan = new Scanner(input);
		ArrayList<String> expected = new ArrayList<>();
		expected.add("hello");
		expected.add("world");
		ArrayList<String> res = Main.parse(scan);
		assertEquals(expected.get(0), res.get(0).toLowerCase());
		assertEquals(expected.get(1),res.get(1).toLowerCase());
	}
//	@Test(timeout = 300)
//	public void testParseQuit() {
//		String quit = "/quit";
//		Scanner scan = new Scanner(quit);
//		assertNull(Main.parse(scan));
//	}
	
	private boolean verifyLadder(ArrayList<String> ladder) {
		String prev = null;
		if (ladder==null) return true;
		for (String word : ladder) {
			if (!dict.contains(word.toUpperCase()) && !dict.contains(word.toLowerCase())) {
				// System.out.println("Word not in dict: " + word);
				return false;
			}
			if (prev != null && !differByOne(prev, word))
				return false;
			prev = word;
		}
		return true;
	}

	private static boolean differByOne(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		int diff = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i) && diff++ > 1) {
				// System.out.println("Not differ by one char: " + s1 + ", " +
				// s2);
				return false;
			}
		}

		return true;
	}

	@Test(timeout = 30000)
	public void testBFS1() {
		ArrayList<String> res = Main.getWordLadderBFS("hello", "cells");

		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() < 6);
	}

	@Test(timeout = 30000)
	public void testDFS1() {
		ArrayList<String> res = Main.getWordLadderDFS("hello", "cells");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0||res.size() == 2);

	}

	@Test(timeout = 30000)
	public void testBFS2() {
		ArrayList<String> res = Main.getWordLadderBFS("hello", "apple");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 18);
	}

	@Test(timeout = 30000)
	public void testDFS2() {
		ArrayList<String> res = Main.getWordLadderDFS("hello", "apple");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS3() {
		ArrayList<String> res = Main.getWordLadderBFS("yucks", "posts");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		Main.printLadder(res);
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 8);
	}

	@Test(timeout = 30000)
	public void testDFS3() {
		ArrayList<String> res = Main.getWordLadderDFS("yucks", "posts");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS4() {
		ArrayList<String> res = Main.getWordLadderBFS("apeek", "alive");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		Main.printLadder(res);
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 13);
	}

	@Test(timeout = 30000)
	public void testDFS4() {
		ArrayList<String> res = Main.getWordLadderDFS("apeek", "alive");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS5() {
		ArrayList<String> res = Main.getWordLadderBFS("cobia", "wimpy");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		Main.printLadder(res);
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 14);
	}

	@Test(timeout = 30000)
	public void testDFS5() {
		ArrayList<String> res = Main.getWordLadderDFS("cobia", "wimpy");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS6() {
		ArrayList<String> res = Main.getWordLadderBFS("homey", "jiver");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 8);
	}

	@Test(timeout = 30000)
	public void testDFS6() {
		ArrayList<String> res = Main.getWordLadderDFS("homey", "jiver");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS7() {
		ArrayList<String> res = Main.getWordLadderBFS("aldol", "drawl");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);

	}

	@Test(timeout = 30000)
	public void testDFS7() {
		ArrayList<String> res = Main.getWordLadderDFS("aldol", "drawl");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS8() {
		ArrayList<String> res = Main.getWordLadderBFS("scamp", "bails");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 10);
	}

	@Test(timeout = 30000)
	public void testDFS8() {
		ArrayList<String> res = Main.getWordLadderDFS("scamp", "bails");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS9() {
		ArrayList<String> res = Main.getWordLadderBFS("roque", "tidal");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 14);
	}

	@Test(timeout = 30000)
	public void testDFS9() {
		ArrayList<String> res = Main.getWordLadderDFS("roque", "tidal");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);

	}

	@Test(timeout = 30000)
	public void testBFS10() {
		ArrayList<String> res = Main.getWordLadderBFS("livre", "amiga");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 19);
	}

	@Test(timeout = 30000)
	public void testDFS10() {
		ArrayList<String> res = Main.getWordLadderDFS("livre", "amiga");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS11() {
		ArrayList<String> res = Main.getWordLadderBFS("quint", "doors");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
//		Main.printLadder(res);
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 11);
	}

	@Test(timeout = 30000)
	public void testDFS11() {
		ArrayList<String> res = Main.getWordLadderDFS("quint", "doors");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS12() {
		ArrayList<String> res = Main.getWordLadderBFS("dumka", "naris");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 11);
	}

	@Test(timeout = 30000)
	public void testDFS12() {
		ArrayList<String> res = Main.getWordLadderDFS("dumka", "naris");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS13() {
		ArrayList<String> res = Main.getWordLadderBFS("jazzy", "leady");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);

	}

	@Test(timeout = 30000)
	public void testDFS13() {
		ArrayList<String> res = Main.getWordLadderDFS("jazzy", "leady");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS14() {
		ArrayList<String> res = Main.getWordLadderBFS("pacts", "might");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 11);
	}

	@Test(timeout = 30000)
	public void testDFS14() {
		ArrayList<String> res = Main.getWordLadderDFS("pacts", "might");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS15() {
		ArrayList<String> res = Main.getWordLadderBFS("heist", "slimy");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 12);
	}

	@Test(timeout = 30000)
	public void testDFS15() {
		ArrayList<String> res = Main.getWordLadderDFS("heist", "slimy");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);

	}

	@Test(timeout = 30000)
	public void testBFS16() {
		ArrayList<String> res = Main.getWordLadderBFS("jinni", "inapt");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);

	}

	@Test(timeout = 30000)
	public void testDFS16() {
		ArrayList<String> res = Main.getWordLadderDFS("jinni", "inapt");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS17() {
		ArrayList<String> res = Main.getWordLadderBFS("polls", "torte");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 8);
	}

	@Test(timeout = 30000)
	public void testDFS17() {
		ArrayList<String> res = Main.getWordLadderDFS("polls", "torte");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS18() {
		ArrayList<String> res = Main.getWordLadderBFS("twixt", "hakus");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);

	}
	@Test(timeout = 30000)
	public void testPrintLadder() {
		ArrayList<String> res = Main.getWordLadderBFS("twixt", "hakus");
		outContent.reset();
		Main.printLadder(res);
		String str = outContent.toString().replace("\n", "").replace(".", "").trim();
		assertEquals("no word ladder can be found between twixt and hakus",str);
	}
	
	@Test(timeout = 30000)
	public void testDFS18() {
		ArrayList<String> res = Main.getWordLadderDFS("twixt", "hakus");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS19() {
		ArrayList<String> res = Main.getWordLadderBFS("rekey", "hints");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testDFS19() {
		ArrayList<String> res = Main.getWordLadderDFS("rekey", "hints");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0||res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFS20() {
		ArrayList<String> res = Main.getWordLadderBFS("pimps", "nidal");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
//		Main.printLadder(res);
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0||res.size() == 2);
		assertTrue(res.size() <= 9);
	}

	@Test(timeout = 30000)
	public void testDFS20() {
		ArrayList<String> res = Main.getWordLadderDFS("pimps", "nidal");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertFalse(res == null || res.size() == 0||res.size() == 2);
	}

	// @Test(timeout = 30000)
	// public void testBFS21() {
	// ArrayList<String> res = Main.getWordLadderBFS("sm@rt", "mon3y");
	// assertTrue(res == null || res.size() == 0||res.size() == 2);
	// }
	//
	// @Test(timeout = 30000)
	// public void testDFS21() {
	// ArrayList<String> res = Main.getWordLadderDFS("sm@rt", "mon3y");
	// assertTrue(res == null || res.size() == 0||res.size() == 2);
	// }
}
