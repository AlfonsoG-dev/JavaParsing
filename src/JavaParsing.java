import Application.Models.ReturnType;
class JavaParsing {
    public static void main(String[] args) {
        ReturnType m = new ReturnType();
        // to test - get methods
        m.getReturnTypeFromLine("./docs/JavaSample.java")
        .stream()
        .forEach(System.out::println);
    }
}