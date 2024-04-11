package io.github.zeculesu.itmo.prog5.data;

import io.github.zeculesu.itmo.prog5.data.SpaceMarine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Ответ получаемый после выполнения команды
 */
public class Response implements Serializable {

    String message;
    String error;
    List<String> output;
    List<SpaceMarine> outputElement;

    public Response(){
        this.output = new ArrayList<>();
        this.outputElement = new ArrayList<>();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<String> output) {
        this.output = output;
    }

    public List<SpaceMarine> getOutputElement() {
        return outputElement;
    }

    public void setOutputElement(ArrayList<SpaceMarine> outputElement) {
        this.outputElement = outputElement;
    }

    public void addLineOutput(String line) {
        this.output.add(line);
    }

    public void addElement(SpaceMarine elem) {
        this.outputElement.add(elem);
    }

    public boolean isMessage(){
        return (this.message != null) && !this.message.isEmpty();
    }

    public boolean isError(){return (this.error != null) && !this.error.isEmpty();}

    public boolean isOutput(){
        return !this.output.isEmpty();
    }
    public boolean isOutputElement(){
        return !this.outputElement.isEmpty();
    }
}
