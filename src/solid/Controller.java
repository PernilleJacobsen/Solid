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
    private String question;
          
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
        if (answer.equals(("")))  
        {
            System.out.println("Manglende indtastning af ord før tyk på check");
            
        }
        if(answer.equalsIgnoreCase(currentOrdpar.getAnswer()))
        {
            return true;
        }
        if(!answer.equals((""))&&!answer.equalsIgnoreCase(currentOrdpar.getAnswer()))
        {
            System.out.println("Du har svaret forkert - prøv igen");
            return false;
        }
        return false;
    }


    @Override
    public String lookup(String question)
    {
        for(Ordpar i :ordparArray)
        {
            if(question.equals(i.getQuestion()))  
            {
                return i.getAnswer();
                
            }else
                if(question.equals(i.getAnswer()))
                        {
                    return i.getQuestion();
            }
        }
        System.out.println("Det indtastede ord er ikke i ordbogen");
        return null;
    
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
