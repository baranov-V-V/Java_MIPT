import java.util.Scanner;

public class TaskSolver {
  public static void main(String[] args) {
    
    try (Scanner sc = new Scanner(System.in)) {
      
      int n = sc.nextInt();
      int k = sc.nextInt();
      
      System.out.println(findAns(n, k));

    }
  }
  
  private static int findAns(int n, int k) {
    if (n > 1) {
      return (findAns(n - 1, k) + k - 1) % n + 1;
    }
    return 1;
  }
  
}
