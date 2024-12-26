package com.ken.paypal;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemA {

    public Node parseXml(String xml) {
        Stack<String> stack = new Stack<>();
        Node parent = null;
        Node children = null;

        for (int i = 0; i < xml.length(); ) {

            if (xml.charAt(i) == '<') {
                int j = xml.substring(i).indexOf(">");
                String temp = xml.substring(i, i + j + 1);
                i = i + j + 1;

                if (temp.contains("/")) {
                    children = parent != null ? parent : new Node();
                    
                    while (true) {
                        String pop = stack.pop();
                        if (isTagStart(pop)) {
                            children.setName(getTagStartName(pop));
                            parent = new Node();
                            parent.setChildren(children);
                            break;
                        } else {
                            children.setValue(pop);
                        }
                    }
                } else {
                    stack.push(temp);
                }
            } else {
                int j = xml.substring(i).indexOf("<");
                String temp = xml.substring(i, i + j);
                stack.push(temp);
                i += j;
            }
        }
        assert parent != null;
        return stringIsEmpty(parent.getName()) && stringIsEmpty(parent.getValue()) ? parent.getChildren() : parent;

    }

    private boolean isTagStart(String pop) {
        return Pattern.compile("<(.*?)>").matcher(pop).matches();
    }

    private String getTagStartName(String pop) {
        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(pop);
        return matcher.find() ? matcher.group(1) : null;
    }

    private boolean stringIsEmpty(String target) {
        return target == null || target.isEmpty();
    }

    public class Node {
        String name;
        String value;
        Node children;

        public Node() {

        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public void setChildren(Node children) {
            this.children = children;
        }

        public Node getChildren() {
            return this.children;
        }

        @Override
        public String toString() {
            return String.format("{name:%s, value:%s}", name, value);
        }
    }
}
