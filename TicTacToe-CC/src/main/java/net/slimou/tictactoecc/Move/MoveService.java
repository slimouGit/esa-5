package net.slimou.tictactoecc.Move;

import org.springframework.stereotype.Service;

@Service
public class MoveService {

    public Move generateNewMoveObject(char sign, Integer row, Integer column) {
        return new Move(sign, row, column);
    }

}
