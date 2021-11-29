package net.slimou.tictactoecc.UserInput;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class UserInput {

    public enum Field {
        ROW("Which row"), COLUMN("Which column");

        public final String label;

        Field(String label) {
            this.label = label;
        }
    }

    private char sign;
    private Integer row;
    private Integer column;

    public void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Whats your Sign");
        String sign = reader.readLine();
        setSign(sign.charAt(0));
        selectField(reader, Field.ROW);
        selectField(reader, Field.COLUMN);
    }

    private void selectField(BufferedReader reader, Field field) {
        System.out.println(field.label);
        try {
            if (field.equals(Field.ROW)) {
                this.row = Integer.parseInt(reader.readLine());
            } else {
                this.column = Integer.parseInt(reader.readLine());
            }
        } catch (Exception e) {
            System.out.println("Falsche Eingabe");
            selectField(reader, field);
        }
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

}
