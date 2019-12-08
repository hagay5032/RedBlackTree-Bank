package RedBlackTreeBank;

import java.io.BufferedReader;
import java.io.FileReader;
 
 /**
 * This class manage the input form a file, It read all lines from the input 
 * and with assumption that the input is legal lines - it execute commands or 
 * queries.
 * 
 * @author Hagay Enoch 
 * @version 8.12.2019
 */
public class Main
{    
    /**
     * This method transfer a string of digit to actual integer value.
     * @return a integer that is the number that was in the input string.
     * @parm s is a string of digits, that we want to transfer to integer.
     */
    public static int transfer_string_to_integer(String s)
    {
        int num = 0;
        int i = 0;
        int minus = 1;
        if(s.charAt(0) == '-')
        {
            minus = -1;
            i++;
        }
        
        while(i<s.length())
        {
            num =num*10+ s.charAt(i)-48;
            i++;
        }
        
        return num*minus;
    }
    
    public static void main(String args[]) throws Exception
    {
        ///////////////////**********************///////////////////
        
        //  There is a need - to change the PATH to the desirable file  //
        
        ///////////////////**********************///////////////////
        
        try
        {
            FileReader file = new FileReader(
            		"C:\\Users\\hagay\\eclipse-workspace\\university2\\"
            		+ "src\\BankRedBlackTrees\\kelet2.txt");
            BufferedReader reader = new BufferedReader(file);
            
            int i;
            RedBlackBank bank = new RedBlackBank();
            Customer anon = bank.getRoot();
            String[] arr = new String[2];
            String[] words = new String[10];
                    
            String line = reader.readLine();
            String theRest = line;
            while(line != null)
            {
                i = 0;
                
                // Extract word from the line and put them in an array. 
                while( theRest != null)
                {
                    arr = line.split(" ", 2);
                    words[i] = arr[0];
                    if(arr.length == 1)
                        break;
                    theRest = arr[1];
                    line = theRest; 
                    i++;
                }
                switch(words[0]) {
	                case "-":    // the command  is a customer leave the bank.
	                {
	                    int acc = transfer_string_to_integer(words[1]);
	                    anon = bank.search(bank.getRoot(), acc); 
	                    if(anon != bank.getNIL())
	                        bank.delete(anon);
	                }
	                break;
	                case "+":	// the command  is a new customer join the bank.
	                {
	                    String first_name = words[1];
	                    String last_name = words[2];
	                    first_name = first_name.concat(" ");
	                    String full_name = first_name.concat(last_name);
	                    int id = transfer_string_to_integer(words[3]);
	                    int acc = transfer_string_to_integer(words[4]);
	                    int money = transfer_string_to_integer(words[5]);
	                    bank.addCustomer(full_name, id, acc, money);
	                }
	                break;
	                case "?": 	// the command is a query
	                {
	                    if(words[1].compareTo("MINUS") == 0)
	                    {
	                        System.out.println("\nAll customers that "
	                        		+ "there balance is negative are:"); 
	                        if( bank.printAllNegativBalance() == false)
	                            System.out.println("There is no "
	                            	+ "customers with negative balance.\n");
	                        else 
	                            System.out.println();
	                    }
	                    else if(words[1].compareTo("MAX") == 0)
	                         bank.printMaxBalance();
	                    else
	                    {
	                    	// it must be a query to return a balance 
	                        int acc = transfer_string_to_integer(words[1]);
	                        anon = bank.search(bank.getRoot(),acc);
	                        if(anon != null)
	                             System.out.println("The balance in account " + 
	                            		 	acc + " is: " + 
	                            		 	anon.getAccount().getBal()+"$ .");
	                    }
	                }
	                break;
	                default: // the command is a deposit or draw 
	                {
	                    String first_name = words[0];
	                    String last_name = words[1];
	                    first_name = first_name.concat(" ");
	                    String full_name = first_name.concat(last_name);
	                    int acc = transfer_string_to_integer(words[2]);
	                    int money = transfer_string_to_integer(words[3]);
	                    anon = bank.search(bank.getRoot(), acc) ;
	                    bank.setCustBalance(anon, money);
	                }  
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch(Exception ex)
        {
          System.out.println(ex.getMessage());
          return;
        }
    }
}


