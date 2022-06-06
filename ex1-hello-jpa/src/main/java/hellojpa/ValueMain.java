package hellojpa;

public class ValueMain {

    public static void main(String[] args) {

        // 자바 기본(원시) 타입
        int a = 10;
        int b = 10;

        // 객체 타입
        Address address1 = new Address("city", "street", "zipcode");
        Address address2 = new Address("city", "street", "zipcode");

        System.out.println("a == b = " + (a == b));
        System.out.println("address1 == address2 = " + (address1 == address2));
        System.out.println("address1 equals address2 = " + (address1.equals(address2)));


    }
}
