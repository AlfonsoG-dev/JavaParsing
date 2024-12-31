import Application.Models.Methods;
class JavaParsing {
    public static void main(String[] args) {
        Methods m = new Methods("./");
        // to test - get methods
        if(1 >= 2) {
            System.out.println("");
        }
        m.getMethodsFromFile("./src/JavaParsing.java")
        .stream()
        .forEach(System.out::println);
    }
    public static void greet() {
        System.out.println("Hellow mother");
    }
    static boolean isRunning() {
        return false;
    }
}