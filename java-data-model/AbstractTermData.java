

/**
 * Abstract Class that contains methods to handle controlled vocab-containing
 * objects.  For example, Gender, OtherRecordIDs, etc, which must be versioned
 * but also contain links to controlled vocabularies.
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
 * Term-holding abstract class
 *
 * Abstract class that extends AbstractData and also hold a Term object.  Extend this to use it.
 *
 * @author Robbie Hott
 */
public abstract class AbstractTermData extends AbstractData {

    /**
     * @var snacdataTerm term The term for this object
     */
    protected Term term;

    /**
     * Get the term of this object
     */
    public Term getTerm() {
        return this.term;
    }

    /**
     * Set the term of this object
     */
    public void setTerm(Term term) {
        this.term = term;
    }

    /**
     * To String
     *
     * Converts this object to a human-readable summary string.  This is enough to identify
     * the object on sight, but not enough to discern programmatically.
     */
    public String toString() {
        return this.dataType + ": " + this.getTerm().getTerm();
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {


        if (other == null || !(other instanceof AbstractTermData))
            return false;

        AbstractTermData a = (AbstractTermData) other;

        if (!a.getDataType().equals(this.getDataType()))
            return false;

        if (!super.equals(other))
            return false;

        if ((this.getTerm() != null && !this.getTerm().equals(a.getTerm())) ||
                (this.getTerm() == null && a.getTerm() != null))
            return false;

        return true;
    }

}
