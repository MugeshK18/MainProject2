package DemoClassAdv;

class Total_Log                                         //Singleton class
{
	public static Total_Log instance;
	public int totalNoOfAcc=0,totalNoOfEnq=0;
	private Total_Log()
	{	
	}
	public static Total_Log get_instance()
	{
		if(instance==null)
			instance = new Total_Log();
		return instance;
	}	
	public void acc_log()
	{
		++totalNoOfAcc;
	}
	public void enq_log()
	{
		++totalNoOfEnq;
	}
}

interface Int_Withdraw_Details                          //Interface
{	
	public double interest();
	public void withdraw_from_ICCI(int pin,double amount);
	public void withdraw_from_RBI(int pin,double amount);
	
}


abstract class Accounts implements Int_Withdraw_Details                 //Abstract class
{
	protected final static String BANK_NAME="ABC";
	protected final static String IFSC_CODE="SBI0012345";
	protected final static String BRANCH_NAME="Puducherry Branch";
	protected final static String BRANCH_ADDRESS="1,1st Main Road,Puducherry";
	
	private String acc_holder_name;
	private int acc_holder_age;
	private long acc_Number=0;
	private String acc_type;
	private double mainBalance=0.0;
	public double interest;
	
	protected final int pin_num=1234;
	protected final static String manager_Password="Hello@123";
	private static int counter=0;
	
	public abstract void deposit(int pin,double amount);
	public abstract void withdraw(int pin,double amount);
	
	protected void setAccDetails(String password,String accType,String name,int age)
	{
		if(Accounts.manager_Password==password)
		{
			Total_Log.get_instance().acc_log();
			acc_type=accType;
			acc_holder_name= name;
			acc_holder_age=age;
			acc_Number=setAccNum();
		}
		else
		{
			System.out.println("Unauthorised Access!");
		}
	}
	protected void getAccDetails(String password)
	{
		if(Accounts.manager_Password==password)
		{
			System.out.println("\nAccount Type: "+acc_type+"\n"+"Account holder: "+acc_holder_name +"\n"+"Account holder's age: "+acc_holder_age +"\n"+"Account Number: "+acc_Number);
		}
		else
		{
			System.out.println("Unauthorised Access!");
		}
	}
	protected void getAccDetails(int pin)
	{
		if(this.pin_num==pin)
		{
			System.out.println("\nAccount Type: "+acc_type+"\n"+"Account holder: "+acc_holder_name +"\n"+"Account holder's age: "+acc_holder_age +"\n"+"Account Number: "+acc_Number);
		}
		else
		{
			System.out.println("Wrong pin used, Please try again later or visit nearby branch");
		}
	}
	protected final void setBalance(double amount)
	{
			mainBalance +=amount;
	}
	protected final void changeBalance(double amount)
	{
		if(mainBalance>amount)
		{
			mainBalance -=amount;
		}
		else
		{
			System.out.println("Transaction ended! No Suffcient Balance.");
		}
	}
	protected final void getBalance(int pin)
	{
		if(pin_num==pin)
		{
			System.out.println("The Main accont balance is: "+mainBalance);
		}
	}	
	public void getBankDetails()
	{
		System.out.println("Bank Name: "+BANK_NAME+"\nBranch name: "+BRANCH_NAME+"\nIFSC Code: "+IFSC_CODE+"\nBranch Address: "+BRANCH_ADDRESS);
	}
	private static long setAccNum()
	{
		return ++counter;
	}
	protected double getMainBalance()
	{
		return mainBalance;
	}
}

class SavingsAccount extends Accounts                                                //Subclass 1
{
	protected static double rate=6;
	public void deposit(int pin,double amount)
	{
		if(pin_num==pin)
		{
			setBalance(amount);
		}
		else
		{
			System.out.println("Wrong pin used! Please try again or visit nearby branch");
		}
	}
	public void withdraw(int pin,double amount)
	{
		if(pin_num==pin && amount<9999)
		{
			changeBalance(amount);
		}
		else
		{
			System.out.println("Transactithon failed! Please try again or visit nearby branch");
		}
	}
	public void withdraw_from_ICCI(int pin,double amount)
	{
		if(pin_num==pin && amount<9999)
		{
			amount=amount+ ((double)(amount*1*1)/100);
			changeBalance(amount);
		}
		else
		{
			System.out.println("Transactithon failed! Please try again or visit nearby branch");
		}
	}
	public void withdraw_from_RBI(int pin,double amount)
	{

		if(pin_num==pin && amount<9999)
		{
			amount=amount+ ((double)(amount*0.5*1)/100);
			changeBalance(amount);
		}
		else
		{
			System.out.println("Transactithon failed! Please try again or visit nearby branch");
		}
	}
	public double interest()
	{
		return interest = (double)(getMainBalance()*rate*1)/100;
	}
	public void enquiry()
	{
		Total_Log.get_instance().enq_log();
	}
}
class CurrentAccount extends Accounts                                        //Subclass 2
{
	protected static double rate=3;
	public void deposit(int pin,double amount)
	{
		if(pin_num==pin)
		{
			setBalance(amount);
		}
		else
		{
			System.out.println("Wrong pin used! Please try again or visit nearby branch");
		}
	}
	public void withdraw(int pin,double amount)
	{
		if(pin_num==pin)
		{
			changeBalance(amount);
		}
		else
		{
			System.out.println("Wrong pin used! Please try again or visit nearby branch");
		}
	}
	public void withdraw_from_ICCI(int pin,double amount)
	{
		if(pin_num==pin)
		{
			amount=amount+ ((double)(amount*1.5*1)/100);
			changeBalance(amount);
		}
		else
		{
			System.out.println("Wrong pin used! Please try again or visit nearby branch");
		}
	}
	public void withdraw_from_RBI(int pin,double amount)
	{
		if(pin_num==pin)
		{
			amount=amount+ ((double)(amount*1*1)/100);
			changeBalance(amount);
		}
		else
		{
			System.out.println("Wrong pin used! Please try again or visit nearby branch");
		}
	}
	public double interest()
	{
		 return interest = (double)(getMainBalance()*rate*1)/100;
	}
	public void enquiry()
	{
		Total_Log.get_instance().enq_log();
	}
}

class CODAccount extends Accounts                                       //Subclass 3
{
	int count;
	static int rate=10;
	public void deposit(int pin,double amount)
	{
		if(pin_num==pin && count==0)
		{
		setBalance(amount);
		count++;
		}
		else if(pin_num==pin && count>=1)
		{
			System.out.println("Transaction failed! Please visit you nearby branch");
			count++;
		}
		else
		{
			System.out.println("Wrong pin used! Please try again or visit nearby branch");
		}
	}
	public void withdraw(int pin,double amount)
	{
		if(pin_num==pin)
		{
			amount=amount+ ((double)(amount*10*1)/100);     //fine
		    changeBalance(amount);
		}
		else
		{
			System.out.println("Wrong pin used! Please try again or visit nearby branch");
		}
	}
	public void withdraw_from_ICCI(int pin,double amount)
	{
		if(pin_num==pin)
		{
			amount=amount+ ((double)(amount*12*1)/100);
			changeBalance(amount);
		}
		else
		{
			System.out.println("Wrong pin used! Please try again or visit nearby branch");
		}
	}
	public void withdraw_from_RBI(int pin,double amount)
	{
		if(pin_num==pin)
		{
			amount=amount+ ((double)(amount*11.5*1)/100);
			changeBalance(amount);
		}
		else
		{
			System.out.println("Wrong pin used! Please try again or visit nearby branch");
		}
	}
	public double interest()
	{
		 return interest = (double)(getMainBalance()*rate*1)/100;
	}
	public void enquiry()
	{
		Total_Log.get_instance().enq_log();
	}
}

public class MainProject3a                                                       //Driver class
{
	public static void main(String[] args) {               
		
		
		//Bank Branch= Bank.get_instance();
		Accounts user1= new SavingsAccount();
		user1.setAccDetails("Hello@123","Savings","Mugesh",21);
		user1.getAccDetails("Hello@123");
		
		user1.deposit(1234,1000);
		user1.withdraw(1234,500);
		user1.withdraw_from_ICCI(1234, 250);
		user1.deposit(1234,1000);
		user1.getBalance(1234);
		System.out.println("Interest= "+user1.interest());
		
		Accounts user2= new CurrentAccount();
		user2.setAccDetails("Hello@123","Current","Rahul",22);
		user2.getAccDetails("Hello@123");
		
		user2.deposit(1234,10000);
		user2.withdraw(1234,500);
		user2.withdraw_from_ICCI(1234, 250);
		user2.getBalance(1234);
		System.out.println("Interest= "+user2.interest());
		Accounts user3= new CODAccount();
		user3.setAccDetails("Hello@123","Certificate of Deposit","Kumar",23);
		user3.getAccDetails("Hello@123");
		user3.deposit(1234,10000);
		user3.withdraw(1234,2000);
		user3.getBalance(1234);
		System.out.println("Interest= "+user3.interest());
		
		System.out.println("\n"+Total_Log.get_instance().totalNoOfAcc);
		System.out.println(Total_Log.get_instance().totalNoOfEnq);
	}
}
