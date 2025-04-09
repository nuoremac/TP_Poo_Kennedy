package util;

public class SerialNumberValidator {
    // Basic IMEI validation (15 digits)
    public static boolean isValidImei(String imei) {
        if (imei == null || imei.length() != 15 || !imei.matches("\\d+")) {
            return false;
        }

        // Luhn algorithm check
        int sum = 0;
        for (int i = 0; i < imei.length(); i++) {
            int digit = Character.getNumericValue(imei.charAt(i));
            if (i % 2 != 0) { // Double every other digit
                digit *= 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        return sum % 10 == 0;
    }

    // Generic serial number validation (at least 6 alphanumeric chars)
    public static boolean isValidSerialNumber(String serial) {
        return serial != null && serial.length() >= 6 && serial.matches("^[a-zA-Z0-9]+$");
    }
}