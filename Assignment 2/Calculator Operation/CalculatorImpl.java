import CalculatorModule.CalculatorPOA;

class CalculatorImpl extends CalculatorPOA {
    CalculatorImpl() {
        super();
        System.out.println("Calculator Object Created");
    }

    public float add(float num1, float num2) {
        return num1 + num2;
    }

    public float subtract(float num1, float num2) {
        return num1 - num2;
    }

    public float multiply(float num1, float num2) {
        return num1 * num2;
    }

    public float divide(float num1, float num2) {
        if (num2 != 0) {
            return num1 / num2;
        } else {
            System.out.println("Error: Division by zero");
            return 0;
        }
    }
}

