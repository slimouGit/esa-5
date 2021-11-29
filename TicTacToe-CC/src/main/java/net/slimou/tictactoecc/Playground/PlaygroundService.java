package net.slimou.tictactoecc.Playground;

import net.slimou.tictactoecc.Move.Move;
import org.springframework.stereotype.Service;

@Service
public class PlaygroundService {

    public char[][] generateNewPlayground() {
        return new Playground(new char[][]{
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        }).getChars();
    };

    public void printBoard(char[][] playground) {
        for (char[] row : playground) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public char setMoveOnPlayground(Move move, char[][] playground) {
        return playground[move.getRow()][move.getColumn()] = move.getSign();
    }
}
