package com.calculato.calculator.Model;

import com.calculator.calculator.Controller.ModelController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    ModelController mControl;

    @BeforeEach
    void beforeEachMethod() {
        mControl = new ModelController();
    }

    @ParameterizedTest
    @ValueSource(strings = {"10.12345"})
    public void isModelNum(String functions) {
        assertEquals("10.12345", mControl.calculation(functions));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1+2"})
    public void isModelPlus(String functions) {
        assertEquals("3.0", mControl.calculation(functions));
    }
    @ParameterizedTest
    @ValueSource(strings = {"1-2"})
    public void isModelMinus(String functions) {
        assertEquals("-1.0", mControl.calculation(functions));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2/(1+1)"})
    public void isModelDivBr(String functions) {
        assertEquals((double)2 / (1 + 1), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2*(5-2)"})
    public void isModelMulBr(String functions) {
        assertEquals((double)2 * (5 - 2), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2^3+2^2"})
    public void isModelPow(String functions) {
        assertEquals((double)8 + 4, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"4mod2"})
    public void isModelMod1(String functions) {
        assertEquals(0.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"((((10))))"})
    public void isModelBrBr(String functions) {
        assertEquals(10.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sqrt9"})
    public void isModelSqrt(String functions) {
        assertEquals(3.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0mod5"})
    public void isModelMod2(String functions) {
        assertEquals(0.0, Double.parseDouble(mControl.calculation(functions)));
    }
    @ParameterizedTest
    @ValueSource(strings = {"log10"})
    public void isModelLog1(String functions) {
        assertEquals(1.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ln2.718281828459045"})
    public void isModelLn1(String functions) {
        assertEquals(1.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"---5"})
    public void isModelMinusMinus(String functions) {
        assertEquals(-5.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"--++5"})
    public void isModelMinusPlus(String functions) {
        assertEquals(5.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10-5"})
    public void isModelMinus2(String functions) {
        assertEquals(5.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0*3"})
    public void isModelMul2(String functions) {
        assertEquals(0.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0^(-1)"})
    public void isModelInf(String functions) {
        assertEquals(pow(0, -1), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ln0"})
    public void isModelMinusInf(String functions) {
        assertEquals(log(0), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-0"})
    public void isModelMinusZero(String functions) {
        assertEquals(0.0, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sin(3.141592653589793/2)"})
    public void isModelSin1(String functions) {
        assertEquals(sin(3.141592653589793 / 2), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sin(0)"})
    public void isModelSin2(String functions) {
        assertEquals(sin(0), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-sin(3.141592653589793/2)"})
    public void isModelSin3(String functions) {
        assertEquals(-sin(3.141592653589793 / 2), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"cos(3.141592653589793)"})
    public void isModelCos1(String functions) {
        assertEquals(cos(3.141592653589793), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"cos(0)"})
    public void isModelCos2(String functions) {
        assertEquals(cos(0.0), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"cos(3.141592653589793/2)+10*-1"})
    public void isModelCos3(String functions) {
        assertEquals(cos(3.141592653589793 / 2) + 10 * -1, Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"tan(0)"})
    public void isModelTan1(String functions) {
        assertEquals(tan(0), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-tan(3.141592653589793/4)"})
    public void isModelTan2(String functions) {
        assertEquals(-tan(3.141592653589793 / 4), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"tan(3.141592653589793/2)"})
    public void isModelTan3(String functions) {
        assertEquals(tan(3.141592653589793 / 2), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"atan(0)"})
    public void isModelAtan1(String functions) {
        assertEquals(atan(0), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asin(0)"})
    public void isModelAsin1(String functions) {
        assertEquals(asin(0), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asin(-1)"})
    public void isModelAsin2(String functions) {
        assertEquals(asin(-1), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"acos(0)"})
    public void isModelAcos1(String functions) {
        assertEquals(acos(0), Double.parseDouble(mControl.calculation(functions)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"acos(1)"})
    public void isModelAcos2(String functions) {
        assertEquals(acos(1), Double.parseDouble(mControl.calculation(functions)));
    }
}
