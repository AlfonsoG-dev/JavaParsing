import Application.Utils.FileUtils;
class JavaParsing {
    public static void main(String[] args) {
        FileUtils fu = new FileUtils("./src");
        fu.fileLines("./src/Application/Utils/FileUtils.java")
        .stream()
        .forEach(System.out::println);
    }
}
