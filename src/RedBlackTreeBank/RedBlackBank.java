package RedBlackTreeBank;

/**
 * This class manage a red-black tree as a bank with customers 
 * 
 * @author Hagay Enoch 
 * @version 30.11.2019
 */
public class RedBlackBank
{
    private Customer root;  
    private Customer NIL;
    private int countC;            // represent the current number of customers.
    private RedBlackAcc allAcc;    // represent the accounts of the customers.
       
    static final int MAX_SIZE = 1000;
    
    /**
     * Default constructor for objects of class RedBlackBank.
     */
    public RedBlackBank()
    {
        NIL = new Customer();
        countC = 0 ;
        allAcc = new RedBlackAcc();
    }

    /**
     * Return a customer with specified account number.
     * @param x is the root of a sub tree. That in this sub tree -
     *  will be executed search for a customer with specified account number.
     * @param account_num is the account number of the customer(to search for).
     * @return a customer with specified account number.
     */
    public Customer search(Customer x, int account_num)
    {
        if(root == null)
            return null;
        if(x == NIL || x.getAccID() == account_num)
            return x;
        if(x.getAccID() > account_num)
            return search(x.left(),account_num);
        return search(x.right(), account_num);
    }
    
    /**
     * Adding a new customer to the red-black tree ( bank of customers ).
     * 
     * @param  name is the name of the customer.
     * @param  id_number is the ID number of the customer.
     * @param  account_id is the account number of the customer.
     * @param  monay is the initial amount of the money that will be 
     * in the account of the customer.
     */
    public void addCustomer(String name, int id_number,
    								int account_id, int money)
    {
    	if(MAX_SIZE <= countC)
    	{
    		System.out.println("Error, Maximmum capacity of "
    				+ "customer is reached.");
    		return;
    	}
    		
    	//initial a new customer 
        Customer z = new Customer(name, account_id, id_number);    
        
        //initial a new account for this newly customer
        Account acc = new Account(money, z);  
        
        // add the account to the red-black tree of accounts
        allAcc.addAcc(acc);
        
        // set the account of this customer to be the newly account
        z.setAccount(acc);      
        
        // insert a node(customer) to read black tree(bank) 
        if(root == null)
        {
            root = z;
            z.turnBlack(); // turn the node black
            z.setP(NIL);
            z.setRight(NIL);
            z.setLeft(NIL);
        }
        else
        {
            Customer x = root;
            Customer y = x;
            int flag = 0; /* 0 represent left, 1 represent right. */
            while(x != NIL)
            {
                y = x;
                if(z.getAccID() < x.getAccID())
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
                y.setLeft(z);
            else
                y.setRight(z);
            z.turnRed();    
            insert_fixup(z);
        }
        countC++;
        System.out.println("New customer joined the \"Red-Black-Bank\"."
        		+ " Current number of customer is: " + countC  +".");
    }
    
    /**
     * Fix the red-black tree of customers after insertion.
     * @param z is the new node( customer ) in the red-black tree of customers.
     */
    public void insert_fixup(Customer z)
    {
        while(z.p().isRed())
        {
            if(z.p()  == z.p().p().left())
            {
                Customer y = z.p().p().right();
                if(y.isRed())										// case 1
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
                    z.p().turnBlack();								// case 3
                    z.p().p().turnRed();
                    right_rotate(z.p().p());
                }
            }
            else
            {
                Customer y = z.p().p().left();
                if(y.isRed())										// case 1
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
     * @param x is the node( customer ) that the rotation executed on.
     */
    public void left_rotate(Customer x)
    {
        Customer y = x.right();
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
     * @param x is the node( customer ) that the rotation executed on.
     */
    public void right_rotate(Customer x)
    {
        Customer y = x.left();
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
     * Deletion of a node( customer ) from the bank.
     * @param z is the node( customer ) that needs to delete.
     * @return a node( customer ) that deleted.
     */
    public Customer delete(Customer z)
    {
        allAcc.delete(z.getAccount(), true);
        
        Customer y = new Customer();
        Customer x = new Customer();
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
            z.copy(y);
        if (y.isBlack())
            delete_fixup(x);
        countC--;
        System.out.println("A customer with account number- "+ y.getAccID()
                           + " leave the bank. Current number of customer is: "
                            + countC + ".");
        return y;
    }
    
    /**
     * Fix the red-black tree of customers after deletion.
     * @param z is the node( customer ) that represent a two black node, 
     *          and this method fix it.
     */
    public void delete_fixup(Customer x)
    {
        Customer w = new Customer();
        while(x != root && x.isBlack())
        {
            if(x  == x.p().left())
            {
                w = x.p().right();
                if(w.isRed() )                            
                {
                    w.turnBlack();									// case 1
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
                    if (w.right().isBlack() )                    
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
                        w.right().turnBlack();						// case 3
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
     * Return customer whose account number is the successor of specified 
     * customer.
     * @param x is a specified customer.
     * @return the customer whose account number is the successor of 
     * specified customer.
     */
    public Customer successor(Customer x)
    {
        if( x.right() != NIL)
            return min(x.right());
        Customer y = new Customer();
        y = x.p();
        while(y != NIL && x == y.right())
        {
            x = y;
            y = y.p();
        }
        return y;
    }
    
    /**
     * Return customer whose account number is the predecessor of a 
     * specified customer.
     * @param x is a specified customer.
     * @return the customer whose account number is the predecessor of a 
     * specified customer.
     */
    public Customer predecessor(Customer x)
    {
        if( x.left() != NIL)
            return max(x.right());
        Customer y = new Customer();
        y = x.p();
        while(y != NIL && x == y.left())
        {
            x = y;
            y = y.p();
        }
        return y;
    }
       
    /**
     * Return customer whose account number is the minimum in a specified 
     * sub tree.
     * @param x is the root of the specified sub tree of customers.
     * @return the customer whose account number is the minimum in a specified
     *  sub tree.
     */
    public Customer min(Customer x)
    {
        while(x.left() != NIL)
            x = x.left();
        return x;
    }
    
    /**
     * Return customer whose account number is the maximum in a specified 
     * sub tree.
     * @param x is the root of the specified sub tree of customers.
     * @return the customer whose account number is the maximum in a specified 
     * sub tree.
     */
    public Customer max(Customer x)
    {
        while(x.right() != NIL)
            x = x.right();
        return x;
    }
    
    /**
     * Print the details of the richest customer of the bank
     */
    public void printMaxBalance()
    {
        System.out.println("The most richest customer is: \"" +
                            allAcc.getMaxi().getCust().getName() +
                            "\" ,with account number: " + 
                            allAcc.getMaxi().getCust().getAccID() + 
                            ", And current balance: " +
                            allAcc.getMaxi().getBal()+"$ .");
    }
    
    /**
     * Update a balance of a specified customer in a specified amount of money.
     * @param c is a specified customer.
     * @param sum is an amount of money.
     */
    public void setCustBalance(Customer c, int sum)
    {
        Customer z = search(root, c.getAccID());
        if (z != null)
        {    
            allAcc.setBalance(z.getAccount() , sum);
            System.out.println("Succesfuly update balance in account number " +
            					z.getAccID()+" with: "+ sum + 
            					"$ . Current balance is: " + 
            					z.getAccount().getBal()+"$ ." );
        }
    }
    
    /**
     * Print the details of all customer of the bank whose balance is minus.
     * @return true if there was at least one customer with negative balance.
     */
    public boolean printAllNegativBalance()
    {  return allAcc.printNeg(allAcc.getRoot()); }
    
    /**
     * Return the root of the red black tree of customers.
     * @return the root of the red black tree of customers.
     */
    public Customer getRoot()
    { return root;}
    
    /**
     * Return the NIL node of the red black tree of customers.
     * @return the NIL node of the red black tree of customers.
     */
    public Customer getNIL()
    { return NIL;}
    
}
