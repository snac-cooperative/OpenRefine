
/**
 * Resource Relation Origination Name File
 *
 * Contains the data class for the originators (creators) of resource relations
 *
 * License:
 *
 *
 * @author Tom Laudeman
 * @author Robbie Hott
 * @license https://opensource.org/licenses/BSD-3-Clause BSD 3-Clause
 * @copyright 2015 the Rector and Visitors of the University of Virginia, and
 *            the Regents of the University of California
 */
package org.snaccooperative.data;

/**
 * Resource Relation Origination Name
 *
 * Stores the name (string) and eventually the ic_id of the creator
 *
 * @author Tom Laudeman
 * @author Robbie Hott
 *
 */
public class OriginationName extends AbstractData {

    /**
     * @var string Name of the originator (creator).
     *
     * A simple string.
     */
    private String name = null;

    /**
     * Constructor
     */
    public OriginationName() {
        super();
        this.setDataType("OriginationName");
        this.setMaxDateCount(0);
    }


    /**
     * Get the name
     *
     * @return string Name of the originator (creator)
     *
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Set the name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || !(other instanceof OriginationName))
            return false;

        if (!super.equals(other))
            return false;

        OriginationName o = (OriginationName) other;
        if ((this.getName() != null && !this.getName().equals(o.getName())) ||
                (this.getName() == null && o.getName() != null))
            return false;
        return true;
    }
}
