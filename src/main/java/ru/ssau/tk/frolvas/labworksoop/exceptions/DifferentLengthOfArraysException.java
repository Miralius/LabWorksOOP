package ru.ssau.tk.frolvas.labworksoop.exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {
    private static final long serialVersionUID = -8862385641924369556L;

    public DifferentLengthOfArraysException() {

    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
