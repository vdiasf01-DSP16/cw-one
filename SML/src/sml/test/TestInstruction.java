package sml.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import sml.AddInstruction;
import sml.BnzInstruction;
import sml.DivInstruction;
import sml.Instruction;
import sml.Labels;
import sml.LinInstruction;
import sml.Machine;
import sml.MulInstruction;
import sml.OutInstruction;
import sml.Registers;
import sml.SubInstruction;

/**
 * JUnit tests for instruction lin
 * 
 * @author Vasco
 *
 */
public class TestInstruction {

	/**
	 * The list of found registers.
	 */
	private Registers foundRegisters;
	
	/**
	 * Machine mock.
	 */
	@Mock
	private Machine machineMock;

	/**
	 * Initialise all mocks.
	 */
	@Before
	public void before() {
		foundRegisters = new Registers();
		machineMock = Mockito.mock(Machine.class);
		machineMock.setRegisters(foundRegisters);
		Mockito.when(machineMock.getRegisters()).thenReturn(foundRegisters);
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

		// We expect the PC to have been set to 
		Mockito.verify(machineMock).setPc(Mockito.eq(4));
	}
	
	/**
	 * Execute given instruction.
	 * 
	 * @param instruction
	 */
	private void execute(Instruction instruction) {
		assertNotNull(instruction);
		instruction.execute(machineMock);
	}
	/**
	 * Test if the found registers match with the expected registers list.
	 * 
	 * @param expectedRegisters
	 * @param foundRegisters
	 */
	private void verifyRegisters(Registers expectedRegisters, Registers foundRegisters) {
		assertNotNull(foundRegisters);
		assertNotNull(expectedRegisters);
		assertEquals(foundRegisters.getRegisters().length, expectedRegisters.getRegisters().length);
		assertTrue(foundRegisters.getRegisters().length > 0);
		for(Integer register = 0; register < expectedRegisters.getRegisters().length; register++ ) {
			assertEquals(expectedRegisters.getRegister(register),foundRegisters.getRegister(register));
		}
	}
}
