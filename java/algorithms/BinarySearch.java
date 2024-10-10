/**
 * A utility class that provides a binary search algorithm implementation.
 */
public class BinarySearch {

  /**
   * Performs a binary search on a sorted array to find the index of the specified
   * element.
   * If the element is found, the index of the first occurrence of the element is
   * returned.
   * If the element is not found, -1 is returned.
   *
   * @param <T>     the type of elements in the array, which must be comparable
   * @param arr     the sorted array to search
   * @param element the element to search for
   * @return the index of the first occurrence of the specified element, or -1 if
   *         the element is not found
   */
  public static <T extends Comparable<T>> int binarySearch(T[] arr, T element) {
    if (arr.length < 1) {
      return -1;
    }

    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
      int middle = left + (right - left) / 2;
      int comparison = arr[middle].compareTo(element);

      if (comparison == 0) {
        // Check for the first occurrence of the element
        if (middle == 0 || arr[middle - 1].compareTo(element) != 0) {
          return middle;
        }
        right = middle - 1;
      } else if (comparison < 0) {
        left = middle + 1;
      } else {
        right = middle - 1;
      }
    }
    return -1;
  }
}
