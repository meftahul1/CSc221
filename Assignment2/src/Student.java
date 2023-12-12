public class Student {
    private String name;
    private int roll_num;
    private int[] marks;

    public Student(String name, int roll_num, int[] marks) {
        this.name = name;
        this.roll_num = roll_num;
        this.marks = marks;
    }
    public double averageMarks() { // returns average = total / length
        int marks_len = marks.length;
        int total = 0;
        double average;
        if (marks_len == 0) {
            return 0.0;
        }

        for (int mark:marks) {
            total += mark;
        }
        average = (double) total / marks_len;
        return average;
    }

    public void studentinfo() {
        System.out.println("Student info: ");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + roll_num);
        System.out.println("Marks: ");
        for (int i = 0; i < marks.length; i++) {
            System.out.println("Subject: " + (i + 1) + ": " + marks[i]);
        }
        System.out.println("Average: " + averageMarks());


    }

}
