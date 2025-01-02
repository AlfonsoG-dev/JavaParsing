import Application.Models.ReturnType;
class JavaParsing {
    public static void main(String[] args) {
        ReturnType m = new ReturnType("./docs/JavaSample.java");
        // to test - get methods
        m.getReturnTypeFromLine()
        .stream()
        .forEach(System.out::println);
    }
}