/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Pernille
 */
public class FileHandler
{
    public static ArrayList<Ordpar> load (String filename)
    {
    Scanner file_scanner = null;
    ArrayList<Ordpar> ordparArray = new ArrayList<Ordpar>();

        try {
            file_scanner = new Scanner(new File(filename));  //Connection to the file using the Scanner object
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find the file to load from! Returning null.");
            ex.printStackTrace();
            return null;  //If something goes wrong the method returns null
        }

        while (file_scanner.hasNextLine()) {  //File found. Reading one line. 
            String question = file_scanner.next();
            String answer = file_scanner.next();
            Ordpar o = new Ordpar(question, answer);
            System.out.println(o);
            ordparArray.add(o);  //Reading in a single line and saving in the ArrayList
        }

        file_scanner.close();  //Closing the file
        return ordparArray;    //returning the arraylist
    } 
    
     public static boolean save(ArrayList<Ordpar> ordparArray, String Ordpar) 
    {
        if (ordparArray == null)
        {
            return false;
        }  //Checking parameter for null.
        FileWriter output;  //Creating reference for filewriter.

        try 
        {
            output = new FileWriter(new File(Ordpar));  //Opening connection to file.
            for (Ordpar ordparline : ordparArray) 
            {   //running through the ArrayList.                    
                output.write(ordparline.toString() + "\n");  //Each String object is written as a line in file.
            }
            output.close();  //Closing the file
        } 
        
        catch (Exception ex) 
        {  //If something goes wrong everything is send to system out.
            System.out.println("Could not save to file!");
            System.out.println(ex.toString());
            ex.printStackTrace();
            return false;  //If something goes wrong false is returned!
        }
        return true;
    }
}
