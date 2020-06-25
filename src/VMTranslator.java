import java.io.File;
import java.io.IOException;

public class VMTranslator {
	static String filename;
	static File file;

	public static void run() {
		file = new File(filename);
		String[] outputName = null;

		if (file.isFile()) {
			if (filename.contains(".vm")) {
				outputName = new String[1];
				outputName[0] = filename.split("[.]")[0] + ".vm";
			} else {
				System.out.println("Not a vm file");
				return;
			}
		} else if (file.isDirectory()) {
			outputName = file.list();
			for (int i = 0; i < outputName.length; i++) {
				if (!outputName[i].contains(".vm")) {
					outputName[i] = "";
				} else {
					outputName[i] = filename + "/" + outputName[i];
				}
			}
			for(int i=0;i<outputName.length;i++){
				if(outputName[i].contains("Sys.vm")){
					String temp = outputName[0];
					outputName[0] = outputName[i];
					outputName[i] = temp;
					break;
				}
			}
		} else {
			System.out.println("Not a vm file");
			return;
		}
		Parser parser;
		CodeWriter codeWriter;
		if(file.isDirectory()){
			codeWriter = new CodeWriter(filename + "/" + filename.split("[.]")[0] + ".asm");
		}else{
			codeWriter = new CodeWriter(filename.split("[.]")[0] + ".asm");
		}
		try {
			for (int i = 0; i < outputName.length; i++) {
				if (outputName[i].equals(""))
					continue;
				System.out.println(outputName[i]);
				parser = new Parser(outputName[i]);
				codeWriter.setFileName(outputName[i]);
				while (parser.hasMoreCommands()) {
					parser.advance();
					int commandType = parser.commandType();
					if (commandType == Constants.C_ARITH) {
						String segment = parser.arg1();
						codeWriter.writeArithmetic(segment);
					} else if (commandType == Constants.C_PUSH
							|| commandType == Constants.C_POP) {
						String command = parser.arg1();
						int index = parser.arg2();
						codeWriter.writePushPop(commandType, command, index);
					} else if (commandType == Constants.C_GOTO) {
						String labelname = parser.arg1();
						codeWriter.writeGoto(labelname);
					} else if (commandType == Constants.C_IF) {
						String labelname = parser.arg1();
						codeWriter.writeIf(labelname);
					} else if (commandType == Constants.C_LABEL) {
						String labelname = parser.arg1();
						codeWriter.writeLabel(labelname);
					} else if (commandType == Constants.C_FUNCTION) {
						String funcName = parser.arg1();
						int nVars = parser.arg2();
						codeWriter.writeFunction(funcName, nVars);
					} else if (commandType == Constants.C_RETURN) {
						codeWriter.writeReturn();
					} else if (commandType == Constants.C_CALL) {
						String funcName = parser.arg1();
						int nVars = parser.arg2();
						codeWriter.writeCall(funcName, nVars);
					}
				}
				parser.close();
			}
			codeWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		filename = args[0];
		if (args.length > 1) {
			filename += " " + args[1];
		}
		run();
	}
}
