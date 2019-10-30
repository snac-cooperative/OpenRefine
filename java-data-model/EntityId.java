

/**
 * Snac EntityId File
 *
 * Contains the data class for entityId information
 *
 * License:
 *
 *
 * @author Robbie Hott
 * @license https://opensource.org/licenses/BSD-3-Clause BSD 3-Clause
 * @copyright 2015 the Rector and Visitors of the University of Virginia, and
 *            the Regents of the University of California
 */
package org.snaccooperative.data;

/**
 * EntityId data storage class
 *
 * @author Robbie Hott
 *
 */
public class EntityId extends AbstractData {

    /**
     * @var string text of this entityId
     */
    private String text;

    /**
     * @var string URI of this entityId
     */
    private String uri;

    /**
     * @var snacdataTerm Type of this entityId
     */
    private Term type;


    /**
     * Constructor
     */
    public EntityId() {
        super();
        this.setDataType("EntityId");
        this.setMaxDateCount(0);
    }

    /**
     * Get the text of this entityId
     *
     * @return string The description text/xml
     *
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * Get the URI of this entityId
     *
     * @return string The uri of this entityId
     */
    public String getURI() {
        return this.uri;
    }

    /**
     * Get the type of this entityId
     *
     * @return snacdataTerm The type of this entityId
     */
    public Term getType() {
        return this.type;
    }

    /**
     * To String
     *
     * Converts this object to a human-readable summary string.  This is enough to identify
     * the object on sight, but not enough to discern programmatically.
     *
     * @return string A human-readable summary string of this object
     */
    public String toString() {
        return "EntityID: " + this.text;
    }


    /**
     * Set the text/xml of this EntityId
     */
    public void setText(String text) {

        this.text = text;
    }

    /**
     * Set the URI of this entityId
     */
    public void setURI(String uri) {
        this.uri = uri;
    }

    /**
     * Set the type of this entityId
     */
    public void setType(Term type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof EntityId))
            return false;

        if (!super.equals(other))
            return false;

        EntityId e = (EntityId) other;
        if ((this.getText() != null && !this.getText().equals(e.getText())) ||
                (this.getText() == null && e.getText() != null))
            return false;
        if ((this.getURI() != null && !this.getURI().equals(e.getURI())) ||
                (this.getURI() == null && e.getURI() != null))
            return false;

        if ((this.getType() != null && !this.getType().equals(e.getType())) ||
                 (this.getType() == null && e.getType() != null))
            return false;

        return true;
    }
}
