package framework.models;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum BrowserUnderTest {
    CHROME, FIREFOX, IE;

    public static BrowserUnderTest fromString(String value) {
        return Arrays.stream(BrowserUnderTest.values())
                .filter(item -> item.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No " + BrowserUnderTest.class.getName() + " found for " + value));
    }
}
