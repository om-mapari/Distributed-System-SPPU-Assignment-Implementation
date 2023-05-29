import StringConcatModule.StringConcat;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

class StringConcatServer {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            // Initialize the POA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // Create the StringConcat object
            StringConcatImpl concatImpl = new StringConcatImpl();

            // Get the object reference from the servant class
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(concatImpl);

            // Narrow the object reference to the appropriate type
            StringConcat concatRef = StringConcatModule.StringConcatHelper.narrow(ref);

            // Resolve the NameService object reference
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Bind the object reference to the naming context with the name "StringConcat"
            String name = "StringConcat";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, concatRef);

            System.out.println("String Concat Server is running and waiting...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

