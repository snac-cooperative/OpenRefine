
/**
 * Language File
 *
 * Contains the data class for the languages
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
 * Language Class
 *
 *  See the abstract parent class for common methods setDBInfo() and getDBInfo().
 *
 * Stores the data related to an individual Constellation's language and script.
 *
 * @author Robbie Hott
 *
 */
public class Language extends AbstractData {

    /**
     * @var snacdataTerm Language, a controlled vocabulary term object
     */
    private Term language = null;

    /**
     * @var snacdataTerm Script, a controlled vocabulary term object.
     */
    private Term script = null;

    /**
     * @var string Vocabulary source for the language. A simple string.
     */
    private String vocabularySource = null;

    /**
     * @var string Note attached to language. A simple string.
     */
    private String note = null;

    /**
     * Constructor
     *
     */
    public Language() {
        super();
        this.setMaxDateCount(0);
    }

    /**
     * Get the language controlled vocab term
     *
     * @return snacdataTerm Language controlled vocabulary term
     *
     */
    public Term getLanguage()
    {
        return this.language;
    }

    /**
     * Get the script controlled vocab term
     *
     * @return snacdataTerm Script controlled vocabulary term
     *
     */
    public Term getScript()
    {
        return this.script;
    }

    /**
     * Get the vocabulary source for this language
     *
     * @return string Vocabulary source for the language
     */
    public String getVocabularySource()
    {
        return this.vocabularySource;
    }

    /**
     * Get the descriptive note
     *
     * @return string Note attached to occupation
     *
     */
    public String getNote()
    {
        return this.note;
    }

    /**
     * Check to see if this language is empty
     *
     * @return boolean true if the language has nothing set, false otherwise
     */
    public boolean isEmpty() {
        if (this.language == null && this.script == null && this.vocabularySource == null
            && this.note == null)
            return true;
        else
            return false;
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
        return "Language: " + (this.language != null ? this.language.getTerm() + " " : "") + (this.script != null ? "("+this.script.getTerm()+")" : "");
    }

    /**
     * Set the language controlled vocabulary name
     */
    public void setLanguage(Term language) {
        this.language = language;
    }

    /**
     * Set the script controlled vocabulary name
     */
    public void setScript(Term script) {
        this.script = script;
    }

    /**
     * Set the vocabulary source.
     */
    public void setVocabularySource(String vocab) {
        this.vocabularySource = vocab;
    }

    /**
     * Set the descriptive note for this language
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {
        if (other == null || ! (other instanceof Language))
            return false;

        if (! super.equals(other))
            return false;

        Language l = (Language) other;
        if ( (this.getVocabularySource() != null && !this.getVocabularySource().equals(l.getVocabularySource())) ||
                (this.getVocabularySource() == null && l.getVocabularySource() != null))
            return false;
        if ( (this.getNote() != null && !this.getNote().equals(l.getNote())) ||
                (this.getNote() == null && l.getNote() != null))
            return false;

        if ( (this.getScript() != null && !this.getScript().equals(l.getScript())) ||
                (this.getScript() == null && l.getScript() != null))
            return false;

        if ( (this.getLanguage() != null && !this.getLanguage().equals(l.getLanguage())) ||
                (this.getLanguage() == null && l.getLanguage() != null))
            return false;

        return true;
    }

}
