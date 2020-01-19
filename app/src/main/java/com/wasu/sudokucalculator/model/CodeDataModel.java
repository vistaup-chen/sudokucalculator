package com.wasu.sudokucalculator.model;

public class CodeDataModel {

    public int x;
    public int y;
    // 填充的数字
    public int filledCode;
    public boolean isBase;

    public CodeDataModel(int x, int y, int filledCode, boolean isBase) {
        this.x = x;
        this.y = y;
        this.filledCode = filledCode;
        this.isBase = isBase;
    }
}
