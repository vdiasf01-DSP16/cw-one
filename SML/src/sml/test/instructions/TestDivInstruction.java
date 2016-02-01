package sml.test.instructions;

import org.junit.Test;

import sml.DivInstruction;
import sml.Registers;

/**
 * JUnit tests for instruction adddiv
 * 
 * @author Vasco
 *
 */
public class TestDivInstruction extends CommonTestInstructions {

	/**
	 * Constructor for common test instructions.
	 */
    public TestDivInstruction() {
    	super();
	}
	/**
	 * Testing div Instruction.
	 */
	@Test
	public void testDivInstruction() {
		foundRegisters.setRegister(1, 100);
		foundRegisters.setRegister(2, 10);

		// Execute instructions
		execute(new DivInstruction("div", 0, 1, 2));
		execute(new DivInstruction("div", 0, 0, 1));
		execute(new DivInstruction("div", 5, 1, 2));

		// Set the expected values.
		Registers expectedRegisters = new Registers();
		expectedRegisters.setRegister(0, 0);
		expectedRegisters.setRegister(1, 100);
		expectedRegisters.setRegister(2, 10);
		expectedRegisters.setRegister(5, 10);

		// Verify if all registers match
		verifyRegisters(expectedRegisters, machineMock.getRegisters());
	}
	
	/**
	 * Testing div Instruction on dividing by zero.
	 */
	@Test(expected=ArithmeticException.class)
	public void testDivInstructionByZero() {
		foundRegisters.setRegister(1, 10);

		// Execute div by zero instruction for exception
		execute(new DivInstruction("div", 0, 1, 2));
	}
}
