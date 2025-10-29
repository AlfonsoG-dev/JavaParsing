package Test;
import java.util.Arrays;
import java.util.List;

import Application.Parser.FileParser;
public class FileParserTest {
    private FileParser parser;
    private final String[] TEST_DECLARATIONS = {
        "public JavaSample()",
        "public final static void greet(int age)",
        "public boolean isRunning(String b, String a,int c, int m,String[] as)",
        "int count()",
        "Opetators o = (a, b) -> a + b;",
        "record Person(int age, String name, boolean married)",
        "public int sum(int a, int b);"
    };

    public FileParserTest() {
        parser = new FileParser("./docs/JavaSample.java");
    }

    public void getDeclarationsTest() {
        try {
            List<String> receive = parser.getDeclarations();
            List<String> expected = Arrays.asList(TEST_DECLARATIONS);
            if(!expected.containsAll(receive)) {
                throw new Exception(
                    "\tExpected: " + expected.toString() + "\n\tReceive: " + receive.toString()
                );
            }
            System.out.println("[Info] No error where produce during test execution of getDeclarationsTest");
        } catch (Exception e) {
            System.err.println("\n[Error] While trying to execute getDeclarationsTest: \n" + e.getLocalizedMessage() + "\n");
        }
    }
    public static void main(String[] args) {
        FileParserTest p = new FileParserTest();
        p.getDeclarationsTest();
    }
    
}
