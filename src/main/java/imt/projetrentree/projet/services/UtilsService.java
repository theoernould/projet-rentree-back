package imt.projetrentree.projet.services;

import imt.projetrentree.projet.exceptions.CustomException;
import jakarta.ws.rs.core.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UtilsService {

    public static <E extends Enum<E>> E convertToEnum(Class<E> enumClass, String value, String errorMessage) {
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomException(Response.Status.BAD_REQUEST, errorMessage);
        }
    }

    public static Double convertToDouble(String value, String errorMessage) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new CustomException(Response.Status.BAD_REQUEST, errorMessage);
        }
    }

    public static <T extends Enum<T>> Map<T, String> getMapFromEnum(Class<T> enumClass) {
        Map<T, String> map = new HashMap<>();
        for (T enumValue : enumClass.getEnumConstants()) {
            try {
                Method method = enumValue.getClass().getMethod("getLabel");
                String label = (String) method.invoke(enumValue);
                map.put(enumValue, label);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static String getEnumKeysString(Class<? extends Enum<?>> enumClass) {
        return Arrays.toString(enumClass.getEnumConstants());
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
