package sml;

/**
 * This class manages all Machine registers.
 * 
 * An instance contains 32 registers and methods to access and change them.
 *
 * @author someone
 */
public class Registers {

	/**
	 * The total number of registers.
	 */
    private final static int TOTAL_NUMBER_OF_REGISTERS = 32;
    
    /**
     * The list of registers.
     */
    private int registers[] = new int[TOTAL_NUMBER_OF_REGISTERS];

    /**
     * Constructor.
     */
    public Registers() {
    	// Resetting all registers to zero
        for (int i = 0; i != registers.length; i++) {
            registers[i] = 0;
        }
    }

    /**
     * Setting a register with a value.
     * 
     * @param registerNumber
     * @param value
     */
    public void setRegister(int registerNumber, int value) {
        registers[registerNumber] = value;
    }

    /**
     * The value in register.
     * 
     * @param registerNumber
     * @return Integer
     */
    public int getRegister(int registerNumber) {
        return registers[registerNumber];
    }

    /**
     * The list of all registers.
     * 
     * @return Integer[]
     */
    public int[] getRegisters() {
        return this.registers;
    }

    /**
     * Setting all registers.
     * 
     * @param registers
     */
    public void setRegisters(int[] registers) {
        this.registers = registers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Registers)) return false;
        final Registers other = (Registers) o;
        if (!other.canEqual((Object) this)) return false;
        if (!java.util.Arrays.equals(this.registers, other.registers)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + java.util.Arrays.hashCode(this.registers);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Registers;
    }

    @Override
    public String toString() {
        return "sml.Registers(registers=" + java.util.Arrays.toString(this.registers) + ")";
    }
}