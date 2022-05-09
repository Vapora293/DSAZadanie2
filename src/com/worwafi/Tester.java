package com.worwafi;

import java.util.ArrayList;
import java.util.HashMap;

public class Tester {
    String dnf;
    String orderOfNodes;
    BDD tree;

    public Tester(String dnf, String orderOfNodes, BDD tree) {
        this.dnf = dnf;
        this.tree = tree;
        this.orderOfNodes = orderOfNodes;
        bdd_use();
    }

    private void bdd_use() {
        String[] entry = dnf.split(" \\+ ");
        for (String actual : entry) {
            ArrayList<StringBuilder> converted = getConvertedEntry(actual, orderOfNodes);
            for (StringBuilder now : converted) {
                if(!(tree.traverseTree(tree.getRoot(), now.toString(), orderOfNodes, -1) == 1)) {
                    System.out.println("error on " + dnf);
                }
            }
        }
    }
    private ArrayList<StringBuilder> getConvertedEntry(String actual, String order) {
        ArrayList<StringBuilder> converted = new ArrayList<>();
        converted.add(new StringBuilder());
        while(!order.equals("")) {
            if(actual.equals("")) {
                doubleAll(converted);
                order = order.substring(1);
                continue;
            }
            if (actual.charAt(0) == '!') {
                if (actual.charAt(1) == order.charAt(0)) {
                    appendAll(converted, "0");
                    actual = actual.substring(2);
                } else {
                    doubleAll(converted);
                }
            } else {
                if (actual.charAt(0) == order.charAt(0)) {
                    appendAll(converted, "1");
                    actual = actual.substring(1);
                } else {
                    doubleAll(converted);
                }

            }
            order = order.substring(1);
        }
        return converted;
    }
    private void doubleAll(ArrayList<StringBuilder> converted) {
        int i = 0;
        try {
            while (converted.get(i) != null) {
                converted.add(i + 1, new StringBuilder(converted.get(i)));
                converted.get(i).append("0");
                converted.get(i + 1).append("1");
                i += 2;
            }
        }
        catch (IndexOutOfBoundsException index) {
        }
    }

    private void appendAll(ArrayList<StringBuilder> converted, String s) {
        for (StringBuilder actual : converted) {
            actual.append(s);
        }
    }
}
