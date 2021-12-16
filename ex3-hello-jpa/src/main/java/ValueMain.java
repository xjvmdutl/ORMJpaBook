import domain.Address;

public class ValueMain {
    public static void main(String[] args) {
        /*
        int a = 10;
        int b = a;
        b = 20;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
         */
        /*
        Integer a = new Integer(10);
        Integer b= a;//참조값이 복사된다. // 같은 참조값을 참조한다.

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        */
        int a = 10;
        int b = 10;
        System.out.println("a == b : "+ (a==b) );//true

        Address address1 = new Address("city","street","10000");
        Address address2 = new Address("city","street","10000");
        System.out.println("address1 == address2 : " + (address1 == address2));//false
        System.out.println("address1 equals address2 : " + (address1.equals(address2)));//true
    }
}
