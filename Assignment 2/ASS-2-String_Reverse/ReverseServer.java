import ReverseModule.Reverse;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

class ReverseServer
{
    public static void main(String[] args)
    {
        try
        {
            // initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

            // initialize the BOA/POA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // creating the calculator object
            ReverseImpl rvr = new ReverseImpl();
            
            // get the object reference from the servant class
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(rvr);

            System.out.println("Step1");
            Reverse h_ref = ReverseModule.ReverseHelper.narrow(ref);
            System.out.println("Step2");

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            System.out.println("Step3");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            System.out.println("Step4");

            String name = "Reverse";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path,h_ref);

            System.out.println("Reverse Server reading and waiting....");
            orb.run();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}



/*
The code you provided is an implementation of a server application in Java that uses the CORBA (Common Object Request Broker Architecture) framework. Let's go through the code and explain each part:

```java
import ReverseModule.Reverse;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
```

These lines import necessary CORBA-related classes and interfaces that will be used in the server implementation.

```java
class ReverseServer {
    public static void main(String[] args) {
        try {
            // initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
```

This declares a class called `ReverseServer` and defines its `main` method. The `main` method serves as the entry point for the server application. The first line inside the `main` method initializes the ORB (Object Request Broker) using the `org.omg.CORBA.ORB.init` method, passing the command-line arguments (`args`) and `null` as the properties parameter.

```java
            // initialize the BOA/POA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();
```

These lines initialize the BOA/POA (Basic/Object Portable Object Adapter) by obtaining the reference to the root POA (Portable Object Adapter) from the ORB using `orb.resolve_initial_references("RootPOA")`. The `POAHelper.narrow` method is then used to narrow the obtained object reference to the `POA` type. Finally, the `rootPOA.the_POAManager().activate()` line activates the POA manager.

```java
            // creating the calculator object
            ReverseImpl rvr = new ReverseImpl();

            // get the object reference from the servant class
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(rvr);
```

These lines create an instance of the `ReverseImpl` class, which represents the implementation of the `Reverse` interface. The `ReverseImpl` class needs to be implemented separately and should contain the actual logic for reversing the string. The `rootPOA.servant_to_reference` method is used to obtain the object reference for the `rvr` servant.

```java
            System.out.println("Step1");
            Reverse h_ref = ReverseModule.ReverseHelper.narrow(ref);
            System.out.println("Step2");
```

These lines demonstrate narrowing the object reference to the appropriate type (`Reverse`). The `ReverseModule.ReverseHelper.narrow` method is used to narrow the object reference obtained earlier to the `Reverse` type.

```java
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            System.out.println("Step3");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            System.out.println("Step4");

            String name = "Reverse";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path,h_ref);
```

These lines obtain the reference to the Naming Service and create a name for our `Reverse` object. The `orb.resolve_initial_references("NameService")` line obtains the object reference to the Naming Service. Then, `NamingContextExtHelper.narrow` is used to narrow the object reference to the appropriate type (`NamingContextExt`). The `ncRef.to_name(name)` line converts the `name` into a `NameComponent` array. Finally, `ncRef.rebind(path, h_ref)` binds the `h_ref` object reference to the name in the Naming Service
 
Certainly! Let's go through the remaining lines:

```java
NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
System.out.println("Step4");
```

These lines narrow the object reference obtained for the Naming Service (`objRef`) to the `NamingContextExt` type using the `NamingContextExtHelper.narrow` method. The `NamingContextExt` interface provides additional functionality for working with the Naming Service.

```java
String name = "Reverse";
NameComponent path[] = ncRef.to_name(name);
ncRef.rebind(path, h_ref);
```

Here, a string variable `name` is assigned the value "Reverse". Then, the `ncRef.to_name(name)` line converts the `name` into a `NameComponent` array. The `NameComponent` represents a name component in the Naming Service hierarchy. Finally, `ncRef.rebind(path, h_ref)` binds the object reference `h_ref` to the specified name `path` in the Naming Service. This step associates the `h_ref` object reference with the name "Reverse" in the Naming Service, allowing clients to look up and access the object by this name.

```java
System.out.println("Reverse Server reading and waiting....");
orb.run();
```

These lines print a message indicating that the server is ready and waiting for client requests. Then, the `orb.run()` method is called, which enters the main event loop of the ORB, allowing it to handle incoming client requests. This call blocks the server execution until the ORB is shut down externally or encounters an error.

Overall, the remaining code in the `main` method sets up the necessary bindings and starts the server, allowing clients to locate and invoke the `Reverse` object's methods through the Naming Service.


*/