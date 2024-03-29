package com.mygdx.game.uChumpClasses.Core.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

//why the heck does this have assert in it
/**
 * http://www.magicwerk.org/page-collections-overview.html
 *
 * GapList combines the strengths of both ArrayList and LinkedList.
 * It is implemented to offer both efficient random access to elements
 * by index (as ArrayList does) and at the same time efficient adding
 * and removing elements to and from beginning and end (as LinkedList does).
 * It also exploits the locality of reference often seen in applications
 * to further improve performance, e.g. for iterating over the list.
 * <p>
 * <strong>Note that this implementation is not synchronized.</s>
 *
 * NOTE: This list has been slightly modified (debug removed, added maxCapacity)
 */
public class GapList<E> extends iList<E> {

    // -- EMPTY --

    // Cannot make a static reference to the non-static type E:
    // public static GapList<E> EMPTY = GapList.create().unmodifiableList();
    // Syntax error:
    // public static <EE> GapList<EE> EMPTY = GapList.create().unmodifiableList();

    /** Unmodifiable empty instance */
    @SuppressWarnings("rawtypes")
    private static final GapList EMPTY = GapList.create().unmodifiableList();

    /**
     * @return unmodifiable empty instance
     */
    @SuppressWarnings("unchecked")
    public static <EE> GapList<EE> EMPTY() {
        return EMPTY;
    }


    /**
     * An immutable version of a GapList.
     * Note that the client cannot change the list,
     * but the content may change if the underlying list is changed.
     */
    protected static class ImmutableGapList<E> extends GapList<E> {

        /** UID for serialization */
        private static final long serialVersionUID = -1352274047348922584L;

        /**
         * Private constructor used internally.
         *
         * @param that  list to create an immutable view of
         */
        protected ImmutableGapList(GapList<E> that) {
            super(true, that);
        }

        @Override
        protected boolean doAdd(int index, E elem) {
        	error();
        	return false;
        }

        @Override
        protected boolean doAddAll(int index, E[] elems) {
        	error();
        	return false;
        }

        @Override
        protected E doSet(int index, E elem) {
        	error();
        	return null;
        }

        @Override
        protected void doSetAll(int index, E[] elems) {
        	error();
        }

        @Override
        protected E doReSet(int index, E elem) {
        	error();
        	return null;
        }

        @Override
        protected E doRemove(int index) {
        	error();
        	return null;
        }

        @Override
        protected void doRemoveAll(int index, int len) {
        	error();
        }

        @Override
        protected void doModify() {
        	error();
        }

        /**
         * Throw exception if an attempt is made to change an immutable list.
         */
        private void error() {
            throw new UnsupportedOperationException("list is immutable");
        }
    };

    /** UID for serialization */
    private static final long serialVersionUID = -4477005565661968383L;

    /** Default capacity for list */
    public static final int DEFAULT_CAPACITY = 10;

	/** Array holding raw data */
	private E[] values;
	/** Number of elements stored in this GapList */
	private int size;
	/** Physical position of first element */
	private int start;
	/** Physical position after last element */
	private int end;
	/** Size of gap (0 if there is no gap) */
	private int gapSize;
	/** Logical index of first element after gap (ignored if gapSize=0) */
	private int gapIndex;
	/** Physical position of first slot in gap (ignored if gapSize=0) */
	private int gapStart;
    /** Max limit of list */
    private int maxCapacity;


	// --- Static methods ---

    /**
     * Create new list.
     *
     * @return          created list
     * @param <E>       type of elements stored in the list
     */
    // This separate method is needed as the varargs variant creates the GapList with specific size
    public static <E> GapList<E> create() {
        return new GapList<E>();
    }

    /**
     * Create new list with specified capacity.
     *
     * @param capacity  capacity
     * @return          created list
     * @param <E>       type of elements stored in the list
     */
    public static <E> GapList<E> create(int capacity) {
        return new GapList<E>(capacity);
    }

    /**
     * Create new list with specified elements.
     *
     * @param coll      collection with element
     * @return          created list
     * @param <E>       type of elements stored in the list
     */
	public static <E> GapList<E> create(Collection<? extends E> coll) {
		return new GapList<E>(coll);
	}

	/**
	 * Create new list with specified elements.
	 *
	 * @param elems 	array with elements
	 * @return 			created list
	 * @param <E> 		type of elements stored in the list
	 */
	public static <E> GapList<E> create(E... elems) {
		return new GapList<E>(elems);
	}

	/**
	 * Calculate index for physical access to an element.
	 *
	 * @param idx	logical index of element
	 * @return		physical index to access element in values[]
	 */
	private final int physIndex(int idx) {
		int physIdx = idx+start;
		if (idx >= gapIndex) {
			physIdx += gapSize;
		}
		if (physIdx >= values.length) {
			physIdx -= values.length;
		}
		return physIdx;
	}

	/**
	 * Calculate indexes for physical access to a range of elements.
	 * The method returns between one and three ranges of physical indexes.
	 *
	 * @param idx0  start index
	 * @param idx1	end index
	 * @return		array with physical start and end indexes (may contain 2, 4, or 6 elements)
	 */
	private int[] physIndex(int idx0, int idx1) {
		assert(idx0 >=0 && idx1 <= size && idx0 <= idx1);

		if (idx0 == idx1) {
			return new int[0];
		}

		// Decrement idx1 to make sure we get the physical index
		// of an existing position. We will increment the physical index
		// again before returning.
		idx1--;
		int pidx0 = physIndex(idx0);
		if (idx1 == idx0) {
			return new int[] {
				pidx0, pidx0+1
			};
		}

		int pidx1 = physIndex(idx1);
		if (pidx0 < pidx1) {
			if (gapSize > 0 && pidx0 < gapStart && pidx1 > gapStart) {
				assert(pidx0 < gapStart);
				assert(gapStart+gapSize < pidx1+1);

				return new int[] {
					pidx0, gapStart,
					gapStart+gapSize, pidx1+1
				};
			} else {
				return new int[] {
					pidx0, pidx1+1
				};
			}
		} else {
			assert(pidx0 > pidx1);
			assert(start != 0);
			if (gapSize > 0 && pidx1 > gapStart && gapStart > 0) {
				assert(pidx0 < values.length);
				assert(0 < gapStart);
				assert(gapStart+gapSize < pidx1+1);

				return new int[] {
					pidx0, values.length,
					0, gapStart,
					gapStart+gapSize, pidx1+1
				};
			} else if (gapSize > 0 && pidx0 < gapStart && gapStart+gapSize < values.length) {
				assert(pidx0 < gapStart);
				assert(gapStart+gapSize < values.length);
				assert(0 < pidx1+1);

				return new int[] {
					pidx0, gapStart,
					gapStart+gapSize, values.length,
					0, pidx1+1
				};
			} else {
				assert(pidx0 < values.length);
				assert(0 < pidx1+1);

				int end = values.length;
				if (gapSize > 0 && gapStart > pidx0) {
					end = gapStart;
				}
				int start = 0;
				if (gapSize > 0 &&  (gapStart+gapSize)%values.length < pidx1+1) {
					start = (gapStart+gapSize)%values.length;
				}

				return new int[] {
					pidx0, end,
					start, pidx1+1
				};
			}
		}
	}

	@Override
	protected void doAssign(iList<E> that) {
		GapList<E> list = (GapList<E>) that;
        this.values = list.values;
        this.size = list.size;
        this.start = list.start;
        this.end = list.end;
        this.gapSize = list.gapSize;
        this.gapIndex = list.gapIndex;
        this.gapStart = list.gapStart;
	}

    /**
     * Constructor used internally, e.g. for ImmutableGapList.
     *
     * @param copy true to copy all instance values from source,
     *             if false nothing is done
     * @param that list to copy
     */
    protected GapList(boolean copy, GapList<E> that) {
        if (copy) {
        	doAssign(that);
        }
    }

	/**
	 * Construct a list with the default initial capacity.
	 */
	public GapList() {
		init();
	}

    /**
     * Construct a list with specified initial capacity.
     *
     * @param capacity capacity
     */
    public GapList(int capacity) {
        init(capacity);
    }

    /**
     * Construct a list with specified initial capacity.
     *
     * @param capacity capacity
     */
    public GapList(int capacity, int maxCapacity) {
        init(capacity);
        this.maxCapacity = maxCapacity;
    }

	/**
	 * Construct a list to contain the specified elements.
	 * The list will have an initial capacity to hold these elements.
	 *
	 * @param coll	collection with elements
	 */
	public GapList(Collection<? extends E> coll) {
		init(coll);
	}

	/**
	 * Construct a list to contain the specified elements.
	 * The list will have an initial capacity to hold these elements.
	 *
	 * @param elems	array with elements
	 */
	public GapList(E... elems) {
		init(elems);
	}

	/**
	 * Initialize the list to be empty.
	 * The list will have the default initial capacity.
	 */
	public void init() {
		init(new Object[DEFAULT_CAPACITY], 0);
	}

	/**
     * Initialize the list to be empty with specified initial capacity.
     *
	 * @param capacity capacity
	 */
	public void init(int capacity) {
		init(new Object[capacity], 0);
	}

	/**
	 * Initialize the list to contain the specified elements only.
	 * The list will have an initial capacity to hold these elements.
	 *
	 * @param coll collection with elements
	 */
	public void init(Collection<? extends E> coll) {
		Object[] array = toArray(coll);
		init(array, array.length);
	}

	/**
     * Initialize the list to contain the specified elements only.
	 * The list will have an initial capacity to hold these elements.
     *
	 * @param elems array with elements
	 */
	public void init(E... elems) {
		Object[] array = elems.clone();
		init(array, array.length);
	}

	@Override
	public E getDefaultElem() {
		return null;
	}

	@Override
    public GapList<E> copy() {
	    return (GapList<E>) super.copy();
	}

	@Override
    public GapList<E> unmodifiableList() {
        // Naming as in java.util.Collections#unmodifiableList
        return new ImmutableGapList<E>(this);
    }

	@Override
	protected void doClone(iList<E> that) {
		// Do not simply clone the array, but make sure its capacity
		// is equal to the size (as in ArrayList)
		init(that.toArray(), that.size());
	}

	/**
	 * Normalize data of GapList so the elements are found
	 * from values[0] to values[size-1].
	 * This method can help to speed up operations like sort or
	 * binarySearch.
	 */
	private void normalize() {
		if (start == 0 && end == 0 && gapSize == 0 && gapStart == 0 && gapIndex == 0) {
			return;
		}
		init(toArray(), size());
	}

	/**
	 * Initialize all instance fields.
	 *
	 * @param values	new values array
	 * @param size		new size
	 */
	@SuppressWarnings("unchecked")
	void init(Object[] values, int size) {
		this.values = (E[]) values;
		this.size = size;

		start = 0;
		end = 0;
		gapSize = 0;
		gapStart = 0;
		gapIndex = 0;
        maxCapacity = -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		return values.length;
	}

    @Override
    public E get(int index) {
        // A note about the inlining capabilities of the Java HotSpot Performance Engine
        // (http://java.sun.com/developer/technicalArticles/Networking/HotSpot/inlining.html)
        // The JVM seems not able to inline the methods called within
        // this method, irrespective whether they are "private final" or not.
        // Also -XX:+AggressiveOpts seems not to help.
        // We therefore do inlining manually.

        // INLINE: checkIndex(index);
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " (size: " + size() + ")");
        }
        return doGet(index);
    }

    @Override
    protected E doGet(int index) {
        assert (index >= 0 && index < size);

        // INLINE: return values[physIndex(index)];
        int physIdx = index+start;
        if (index >= gapIndex) {
            physIdx += gapSize;
        }
        if (physIdx >= values.length) {
            physIdx -= values.length;
        }
        return values[physIdx];
    }

	@Override
    protected E doSet(int index, E elem) {
        assert (index >= 0 && index < size);

        int physIdx = physIndex(index);
        E oldElem = values[physIdx];
        values[physIdx] = elem;
        return oldElem;
    }

    @Override
    protected E doReSet(int index, E elem) {
        assert (index >= 0 && index < size);

        int physIdx = physIndex(index);
        E oldElem = values[physIdx];
        values[physIdx] = elem;
        return oldElem;
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

	@Override
	public GapList<E> getAll(int index, int len) {
		return (GapList<E>) super.getAll(index, len);
	}

	@Override
	public GapList<E> getAll(E elem) {
		return (GapList<E>) super.getAll(elem);
	}

	@Override
	protected boolean doAdd(int index, E elem) {
        doEnsureCapacity(size + 1);

		if (index == -1) {
		    index = size;
		}
        assert(index >= 0 && index <= size);

        int physIdx;
		// Add at last position
		if (index == size && (end != start || size == 0)) {
			physIdx = end;
			end++;
			if (end >= values.length) {
				end -= values.length;
			}

		// Add at first position
		} else if (index == 0 && (end != start || size == 0)) {
			start--;
			if (start < 0) {
				start += values.length;
			}
			physIdx = start;
			if (gapSize > 0) {
				gapIndex++;
			}

		// Shrink gap
		} else if (gapSize > 0 && index == gapIndex) {
			physIdx = gapStart+gapSize-1;
			if (physIdx >= values.length) {
				physIdx -= values.length;
			}
			gapSize--;

		// Add at other positions
		} else {
			physIdx = physIndex(index);

			if (gapSize == 0) {
				// Create new gap
				if (start < end && start > 0) {
					// S4: Space is at head and tail
					assert(debugState() == 4);
					int len1 = physIdx-start;
					int len2 = end-physIdx;
					if (len1 <= len2) {
						moveData(start, 0, len1);
						gapSize = start-1;
						gapStart = len1;
						gapIndex = len1;
						start = 0;
						physIdx--;
					} else {
						moveData(physIdx, values.length-len2, len2);
						gapSize = values.length-end-1;
						gapStart = physIdx+1;
						gapIndex = index+1;
						end = 0;
					}

				} else if (physIdx < end) {
					assert(debugState() == 2 || debugState() == 5);
					int len = end-physIdx;
					int rightSize = (start-end+values.length)%values.length;
					moveData(physIdx, end+rightSize-len, len);
					end = start;
					gapSize = rightSize-1;
					gapStart = physIdx+1;
					gapIndex = index+1;

				} else {
					assert(debugState() == 3 || debugState() == 5);
					assert(physIdx > end);
					int len = physIdx-start;
					int rightSize = start-end;
					moveData(start, end, len);
					start -= rightSize;
					end = start;
					gapSize = rightSize-1;
					gapStart = start+len;
					gapIndex = index;
					physIdx--;
				}
			} else {
				// Move existing gap
				boolean moveLeft;
				int gapEnd = (gapStart+gapSize-1) % values.length + 1;
				if (gapEnd < gapStart) {
					assert(debugState() == 9 || debugState() == 12);
					// Gap is at head and tail
					int len1 = physIdx-gapEnd;
					int len2 = gapStart-physIdx-1;
					if (len1 <= len2) {
						moveLeft = true;
					} else {
						moveLeft = false;
					}

				} else {
					assert(debugState() == 6 || debugState() == 7 || debugState() == 8 || debugState() == 9 || debugState() == 10 ||
						debugState() == 11 || debugState() == 12 || debugState() == 13 || debugState() == 14 || debugState() == 15);
					if (physIdx > gapStart) {
						moveLeft = true;
					} else  {
						moveLeft = false;
					}
				}
				if (moveLeft) {
					int src = gapStart+gapSize;
					int dst = gapStart;
					int len = physIdx-gapEnd;
					moveGap(src, dst, len);
					physIdx--;
					gapSize--;
					gapIndex = index;
					gapStart += len;
					if (gapStart >= values.length) {
						gapStart -= values.length;
					}

					if (index == 0) {
						start = physIdx;
						if ((gapStart + gapSize) % values.length == start) {
							end = gapStart;
							gapSize = 0;
						}
					}
				} else {
					int src = physIdx;
					int dst = physIdx+gapSize;
					int len = gapStart-physIdx;
					moveGap(src, dst, len);
					gapSize--;
					gapStart = physIdx+1;
					gapIndex = index+1;

					if (index == 0) {
						start = physIdx;
						end = physIdx;
					} else if (index == size) {
						if ((gapStart + gapSize) % values.length == start) {
							end = gapStart;
							gapSize = 0;
						}
					}

				}
			}
		}

		values[physIdx] = elem;
		size++;

		return true;
	}

	/**
	 * Move a range of elements in the values array.
	 * The elements are first copied and the source range is then
	 * filled with null.
	 *
	 * @param src	start index of source range
	 * @param dst	start index of destination range
	 * @param len	number of elements to move
	 */
	private void moveGap(int src, int dst, int len) {
		if (src > values.length) {
			src -= values.length;
		}
		if (dst > values.length) {
			dst -= values.length;
		}
		assert(len >= 0);
		assert(src+len <= values.length);

		if (start >= src && start < src+len) {
			start += dst-src;
			if (start >= values.length) {
				start -= values.length;
			}
		}
		if (end >= src && end < src+len) {
			end += dst-src;
			if (end >= values.length) {
				end -= values.length;
			}
		}
		if (dst+len <= values.length) {
			moveData(src, dst, len);
		} else {
			// Destination range overlaps end of range so do the
			// move in two calls
			int len2 = dst+len - values.length;
			int len1 = len - len2;
			if (!(src <= len2 && len2 < dst)) {
				moveData(src+len1, 0, len2);
				moveData(src, dst, len1);
			} else {
				moveData(src, dst, len1);
				moveData(src+len1, 0, len2);
			}
		}
	}

	/**
	 * Move a range of elements in the values array.
	 * The elements are first copied and the source range is then
	 * filled with null.
	 *
	 * @param src	start index of source range
	 * @param dst	start index of destination range
	 * @param len	number of elements to move
	 */
	void moveData(int src, int dst, int len) {
		System.arraycopy(values, src, values, dst, len);

		// Write null into array slots which are not used anymore
		// This is necessary to allow GC to reclaim non used objects.
		if (src <= dst) {
			int start = src;
			int end = (dst < src+len) ? dst : src+len;
			assert(end-start <= len);
			for (int i=start; i<end; i++) {
				values[i] = null;
			}
		} else {
			int start = (src > dst+len) ? src : dst+len;
			int end = src+len;
			assert(end-start <= len);
			for (int i=start; i<end; i++) {
				values[i] = null;
			}
		}
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		return doRemove(index);
	}

	@Override
	protected E doRemove(int index) {
		int physIdx;

		// Remove at last position
		if (index == size-1) {

			end--;
			if (end < 0) {
				end += values.length;
			}
			physIdx = end;

			// Remove gap if it is followed by only one element
			if (gapSize > 0) {
				if (gapIndex == index) {
					end = gapStart;
					gapSize = 0;
				}
			}

		// Remove at first position
		} else if (index == 0) {

			physIdx = start;
			start++;
			if (start >= values.length) {
				start -= values.length;
			}

			// Remove gap if if it is preceded by only one element
			if (gapSize > 0) {
				if (gapIndex == 1) {
					start += gapSize;
					if (start >= values.length) {
						start -= values.length;
					}
					gapSize = 0;
				} else {
					gapIndex--;
				}
			}

		} else {
			// Remove in middle of list
			physIdx = physIndex(index);

			// Create gap
			if (gapSize == 0) {
				gapIndex = index;
				gapStart = physIdx;
				gapSize = 1;

			// Extend existing gap at tail
			} else if (index == gapIndex) {
				gapSize++;

			// Extend existing gap at head
			} else if (index == gapIndex-1) {
				gapStart--;
				if (gapStart < 0) {
					gapStart += values.length;
				}
				gapSize++;
				gapIndex--;

			} else {
				// Move existing gap
				assert(gapSize > 0);

				boolean moveLeft;
				int gapEnd = (gapStart+gapSize-1) % values.length + 1;
				if (gapEnd < gapStart) {
					// Gap is at head and tail: check where fewer
					// elements must be moved
					int len1 = physIdx-gapEnd;
					int len2 = gapStart-physIdx-1;
					if (len1 <= len2) {
						moveLeft = true;
					} else {
						moveLeft = false;
					}

				} else {
					if (physIdx > gapStart) {
						// Existing gap is left of insertion point
						moveLeft = true;
					} else  {
						// Existing gap is right of insertion point
						moveLeft = false;
					}
				}
				if (moveLeft) {
					int src = gapStart+gapSize;
					int dst = gapStart;
					int len = physIdx-gapEnd;
					moveGap(src, dst, len);
					gapStart += len;
					if (gapStart >= values.length) {
						gapStart -= values.length;
					}
					gapSize++;

				} else {
					int src = physIdx+1;
					int dst = physIdx+gapSize+1;
					int len = gapStart-physIdx-1;
					moveGap(src, dst, len);
					gapStart = physIdx;
					gapSize++;
				}
				gapIndex = index;
			}
		}

		E removed = values[physIdx];
		values[physIdx] = null;
		size--;
		return removed;
	}

	//SuppressWarnings added by ral
    @SuppressWarnings("unchecked")
	@Override
    protected void doEnsureCapacity(int minCapacity) {
		// Note: Same behavior as in ArrayList.ensureCapacity()
		int oldCapacity = values.length;
		if (minCapacity <= oldCapacity) {
			return;	// do not shrink
		}
	    int newCapacity = (oldCapacity*3)/2 + 1;
	    if (newCapacity < minCapacity) {
	    	newCapacity = minCapacity;
    	}
        System.out.println(newCapacity);
        if (maxCapacity != -1) {
            if (maxCapacity <= minCapacity) {
                throw new IllegalStateException("Max GapList size of " + maxCapacity + " reached");
            }
            if (maxCapacity < newCapacity) {
                newCapacity = maxCapacity;
            }
        }
        System.out.println(newCapacity);

		E[] newValues = null;
		if (start == 0) {
			newValues = Arrays.copyOf(values, newCapacity);
		} else if (start > 0) {
			int grow = newCapacity-values.length;
			newValues = (E []) new Object[newCapacity];
			System.arraycopy(values, 0, newValues, 0, start);
			System.arraycopy(values, start, newValues, start+grow, values.length-start);
			if (gapStart > start && gapSize > 0) {
				gapStart += grow;
			}
			start += grow;
		}
		if (end == 0 && size != 0) {
			end = values.length;
		}
		values = newValues;

	}

    @Override
	public void trimToSize() {
        doModify();

    	if (size < values.length) {
    		init(toArray(), size);
    	}
    }

	@Override
	protected <T> void doGetAll(T[] array, int index, int len) {
		int[] physIdx = physIndex(index, index+len);
		int pos = 0;
        for (int i=0; i<physIdx.length; i+=2) {
        	int num = physIdx[i+1] - physIdx[i];
        	System.arraycopy(values, physIdx[i], array, pos, num);
        	pos += num;
        }
        assert(pos == len);
	}


	// --- Serialization ---

    /**
     * Serialize a GapList object.
     *
     * @serialData The length of the array backing the <tt>GapList</tt>
     *             instance is emitted (int), followed by all of its elements
     *             (each an <tt>Object</tt>) in the proper order.
     * @param oos  output stream for serialization
     * @throws 	   java.io.IOException if serialization fails
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // Write out array length
	    int size = size();
        oos.writeInt(size);

        // Write out all elements in the proper order.
        for (int i=0; i<size; i++) {
        	oos.writeObject(doGet(i));
        }
    }

    /**
     * Deserialize a GapList object.
 	 *
     * @param ois  input stream for serialization
     * @throws 	   java.io.IOException if serialization fails
     * @throws 	   ClassNotFoundException if serialization fails
     */
    @SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // Read in array length and allocate array
        size = ois.readInt();
        values = (E[]) new Object[size];

        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            values[i] = (E) ois.readObject();
        }
    }

    @Override
    public iList<E> doCreate(int capacity) {
    	if (capacity == -1) {
    		capacity = DEFAULT_CAPACITY;
    	}
    	return new GapList<E>(capacity);
    }

    @Override
	protected void doRemoveAll(int index, int len) {
		if (len == size()) {
			doModify();
			init(values, 0);
		} else {
			super.doRemoveAll(index, len);
		}
	}

    @Override
    public void sort(int index, int len, Comparator<? super E> comparator) {
    	checkRange(index, len);

    	normalize();
    	Arrays.sort(values, index, index+len, comparator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K> int binarySearch(int index, int len, K key, Comparator<? super K> comparator) {
    	checkRange(index, len);

    	normalize();
    	return Arrays.binarySearch((Object[]) values, index, index+len, key, (Comparator<Object>) comparator);
    }

	// --- Helper methods for debugging ---

	/**
	 * Private method to check invariant of GapList.
	 * It is only used for debugging.
	 */
//	private void debugCheck() {
//		// If the GapList is not used for storing content in KeyListImpl, values may be null
//		if (values == null) {
//			assert(size == 0 && start == 0 && end == 0);
//			assert(gapSize == 0 && gapStart == 0 && gapIndex == 0);
//			return;
//		}
//
//		assert(size >= 0 && size <= values.length);
//		assert(start >=0 && (start < values.length || values.length == 0));
//		assert(end >= 0 && (end < values.length || values.length == 0));
//		assert(values.length == 0 || (start+size+gapSize) % values.length == end);
//
//		// Check that logical gap index is correct
//		assert(gapSize >= 0);
//		if (gapSize > 0) {
//			assert(gapStart >= 0 && gapStart < values.length);
//			// gap may not be at start or end
//			assert(gapIndex > 0 && gapIndex < size);
//			// gap start may not be the same as start or end
//			assert(gapStart != start && gapStart != end);
//			// check that logical and phyiscal gap index are correct
//			assert(physIndex(gapIndex) == (gapStart+gapSize) % values.length);
//		}
//
//		// Check that gap positions contain null values
//		if (gapSize > 0) {
//			for (int i=gapStart; i<gapStart+gapSize; i++) {
//				int pos = (i % values.length);
//				assert(values[pos] == null);
//			}
//		}
//
//		// Check that all end positions contain null values
//		if (end != start) {
//			for (int i=end; i<start; i++) {
//				int pos = (i % values.length);
//				assert(values[pos] == null);
//			}
//		}
//	}

	/**
	 * Private method to determine state of GapList.
	 * It is only used for debugging.
	 *
	 * @return	state in which GapList is
	 */
	private int debugState() {
		if (size == 0) {
			return 0;
		} else if (size == values.length) {
			return 1;
		} else if (gapSize == 0) {
			if (start == 0) {
				return 2;
			} else if (end == 0) {
				return 3;
			} else if (start < end) {
				return 4;
			} else if (start > end) {
				return 5;
			}
		} else if (gapSize > 0) {
			if (start == end) {
				if (start == 0) {
					return 6;
				} else if (gapStart < start) {
					return 7;
				} else if (gapStart > start) {
					int gapEnd = (gapStart+gapSize) % values.length;
					if (gapEnd > gapStart) {
						return 8;
					} else if (gapEnd < gapStart) {
						return 9;
					}
				}
			} else if (start != end) {
				if (start == 0) {
					return 10;
				} else if (gapStart < start) {
					return 14; //
				} else if (gapStart > start) {
					int gapEnd = (gapStart+gapSize) % values.length;
					if (gapEnd < gapStart) {
						return 12;
					} else {
						if (end == 0) {
							return 11;
						} else if (end > start) {
							return 13;
						} else if (end < start) {
							return 15;
						}
					}
				}
			}
		}
		assert(false);
		return -1;
	}

	/**
	 * Private method to dump fields of GapList.
	 * It is only called if the code is run in development mode.
	 */
//	private void debugDump() {
//		debugLog("values: size= " +values.length + ", data= "+ debugPrint(values));
//		debugLog("size=" + size + ", start=" + start + ", end=" + end +
//				", gapStart=" + gapStart + ", gapSize=" + gapSize + ", gapIndex=" + gapIndex);
//		debugLog(toString());
//	}

	/**
	 * Print array values into string.
	 *
	 * @param values	array with values
	 * @return			string representing array values
	 */
//	private String debugPrint(E[] values) {
//		StringBuilder buf = new StringBuilder();
//		buf.append("[ ");
//		for (int i=0; i<values.length; i++) {
//			if (i > 0) {
//				buf.append(", ");
//			}
//			buf.append(values[i]);
//		}
//		buf.append(" ]");
//		return buf.toString();
//	}


	/**
	 * Private method write logging output.
	 * It is only used for debugging.
	 *
	 * @param msg message to write out
	 */
//	private void debugLog(String msg) { //TODO
//	}

}