package sml.test.translator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import sml.BnzInstruction;
import sml.Instruction;
import sml.Labels;
import sml.LinInstruction;
import sml.MulInstruction;
import sml.OutInstruction;
import sml.SubInstruction;
import sml.Translator;

/**
 * JUnit tests for translator
 * 
 * @author Vasco
 *
 */
public class TestTranslator extends CommonTestTranslator {

	/**
	 * Path to test files.
	 */
	private final String PATH = "sml/test/translator/";
	
	/**
	 * Same label at the beginning.
	 */
	private final String TEST_00 = "TestSameLabel00.sml";

	/**
	 * Same label at the middle.
	 */
	private final String TEST_01 = "TestSameLabel01.sml";

	/**
	 * Same label at the end.
	 */
	private final String TEST_02 = "TestSameLabel02.sml";

	/**
	 * Same label at different points.
	 */
	private final String TEST_03 = "TestSameLabel03.sml";

	/**
	 * Unknown instruction.
	 */
	private final String TEST_04 = "TestSameLabel04.sml";

	/**
	 * Load complete set of instructions and check labels.
	 */
	private final String TEST_05 = "TestLabels00.sml";

    /**
     * Constructor for common tests.
     */
    public TestTranslator() {
		super();
	}

	/**
	 * Testing same label test 00.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTranslator00() {
		translatorHandler = new Translator(PATH+TEST_00);
		translatorHandler.readAndTranslate(labels, prog);
	}

	/**
	 * Testing same label test 01.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTranslator01() {
		translatorHandler = new Translator(PATH+TEST_01);
		translatorHandler.readAndTranslate(labels, prog);
	}

	/**
	 * Testing same label test 02.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTranslator02() {
		translatorHandler = new Translator(PATH+TEST_02);
		translatorHandler.readAndTranslate(labels, prog);
	}

	/**
	 * Testing same label test 03.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTranslator03() {
		translatorHandler = new Translator(PATH+TEST_03);
		translatorHandler.readAndTranslate(labels, prog);
	}
	
	/**
	 * Testing unknown instruction test 04.
	 */
	@Test(expected=NullPointerException.class)
	public void testTranslator04() {
		translatorHandler = new Translator(PATH+TEST_04);
		translatorHandler.readAndTranslate(labels, prog);
	}

	/**
	 * Testing if labels were all loaded.
	 */
	@Test
	public void testTranslator05() {
		translatorHandler = new Translator(PATH+TEST_05);
		translatorHandler.readAndTranslate(labels, prog);
		String[] expectedLabels = {"f0", "f1", "f2", "f3", "f4", "f5", "f6"};
		verifyLabels(expectedLabels, labels);
		ArrayList<Instruction> expectedProg = new ArrayList<>();
		expectedProg.add(new LinInstruction("lin", 0, 6)); // f0 lin 0 6
		expectedProg.add(new LinInstruction("lin", 0, 6)); // f1 lin 1 1
		expectedProg.add(new LinInstruction("lin", 0, 6)); // f2 lin 2 1
		expectedProg.add(new MulInstruction("mul", 1, 1, 0)); // f3 mul 1 1 0
		expectedProg.add(new SubInstruction("sub", 0, 0, 2)); // f4 sub 0 0 2
		expectedProg.add(new BnzInstruction("bnz", 0, "f3")); // f5 bnz 0 f3
		expectedProg.add(new OutInstruction("lin", 1)); // f6 out 1
		verifyProgram(expectedProg, prog);
	}

	/**
	 * Verify expected and found Instruction list contain same amount of
	 * Instructions and in the same order.
	 * 
	 * @param expectedProg ArrayList[Instruction]
	 * @param foundProgram ArrayList[Instruction]
	 */
	private void verifyProgram(ArrayList<Instruction> expectedProg, ArrayList<Instruction> foundProgram) {
		assertNotNull(foundProgram);
		assertEquals(expectedProg.size(), foundProgram.size());

		for( int index = 0; index < expectedProg.size(); index++ ) {
			assertEquals(expectedProg.get(index).getClass(), 
					     foundProgram.get(index).getClass());
		}
	}

	/**
	 * Check if all labels were added in the correct order.
	 * 
	 * @param expected String[]
	 * @param found Labels
	 */
	private void verifyLabels(String[] expected, Labels found) {
		assertNotNull(found);
		int line = 0;
		for( String label : expected ) {
			assertTrue(found.indexOf(label) != -1);
			assertEquals(line, found.indexOf(label));
			line++;
		}
	}
}
