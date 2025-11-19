public class hello {
    public static void main(String[] args){
        char character;
        int integer;
        int integer2;
        boolean bool;
        double dub;
        String str;
        Object o;

        bool= true;
        str = "hello my name is sam";

        /*
        System.out.println(integer);
        integer = 7;
        System.out.println(integer);
        integer++; // same as +=1
        System.out.println(integer);
        integer-=7;
        System.out.println(integer);
        integer2 = integer -5;
        */

        integer = 5;
        dub = 4.5;

        if (dub>integer){
            System.out.println("option 1");
        }
        else if (integer==dub){
            System.out.println("option 2");
        }
        else{
            System.out.println("option 3");
        }


    }
}
