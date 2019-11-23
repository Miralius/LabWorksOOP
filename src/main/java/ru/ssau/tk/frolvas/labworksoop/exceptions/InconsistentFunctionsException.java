package ru.ssau.tk.frolvas.labworksoop.exceptions;

public class InconsistentFunctionsException extends RuntimeException {
    private static final long serialVersionUID = 3173407982547557358L;

    public InconsistentFunctionsException() {

    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
