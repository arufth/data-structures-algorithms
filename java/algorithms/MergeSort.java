import java.util.Arrays;
import java.lang.reflect.Array;

/**
 * A class that provides static methods to perform merge sort on arrays.
 */
public class MergeSort {

  /**
   * Sorts the given array using the merge sort algorithm.
   *
   * @param <T> the type of elements in the array, which must be comparable
   * @param arr the array to be sorted
   * @return a new array containing the sorted elements
   */
  public static <T extends Comparable<T>> T[] mergeSort(T[] arr) {
    if (arr.length <= 1) {
      return arr;
    }
    int middle = arr.length / 2;

    T[] left = Arrays.copyOfRange(arr, 0, middle);
    T[] right = Arrays.copyOfRange(arr, middle, arr.length);

    T[] mergedLeft = MergeSort.mergeSort(left);
    T[] mergedRight = MergeSort.mergeSort(right);

    return MergeSort.merge(mergedLeft, mergedRight);
  }

  /**
   * Merges two sorted arrays into a single sorted array.
   *
   * @param <T>  the type of elements in the arrays, which must be comparable
   * @param arr1 the first sorted array
   * @param arr2 the second sorted array
   * @return a new array containing all elements from arr1 and arr2, sorted
   */
  public static <T extends Comparable<T>> T[] merge(T[] arr1, T[] arr2) {
    if (arr1.length == 0) {
      return arr2;
    }

    if (arr2.length == 0) {
      return arr1;
    }

    int pointer1 = 0, pointer2 = 0, pointer3 = 0;

    @SuppressWarnings("unchecked")
    T[] mergedArr = (T[]) Array.newInstance(arr1.getClass().getComponentType(), arr1.length + arr2.length);

    while (pointer1 < arr1.length && pointer2 < arr2.length) {
      mergedArr[pointer3++] = (arr1[pointer1].compareTo(arr2[pointer2]) <= 0) ? arr1[pointer1++]
          : arr2[pointer2++];
    }

    while (pointer1 < arr1.length) {
      mergedArr[pointer3++] = arr1[pointer1++];
    }

    while (pointer2 < arr2.length) {
      mergedArr[pointer3++] = arr2[pointer2++];
    }

    return mergedArr;
  }
}
