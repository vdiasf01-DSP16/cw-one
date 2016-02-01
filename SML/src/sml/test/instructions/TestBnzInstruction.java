package sml.test.instructions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import sml.BnzInstruction;
import sml.Labels;

/**
 * JUnit tests for instruction bnz
 * 
 * @author Vasco
 *
 */
public class TestBnzInstruction extends CommonTestInstructions {

	/**
	 * Constructor for common test instructions.
	 */
    public TestBnzInstruction() {
    	super();
	}
    
	/**
	 * Testing bnz Instruction when not zero.
	 */
	@Test
	public void testBnzInstruction() {
		// Set some labels
		Labels labels = new Labels();
		labels.addLabel("f0"); // PC 0
		labels.addLabel("f1"); // PC 1
		labels.addLabel("f2"); // PC 2

		foundRegisters.setRegister(1, 10);

		Mockito.when(machineMock.getLabels()).thenReturn(labels);
		Mockito.when(machineMock.getRegisters()).thenReturn(foundRegisters);
		Mockito.when(machineMock.getPc()).thenReturn(3);

		// Execute bnz instruction
		execute(new BnzInstruction("f3", 1, "f1"));

		// We expect the PC to have been set to 
		Mockito.verify(machineMock).setPc(Mockito.eq(1));
	}
	
	/**
	 * Testing bnz Instruction when zero.
	 */
	@Test
	public void testBnzInstructionZero() {
		// Set some labels
		Labels labels = new Labels();
		labels.addLabel("f0"); // PC 0
		labels.addLabel("f1"); // PC 1
		labels.addLabel("f2"); // PC 2

		foundRegisters.setRegister(1, 0);

		Mockito.when(machineMock.getLabels()).thenReturn(labels);
		Mockito.when(machineMock.getRegisters()).thenReturn(foundRegisters);
		Mockito.when(machineMock.getPc()).thenReturn(3);

		// Execute bnz instruction
		execute(new BnzInstruction("f3", 1, "f1"));

		// We expect the PC to have been set to 3 
		assertEquals(machineMock.getPc(),3);
	}
}
