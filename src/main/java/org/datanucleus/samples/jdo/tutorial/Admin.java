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
 * Definition of a Admin. Extends basic Product class.
 * @author mikel
 *
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Admin extends User
{
	/**
	 * This variable represents the money of the Admin
	 */
	protected boolean status = false;

	/**
	 * Void constructor of Admin
	 */
    protected Admin()
    {
        super();
    }

	/**
	 * A Admin of our web page
	 * @param name name of the Admin
	 * @param password password of the Admin
	 * @param status status of the Admin
	 */ 
    public Admin(String name, String password, boolean status)
    {
        super(name,password);
        this.status = status;
    }

	/**
	 * Getter for the status of the Admin
	 * @return The boolean corresponding to the status of the Admin
	 */
    public boolean getStatus()
    {
        return status;
    }

	/**
	 * Setter for the status
	 * @param status The boolean corresponding to the status of the Admin
	 */
    public void setStatus(boolean status)
    {
        this.status = status;
    }

	/**
	 * String representation of a Admin object instance
	 * @return The String corresponding to the string representation of a Admin instance
	 */
	@Override
    public String toString()
    {
        return "Status : " + status + " \n";
    }
}