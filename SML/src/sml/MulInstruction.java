package sml;

/**
 * This class executes the instruction of multiplying values 
 * of two registers into the first supplied register.
 * 
 * e.g.: 'Label' 'mul' 'R1' 'R2' 'R3'
 *       R1 = R2 * R3
 * 
 * @author someone
 */
public class MulInstruction extends Instruction {

	/**
	 * The result of the instruction.
	 */
    private int result;
    
    /**
     * The operator 1.
     */
    private int op1;
    
    /**
     * The operator 2.
     */
    private int op2;

    /**
     * The Instruction constructor.
     * 
     * @param String label
     * @param String op
     */
    public MulInstruction(String label, String op) {
        super(label, op);
    }

    /**
     * The Instructions constructor.
     * 
     * @param String label
     * @param Integer result
     * @param Integer op1
     * @param Integer op2
     */
    public MulInstruction(String label, int result, int op1, int op2) {
        this(label, "mul");
        this.result = result;
        this.op1 = op1;
        this.op2 = op2;
    }

    /**
     * The Machine execution for this instruction.
     * 
     * @param Machine m
     */
    @Override
    public void execute(Machine m) {
        int value1 = m.getRegisters().getRegister(op1);
        int value2 = m.getRegisters().getRegister(op2);
        // The addition core operation.
        m.getRegisters().setRegister(result, value1 * value2);
    }

    @Override
    public String toString() {
        return super.toString() + " R" + op1 + " + R" + op2 + " -> R" + result;
    }
}
