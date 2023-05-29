import CalculatorModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;

class CalculatorClient {
    public static void main(String args[]) {
        Calculator calcImpl = null;

        try {
            // Initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            // Resolve the NameService object reference
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolve the Calculator object reference using the name "Calculator"
            String name = "Calculator";
            calcImpl = CalculatorHelper.narrow(ncRef.resolve_str(name));

            // Prompt the user to enter two numbers
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the first number:");
            float num1 = Float.parseFloat(br.readLine());

            System.out.println("Enter the second number:");
            float num2 = Float.parseFloat(br.readLine());

            // Perform calculator operations
            float resultAdd = calcImpl.add(num1, num2);
            float resultSubtract = calcImpl.subtract(num1, num2);
            float resultMultiply = calcImpl.multiply(num1, num2);
            float resultDivide = calcImpl.divide(num1, num2);

            // Display the results
            System.out.println("Addition: " + num1 + " + " + num2 + " = " + resultAdd);
            System.out.println("Subtraction: " + num1 + " - " + num2 + " = " + resultSubtract);
            System.out.println("Multiplication: " + num1 + " * " + num2 + " = " + resultMultiply);
            System.out.println("Division: " + num1 + " / " + num2 + " = " + resultDivide);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

