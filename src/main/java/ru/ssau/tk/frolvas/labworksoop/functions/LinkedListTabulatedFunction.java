package ru.ssau.tk.frolvas.labworksoop.functions;

import ru.ssau.tk.frolvas.labworksoop.exceptions.*;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    private int count;
    private Node head;

    private static class Node {
        Node next;
        Node prev;
        double x;
        double y;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Size of array is less than minimum (2)");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        this.count = xValues.length;
        for (int i = 0; i < count; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Number of values is less than minimum (2)");
        }
        this.count = count;
        double auxiliaryVariableForChange;
        if (xFrom > xTo) {
            auxiliaryVariableForChange = xFrom;
            xFrom = xTo;
            xTo = auxiliaryVariableForChange;
        }
        double step = (xTo - xFrom) / (count - 1);
        double auxiliaryVariable = xFrom;
        for (int i = 0; i < count; i++) {
            this.addNode(auxiliaryVariable, source.apply(auxiliaryVariable));
            auxiliaryVariable += step;
        }
    }

    void addNode(double x, double y) {
        Node newNode = new Node();
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
            newNode.x = x;
            newNode.y = y;
        } else {
            newNode.next = head;
            newNode.prev = head.prev;
            newNode.x = x;
            newNode.y = y;
            head.prev.next = newNode;
            head.prev = newNode;
        }
    }

    public int getCount() {
        return count;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }

    private Node getNode(int index) {
        checkIncludeInBounds(index);
        Node first;
        if (index > (count / 2)) {
            first = head.prev;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    break;
                } else {
                    first = first.prev;
                }
            }

        } else {
            first = head;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    break;
                } else {
                    first = first.next;
                }
            }
        }
        return first;
    }

    public double getX(int index) {
        checkIncludeInBounds(index);
        return getNode(index).x;
    }

    public double getY(int index) {
        checkIncludeInBounds(index);
        return getNode(index).y;
    }

    public void setY(int index, double valueY) {
        checkIncludeInBounds(index);
        getNode(index).y = valueY;
    }

    public int indexOfX(double x) {
        Node first;
        first = head;
        for (int i = 0; i < count; i++) {
            if (first.x == x) {
                return i;
            } else {
                first = first.next;
            }
        }
        return -1;
    }

    public int indexOfY(double y) {
        Node buff;
        buff = head;
        for (int i = 0; i < count; i++) {
            if (buff.y == y) {
                return i;
            } else {
                buff = buff.next;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X is less than minimal value in linked list");
        }
        Node buff;
        buff = head;
        for (int i = 0; i < count; i++) {
            if (buff.x < x) {
                buff = buff.next;
            } else {
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        Node right = left.next;
        if (x < left.x || right.x < x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    private void checkIncludeInBounds(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of array's bounds");
        }
    }
}