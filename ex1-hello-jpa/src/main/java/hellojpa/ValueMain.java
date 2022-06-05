package hellojpa;

public class ValueMain {

    public static void main(String[] args) {

        Integer a = new Integer(10);
        Integer b = a; // 레퍼런스가 넘어감.

        a = 20;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
