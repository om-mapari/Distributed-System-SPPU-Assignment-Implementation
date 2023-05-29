import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;

class ReverseClient
{
    
    public static void main(String args[])
    {
        Reverse ReverseImpl=null;
        
        try
        {
            // initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            String name = "Reverse";
            ReverseImpl = ReverseHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Enter String=");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str= br.readLine();

            String tempStr= ReverseImpl.reverse_string(str);
        
            System.out.println(tempStr);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}


/*

Certainly! Let's go through the code and explain each part:

```java
import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;
```

These lines import necessary classes and interfaces for the client application, including the `ReverseModule` package, CORBA-related classes, and the `java.io` package for input/output operations.

```java
class ReverseClient {
    public static void main(String args[]) {
        Reverse ReverseImpl = null;

        try {
            // initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
```

This declares a class called `ReverseClient` and defines its `main` method. The `main` method serves as the entry point for the client application. The first line inside the `main` method initializes the ORB (Object Request Broker) using the `org.omg.CORBA.ORB.init` method, passing the command-line arguments (`args`) and `null` as the properties parameter.

The next lines obtain the reference to the Naming Service in a similar way as in the server application. The `orb.resolve_initial_references("NameService")` line retrieves the object reference to the Naming Service. Then, `NamingContextExtHelper.narrow` is used to narrow the object reference to the appropriate type (`NamingContextExt`).

```java
String name = "Reverse";
ReverseImpl = ReverseHelper.narrow(ncRef.resolve_str(name));
```

Here, a string variable `name` is assigned the value "Reverse", which represents the name of the object to be resolved and invoked. The `ncRef.resolve_str(name)` line resolves the object reference associated with the name "Reverse" in the Naming Service and returns the object reference for the `Reverse` object. The `ReverseHelper.narrow` method is used to narrow the obtained object reference to the appropriate type (`Reverse`).

```java
System.out.println("Enter String=");
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String str = br.readLine();

String tempStr = ReverseImpl.reverse_string(str);

System.out.println(tempStr);
```

These lines prompt the user to enter a string, read the string input from the user using `BufferedReader`, and store it in the `str` variable. Then, the `ReverseImpl.reverse_string(str)` line invokes the `reverse_string` method on the `ReverseImpl` object, passing the entered string as a parameter. The returned reversed string is stored in the `tempStr` variable.

Finally, the reversed string is printed to the console using `System.out.println(tempStr)`.

Overall, the code sets up the necessary connections and bindings to locate and invoke the `Reverse` object's methods on the server side. It prompts the user for a string, sends it to the server for reversal, and displays the reversed string received from the server.

 */