package com.calculator.calculator.Controller;

import com.calculator.calculator.Model.Model;

public class ModelController {
    public String calculation(String inString) {
        Model mainModel = new Model(inString);
        return mainModel.calculateResult();
    }
}
