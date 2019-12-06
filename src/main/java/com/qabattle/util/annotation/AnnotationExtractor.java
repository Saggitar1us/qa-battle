package com.qabattle.util.annotation;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Aleksei Stepanov
 */

public class AnnotationExtractor {

    private static Set<String> userAnnotations = Set.of("UserType");

    public static Set<Annotation> getAnnotations(ExtensionContext context) {
        Set<Annotation> testAnnotations = new HashSet<>();
        testAnnotations.addAll(getClassAnnotations(context));
        testAnnotations.addAll(getMethodAnnotations(context));
        return testAnnotations.stream()
                .filter(testAnnotation -> userAnnotations.stream()
                        .anyMatch(ann -> testAnnotation.toString().contains(ann)))
                .collect(Collectors.toSet());
    }

    private static Set<Annotation> getClassAnnotations(ExtensionContext context) {
        return Set.of(context.getTestClass().get().getAnnotations());
    }

    private static Set<Annotation> getMethodAnnotations(ExtensionContext context) {
        String testName = context.getTestMethod().get().getName();
        Method testMethod = Stream.of(context.getTestClass().get().getDeclaredMethods())
                .filter(test -> test.getName().equals(testName)).findFirst().get();
        Annotation[] annotations = testMethod.getDeclaredAnnotations();
        return Set.of(annotations);
    }

}
