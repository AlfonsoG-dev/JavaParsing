import Application.Parser.FileParser;
class JavaParsing {
    public static void main(String[] args) {
        String f = "./docs/JavaSample.java";
        FileParser p = new FileParser(f);
        p.parse();
    }
}
