

/**
 * Parent class of Role, Privilege, and Group
 *
 * User's are authorized for system functions based on having privileges. Privileges are grouped inside Roles.
 *
 * Groups are simply groups of users to simplify workflow.
 *
 * License:
 *
 * @author Tom Laudeman
 * @license https://opensource.org/licenses/BSD-3-Clause BSD 3-Clause
 * @copyright 2016 the Rector and Visitors of the University of Virginia, and
 *            the Regents of the University of California
 */
package org.snaccooperative.data;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Abstract class for various grouping of user meta data
 *
 * Fundamental class for privilege, group, role. Role has a list of privileges.
 *
 * @author Tom Laudeman
 *
 */

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AbstractGrouping {

    /**
     * @var string dataType The data type of this object.
     *
     * This should be overwritten by any inheriting/child class
     */
    protected String dataType;

    /**
     * Object id
     *
     * @var integer Object id from sql object.id record id
     */
    protected int id = 0;

    /**
     * Object label
     *
     * Short label that identifies this object
     *
     * @var string Object label
     */
    protected String label;

    /**
     * Object description
     *
     * Description of what this object authorizes, and the purpose. One sentence or phrase.
     *
     * @var string Description of this object.
     */
    protected String description;

    /**
     * Get id
     * @return integer id
     */
    public int getID()
    {
        return this.id;
    }

    /**
     * Set id
     */
    public void setID(int id)
    {
        this.id = id;
    }

    /**
     * Get label
     */
    public String getLabel()
    {
        return this.label;
    }

    /**
     * Set label
     */
    public void setLabel(String label)
    {
        this.label = label;
    }

    /**
     * Get description
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Set description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }


}
