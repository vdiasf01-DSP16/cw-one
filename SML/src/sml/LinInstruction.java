package sml;

/**
 * This class executes the instruction of setting a register
 * with the given value.
 * 
 * e.g.: 'Label' 'lin' 'R1' '#123'
 *       R1 = 123
 * 
 * @author someone
 */
public class LinInstruction extends Instruction {
	
	/**
	 * The register to update.
	 */
    private int register;
    
    /**
     * The value set to register.
     */
    private int value;

    /**
     * The super constructor for label and operation instruction code.
     * 
     * @param label
     * @param opcode
     */
    public LinInstruction(String label, String opcode) {
        super(label, opcode);
    }

    /**
     * Constructor for the expected complete line of code.
     * 
     * @param label
     * @param register
     * @param value
     */
    public LinInstruction(String label, int register, int value) {
        super(label, "lin");
        this.register = register;
        this.value = value;
    }

    /**
     * Execute the lin instruction on the Machine.
     */
    @Override
    public void execute(Machine m) {
    	// The core of the lin code, setting given register
    	// with the given value.
        m.getRegisters().setRegister(register, value);
    }

    /**
     * Printing out a user-friendly version of what was done in this instruction.
     */
    @Override
    public String toString() {
        return super.toString() + " R" + register + " = " + value;
    }
}
