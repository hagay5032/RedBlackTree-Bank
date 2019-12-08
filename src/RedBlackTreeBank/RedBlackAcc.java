package RedBlackTreeBank;

/**
 * This class manage a red-black tree of customers accounts.
 * Every node in this tree as a customer in the bank.
 * 
 * @author Hagay Enoch 
 * @version 30.11.2019
 */
public class RedBlackAcc
{
    private Account root;  
    private Account NIL;
    private int countA; //Represent the current number of customers in the bank.
    private Account maxi;
    
    /**
     * Constructor for objects of class RedBlackBank
     */
    public RedBlackAcc()
    {
        NIL = new Account();
        countA = 0;
    }
    
    /**
     * Adding an account to the red-black tree of accounts. 
     * @param z is an account to add.
     */
    public void addAcc(Account z)
    {
    	if(RedBlackBank.MAX_SIZE <= countA)
    	{
    		System.out.println("Error, Maximmum capacity of customer "
    				+ "is reached.");
    		return;
    	}
    	
        if(root == null)
        {
            root = z;
            z.turnBlack();
            z.setP(NIL);
            z.setRight(NIL);
            z.setLeft(NIL);
            maxi = z;
        }
        else
        {
            if( z.getBal() > maxi.getBal())
                maxi = z;
        
            Account x = root;
            Account y = x;
            int flag = 0; /* 0 represent left, 1 represent right*/
            while(x != NIL)
            {
                y = x;
                if(z.getBal() < x.getBal())
                {
                    x = x.left();
                    flag = 0;
                }
                else 
                {
                    x = x.right();    
                    flag = 1;
                }
            }
            z.setP(y);
            z.setRight(NIL);
            z.setLeft(NIL);
            if(flag == 0)
                y.setLeft(z) ;
            else
                y.setRight(z) ;
            z.turnRed();     
            insert_fixup(z);
        }
        countA++; 
    }
    
    /**
     * Fix the red-black tree of accounts after insertion.
     * @param z is the new node( account ) in the red-black tree of accounts.
     */
    public void insert_fixup(Account z)
    {
        while(z.p().isRed())
        {
            if(z.p()  == z.p().p().left())
            {
                Account y = z.p().p().right();
                if(y.isRed()) 										// case 1
                {
                    z.p().turnBlack();
                    y.turnBlack();
                    z.p().p().turnRed();
                    z = z.p().p();
                }
                else
                {
                    if (z == z.p().right())							// case 2
                    {
                        z = z.p();
                        left_rotate(z);
                    }
                    z.p().turnBlack(); 								// case 3
                    z.p().p().turnRed() ; 
                    right_rotate(z.p().p());
                }
            }
            else
            {
                Account y = z.p().p().left();
                if(y.isRed() )										// case 1
                {
                    z.p().turnBlack();
                    y.turnBlack();
                    z.p().p().turnRed();
                    z = z.p().p();
                }
                else 
                {
                    if (z == z.p().left())							// case 2
                    {
                        z = z.p();
                        right_rotate(z);
                    }
                    z.p().turnBlack();								// case 3
                    z.p().p().turnRed();
                    left_rotate(z.p().p());
                }
            }
        }
        root.turnBlack();
    }
    
    /**
     * Execute rotation to the left in a red black tree.
     * @param x is the node( account ) that the rotation executed on.
     */
    public void left_rotate(Account x)
    {
        Account y = x.right();
        x.setRight(y.left());
        if(y.left() != NIL)
            y.left().setP(x);
        y.setP(x.p());
        if(x.p() == NIL)
            root = y;
        else if ( x == x.p().left())
            x.p().setLeft(y);
        else     
            x.p().setRight(y);
        y.setLeft(x);
        x.setP(y);
    }
    
    /**
     * Execute rotation to the right in a red black tree.
     * @param x is the node( account ) that the rotation executed on.
     */
    public void right_rotate(Account x)
    {
        Account y = x.left();
        x.setLeft(y.right());
        if(y.right() != NIL)
            y.right().setP(x);
        y.setP(x.p());
        if( x.p() == NIL)
            root = y;
        else if ( x == x.p().right())
            x.p().setRight(y);
        else     
            x.p().setLeft(y);
        y.setRight(x);
        x.setP(y);       
    }
    
    /**
     * Deletion of a node( account ) from the bank.
     * @param z is the node( account ) that needs to delete.
     * @param is_leaving is a boolean that represent if 
     *          the customer whose belong this account is leaving the bank.
     * @return a node( account ) that deleted.
     */
    public Account delete(Account z,  boolean is_leaving)
    {
    	//update the maximum account if necessary
        if (z == maxi)       
            maxi = predccessor(z);
        
        if(is_leaving)      //checking if the deletion is because a customer 
        {                   // leave the bank and set his balance to zero. 
            int sum = z.getBal();
            if(sum != 0)
                z.updateBal((-1)*sum);
        }
        
        Account y = new Account();
        Account x = new Account();
        if(z.left() == NIL || z.right() == NIL)
            y = z;
        else
            y = successor(z);
        if ( y.left() != NIL)
            x = y.left();
            else
            x = y.right();
        x.setP(y.p());
        if(y.p() == NIL)
            root = x;
        else if (y == y.p().left())
            y.p().setLeft(x);
        else
            y.p().setRight(x);
        
        if( y != z)
            exchange(z,y);
        if (y.isBlack())
            delete_fixup(x);
        countA--;
        return y;
    }
    
    /**
     * Fix the red-black tree of accounts after deletion.
     * @param x is the node( account ) that represent a two black node, 
     *          and this method fix it.
     */
    public void delete_fixup(Account x)
    {
        Account w = new Account();
        while(x != root && x.isBlack())
        {
            if(x  == x.p().left())
            {
                w = x.p().right();
                if(w.isRed() )                            
                {
                    w.turnBlack();   					            // case 1
                    x.p().turnRed();
                    left_rotate(x.p());
                    w = x.p().right();
                }
                if (w.left().isBlack() && w.right().isBlack())     
                {
                    w.turnRed();									// case 2
                    x = x.p();
                }
                else
                {
                    if (w.right().isBlack())                    
                    {
                        w.left().turnBlack();						// case 3
                        w.turnRed();
                        right_rotate(w);
                        w = w.p().right();
                    }
                    w.copyColor(x.p()) ;        
                    x.p().turnBlack();
                    w.right().turnBlack();
                    left_rotate(x.p());
                    x = root;
                }
            }
            else
            {
                w = x.p().left();
                if(w.isRed() )                            
                {
                    w.turnBlack();									// case 1
                    x.p().turnRed(); 
                    right_rotate(x.p());
                    w = x.p().left();
                }
                if (w.right().isBlack() && w.left().isBlack())     
                {
                    w.turnRed();									// case 2
                    x = x.p();
                }
                else
                {
                    if (w.left().isBlack() )                    
                    {
                        w.right().turnBlack(); 					   // case 3
                        w.turnRed(); 
                        left_rotate(w);
                        w = w.p().left();
                    }
                    w.copyColor(x.p());        
                    x.p().turnBlack(); 
                    w.left().turnRed();
                    right_rotate(x.p());
                    x = root;
                }
            }
        }
        x.turnBlack();
    }
    
    /**
     * Return account whose balance is the successor of a specified account.
     * @param x is a specified account.
     * @return the account whose balance is the successor of a specified
     *  account.
     */
    public Account successor(Account x)
    {
        if( x.right() != NIL)
            return min(x.right());
        Account y = new Account();
        y = x.p();
        while(y != NIL && x == y.right())
        {
            x = y;
            y = y.p();
        }
        return y;
    }
    
    /**
     * Return account whose balance is the predecessor of a specified account.
     * @param x is a specified account.
     * @return the account whose balance is the predecessor of a specified 
     * account.
     */
    public Account predccessor(Account x)
    {
        if( x.left() != NIL)
            return max(x.right());
        Account y = new Account();
        y = x.p();
        while(y != NIL && x == y.left())
        {
            x = y;
            y = y.p();
        }
        return y;
    }
    
    /**
     * Return account whose balance is the minimum in a specified sub tree.
     * @param x is the root of the specified sub tree of accounts.
     * @return the account whose balance is the minimum in a specified sub tree.
     */
    public Account min(Account x)
    {
        while(x.left() != NIL)
            x = x.left();
        return x;
    }
    
    /**
     * Return account whose balance is the maximum in a specified sub tree.
     * @param x is the root of the specified sub tree of accounts.
     * @return the account whose balance is the maximum in a specified sub tree.
     */
    public Account max(Account x)
    {
        while(x.right() != NIL)
            x = x.right();
        return x;
    }
    
    /**
     * Return the root of the red black tree of accounts.
     * @return the root of the red black tree of accounts.
     */
    public Account getRoot()
    { return root;}
    
    /**
     * Update the balance in a specified account.
     * @param z is the specified account.
     * @param sum is the amount of money to be update in the account.
     */
    public void setBalance(Account z, int sum)
    {
        if(z == maxi)
            maxi = predccessor(z);
        Account x = delete(z,false);
        x.updateBal(sum);
        addAcc(x);
    }
    
    /**
     * Print the details of all customer in a specified sub tree whose balance
     * is negative.
     * @param x is the root of the specified sub tree.
     * @return true if there was at least one customer with negative balance.
     */
    public boolean printNeg(Account x)
    {
        if ( x == NIL)
            return false;
        if( x.getBal() < 0)
        {   
            System.out.println("Customer name is: "+x.getCust().getName() +
                                ", And his account number is: " + 
                                	x.getCust().getAccID() + "." +
                                 " Current balance is: "+x.getBal()+"$ .");
            printNeg(x.left());
            printNeg(x.right());
            return true;
        }
        else 
        {
        	/* if the balance is bigger then zero: there is no need to search 
        	   in the right sub tree. */
            return printNeg(x.left());
        }
    }
    
    /**
    * Return the richest customer of the bank.
    * @return the richest customer of the bank. 
    */
    public Account getMaxi()
    { return maxi; }
    
    /**
    * Exchange the content of two Accounts.
    * @param s is the first account
    * @param c is the second account
    */
    public void exchange(Account s, Account c)
    {   
        int temp = s.getBal();
        Customer t = s.getCust();
        
        s.setBal(c.getBal());
        s.setCust(c.getCust());
        
        c.setBal(temp);
        c.setCust(t);
        
        s.getCust().setAccount(s);
        c.getCust().setAccount(c);
    }
}
