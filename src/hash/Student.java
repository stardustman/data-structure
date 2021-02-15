package hash;

public class Student {
    private int grade;
    private int cls;
    private String firstName;
    private String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int B = 31;
        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        // 是不是本身
        if(this == obj){
            return true;
        }
        //
        if(obj == null){
            return false;
        }
        // 对象所属的类
        if(getClass() != obj.getClass()){
            return false;
        }

        Student s = (Student)obj;
        return  this.grade == s.grade &&
                this.cls == s.cls &&
                this.firstName.toLowerCase().equals(s.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(s.lastName.toLowerCase());
    }
}
