package solid.ocp.good;

public class SumOperation implements Operation{
    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}
