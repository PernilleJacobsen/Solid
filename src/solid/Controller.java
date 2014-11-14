/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solid;

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
    ArrayList<Ordpar> ordparArray = new ArrayList<>();
          
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
        Random random = new Random();
        Ordpar question = ordparArray.get(random.nextInt(ordparArray.size()));
        return question.getQuestion();
        
        
    }

    @Override
    public boolean checkGuess(String question, String quess)
    {
        
    }

    @Override
    public String lookup(String question)
    {
        
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
        //her mangler at kooden bliver korrekt
        FileHandler.save(ordparArray, "Ordpar");
        
    }

    @Override
    public void clear()
    {
       
        //danish.setText("");
        //english.setText("");
        //textArea.setText("");
    }
    
}
