package framework.models;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum OSInTest {
    WINDOWS, LINUX, MAC;

    public static OSInTest fromString(String value) {
        return Arrays.stream(OSInTest.values())
                .filter(item -> item.name().equalsIgnoreCase(value)
                        || value.toLowerCase().startsWith(item.name().toLowerCase()))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No " + OSInTest.class.getName() + " found for " + value));
    }
}