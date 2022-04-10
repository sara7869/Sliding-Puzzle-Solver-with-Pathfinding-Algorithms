import java.util.LinkedList;

// public class PokemonIceCave {
// path with the least moves, not distance
public class stack_overflow {

    public static void main(String[] args) {
        int[][] iceCave1 = {
                { 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 1 },
                { 0, 1, 1, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 0, 0, 0, 1, 0 }
        };
        // System.out.println("No of moves: " + solve(iceCave1, 0, 0, 2, 4));
        System.out.println("No of moves: " + solve(iceCave1, 0, 0, 3, 2));
        System.out.println();

        // int[][] iceCave2 = {
        //         { 0, 0, 0, 1, 0 },
        //         { 0, 0, 0, 0, 1 },
        //         { 0, 1, 1, 0, 0 },
        //         { 0, 1, 0, 0, 1 },
        //         { 0, 0, 0, 1, 0 },
        //         { 0, 0, 0, 0, 0 }
        // };
        // System.out.println(solve(iceCave2, 0, 0, 2, 5));
    }

    public static int solve(int[][] iceCave, int startX, int startY, int endX, int endY) {
        Point startPoint = new Point(startX, startY); // Create a Point object for the starting point

        LinkedList<Point> queue = new LinkedList<>(); // Create a Linked list called queue of type Point

        // iceCave.length = rows of iceCave array
        // iceCave[0].length = columns of iceCave array
        // Create a 2D array called iceCaveColors of type Point of same size as iceCave
        // iceCaveColors
        Point[][] iceCaveColors = new Point[iceCave.length][iceCave[0].length];

        queue.addLast(new Point(0, 0));
        iceCaveColors[startY][startX] = startPoint;

        while (queue.size() != 0) {
            Point currentPosition = queue.pollFirst();
            System.out.println(currentPosition);
            // traverse adjacent nodes while sliding on the ice
            for (Direction direction : Direction.values()) {
                Point nextPosition = move(iceCave, iceCaveColors, currentPosition, direction);
                System.out.println("\t" + nextPosition);
                if (nextPosition != null) {
                    // move to that point
                    queue.addLast(nextPosition);
                    iceCaveColors[nextPosition.getY()][nextPosition.getX()] = new Point(currentPosition.getX(),
                            currentPosition.getY());
                    if (nextPosition.getY() == endY && nextPosition.getX() == endX) {
                        // we found the end point
                        Point tmpPoint = currentPosition; // if we start from nextPosition, we will count one too many
                                                          // edges
                        int count = 0;
                        while (tmpPoint != startPoint) {
                            count++;
                            // tmpPoint = iceCaveColors[currentPosition.Y, currentPosition.X]
                            tmpPoint = iceCaveColors[tmpPoint.getY()][tmpPoint.getX()];
                        }
                        return count;
                    }
                }
            }
            System.out.println();
        }
        // Queue has finished
        return -1;
    }

    public static Point move(int[][] iceCave, Point[][] iceCaveColors, Point currentPosition, Direction direction) {
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        // If direction is left, diffX = -1
        // Else if direction is right, diffX = 1
        // Else diffX = 0
        int diffX = (direction == Direction.LEFT ? -1 : (direction == Direction.RIGHT ? 1 : 0));
        int diffY = (direction == Direction.UP ? -1 : (direction == Direction.DOWN ? 1 : 0));

        // diffX is multiplied by 1 to get the polarity
        // If diffX = 1, x + 1
        // If diffX = -1, x - 1
        int i = 1; // i = how many coordinates to move in that direction
        // while within the area of the iceCave, increase i
        while (x + i * diffX >= 0
                && x + i * diffX < iceCave[0].length
                && y + i * diffY >= 0
                && y + i * diffY < iceCave.length
                && iceCave[y + i * diffY][x + i * diffX] != 1) {
            i++;
        }

        // when while loop ends, i has exceeded area of iceCave, so reduce 1
        i--; // reverse the last step

        // if the calculated new point already has a value, return null
        if (iceCaveColors[y + i * diffY][x + i * diffX] != null) {
            // we've already seen this point
            return null;
        }

        // else move there
        return new Point(x + i * diffX, y + i * diffY);
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
}