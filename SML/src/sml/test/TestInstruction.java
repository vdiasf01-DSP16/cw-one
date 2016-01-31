package sml.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import sml.AddInstruction;
import sml.Instruction;
import sml.LinInstruction;
import sml.Machine;
import sml.Registers;

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
		execute(new LinInstruction("test", 1, 2));
		execute(new LinInstruction("test", 0, 3));

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
		execute(new AddInstruction("test", 0, 1, 2));
		execute(new AddInstruction("test", 0, 0, 1));
		execute(new AddInstruction("test", 10, 10, 10));

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
		execute(new SubInstruction("test", 0, 1, 2));
		execute(new SubInstruction("test", 0, 0, 1));
		execute(new SubInstruction("test", 10, 1, 1));

		// Set the expected values.
		Registers expectedRegisters = new Registers();
		expectedRegisters.setRegister(0, -7);
		expectedRegisters.setRegister(1, -7);
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
		execute(new MulInstruction("test", 0, 1, 2));
		execute(new MulInstruction("test", 0, 0, 1));
		execute(new MulInstruction("test", 5, 1, 3));

		// Set the expected values.
		Registers expectedRegisters = new Registers();
		expectedRegisters.setRegister(0, -700);
		expectedRegisters.setRegister(1, 10);
		expectedRegisters.setRegister(2, -7);

		// Verify if all registers match
		verifyRegisters(expectedRegisters, machineMock.getRegisters());
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
