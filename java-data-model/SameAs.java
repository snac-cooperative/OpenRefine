

/**
 * Snac SameAs File
 *
 * Contains the data class for sameAs or otherRecordID information
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
 * SameAs data storage class
 *
 * @author Robbie Hott
 *
 */
public class SameAs extends AbstractData {

    /**
     * @var string text of this sameAs or otherRecordID
     */
    private String text;

    /**
     * @var string URI of this sameAs or otherRecordID
     */
    private String uri;

    /**
     * @var snacdataTerm Type of this sameAs or otherRecordID
     */
    private Term type;


    /**
     * Constructor
     */
    public SameAs() {
        super();
        this.setDataType("SameAs");
        this.setMaxDateCount(0);
    }

    /**
     * Get the text of this sameAs or otherRecordID
     *
     * @return string The description text/xml
     *
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * Get the URI of this sameAs or otherRecordID
     *
     * @return string The uri of this sameAs or otherRecordID
     */
    public String getURI() {
        return this.uri;
    }

    /**
     * Get the type of this sameAs or otherRecordID
     *
     * @return snacdataTerm The type of this sameAs or otherRecordID
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
        return "Same As: " + this.text;
    }

    /**
     * Set the text/xml of this SameAs
     */
    public void setText(String text) {

        this.text = text;
    }

    /**
     * Set the URI of this sameAs or otherRecordID
     */
    public void setURI(String uri) {
        this.uri = uri;
    }

    /**
     * Set the type of this sameAs or otherRecordID
     */
    public void setType(Term type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof SameAs))
            return false;

        if (!super.equals(other))
            return false;

        SameAs s = (SameAs) other;

        if ((this.getText() != null && ! this.getText().equals(s.getText())) ||
                (this.getText() == null && s.getText() != null))
            return false;
        if ((this.getURI() != null && ! this.getURI().equals(s.getURI())) ||
                (this.getURI() == null && s.getURI() != null))
            return false;

        if ((this.getType() != null && ! this.getType().equals(s.getType())) ||
                 (this.getType() == null && s.getType() != null))
            return false;

        return true;
    }
}
