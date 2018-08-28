import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Level1 {

  public static Boolean binarySearch(int[] arr, int n) {
    if (arr.length == 0) {
      return false;
    }
    int lo = 0;
    int hi = arr.length - 1;
    int mid;

    while (lo <= hi) {
      mid = lo + (hi - lo) / 2;

      if (arr[mid] == n) {
        return true;
      } else if (arr[mid] < n) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return false;
  }

}
