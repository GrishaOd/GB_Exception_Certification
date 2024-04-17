import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");

        String input = scanner.nextLine().trim(); // trim убирает пробелы в начале и конце строки
        String[] data = input.split(" ");

        try {
            if (data.length != 6) {
                throw new IllegalArgumentException("Вы ввели недостаточно или слишком много данных");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String dob = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char sex = data[5].charAt(0);

            if (!dob.matches("\\d{2}.\\d{2}.\\d{4}")) {
                throw new IllegalArgumentException("Неверный формат даты рождения");
            }

            if (sex != 'f' && sex != 'm') {
                throw new IllegalArgumentException("Пол должен быть 'f' или 'm'");
            }

            String filename = lastName + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(lastName + " " + firstName + " " + middleName + " " + dob + " " + phoneNumber + " " + sex);
            writer.newLine();
            writer.close();

            System.out.println("Данные успешно записаны в файл " + filename);

        } catch (IllegalArgumentException | IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
