import Application.Models.Arguments;
class JavaParsing {
    public static void main(String[] args) {
        Arguments m = new Arguments();
        m.initializeLists("./docs/JavaSample.java");
        // to test - get methods
        m.getArgumentsFromLine()
        .stream()
        .forEach(System.out::println);
    }
}