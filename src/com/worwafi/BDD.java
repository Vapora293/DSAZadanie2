package com.worwafi;

import java.util.ArrayList;
import java.util.HashMap;

public class BDD {
    private BDDNode root;
    private final String entry;
    private final String orderOfNodes;
    private final BDDNode bright;
    private final BDDNode bfalse;
    private ArrayList<HashMap<String, BDDNode>> nodeHashMap;
    public BDD(String entry, String order) {
        this.entry = entry;
        this.orderOfNodes = order;
        nodeHashMap = new ArrayList<>(orderOfNodes.length()+1);
        for(int i = 0; i < orderOfNodes.length()+1; i++)
            nodeHashMap.add(i, new HashMap<>());
        bright = new BDDNode(1);
        bfalse = new BDDNode(0);
        root = new BDDNode(orderOfNodes.charAt(0));
        root = addRecursive(root, entry, orderOfNodes);
    }

    private String[] getConvertedEntry() {
        return entry.split(" + ");
    }

    private void createTree() {
//        for(int i = 0; i < convertedEntry.length; i++) {
//            currentConfig = convertedEntry[i];
////            int actual = Integer.parseInt(String.valueOf(convertedEntry.charAt(i)));
////            currentConfig = getBinaryValues(i);
//            addToBDD(convertedEntry, currentConfig);
//        }
    }

//    private void addToBDD(BDDNode root, String[] convertedEntry, String currentConfig) {
//        if(root == null) {
//            root = new BDDNode(orderOfNodes.charAt(0));
//        }
//        root = addRecursive(root, currentConfig, orderOfNodes);
//    }
    private BDDNode addRecursive(BDDNode root, String currentConfig, String orderOfNodes) {
        if(nodeHashMap.get(orderOfNodes.length()).put(currentConfig, root) != null) {
            System.out.println("problem on " + currentConfig.length() + " level with " + orderOfNodes);
        }
        if(currentConfig.equals(".")) {
            root = bright;
            return root;
        }
        else if(currentConfig.equals("")) {
            root = bfalse;
            return root;
        }
        if(orderOfNodes.length() > 1) {
            root.setLeft(addRecursive(new BDDNode(orderOfNodes.charAt(1)), division(currentConfig, orderOfNodes.charAt(0), false), orderOfNodes.substring(1)));
            root.setRight(addRecursive(new BDDNode(orderOfNodes.charAt(1)), division(currentConfig, orderOfNodes.charAt(0), true), orderOfNodes.substring(1)));
        }
        else {
            root.setLeft(addRecursive(new BDDNode(' '), division(currentConfig, orderOfNodes.charAt(0), false), ""));
            root.setRight(addRecursive(new BDDNode(' '), division(currentConfig, orderOfNodes.charAt(0), true), ""));
        }
//        if (checkLetter(currentConfig, orderOfNodes)) {
//            if(currentConfig.charAt(0) == '!') {
//                if(root.getLeft() == null)
//                    root.setLeft(new BDDNode(currentConfig.charAt(1)));
//                addRecursive(root.getLeft(), currentConfig.substring(2), orderOfNodes.substring(1));
//            }
//            else {
//                if(root.getRight() == null)
//                    root.setLeft(new BDDNode(currentConfig.charAt(1)));
//                addRecursive(root.getRight(), currentConfig.substring(1), orderOfNodes.substring(1));
//            }
//        }
//        else {
//
//        }
//        if(currentConfig.charAt(0) == '!') {
//            if(root.getLeft() == null) {
//                root.setLeft(new BDDNode(currentConfig.charAt(1)));
//            }
//            addRecursive(root.getLeft(), currentConfig.substring(2), orderOfNodes.substring(1));
//        }
//        if(Integer.parseInt(String.valueOf(currentConfig.charAt(0))) == 0) {
//            if(root.getLeft() == null) {
//                root.setLeft(new BDDNode(currentConfig.charAt(0)));
//            }
//            addRecursive(root.getLeft(), currentConfig.substring(1), orderOfNodes);
//        }
//        else {
//            if(root.getRight() == null) {
//                root.setRight(new BDDNode(currentConfig.charAt(0)));
//            }
//            addRecursive(root.getRight(), currentConfig.substring(1), orderOfNodes);
//        }
        return root;
    }

    private String division(String currentConfig, char achar, boolean right) {
        String[] helper = currentConfig.split(" \\+ ");
        String result = new String();
        if(helper[0].equals(""))
            return "";
        if(!right) {
            for(int i = 0; i < helper.length; i++) {
                if(helper[i].charAt(0) == '!') {
                    if(helper[i].charAt(1) != achar) {
                        if(result.equals("")) {
                            result = helper[i];
                            continue;
                        }
                        result += " + " + helper[i];
                        continue;
                    }
                    else {
                        if(helper[i].length() == 2) {
                            result = ".";
                            break;
                        }
                        if(result.equals("")) {
                            result = helper[i].substring(2);
                            continue;
                        }
                        result += " + " + helper[i].substring(2);
                    }
                }
                else {
                    if(helper[i].charAt(0) != achar) {
                        if(result.equals("")) {
                            result = helper[i].substring(1);
                            continue;
                        }
                        result += " + " + helper[i];
                    }
                }
            }
            return result;
        }
        for(int i = 0; i < helper.length; i++) {
            if(helper[i].charAt(0) != '!') {
                if(helper[i].charAt(0) != achar) {
                    if(result.equals("")) {
                        result = helper[i];
                        continue;
                    }
                    result += " + " + helper[i];
                    continue;
                }
                else {
                    if(helper[i].length() == 1) {
                        result = ".";
                        break;
                    }
                    if(result.equals("")) {
                        result = helper[i].substring(1);
                        continue;
                    }
                    result += " + " + helper[i].substring(1);
                }
            }
            else {
                if(helper[i].charAt(1) != achar) {
                    if(result.equals("")) {
                        result = helper[i].substring(1);
                        continue;
                    }
                    result += " + " + helper[i];
                }
            }
        }
        return result;
//
//        if(!right)
//            return currentConfig.replaceAll("[^" + achar + "\\s]*" + achar + "\\s*","");
//        else
//            return currentConfig.
    }

//    private boolean checkLetter(String currentConfig, String orderOfNodes) {
//        if (currentConfig.charAt(0) == '!')
//            return currentConfig.charAt(1) == orderOfNodes.charAt(0);
//        return currentConfig.charAt(0) == orderOfNodes.charAt(0);
//    }
//
//    private String getBinaryValues(int i) {
//        String actual = Integer.toBinaryString(i);
//        if(actual.length() != numberOfNodes) {
//            actual = getNullRepr(actual);
//        }
//        return actual;
//    }
//
//    private String getNullRepr(String actual) {
//        int nullAdd = numberOfNodes - actual.length();
//        String returnable = new String();
//        for(int i = 0; i < nullAdd; i++) {
//            returnable += "0";
//        }
//        return returnable + actual;
//    }
//
//    private boolean andFunction(BDDNode first, BDDNode second) {
//        return true;
//    }
//    private boolean orFunction(BDDNode first, BDDNode second) {
//        return true;
//    }
}
