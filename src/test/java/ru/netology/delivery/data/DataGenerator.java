package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    private final static Faker faker = new Faker(new Locale("ru"));
    private static final List<String> cities = List.of(
            "Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик", "Элиста",
            "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск",
            "Якутск", "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары",
            "Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь",
            "Владивосток", "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань",
            "Белгород", "Брянск", "Владимир", "Волгоград", "Вологда", "Воронеж", "Иваново", "Орёл",
            "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Курган", "Курск",
            "Санкт-Петербург", "Липецк", "Магадан", "Москва", "Мурманск", "Нижний Новгород",
            "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Пенза", "Псков", "Салехард",
            "Ростов-на-Дону", "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург",
            "Смоленск", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск",
            "Ярославль", "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь"
    );

    public static String generateCity() {
        return cities.get(new Random().nextInt(cities.size()));
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateName() {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser() {
            return new UserInfo(generateCity(), generateName(), generatePhone());
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
