/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solid;

import Model.Ordpar;
import FileHandler.FileHandler;
import Interface.WordPairControlInterface;
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
    private ArrayList<Ordpar> ordparArray2 = new ArrayList<>();
    private ArrayList<Ordpar> ordparArray3 = new ArrayList<>();
    private Random random = new Random();
    private Ordpar currentOrdpar;
    private String question;
    private String filename;

    @Override
    public void add(String question, String answer)
    {
        Ordpar ordpar = new Ordpar(question, answer);
        ordparArray.add(ordpar);
        System.out.println("Just before saving    " + ordpar.toString());
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
        //currentOrdpar = ordparArray.get(random.nextInt(ordparArray.size()));
        //return currentOrdpar.getQuestion();
       int tilfældigtTal =random.nextInt(100);
        System.out.println(tilfældigtTal);
       
        if (tilfældigtTal>0&&tilfældigtTal<=65)
        {
           currentOrdpar = ordparArray.get(random.nextInt(ordparArray.size()));
           return currentOrdpar.getQuestion();
        }
        if (tilfældigtTal>65 && tilfældigtTal<=85)
        {
            currentOrdpar = ordparArray2.get(random.nextInt(ordparArray2.size()));
            return currentOrdpar.getQuestion();
        }
        if(tilfældigtTal>=86)
        {
           currentOrdpar = ordparArray3.get(random.nextInt(ordparArray3.size()));
           return currentOrdpar.getQuestion();   
        }
        return "ikke fundet arrayliste";
        
    }

    @Override
    public boolean checkGuess(String question, String answer)
    {
        for (Ordpar op: ordparArray) {
            if (op.getQuestion().equalsIgnoreCase(question)) {
                currentOrdpar = op;
                break;
            }
        }
        if (answer.equals(("")))  //først if virker ikke p.t. - bliver ikke vist korrekt på guien.
        {
            System.out.println("Manglende indtastning af ord før tyk på check");
            return false;
        }
        if (answer.equalsIgnoreCase(currentOrdpar.getAnswer()))
        {
            return true;
        }
        if (!answer.equals(("")) && !answer.equalsIgnoreCase(currentOrdpar.getAnswer()))
        {
            System.out.println("Du har svaret forkert - prøv igen");
            return false;
        }
        return false;
    }

    @Override
    public String lookup(String question)
    {
        for (Ordpar i : ordparArray)
        {
            if (question.equals(i.getQuestion()))
            {
                return i.getAnswer();

            } else if (question.equals(i.getAnswer()))
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
        this.filename = filename;
        ordparArray = FileHandler.load(this.filename);
        if (ordparArray == null)
        {
            System.out.println("Intet indhold i arraylist");
            return false;
        } else
        {
            return true;
        }
    }

    @Override
    public boolean save(String filename)
    {

        FileHandler.save(ordparArray, filename);
        if (ordparArray == null)
        {
            return false;
        } else
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
