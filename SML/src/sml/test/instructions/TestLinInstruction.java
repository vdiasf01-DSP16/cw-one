package sml.test.instructions;

import org.junit.Test;

import sml.LinInstruction;
import sml.Registers;

/**
 * JUnit tests for instruction lin
 * 
 * @author Vasco
 *
 */
public class TestLinInstruction extends CommonTestInstructions {

	/**
	 * Constructor for common test instructions.
	 */
    public TestLinInstruction() {
    	super();
	}
    
	/**
	 * Testing lin Instruction.
	 */
	@Test
	public void testLinInstruction() {
		// Execute instructions
		execute(new LinInstruction("lin", 1, 2));
		execute(new LinInstruction("lin", 0, 3));

		// Set the expected values.
		Registers expectedRegisters = new Registers();
		expectedRegisters.setRegister(1, 2);
		expectedRegisters.setRegister(0, 3);

		// Verify if all registers match
		verifyRegisters(expectedRegisters, machineMock.getRegisters());
	}
}
