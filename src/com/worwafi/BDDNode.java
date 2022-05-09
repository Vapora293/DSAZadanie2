package com.worwafi;

public class BDDNode {
    private BDDNode left;
    private BDDNode right;
    private int value;
    private char aChar;

    public BDDNode(int value) {
        this.value = value;
        aChar = ' ';
        left = null;
        right = null;
    }
    public BDDNode(char aChar) {
        this.aChar = aChar;
        left = null;
        right = null;
    }

    public BDDNode getLeft() {
        return left;
    }

    public BDDNode getRight() {
        return right;
    }

    public void setLeft(BDDNode left) {
        this.left = left;
    }

    public void setRight(BDDNode right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public char getaChar() {
        return aChar;
    }
}
