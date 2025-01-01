import Application.Models.Methods;
class JavaParsing {
    public static void main(String[] args) {
        Methods m = new Methods("./");
        // to test - get methods
        m.getMethodsFromFile("./docs/JavaSample.java")
        .stream()
        .forEach(System.out::println);
    }
}