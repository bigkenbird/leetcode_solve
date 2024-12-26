package com.ken;

import com.ken.paypal.ProblemA;
import com.ken.paypal.SolutionA;

public class Main {
    public static void main(String[] args) {
        String xml = "<a>Final<b><c><d>MyText</d></c>AnotherText</b></a>";
        SolutionA solutionA = new SolutionA();
        ProblemA problemA = new ProblemA();
        ProblemA.Node result = problemA.parseXml(xml);
        ProblemA.Node saveNode = result;
        while (true) {
            System.out.println(saveNode);
            if (saveNode.getChildren() != null) {
                saveNode = saveNode.getChildren();
            } else {
                break;
            }
        }


    }
}