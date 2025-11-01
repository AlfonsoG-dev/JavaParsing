import Cases.*;

class TestLauncher {
    private final static MethodTest mt = new MethodTest();
    private final static FileParserTest fpt = new FileParserTest();

    // TODO: invoke methods that end in *test* prefix using *reflection* on generic types.

    public static void main(String[] args) {
        System.out.println("\n[Info] Executing MethodTest [TEST CASES]...");
        mt.getArgumentsTest();
        mt.getReturnTypeTest();
        mt.getMethodNameTest();
        System.out.println("\n[Info] Executing FileParserTest [TEST CASES]...");
        fpt.getDeclarationsTest();
    }
}
