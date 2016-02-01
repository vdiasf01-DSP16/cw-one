package sml.test.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

/**
 * JUnit tests loader for all instructions
 * 
 * @author Vasco
 *
 */
public abstract class CommonTestInstructions {

	/**
	 * The list of found registers.
	 */
	protected Registers foundRegisters;
	
	/**
	 * Machine mock.
	 */
	@Mock
	protected Machine machineMock;

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
	 * Execute given instruction.
	 * 
	 * @param instruction
	 */
	protected void execute(Instruction instruction) {
		assertNotNull(instruction);
		instruction.execute(machineMock);
	}

	/**
	 * Test if the found registers match with the expected registers list.
	 * 
	 * @param expectedRegisters
	 * @param foundRegisters
	 */
	protected void verifyRegisters(Registers expectedRegisters, Registers foundRegisters) {
		assertNotNull(foundRegisters);
		assertNotNull(expectedRegisters);
		assertEquals(foundRegisters.getRegisters().length, expectedRegisters.getRegisters().length);
		assertTrue(foundRegisters.getRegisters().length > 0);
		for(Integer register = 0; register < expectedRegisters.getRegisters().length; register++ ) {
			assertEquals(expectedRegisters.getRegister(register),foundRegisters.getRegister(register));
		}
	}
}
