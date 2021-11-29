package net.slimou.tictactoecc;

import net.slimou.tictactoecc.Move.Move;
import net.slimou.tictactoecc.Move.MoveService;
import net.slimou.tictactoecc.Playground.Playground;
import net.slimou.tictactoecc.Playground.PlaygroundService;
import net.slimou.tictactoecc.Validation.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@SpringBootTest
class TicTacToeCcApplicationTests {

	@Autowired
	private MoveService moveService;

	@Autowired
	private PlaygroundService playgroundService;

	@Autowired
	private ValidationService validationService;

	@Test
	void createPlayground() {
		char[][] playground = this.playgroundService.generateNewPlayground();
		assertNotNull(playground);
	}

	@Test
	void createMove() {
		Move move = this.moveService.generateNewMoveObject('x',0,0);
		assertNotNull(move);
	}

	@Test
	void isMoveValid() {
		char[][] playground = this.playgroundService.generateNewPlayground();
		assertNotNull(playground);
		Move move = this.moveService.generateNewMoveObject('x',0,0);
		assertNotNull(move);
		boolean isMoveValid = this.validationService.isMoveValid(move, playground);
		assertTrue(isMoveValid);
	}

	@Test
	void isMoveNotValid() {
		char[][] playground = this.playgroundService.generateNewPlayground();
		assertNotNull(playground);
		Move move = this.moveService.generateNewMoveObject('x',3,3);
		assertNotNull(move);
		boolean isMoveValid = this.validationService.isMoveValid(move, playground);
		assertFalse(isMoveValid);
	}

	@Test
	void isMatchWonTrue() {
		char[][] playground = this.playgroundService.generateNewPlayground();
		Move m1 = this.moveService.generateNewMoveObject('x',0,0);
		this.playgroundService.setMoveOnPlayground(m1, playground);
		Move m2 = this.moveService.generateNewMoveObject('x',1,1);
		this.playgroundService.setMoveOnPlayground(m2, playground);
		Move m3 = this.moveService.generateNewMoveObject('x',2,2);
		this.playgroundService.setMoveOnPlayground(m3, playground);
		boolean isMatchWon = this.validationService.isMatchWonByCurrentMove(m3, playground);
		assertTrue(isMatchWon);
	}

	@Test
	void isMatchWonFalse() {
		char[][] playground = this.playgroundService.generateNewPlayground();
		Move m1 = this.moveService.generateNewMoveObject('x',0,0);
		this.playgroundService.setMoveOnPlayground(m1, playground);
		Move m2 = this.moveService.generateNewMoveObject('x',1,1);
		this.playgroundService.setMoveOnPlayground(m2, playground);
		Move m3 = this.moveService.generateNewMoveObject('x',1,2);
		this.playgroundService.setMoveOnPlayground(m3, playground);
		boolean isMatchWon = this.validationService.isMatchWonByCurrentMove(m3, playground);
		assertFalse(isMatchWon);
	}

}
