import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class OptionMenu {
	Scanner menuInput = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
	HashMap<Integer, Account> data = new HashMap<Integer, Account>();

	public void mainMenu() throws IOException {
		//data.put(952141, new Account(952141, 191904, 1000, 5000));
		//data.put(123, new Account(123, 123, 20000, 50000));
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\n Type 1 - Login");
				System.out.println(" Type 2 - Create Account");
				System.out.println(" Type 3 - Exit");
				System.out.print("\nChoice: ");
				int choice = menuInput.nextInt();
				switch (choice) {
					case 1:
						getLogin(); //TODO - LOGIN MENU
						end = true;
						break;
					case 2:
						createAccount(); //TODO - CREATE ACCOUNT MENU
						end = true;
						break;
					case 3:
						end = true;
						break;
					default:
						System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
		System.out.println("\nThank You for using this ATM.\n");
		menuInput.close();
		System.exit(0);
	}

	public void createAccount() throws IOException {
		int cst_no = 0;
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nEnter your customer number ");
				cst_no = menuInput.nextInt(); 				//TODO - gets and stores input
				Iterator it = data.entrySet().iterator();	//TODO - stores static "database" hashmap into Iterator obj
				while (it.hasNext()) { 						//TODO - while the iterator has a next spot "available"
					Map.Entry pair = (Map.Entry) it.next(); //TODO - cast the iterator obj to a Map and store
					if (!data.containsKey(cst_no)) { 		//TODO - if user input is not in the database...
						end = true;							//TODO - Return true to kick out of the while loop
					}										//TODO - and continue by asking for a PIN next
				}
				if (!end) {
					System.out.println("\nThis customer number is already registered");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
		System.out.println("\nEnter PIN to be registered");
		int pin = menuInput.nextInt();
		data.put(cst_no, new Account(cst_no, pin));			//TODO - create & stores account
		System.out.println("\nYour new account has been successfuly registered!");
		System.out.println("\nRedirecting to login.............");
		getLogin();
	}

	public void getLogin() throws IOException {
		boolean end = false;
		int customerNumber = 0;
		int pinNumber = 0;
		while (!end) {
			try {
				System.out.print("\nEnter your customer number: ");
				customerNumber = menuInput.nextInt();
				System.out.print("\nEnter your PIN number: ");
				pinNumber = menuInput.nextInt();
				Iterator it = data.entrySet().iterator(); 		//TODO - stores hashmap into Iterator Obj
				while (it.hasNext()) { 							//TODO - while the iterator has a next spot "available"
					Map.Entry pair = (Map.Entry) it.next(); 	//TODO - Casts iterator obj as map and stores into map
					Account acc = (Account) pair.getValue(); 	//TODO - Casts map value as an acc and stores into acc
					if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
													//TODO - if database contains accNum AND the pin number matches
						getAccountType(acc); 					//TODO - continue to new method and pass acc over
						end = true; 							//TODO - End loop & break
						break;
					}
				}
				if (!end) {
					System.out.println("\nWrong Customer Number or Pin Number");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Character(s). Only Numbers.");
			}
		}
	}

	public void getAccountType(Account acc) throws IOException {
		//readFromFile(acc);
		//writeToFile(acc);
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSelect the account you want to access: ");
				//System.out.println("Checkings Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
				//System.out.println("Savings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
				System.out.println(" Type 1 - Checkings Account");
				System.out.println(" Type 2 - Savings Account");
				System.out.println(" Type 3 - Log Out & Return");
				System.out.println(" Type 4 - Log Out & Exit");
				System.out.print("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection) {
				case 1:
					getChecking(acc); //TODO - Go to Checking acc
					break;
				case 2:
					getSaving(acc);	//TODO - Go to Savings acc
					break;
				case 3:
					mainMenu();
					break;
				case 4:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void getChecking(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCheckings Account: ");
				System.out.println(" Type 1 - View Balance");
				//System.out.println("\nCheckings Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Return");
				System.out.print("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection) {
				case 1:
					System.out.println("\nCheckings Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
					break;
				case 2:
					acc.getCheckingWithdrawInput();
					break;
				case 3:
					acc.getCheckingDepositInput();
					break;

				case 4:
					acc.getTransferInput("Checkings");
					break;
				case 5:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void getSaving(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSavings Account: ");
				//System.out.println("Savings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Return");
				System.out.print("Choice: ");
				int selection = menuInput.nextInt();
				switch (selection) {
				case 1:
					System.out.println("\nSavings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
					break;
				case 2:
					acc.getsavingWithdrawInput();
					break;
				case 3:
					acc.getSavingDepositInput();
					break;
				case 4:
					acc.getTransferInput("Savings");
					break;
				case 5:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}


	public static void writeToFile(Account acc) throws IOException {
		String num = String.valueOf(acc.getCustomerNumber());
		String num2 = String.valueOf(acc.getPinNumber());
		String num3 = String.valueOf(acc.getCheckingBalance());
		String num4 = String.valueOf(acc.getSavingBalance());

		BufferedWriter writer = new BufferedWriter(new FileWriter("accountLog.txt"));
		BufferedReader reader = new BufferedReader(new FileReader("/Users/calvin/Projects/4th-week/ATM-Machine-Java/accountLog.txt"));
		String line = null;
		int counter = 0;
		while (((line = reader.readLine()) == null) && counter < 9) {
			writer.write(num);
			writer.write("\n"+num2);
			writer.write("\n"+num3);
			writer.write("\n"+num4);
			counter++;
		}
		System.out.println("Saved update to database");
		writer.close();
	}

	public static void readFromFile(Account acc) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/calvin/Projects/4th-week/ATM-Machine-Java/accountLog.txt"));
		ArrayList<String> record = new ArrayList<String>();
		String line;
		while((line = reader.readLine()) != null) {
			//System.out.println(line);
			record.add(line);
		}
		System.out.println("After read from file, store into Arraylist");
		for(String info : record) {
			System.out.println(info);
		}
		//double num = Integer.valueOf(line2);
		//System.out.println(num);
		reader.close();
	}
}
