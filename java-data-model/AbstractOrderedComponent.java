

/**
 * AbstractOrderedComponent Class file
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
 * Abstract Ordered Component Class
 *
 * This class contains an abstract implementation of an ordered (term, text) component
 *
 * @author Robbie Hott
 */
public abstract class AbstractOrderedComponent extends AbstractData {

    /**
     * string text The text of the component
     */
    private String text = null;

    /**
     * snacdataTerm type The term associated with this component
     */
    private Term type = null;

    /**
     * int order The ordering of this component in the list
     */
    private int order = 0;

    /**
     * Get Component Text
     *
     * Get the text of this component.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Get Component Type
     *
     * Get type of this component, i.e. what it part it is
     */
    public Term getType() {
        return this.type;
    }

    /**
     * Get Component Ordering
     *
     * Get the order within the list that this component was entered.
     */
    public int getOrder() {
        return this.order;
    }

    /**
     * Set Component Text
     *
     * Set the text of this component.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Set Component Type
     *
     * Set the type of component
     */
    public void setType(Term type) {
        this.type = type;
    }

    /**
     * Set Component Order
     *
     * Set the order of this component within the list.  This is an integer index.
     */
    public void setOrder(int i) {
        this.order = i;
    }


    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || !(other instanceof AbstractOrderedComponent))
            return false;

        AbstractOrderedComponent a = (AbstractOrderedComponent) other;

        if (!a.getDataType().equals(this.getDataType()))
            return false;

        if (! super.equals(other))
            return false;

        if ((this.getText() != null && ! this.getText().equals(a.getText())) ||
                (this.getText() == null && a.getText() != null))
            return false;
        if (this.getOrder() != a.getOrder())
            return false;

        if ((this.getType() != null && ! this.getType().equals(a.getType())) ||
                 (this.getType() == null && a.getType() != null))
            return false;
        return true;
    }
}
