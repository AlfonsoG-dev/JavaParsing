package Test;
import java.util.Arrays;
import java.util.List;

import Application.Models.Methods;

public class MethodTest {

    private Methods methods;

    private final String[] TEST_RETURN_TYPES = {
        "JavaSample", "void", "boolean", "int", "Opetators", "Person", "int"
    };
    private final String[] TEST_ARGUMENTS = {
        "()",
        "(int)",
        "(String, String, int, int, String[])",
        "()",
        "(a, b)",
        "(int, String, boolean)",
        "(int, int)"
    };

    public MethodTest() {
        String[] rawDeclarations = {
            "public JavaSample()",
            "public final static void greet(int age)",
            "public boolean isRunning(String b, String a,\nint c, int m,\nString[] as)",
            "int count()",
            "Opetators o = (a, b) -> a + b;",
            "record Person(int age, String name, boolean married)",
            "public int sum(int a, int b);"
        };
        List<String> declarations = Arrays.asList(rawDeclarations);
        methods = new Methods(declarations);
    }
    public void getReturnTypeTest() {
        try {
            List<String> receive = methods.getReturnType();
            List<String> expected = Arrays.asList(TEST_RETURN_TYPES);
            if(!receive.containsAll(expected)) {
                throw new Exception(
                    "Expected: " + expected.toString() + "\nReceive: " + receive.toString()
                );
            }
            System.out.println("[Info] No error where produce during test execution of getReturnTypeTest");
        } catch(Exception e) {
            System.err.println("[Error] While trying to execute getReturnTypeTest:\n" + e.getLocalizedMessage());
        }
    }
    public void getArgumentsTest() {
        try {
            List<String> receive = methods.getArguments();
            List<String> expected = Arrays.asList(TEST_ARGUMENTS);
            if(!receive.containsAll(expected)) {
                throw new Exception(
                    "Expected: " + expected.toString() + "\nReceive: " + receive.toString()
                );
            }
            System.out.println("[Info] No error where produce during test execution of getArgumentsTest");
        } catch (Exception e) {
            System.err.println("[Error] While trying to execute getArgumentsTest:\n" + e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        MethodTest t = new MethodTest();
        t.getReturnTypeTest();
        t.getArgumentsTest();
    }
    
}
