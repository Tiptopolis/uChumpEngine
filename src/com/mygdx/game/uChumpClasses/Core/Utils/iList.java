package com.mygdx.game.uChumpClasses.Core.Utils;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;

public abstract class iList<E> extends AbstractList<E>
implements
	// All interfaces of ArrayList
	List<E>, RandomAccess, Cloneable, Serializable,
	// Additional interfaces of LinkedList
	Deque<E> {

/**
 * @author eclipse (added by ral)
 */
private static final long serialVersionUID = -9174212688341615200L;

/**
 * Copies the collection values into an array.
 *
 * @param coll   collection of values
 * @return       array containing the collection values
 */
static Object[] toArray(Collection<?> coll) {
	Object[] values = coll.toArray();
	// as in ArrayList: toArray() might (incorrectly) not return Object[] (see bug 6260652)
	if (values.getClass() != Object[].class) {
		values = Arrays.copyOf(values, values.length, Object[].class);
    }
	return values;
}

/**
 * Returns a shallow copy of this <tt>GapList</tt> instance
 * (the new list will contain the same elements as the source list, i.e. the elements themselves are not copied).
 * This method is identical to clone() except that the result is casted to GapList.
 *
 * @return a clone of this instance
 * @see #clone
 */
@SuppressWarnings("unchecked")
public iList<E> copy() {
    return (iList<E>) clone();
}

/**
 * Returns an unmodifiable view of this list. This method allows
 * modules to provide users with "read-only" access to internal lists.
 * Query operations on the returned list "read through" to the specified
 * list, and attempts to modify the returned list, whether direct or
 * via its iterator, result in an UnsupportedOperationException.
 *
 * @return an unmodifiable view of the specified list
 */
abstract public iList<E> unmodifiableList();

/**
 * Returns a shallow copy of this <tt>GapList</tt> instance
 * (The elements themselves are not copied).
 * The capacity of the list will be set to the number of elements,
 * so after calling clone(), size and capacity are equal.
 *
 * @return a clone of this <tt>GapList</tt> instance
 */
@SuppressWarnings("unchecked")
@Override
public Object clone() {
	try {
		iList<E> list = (iList<E>) super.clone();
		list.doClone(this);
	    return list;
	}
	catch (CloneNotSupportedException e) {
	    // This shouldn't happen, since we are Cloneable
	    throw new AssertionError(e);
	}
}

/**
 * Initialize this object after the bitwise copy has been made
 * by Object.clone().
 *
 * @param that	source object
 */
abstract protected void doClone(iList<E> that);

@Override
public void clear() {
	doRemoveAll(0, size());
}

@Override
abstract public int size();

/**
 * Returns capacity of this GapList.
 * Note that two GapLists are considered equal even if they have a distinct capacity.
 * Also the capacity can be changed by operations like clone() etc.
 *
 * @return capacity of this GapList
 */
abstract public int capacity();

@Override
public E get(int index) {
	checkIndex(index);
    return doGet(index);
}

/**
 * Helper method for getting an element from the GapList.
 * This is the only method which really gets an element.
 * Override if you need to validity checks before getting.
 *
 * @param index index of element to return
 * @return      the element at the specified position in this list
 */
abstract protected E doGet(int index);

/**
 * Helper method for setting an element in the GapList.
 * This is the only method which really sets an element.
 * Override if you need to validity checks before setting.
 *
 * @param index index where element will be placed
 * @param elem  element to set
 * @return      old element which was at the position
 */
abstract protected E doSet(int index, E elem);

@Override
public E set(int index, E elem) {
	checkIndex(index);
	return doSet(index, elem);
}

/**
 * Sets an element at specified position.
 * This method is used internally if existing elements will be moved etc.
 * Override if you need to validity checks.
 *
 * @param index index where element will be placed
 * @param elem  element to set
 * @return      old element which was at the position
 */
abstract protected E doReSet(int index, E elem);

abstract protected E getDefaultElem();

/**
 * This method is called internally before elements are allocated or freed.
 * Override if you need to validity checks.
 */
protected void doModify() {
}

@Override
public boolean add(E elem) {
	return doAdd(-1, elem);
}

@Override
public void add(int index, E elem)	{
	checkIndexAdd(index);
	doAdd(index, elem);
}

/**
 * Helper method for adding an element to the GapList.
 * This is the only method which really adds an element.
 * Override if you need to validity checks before adding.
 *
 * @param index	index where element should be added
 *              (-1 means it is up to the implementation to choose the index)
 * @param elem	element to add
 * @return      true if element has been added (GapList.add() will always return true)
 */
abstract protected boolean doAdd(int index, E elem);

@Override
public E remove(int index) {
	checkIndex(index);
	return doRemove(index);
}

/**
 * Helper method to remove an element.
 * This is the only method which really removes an element.
 * Override if you need to validity checks before removing.
 *
 * @param index	index of element to remove
 * @return		removed element
 */
abstract protected E doRemove(int index);

/**
 * Increases the capacity of this <tt>GapList</tt> instance, if
 * necessary, to ensure that it can hold at least the number of elements
 * specified by the minimum capacity argument.
 *
 * @param   minCapacity   the desired minimum capacity
 */
// Note: Provide this method to make transition from ArrayList as
//       smooth as possible
public void ensureCapacity(int minCapacity) {
	doModify();
	doEnsureCapacity(minCapacity);
}

/**
 * Increases the capacity of this <tt>GapList</tt> instance, if
 * necessary, to ensure that it can hold at least the number of elements
 * specified by the minimum capacity argument.
 *
 * @param   minCapacity   the desired minimum capacity
 */
abstract protected void doEnsureCapacity(int minCapacity);

/**
 * Trims the capacity of this <tt>GapList</tt> instance to be the
 * list's current size.  An application can use this operation to minimize
 * the storage of an <tt>GapList</tt> instance.
 */
// Note: Provide this method to make transition from ArrayList as
//       smooth as possible
abstract public void trimToSize();

@Override
public boolean equals(Object obj) {
	if (obj == this) {
		return true;
	}
	if (!(obj instanceof List<?>)) {
		return false;
	}
	@SuppressWarnings("unchecked")
	List<E> list = (List<E>) obj;
	int size = size();
	if (size != list.size()) {
		return false;
	}
	for (int i=0; i<size; i++) {
		if (!equalsElem(doGet(i), list.get(i))) {
			return false;
		}
	}
	return true;
}

@Override
public int hashCode() {
	int hashCode = 1;
	int size = size();
	for (int i=0; i<size; i++) {
		E elem = doGet(i);
		hashCode = 31*hashCode + hashCodeElem(elem);
	}
	return hashCode;
}

@Override
public String toString() {
	StringBuilder buf = new StringBuilder();
	buf.append("[");
	int size = size();
	for (int i=0; i<size; i++) {
		if (i > 0) {
			buf.append(", ");
		}
		buf.append(doGet(i));
	}
	buf.append("]");
	return buf.toString();
}

@Override
public boolean isEmpty() {
	return size() == 0;
}

/**
 * Helper function to check two elements stored in the GapList
 * for equality.
 *
 * @param elem1	first element
 * @param elem2	second element
 * @return		true if the elements are equal, otherwise false
 */
static boolean equalsElem(Object elem1, Object elem2) {
	if (elem1 == null) {
		if (elem2 == null) {
			return true;
		}
	} else {
		if (elem1.equals(elem2)) {
			return true;
		}
	}
	return false;
}

/**
 * Helper method to calculate hash code of a element stored in
 * the GapList.
 *
 * @param elem	element
 * @return		hash code for element
 */
static int hashCodeElem(Object elem) {
    if (elem == null) {
        return 0;
    } else {
        return elem.hashCode();
    }
}

/**
 * Counts how many times the specified element is contained in the list.
 *
 * @param elem	element to count
 * @return		count how many times the specified element is contained in the list
 */
public int getCount(E elem) {
	int count = 0;
	int size = size();
	for (int i=0; i<size; i++) {
		if (equalsElem(doGet(i), elem)) {
			count++;
		}
	}
	return count;
}

/**
 * Returns all elements in the list equal to the specified element.
 *
 * @param elem	element to look for
 * @return		all elements in the list equal to the specified element
 */
public iList<E> getAll(E elem) {
    iList<E> list = doCreate(-1);
	int size = size();
	for (int i=0; i<size; i++) {
		E e = doGet(i);
		if (equalsElem(e, elem)) {
			list.add(e);
		}
	}
    return list;
}

/**
 * Returns distinct elements in the list.
 *
 * @return		distinct elements in the list
 */
public Set<E> getDistinct() {
    Set<E> set = new HashSet<E>();
	int size = size();
	for (int i=0; i<size; i++) {
		set.add(doGet(i));
	}
    return set;
}

@Override
public int indexOf(Object elem) {
	int size = size();
	for (int i=0; i<size; i++) {
		if (equalsElem(doGet(i), elem)) {
			return i;
		}
	}
	return -1;
}

@Override
public int lastIndexOf(Object elem) {
	for (int i=size()-1; i>=0; i--) {
		if (equalsElem(doGet(i), elem)) {
			return i;
		}
	}
	return -1;
}

@Override
public boolean remove(Object elem) {
	int index = indexOf(elem);
	if (index == -1) {
		return false;
	}
	doRemove(index);
	return true;
}

@Override
public boolean contains(Object elem) {
	return indexOf(elem) != -1;
}

/**
 * Returns true if any of the elements of the specified collection is contained in the list.
 *
 * @param coll collection with elements to be contained
 * @return     true if any element is contained, false otherwise
 */
public boolean containsAny(Collection<?> coll) {
    // Note that the signature has been chosen as in List:
    // - boolean addAll(Collection<? extends E> c);
    // - boolean containsAll(Collection<?> c);
    for (Object elem: coll) {
        if (contains(elem)) {
            return true;
        }
    }
    return false;
}

@Override
public boolean containsAll(Collection<?> coll) {
    // Note that this method is already implemented in AbstractCollection.
	// It has been duplicated so the method is also available in the primitive classes.
    for (Object elem: coll) {
        if (!contains(elem)) {
            return false;
        }
    }
    return true;
}

@Override
public boolean removeAll(Collection<?> coll) {
    // Note that this method is already implemented in AbstractCollection.
	// It has been duplicated so the method is also available in the primitive classes.
    boolean modified = false;
    int size = size();
	for (int i=0; i<size; i++) {
		if (coll.contains(doGet(i))) {
			doRemove(i);
			size--;
			i--;
			modified = true;
		}
	}
	return modified;
}

/**
 * Removes all equal elements.
 *
 * @param elem	element
 * @return		removed equal elements (never null)
 */
public iList<E> removeAll(E elem) {
    iList<E> list = doCreate(-1);
    int size = size();
	for (int i=0; i<size; i++) {
		E e = doGet(i);
		if (equalsElem(elem, e)) {
			list.add(e);
			doRemove(i);
			size--;
			i--;
		}
	}
	return list;
}

/**
 * @see #removeAll(java.util.Collection)
 */
public boolean removeAll(iList<?> coll) {
	// There is a special implementation accepting a GapList
	// so the method is also available in the primitive classes.
    boolean modified = false;
    int size = size();
	for (int i=0; i<size; i++) {
		if (coll.contains(doGet(i))) {
			doRemove(i);
			size--;
			i--;
			modified = true;
		}
	}
	return modified;
}

@Override
public boolean retainAll(Collection<?> coll) {
    // Note that this method is already implemented in AbstractCollection.
	// It has been duplicated so the method is also available in the primitive classes.
    boolean modified = false;
    int size = size();
	for (int i=0; i<size; i++) {
		if (!coll.contains(doGet(i))) {
			doRemove(i);
			size--;
			i--;
			modified = true;
		}
	}
	return modified;
}

/**
 * @see #retainAll(java.util.Collection)
 */
public boolean retainAll(iList<?> coll) {
	// There is a special implementation accepting a GapList
	// so the method is also available in the primitive classes.
    boolean modified = false;
    int size = size();
	for (int i=0; i<size; i++) {
		if (!coll.contains(doGet(i))) {
			doRemove(i);
			size--;
			i--;
			modified = true;
		}
	}
	return modified;
}

@Override
public Object[] toArray() {
    int size = size();
	Object[] array = new Object[size];
	doGetAll(array, 0, size);
    return array;
}

/**
 * Returns an array containing the specified elements in this list.
 *
 * @param index	index of first element to copy
 * @param len	number of elements to copy
 * @return		array the specified elements
 */
public Object[] toArray(int index, int len) {
	Object[] array = new Object[len];
	doGetAll(array, index, len);
    return array;
}

@SuppressWarnings("unchecked")
@Override
public <T> T[] toArray(T[] array) {
    int size = size();
    if (array.length < size) {
    	array = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
    }
    doGetAll(array, 0, size);
    if (array.length > size) {
    	array[size] = null;
    }
    return array;
}

/**
 * Helper method to fill the specified elements in an array.
 *
 * @param array	array to store the list elements
 * @param index	index of first element to copy
 * @param len	number of elements to copy
 * @param <T> type of elements stored in the list
 */
@SuppressWarnings("unchecked")
protected <T> void doGetAll(T[] array, int index, int len) {
	for (int i=0; i<len; i++) {
		array[i] = (T) doGet(index+i);
	}
}

/**
 * Adds all of the elements in the specified collection into this list.
 * The new elements will appear in the list in the order that they
 * are returned by the specified collection's iterator.
 *
 * @param coll collection containing elements to be added to this list
 * @return <tt>true</tt> if this list changed as a result of the call
 * @throws NullPointerException if the specified collection is null
 */
@Override
public boolean addAll(Collection<? extends E> coll) {
    // ArrayList.addAll() also first creates an array containing the
    // collection elements. This guarantees that the list's capacity
    // must only be increased once.
    @SuppressWarnings("unchecked")
    E[] array = (E[]) toArray(coll);
    return doAddAll(-1, array);
}

/**
 * Inserts all of the elements in the specified collection into this
 * list, starting at the specified position.
 * Shifts the element currently at that position (if any) and any
 * subsequent elements to the right (increases their indices).
 * The new elements will appear in the list in the order that they
 * are returned by the specified collection's iterator.
 *
 * @param index index at which to insert the first element from the
 *              specified collection
 * @param coll collection containing elements to be inserted into this list
 * @return <tt>true</tt> if this list changed as a result of the call
 * @throws IndexOutOfBoundsException if the index is invalid
 * @throws NullPointerException if the specified collection is null
 */
@Override
public boolean addAll(int index, Collection<? extends E> coll) {
    checkIndexAdd(index);

    // ArrayList.addAll() also first creates an array containing the
    // collection elements. This guarantees that the list's capacity
    // must only be increased once.
    @SuppressWarnings("unchecked")
    E[] array = (E[]) toArray(coll);
    return doAddAll(index, array);
}

/**
 * Adds all specified elements into this list.
 *
 * @param elems elements to be added to this list
 * @return <tt>true</tt> if this list changed as a result of the call
 */
public boolean addAll(E... elems) {
	return doAddAll(-1, elems);
}

/**
 * Inserts the specified elements into this list,
 * starting at the specified position.
 * Shifts the element currently at that position (if any) and any
 * subsequent elements to the right (increases their indices).
 *
 * @param index index at which to insert the first element from the
 *              specified collection
 * @param elems elements to be inserted into this list
 * @return <tt>true</tt> if this list changed as a result of the call
 * @throws IndexOutOfBoundsException if the index is invalid
 */
public boolean addAll(int index, E... elems) {
    checkIndexAdd(index);

    return doAddAll(index, elems);
}

/**
 * Adds all of the elements in the specified list into this list.
 *
 * @param list collection containing elements to be added to this list
 * @return <tt>true</tt> if this list changed as a result of the call
 * @throws NullPointerException if the specified list is null
 */
@SuppressWarnings("unchecked")
public boolean addAll(iList<? extends E> list) {
    return doAddAll(-1, (E[]) list.toArray());
}

/**
 * Inserts all of the elements in the specified list into this
 * list, starting at the specified position.
 * Shifts the element currently at that position (if any) and any
 * subsequent elements to the right (increases their indices).
 *
 * @param index index at which to insert the first element from the
 *              specified collection
 * @param list list containing elements to be inserted into this list
 * @return <tt>true</tt> if this list changed as a result of the call
 * @throws IndexOutOfBoundsException if the index is invalid
 * @throws NullPointerException if the specified collection is null
 */
@SuppressWarnings("unchecked")
public boolean addAll(int index, iList<? extends E> list) {
	checkIndexAdd(index);

	return doAddAll(index, (E[]) list.toArray());
}

/**
 * Helper method for adding multiple elements to the GapList.
 * It still calls doAdd() for adding each element.
 *
 * @param index index where element should be added
 *              (-1 is valid for adding at the end)
 * @param array array with elements to add
 * @return      true if elements have been added, false otherwise
 */
protected boolean doAddAll(int index, E[] array) {
    doEnsureCapacity(size() + array.length);

	if (array.length == 0) {
		return false;
	}
	for (E elem: array) {
		doAdd(index, elem);
		if (index != -1) {
		    index++;
		}
	}
	return true;
}

// Iterators

@Override
public Iterator<E> iterator() {
	return new Iter(true);
}

@Override
public ListIterator<E> listIterator() {
	return new ListIter(0);
}

@Override
public ListIterator<E> listIterator(int index) {
	return new ListIter(index);
}

@Override
public Iterator<E> descendingIterator() {
	return new Iter(false);
}

// Queue operations

@Override
public E peek() {
    if (size() == 0) {
        return null;
    }
    return getFirst();
}

@Override
public E element() {
	// inline version of getFirst():
    if (size() == 0) {
        throw new NoSuchElementException();
    }
	return doGet(0);
}

@Override
public E poll() {
    if (size() == 0) {
        return null;
    }
	return doRemove(0);
}

@Override
public E remove() {
	// inline version of removeFirst():
    if (size() == 0) {
        throw new NoSuchElementException();
    }
	return doRemove(0);
}

@Override
public boolean offer(E elem) {
	// inline version of add(elem):
	return doAdd(-1, elem);
}

// Deque operations

@Override
public E getFirst() {
    if (size() == 0) {
        throw new NoSuchElementException();
    }
	return doGet(0);
}

@Override
public E getLast() {
	int size = size();
    if (size == 0) {
        throw new NoSuchElementException();
    }
	return doGet(size-1);
}

@Override
public void addFirst(E elem) {
	doAdd(0, elem);
}

@Override
public void addLast(E elem) {
	// inline version of add(elem):
	doAdd(-1, elem);
}

@Override
public E removeFirst() {
    if (size() == 0) {
        throw new NoSuchElementException();
    }
	return doRemove(0);
}

@Override
public E removeLast() {
	int size = size();
    if (size == 0) {
        throw new NoSuchElementException();
    }
	return doRemove(size-1);
}

@Override
public boolean offerFirst(E elem) {
    // inline version of addFirst(elem):
	doAdd(0, elem);
    return true;
}

@Override
public boolean offerLast(E elem) {
    // inline version of addLast(elem):
	doAdd(-1, elem);
    return true;
}

@Override
public E peekFirst() {
    if (size() == 0) {
        return null;
    }
    return doGet(0);
}

@Override
public E peekLast() {
	int size = size();
    if (size == 0) {
        return null;
    }
    return doGet(size-1);
}

@Override
public E pollFirst() {
    if (size() == 0) {
        return null;
    }
    return doRemove(0);
}

@Override
public E pollLast() {
	int size = size();
    if (size == 0) {
        return null;
    }
    return doRemove(size-1);
}

@Override
public E pop() {
    // inline version of removeFirst():
    if (size() == 0) {
        throw new NoSuchElementException();
    }
	return doRemove(0);

}

@Override
public void push(E elem) {
    // inline version of addFirst();
	doAdd(0, elem);
}

@Override
public boolean removeFirstOccurrence(Object elem) {
	int index = indexOf(elem);
	if (index == -1) {
		return false;
	}
	doRemove(index);
	return true;
}

@Override
public boolean removeLastOccurrence(Object elem) {
	int index = lastIndexOf(elem);
	if (index == -1) {
		return false;
	}
	doRemove(index);
	return true;
}

// --- Static bulk methods working with two GapLists ---

/**
 * Moves elements from one GapList to another.
 *
 * @param src		source list
 * @param srcIndex	index of first element in source list
 * @param dst		destination list
 * @param dstIndex	index of first element in source list
 * @param len		number of elements to move
 * @param <E> 		type of elements stored in the list
 * @throws 			IndexOutOfBoundsException if the ranges are invalid
 */
public static <E> void move(iList<E> src, int srcIndex, iList<? super E> dst, int dstIndex, int len) {
    if (src == dst) {
        src.move(srcIndex, dstIndex, len);

    } else {
        src.checkRange(srcIndex, len);
        dst.checkRange(dstIndex, len);

        E defaultElem = src.getDefaultElem();
		for (int i=0; i<len; i++) {
			E elem = src.doReSet(srcIndex+i, defaultElem);
			dst.doSet(dstIndex+i, elem);
		}
    }
}

/**
 * Copies elements from one GapList to another.
 *
 * @param src		source list
 * @param srcIndex	index of first element in source list
 * @param dst		destination list
 * @param dstIndex	index of first element in source list
 * @param len		number of elements to copy
 * @param <E> 		type of elements stored in the list
 * @throws 			IndexOutOfBoundsException if the ranges are invalid
 */
public static <E> void copy(iList<? extends E> src, int srcIndex, iList<E> dst, int dstIndex, int len) {
    if (src == dst) {
        src.copy(srcIndex, dstIndex, len);

    } else {
        src.checkRange(srcIndex, len);
        dst.checkRange(dstIndex, len);

		for (int i=0; i<len; i++) {
			E elem = src.doGet(srcIndex+i);
			dst.doSet(dstIndex+i, elem);
		}
	}
}

/**
 * Swaps elements from two GapLists.
 *
 * @param src		first list
 * @param srcIndex	index of first element in first list
 * @param dst		second list
 * @param dstIndex	index of first element in second list
 * @param len		number of elements to swap
 * @param <E> 		type of elements stored in the list
 * @throws 			IndexOutOfBoundsException if the ranges are invalid
 */
public static <E> void swap(iList<E> src, int srcIndex, iList<E> dst, int dstIndex, int len) {
    if (src == dst) {
        src.swap(srcIndex, dstIndex, len);

    } else {
    	src.checkRange(srcIndex, len);
    	dst.checkRange(dstIndex, len);

    	if (src != dst) {
    		for (int i=0; i<len; i++) {
        		E swap = src.doGet(srcIndex+i);
        		swap = dst.doSet(dstIndex+i, swap);
        		src.doSet(srcIndex+i, swap);
    		}
    	}
    }
}


// --- Bulk methods ---

// -- Readers --

/**
 * Create list with specified capacity.
 *
 * @param capacity	initial capacity (use -1 for default capacity)
 * @return			created list
 */
abstract protected iList<E> doCreate(int capacity);

/**
 * Assign this list the content of the that list.
 * This is done by bitwise copying so the that list should not be user afterwards.
 *
 * @param that list to copy content from
 */
abstract protected void doAssign(iList<E> that);


/**
 * Returns specified range of elements from list.
 *
 * @param index index of first element to retrieve
 * @param len   number of elements to retrieve
 * @return      GapList containing the specified range of elements from list
 */
public iList<E> getAll(int index, int len) {
    checkRange(index, len);

    iList<E> list = doCreate(len);
    for (int i=0; i<len; i++) {
        list.add(doGet(index+i));
    }
    return list;
}

/**
 * Returns specified range of elements from list.
 *
 * @param index index of first element to retrieve
 * @param len   number of elements to retrieve
 * @return      GapList containing the specified range of elements from list
 */
public E[] getArray(int index, int len) {
    checkRange(index, len);

    @SuppressWarnings("unchecked")
    E[] array = (E[]) new Object[len];
    for (int i=0; i<len; i++) {
        array[i] = doGet(index+i);
    }
    return array;
}

// -- Mutators --

/**
 * Replaces the specified elements.
 *
 * @param index index of first element to set
 * @param list  list with elements to set
 */
public void setAll(int index, iList<? extends E> list) {
	// There is a special implementation accepting a GapList
	// so the method is also available in the primitive classes.
    int size = list.size();
    checkRange(index, size);

    for (int i=0; i<size; i++) {
        doSet(index+i, list.get(i));
    }
}

/**
 * Replaces the specified elements.
 *
 * @param index index of first element to set
 * @param coll  collection with elements to set
 */
public void setAll(int index, Collection<? extends E> coll) {
    checkRange(index, coll.size());

    // In contrary to addAll() there is no need to first create an array
    // containing the collection elements, as the list will not grow.
    int i = 0;
    Iterator<? extends E> iter = coll.iterator();
    while (iter.hasNext()) {
        doSet(index+i, iter.next());
        i++;
    }
}

/**
 * Replaces the specified elements.
 *
 * @param index index of first element to set
 * @param elems elements to set
 */
public void setAll(int index, E... elems) {
    checkRange(index, elems.length);

    doSetAll(index, elems);
}

/**
 * Replaces the specified elements.
 *
 * @param index index of first element to set
 * @param elems elements to set
 */
protected void doSetAll(int index, E[] elems) {
    for (int i=0; i<elems.length; i++) {
        doSet(index+i, elems[i]);
    }
}

/**
 * Remove specified range of elements from list.
 *
 * @param index	index of first element to remove
 * @param len	number of elements to remove
 */
public void remove(int index, int len) {
	checkRange(index, len);

	doRemoveAll(index, len);
}

/**
 * Remove specified range of elements from list.
 *
 * @param index index of first element to remove
 * @param len   number of elements to remove
 */
protected void doRemoveAll(int index, int len) {
	for (int i=index+len-1; i>=index; i--) {
		doRemove(i);
	}
}

/**
 * Initializes the list so it will afterwards have a size of
 * <code>len</code> and contain only the element <code>elem</code>.
 * The list will grow or shrink as needed.
 *
 * @param len  length of list
 * @param elem element which the list will contain
 */
public void init(int len, E elem) {
    checkLength(len);

    int size = size();
    if (len < size) {
        remove(len, size-len);
        fill(0, len, elem);
    } else {
        fill(0, size, elem);
        for (int i=size; i<len; i++) {
            add(elem);
        }
    }
    assert(size() == len);
}

/**
 * Resizes the list so it will afterwards have a size of
 * <code>len</code>. If the list must grow, the specified
 * element <code>elem</code> will be used for filling.
 *
 * @param len  length of list
 * @param elem element which will be used for extending the list
 */
public void resize(int len, E elem) {
    checkLength(len);

    int size = size();
    if (len < size) {
        remove(len, size-len);
    } else {
        for (int i=size; i<len; i++) {
            add(elem);
        }
    }
    assert(size() == len);
}

/**
 * Fill list.
 *
 * @param elem  element used for filling
 */
// see java.util.Arrays#fill
public void fill(E elem) {
	int size = size();
    for (int i=0; i<size; i++) {
        doSet(i, elem);
    }
}

/**
 * Fill specified elements.
 *
 * @param index	index of first element to fill
 * @param len	number of elements to fill
 * @param elem	element used for filling
 */
// see java.util.Arrays#fill
public void fill(int index, int len, E elem) {
	checkRange(index, len);

	for (int i=0; i<len; i++) {
		doSet(index+i, elem);
	}
}

/**
 * Copy specified elements.
 * Source and destination ranges may overlap.
 *
 * @param srcIndex	index of first source element to copy
 * @param dstIndex	index of first destination element to copy
 * @param len		number of elements to copy
 */
public void copy(int srcIndex, int dstIndex, int len) {
	checkRange(srcIndex, len);
	checkRange(dstIndex, len);

	if (srcIndex < dstIndex) {
		for (int i=len-1; i>=0; i--) {
			doReSet(dstIndex+i, doGet(srcIndex+i));
		}
	} else if (srcIndex > dstIndex) {
		for (int i=0; i<len; i++) {
			doReSet(dstIndex+i, doGet(srcIndex+i));
		}
	}
}

/**
 * Move specified elements.
 * The elements which are moved away are set to null.
 * Source and destination ranges may overlap.
 *
 * @param srcIndex	index of first source element to move
 * @param dstIndex	index of first destination element to move
 * @param len		number of elements to move
 */
public void move(int srcIndex, int dstIndex, int len) {
	checkRange(srcIndex, len);
	checkRange(dstIndex, len);

	// Copy elements
	if (srcIndex < dstIndex) {
		for (int i=len-1; i>=0; i--) {
			doReSet(dstIndex+i, doGet(srcIndex+i));
		}
	} else if (srcIndex > dstIndex) {
		for (int i=0; i<len; i++) {
			doReSet(dstIndex+i, doGet(srcIndex+i));
		}
	}

	// Set elements to null after the move operation
	if (srcIndex < dstIndex) {
		int fill = Math.min(len, dstIndex-srcIndex);
		fill(srcIndex, fill, null);
	} else if (srcIndex > dstIndex) {
		int fill = Math.min(len, srcIndex-dstIndex);
		fill(srcIndex+len-fill, fill, null);
	}
}

/**
 * Reverses the order of all elements in the specified list.
 */
public void reverse() {
	reverse(0, size());
}

/**
 * Reverses the order of the specified elements in the list.
 *
 * @param index	index of first element to reverse
 * @param len	number of elements to reverse
 */
public void reverse(int index, int len) {
	checkRange(index, len);

	int pos1 = index;
	int pos2 = index+len-1;
	int mid = len / 2;
	for (int i=0; i<mid; i++) {
		E swap = doGet(pos1);
		swap = doReSet(pos2, swap);
		doReSet(pos1, swap);
		pos1++;
		pos2--;
	}
}

/**
 * Swap the specified elements in the list.
 *
 * @param index1	index of first element in first range to swap
 * @param index2	index of first element in second range to swap
 * @param len		number of elements to swap
 * @throws 			IndexOutOfBoundsException if the ranges overlap
 */
public void swap(int index1, int index2, int len) {
	checkRange(index1, len);
	checkRange(index2, len);
	if ((index1 < index2 && index1+len > index2) ||
		index1 > index2 && index2+len > index1) {
		throw new IllegalArgumentException("Swap ranges overlap");
	}

	for (int i=0; i<len; i++) {
		E swap = doGet(index1+i);
		swap = doReSet(index2+i, swap);
		doReSet(index1+i, swap);
	}
}

/**
 * Rotate specified elements in the list.
 * The distance argument can be positive or negative:
 * If it is positive, the elements are moved towards the end,
 * if negative, the elements are moved toward the beginning,
 * if distance is 0, the list is not changed.
 *
 * @param distance	distance to move the elements
 */
public void rotate(int distance) {
	rotate(0, size(), distance);
}

/**
 * Rotate specified elements in the list.
 * The distance argument can be positive or negative:
 * If it is positive, the elements are moved towards the end,
 * if negative, the elements are moved toward the beginning,
 * if distance is 0, the list is not changed.
 *
 * @param index		index of first element to rotate
 * @param len		number of elements to rotate
 * @param distance	distance to move the elements
 */
public void rotate(int index, int len, int distance) {
	checkRange(index, len);

	int size = size();
    distance = distance % size;
    if (distance < 0) {
        distance += size;
    }
    if (distance == 0) {
        return;
    }

    int num = 0;
    for (int start=0; num != size; start++) {
        E elem = doGet(index+start);
        int i = start;
        do {
            i += distance;
            if (i >= len) {
                i -= len;
            }
            elem = doReSet(index+i, elem);
            num++;
        } while (i != start);
    }
}

/**
 * Sort elements in the list using the specified comparator.
 *
 * @param comparator	comparator to use for sorting
 * 						(null means the elements natural ordering should be used)
 *
 * @see java.util.Arrays#sort
 */
public void sort(Comparator<? super E> comparator) {
	sort(0, size(), comparator);
}

/**
 * Sort specified elements in the list using the specified comparator.
 *
 * @param index			index of first element to sort
 * @param len			number of elements to sort
 * @param comparator	comparator to use for sorting
 * 						(null means the elements natural ordering should be used)
 *
 * @see java.util.Arrays#sort
 */
abstract public void sort(int index, int len, Comparator<? super E> comparator);

/*
Question:
   Why is the signature of method binarySearch
       public <K> int binarySearch(K key, Comparator<? super K> comparator)
   and not
       public int binarySearch(E key, Comparator<? super E> comparator)
   as you could expect?
Answer:
   This allows to use the binarySearch method not only with keys of
   the type stored in the GapList, but also with any other type you
   are prepared to handle in you Comparator.
   So if we have a class Name and its comparator as defined in the
   following code snippets, both method calls are possible:
   new GapList<Name>().binarySearch(new Name("a"), new NameComparator());
   new GapList<Name>().binarySearch("a), new NameComparator());
   class Name {
       String name;
       public Name(String name) {
           this.name = name;
       }
       public String getName() {
           return name;
       }
       public String toString() {
           return name;
       }
   }
   static class NameComparator implements Comparator<Object> {
       @Override
       public int compare(Object o1, Object o2) {
           String s1;
           if (o1 instanceof String) {
               s1 = (String) o1;
           } else {
               s1 = ((Name) o1).getName();
           }
           String s2;
           if (o2 instanceof String) {
               s2 = (String) o2;
           } else {
               s2 = ((Name) o2).getName();
           }
           return s1.compareTo(s2);
       }
    }
*/

/**
 * Searches the specified range for an object using the binary
 * search algorithm.
 *
 * @param key           the value to be searched for
 * @param comparator    the comparator by which the list is ordered.
 *                      A <tt>null</tt> value indicates that the elements'
 *                      {@linkplain Comparable natural ordering} should be used.
 * @return              index of the search key, if it is contained in the array;
 *                      otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
 *                      <i>insertion point</i> is defined as the point at which the
 *                      key would be inserted into the array: the index of the first
 *                      element greater than the key, or <tt>a.length</tt> if all
 *                      elements in the array are less than the specified key.  Note
 *                      that this guarantees that the return value will be &gt;= 0 if
 *                      and only if the key is found.
 *
 * @see java.util.Arrays#binarySearch
 */
public <K> int binarySearch(K key, Comparator<? super K> comparator) {
	return binarySearch(0, size(), key, comparator);
}

/**
 * Searches the specified range for an object using the binary
 * search algorithm.
 *
 * @param index         index of first element to search
 * @param len           number of elements to search
 * @param key           the value to be searched for
 * @param comparator    the comparator by which the list is ordered.
 *                      A <tt>null</tt> value indicates that the elements'
 *                      {@linkplain Comparable natural ordering} should be used.
 * @return              index of the search key, if it is contained in the array;
 *                      otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
 *                      <i>insertion point</i> is defined as the point at which the
 *                      key would be inserted into the array: the index of the first
 *                      element greater than the key, or <tt>a.length</tt> if all
 *                      elements in the array are less than the specified key.  Note
 *                      that this guarantees that the return value will be &gt;= 0 if
 *                      and only if the key is found.
 *
 * @see java.util.Arrays#binarySearch
 */
abstract public <K> int binarySearch(int index, int len, K key, Comparator<? super K> comparator);

//--- Arguments check methods

/**
 * Check that specified index is valid for getting/setting elements.
 *
 * @param index	index to check
 * @throws IndexOutOfBoundsException if index is invalid
 */
protected void checkIndex(int index) {
	if (index < 0 || index >= size()) {
		throw new IndexOutOfBoundsException("Invalid index: " + index + " (size: " + size() + ")");
	}
}

/**
 * Check that specified index is valid for adding elements.
 *
 * @param index	index to check
 * @throws IndexOutOfBoundsException if index is invalid
 */
protected void checkIndexAdd(int index) {
	if (index < 0 || index > size()) {
		throw new IndexOutOfBoundsException("Invalid index: " + index + " (size: " + size() + ")");
	}
}

/**
 * Check that specified range is valid.
 *
 * @param index	start index of range to check
 * @param len	number of elements  in range to check
 * @throws IndexOutOfBoundsException if index is invalid
 */
protected void checkRange(int index, int len) {
	if (index < 0 || len < 0 || index+len > size()) {
		throw new IndexOutOfBoundsException("Invalid range: " + index + "/" + len + " (size: " + size() + ")");
	}
}

/**
 * Check that specified length is valid (>= 0).
 *
 * @param length length to check
 * @throws IndexOutOfBoundsException if length is invalid
 */
protected void checkLength(int length) {
    if (length < 0) {
        throw new IndexOutOfBoundsException("Invalid length: " + length);
    }
}

// --- Start class Iter ---

/**
 * Iterator supports forward and reverse iteration.
 */
class Iter implements Iterator<E> {
	/** true if iterator moves forward */
	boolean forward;
	/** current index */
	int index;
	/** index where element will be removed if remove() is called */
	int remove;

	/**
	 * Constructor.
	 *
	 * @param forward	true for forward access
	 */
	public Iter(boolean forward) {
		this.forward = forward;

		if (forward) {
			index = 0;
		} else {
			index = size()-1;
		}
		remove = -1;
	}

	@Override
	public boolean hasNext() {
		if (forward) {
			return index != size();
		} else {
			return index != -1;
		}
	}

	@Override
	public E next() {
		if (forward) {
			if (index >= size()) {
				throw new NoSuchElementException();
			}
		} else {
			if (index < 0) {
				throw new NoSuchElementException();
			}
		}
		E elem = get(index);
		remove = index;
		if (forward) {
			index++;
		} else {
			index--;
		}
		return elem;
	}

	@Override
	public void remove() {
		if (remove == -1) {
			throw new IllegalStateException("No current element to remove");
		}
		iList.this.remove(remove);
		if (index > remove) {
			index--;
		}
		remove = -1;
	}
}

// --- End class Iter ---

// --- Start class ListIter ---

/**
 * Iterator supports forward and reverse iteration.
 */
class ListIter implements ListIterator<E> {
	/** current index */
	int index;
	/** index where element will be removed if remove() is called */
	int remove;

	/**
	 * Constructor.
	 *
	 * @param index	start index
	 */
	public ListIter(int index) {
		checkIndexAdd(index);
			this.index = index;
		this.remove = -1;
	}

	@Override
	public boolean hasNext() {
		return index < size();
	}

	@Override
	public boolean hasPrevious() {
		return index > 0;
	}

	@Override
	public E next() {
		if (index >= size()) {
			throw new NoSuchElementException();
		}
		E elem = iList.this.get(index);
		remove = index;
		index++;
		return elem;
	}

	@Override
	public int nextIndex() {
		return index;
	}

	@Override
	public E previous() {
		if (index <= 0) {
			throw new NoSuchElementException();
		}
		index--;
		E elem = iList.this.get(index);
		remove = index;
		return elem;
	}

	@Override
	public int previousIndex() {
		return index-1;
	}

	@Override
	public void remove() {
		if (remove == -1) {
			throw new IllegalStateException("No current element to remove");
		}
		iList.this.remove(remove);
		if (index > remove) {
			index--;
		}
		remove = -1;
	}

	@Override
	public void set(E e) {
		if (remove == -1) {
			throw new IllegalStateException("No current element to set");
		}
		iList.this.set(remove, e);
	}

	@Override
	public void add(E e) {
		iList.this.add(index, e);
		index++;
		remove = -1;
	}
}

// --- End class ListIter ---

}
