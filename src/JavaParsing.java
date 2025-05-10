import Application.Models.*;
import Application.Utils.FileUtils;


class JavaParsing {
    public static void main(String[] args) {
        Methods m = new Methods();
        String filePath = "./src/Application/Models/Methods.java";
        // String filePath = "./docs/JavaSample.java";
        m.initializeLists(filePath);

        Arguments a = new Arguments(m);
        ReturnType r = new ReturnType(m);

        FileUtils u = new FileUtils();

        m.getMethodContent("getMethodLineNumber");
    }
}
