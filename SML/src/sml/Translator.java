package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	/**
	 * The static path for where all program files are to be found.
	 */
    private static final String PATH = "/Users/Vasco/Desktop/SDPcwone/cwone/SML/src/";

    // word + line is the part of the current line that's not yet processed
    // word has no whitespace
    // If word and line are not empty, line begins with whitespace
    private String line = "";
    private Labels labels; // The labels of the program being translated
    private ArrayList<Instruction> program; // The program to be created
    private String fileName; // source file of SML code

    /**
     * Constructor.
     * 
     * @param fileName
     */
    public Translator(String fileName) {
        this.fileName = PATH + fileName;
    }

    /**
     * Translate the small program in the file into lab (the labels) and
     * prog (the program) return "no errors were detected"
     */
    public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

        try (Scanner sc = new Scanner(new File(fileName))) {
            // Scanner attached to the file chosen by the user
            labels = lab;
            labels.reset();
            program = prog;
            program.clear();

            try {
                line = sc.nextLine();
            } catch (NoSuchElementException ioE) {
                return false;
            }

            // Each iteration processes line and reads the next line into line
            while (line != null) {
                // Store the label in label
                String label = scan();

                if (label.length() > 0) {
                	// Check if label has not been already added
                	if ( this.labels.indexOf(label) != -1 )
                		throw new IllegalArgumentException("ERROR @ line "+(program.size()+1)+": cannot reuse label '"+label+"'.");
                    Instruction ins = getInstruction(label);
                    if (ins != null) {
                        labels.addLabel(label);
                        program.add(ins);
                    }
                }

                try {
                    line = sc.nextLine();
                } catch (NoSuchElementException ioE) {
                    return false;
                }
            }
        } catch (IOException ioE) {
            System.out.println("File: IO error " + ioE.getMessage());
            System.exit(-1);
            return false;
        }
        return true;
    }

    /**
     * Line should consist of an MML instruction, with its label already
     * removed. Translate line into an instruction with label label
     * and return the instruction.
     * 
     * @param String label 
     * @return Instruction
     */
    public Instruction getInstruction(String label) {

    	if (line.equals(""))
            return null;

        String ins = scan();
        
        // Building the Instruction class name from the instruction.
        String className = "sml." + ins.substring(0, 1).toUpperCase()
    			+ ins.substring(1).toLowerCase() + "Instruction";

        // Get the class for the found name.
    	Class<?> subclass = null;
		try {
			subclass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Get the constructors.
    	Constructor<?>[] cc = subclass.getConstructors();
    	Constructor<?> constructor = null;
    	int maxParams = 0;
    	for( Constructor<?> c : cc ) {
    		int parameterCount = c.getParameterCount();
    		if ( maxParams < parameterCount) {
    			constructor = c;
    			maxParams = parameterCount;
    		}
    	}

        Class<?> parameterTypes[] = constructor.getParameterTypes();
        Object[] parameterList = new Object[parameterTypes.length];

        // First parameter is the instruction.
        parameterList[0] = new String(ins);
        
        // Starting from second parameter, get constructor values.
        for(int index = 1; index < parameterTypes.length; index++) {
        	Type t = parameterTypes[index];
        	switch(t.getTypeName()) {
            	case "int":
            		parameterList[index] = new Integer(scanInt());
            		break;
            	case "java.lang.String":
            		parameterList[index] = new String(scan());
            		break;
                default: 
                	throw new IllegalArgumentException(
                			"Illegal Type: '"+t.getTypeName()+"'");
        	}
        }

        try {
			return (Instruction) constructor.newInstance(parameterList);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

        return null;
    }

    /**
     * Return the first word of line and remove it from line. If there is no
     * word, return ""
     * 
     * @return String
     */
    private String scan() {
        line = line.trim();
        if (line.length() == 0)
            return "";

        int i = 0;
        while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
            i = i + 1;
        }
        String word = line.substring(0, i);
        line = line.substring(i);
        return word;
    }

    /**
     * Return the first word of line as an integer. 
     * If there is any error, return the maximum int
     * 
     * @return Integer
     */
    private int scanInt() {
        String word = scan();
        if (word.length() == 0) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}