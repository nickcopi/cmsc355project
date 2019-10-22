package one.group.oneapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void onCreate() {
        boolean exist = true;
        assertEquals(exist, MainActivity.doesThisExist);
    }
}