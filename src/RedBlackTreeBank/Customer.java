package RedBlackTreeBank;

/**
 * This class represent a customer of a bank.
 * Every customer has an account.
 * All customers will be stored as a red-black tree
 * with account number as the key.
 *
 * @author Hagay Enoch 
 * @version 8.12.2019
 */
public class Customer
{
    private int color;              // 0 represent black and 1 represent red
    private String name;            // the name of the customer
    private int id;                 // the id number of the customer 
    private int account_id;         // the account number of the customer
    private Customer p;
    private Customer left;
    private Customer right;
    private Account acc;            // this is the account of this customer
        
    /**
     * Default constructor for objects of class customer.
     */
    public Customer()
    {
        color = 0;    // set node to be black
    }
    
    /**
     * Constructor for objects of class customer.
     *@param _name is the name of the customer.
     *@param _acc is the account number of the customer.
     *@param _id is the ID number of the customer.
     */
    public Customer(String _name, int _acc, int _id)
    {
        color = 1;                  // initializing the node to be a red node
        name = _name;
        account_id = _acc;
        id = _id;    
    }
    
    /**
     * Copy constructor for objects of class customer.     
     * @param s is the customer that it's parameters we duplicate    
     *                  to be the parameters of the new customer.
     */
    public Customer(Customer s)
    {
        color = s.color;
        name = s.name;
        account_id = s.account_id;
        id = s.id;   
    }
    
    /**
     * This method copy the parameters of one customer to another customer.
     * @param s is the customer that it's parameters we duplicate to be
     *          the parameters of this customer.
     */
    public void copy(Customer s)
    {
        name = s.name;   
        account_id = s.account_id;
        id = s.id;
        acc = s.acc;
    }
        
    /**
     * This method copy the color of one customer to another customer.
     * @param s is the customer that it's color we duplicate to be the color 
     * this customer.
     */
    public void copyColor(Customer s)
    { color = s.color(); }
    
    /**
     * Return the customer at the left position of this node( customer ).
     * @return the customer at the left position of this node( customer ).
     */
    public Customer left()
    { return left; }
        
    /**
     * Return the customer at the right position of this node( customer ).
     * @return the customer at the right position of this node( customer ).
     */
    public Customer right()
    { return right; }
        
    /**
     * Return the parent node(customer) of this customer at the red-black-tree.
     * @return the parent node(customer) of this customer at the red-black-tree.
     */
    public Customer p()
    { return p; }
        
    /**
     * Return the color of a node( customer ).
     * @return the color of this node( customer ).
     */
    public int color()
    { return color; }
        
    /**
     * Return the id number of a customer.
     * @return the id number of this customer.
     */
    public int id()
    { return id; }
    
    /**
     * Return the account id number of a customer.
     * @return the account id number of this customer.
     */
    public int getAccID()
    { return account_id ; }
        
    /**
     * Return the name of this customer.
     * @return the name of this customer.
     */
    public String getName()
    { return name; }
    
    /**
     * Return the account of this customer.
     * @return the account of this customer.
     */
    public Account getAccount()
    { return acc; }
        
    /**
     * Set the color of this node( customer ) to black.
     */
    public void turnBlack()
    { color = 0; }
        
    /**
     * Set the color of this node( customer ) to red.
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
     * Set the customer at the parent position of this node( customer ).
     * @param _p is customer that will be at parent position of this 
     * node( customer ).
     */
    public void setP(Customer _p)   
    { p = _p; }
          
    /**
     * Set the customer at the left position of this node( customer ).
     * @param _left is customer that will be at left position of this
     *  node( customer ).
     */
    public void setLeft(Customer _left)    
    { left = _left; }
                    
    /**
     * Set the customer at the right position of this node( customer ).
     * @param _right is customer that will be at right position of this 
     * node( customer ).
     */
    public void setRight(Customer _right)    
    { right =_right; }
    
    /**
     * Set the customer account of this customer.
     * @param _acc is account that will belong to this customer.
     */
    public void setAccount(Account _acc)    
    { acc =_acc; }
        
}
