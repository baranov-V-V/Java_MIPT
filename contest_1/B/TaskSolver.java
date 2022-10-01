import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskSolver {
  public static void main(String[] args) {
    
    try (Scanner sc = new Scanner(System.in)) {
      
      int points_num = sc.nextInt();

      List<Point> points = readPoints(points_num, sc);

      double res = 0;
	    
      for (int i = 0; i < points.size(); ++i) {
		    Point p1, p2;
        if (i == 0) {
          p1 = points.get(points.size() - 1);
        } else {
          p1 = points.get(i - 1);
        }
        p2 = points.get(i);
		  
        res += (p1.x - p2.x) * (p1.y + p2.y);
	    }

      System.out.println(Math.abs(res) / 2);
    }
  }

  private static List<Point> readPoints(int points_num, Scanner sc) {
    List<Point> points = new ArrayList<>(points_num);

    for (int i = 0; i < points_num; ++i) {
      points.add(i, new Point(sc.nextInt(), sc.nextInt()));
    }

    return points;
  }

}

class Point {
  public final int x;
  public final int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
