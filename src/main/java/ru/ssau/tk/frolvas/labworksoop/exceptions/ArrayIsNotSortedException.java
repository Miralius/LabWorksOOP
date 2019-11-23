package ru.ssau.tk.frolvas.labworksoop.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    private static final long serialVersionUID = -4566389467303186613L;

    public ArrayIsNotSortedException() {

    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
