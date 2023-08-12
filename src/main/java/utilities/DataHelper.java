package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private Locale local = new Locale("en");
    private Faker faker = new Faker(local);

    public static DataHelper getDataHelper() {
        return new DataHelper();

    }

    public Integer getRandomNumber() {
        return (int) faker.number().randomNumber();
    }
}
//
//
//    public String getCityName() {
//        return faker.address().cityName();
//    }
//
//    public String getState() {
//        return faker.address().state();
//    }
//
//    public String getPostalCode() {
//        return faker.address().zipCode();
//    }
//
//
//    public String getAddress() {
//        return faker.address().streetAddressNumber();
//    }
//
//    public String getRandomString() {
//        return faker.toString();
//    }
//
//    public String getRandomCardNumber() {
//        return faker.business().creditCardNumber();
//    }
//
//    public Integer getRandomNumber() {
//        return faker.number().numberBetween(10, 1000000000);
//    }


