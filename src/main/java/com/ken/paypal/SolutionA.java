package com.ken.paypal;

import java.util.Stack;

public class SolutionA {

    public String xmlParser(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<String> st = new Stack<>();
        for (int i = 0; i < s.length(); ) {
            String temp = "";
            int j = 1;
            int r = 0;
            if (s.charAt(i) == '<') {
                r = (s.substring(i)).indexOf('>');
                temp = s.substring(i, i + r + 1);
                if (temp.contains("/")) {
                    st.pop();
                    j--;
                } else {
                    st.add(temp);
                }
                i += r + 1;
            } else {
                r = s.substring(i).indexOf('<');
                temp = s.substring(i, i + r);
                i += r;
            }
            for (; j < st.size(); j++) {
                sb.append(" ");
            }
            sb.append(temp);
            sb.append("\n");
        }
        return sb.toString();
    }

}
