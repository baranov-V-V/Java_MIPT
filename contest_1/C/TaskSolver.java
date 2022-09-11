import java.util.Scanner;

public class TaskSolver {
  public static void main(String[] args) {
  
    try (Scanner sc = new Scanner(System.in)) {
      
      int lenght_A = sc.nextInt();
      int[] A = new int[lenght_A];
      readIntArray(A, lenght_A, sc);
      
      int lenght_B = sc.nextInt();
      int[] B = new int[lenght_B];
      readIntArray(B, lenght_B, sc);
     
      int target_sum = sc.nextInt();
      int target_count = 0;

      int i = 0;
      int j = lenght_B - 1;

      while (i < lenght_A && j >= 0) {
        if (A[i] + B[j] == target_sum) {
          target_count++;
          if (i < lenght_A - 1) {
            ++i;
            continue;
          }
          if (j > 0) {
            --j;
            continue;
          }
          break;
        } else if (A[i] + B[j] < target_sum) {
          if (i < lenght_A - 1) {
            ++i;
          } else {
            break;
          }
        } else if (A[i] + B[j] > target_sum) {
          if (j > 0) {
            --j;
          } else {
            break;
          }
        }
      }
      
      System.out.println(target_count);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private static void readIntArray(int[] arr, int lenght, Scanner sc) {
    for (int i = 0; i < lenght; ++i) {
      arr[i] = sc.nextInt();
    }
  }

}