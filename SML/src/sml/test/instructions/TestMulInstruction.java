package sml.test.instructions;

import org.junit.Test;

import sml.MulInstruction;
import sml.Registers;

/**
 * JUnit tests for instruction mul
 * 
 * @author Vasco
 *
 */
public class TestMulInstruction extends CommonTestInstructions {

	/**
	 * Constructor for common test instructions.
	 */
    public TestMulInstruction() {
    	super();
	}
    
	/**
	 * Testing mul Instruction.
	 */
	@Test
	public void testMulInstruction() {
		foundRegisters.setRegister(1, 10);
		foundRegisters.setRegister(2, -7);

		// Execute instructions
		execute(new MulInstruction("mul", 0, 1, 2));
		execute(new MulInstruction("mul", 0, 0, 1));
		execute(new MulInstruction("mul", 5, 1, 3));

		// Set the expected values.
		Registers expectedRegisters = new Registers();
		expectedRegisters.setRegister(0, -700);
		expectedRegisters.setRegister(1, 10);
		expectedRegisters.setRegister(2, -7);

		// Verify if all registers match
		verifyRegisters(expectedRegisters, machineMock.getRegisters());
	}
}
