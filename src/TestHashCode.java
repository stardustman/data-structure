import hash.Student;

public class TestHashCode {
    public static void main(String ...args){
        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        int c = 0;
        System.out.println(((Integer)c).hashCode());

        double d = 3.1415926;
        System.out.println(((Double)d).hashCode());

        Student student = new Student(3, 2, "all","young");
        System.out.println(student.hashCode());

        Student student2 = new Student(3, 2, "All","young");
        System.out.println(student2.hashCode());
        System.out.println(student2.equals(student));

    }
}
