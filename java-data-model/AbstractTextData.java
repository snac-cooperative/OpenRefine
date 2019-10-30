

/**
 * Abstract Class that contains methods to handle text- or xml-only containing
 * objects.  For example, StructureOrGenealogy, Mandate, etc, which must be versioned
 * but also contain text.
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
 * Text-holding Abstract Class
 *
 * Abstract class that extends AbstractData and also holds a text string
 *
 * @author Robbie Hott
 */
public abstract class AbstractTextData extends AbstractData {


    /**
     * var string Text of this object
     */
    protected String text;

    /**
     * Get the text of this object
     */
    public String getText() {
        return this.text;
    }

    /**
     * Set the text of this object
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * To String
     *
     * Converts this object to a human-readable summary string.  This is enough to identify
     * the object on sight, but not enough to discern programmatically.
     */
    public String toString() {
        return this.dataType + ": " + this.getText();
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof AbstractTextData))
            return false;

        if (!super.equals(other))
            return false;

        AbstractTextData a = (AbstractTextData) other;
        if ((this.getText() != null && ! this.getText().equals(a.getText())) ||
                (this.getText() == null && a.getText() != null))
            return false;

        return true;
    }

}
