import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

class BankProgram
{
    private HashMap<Integer, Integer> accounts = new HashMap<>();
    private double rate = 0.01;
    private int nextacct = 0;
    private int current = -1;
    private Scanner scanner;
    private Boolean done = false;

    public void run()
    {
        scanner = new Scanner(System.in);
        while(!done)
        {
            System.out.print("Enter Command (0=quit, 1=new, 2=select, 3=deposite, 4=loan, 5=show, 6=interest): ");
            int cmd = scanner.nextInt();
            processCommand(cmd);
        }
        scanner.close();
    }

    private void processCommand(int cmd)
    {
        if (cmd == 0)
        {
            quit();
        }

        else if(cmd == 1)
        {
            newAccount();
        }

        else if(cmd == 2)
        {
            select();
        }
        
        else if(cmd == 3)
        {
            deposite();
        }
        
        else if(cmd == 4)
        {
            authorizeLoan();
        }
        
        else if(cmd == 5)
        {
            showAll();
        }
        
        else if(cmd == 6)
        {
            addInterest();
        }
        
        else
        {
            System.out.println("Illegal Command");
        }
    }

    /*
        The program’s run method performs a loop that repeatedly reads commands from the console and executes them. 
        There are seven commands, each of which has a corresponding method.
        The quit method sets the global variable done to true, which causes the loop to terminate
    */
    public void quit()
    {
        done = true;
        System.out.println("Goodbye!");
    }

    /*
    The global variable current keeps track of the current account. 
    The newAccount method allocates a new account number, makes it current, and assigns it to the map with an initial balance of 0.
    */
    public void newAccount()
    {
        current = nextacct++;
        accounts.put(current, 0);
        System.out.println("Your new account number is " + current);
    }

    /*
    The select method makes an existing account current. 
    It also prints the account balance.
    */
    private void select()
    {
        System.out.print("Enter acccunt#: ");
        current = scanner.nextInt();
        int balance = accounts.get(current);
        System.out.println("The balane of account " + current + " is " + balance);
        
    }

    /*
    The deposit method increases the balance of the current account by a
    specified number of cents.
    */
    private void deposite()
    {
        System.out.print("Enter deposit amount : ");
        int amt = scanner.nextInt();
        int balance = accounts.get(current);
        accounts.put(current, balance+amt);
    }

    /*
    The method authorizeLoan determines whether the current account has enough money to be used as collateral for a loan. 
    The criterion is that the account must contain at least half of the loan amount.
    */    
    private void authorizeLoan()
    {
        System.out.print("Enter loan amount");
        int loanamt = scanner.nextInt();
        int balance = accounts.get(current);
        
        if(balance >= loanamt / 2)
        {      
            System.out.println("Your loan is approved");
        }
        
        else
        {
            System.out.println("Your loan is denied");
        }
    }

    /*
    The showAll method prints the balance of every account.
    */
    private void showAll()
    {
        Set<Integer> accts = accounts.keySet();
        System.out.println("The bank has " + accts.size() + " accounts");
        for(int i : accts)
        {
            System.out.println("\tBank account " + i + " : balance= " + accounts.get(i));
        }
    }

    /*
    Finally, the addInterest method increases the balance of each
    account by a fixed interest rate.
    */
    private void addInterest()
    {
        Set<Integer> accts = accounts.keySet();
        for (int i : accts)
        {
            int balance = accounts.get(i);
            int newBalance = (int)(balance * (1 + rate));
            accounts.put(i, newBalance);
        }
    }
    
    public static void main(String[] args)
    {
        BankProgram program = new BankProgram();
        program.run();
    }
}