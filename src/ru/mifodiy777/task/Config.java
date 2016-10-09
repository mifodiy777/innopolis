package ru.mifodiy777.task;

/**
 * Created by innopolis on 09.10.16.
 */
public class Config {

    String srcInput;

    String srcOutput;

    String nameInput;

    String nameOutput;

    public Config(String srcInput, String srcOutput, String nameInput, String nameOutput) {
        this.srcInput = srcInput;
        this.srcOutput = srcOutput;
        this.nameInput = nameInput;
        this.nameOutput = nameOutput;
    }

    public Config() {
    }


    public String getSrcInput() {
        return srcInput;
    }

    public void setSrcInput(String srcInput) {
        this.srcInput = srcInput;
    }

    public String getSrcOutput() {
        return srcOutput;
    }

    public void setSrcOutput(String srcOutput) {
        this.srcOutput = srcOutput;
    }

    public String getNameInput() {
        return nameInput;
    }

    public void setNameInput(String nameInput) {
        this.nameInput = nameInput;
    }

    public String getNameOutput() {
        return nameOutput;
    }

    public void setNameOutput(String nameOutput) {
        this.nameOutput = nameOutput;
    }
}
