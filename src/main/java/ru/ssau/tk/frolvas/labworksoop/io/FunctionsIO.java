package ru.ssau.tk.frolvas.labworksoop.io;

import ru.ssau.tk.frolvas.labworksoop.functions.Point;
import ru.ssau.tk.frolvas.labworksoop.functions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point newPoint : function) {
            out.writeDouble(newPoint.x);
            out.writeDouble(newPoint.y);
        }
        out.flush();
    }

}
