package sml;

/**
 * This class executes the instruction of sdtOUT given register
 * 
 * e.g.: 'Label' 'out' 'R1' 
 *       println("R1 = " + #R1)
 * 
 * @author someone
 */
public class OutInstruction extends Instruction {

	/**
	 * The result of the instruction.
	 */
    private int result;
    
	/**
	 * The requested register.
	 */
    private int register;
    
    /**
     * The Instruction constructor.
     * 
     * @param String label
     * @param String op
     */
    public OutInstruction(String label, String op) {
        super(label, op);
    }

    /**
     * The Instructions constructor.
     * 
     * @param String label
     * @param Integer result
     */
    public OutInstruction(String label, int register) {
        this(label, "out");
        this.register = register;
    }

    /**
     * The Machine execution for this instruction.
     * 
     * @param Machine m
     */
    @Override
    public void execute(Machine m) {
        // The result of the requested register saved into result.
        result = m.getRegisters().getRegister(register);
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() + " R" + result + " = " + result;
    }
}
