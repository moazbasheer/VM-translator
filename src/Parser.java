import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
	File file;
	Scanner scan; 
	String command;

	public Parser(String filename) throws FileNotFoundException {
		file = new File(filename);
		scan = new Scanner(file);
		command = "";
	}

	public boolean hasMoreCommands() {
		return scan.hasNextLine();
	}

	void setFilename(String filename) throws FileNotFoundException {
		file = new File(filename);
		scan = new Scanner(file);
		command = "";
	}

	public void advance() {
		do {
			command = scan.nextLine().trim();

		} while (command.equals("") || command.substring(0, 2).equals("//"));

		if (command.contains("//")) {
			int x = command.indexOf("//");
			command = command.substring(0, x);
			x = command.indexOf('\t');

			if (x != -1) {
				command = command.substring(0, x);
			}
		}
		System.out.println(command);
	}

	public int commandType() {
		if (command.matches("^(add|sub|neg|eq|gt|lt|and|or|not).*")) {
			return Constants.C_ARITH;
		} else if (command.matches("^push.*")) {
			return Constants.C_PUSH;
		} else if (command.matches("^pop.*")) {
			return Constants.C_POP;
		} else if (command.matches("^label.*")) {
			return Constants.C_LABEL;
		} else if (command.matches("^goto.*")) {
			return Constants.C_GOTO;
		} else if (command.matches("^if-goto.*")) {
			return Constants.C_IF;
		} else if (command.matches("^function.*")) {
			return Constants.C_FUNCTION;
		} else if (command.matches("^call.*")) {
			return Constants.C_CALL;
		} else if (command.matches("^return.*")) {
			return Constants.C_RETURN;
		}
		return -1;
	}

	public String arg1() {
		String result = null;
		int type = commandType();

		if (type == Constants.C_ARITH) {
			result = command;
		} else {
			result = command.split(" ")[1];
		}

		return result;
	}

	public int arg2() {
		int type = commandType();
		String result = null;

		result = command.split(" ")[2];

		if (!isNumeric(result)) {
			return -1;
		}
		int res = Integer.parseInt(result);
		return res;
	}

	private boolean isNumeric(String str) {
		if (str.length() == 0) {
			return false;
		}

		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	void close() {
		scan.close();
	}
}
