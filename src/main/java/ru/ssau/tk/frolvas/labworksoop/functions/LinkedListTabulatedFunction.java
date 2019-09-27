package ru.ssau.tk.frolvas.labworksoop.functions;

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

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        this.count = xValues.length;
        for (int i = 0; i < count; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    LinkedListTabulatedFunction(MathFunction sourse, double xFrom, double xTo, int count) {
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
            for (int i = 0; i <= count; i++) {
                this.addNode(auxiliaryVariable, sourse.apply(auxiliaryVariable));
                auxiliaryVariable += step;
            }
        } else {
            for (int i = 0; i < count; i++) {
                this.addNode(xFrom, sourse.apply(xFrom));
            }
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
        return getNode(index).x;
    }

    public double getY(int index) {
        return getNode(index).y;
    }

    public void setY(int index, double valueY) {
        valueY = getNode(index).y;
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

    public int floorIndexOfX(double x) {
        Node buff;
        if (x < head.x) {
            return 0;
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

    @Override
    protected double extrapolateLeft(double x) {
        if (head.x == last.x) {
            return head.y;
        }
        return head.y + (head.next.y - head.y) / (head.next.x - head.x) * (x - head.x);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (head.x == last.x) {
            return head.y;
        }
        return last.prev.y + (last.y - last.prev.y) * (x - last.prev.x) / (last.x - last.prev.x);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == last.x) {
            return head.y;
        }
        Node left = getNode(floorIndex);
        Node right = left.next;
        return left.y + (right.y - left.y) / (right.x - left.x) * (x - left.x);
    }
}





