package Test;

public class TestClass {
    private String name;

    public static void main(String[] args) throws Exception {
        Object o = TestClass.class.getConstructor(String.class).newInstance("Jack");
        System.out.println(o);


    }

    public TestClass() {

    }

    public TestClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "My name is " + name;
    }



}
