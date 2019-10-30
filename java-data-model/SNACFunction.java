

/**
 * Snac Function File
 *
 * Contains the data class for functions
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
 * Function data storage class
 *
 *  See the abstract parent class for common methods setDBInfo() and getDBInfo().
 *
 * @author Robbie Hott
 *
 */
public class SNACFunction extends AbstractData {

    /**
     * Vocabulary Term
     *
     * From EAC-CPF tag(s):
     *
     * * function/term
     *
     * @var snacdataTerm Function controlled vocabulary term
     */
    private Term term;

    /**
     * Type of Function
     *
     * From EAC-CPF tag(s):
     *
     * * function/@localType
     *
     * @var snacdataTerm Type of the function
     */
    private Term type;


    /**
     * Descriptive Note
     *
     * From EAC-CPF tag(s):
     *
     * * function/descriptiveNote
     *
     * @var string Descriptive note for the function
     */
    private String note;

    /**
     * Vocabulary Source
     *
     * From EAC-CPF tag(s):
     *
     * * function/term/@vocabularySource
     *
     * @var string Vocabulary source for the function
     */
    private String vocabularySource = null;

    /**
     * Constructor
     *
     * Functions may only have one date object.
     */
    public SNACFunction() {
        super();
        this.setDataType("SNACFunction");
        this.setMaxDateCount(1);
    }


    /**
     * Get the Term for this function
     *
     * * function/term
     *
     * @return snacdataTerm Function controlled vocabulary term
     *
     */
    public Term getTerm()
    {
        return this.term;
    }

    /**
     * Get the type of this function
     *
     * * function/@localType
     *
     * @return snacdataTerm Type of the function
     *
     */
    public Term getType()
    {
        return this.type;
    }


    /**
     * Get Descriptive Note
     *
     * Get the human-readable descriptive note for this function
     *
     * * function/descriptiveNote
     *
     * @return string Descriptive note for the function
     *
     */
    public String getNote()
    {
        return this.note;
    }

    /**
     * Get the vocabulary source
     *
     * * function/term/@vocabularySource
     *
     * @return string Vocabulary source for the function
     *
     */
    public String getVocabularySource()
    {
        return this.vocabularySource;
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
        return "Function: " + this.term.getTerm();
    }

    /**
     * Set the term of this function
     *
     * This comes from the controlled vocabulary
     */
    public void setTerm(Term term) {

        this.term = term;
    }

    /**
     * Set the type of this function
     */
    public void setType(Term type) {

        this.type = type;
    }

    /**
     * Set the vocabulary source
     */
    public void setVocabularySource(String vocab) {

        this.vocabularySource = vocab;
    }

    /**
     * Set the descriptive note for this function
     */
    public void setNote(String note) {

        this.note = note;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof SNACFunction))
            return false;

        if (!super.equals(other))
            return false;

        SNACFunction f = (SNACFunction) other;

        if ((this.getVocabularySource() != null && ! this.getVocabularySource().equals(f.getVocabularySource())) ||
                (this.getVocabularySource() == null && f.getVocabularySource() != null))
            return false;
        if ((this.getNote() != null && ! this.getNote().equals(f.getNote())) ||
                (this.getNote() == null && f.getNote() != null))
            return false;

        if ((this.getTerm() != null && ! this.getTerm().equals(f.getTerm())) ||
                 (this.getTerm() == null && f.getTerm() != null))
            return false;
        if ((this.getType() != null && ! this.getType().equals(f.getType())) ||
                 (this.getType() == null && f.getType() != null))
            return false;

        return true;
    }
}
