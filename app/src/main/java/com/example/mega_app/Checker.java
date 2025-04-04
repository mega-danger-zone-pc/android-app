package com.example.mega_app;

public class Checker {
    // Проверка на заполненность полей
    public static boolean isAllFieldsFilled(String fullName, String email, String mobileNumber,
                                            String dateOfBirth, String password, String confirmPassword) {
        return !fullName.isEmpty() && !email.isEmpty() && !mobileNumber.isEmpty() &&
                !dateOfBirth.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty();
    }
    // Проверка на валидность полного имени
    public static boolean isValidFullName(String fullName) {
        String[] nameParts = fullName.split(" ");
        return nameParts.length >= 2;
    }
    // Проверка на валидность Email
    public static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    // Проверка на валидность мобильного номера
    public static boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.matches("^\\+?\\d{10,15}$");
    }
    // Проверка на валидность даты рождения
    public static boolean isValidDateOfBirth(String dateOfBirth) {
        return dateOfBirth.matches("^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(\\d{4})$");
    }
    // Проверка на совпадение паролей
    public static boolean doPasswordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
    // Проверка на валидность пароля
    public static boolean isValidPassword(String password) {
        // Минимум 8 символов, максимум 16, только английские буквы, цифры и специальные символы (!@#$%^&*).
        return password.matches("^[a-zA-Z0-9!@#$%^&*]{8,16}$");
    }
}