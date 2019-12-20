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
        JOptionPane.showMessageDialog(parent, "Ошибка!", head, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат числа";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Массив точек неотсортирован";
        }
        if (e instanceof IOException) {
            return "Файл не найден или повреждён";
        }
        if (e instanceof InconsistentFunctionsException) {
            return "Длина массивов неодинакова";
        }
        return "Неизвестная ошибка";
    }

}