package uni.project;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSV {
    
    private String fileName;

    CSV(String file) {
        this.fileName = file;
    }

    public void write(String input) {
        try {
            FileWriter myFile = new FileWriter(this.fileName, true);
            
            myFile.append(input);
            myFile.append("\n");

            myFile.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> read() {
        File myFile = new File(this.fileName);

        ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> ();

        try {
            Scanner scanner = new Scanner(myFile);
            
            while(scanner.hasNextLine()) {
                
                ArrayList<String> row = new ArrayList<String> (Arrays.asList(scanner.nextLine().split(",")));
                
                listOfLists.add(row);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file");
            e.printStackTrace();
        }

        return listOfLists;
    }

    public String[][] dataForTable() {
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> (this.read());

        String[][] data = new String[listOfLists.size() - 1][];
        
        for (int i = 1; i < listOfLists.size(); i++) {

            ArrayList<String> row = new ArrayList<String> (listOfLists.get(i));
            
            data[i - 1] = row.toArray(new String[row.size()]);
        }

        return data;
    }

    public String[] headersForTable() {
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> (this.read());
        String[] columns = new String[listOfLists.get(0).size()];
        
        columns = listOfLists.get(0).toArray(new String[columns.length]);

        return columns;
    }

    public void updateCell(int row, int column, String newData) {
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> (this.read());
        
        listOfLists.get(row).set(column, newData);
        
        try {
            FileWriter myFile = new FileWriter(this.fileName);
            
            for (int i = 0; i < listOfLists.size(); i++) {
                myFile.append(String.join(",", listOfLists.get(i)));
                myFile.append("\n");
            }
            myFile.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void updateRow(int row, String newData) {
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> (this.read());
        ArrayList<String> newRow = new ArrayList<String> (Arrays.asList(newData.split(",")));
        
        listOfLists.set(row, newRow);
        
        try {
            FileWriter myFile = new FileWriter(this.fileName);
            
            for (int i = 0; i < listOfLists.size(); i++) {
                myFile.append(String.join(",", listOfLists.get(i)));
                myFile.append("\n");
            }
            myFile.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void deleteRow(int row) {
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> (this.read());
        
        try {
            FileWriter myFile = new FileWriter(this.fileName);
            
            for (int i = 0; i < listOfLists.size(); i++) {
                if (i == row) {
                    continue;
                }
                myFile.append(String.join(",", listOfLists.get(i)));
                myFile.append("\n");
            }
            myFile.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}