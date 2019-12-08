package RedBlackTreeBank;
    
/**
 * This class represent an account of a customer in a bank.
 * Every account has only one customer that it belong too.
 * All accounts will be stored in a red-black tree with the balance as the key.
 * 
 * @author Hagay Enoch 
 * @version 8.12.2019
 */
public class Account
{
    private int color;      // 0 represent black and 1 represent red. 
    private int balance;	// This value will be the balance in the account.
    private Customer cust;  // The appropriate customer of this account. 
    private Account p;
    private Account left;
    private Account right;
    
    /**
     * Default constructor for objects of class Account.
     */
    public Account()
    {
        color = 0;
    }
    
    /**
     * Constructor for objects of class Account.
     * @param b is the balance of in the Account.
     * @param c is the appropriate customer in the red-black-tree of customers.
     */
    public Account(int b, Customer c)
    {
        color = 1;
        balance = b;
        cust = c;
    }
    
    /**
    * Copy constructor for objects of class Account.
    * @param _account is the Account that it's parameters we duplicate 
    *          to be the parameters of this new Account.
    */
    public Account(Account _account)
    {
        color = _account.color;
        balance = _account.balance;
        cust = _account.cust;
    }
         
    /**
    * This method copy the color of one Account to another Account.
    * @param _account is the Account that it's color we duplicate to be the 
    * color this Account.   
    */
    public void copyColor(Account _account)
    { color = _account.color(); }
       
    /**
     * Return the account at the left position of this node( account ).
     * @return the account at the left position of this node( account ).
     */
    public Account left()
    { return left; }
        
    /**
     * Return the account at the right position of this node( account ).
     * @return the account at the right position of this node( account ).
     */
    public Account right()
    { return right; }
        
    /**
     * Return the account at the parent position of this node( account ).
     * @return the account at the parent position of this node( account ).
     */
    public Account p()
    { return p; }
      
    /**
     * Return the color of this node( account ).
     * @return the color of this node( account ).
     */
    public int color()
    { return color; }    
    
    /**
     * Return the balance in this account.
     * @return the balance in this account.
     */
    public int getBal()
    { return balance; }
    
    /**
     * Return the customer of this account.
     *@return the customer of this account.
     */
    public Customer getCust()
    { return cust; }
    
    /**
     * Set the color of this node( account ) to black.
     */
    public void turnBlack()
    { color = 0; }
        
    /**
     * Set the color of this node( account ) to red.
     */
    public void turnRed()
    { color = 1; }
        
    /**
     * Return true if the color of this node is black, false otherwise.
     * @return true if the color of this node is black, false otherwise.
     */
    public boolean isBlack()
    { return (color == 0); }  
    
    /**
     * Return true if the color of this node is red, false otherwise.
     * @return true if the color of this node is red, false otherwise.
     */
    public boolean isRed()
    { return (color == 1); }  
    
    /**
     * Set the account at the parent position of this node( account ).
     * @param _p is account that will be at parent position of this 
     * node( account ).
     */
    public void setP(Account _p)
    { p = _p; }
        
    /**
     * Set the account at the left position of this node( account ).
     * @param _left is account that will be at left position of this 
     * node( account ).
     */
    public void setLeft(Account _left)
    { left = _left; }
                
    /**
     * Set the account at the right position of this node( account ).
     * @param _right is account that will be at right position of this 
     * node( account ).
     */
    public void setRight(Account _right)
    { right =_right; }    
    
    /**
     * Set the balance of this account.
     * @param sum is the amount of money, that the balance of this account 
     * initialized to be .
     */
    public void setBal(int sum)
    { balance = sum; }
   
    /**
     * Update the balance of this account.
     * @param sum is the amount of money, that the balance of this account 
     * will change in.
     */
    public void updateBal(int sum)
    { balance = balance + sum; }
    
    /**
     * Set the customer of this account.
     * @param c is customer of this account.
     */
    public void setCust(Customer c)
    { cust = c; }
    
}