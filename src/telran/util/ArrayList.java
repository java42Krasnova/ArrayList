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
		
		if (size == array.length) {
			add(element);
			return true;
		}
		
		System.arraycopy(array, index, array, index+1, size-index);
		size++;
		array[index]=element;
		
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
		if (res == null) {
			return res;
		}
		
		if(index==size-1)
		{
			array[index]=null;
			size--;
			return res;
		}
		for(int i=index; i<size;i++)
		{
		array[i]=array[i+1];
		}
		size--;
		return res;
	}

}
