package net.slimou.tictactoecc;

import net.slimou.tictactoecc.Move.Move;
import net.slimou.tictactoecc.Move.MoveService;
import net.slimou.tictactoecc.Playground.PlaygroundService;
import net.slimou.tictactoecc.UserInput.UserInput;
import net.slimou.tictactoecc.Validation.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Profile("!test")
@Component
public class TicTacToeRunner {

    private static final Logger log = LoggerFactory.getLogger(TicTacToeRunner.class);

    private UserInput userInput;
    private ValidationService validationService;
    private PlaygroundService playgroundService;
    private MoveService moveService;
    private char[][] playground;


    public TicTacToeRunner(UserInput userInput, ValidationService validationService, PlaygroundService playgroundService, MoveService moveService) {
        this.userInput = userInput;
        this.validationService = validationService;
        this.playgroundService = playgroundService;
        this.moveService = moveService;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            startGame();
        };
    }

    public void startGame() throws Exception {
        log.info("Welcome to TicTacToe");
        this.playground = this.playgroundService.generateNewPlayground();
        this.receiveUserInput();
    }

    public void receiveUserInput() throws IOException {
        this.userInput.getInput();
        Move move = this.moveService.generateNewMoveObject(this.userInput.getSign(), this.userInput.getRow(), this.userInput.getColumn());
        this.checkInputBeforeExecute(move);
    }

    public void checkInputBeforeExecute(Move move) throws IOException {
        if (this.validationService.isMoveValid(move, this.playground)) {
            this.play(move);
        } else {
            this.receiveUserInput();
        }
    }

    private void play(Move move) throws IOException {
        this.playgroundService.setMoveOnPlayground(move, this.playground);
        this.playgroundService.printBoard(this.playground);
        if (this.validationService.isMatchWonByCurrentMove(move, this.playground)) {
            log.info("{} has won the game", move.getSign());
        } else {
            this.receiveUserInput();
        }
    }
}



