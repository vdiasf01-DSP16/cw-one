package sml.test.instructions;

import org.junit.Test;

import sml.AddInstruction;
import sml.Registers;

/**
 * JUnit tests for instruction add
 * 
 * @author Vasco
 *
 */
public class TestAddInstruction extends CommonTestInstructions {

	/**
	 * Constructor for common test instructions.
	 */
    public TestAddInstruction() {
    	super();
	}
    
	/**
	 * Testing add Instruction.
	 */
	@Test
	public void testAddInstruction() {
		foundRegisters.setRegister(1, 10);
		foundRegisters.setRegister(2, -7);

		// Execute instructions
		execute(new AddInstruction("add", 0, 1, 2));
		execute(new AddInstruction("add", 0, 0, 1));
		execute(new AddInstruction("add", 10, 10, 10));

		// Set the expected values.
		Registers expectedRegisters = new Registers();
		expectedRegisters.setRegister(0, 13);
		expectedRegisters.setRegister(1, 10);
		expectedRegisters.setRegister(2, -7);

		// Verify if all registers match
		verifyRegisters(expectedRegisters, machineMock.getRegisters());
	}
}
