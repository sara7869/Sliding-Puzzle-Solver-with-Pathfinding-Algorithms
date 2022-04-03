import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {

        File myObj = new File("../examples/maze30_2.txt");
        List<List<Character>> mapList = new ArrayList<List<Character>>(); // Create a 2D arraylist

        try {
            FileReader fileReader = new FileReader(myObj); // Creation of File Reader object
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                int c = bufferedReader.read();

                // while end of file not reached
                while (c != -1) {
                    List<Character> rowList = new ArrayList<Character>();
                    // while new line is not reached
                    while (c != 10 && c != -1) {
                        char character = (char) c;
                        rowList.add(character); // add character to row
                        c = bufferedReader.read();
                    }

                    // add row to 2D array
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

        for (int rowCount = 0; rowCount < mapList.size(); rowCount++) {
            List<Character> rowList = mapList.get(rowCount);
            for (int columnCount = 0; columnCount < rowList.size(); columnCount++) {
                System.out.print(rowList.get(columnCount));
            }
            System.out.println();
        }

    }
}