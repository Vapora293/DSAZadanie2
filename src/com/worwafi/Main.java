package com.worwafi;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            int numberOfChars = 5;
            for (int j = 1; j < 3; j++) {
                for (int k = 0; k < 10; k++) {
                    long start = System.currentTimeMillis();
//                    System.out.println("Tree number " + k + " with " + numberOfChars + " nodes and " + (100 + 50 * i * j) + " parts");
                    String dnf = dnfMake(numberOfChars, 10);
                    dnf = formatInput(dnf);
//                    System.out.println(dnf);
                    Tester tester = new Tester(dnf, orderBuilder(numberOfChars), new BDD(dnf, orderBuilder(numberOfChars)));
                    long end = System.currentTimeMillis();
//                    System.out.println("Time elapsed is " + (end - start));
                }
            }
        }
//        int numberOfChars = 50;
//        String dnf = "!E!F!G!H!J!L!MPQR + !B!E!G!I!JKM!O!R + !AC!FG!H!K!LO!P + !CDFG!KLN!P!R!S + CDEF!G!I!JO!PR + !B!CF!H!I!JMN!O!PQ!S + A!C!FI!J!LO!PR + A!CD!E!FGJL!MOQ!RS + !A!B!CD!FHN!OQ!R!S + !BE!H!J!K!LMNQR + DEIJK!MO!P!Q + B!C!DIJK!LPR + !BC!D!E!FH!IJ!KO + !CDEG!HI!J!K!LM!P!Q + B!CD!E!GH!I!LMNP!QS + A!CE!GH!I!L!OP!QS + !BCD!F!KL!N!O!PQ!R + BC!D!E!G!HMN!O!Q!R + !A!B!CD!F!G!H!L!O!P!S + !A!B!EG!JL!N!O!P!S + !BG!J!K!LMNP!Q!R!S + ACDE!I!J!K!M!N!P!R + A!C!EF!HI!JK!L!M!N!Q + !B!C!EI!JN!OS + BEFG!HI!J!N!PQ!S + !A!BC!DH!IJK!M!NOQ + BCFHJ!KL!M!O!R!S + B!CEF!G!KL!M!O!PR!S + !B!DFG!H!I!L!N!O!PR + !BCD!E!F!GHLNPQR + !C!EF!GH!JKO!P!S + !ABDH!IKLMPS + !B!DEFIJ!LM!Q + !BD!GH!I!L!M!QR + !EI!J!K!LMO!P!R + AB!DFJLN!PR + B!C!DFGH!L!N!OPQ!RS + !BCDEGILNOP!Q + CEF!G!H!I!J!M!NOR + !A!B!DF!GHIJ!MOS + !ACD!F!G!HIJL!M!P + A!BCD!FJ!NOP + !B!CFGJKL!NRS + !BE!FG!HIL!MN!O!P + A!B!D!G!H!IJ!K!M!P!S + DFGHJ!LMOQ!R!S + CD!GH!ILP!Q!S + !BEFHI!J!K!MNP!Q!R + A!DF!GH!I!JKM!NQ!R!S + A!DE!F!GHI!KNR + A!FGH!K!M!N!P!QS + !C!DEIJ!KL!MN!Q + !A!CDE!H!JLMNOQ + !AC!E!IJKPQS + !B!DEF!GHIMN!OP!Q!R!S + !ACDEH!I!KL!MNORS + BDE!FKMN!P!R!S + BCE!FG!H!J!KLM!OP!R + !A!BC!E!G!HI!N!P!Q!R + A!B!DFGH!J!LNR!S + !BD!EGH!I!KLO!P + !CD!E!HILN!O!Q!S + !A!B!D!G!HIL!N!O!RS + ABGIK!M!N!OPQ!S + !ACEFG!I!KNO!Q + BCF!GI!K!MOS + !B!E!HK!M!OP!Q!RS + !AC!E!G!J!M!NOPRS + !AB!C!D!F!GJL!M!Q!R + !ADF!H!JLN!P!R!S + ABC!F!IJ!K!L!NPQS + A!D!E!F!GHKLNR!S + !ACD!EFGHI!J!K!MP!R!S + !B!C!FG!HILM!NOQ!S + ABC!D!F!GJ!KM!N!OP!Q + B!D!EF!H!KNP!R!S + !B!C!DE!IJL!MO!P!Q!S + A!CDFGHIJ!M!NO!PS + !CE!F!G!HJ!K!MNO!Q + A!BDE!HJL!P!R + B!DJK!M!N!OR!S + ABCD!EGH!IJ!MN!OQ!R!S + !A!C!D!HJ!LNQ!S + A!C!FHK!P!R + !BEFH!LM!N!PQR + !B!C!E!H!J!K!L!M!NS + BD!G!H!I!LOP!Q!R + A!B!E!F!GI!J!K!O!Q + A!B!DGH!I!KM!N!OQ + ABGHI!J!PRS + AE!F!HI!JK!NO!PQS + !BEGIJK!L!MN!OQ!S + A!CFLMN!P!QR + CD!FG!H!I!KL!M!OQS + AF!H!I!LNPQS + C!GHJN!P!Q!R!S + !ABCFG!JK!P!QS + AF!GHI!K!L!M!N!Q!R + B!D!EF!G!HI!MPS + !ACDHIKLMN!R + !AB!C!DH!IKLOP!Q + !A!E!F!GILNO!PR + !A!CE!H!J!K!L!N!OPQ + !A!BCEFH!I!J!KOP!Q + CE!F!GHI!JKL!N + !A!CE!GH!IK!MO!P!Q!R + !A!E!F!GI!J!K!MOP!QR!S + BCEGHI!JK!L!MO!P!R + A!BD!E!FG!HI!M!OQ!S + !AB!C!D!EHI!L!M!NPQ + AC!DE!G!H!I!M!O!Q!S + !A!B!CF!G!H!ILOQ + !EF!L!M!PQS + B!DEGIJK!LM!N + !A!B!CE!L!O!P!Q!R + A!BCEFI!JM!NOPS + !A!EGH!IJ!KMPQ!S + AB!IJ!O!QRS + !A!E!F!GH!J!L!M!NO!QR!S + BCD!E!FKM!NO!S + !ABE!F!K!M!N + AB!DEFK!R!S + A!EF!GIJ!K!LM!O!PQS + !B!CE!F!H!I!JMNP!QRS + !CD!E!F!GHJKLOS + !ABC!D!FIO!P!Q!R!S + !AB!C!FG!HJ!L!M!NOR + DE!F!J!KL!N!O!P!Q!R!S + B!C!DFGH!LNP!RS + A!D!EFJL!OP!Q + A!DE!FGK!M!N!OPS + !A!DEF!G!JKL!MP!Q + AB!DE!G!H!I!JNOQ!S + AFIJ!K!NOS + D!IJKL!O!PQ!S + !CD!EFG!H!I!KMNO!R!S + BCEG!KLN!PR + AB!C!D!G!IK!L!MNQS + !A!CE!GH!JLNOP!Q!RS + !B!C!H!J!L!MO + !A!BC!FG!H!IJL!MQ!R + !EFGH!I!KMNQR + !A!CDE!FGJK!LPRS + BDF!GHJ!PR!S + !EG!IKOP!R!S + B!CF!G!H!IJNPQ!S + !B!C!E!FG!HI!J!K!L!N!Q + A!E!FHI!KLMPR!S + !BC!D!E!FGHI!KM!Q + ABC!FJKO!P + !C!D!FGH!I!JPQ!R + !AB!EG!IK!MPQR!S + !BFG!H!I!JKL!N!RS + A!C!FGH!JO!PR!S + ACDG!HJ!L!O!PS + !C!E!GH!J!N!OP!R + !AC!DEFIKLM!N!Q!S + B!C!D!HJ!MNPQ!R + A!CD!F!G!I!KO!QS + !C!E!FG!H!J!LM!NOP!Q!R + !A!B!E!FGH!IJ!OP!Q!R + !A!C!DF!H!JKLMN!R + !B!D!F!GK!MNO + A!DE!F!GHK!L!O!P + !B!D!E!FGJK!M!NOQ + A!BC!DE!GJ!O!PQR + !ABCD!FI!J!KLM!N!P!R!S + B!D!E!G!HIJM!N!O!P!R + !A!BC!EF!HJ!L!M!NO!QRS + !AB!C!GHJK!MP!Q + !A!BCD!GH!J!KMNO + BCDGH!L!MO!PQ!R + !D!EGHL!M!N!OPQ!R + B!C!EG!H!I!J!NOP!QS + AD!EG!H!IK!O + CD!F!G!HL!M!P!Q + B!DGHJ!K!L!M!S + !BCDE!GI!J!KO!PS + BE!FGHJL!MOPQ!R + CDEG!H!IJKL!Q!S + AB!GIK!LMNO!P!Q + !A!B!G!HIJ!KL!PQ!R + A!B!DE!GIJL!M!QR + BCD!EIJL!M!O!R!S + DE!FH!MNOQS + BC!G!IJ!L!N!Q!R + BC!H!ILM!N!O!PQ + BD!F!G!KLN!OP + A!CDF!H!I!LMP!Q + !A!BCDEFI!J!KM!P!R + CDEF!G!HJ!L!P!S + AB!C!I!L!MP!S + DE!G!HI!J!K!L!MP!Q!R!S + A!C!EG!JK!LPQ!R + AEFI!LMOQR!S + A!B!FGIJ!MNOP!S + !AB!C!DEH!KMPQS + !BCHIJ!KM!P!Q + ABF!IJ!K!L!MOPRS + BC!E!GKMO!PQR";
//        System.out.println(dnf);
//        String dnf = "!AB + K + L";
//        String dnf = "!A!B!DE + AD + B!D + !A!B!C!E + !ACD!E + !ACD + A!C!DE + !AB!DE + !DE + !B!C!D + AD!E + A!BC + !AD!E + !ADE + !A!B!C!E + !A!B!E + !BDE + AC!E";
//        String dnf = "!B!CD + !BCD + BD";
//        String dnf = "AB + AC + B + D";
//        String dnf = "!A!BE!F + C!D!EFG + !B!C!DGH + !B!DE!F + BE!F!G + !C!DF!H + !BC!DH + ABC!D!F + AEGH + BC!DF!H + !D!FG!H + D!EF!H + !CDH + AC!FG!H + B!D!GH + BC!EFG + !B!C!G!H + !A!B!D!F!G + !BC!E!F!G + B!CD!E!F!H + B!CD!E!GH + BD!FG + !BDEF!G + ACEF!G + A!B!CGH + B!C!EFG + !A!C!F!G!H + !B!D!EF + !B!C!DE!GH + CEF!G!H + !BCEF + B!EFG + !DE!FGH + !A!B!DG!H + D!E!GH + !ABEGH + !AC!DEFGH + ADE!GH + B!C!G + !B!FH";
//        String dnf = "CDE + !ADE + ADE";
//        String dnf = "!C!E + ABE + !A!B + !AB!C + BDE + A!BCD + !BCD + !ABC!E + !ABCD + !CD!E + AB!C + B!CD + !ACD + !ABE + !A!B!CD + !A!BCE";
//        String dnf = "AB + BAD + DBA + ECAB + DBEC + BED + BC + EBC + DAE + EBD + CED + DECA + ADB + ACD + DC";
//        String dnf = "!ADE + !BDE + !BC + A!C + AD + AC + B!D + !AB!E + !BE + A!D + A!BC!E + !A!B!C!E + !A!C!D + A!CD + !BC + !A!B!C";
//        String dnf = "A!C + !C!E + DE + B!E + !A!E + !A!C + !AC!D + !A!C!E + AB + A!C!E + !A!B!D!E + C!DE + !BD + !C!DE";
//        System.out.println("DNF is: " + dnf);
//        String dnf = "!A!C!D!EF!GJL!M!PR + !A!CGH!JL!O!PS + !BCD!HL!M!N!OPQR + AB!CD!FGHJ!L!MOPQ!R + !A!CFG!I!L!M!NPR + !ABE!FH!J!K!O!PQ + B!CE!GH!K!LOPQR + CDG!H!J!KL!MN!OPS + B!DJ!KM!N!O!PQS + !C!DG!I!K!L!N!P!RS + !ADFH!J!KLMRS + !CD!EH!I!KL!M!N!PQRS + !A!BC!DE!FI!L!MP!QS + !A!E!F!GI!JKL!MO!P!S + !F!G!J!L!O!PQS + !ABDE!F!JK!M!O!PQRS + !BCEF!GI!K!N!O!P!R!S + !A!D!F!GJK!L!N!O + AB!C!F!H!IJ!KN!OR + !C!D!E!F!LNOPQR + A!CE!G!H!I!JM!NOP!S + C!DE!FGHIJ!LN!P!S + A!B!D!F!HL!MN!O!R!S + E!FGHI!JM!ORS + B!CDGH!I!J!K!LN!OP!QS + !BC!E!FI!JKMNO!P!S + !CFG!H!I!JKNP!QR + BCDEFG!H!IL!M!N!OP + !BC!F!HIJK!MP!RS + AE!F!G!HIKL!MP!QS + !A!BC!D!E!F!G!H!J!L!OS + !ABC!DE!F!KLMN!OQ + !A!BC!FG!I!K!OPRS + !C!DEHIN!PQR + !ABC!DF!KLNO!P!Q + !E!FGIJLM!NQ!S + C!DEF!H!J!K!L!N!PR + AC!E!G!HLO!P!QS + ABEIJ!KL!N!P!Q!RS + !B!CE!F!GJK!L!M!N!Q + A!B!CD!E!I!KL!PQR!S + B!C!DFGJ!LMNO!Q + !B!CF!J!KM!N!P!QS + !B!C!FJK!M!O!P!QR!S + ACD!E!F!GJK!L!MO!R + !CD!FGK!L!N!QS + !FI!NOP!Q!R!S + A!C!EGHIJKMNO!P!RS + !B!CE!FGJK!L!M!P!QR!S + !AC!D!IK!L!M!N!O!PS + ACEFH!IJ!M!NO!S + !A!B!F!HL!MQR!S + ACD!J!KL!MO!R + A!B!C!E!HLN!OQS + ABCD!F!G!I!JK!O!QR!S + AB!C!FIM!NO!Q!R + !AC!E!J!K!LM!N!O!R + A!BC!D!EFIO!Q!R!S + CD!E!FJ!KNQRS + !B!CDE!FH!IJL!RS + !B!CDEG!HJ!KN!PR!S + A!BCFGH!I!KM!P!QR + A!B!C!DEF!H!IK!L!N!P!Q + ADEH!I!K!L!MN!O!P + A!DF!GHI!L!N!OQS + B!D!F!G!JNOPQ!S + A!B!C!FJ!K!OP!Q!S + !AC!D!EJK!OPRS + !A!B!CD!E!FGI!JK!MP + !ABCD!FG!HJK!M!OP!R!S + AC!DE!F!HK!L!N!OQR + ABCDE!GIKOR!S + !CD!GI!K!LN!O!PR + A!B!DF!H!JK!L!MO!P!Q!R + !AB!CDGH!I!J!LM!QS + AC!FH!KL!NO!PS + ABD!EGHL!MOP + !BCD!E!FG!HI!MNR + !B!DEFIJ!K!N!Q!S + !A!D!EH!MO!P!Q!R + C!H!J!KM!NO!PQ!R!S + !B!CGH!I!J!L!MN!OR + !AB!D!EGHMNS + AC!DEG!IJ!LNP + !ACE!F!H!MN!O!S + !A!C!DFGH!I!KMOR!S + !ABC!F!IKOP!RS + BCD!F!LN!OP!Q!R + BDE!F!JLMOQRS + A!CEF!H!J!K!LO!Q!R + EFH!MNQ!RS + A!CEF!KLPQ + E!F!GI!JLMN!OQ!RS + !A!B!CF!IJ!KLQS + !AB!C!DE!FHIJ!N!OP!Q + A!DEFG!IJ!M!Q!R!S + !C!D!F!GH!K!Q!RS + A!BC!DF!H!L!M!O!R!S + !BC!FGHI!J!K!P!RS + AB!C!EF!GKLO!P!QS + BD!HJKMNOPS + CF!GH!IJK!MOS + BDHIJKNP + !A!BE!F!G!J!K!L!MNOP!Q + A!B!HM!NPQ!RS + !A!BCE!FJ!LM!OPS + !A!D!EHK!L!OP!Q!RS + A!B!CE!GI!JK!L!M!QS + AC!EF!G!H!K!L!M!NQRS + !DE!F!G!HINOQR!S + AC!D!H!I!NOPQ + !AB!CEFGHI!NO!RS + C!E!F!G!HIJ!KL!M!N!PQ + BE!HIJ!K!OPR!S + !ACGJL!MNQR + !AC!DJ!KLOP!S + !CEG!H!KL!OQ!R + !ACD!HJK!L!MNQ + !ACF!HJM!N!P!Q!S + A!E!F!G!H!J!K!N!OP!Q!S + !A!B!CD!I!JKL!M!S + AB!C!D!EG!H!KRS + B!DEF!GIJ!L!M!NO + !ABCDFGHIJK!OS + A!EFILMO!PQ!RS + !A!BCG!HI!KPQR + AB!CH!K!L!P!S + AB!CE!F!H!IJKL!OS + !EF!KMNOQRS + !A!BCFHJKMNO!P!Q!R!S + BD!E!F!GIL!MS + !A!B!CD!EJK!LNOP!Q + BCFI!J!KM!O!QR + !A!BC!DE!H!J!KN!OQ!R + AB!CE!GH!K!LOPQ!S + AF!GHJK!MNRS + !AC!GIK!LM!O!PRS + !BC!EG!HJLNPQS + !A!EF!GH!NO!PR + B!CDEG!IJKN!P!QRS + BF!GHKLO!QRS + D!E!G!H!IJKLMQR!S + CD!EGI!JKL!NS + !AB!CIJ!LM!N!R + ACEJ!K!L!M!O!PQ!RS + !E!G!HI!KMNQR + BCFG!H!IJO!QS + !A!BDFGH!IK!LMNOQR + !BDE!FJ!M!O!PQ!S + B!CDGJ!K!L!M!N!R + A!BCD!GH!J!K!L!M!PR + A!BC!DE!HIKQ!R!S + !AFIJ!LMOP!Q!S + AB!FI!K!L!OP!R!S + !A!C!IJK!LMPQ!R!S + !E!FG!H!JLMNP + !ADE!H!I!J!K!LMQ!S + !AB!CDF!GH!IJKL!Q + !A!BD!F!G!H!I!KM!N!O!P + C!F!H!IJK!L!N!PR + !B!F!IJKL!M!PR + A!BCE!F!G!H!IK!M!O!Q!S + !BCDG!H!KMO!R + !A!BE!FG!L!OPQS + !D!FH!I!J!NQ + C!D!EH!IJK!LMO!PS + AB!C!D!E!H!IKL!NO!PQ!S + !BDEF!G!H!JK!MS + !ABC!DH!IKL!MN!OQ!R + !B!C!E!FJ!K!NPQ!RS + AC!EFGHJ!M!NR!S + ABC!DFIJ!K!LNR + A!C!E!G!H!I!LMNO!Q!RS + !AE!F!G!H!J!L!NQR!S + !C!DE!F!HI!J!KMO!Q!R + !CDF!I!J!M!P!Q + C!GH!IJ!L!MO!R!S + G!HJKL!MOPR!S + ABEG!H!K!L!MN!P!R + !BC!EF!HJ!K!LNQS + !E!FG!I!L!M!Q!R + A!D!E!G!IKLMNQS + A!C!D!FHK!LN!P + !ADF!G!JL!M!NO!QR + BEGH!ILOQ!R!S + AC!D!F!IJ!O!PQ!R!S + ACDEGH!KL!M!PQ + A!C!EIJ!OQ!R + A!CD!IJ!L!O!QRS + !A!B!CD!EF!HJ!KMN!Q + AF!I!JL!MPQ + AB!C!FG!H!J!N!O!PQ + !A!BCDE!F!I!O + B!F!JKL!M!NOPQ + !AD!EGH!I!KL!MOQ + !B!C!DEGIKM!N!OPQ!RS + CEF!G!HJ!K!MN!O!P!R + !B!E!FHJ!KLORS + AE!F!G!HIK!L!OQ!S + DE!FG!HIK!L!OPQ";
//        BDD tree = new BDD(dnf, orderBuilder(4));
//        dnf = formatInput(dnf);
//        Tester tester = new Tester(dnf, orderBuilder(19), new BDD(dnf, orderBuilder(19)));

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

