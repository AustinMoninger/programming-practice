/**
 * Function that takes an array of integers and returns the third
 * largest distinct value. This runs in O(n) time as it is written.
 * It could be written more concisely by first sorting the array,
 * but that would decrease time efficiency of the algorithm.
 *
 * Assume: arr will always have >= 3 distinct elements
 */
static int ThirdLargest(int[] arr) {
  assert arr.length >= 3 : "Invalid input: arr has less than 3 elements";
    int firstLargest = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > firstLargest) {
        firstLargest = arr[i];
      }
    }

    int secondLargest = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > secondLargest && arr[i] < firstLargest) {
          secondLargest = arr[i];
      }
    }

    int thirdLargest = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > thirdLargest && arr[i] < secondLargest) {
        thirdLargest = arr[i];
      }
    }

    return thirdLargest;
}

