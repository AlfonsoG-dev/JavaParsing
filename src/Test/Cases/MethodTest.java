package Test.Cases;
import java.util.Arrays;
import java.util.List;

import Application.Models.Methods;

public class MethodTest {

    private Methods methods;

    /**
     * test data base on JavaSample file on docs folder
     */
    private final String[] TEST_RETURN_TYPES = {
        "JavaSample", "void", "boolean", "int", "Opetators", "Person", "int"
    };
    private final String[] TEST_ARGUMENTS = {
        "()", "(int)", "(String, String, int, int, String[])",
        "()", "(a, b)", "(int, String, boolean)", "(int, int)"
    };
    private final String[] TEST_METHOD_NAMES = {
        "JavaSample",
        "greet",
        "isRunning",
        "count",
        "Opetators",
        "Person",
        "sum"
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
            if(!expected.containsAll(receive)) {
                throw new Exception(
                    "\tExpected: " + expected.toString() + "\n\tReceive: " + receive.toString()
                );
            }
            System.out.println("[Info] Successfully execution of getReturnTypeTest");
        } catch(Exception e) {
            System.err.println("\n[Error] While trying to execute getReturnTypeTest:\n" + e.getLocalizedMessage() + "\n");
        }
    }
    public void getArgumentsTest() {
        try {
            List<String> receive = methods.getArguments();
            List<String> expected = Arrays.asList(TEST_ARGUMENTS);
            if(!expected.containsAll(receive)) {
                throw new Exception(
                    "\tExpected: " + expected.toString() + "\n\tReceive: " + receive.toString()
                );
            }
            System.out.println("[Info] successfully execution of getArgumentsTest");
        } catch (Exception e) {
            System.err.println("\n[Error] While trying to execute getArgumentsTest:\n" + e.getLocalizedMessage() + "\n");
        }
    }
    public void getMethodNameTest() {
        try {
            List<String> receive = methods.getMethods();
            List<String> expected = Arrays.asList(TEST_METHOD_NAMES);
            if(!expected.containsAll(receive)) {
                throw new Exception(
                    "\tExpected: " + expected.toString() + "\n\tReceive: " + receive.toString()
                );
            }
            System.out.println("[Info] Successfully execution of getMethodNameTest");
        } catch (Exception e) {
            System.err.println("\n[Error] While trying to execute getMethodNameTest:\n" + e.getLocalizedMessage() + "\n");
        }
    }
    
}
