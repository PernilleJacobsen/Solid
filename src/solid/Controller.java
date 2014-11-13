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
import java.util.Scanner;



/**
 *
 * @author Pernille
 */
public class Controller implements WordPairControlInterface
{
    ArrayList<Ordpar> ordparArray;
          
    @Override
    public void add(String question, String answer)
    {
        
    }

    @Override
    public int size()
    {
        
    }

    @Override
    public String getRandomQuestion()
    {
        
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
        
    }

    @Override
    public void clear()
    {
       
    }
    //Metode tl at gemme nye ordpar i Arrayliste
    public void createNewWordpair(String question, String answer)
    {
        Ordpar ordpar = new Ordpar(question, answer);
        ordparArray.add(ordpar);
        System.out.println("Just before saving    "+ ordpar.toString() );
        FileHandler.save(ordparArray, "Ordpar.txt");
    }
    
}
