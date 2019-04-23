package com.manipovore.calculator;

public class Calcul {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        Operation op = new Operation(result);
        this.result = String.valueOf(op.getResult());
    }
}
