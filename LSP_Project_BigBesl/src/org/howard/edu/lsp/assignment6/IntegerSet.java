package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

/**
 * 
 * The {@code IntegerSet} class represents a mathematical set of integers.
 * 
 * It disallows duplicates and provides common set operations such as union,
 * 
 * intersection, and difference. The internal representation uses an
 * 
 * {@code ArrayList<Integer>}.
 * 
 * 
 * 
 * <p>
 * Mutator methods modify the current instance rather than returning new sets.
 * </p>
 * 
 */

public class IntegerSet {

	private List<Integer> set = new ArrayList<Integer>();

	/**
	 * 
	 * Clears all elements from the set.
	 * 
	 */

	public void clear() {

		set.clear();

	}

	/**
	 * 
	 * Returns the number of elements in the set.
	 * 
	 * @return number of elements
	 * 
	 */

	public int length() {

		return set.size();

	}

	/**
	 * 
	 * Compares this set to another object for equality.
	 * 
	 * Two sets are equal if they contain the same integers, regardless of order.
	 * 
	 * 
	 * 
	 * @param o object to compare to
	 * 
	 * @return true if sets are equal; false otherwise
	 * 
	 */

	@Override

	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (!(o instanceof IntegerSet))
			return false;

		IntegerSet other = (IntegerSet) o;

		return set.containsAll(other.set) && other.set.containsAll(set);

	}

	/**
	 * 
	 * Checks whether the set contains a specific value.
	 * 
	 * @param value integer to check for
	 * 
	 * @return true if present; false otherwise
	 * 
	 */

	public boolean contains(int value) {

		return set.contains(value);

	}

	/**
	 * 
	 * Returns the largest element in the set.
	 * 
	 * @return largest integer
	 * 
	 * @throws IllegalStateException if the set is empty
	 * 
	 */

	public int largest() {

		if (set.isEmpty()) {

			throw new IllegalStateException("Cannot determine largest value of an empty set.");

		}

		return Collections.max(set);

	}

	/**
	 * 
	 * Returns the smallest element in the set.
	 * 
	 * @return smallest integer
	 * 
	 * @throws IllegalStateException if the set is empty
	 * 
	 */

	public int smallest() {

		if (set.isEmpty()) {

			throw new IllegalStateException("Cannot determine smallest value of an empty set.");

		}

		return Collections.min(set);

	}

	/**
	 * 
	 * Adds an integer to the set if not already present.
	 * 
	 * @param item integer to add
	 * 
	 */

	public void add(int item) {

		if (!set.contains(item)) {

			set.add(item);

		}

	}

	/**
	 * 
	 * Removes an integer from the set if present.
	 * 
	 * @param item integer to remove
	 * 
	 */

	public void remove(int item) {

		set.remove(Integer.valueOf(item));

	}

	/**
	 * 
	 * Modifies this set to be the union of itself and another set.
	 * 
	 * @param other the other IntegerSet
	 * 
	 */

	public void union(IntegerSet other) {

		for (int num : other.set) {

			if (!set.contains(num)) {

				set.add(num);

			}

		}

	}

	/**
	 * 
	 * Modifies this set to contain only elements that are also in the other set.
	 * 
	 * @param other the other IntegerSet
	 * 
	 */

	public void intersect(IntegerSet other) {

		set.retainAll(other.set);

	}

	/**
	 * 
	 * Removes all elements found in another set.
	 * 
	 * @param other the other IntegerSet
	 * 
	 */

	public void diff(IntegerSet other) {

		set.removeAll(other.set);

	}

	/**
	 * 
	 * Modifies this set to become the complement of itself with respect to another
	 * set.
	 * 
	 * (i.e., elements in the other set that are NOT in this set)
	 * 
	 * @param other the other IntegerSet
	 * 
	 */

	public void complement(IntegerSet other) {

		List<Integer> newSet = new ArrayList<Integer>(other.set);

		newSet.removeAll(set);

		set = newSet;

	}

	/**
	 * 
	 * Checks whether the set is empty.
	 * 
	 * @return true if empty; false otherwise
	 * 
	 */

	public boolean isEmpty() {

		return set.isEmpty();

	}

	/**
	 * 
	 * Returns a string representation of the set, formatted as [1, 2, 3].
	 * 
	 * @return formatted string representation
	 * 
	 */

	@Override

	public String toString() {

		return set.toString();

	}

}