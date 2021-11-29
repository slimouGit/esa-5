package net.slimou.tictactoecc.Move;

public class Move {

    private char sign;
    private Integer row;
    private Integer column;

    public Move(char sign, Integer row, Integer column) {
        this.sign = sign;
        this.row = row;
        this.column = column;
    }

    public char getSign() {
        return sign;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

}
