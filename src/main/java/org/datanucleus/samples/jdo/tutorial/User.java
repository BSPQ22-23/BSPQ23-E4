/**********************************************************************
Copyright (c) 2003 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package org.datanucleus.samples.jdo.tutorial;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

/**
 * Definition of a User. Extends basic Product class.
 * @author mikel
 *
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class User
{
	/**
	 * This variable represents the name of a User
	 */
    protected String name=null;
    /**
	 * This variable represents the name of a User
	 */
    protected String password=null;
    
    /**
	 * Void constructor of User
	 */
    protected User()
    {
    }
    
    /**
     * A user of our web page
	 * @param name name of the user
	 * @param password password of the user
     */
    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    /**
	 * Getter for the name of the User
	 * @return The String corresponding to the name of the User
	 */
    public String getName()
    {
        return name;
    }

    /**
	 * Getter for the password of the User
	 * @return The String corresponding to the name of the User
	 */
    public String getPassword()
    {
        return password;
    }

    /**
	 * Setter for the name
	 * @param name The String corresponding to the name of the User
	 */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
	 * Setter for the password
	 * @param name The String corresponding to the password of the User
	 */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
	 * String representation of a User object instance
	 * @return The String corresponding to the string representation of a User instance
	 */
    public String toString()
    {
        return "Name : " + name + " \n Password : " + password + " \n";
    }
}