package net.slimou.tictactoecc.Validation;

import net.slimou.tictactoecc.Move.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private static final Logger log = LoggerFactory.getLogger(ValidationService.class);

    public boolean isMoveValid(Move move, char[][] playground) {
        boolean isMoveValid;
        if (move.getRow() > playground.length - 1 || move.getColumn() > playground.length - 1) {
            log.info("error");
            isMoveValid = false;
        } else if (playground[move.getRow()][move.getColumn()] != '_') {
            log.info("not empty field");
            isMoveValid = false;
        } else {
            isMoveValid = true;
        }
        return isMoveValid;
    }

    public boolean isMatchWonByCurrentMove(Move move, char[][] playground) {
        return checkHorizontalAndVerticalRows(move, playground) || checkDiagonalRows(move, playground);
    }

    private boolean checkHorizontalAndVerticalRows(Move move, char[][] playground) {
        boolean matchIsWon = false;
        for (int i = 0; i <= 2; i++) {
            if (playground[i][0] == move.getSign() && playground[i][1] == move.getSign() && playground[i][2] == move.getSign()) {
                matchIsWon = true;
            }
            if (playground[0][i] == move.getSign() && playground[1][i] == move.getSign() && playground[2][i] == move.getSign()) {
                matchIsWon = true;
            }
        }
        return matchIsWon;
    }

    private boolean checkDiagonalRows(Move move, char[][] playground) {
        boolean matchIsWon = false;
        if (playground[2][0] == move.getSign() && playground[1][1] == move.getSign() && playground[0][2] == move.getSign()) {
            matchIsWon = true;
        }
        if (playground[0][0] == move.getSign() && playground[1][1] == move.getSign() && playground[2][2] == move.getSign()) {
            matchIsWon = true;
        }
        return matchIsWon;
    }

}
