package dev.misha.school.bootstrap;

import dev.misha.school.service.StudentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchoolBootstrap {
    StudentService studentService;
    Scanner scanner =new Scanner(System.in);

    @Autowired
    public SchoolBootstrap(StudentService studentService){
        this.studentService = studentService;
    }

    public void boostrap(){
        while (true){
            System.out.println("Меню:");
            System.out.println("1. Додати учня");
            System.out.println("2. Видалити учня");
            System.out.println("3. Додати оцінки учню");
            System.out.println("4. Показати список учнів та їх оцінок");
            System.out.println("5. Обчислити середній бал учня");
            System.out.println("6. Вийти з програми");
            System.out.print("Оберіть опцію: ");

            int optional;
            try {
                optional = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Введіть числове значення.");
                continue;
            }

            switch (optional) {
                case 1:
                    System.out.print("Введіть ім'я учня: ");
                    String studentName = scanner.nextLine();
                    studentService.addStudent(studentName);
                    break;
                case 2:
                    System.out.print("Введіть ім'я учня, якого потрібно видалити: ");
                    String studentToRemove = scanner.nextLine();
                    studentService.removeStudent(studentToRemove);
                    break;
                case 3:
                    System.out.print("Введіть ім'я учня: ");
                    String studentToGrade = scanner.nextLine();
                    System.out.print("Введіть оцінки (розділені комами або пробілами): ");
                    String gradesInput = scanner.nextLine();
                    studentService.addGrades(studentToGrade, gradesInput);
                    break;
                case 4:
                    studentService.getAll();
                    break;
                case 5:
                    System.out.print("Введіть ім'я учня: ");
                    String studentToAverage = scanner.nextLine();
                    studentService.calculateAverageGrade(studentToAverage);
                    break;
                case 6:
                    System.out.println("Програма завершує роботу.");
                    System.exit(0);
                default:
                    System.out.println("Невірна опція. Спробуйте ще раз.");
            }
        }
    }
}
