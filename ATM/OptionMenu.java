import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class OptionMenu {
	Scanner menuInput = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
	HashMap<Integer, Account> data = new HashMap<Integer, Account>();

	public void mainMenu() throws IOException {
		//data.put(123, new Account(123, 123, 0.0, 0.0));
		//data.put(234, new Account(234, 234, 1000.0, 1000.0));

		readFromFile();
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
		writeAccToFile();
		System.out.println("\nThank You for using this ATM.\n");
		menuInput.close();
		System.exit(0);
	}

	public void createAccount() throws IOException {
		int cst_no = 0;
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nEnter your customer number: ");
				cst_no = menuInput.nextInt();
				System.out.println("Entered number is: " +cst_no);//TODO - gets and stores input
				Iterator it = data.entrySet().iterator();	//TODO - stores static "database" hashmap into Iterator obj
				while (it.hasNext()) {//TODO - while the iterator has a next spot "available"
					//System.out.println("In while loop");
					Map.Entry pair = (Map.Entry) it.next(); //TODO - cast the iterator obj to a Map and store
					if (!data.containsKey(cst_no)) {
						//System.out.println("if statement");	//TODO - if user input is not in the database...
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
		Account temp = new Account(cst_no, pin);
		data.put(cst_no, temp);			//TODO - create & stores account
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
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSelect the account you want to access: ");
				System.out.println(" Type 1 - Checkings Account");
				System.out.println(" Type 2 - Savings Account");
				System.out.println(" Type 3 - Log Out & Return to Main Menu");
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

	public void writeAccToFile() throws IOException {
		//System.out.println("Helooooo");
		BufferedWriter writer = new BufferedWriter(new FileWriter("accountLog.txt", false));

		Iterator it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Account acc = (Account) pair.getValue();

			String num1 = String.valueOf(acc.getCustomerNumber());
			String num2 = String.valueOf(acc.getPinNumber());
			String num3 = String.valueOf(acc.getCheckingBalance());
			String num4 = String.valueOf(acc.getSavingBalance());
			System.out.println("Test: "+num1+","+num2+","+num3+","+num4+"\n");
			writer.write(num1+","+num2+","+num3+","+num4+"\n");
		}
		writer.close();

		System.out.println("Account has been written to text file.");
	}

	public static void writeTransaction(Account acc, String transactionType, String accType, double amount, double balance) throws IOException {

		String accID = acc.getCustomerNumber() +".txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(accID, true));

		writer.write(transactionType + ": " + amount);
		writer.write("\n" + accType + " balance : " + balance + "\n\n");

//		BufferedReader reader = new BufferedReader(new FileReader(accID));
//
//		String line;
//		while((line = reader.readLine()) != null) {
//			writer.write(transactionType + ": " + amount);
//			writer.write("\n" + accType + " balance : " + balance + "\n\n");
//		}
		writer.close();
		System.out.println("Transaction recorded to file.");
	}

	public void readFromFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/calvin/Projects/3rd-week/ATM-Machine-Java/accountLog.txt"));
		String line;
		while((line = reader.readLine()) != null) {
			String[] record = line.split(",");
			int customerNum  = Integer.parseInt(record[0]);
			int pinNum = Integer.parseInt(record[1]);
			Double checkBal = Double.parseDouble(record[2]);
			Double savBal = Double.parseDouble(record[3]);
			Account acc = new Account(customerNum, pinNum, checkBal, savBal);
			data.put(customerNum, new Account(customerNum, pinNum, checkBal, savBal));
		}
		reader.close();
		System.out.println("Data as been read and loaded from file");
	}
}
