/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solid;

import Model.Ordpar;
import FileHandler.FileHandler;
import Interfaces.WordPairControlInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



/**
 *
 * @author Pernille
 */
public class Controller implements WordPairControlInterface
{
    private ArrayList<Ordpar> ordparArray = new ArrayList<>();
    private Random random = new Random();
    private Ordpar currentOrdpar;
          
    @Override
    public void add(String question, String answer)
    {
        Ordpar ordpar = new Ordpar(question, answer);   
        ordparArray.add(ordpar);
        System.out.println("Just before saving    "+ ordpar.toString() );
        FileHandler.save(ordparArray, "Ordpar.txt");
    }

    @Override
    public int size()
    {
        return ordparArray.size();
        
    }

    @Override
    public String getRandomQuestion()
    {
        load("Ordpar.txt");
        
        currentOrdpar = ordparArray.get(random.nextInt(ordparArray.size()));
        
        return currentOrdpar.getQuestion();
    }

    @Override
    public boolean checkGuess(String question, String answer)
    {
        if (answer==null)  
        {
            System.out.println("Manglende indtastning af ord før check");
        }
        if(answer.equalsIgnoreCase(currentOrdpar.getAnswer()))
        {
            return true;
        }
        else
        {
            System.out.println("Du har svaret forkert - prøv igen");
            return false;
        }
    }


    @Override
    public String lookup(String question)
    {
        
        
    //if (s.equals(value))
    //{
    //    }
     //   return 
    }
    

    @Override
    public boolean load(String filename)
    {
       ordparArray = FileHandler.load("Ordpar.txt");
       if (ordparArray == null)
       {
           System.out.println("Intet indhold i arraylist");
       return false;
       }else    
       return true;
    }

    @Override
    public boolean save(String filename)
    {   
        
        FileHandler.save(ordparArray, "Ordpar");
        if (ordparArray ==null)
        {
            return false;
        }
        else
        {
            return true;
        }
        }
        
    

    @Override
    public void clear()
    {
       ordparArray.clear();       
    }
    
}
