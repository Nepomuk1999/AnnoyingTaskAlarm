package persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Christoph Bauer on 26.03.2018.
 *
 *          ,/
 *        ./(\
 * -`___-'  |`
 * ''-(  -`--)
 *     7/`
 *     \\
 */

@Entity
public class Task {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "Question")
    private String question;

    @ColumnInfo(name = "RightAnswer")
    private String rightAnswer;

    @ColumnInfo(name = "WrongAnswerA")
    private String wrongAnswerA;

    @ColumnInfo(name = "WrongAnswerB")
    private String wrongAnswerB;

    @ColumnInfo(name = "WrongAnswerC")
    private String wrongAnswerC;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswere) {
        this.rightAnswer = rightAnswere;
    }

    public String getWrongAnswerA() {
        return wrongAnswerA;
    }

    public void setWrongAnswerA(String wrongAnswereA) {
        this.wrongAnswerA = wrongAnswereA;
    }

    public String getWrongAnswerB() {
        return wrongAnswerB;
    }

    public void setWrongAnswerB(String wrongAnswerB) {
        this.wrongAnswerB = wrongAnswerB;
    }

    public String getWrongAnswerC() {
        return wrongAnswerC;
    }

    public void setWrongAnswerC(String wrongAnswereC) {
        this.wrongAnswerC = wrongAnswereC;
    }




}
