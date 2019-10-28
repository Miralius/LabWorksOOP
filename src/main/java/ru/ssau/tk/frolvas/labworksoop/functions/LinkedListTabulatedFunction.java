package ru.ssau.tk.frolvas.labworksoop.functions;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    private int count;
    private Node head;
    private Node last;

    private static class Node {
        Node next;
        Node prev;
        double x;
        double y;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Count of points less then 2");
        }
        this.count = xValues.length;
        for (int i = 0; i < count; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction sourse, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Count of points less then 2");
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
        if (xFrom != xTo) {
            for (int i = 0; i < count; i++) {
                this.addNode(auxiliaryVariable, sourse.apply(auxiliaryVariable));
                auxiliaryVariable += step;
            }
        } else {
            for (int i = 0; i < count; i++) {
                this.addNode(xFrom, sourse.apply(xFrom));
            }
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
            last = newNode;
        } else {
            newNode.next = head;
            newNode.prev = last;
            head.prev = newNode;
            last.next = newNode;
            newNode.x = x;
            newNode.y = y;
            last = newNode;

        }
    }

    public int getCount() {
        return count;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return last.x;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("ivalid index");
        }

        Node first;
        if (index > (count / 2)) {
            first = last;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    return first;
                } else {
                    first = first.prev;
                }
            }

        } else {
            first = head;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    return first;
                } else {
                    first = first.next;
                }
            }
        }
        return null;

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

    protected int floorIndexOfX(double x) {
        Node buff;
        if (x < head.x) {
            throw new IllegalArgumentException("X less then head");
        }
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

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, last.prev.x, last.x, last.prev.y, last.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    private void checkIncludeInBounds(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Ivalid index");
        }
    }

    public Iterator<Point> iterator() {
        Iterator<Point> iterator = new Iterator<>() {
            private Node node = head;

            public boolean hasNext() {
                return node != null;
            }

            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Point point = new Point(node.x, node.y);
                    if (node != head.prev) {
                        node = node.next;
                    } else {
                        node = null;
                    }
                    return point;
                }
            }
        };
        return iterator;
    }

}





