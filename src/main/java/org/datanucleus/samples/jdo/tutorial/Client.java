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
 * Definition of a Client. Extends basic Product class.
 * @author mikel
 *
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Client extends User
{
	/**
	 * This variable represents the money of the client
	 */
	protected double purse=0.0;

	/**
	 * Void constructor of Client
	 */
    protected Client()
    {
        super();
    }

	/**
	 * A client of our web page
	 * @param name name of the client
	 * @param password password of the client
	 * @param purse money available of the Client
	 */ 
    public Client(String name, String password, double purse)
    {
        super(name,password);
        this.purse = purse;
    }

	/**
	 * Getter for the purse (money available) of the client
	 * @return The double corresponding to the purse of the client
	 */
    public double getPurse()
    {
        return purse;
    }

	/**
	 * Setter for the purse
	 * @param purse The double corresponding to the purse of the client
	 */
    public void setPurse(double purse)
    {
        this.purse = purse;
    }

	/**
	 * String representation of a Client object instance
	 * @return The String corresponding to the string representation of a Client instance
	 */
	@Override
    public String toString()
    {
        return "Purse : " + purse + " \n";
    }
}