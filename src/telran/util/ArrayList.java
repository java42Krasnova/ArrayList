package telran.util;

import java.util.Arrays;
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
		if (size == array.length) {
			allocate();
		}
		array[size++] = element;
	}

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
			size--;
			System.arraycopy(array, index + 1, array, index, size - index);
		}

		return res;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		for (int i = 0; i < size; i++) {
			if (array[i].equals(pattern)) {
				res = true;
				break;
			}
		}
		return res;
	}

	@Override
	public int indexOf(T pattern) {

		int res = -1;
		for (int i = 0; i < size; i++) {
			if (array[i].equals(pattern)) {
				res = i;
				break;
			}
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {

		int res = -1;
		for (int i = size - 1; i >= 0; i--) {
			if (array[i].equals(pattern)) {
				res = i;
				break;
			}
		}
		return res;
	}

	@Override
	public boolean contains(Predicate<T> predicate) {
		boolean res = false;
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				res = true;
				break;
			}
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

		boolean res = false;
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				remove(i);
				res= true;
			}
		}
	
		return res;
	}
}
