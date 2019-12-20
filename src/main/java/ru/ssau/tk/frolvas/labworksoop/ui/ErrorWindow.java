package ru.ssau.tk.frolvas.labworksoop.ui;

import ru.ssau.tk.frolvas.labworksoop.exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ErrorWindow {

    ErrorWindow(Component parent, Exception e) {
        showErrorWindow(parent, e);
    }

    public void showErrorWindow(Component parent, Exception e) {
        String head = generateMessageForException(e);
        JOptionPane.showMessageDialog(parent, "Error!", head, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Wrong number format";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Array isn't sorted";
        }
        if (e instanceof IOException) {
            return "File is missed";
        }
        if (e instanceof InconsistentFunctionsException) {
            return "Values are different";
        }
        return "Unknown error";
    }

}