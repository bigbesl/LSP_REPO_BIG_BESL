package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * JUnit 5 test suite for the IntegerSet class.
 * 
 * Verifies correctness of all public methods including edge cases.
 * 
 */

public class IntegerSetTest {

	@Test

	public void testAddAndToString() {

		IntegerSet set = new IntegerSet();

		set.add(1);

		set.add(2);

		set.add(1); // duplicate should be ignored

		assertEquals("[1, 2]", set.toString());

		assertEquals(2, set.length());

	}

	@Test

	public void testClearAndIsEmpty() {

		IntegerSet set = new IntegerSet();

		set.add(5);

		set.clear();

		assertTrue(set.isEmpty());

	}

	@Test

	public void testEquals() {

		IntegerSet a = new IntegerSet();

		IntegerSet b = new IntegerSet();

		a.add(1);
		a.add(2);

		b.add(2);
		b.add(1);

		assertTrue(a.equals(b));

		b.add(3);

		assertFalse(a.equals(b));

	}

	@Test

	public void testContains() {

		IntegerSet set = new IntegerSet();

		set.add(10);

		assertTrue(set.contains(10));

		assertFalse(set.contains(20));

	}

	@Test

	public void testLargestAndSmallest() {

		IntegerSet set = new IntegerSet();

		set.add(4);

		set.add(9);

		set.add(1);

		assertEquals(9, set.largest());

		assertEquals(1, set.smallest());

	}

	@Test

	public void testLargestThrowsExceptionIfEmpty() {

		IntegerSet empty = new IntegerSet();

		assertThrows(IllegalStateException.class, () -> empty.largest());

	}

	@Test

	public void testRemove() {

		IntegerSet set = new IntegerSet();

		set.add(5);

		set.add(7);

		set.remove(5);

		assertFalse(set.contains(5));

		assertEquals("[7]", set.toString());

	}

	@Test

	public void testUnion() {

		IntegerSet s1 = new IntegerSet();

		IntegerSet s2 = new IntegerSet();

		s1.add(1);
		s1.add(2);

		s2.add(2);
		s2.add(3);

		s1.union(s2);

		assertEquals("[1, 2, 3]", s1.toString());

	}

	@Test

	public void testIntersect() {

		IntegerSet s1 = new IntegerSet();

		IntegerSet s2 = new IntegerSet();

		s1.add(1);
		s1.add(2);

		s2.add(2);
		s2.add(3);

		s1.intersect(s2);

		assertEquals("[2]", s1.toString());

	}

	@Test

	public void testDiff() {

		IntegerSet s1 = new IntegerSet();

		IntegerSet s2 = new IntegerSet();

		s1.add(1);
		s1.add(2);
		s1.add(3);

		s2.add(2);

		s1.diff(s2);

		assertEquals("[1, 3]", s1.toString());

	}

	@Test

	public void testComplement() {

		IntegerSet s1 = new IntegerSet();

		IntegerSet s2 = new IntegerSet();

		s1.add(1);
		s1.add(2);

		s2.add(2);
		s2.add(3);

		s1.complement(s2);

		assertEquals("[3]", s1.toString());

	}

	@Test

	public void testIsEmpty() {

		IntegerSet s1 = new IntegerSet();

		assertTrue(s1.isEmpty());

		s1.add(10);

		assertFalse(s1.isEmpty());

	}

	// ----- EDGE-CASE TESTS FOR ROBUSTNESS -----

	@Test

	public void testUnionWithEmptySet() {

		IntegerSet a = new IntegerSet();

		IntegerSet b = new IntegerSet();

		a.add(1);

		a.union(b); // union with empty set should not change

		assertEquals("[1]", a.toString());

		b.union(a); // empty union non-empty -> b becomes [1]

		assertEquals("[1]", b.toString());

	}

	@Test

	public void testIntersectWithEmptySet() {

		IntegerSet a = new IntegerSet();

		IntegerSet b = new IntegerSet();

		a.add(1);

		a.add(2);

		a.intersect(b); // intersection with empty set = empty

		assertTrue(a.isEmpty());

	}

	@Test

	public void testDiffWithSelf() {

		IntegerSet a = new IntegerSet();

		a.add(1);

		a.add(2);

		a.diff(a); // difference with self = empty

		assertTrue(a.isEmpty());

	}

	@Test

	public void testComplementWithSelf() {

		IntegerSet a = new IntegerSet();

		a.add(1);

		a.add(2);

		a.complement(a); // complement with itself = empty

		assertTrue(a.isEmpty());

	}

	@Test

	public void testNegativeAndLargeNumbers() {

		IntegerSet s = new IntegerSet();

		s.add(-10);

		s.add(1000000);

		assertTrue(s.contains(-10));

		assertTrue(s.contains(1000000));

		assertEquals(-10, s.smallest());

		assertEquals(1000000, s.largest());

	}

	@Test

	public void testSmallestThrowsExceptionIfEmpty() {

		IntegerSet s = new IntegerSet();

		assertThrows(IllegalStateException.class, () -> s.smallest());

	}

}