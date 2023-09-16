import java.io.IOException;
import java.io.File;
import java.util.*;
import static java.lang.System.*;

public class WordLadder
   {
      private Queue<Stack<String>> queueOfStacks;
      private Stack<String> completeStack;

     
      public WordLadder()
         {
            queueOfStacks = new LinkedList<>();
            completeStack = new Stack<>();

         }
         
      public void setWords (String a, String b)
         {
            queueOfStacks = new LinkedList<>();
            completeStack = new Stack<>();
            Stack<String> stack = new Stack<>();
            stack.push(a);
            queueOfStacks.offer(stack);
            int stringALength = a.length();
                        
            
         
            
            ArrayList<String> dictionary = new ArrayList<>();
            ArrayList<String> alreadyUsed = new ArrayList<>();
            
            Scanner file = null;
            try {
               file = new Scanner(new File("dictionary.txt"));
            }
            catch (IOException e) {
               System.out.print("Error");
            }
            

            
           while (file.hasNext())
            {     
                  String s = file.next().toLowerCase();
                  if(s.length() == stringALength)
                     dictionary.add(s);
            }
            
            
           while(!queueOfStacks.isEmpty())
           {  
               if(a.equals(b))
                  {
                     completeStack = stack;
                     return;
                  }
               
               for (int i = 0; i < a.length(); i++)
                  {
                     for(char ch = 'a'; ch <= 'z'; ch++)
                        {
                           String temp =  queueOfStacks.peek().peek();
                           
                           String before = temp.substring(0,i);
                           
                           String letter = "" + ch;
                           
                           String after;
                           if (i+1 == temp.length())
                               after = "";
                           else
                              after = temp.substring(i+1,temp.length());
                           
                           String s = before + letter + after;
                           
                           if (!alreadyUsed.contains(s))
                              {
                                 if(s.equals(b))
                                    {
                                       Stack<String> stackCopy = (Stack<String>)queueOfStacks.peek().clone();                             
                                       stackCopy.push(s);
                                       completeStack = (Stack<String>)stackCopy.clone();
                                       return;
                                    }
                                 if (dictionary.contains(s))
                                    {
                                       Stack<String> stackCopy = (Stack<String>)queueOfStacks.peek().clone();                             
                                       stackCopy.push(s);
                                       queueOfStacks.offer(stackCopy);
                                       alreadyUsed.add(s);
                                       
                                    }
                             }
                   
                        }
                                                
                  }
                  
              queueOfStacks.poll();
         }
   
                        
         }
         
         public String toString()
            {
                return completeStack.toString();
            } 
   }