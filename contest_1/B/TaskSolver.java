import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskSolver {
  public static void main(String[] args) {
    
    try (Scanner sc = new Scanner(System.in)) {
      
      int points_num = sc.nextInt();

      List<Point> points = readPoints(points_num, sc);

      double area = 0;

      for (int i = 0; i < points_num - 1; ++i) {
        area += calcPolygonArea(points.get(i), points.get(i + i));
      }

      System.out.println(area / 2);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private static double calcPolygonArea(Point a, Point b) {
    return (b.getX() - a.getX()) * (b.getY() + a.getY()) * 0.5;
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
  private final int coord_x;
  private final int coord_y;

  Point(int x, int y) {
    coord_x = x;
    coord_y = y;
  }
  
  int getX() {
    return coord_x;
  }

  int getY() {
    return coord_y;
  }

}
