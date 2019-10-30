
/**
 * Occupation File
 *
 * Contains the data class for the occupations
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
 * Occupation Class
 *
 *  See the abstract parent class for common methods setDBInfo() and getDBInfo().
 *
 * Stores the data related to an individual Constellation's occupation.
 *
 * @author Robbie Hott
 *
 */
public class Occupation extends AbstractData {

    /**
     * Occupation Term
     *
     * From EAC-CPF tag(s):
     *
     * * occupation/term
     *
     * @var snacdataTerm Occupation controlled vocabulary term
     */
    private Term term = null;

    /**
     * Vocabulary Source
     *
     * From EAC-CPF tag(s):
     *
     * occupation/term/@vocabularySource
     *
     * This example for <function> is similar to <occupation>
     *
     * <function>
     *    <term vocabularySource="d3nyui3o8w--11y7jgy8q3wnt">notaire à paris</term>
     *    <dateRange>
     *        <fromDate standardDate="1578-01-01">1er janvier 1578</fromDate>
     *        <toDate standardDate="1613-10-22">22 octobre 1613</toDate>
     *    </dateRange>
     * </function>
     *
     *
     * The vocabulary source. These values come from a controlled vocabulary, but so far, they are not
     * well defined. For example: d699msirr1g-3naumnfaswc
     *
     *
     * @var string Vocabulary source for the occupation
     */
    private String vocabularySource = null;

    /**
     * Descriptive Note
     *
     * From EAC-CPF tag(s):
     *
     * * occupation/descriptiveNote
     *
     * @var string Note attached to occupation
     */
    private String note = null;

    /**
     * Constructor for the class. See the abstract parent class for common methods setDBInfo() and getDBInfo().
     *
     * A setMaxDateCount(1) means a single date object.
     */
    public Occupation() {
        super();
        this.setDataType("Occupation");
        this.setMaxDateCount(1);
    }

    /**
     * Get the Occupation Term for this occupation
     *
     * @return snacdataTerm Occupation controlled vocabulary term
     *
     */
    public Term getTerm()
    {
        return this.term;
    }

    /**
     * Get the vocabulary source for this occupation
     *
     * @return string Vocabulary source for the occupation
     */
    public String getVocabularySource()
    {
        return this.vocabularySource;
    }

    /**
     * Get the human readable descriptive note attached to this occupation
     *
     * @return string Note attached to occupation
     *
     */
    public String getNote()
    {
        return this.note;
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
        return "Occupation: " + this.term.getTerm();
    }

    /**
     * Set the occupation controlled vocabulary term
     */
    public void setTerm(Term term) {
        this.term = term;
    }


    /**
     * Set the vocabulary source
     *
     * These values come from a controlled vocabulary, but so far, they are not
     * well defined. For example: d699msirr1g-3naumnfaswc
     */
    public void setVocabularySource(String vocab) {
        this.vocabularySource = vocab;
    }

    /**
     * Set the descriptive note for this occupation
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof Occupation))
            return false;

        if (! super.equals(other))
            return false;

        Occupation o = (Occupation) other;
        if ((this.getVocabularySource() != null && !this.getVocabularySource().equals(o.getVocabularySource())) ||
                (this.getVocabularySource() == null && o.getVocabularySource() != null))
            return false;
        if ((this.getNote() != null && !this.getNote().equals(o.getNote())) ||
                (this.getNote() == null && o.getNote() != null))
            return false;

        if ((this.getTerm() != null && !this.getTerm().equals(o.getTerm())) ||
                (this.getTerm() == null && o.getTerm() != null))
            return false;

        return true;
    }


}
