import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class main {
    static int startX = 0;
    static int startY = 0;
    static int endX = 0;
    static int endY = 0;
    static ArrayList<Point> rockList = new ArrayList<Point>();
    // static ArrayList<Point> visitedPointsList = new ArrayList<Point>();

    public static void main(String[] args) {

        ArrayList<ArrayList<Character>> mapList = parseFile(); // Parse text file and return 2D arraylist map

        printMap(mapList); // Print map

        solve(mapList);

    }

    private static ArrayList<ArrayList<Character>> parseFile() {
        File myObj = new File("../examples/maze10_3.txt");
        ArrayList<ArrayList<Character>> mapList = new ArrayList<ArrayList<Character>>(); // Create a 2D arraylist
        try {
            FileReader fileReader = new FileReader(myObj); // Create File Reader object
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                int c = bufferedReader.read();

                // while end of file not reached
                while (c != -1) {
                    ArrayList<Character> rowList = new ArrayList<Character>();
                    // while new line is not reached
                    while (c != 10 && c != -1) {
                        char character = (char) c;
                        int currentXCoordinate = rowList.size();
                        int currentYCoordinate = mapList.size();
                        if (character == 'S') {
                            // startColumn = rowList.size();
                            // row and column are not made yet
                            startX = currentXCoordinate; // 0 onwards
                            startY = currentYCoordinate;
                        } else if (character == 'F') {
                            endX = currentXCoordinate; // rows start from 0
                            endY = currentYCoordinate;
                        } else if (character == '0') {
                            Point currentPoint = new Point(currentXCoordinate, currentYCoordinate);
                            rockList.add(currentPoint);
                        }
                        rowList.add(character); // add character to row
                        c = bufferedReader.read();
                    }

                    // add row to 2D arraylist
                    mapList.add(rowList);
                    c = bufferedReader.read();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("A FileNotFoundException occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An IOException occurred.");
            e.printStackTrace();
        }

        return mapList;
    }

    private static void printMap(ArrayList<ArrayList<Character>> mapList) {
        for (int rowCount = 0; rowCount < mapList.size(); rowCount++) {
            List<Character> rowList = mapList.get(rowCount);
            for (int columnCount = 0; columnCount < rowList.size(); columnCount++) {
                System.out.print(rowList.get(columnCount));
            }
            System.out.println();
        }
    }

    private static int solve(ArrayList<ArrayList<Character>> mapList) {
        Point startPoint = new Point(startX, startY); // Create a Point object for the starting point

        ArrayList<Point> visitedPointsList = new ArrayList<Point>(); // Create an array list called visitedPointsList of
                                                                     // type Point

        visitedPointsList.add(startPoint);

        LinkedList<Point> pointsToVisitQueue = new LinkedList<>(); // Create a Linked list called queue of type Point

        // char [] mapArray = mapList.toArray();

        // mapList.size() = rows of mapList linked list
        // mapList.get(0).size() = columns of mapList linked list
        // Create a 2D array called mapColors of type Point of same size as mapList
        // mapColors - to see which points have been visited already

        // Point[][] mapColors = new Point[mapList.size()][mapList.get(0).size()];

        // pointsToVisitQueue.addLast(new Point(0, 0));
        // mapColors[startY][startX] = startPoint;
        System.out.println("Start at " + startPoint.toString());

        // move to next point on queue and remove it from queue
        // Point currentPosition = pointsToVisitQueue.pollFirst();
        Point currentPosition = startPoint;

        // while the queue is not empty
        while (pointsToVisitQueue.isEmpty() == false || currentPosition == startPoint) {

            // traverse adjacent nodes while sliding on the ice
            // for all 4 directions
            for (Direction direction : Direction.values()) {
                Point nextPoint = getNextPoint(mapList, visitedPointsList, currentPosition, direction);
                // If nextPoint is null, that means we cannot move in that direction

                // Character character = mapList.get(nextPoint.getY()).get(nextPoint.getY());

                if (nextPoint != null) {
                    // move to next point
                    pointsToVisitQueue.addLast(nextPoint);
                    visitedPointsList.add(new Point(currentPosition.getX(), currentPosition.getY())); // add current
                                                                                                      // point to
                                                                                                      // visitedPointsList
                    if (nextPoint.getY() == endY && nextPoint.getX() == endX) {
                        // we found the end point
                        Point tmpPoint = currentPosition; // if we start from nextPosition, we will count one too many
                                                          // edges
                        int count = 0;
                        // while (tmpPoint != startPoint) {
                        count++;
                        // tmpPoint = mapColors[currentPosition.Y, currentPosition.X]
                        // tmpPoint = mapColors[tmpPoint.getY()][tmpPoint.getX()];
                        // count how many points we visited except for start and end
                        // this gives how many turns/edges we made
                        // }
                        return count;
                    }
                    // move to next point on queue and remove it from queue
                    currentPosition = pointsToVisitQueue.pollFirst();
                    System.out.println("Move " + direction + " to " + currentPosition.toString());
                    // break;

                }
            }
            System.out.println();

        }
        // Queue has finished
        return -1;
    }

    public static Point getNextPoint(ArrayList<ArrayList<Character>> mapList, ArrayList<Point> visitedPointsList,
            Point currentPosition,
            Direction direction) {
        int currentXCoordinate = currentPosition.getX();
        int currentYCoordinate = currentPosition.getY();

        // If left direction, diffX = -1,if right, diffX = 1, else diffX = 0
        int diffX = (direction == Direction.left ? -1 : (direction == Direction.right ? 1 : 0));
        // If up direction, diffX = -1,if down, diffX = 1, else diffX = 0
        int diffY = (direction == Direction.up ? -1 : (direction == Direction.down ? 1 : 0));

        int i = 1;

        int nextX = currentXCoordinate + i * diffX;
        int nextY = currentYCoordinate + i * diffY;

        // i - how many coordinates to move in that direction
        // while within the area of the map and met no rock, increase i
        while (nextX >= 0
                && nextX < mapList.get(0).size()
                && nextY >= 0
                && nextY < mapList.size()
                && mapList.get(nextY).get(nextX) != '0'
                && mapList.get(nextY).get(nextX) != '0') {
            i++;
            nextX = currentXCoordinate + i * diffX;
            nextY = currentYCoordinate + i * diffY;
        }

        // when while loop ends, i has exceeded area of map, so reduce 1
        i--; // reverse the last step

        nextX = currentXCoordinate + i * diffX;
        nextY = currentYCoordinate + i * diffY;

        // if cannot move further in that direction
        if (nextX == currentXCoordinate && nextY == currentYCoordinate) {
            return null;
        }

        // if the calculated new point already has been visited, return null
        for (int count = 0; count < visitedPointsList.size(); count++) {
            Point tempPoint = visitedPointsList.get(count);
            if (tempPoint.getX() == nextX && tempPoint.getY() == nextY) {
                // we've already seen this point
                return null;
            }
        }

        // else move there
        return new Point(nextX, nextY);
    }

    public enum Direction {
        left,
        right,
        up,
        down
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
            return "(" + x + ", " + y + ')';
        }

    }

}