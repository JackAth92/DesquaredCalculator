package com.jackathan.desquaredcalculator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectUtilsTest {

    @Test
    public void test_convertSimpleEquationStringToList() {
        List<String> result = new ArrayList<>();
        result.add("152");
        result.add("*");
        result.add("10");
        Assert.assertEquals(result, ProjectUtils.ConvertStringToList("152*10"));
    }

    @Test
    public void test_convertComplexEquationStringToList() {
        List<String> result = new ArrayList<>();
        result.add("152");
        result.add("*");
        result.add("(");
        result.add("10");
        result.add("-");
        result.add("2");
        result.add(")");
        Assert.assertEquals(result, ProjectUtils.ConvertStringToList("152*(10-2)"));
    }

    @Test
    public void test_checkExpressionValidity() {
        Assert.assertTrue(ProjectUtils.isExpressionValid("(())"));
        Assert.assertFalse(ProjectUtils.isExpressionValid("(()"));
        Assert.assertFalse(ProjectUtils.isExpressionValid("152*(10-2))"));
        Assert.assertTrue(ProjectUtils.isExpressionValid("(152*(10-2))"));
        Assert.assertFalse(ProjectUtils.isExpressionValid("(((152*(10-2))"));
    }

    @Test
    public void test_isOperator() {
        Assert.assertTrue(ProjectUtils.isOperator("+"));
        Assert.assertTrue(ProjectUtils.isOperator("-"));
        Assert.assertTrue(ProjectUtils.isOperator("*"));
        Assert.assertTrue(ProjectUtils.isOperator("/"));
        Assert.assertFalse(ProjectUtils.isOperator("5.21"));
        Assert.assertFalse(ProjectUtils.isOperator("a"));
        Assert.assertFalse(ProjectUtils.isOperator("a1"));
    }

}
