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
    private int tilfældigtTal;

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
        //metoden finder random question via tilfældigt tal mellem 1 og 100 
        //afhængig af hvilke arrays der er data i.
        createTilfældigtTal();
        System.out.println("tilfældigt tal er " + tilfældigtTal);

        if (tilfældigtTal > 0 && tilfældigtTal <= 65)
        {
            currentOrdpar = ordparArray.get(random.nextInt(ordparArray.size()));
            return currentOrdpar.getQuestion();
        }
        if (tilfældigtTal > 65 && tilfældigtTal <= 85)
        {
            currentOrdpar = ordparArray2.get(random.nextInt(ordparArray2.size()));
            return currentOrdpar.getQuestion();
        }
        if (tilfældigtTal >= 86)
        {
            currentOrdpar = ordparArray3.get(random.nextInt(ordparArray3.size()));
            return currentOrdpar.getQuestion();
        }
        return "ikke fundet arrayliste";
    }

    @Override
    public boolean checkGuess(String question, String answer)
    {
        //metoden tjekker om det indtastede er lig med det der ligger 
        //i arraylisten - her er de tre arraylister sasmlet til en temporær
        ArrayList<Ordpar> temp = new ArrayList<>(ordparArray);
        temp.addAll(ordparArray2);
        temp.addAll(ordparArray3);

        for (Ordpar op : temp)
        {
            if (op.getQuestion().equalsIgnoreCase(question))
            {
                currentOrdpar = op;
                break;
            }
        }
//        if (answer.equals(("")))  //først if virker ikke p.t. - bliver ikke vist korrekt på guien.
//        {
//            System.out.println("Manglende indtastning af ord før tyk på check");
//            return false;
//        }
        if (answer.equalsIgnoreCase(currentOrdpar.getAnswer()))
        {
            moveWord(currentOrdpar);

            return true;
        }
        if (!answer.equals(("")) && !answer.equalsIgnoreCase(currentOrdpar.getAnswer()))
        {
            System.out.println("Du har svaret forkert - prøv igen");
            return false;
        }
        return false;
    }
    // Metoden danner et tilfældigt tal afhængig af hvilke arraylister der er data i.
    private void createTilfældigtTal()
    {
       
        int a1 = ordparArray.size();
        int a2 = ordparArray2.size();
        int a3 = ordparArray3.size();
        System.out.println("a1= " + a1 + " a2= " + a2 + " a3= " + a3);

        if (a1 > 0 && a2 > 0 && a3 > 0)
        {
            tilfældigtTal = random.nextInt(100);
        }
        if (a1 > 0 && a2 > 0)
        {
            tilfældigtTal = random.nextInt(85);
        }
        if (a1 > 0 && a2 <= 0)
        {
            tilfældigtTal = random.nextInt(65);
        }
        if (a1 <= 0 && a2 > 0)
        {
            tilfældigtTal = random.nextInt(19) + 66;
        }
        if (a1 <= 0 && a2 <= 0)
        {
            tilfældigtTal = random.nextInt(14) + 86;
        }
//        else
//        {
//            tilfældigtTal=random.nextInt(65);
//        }
    }
    //metoden flytter ordpar fra ordparArray til ordparArray2 første gang det gættes.
    //hvis ord gættes der ligger i ordparArray2 flyttes dettte til ordparArray3.

    private void moveWord(Ordpar currentOrdpar)
    {
       
        if (ordparArray.contains(currentOrdpar))
        {
            ordparArray2.add(currentOrdpar);
            ordparArray.remove(currentOrdpar);
        } else if (ordparArray2.contains(currentOrdpar))
        {
            ordparArray3.add(currentOrdpar);
            ordparArray2.remove(currentOrdpar);
        }
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
