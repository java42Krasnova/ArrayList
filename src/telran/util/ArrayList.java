package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CARACITY = 16;
	private T[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];

	}

	public ArrayList() {
		this(DEFAULT_CARACITY);

	}

	public void add(T element) {
		// V.R. It isn't tested carefully, coverage isn't 100%
		if (size == array.length) {
			allocate();
		}
		array[size++] = element;
	}

	// V.R. It isn't tested carefully, coverage isn't 100%
	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);

	}

	@Override

	public boolean add(int index, T element) {
		boolean res = false;
		if (index == size) {
			add(element);
			res = true;

		} else if (isValidIndex(index)) {
			res = true;
			// V.R. It isn't tested carefully, coverage isn't 100%
			if (size == array.length) {
				allocate();
			}
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = element;
			size++;
		}
		return res;

	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public T get(int index) {
		return isValidIndex(index) ? array[index] : null;
	}

	private boolean isValidIndex(int index) {
		return index >= 0 && index < size;
	}

	@Override
	public T remove(int index) {
		T res = null;
		if (isValidIndex(index)) {
			res = array[index];
			System.arraycopy(array, index + 1, array, index, size - index - 1);
			size--;
		}
		return res;
	}
	
	@Override
	public int indexOf(Predicate<T> predicate) {

		int res = -1;
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				res = i;
				break;
			}
		}
		return res;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {

		int res = -1;
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test(array[i])) {
				res = i;
				break;
			}
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {

		int startLength = size;

		for (int i = startLength - 1; i >= 0; i--) {
			if (predicate.test(array[i])) {
				remove(i);
			}
		}
		return startLength > size;
	}

	@Override
	public void sort(Comparator<T> comp) {
		//Done
		/* V.R.
		 *  1. This code is incorrect because the length of output array is less
		 *  than length of source array.
		 *  2. The algorithm isn't effective.
		 */
		T[] tmpArr = (T[]) new Object [size];
		for(int i=0; i<size; i++)
		{
			tmpArr[i]=array[i];
		}
		 Arrays.sort(tmpArr, comp);
		 array = tmpArr;
		
	}
}
