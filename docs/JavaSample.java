public class JavaSample {
    private final static int age = 18;
    public JavaSample()
    {
    }
    public final static void greet(int age) {
        if(JavaSample.age <= age) {
            System.out.println("Hellow Mother");
        }
    }
    public boolean isRunning(String b, String a,
     int c, int m,
      String[] as) 
    {
        return false;
    }
    int count() {
        Opetators o = (a, b) -> a +b ;
        return 0;
    }
}
record Person(int age, String name, boolean married) {
}

interface Opetators {
    public int sum(int a, int b);
}