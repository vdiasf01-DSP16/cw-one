package sml;

/**
 * This class is the superclass of the classes for machine instructions
 *
 * @author someone
 */
public abstract class Instruction {

	/**
	 * The instruction label.
	 */
    protected String label;
    
    /**
     * The operation code of the instruction.
     */
    protected String opcode;

    /**
     * Constructor.
     * An instruction with label l and opcode op.
     * (op must be an operation of the language).
     * 
     * @param label
     * @param op
     */
    public Instruction(String label, String op) {
        this.label = label;
        this.opcode = op;
    }

    /**
     * = the representation "label: opcode" of this Instruction
     */
    @Override
    public String toString() {
        return label + ": " + opcode;
    }

    /**
     * Execute this instruction on machine m.
     * 
     * @param Machine
     */
    public abstract void execute(Machine m);
}
