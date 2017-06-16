package com.example.bal_esspasova.compostingapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bal_esspasova on 5/8/2017.
 */

//this class reads the csv file that we pass in
public class CSVReader {
    InputStream inputStream;

    /**
     *constructor
     * @param is an InputStream constructor
     */
    public CSVReader(InputStream is){
        this.inputStream = is;
    }

    /**
     * using csvLine we read the csv files with all our items
     * @return the double array of items in a line
     */
    public List<String[]> read(){
        List<String[]> resultList = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        //adds an item from the csv to the resultList if it is not null
        try{
            String csvLine;
            while((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);

            }
        }

        //exceptions that are foreseen for any csv file
        //just safety precautions
        catch(IOException ex){
            throw new RuntimeException("Error in reading CSV file: " + ex);
        }finally{
            try{
                inputStream.close();
            }catch (IOException e){
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return resultList;
    }
}
