import CalculatorModule.Calculator;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            // Initialize the POA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // Create the Calculator object
            CalculatorImpl calcImpl = new CalculatorImpl();

            // Get the object reference from the servant class
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(calcImpl);

            // Narrow the object reference to the appropriate type
            Calculator calcRef = CalculatorModule.CalculatorHelper.narrow(ref);

            // Resolve the NameService object reference
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Bind the object reference to the naming context with the name "Calculator"
            String name = "Calculator";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, calcRef);

            System.out.println("Calculator Server is running and waiting...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

