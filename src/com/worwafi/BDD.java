package com.worwafi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BDD {
    private BDDNode root;
    private final String entry;
    private final String orderOfNodes;
    private final BDDNode bright;
    private final BDDNode bfalse;
    private int sReductions;
    private int iReductions;
    private int numberOfNodes;
    private ArrayList<HashMap<String, BDDNode>> nodeHashMap;

    public BDD(String entry, String order) {
        this.entry = entry;
        this.orderOfNodes = order;
        nodeHashMap = new ArrayList<>(orderOfNodes.length() + 1);
        for (int i = 0; i < orderOfNodes.length() + 1; i++)
            nodeHashMap.add(i, new HashMap<>());
        sReductions = 0;
        iReductions = 0;
        bright = new BDDNode(1);
        bfalse = new BDDNode(0);
        root = new BDDNode(orderOfNodes.charAt(0));
        root = addRecursive(root, entry, orderOfNodes);
        numberOfNodes = 2;
        getNumberOfNodes();
        System.out.println("Total number of nodes is " + numberOfNodes);
        System.out.println("Total number without reductions could be " + (Math.pow(2, orderOfNodes.length()) + 2));
        System.out.println("Reduced by " + ((1 - (numberOfNodes / (Math.pow(2, orderOfNodes.length()) + 2))) * 100) + "%");
        System.out.println("There were " + iReductions + " I-Reductions");
        System.out.println("There were " + sReductions + " S-Reductions");
    }

    private void getNumberOfNodes() {
        for (HashMap<String, BDDNode> actual : nodeHashMap) {
            numberOfNodes += actual.size();
        }
    }

    public HashMap<String, Integer> testing() {
        HashMap<String, Integer> testingMap = new HashMap<>();
        for (int i = 0; i < Math.pow(2, orderOfNodes.length()); i++) {
            String number = generateBinaryNumber(i, orderOfNodes.length());
            int result;
            if((result = traverseTree(root, number, orderOfNodes, -1)) != -1)
                testingMap.put(number, result);
            else {
                System.out.println("error on combination " + number);
            }
        }
        return testingMap;
    }

    private String generateBinaryNumber(int number, int length) {
        String binary = Integer.toBinaryString(number);
        StringBuilder local = new StringBuilder();
        if (binary.length() != length) {
            for (int i = 0; i < length - binary.length(); i++) {
                local.append("0");
            }
        }
        local.append(binary);
        return local.toString();
    }

    public BDDNode getRoot() {
        return root;
    }

    //    private BDDNode traverseTree(BDDNode point, String actual) {
//        if (point.getValue() == 1)
//            return point;
//        if (actual.equals("")) {
//            if (point.getValue() == 1) {
//                return point;
//            } else {
//                System.out.println("problem here");
//                return null;
//            }
//        } else {
//            if (actual.charAt(0) == '!') {
//                if (point.getaChar() == actual.charAt(1))
//                    if (point.getLeft() != null)
//                        traverseTree(point.getLeft(), actual.substring(2));
//                else {
//                    if (point.getLeft() != null)
//                        traverseTree(point.getLeft(), actual);
//                    if (point.getRight() != null)
//                        traverseTree(point.getRight(), actual);
//                }
//
//            } else {
//                if (point.getaChar() == actual.charAt(0))
//                    if (point.getRight() != null)
//                        traverseTree(point.getRight(), actual.substring(1));
//                else {
//                    if (point.getLeft() != null)
//                        traverseTree(point.getLeft(), actual);
//                    if (point.getRight() != null)
//                        traverseTree(point.getRight(), actual);
//                }
//
//            }
//        }
//        return point;
//    }
    public int traverseTree(BDDNode point, String actual, String order, int result) {
        if (point.equals(bright)) {
            result = 1;
            return result;
        }
        if (point.equals(bfalse)) {
            result = 0;
            return result;
        }
        if (actual.equals("")) {
            if(!point.equals(bright) || !point.equals(bfalse)) {
                result = -1;
                return result;
            }
        } else {
            if (actual.charAt(0) == '0') {
                if (point.getaChar() == order.charAt(0))
                    try {
                        result = traverseTree(point.getLeft(), actual.substring(1), order.substring(1), result);
                    } catch (NullPointerException e) {
                    }
                    else {
                        result = traverseTree(point, actual.substring(1), order.substring(1), result);
                    }
            }
            else {
                if (point.getaChar() == order.charAt(0))
                    try {
                        result = traverseTree(point.getRight(), actual.substring(1), order.substring(1), result);
                    } catch (NullPointerException e) {
                    }
                else {
                    result = traverseTree(point, actual.substring(1), order.substring(1), result);
                }
            }
        }
        return result;
    }

    private BDDNode addRecursive(BDDNode root, String currentConfig, String orderOfNodes) {
        if (currentConfig.equals(".")) {
            root = bright;
            return root;
        } else if (currentConfig.equals("")) {
            root = bfalse;
            return root;
        }
        /**
         * I Reduction check
         */
        if (nodeHashMap.get(orderOfNodes.length()).get(currentConfig) != null) {
            return iReduction(root, currentConfig, orderOfNodes);
        } else
            nodeHashMap.get(orderOfNodes.length()).put(currentConfig, root);

        if (orderOfNodes.length() > 1) {
            root.setLeft(addRecursive(new BDDNode(orderOfNodes.charAt(1)), division(currentConfig, orderOfNodes.charAt(0), false), orderOfNodes.substring(1)));
            root.setRight(addRecursive(new BDDNode(orderOfNodes.charAt(1)), division(currentConfig, orderOfNodes.charAt(0), true), orderOfNodes.substring(1)));
            root = sReduction(root, orderOfNodes);
        } else {
            root.setLeft(addRecursive(new BDDNode(' '), division(currentConfig, orderOfNodes.charAt(0), false), ""));
            root.setRight(addRecursive(new BDDNode(' '), division(currentConfig, orderOfNodes.charAt(0), true), ""));
            root = sReduction(root, orderOfNodes);
        }
        return root;
    }

    private BDDNode iReduction(BDDNode root, String currentConfig, String orderOfNodes) {
        BDDNode actual = nodeHashMap.get(orderOfNodes.length()).get(currentConfig);
        if (orderOfNodes.length() == this.orderOfNodes.length())
            return root;
        for (Map.Entry<String, BDDNode> pair : nodeHashMap.get(orderOfNodes.length() + 1).entrySet()) {
            if (pair.getValue().getRight() != null) {
                if (pair.getValue().getRight().equals(actual)) {
                    root = actual;
                    break;
                }
            }
            if (pair.getValue().getLeft() != null) {
                if (pair.getValue().getLeft().equals(actual)) {
                    root = actual;
                    break;
                }
            }
        }
        iReductions++;
        return root;
    }

    private BDDNode sReduction(BDDNode root, String orderOfNodes) {
        if (equalChildren(root, orderOfNodes.length())) {
            nodeHashMap.get(orderOfNodes.length()).remove(getNodeKey(root, orderOfNodes.length()));
            root = root.getLeft();
            root = moveAllUp(root, orderOfNodes.length() - 1);
            sReductions++;
        }
        return root;
    }

    private String getNodeKey(BDDNode root, int length) {
        for (Map.Entry<String, BDDNode> pair : nodeHashMap.get(length).entrySet()) {
            if (pair.getValue().equals(root)) {
                return pair.getKey();
            }
        }
        return "";
    }

    private BDDNode moveAllUp(BDDNode left, int length) {
        for (Map.Entry<String, BDDNode> pair : nodeHashMap.get(length).entrySet()) {
            if (pair.getValue().equals(left)) {
                if (nodeHashMap.get(length + 1).containsKey(pair.getKey())) {
                    removeAll(left, length);
                    iReductions++;
                    return nodeHashMap.get(length + 1).get(pair.getKey());
                } else {
                    nodeHashMap.get(length + 1).put(pair.getKey(), pair.getValue());
                }
                break;
            }
        }
        nodeHashMap.get(length).remove(getNodeKey(left, length));
        if (left.getLeft() != null)
            moveAllUp(left.getLeft(), length - 1);
        if (left.getRight() != null)
            moveAllUp(left.getRight(), length - 1);
        return left;
    }

    private void removeAll(BDDNode right, int length) {
        nodeHashMap.get(length).remove(getNodeKey(right, length));
        if (right.getLeft() != null)
            removeAll(right.getLeft(), length - 1);
        if (right.getRight() != null)
            removeAll(right.getRight(), length - 1);
    }

    private boolean equalChildren(BDDNode root, int level) {
        if (root.getLeft() != null && root.getRight() != null)
            if (root.getLeft().equals(root.getRight()))
                return true;
        if (level != 0) {
            String right = "";
            String left = "";
            for (Map.Entry<String, BDDNode> pair : nodeHashMap.get(level - 1).entrySet()) {
                if (pair.getValue().equals(root.getLeft()))
                    left = pair.getKey();
                if (pair.getValue().equals(root.getRight()))
                    right = pair.getKey();
            }
            if (left.equals(right) && !right.equals("") && !left.equals(""))
                return true;
        }
        return false;
    }

    private String division(String currentConfig, char achar, boolean right) {
        String[] helper = currentConfig.split(" \\+ ");
        StringBuilder result = new StringBuilder(new String());
        if (helper[0].equals(""))
            return "";
        if (!right) {
            for (int i = 0; i < helper.length; i++) {
                if (helper[i].charAt(0) == '!') {
                    if (helper[i].charAt(1) != achar) {
                        if (result.toString().equals("")) {
                            result = new StringBuilder(helper[i]);
                            continue;
                        }
                        if (!isInString(result.toString(), helper[i]))
                            result.append(" + ").append(helper[i]);
                    } else {
                        if (helper[i].length() == 2) {
                            result = new StringBuilder(".");
                            break;
                        }
                        if (result.toString().equals("")) {
                            result = new StringBuilder(helper[i].substring(2));
                            continue;
                        }
                        if (!isInString(result.toString(), helper[i].substring(2)))
                            result.append(" + ").append(helper[i].substring(2));
                    }
                } else {
                    if (helper[i].charAt(0) != achar) {
                        if (result.toString().equals("")) {
                            result = new StringBuilder(helper[i]);
                            continue;
                        }
                        if (!isInString(result.toString(), helper[i]))
                            result.append(" + ").append(helper[i]);
                    }
                }
            }
            return result.toString();
        }
        for (int i = 0; i < helper.length; i++) {
            if (helper[i].charAt(0) != '!') {
                if (helper[i].charAt(0) != achar) {
                    if (result.toString().equals("")) {
                        result = new StringBuilder(helper[i]);
                        continue;
                    }
                    if (!isInString(result.toString(), helper[i]))
                        result.append(" + ").append(helper[i]);
                } else {
                    if (helper[i].length() == 1) {
                        result = new StringBuilder(".");
                        break;
                    }
                    if (result.toString().equals("")) {
                        result = new StringBuilder(helper[i].substring(1));
                        continue;
                    }
                    if (!isInString(result.toString(), helper[i].substring(1)))
                        result.append(" + ").append(helper[i].substring(1));
                }
            } else {
                if (helper[i].charAt(1) != achar) {
                    if (result.toString().equals("")) {
                        result = new StringBuilder(helper[i]);
                        continue;
                    }
                    if (!isInString(result.toString(), helper[i]))
                        result.append(" + ").append(helper[i]);
                }
            }
        }
        return result.toString();
    }

    private boolean isInString(String whole, String toFind) {
        String[] field = whole.split(" \\+ ");
        for (String actual : field) {
            if (actual.equals(toFind))
                return true;
        }
        return false;
    }
}
