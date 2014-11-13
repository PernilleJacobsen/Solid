/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solid;

/**
 *
 * @author Pernille
 */
public class Ordpar
{
  String question;
  String answer;
  
  public Ordpar(String question, String answer)
{
    this.question=question;
    this.answer=answer;
}

    @Override
    public String toString()
    {
        return "Ordpar{" + "question=" + question + ", answer=" + answer + '}';
    }
  
}

