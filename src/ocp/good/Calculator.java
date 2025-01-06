package ocp.good;

public class Calculator {

    public int calc(int a, int b, Operation operation) {
        return operation.calc(a, b);
    }
}
