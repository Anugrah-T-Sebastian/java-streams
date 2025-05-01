import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionFile {
    //Create a custom arraylist which does not allow addition of duplicate elements
    public class CustomArrayList extends ArrayList {
        @Override
        public boolean add(Object e) {
            if (this.contains(e)) {
                return true;
            }
            return super.add(e);
        }
        
    }

    //Comparable & Comparator example
    public static class Student implements Comparable<Student> {
        int id;
        String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(CollectionFile.Student s) {
            if(this.id == s.id)
                return 0;
            else if(this.id > s.id)
                return 1;
            else
                return -1;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return super.toString();
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    //Comparable & Comparator example
    public static class IdComparator implements Comparator<Student> {

        @Override
        public int compare(CollectionFile.Student s1, CollectionFile.Student s2) {
           if (s1.getId() == s2.getId()) 
                return 0;
            else if (s1.getId() > s2.getId())
                return 1;
            return -1;
        }
    }

    public static void main(String[] args) {

        //1. Create a custom arraylist which does not allow addition of duplicate elements
        List<Student> students = new ArrayList<>();
        students.add(new Student(0,"ABC"));
        students.add(new Student(1,"PQR"));
        students.add(new Student(2,"XYZ"));
        
        //2. Comparable & Comparator example
        Collections.sort(students);
        Collections.sort(students, new IdComparator());
        System.out.println("Sorted student: " + students.toString());

        
    }
}
