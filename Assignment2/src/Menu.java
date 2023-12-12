import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.SQLOutput;


public class Menu {
    private static final String FILENAME = "StudentsRecords.txt"; // this file will include all records
    private static final Scanner scanner = new Scanner(System.in); // to get user input

    public static void main(String[] args) {

        createFile(); // creates a file with the name of FILENAME

        boolean isExit = false;

        while(!isExit) {

            System.out.println("\nMenu: ");
            System.out.println("Press 1 to add a new student record");
            System.out.println("Press 2 to view all student records");
            System.out.println("Press 3 to search for a student record by roll number");
            System.out.println("Press 4 to edit a student record by roll number");
            System.out.println("Press 5 to delete a student record by roll number");
            System.out.println("Press 6 to exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                addNewStudentRecord();
                break;
            }
            else if (option == 2) {
                viewStudentRecord();
                break;
            }
            else if (option == 3) {
                searchStudentbyRoll();
                break;
            }
            else if (option == 4) {
                editRecord();
                break;
            }
            else if (option == 5) {
                deleteRecord();
                break;
            }
            else if (option == 6) {
                System.out.println("Exiting Program");
                isExit = true;
            }
            else {
                System.out.println("Invalid Choice. Choose an option from 1-6.");
//                displayMenu();
            }


        }


    }

    private static void deleteRecord() {
        System.out.print("Enter roll number to delete: ");
        int searchRollNumber = isRollNumber();

        List<String> records = new ArrayList<>();
        boolean found = false;

        try (Scanner fileScanner = new Scanner(new File(FILENAME))) {
            while (fileScanner.hasNextLine()) {
                String currentRecord = fileScanner.nextLine();
                String[] record = currentRecord.split(",");
                if (Integer.parseInt(record[1]) == searchRollNumber) {
                    found = true;
                    System.out.println("Student Record Found and Deleted:");
                    System.out.println("Name: " + record[0] + ", Roll Number: " + record[1] + ", Marks: " + Arrays.toString(Arrays.copyOfRange(record, 2, record.length)));
                } else {
                    records.add(currentRecord);
                }
            }
            if (!found) {
                System.out.println("Student record not found for the given roll number.");
            } else {
                updatedFile(records);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void editRecord() {
        // requires roll to edit a student's info, needs to check whether roll number is acceptable or not by using isRollNumber function
        int search_roll = isRollNumber();

        List<String> records = new ArrayList<>();
        boolean found = false;

        try (Scanner fileScanner = new Scanner(new File(FILENAME))) {
            while(fileScanner.hasNextLine()) {
                String current = fileScanner.nextLine();
                String[] record = current.split(",");

                if(Integer.parseInt(record[1]) == search_roll) {
                    found = true;
                    System.out.println("Student record found and it includes");
                    System.out.println("Name: " + record[0] + ", Roll Number: " + record[1] + ", Marks: " + Arrays.toString(Arrays.copyOfRange(record, 2, record.length)));

                    System.out.println("Enter new details that you would like to edit");
                    System.out.println("Name: ");
                    String name = scanner.nextLine();

                    System.out.println("Roll: ");
                    int roll = isRollNumber();

                    System.out.println("Marks (comma-separated): e.g. 75, 90, 82, ...");
                    String marks_input = scanner.nextLine();
                    int[] marks = isMarks(marks_input);

                    String updated = name + "," + roll + "," + String.join(",", Arrays.stream(marks).mapToObj(String::valueOf).toArray(String[]::new));
                    records.add(updated);


                } else {
                    records.add(current);
                }
            }
        } catch (IOException e) {
            System.out.println("Generated error: " + e.getMessage());
        }

    }


    private static void searchStudentbyRoll() {
        System.out.print("Enter roll number to search: ");
        int searchRollNumber = isRollNumber();

        try (Scanner fileScanner = new Scanner(new File(FILENAME))) {
            boolean found = false;
            while(fileScanner.hasNextLine()) {
                String[] recordStudent = fileScanner.nextLine().split(",");
                if (Integer.parseInt(recordStudent[1]) == searchRollNumber) {
                    System.out.println("Student Record Found:");
                    System.out.println("Name: " + recordStudent[0] + ", Roll Number: " + recordStudent[1] + ", Marks: " + Arrays.toString(Arrays.copyOfRange(recordStudent, 2, recordStudent.length)));
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Student record not found for the given roll number.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void viewStudentRecord() {
        try (Scanner fileScanner = new Scanner(new File(FILENAME))) {
            while(fileScanner.hasNextLine()) {
                String[] student_record = fileScanner.nextLine().split(",");
                System.out.println("Name: " + student_record[0] + ", Roll Number: " + student_record[1] + ", Marks: " + Arrays.toString(Arrays.copyOfRange(student_record, 2, student_record.length)));

            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    private static void addNewStudentRecord() {
        try (FileWriter fileWriter = new FileWriter(FILENAME, true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student roll number: ");
            int rollNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter marks (comma-separated and NO SPACE in between commas): example: 92,90,79, . . . .");
            String marks_input = scanner.nextLine();
            int[] marks = isMarks(marks_input); // isMarks checks to see whether the input is valid or not

            String record = name + "," + rollNumber + "," + String.join(",", Arrays.stream(marks).mapToObj(String::valueOf).toArray(String[]::new));
            writer.write(record);
            writer.newLine();
            System.out.println("You have successfully added a student record");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }


    }







    private static int[] isMarks(String marks_input) {
        while (true) {
            try {
                String[] marksInput = scanner.nextLine().split(",");
                int[] marks = new int[marksInput.length];
                for (int i = 0; i < marksInput.length; i++) {
                    marks[i] = Integer.parseInt(marksInput[i].trim());
                    if (marks[i] < 0 || marks[i] > 100) {
                        throw new IllegalArgumentException();
                    }
                }
                return marks;
            } catch (IllegalArgumentException e) {
                System.out.print("Invalid input. Enter valid marks (comma-separated and NO SPACES, example: 79,92,90,73): ");
            }
        }
    }


    private static int isRollNumber() {
        int rollNumber;
        while (true) {
            try {
                rollNumber = Integer.parseInt(scanner.nextLine());
                if (rollNumber > 0) {
                    break;
                } else {
                    System.out.print("Roll number should be a positive integer. Enter again: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Roll number should be an integer. Enter again: ");
            }
        }
        return rollNumber;
    }

    public static void createFile() {
        File file = new File(FILENAME);

        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File 'StudentRecords.txt' created successfully.");
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    private static void updatedFile(List<String> records) {
        try (FileWriter fileWriter = new FileWriter(FILENAME);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("Student record deleted successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    // this is where main ends
}