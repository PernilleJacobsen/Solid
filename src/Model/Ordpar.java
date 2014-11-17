/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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

    public String getQuestion()
    {
        return question;
    }

    public String getAnswer()
    {
        return answer;
    }

    @Override
    public String toString()
    {
        return ""+question+","+answer;
        
    }
  
}

