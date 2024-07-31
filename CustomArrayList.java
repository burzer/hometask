import java.util.Arrays;
import java.util.Comparator;

public class CustomArrayList<E> {

    private Object [] elements;
    private int size;

    public CustomArrayList(){
        elements = new Object[10];
    }

    //проверка есть ли мество в массиве
    private void ensureCapacity(){
        if(size == elements.length){
            Object[] newElements = new Object[elements.length * 2];
            for(int i = 0; i < elements.length; i++){
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    public void add(E e){
        ensureCapacity();
        elements[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public E get(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    public void remove(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for(int i = index; i < size - 1; i++){
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (elements[index] == null) {
                    remove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(elements[index])) {
                    remove(index);
                    return true;
                }
            }
        }
        return false;
    }


    public int size(){
        return size;
    }



    public boolean isEmpty(){
        return size == 0;
    }
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }


    public void sort(Comparator<? super E> c) {
        if (size > 1) {
            mergeSort((E[]) elements, 0, size - 1, c);
        }
    }

    private void mergeSort(E[] array, int left, int right, Comparator<? super E> c) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, c);
            mergeSort(array, mid + 1, right, c);
            merge(array, left, mid, right, c);
        }
    }

    private void merge(E[] array, int left, int mid, int right, Comparator<? super E> c) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        E[] L = Arrays.copyOfRange(array, left, mid + 1);
        E[] R = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (c.compare(L[i], R[j]) <= 0) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }


    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList();
        list.add(35);
        list.add(22);
        list.add(29);
        list.add(31);
        list.add(21);

        list.remove(3);
        list.remove(Integer.valueOf(2));

        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + ", ");
        }

        list.sort(Integer::compareTo);

        System.out.println("\nmerdge sort");

        for(int i = 0; i < list.size(); i++){
            System.out.print( list.get(i) + ", " );
        }
        list.clear();
        System.out.println("\nIs list empty now ? : " + list.isEmpty());
    }
}
