import java.util.Scanner;

public class TaskSolver {
  public static void main(String[] args) {
  
    try (Scanner sc = new Scanner(System.in)) {
      int lenght = sc.nextInt();

      int[] A = new int[lenght];
      int[] B = new int[lenght];

      readIntArray(A, lenght, sc);
      readIntArray(B, lenght, sc);

      int[] pos_of_max_value = calcMaxValuesForEach(A);

      int i0 = 0;
      int j0 = 0;

      long max_sum = Long.MIN_VALUE;
      long curr_max = Long.MIN_VALUE;

      for (int i = 0; i < B.length; ++i) {
        curr_max = A[pos_of_max_value[i]] + B[i];
        if (curr_max > max_sum) {
          i0 = pos_of_max_value[i];
          j0 = i;
          max_sum = curr_max;
        }
      }

      System.out.printf("%d %d", i0, j0);
    }
  }

  private static void readIntArray(int[] arr, int lenght, Scanner sc) {
    for (int i = 0; i < lenght; ++i) {
      arr[i] = sc.nextInt();
    }
  }

  private static int[] calcMaxValuesForEach(int[] arr) {
    int[] pos_of_max_value = new int[arr.length];
    long max = Long.MIN_VALUE;
    int max_pos = 0;

    for (int i = 0; i < arr.length; ++i) {
      if (arr[i] > max) {
        max = arr[i];
        max_pos = i;
      }
      pos_of_max_value[i] = max_pos; 
    }

    return pos_of_max_value;
  }

}