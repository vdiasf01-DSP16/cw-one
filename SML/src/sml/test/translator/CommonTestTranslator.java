package sml.test.translator;

import java.util.ArrayList;

import org.junit.Before;

import sml.Instruction;
import sml.Labels;
import sml.Translator;

/**
 * JUnit tests loader for all instructions
 * 
 * @author Vasco
 *
 */
public abstract class CommonTestTranslator {
	/**
	 * Labels.
	 */
	protected Labels labels;

	/**
	 * List of all loaded instructions.
	 */
	protected ArrayList<Instruction> prog;
	
	/**
	 * The handler to the Translator.
	 */
	protected Translator translatorHandler;

    /**
     * Initialisations.
     */
	@Before
	public void before() {
        labels = new Labels();
        prog = new ArrayList<>();
	}

}
