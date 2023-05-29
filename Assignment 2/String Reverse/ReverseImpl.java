
//============================================================================
//
//  Name        : ReverseImpl.java
//  Author      : Sachin Vijaykumar Kunte
//  Copyright   : Copyright ©2019 by Sachin Kunte All rights reserved.
//  Description : A Java program for a string reverse logic 
//  contact     : kuntesv@gmail.com
//
//============================================================================

import ReverseModule.ReversePOA;
import java.lang.String;
class ReverseImpl extends ReversePOA
{
    ReverseImpl()
    {
        super();
        System.out.println("Reverse Object Created");
    }

    public String reverse_string(String name)
    {
    StringBuffer str=new StringBuffer(name);    
    str.reverse();
        return (("Server Send "+str));
    }
}

