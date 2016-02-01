package sml.test.instructions;

import org.junit.Test;

import sml.Registers;
import sml.SubInstruction;

/**
 * JUnit tests for instruction sub
 * 
 * @author Vasco
 *
 */
public class TestSubInstruction extends CommonTestInstructions {

	/**
	 * Constructor for common test instructions.
	 */
    public TestSubInstruction() {
    	super();
	}
    
	/**
	 * Testing sub Instruction.
	 */
	@Test
	public void testSubInstruction() {
		foundRegisters.setRegister(1, 10);
		foundRegisters.setRegister(2, -7);

		// Execute instructions
		execute(new SubInstruction("sub", 0, 1, 2));
		execute(new SubInstruction("sub", 0, 0, 1));
		execute(new SubInstruction("sub", 10, 1, 1));

		// Set the expected values.
		Registers expectedRegisters = new Registers();
		expectedRegisters.setRegister(0, 7); // 10 + 7 = 17 - 10
		expectedRegisters.setRegister(1, 10); 
		expectedRegisters.setRegister(2, -7);

		// Verify if all registers match
		verifyRegisters(expectedRegisters, machineMock.getRegisters());
	}
}
