package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by intern on 7/17/15.
 */
public class TestRunner <T> {
    private List<Method> beforeMethods = new ArrayList<Method>();
    private List<Method> afterMethods = new ArrayList<Method>();
    private List<Method> testMethods= new ArrayList<Method>();

    public void RunTestOnClass(Class<T> klass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));
        Object obj = klass.newInstance();

        parseMethods(allMethods);

        invokeBefore(obj);
        invokeTest(obj);
        invokeAfter(obj);

    }

    private void invokeBefore(Object obj) throws IllegalAccessException, InvocationTargetException {
        for (Method method : beforeMethods) {
            method.invoke(obj);
        }
    }
    private void invokeTest(Object obj) throws IllegalAccessException, InvocationTargetException {
        for (Method method : testMethods) {
            method.invoke(obj);
        }
    }

    private void invokeAfter(Object obj) throws IllegalAccessException, InvocationTargetException {
        for (Method method : afterMethods) {
            method.invoke(obj);
        }
    }

    private void parseMethods(List<Method> allMethods) {
        for (Method method : allMethods) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }
    }

}
