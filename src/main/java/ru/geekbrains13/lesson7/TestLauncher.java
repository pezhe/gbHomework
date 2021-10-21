package ru.geekbrains13.lesson7;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class TestLauncher {

    public static void start(Class<?> tester) throws
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException {
        Method[] methods = tester.getDeclaredMethods();
        Method before = null;
        Method after = null;
        List<Method> tests = new LinkedList<>();
        for (Method m : methods) {
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations) {
                if (a.annotationType().equals(BeforeSuite.class)) {
                    if (before == null) {
                        before = m;
                        break;
                    } else {
                        throw new RuntimeException("More than one @BeforeSuite method");
                    }
                }
                if (a.annotationType().equals(AfterSuite.class)) {
                    if (after == null) {
                        after = m;
                        break;
                    } else {
                        throw new RuntimeException("More than one @AfterSuite method");
                    }
                }
                if (a.annotationType().equals(Test.class)) {
                    int priority = ((Test) a).priority();
                    int i = 0;
                    while (i < tests.size()) {
                        if (tests.get(i).getAnnotation(Test.class).priority() > priority) {
                            i++;
                        } else break;
                    }
                    tests.add(i, m);
                    break;
                }
            }
        }
        if (before != null) before.invoke(null, null);
        for (Method m : tests) {
            m.invoke(null, null);
        }
        if (after != null) after.invoke(null, null);
    }

}
