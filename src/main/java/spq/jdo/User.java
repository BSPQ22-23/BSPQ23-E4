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
package spq.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PrimaryKey;
/**
 * Definition of a User.
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class User
{	
	
	
	/**
	 * This variable represents the name of a User
	 */
	
	@PrimaryKey
    String name=null;
   
	/**
	 * This variable represents the name of a User
	 */
    
	String password=null;
    
	//Purse of the User
    
	double purse=0;
    
	//Type of the user 0 is client ,1 is admin
    
	int type;
    
    /**
     * A user of our web page
	 * @param name name of the user
	 * @param password password of the user
     */
    public User(String name, String password,double purse,int type)
    {
        this.name = name;
        this.password = password;
        this.purse=purse;
        this.type=type;
        
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
        return "Name = " + name + ", Password = " + password + ", Your money = "+purse;
    }

	public double getPurse() {
		return purse;
	}

	public void setPurse(double purse) {
		this.purse = purse;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean canBuyProduct(double productPrice) {
		return this.purse >= productPrice;
	}

	public void increasePurse(double amount) {
		this.purse = this.purse + amount;
	}

	public void reducePurse(double amount) {
		this.purse = this.purse - amount;
	}
	
}