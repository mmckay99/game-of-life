package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;

import org.junit.Test;

import static org.junit.Assert.*;

public class LifeTest {
	@Test
	public void testEvolveEmptyGrid() {
		String emptyStateInput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState emptyState = new GameStateImpl(emptyStateInput);
		assertEquals("An empty grid should stay the same", emptyStateInput, testLife.evolve(emptyState).toString());
	}

	@Test
	public void testEvolveUnderpopulation() {
		Life testLife = new LifeImpl();

		String zeroNeighbourStateString = "...\n.*.\n...";
		String zeroNeighbourStateExpectedEvolvedString = "...\n...\n...";

		GameState zeroNeighbourState = new GameStateImpl(zeroNeighbourStateString);
		String zeroNeighbourStateActualEvolvedString = testLife.evolve(zeroNeighbourState).toString();

		String oneNeighbourStateString = "...\n.*.\n...";
		String oneNeighbourStateExpectedEvolvedString = "...\n...\n...";

		GameState oneNeighbourState = new GameStateImpl(oneNeighbourStateString);
		String oneNeighbourStateActualEvolvedString = testLife.evolve(oneNeighbourState).toString();

		assertEquals("a cell with zero neighbours will not stay alive",
				zeroNeighbourStateExpectedEvolvedString,
				zeroNeighbourStateActualEvolvedString);

		assertEquals("a cell with one neighbour will not stay alive",
				oneNeighbourStateExpectedEvolvedString,
				oneNeighbourStateActualEvolvedString);
	}

	@Test
	public void testEvolveOverpopulation() {
		Life testLife = new LifeImpl();

		String fourNeighbourStateString = "***\n**.";
		String fourNeighbourStateExpectedEvolvedString = "*.*\n*.*";

		GameState fourNeighbourState = new GameStateImpl(fourNeighbourStateString);
		String fourNeighbourStateActualEvolvedString = testLife.evolve(fourNeighbourState).toString();

		assertEquals("a cell with four neighbours will not stay alive",
				fourNeighbourStateExpectedEvolvedString,
				fourNeighbourStateActualEvolvedString);
	}

	@Test
	public void testEvolveSurvival() {
		Life testLife = new LifeImpl();

		String steadyStateStateString = "**.\n**.";
		String steadyStateExpectedEvolvedString = "**.\n**.";

		GameState steadyState = new GameStateImpl(steadyStateStateString);
		String steadyStateActualEvolvedString = testLife.evolve(steadyState).toString();

		assertEquals("cells with 3 neighbours will survive",
				steadyStateExpectedEvolvedString,
				steadyStateActualEvolvedString);
	}

	@Test
	public void testEvolveCreationOfLife() {
		Life testLife = new LifeImpl();

		String birthStateStateString = "*.*\n.*.";
		String birthStateExpectedEvolvedString = ".*.\n.*.";

		GameState birthState = new GameStateImpl(birthStateStateString);
		String birthStateActualEvolvedString = testLife.evolve(birthState).toString();

		assertEquals("non-living cells with 3 neighbours will become alive",
				birthStateExpectedEvolvedString,
				birthStateActualEvolvedString);
	}

	/**
	 * This tests the behaviour of the "spinner": three horizontal living cells
	 * will become three vertical living cells, then in the next evolution three
	 * horizontal living cells again.
	 */
	@Test
	public void testEvolveThreeCellSpinner() {
		Life testLife = new LifeImpl();

		String horizontalStateString = "...\n***\n...";
		String expectedVerticalStateString = ".*.\n.*.\n.*.";

		GameState firstHorizontalState = new GameStateImpl(horizontalStateString);
		GameState secondVerticalState = testLife.evolve(firstHorizontalState);
		String secondVerticalStateString = secondVerticalState.toString();

		assertEquals("horizontal three cell line should become vertical three cell line",
				expectedVerticalStateString,
				secondVerticalStateString);

		GameState thirdHorizontalState = testLife.evolve(secondVerticalState);
		String thirdHorizontalStateString = thirdHorizontalState.toString();

		assertEquals("vertical three cell line should become horizontal three cell line",
				horizontalStateString,
				thirdHorizontalStateString);


	}

	@Test
	public void testEvolveKnownExample() {
		// Game Of Life tool at http://www.dcode.fr/game-of-life
		// used to generate example test case.
		String knownExampleString = "**.*...\n.**..**\n**.....\n..***.*\n.*.*...\n.......\n.....*.";
		String expectedOneStepEvolveString = "**.....\n.......\n*...*.*\n*..**..\n...**..\n.......\n.......";

		GameState knownExampleState = new GameStateImpl(knownExampleString);
		Life life = new LifeImpl();

		String actualOneStepEvolveString = life.evolve(knownExampleState).toString();

		assertEquals("known example evolves correctly",
				expectedOneStepEvolveString,
				actualOneStepEvolveString);
	}
}
