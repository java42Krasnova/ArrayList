package telran.util;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CARACITY = 2;
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
		if (index < 0 || index > size) {
			return false;
		}
		if (index == size || size == array.length) {
			allocate();
		}
		@SuppressWarnings("unchecked")
		T tmpArray[] = (T[]) new Object[array.length];
		System.arraycopy(array, 0, tmpArray, 0, index);
		tmpArray[index] = element;
		System.arraycopy(array, index, tmpArray, index + 1, size - index);
		array = tmpArray;
		size++;
		return true;

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
		T res = get(index);
		if (res != null) {
			@SuppressWarnings("unchecked")
			T tmpArray[] = (T[]) new Object[array.length];
			System.arraycopy(array, 0, tmpArray, 0, index);
			System.arraycopy(array, index + 1, tmpArray, index, array.length - index - 1);
			size--;
			array = tmpArray;
		}
		return res;
	}

}
