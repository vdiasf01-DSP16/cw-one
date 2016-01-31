package sml;

/**
 * This class executes the instruction by setting the PC counter
 * into a new position if the value of S1 is not zero.
 * The new PC value is set to where label supplied is.
 * 
 * e.g.: 'Label' 'bnz' 'S1' 'L1'
 *       Sets PC to where L1 is if S1 is != 0
 * 
 * @author someone
 */
public class BnzInstruction extends Instruction {

    /**
     * The operator 1.
     */
    private int op1;
    
    /**
     * The operator 2.
     */
    private String op2;

    /**
     * The Instruction constructor.
     * 
     * @param String label
     * @param String op
     */
    public BnzInstruction(String label, String op) {
        super(label, op);
    }

    /**
     * The Instructions constructor.
     * 
     * @param String label
     * @param Integer op1
     * @param String op2
     */
    public BnzInstruction(String label, int op1, String op2) {
        this(label, "bnz");
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
        int newPc  = m.getLabels().indexOf(op2);
        
        // Set the pc into the new found value if op1 is not zero.
        if ( value1 != 0 ) {
        	m.setPc(newPc);
        } else { 
        	m.setPc(1 + m.getPc());
        }
    }

    @Override
    public String toString() {
        return super.toString() + " R" + op1 + " !=0 ? L" + op2 + " : continue";
    }
}
