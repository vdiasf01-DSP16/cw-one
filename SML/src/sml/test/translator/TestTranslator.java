package sml.test.translator;

import org.junit.Test;

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
}
