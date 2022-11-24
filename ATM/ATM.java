import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ATM {

	public static void main(String[] args) throws IOException {
		OptionMenu optionMenu = new OptionMenu(); //Creates menu obj
		introduction(); //calls intro method to sout
		optionMenu.mainMenu(); //runs mainMenu method from OptionMenu obj
	}

	public static void introduction() {
		System.out.println("Welcome to the ATM Project!");
	}
}

