package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CARACITY = 2;//YG DEFAULT_CAPACITY = 16
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
		//[YG] no need to separate that check
		if(index==size-1)
		{
			array[index]=null;
			size--;
			return res;
		}
		//[YG] better to apply System.arraycopy
		for(int i=index; i<size;i++)
		{
		array[i]=array[i+1];
		}
		size--;
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		for(int i =0; i<size; i++)
		{
			if (array[i].equals(pattern))
			{
				res = true;
				break;
			}
		}
return res;
	}

	@Override
	public int indexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Predicate<T> predicate) {
		boolean res = false;
		for(int i =0; i<size; i++)
		{
			if (predicate.test(array[i]))
			{
				res = true;
				break;
			}
		}
return res;
		
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

}
