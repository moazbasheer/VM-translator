import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CodeWriter {
	File file;
	FileWriter output;
	static int counteq = 0, countgt = 0, countlt = 0, countstatic = 0;
	public HashMap<String, String> map;
	static int cntret = 1;
	private String functionName = "";
	private String fileName = "";
	public CodeWriter(String filename) {
		try {
			file = new File(filename);
			output = new FileWriter(file);
			map = new HashMap<>();
			map.put("local", "LCL");
			map.put("argument", "ARG");
			map.put("this", "THIS");
			map.put("that", "THAT");
			map.put("temp", "5");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void setFileName(String filename) throws IOException {
		fileName = filename.split("[.]")[0];
		fileName = fileName.split("/")[1];
		if(filename.contains("Sys.vm")){
			writeInit();
		}
	}
	void writeArithmetic(String command) throws IOException {
		if (command.matches("^add.*")) {
			writeDoubleCommand();
			writeAddCommand();
		} else if (command.matches("^sub.*")) {
			writeDoubleCommand();
			writeSubCommand();
		} else if (command.matches("^neg.*")) {
			writeNegCommand();
		} else if (command.matches("^eq.*")) {
			writeDoubleCommand();
			writeEqCommand();
		} else if (command.matches("^gt.*")) {
			writeDoubleCommand();
			writeGtCommand();
		} else if (command.matches("^lt.*")) {
			writeDoubleCommand();
			writeLtCommand();
		} else if (command.matches("^and.*")) {
			writeDoubleCommand();
			writeAndCommand();
		} else if (command.matches("^or.*")) {
			writeDoubleCommand();
			writeOrCommand();
		} else if (command.matches("^not.*")) {
			writeNotCommand();
		} else {
			return;
		}

	}

	void writeAddCommand() throws IOException {
		output.write("M=M+D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
	}

	void writeSubCommand() throws IOException {
		output.write("M=M-D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
	}

	void writeAndCommand() throws IOException {
		output.write("M=M&D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
	}

	void writeOrCommand() throws IOException {
		output.write("M=M|D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
	}

	void writeNotCommand() throws IOException {
		output.write("@SP\n");
		output.write("M=M-1\n");
		output.write("A=M\n");
		output.write("M=!M\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
	}

	void writeNegCommand() throws IOException {
		output.write("@SP\n");
		output.write("M=M-1\n");
		output.write("A=M\n");
		output.write("M=-M\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
	}

	void writeDoubleCommand() throws IOException {
		output.write("@SP\n");
		output.write("M=M-1\n");
		output.write("A=M\n");
		output.write("D=M\n");
		output.write("@SP\n");
		output.write("M=M-1\n");
		output.write("A=M\n");
	}

	void writeEqCommand() throws IOException {

		// if true goto true
		output.write("D=M-D\n");
		output.write("@EQ" + (counteq) + "\n");
		output.write("D;JEQ\n");
		// in case false
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=0\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		output.write("@ENDEQ" + (counteq) + "\n");
		output.write("0;JMP\n");
		// in case true
		output.write("(EQ" + (counteq) + ")\n");
		// pointer three lines
		output.write("@SP" + "\n");
		output.write("A=M\n");
		output.write("M=-1\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		output.write("(ENDEQ" + (counteq) + ")\n");
		counteq++;
	}

	void writeGtCommand() throws IOException {
		// if true goto true
		output.write("D=M-D\n");
		output.write("@GT" + (countgt) + "\n");
		output.write("D;JGT\n");
		// in case false
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=0\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		output.write("@ENDGT" + (countgt) + "\n");
		output.write("0;JMP\n");
		// in case true
		output.write("(GT" + (countgt) + ")\n");
		// pointer three lines
		output.write("@SP" + "\n");
		output.write("A=M\n");
		output.write("M=-1\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		output.write("(ENDGT" + (countgt) + ")\n");
		countgt++;
	}

	void writeLtCommand() throws IOException {
		// if true goto true
		output.write("D=M-D\n");
		output.write("@LT" + (countlt) + "\n");
		output.write("D;JLT\n");
		// in case false
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=0\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		output.write("@ENDLT" + (countlt) + "\n");
		output.write("0;JMP\n");
		// in case true
		output.write("(LT" + (countlt) + ")\n");
		// pointer three lines
		output.write("@SP" + "\n");
		output.write("A=M\n");
		output.write("M=-1\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		output.write("(ENDLT" + (countlt) + ")\n");
		countlt++;
	}

	void writePushPop(int commandType, String command, int index)
			throws IOException {
		if (commandType == Constants.C_PUSH) {
			if (command.equals("local") || command.equals("this")
					|| command.equals("argument") || command.equals("that")) {
				writePush1(map.get(command), index);
			} else if (command.equals("temp")) {
				writePush2("5", index);
			} else if (command.equals("static")) {
				writePushStatic(command, index);
			} else if (command.equals("pointer")) {
				if (index == 0) {
					writePush3("THIS");
				} else {
					writePush3("THAT");
				}
			} else if (command.equals("constant")) {
				writeConstPush(index);
			}
		} else if (commandType == Constants.C_POP) {
			if (command.equals("local") || command.equals("this")
					|| command.equals("argument") || command.equals("that")) {
				writePop1(map.get(command), index);
			} else if (command.equals("temp")) {
				writePop2("5", index);
			} else if (command.equals("static")) {
				writePopStatic(command, index);
			} else if (command.equals("constant")) {
				writeConstPop(index);
			} else if (command.equals("pointer")) {
				if (index == 0) {
					writePop3("THIS");
				} else {
					writePop3("THAT");
				}
			}
		}
	}
	void writePushStatic(String command,int index) throws IOException{
		command = fileName + "." + index;
		output.write("@" + command + "\n");
		output.write("D=M\n");
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
	}
	
	void writePopStatic(String command,int index) throws IOException{
		command = fileName + "." + index;
		output.write("@SP\n");
		output.write("M=M-1\n");
		output.write("A=M\n");
		output.write("D=M\n");
		output.write("@" + command + "\n");
		output.write("M=D\n");
	}
	
	void writeConstPush(int index) throws IOException {

		output.write("@" + index + "\n");
		output.write("D=A\n");
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
	}

	void writeConstPop(int index) throws IOException {
		output.write("@SP\n");
		output.write("M=M-1\n");
		output.write("A=M\n");
		output.write("D=M\n");

		output.write("@" + index + "\n");
		output.write("M=D\n");
	}

	void writePush1(String command, int index) throws IOException {

		output.write("@" + command + "\n");
		output.write("D=M" + "\n");
		output.write("@" + index + "\n");

		output.write("D=A+D\n");
		output.write("A=D" + "\n");
		output.write("D=M" + "\n");
		output.write("@SP" + "\n");
		output.write("A=M" + "\n");
		output.write("M=D" + "\n");
		output.write("@SP" + "\n");
		output.write("M=M+1" + "\n");
	}

	void writePush2(String command, int index) throws IOException {

		output.write("@" + command + "\n");
		output.write("D=A" + "\n");
		output.write("@" + index + "\n");

		output.write("D=A+D\n");
		output.write("A=D" + "\n");
		output.write("D=M" + "\n");
		output.write("@SP" + "\n");
		output.write("A=M" + "\n");
		output.write("M=D" + "\n");
		output.write("@SP" + "\n");
		output.write("M=M+1" + "\n");
	}

	void writePush3(String command) throws IOException {
		output.write("@" + command + "\n");
		output.write("D=A" + "\n");
		output.write("A=D" + "\n");
		output.write("D=M" + "\n");
		output.write("@SP" + "\n");
		output.write("A=M" + "\n");
		output.write("M=D" + "\n");
		output.write("@SP" + "\n");
		output.write("M=M+1" + "\n");
	}

	void writePop1(String command, int index) throws IOException {

		output.write("@" + command + "\n");
		output.write("D=M" + "\n");
		output.write("@" + index + "\n");
		output.write("D=A+D\n");

		output.write("@R13\n");
		output.write("M=D\n");

		output.write("@SP" + "\n");
		output.write("M=M-1" + "\n");
		output.write("@SP" + "\n");
		output.write("A=M" + "\n");
		output.write("D=M" + "\n");
		output.write("@R13\n");
		output.write("A=M\n");
		output.write("M=D\n");
	}

	void writePop2(String command, int index) throws IOException {
		output.write("@" + command + "\n");
		output.write("D=A" + "\n");
		output.write("@" + index + "\n");
		output.write("D=A+D\n");

		output.write("@R13\n");
		output.write("M=D\n");

		output.write("@SP" + "\n");
		output.write("M=M-1" + "\n");
		output.write("@SP" + "\n");
		output.write("A=M" + "\n");
		output.write("D=M" + "\n");
		output.write("@R13\n");
		output.write("A=M\n");
		output.write("M=D\n");
	}

	void writePop3(String command) throws IOException {
		output.write("@" + command + "\n");
		output.write("D=A" + "\n");

		output.write("@R13\n");
		output.write("M=D\n");

		output.write("@SP" + "\n");
		output.write("M=M-1" + "\n");
		output.write("@SP" + "\n");
		output.write("A=M" + "\n");
		output.write("D=M" + "\n");
		output.write("@R13\n");
		output.write("A=M\n");
		output.write("M=D\n");
	}

	void writeLabel(String labelname) throws IOException {
		output.write("(" + functionName + "$" + labelname + ")\n");
	}

	void writeGoto(String labelname) throws IOException {
		output.write("@" + functionName + "$" + labelname + "\n");
		output.write("0;JMP\n");
	}

	void writeIf(String labelname) throws IOException {
		output.write("@SP\n");
		output.write("M=M-1\n");
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("D=M\n");
		output.write("@" + functionName + "$" + labelname + "\n");
		output.write("D;JNE\n");
	}

	void writeInit() throws IOException {
		output.write("@256\n");
        	output.write("D=A\n");
        	output.write("@SP\n");
        	output.write("M=D\n");
        	functionName = "Sys.init";
        	writeCall("Sys.init",0);
	}

	void writeFunction(String funcName, int nVars) throws IOException {
		functionName = funcName;
		output.write("(" + funcName + ")\n");
		for (int i = 0; i < nVars; i++) {
			writePushPop(Constants.C_PUSH, "constant", 0);
		}
	}

	void writeCall(String funcName,int nVars) throws IOException {
		// push retAddress
		output.write("@ret" + cntret + "\n");
		output.write("D=A\n");
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		//push LCL
		output.write("@LCL" + "\n");
		output.write("D=M\n");
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		//push ARG
		output.write("@ARG" + "\n");
		output.write("D=M\n");
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		//push THIS
		output.write("@THIS" + "\n");
		output.write("D=M\n");
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		//push THAT
		output.write("@THAT" + "\n");
		output.write("D=M\n");
		output.write("@SP\n");
		output.write("A=M\n");
		output.write("M=D\n");
		output.write("@SP\n");
		output.write("M=M+1\n");
		//ARG=SP-5-nArgs
		output.write("@SP\n");
		output.write("D=M\n");
		output.write("@5\n");
		output.write("D=D-A\n");
		output.write("@" + nVars + "\n");
		output.write("D=D-A\n");
        	output.write("@ARG\n");
        	output.write("M=D\n");
		//LCL=SP
		output.write("@SP\n");
		output.write("D=M\n");
		output.write("@LCL\n");
		output.write("M=D\n");
		//goto funcName
		output.write("@" + funcName + "\n");
		output.write("0;JMP\n");
		// (returnAddress)
		output.write("(ret" + cntret + ")\n");
		cntret++;
	}

	void writeReturn() throws IOException {
		// endFrame=LCL
		output.write("@LCL\n");
		output.write("D=M\n");
		output.write("@endFrame\n"); // endFrame
		output.write("M=D\n");
		//retAddr=*(endFrame-5)
		output.write("@endFrame\n");
		output.write("D=M\n");
		output.write("@5\n");
		output.write("D=D-A\n");
		output.write("A=D\n");
		output.write("D=M\n");
		output.write("@retAddr\n"); // retAddr
		output.write("M=D\n");
		//*ARG=pop()
		output.write("@SP\n");
		output.write("A=M-1\n");
		output.write("D=M\n");
		output.write("@ARG\n");
		output.write("A=M\n");
		output.write("M=D\n");
		//SP=ARG+1
		output.write("@ARG\n");
		output.write("D=M\n");
		output.write("D=D+1\n");
		output.write("@SP\n");
		output.write("M=D\n");
		//THAT=*(endFrame-1)
		output.write("@endFrame\n");
		output.write("D=M\n");
		output.write("D=D-1\n");
		output.write("A=D\n");
		output.write("D=M\n");
		output.write("@THAT\n");
		output.write("M=D\n");
		//THIS=*(endFrame-2)
		output.write("@endFrame\n");
		output.write("D=M\n");
		output.write("@2\n");
		output.write("D=D-A\n");
		output.write("A=D\n");
		output.write("D=M\n");
		output.write("@THIS\n");
		output.write("M=D\n");
		//ARG=*(endFrame-3)
		output.write("@endFrame\n");
		output.write("D=M\n");
		output.write("@3\n");
		output.write("D=D-A\n");
		output.write("A=D\n");
		output.write("D=M\n");
		output.write("@ARG\n");
		output.write("M=D\n");
		//LCL=*(endFrame-4)
		output.write("@endFrame\n");
		output.write("D=M\n");
		output.write("@4\n");
		output.write("D=D-A\n");
		output.write("A=D\n");
		output.write("D=M\n");
		output.write("@LCL\n");
		output.write("M=D\n");
		//goto retAddr
		output.write("@retAddr\n");
		output.write("A=M\n");
		output.write("0;JMP\n");
	}

	void close() throws IOException {
		output.close();
	}
}
