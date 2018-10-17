package lambdasinaction.chap8;


import java.util.*;

public class Debugging {

  /**
   * limit的作用防止流溢出
   */
  public static void main(String[] args) {
    List<Point> points = Arrays.asList(new Point(12, 2), null);
    points.stream()
        //.limit(points.size())
        .map(p -> p.getX()).forEach(System.out::println);
  }


  private static class Point {

    private int x;
    private int y;

    private Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }
  }
}
