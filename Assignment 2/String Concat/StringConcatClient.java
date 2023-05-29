import StringConcatModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;

class StringConcatClient {
    public static void main(String args[]) {
        StringConcat concatImpl = null;

        try {
            // Initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            // Resolve the NameService object reference
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolve the StringConcat object reference using the name "StringConcat"
            String name = "StringConcat";
            concatImpl = StringConcatHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Enter first string:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str1 = br.readLine();

            System.out.println("Enter second string:");
            String str2 = br.readLine();

            String concatenatedStr = concatImpl.concatenate_strings(str1, str2);

            System.out.println("Concatenated string: " + concatenatedStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

