package com.worwafi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int numberOfChars = 5;
//        String dnf = dnfMake(numberOfChars, 20);
        String dnf = "!C!E + ABE + !A!B + !AB!C + BDE + A!BCD + !BCD + !ABC!E + !ABCD + !CD!E + AB!C + B!CD + !ACD + !ABE + !A!B!CD + !A!BCE";
//        String dnf = "AB + BAD + DBA + ECAB + DBEC + BED + BC + EBC + DAE + EBD + CED + DECA + ADB + ACD + DC";
//        String dnf = "!ADE + !BDE + !BC + A!C + AD + AC + B!D + !AB!E + !BE + A!D + A!BC!E + !A!B!C!E + !A!C!D + A!CD +  !BC + !A!B!C";
//        String dnf = "A!C + !C!E + DE + B!E + !A!E + !A!C + !AC!D + !A!C!E + AB + A!C!E + !A!B!D!E + C!DE + !BD + !C!DE";
        dnf = formatInput(dnf);
        BDD actual = new BDD(dnf, orderBuilder(numberOfChars));
        System.out.println("yes");
    }

    private static String dnfMake(int numberOfChars, int numberOfParts) {
        StringBuilder dnf = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < numberOfParts; i++) {
            int length = rand.nextInt(numberOfChars - (numberOfChars / 3), numberOfChars);
            StringBuilder actual = new StringBuilder();
            for (int j = 0; j < length; j++) {
                char letter = (char) rand.nextInt(65, 65 + numberOfChars);
                if (!actual.toString().contains(String.valueOf(letter))) {
                    actual.append(letter);
                }
            }
            if (i == 0)
                dnf.append(actual);
            if (!actual.equals("") && !dnf.toString().contains(actual))
                dnf.append(" + " + actual);
        }
        return dnf.toString();
    }

    private static String formatInput(String input) {
        StringBuilder format = new StringBuilder();
        String[] expressions = input.split(" \\+ ");
        Random rand = new Random();
        for (int i = 0; i < expressions.length; i++) {
            String[] variables = expressions[i].split("");

            Arrays.sort(variables, Comparator.comparingInt(o -> (o.length() == 2 ? o.charAt(1) : o.charAt(0))));

            for (int j = 0; j < variables.length; j++) {
                if (variables[j].equals("!"))
                    continue;
                if (rand.nextBoolean())
                    format.append("!");
                format.append(variables[j]);
//                if (j < expressions.length - 1)
//                    format.append(".");
            }
            if (i < expressions.length - 1)
                format.append(" + ");
        }
        return format.toString();
    }

    private static String orderBuilder(int numberOfChars) {
        StringBuilder order = new StringBuilder();
        for (int i = 0; i < numberOfChars; i++)
            order.append((char) (65 + i));
        return order.toString();
    }
}

