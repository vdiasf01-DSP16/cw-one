package sml.test.instructions;

import org.junit.Test;
import org.mockito.Mockito;

import sml.OutInstruction;
import sml.Registers;

/**
 * JUnit tests for instruction out
 * 
 * @author Vasco
 *
 */
public class TestOutInstruction extends CommonTestInstructions {

	/**
	 * Constructor for common test instructions.
	 */
    public TestOutInstruction() {
    	super();
	}
    
	/**
	 * Testing out Instruction.
	 */
	@Test
	public void testOutInstruction() {
		// Mock the found register for this
		foundRegisters = Mockito.mock(Registers.class);
		// Ensure we return the value of the register when called
		Mockito.when(foundRegisters.getRegister(1)).thenReturn(10);
		// Ensure we return the foundRegisters when requested
		Mockito.when(machineMock.getRegisters()).thenReturn(foundRegisters);

		// Execute out instruction
		execute(new OutInstruction("out", 1));

		// Verify that the getRegisters() was called
		Mockito.verify(machineMock, Mockito.times(1)).getRegisters();
		// Verify that the getRegister(1) was called
		Mockito.verify(foundRegisters, Mockito.times(1)).getRegister(1);
	}
}
