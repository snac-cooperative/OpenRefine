

/**
 * Snac BiogHist File
 *
 * Contains the data class for biographical histories
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
 * BiogHist data storage class
 *
 * @author Robbie Hott
 *
 */
public class BiogHist extends AbstractData {

    /**
     *
     * @var snacdataLanguage The language this biogHist was written in
     */
    private Language language;

    /**
     * @var string Text/XML contents of this biogHist.
     */
    private String text;

    /**
     * Constructor.
     *
     * Mostly this exists to call setMaxDateCount() to a reasonable number for this class.
     */
    public BiogHist() {
        super();
        this.setDataType("BiogHist");
        this.setMaxDateCount(0);
    }


    /**
     * Get the language this biogHist was written in
     *
     * @return snacdataLanguage Language of this BiogHist
     *
     */
    public Language getLanguage()
    {
        return this.language;
    }

    /**
     * Get the text/xml of this biogHist
     *
     * @return string The full biogHist
     *
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * Append to this BiogHist
     *
     * Appends the information in the given biog hist to this one.  The text field is appended
     * to this one, the SCMs are merged, and if there is no language in this biogHist,
     * then the language is copied.  Otherwise, the given biogHist's language will be dropped.
     *
     * @param  snacdataBiogHist biogHist BiogHist to append
     */
    public void append(BiogHist biogHist) {
        if (biogHist == null) {
            return;
        }

        // Append the text objects
        this.text += biogHist.text;

        // Combine SCMs
        if (biogHist.snacControlMetadata != null) {
            for (SNACControlMetadata scm : biogHist.snacControlMetadata) {
                this.snacControlMetadata.add(scm);
            }
        }

        // If this biogHist's language is null or empty, pull in the given biogHist's language
        if (this.language == null || this.language.isEmpty()) {
            this.language = biogHist.language;
        }
    }

    /**
     * Set the language of this BiogHist.
     */
    public void setLanguage(Language language) {

        this.language = language;
    }

    /**
     * Set the text/xml of this BiogHist
     */
    public void setText(String text) {

        this.text = text;
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
        return "BiogHist: " + this.text;
    }

    /**
     *
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || !(other instanceof BiogHist))
            return false;

        if (!super.equals(other))
            return false;

        BiogHist b = (BiogHist) other;

        if ((this.getText() != null && !this.getText().equals(b.getText())) ||
                (this.getText() == null && b.getText() != null))
            return false;

        if ((this.getLanguage() != null && !this.getLanguage().equals(b.getLanguage())) ||
                (this.getLanguage() == null && b.getLanguage() != null))
            return false;

        return true;
    }

    /**
     * Cleanse all sub-elements
     *
     * Removes the ID and Version from sub-elements and updates the operation to be
     * INSERT.  If the operation is specified by the parameter, this method
     * will use that operation instead of INSERT.
     */
    public void cleanseSubElements(String operation) {
        String newOperation = AbstractData.OPERATION_INSERT;
        if (operation != null) {
            newOperation = operation;
        }

        super.cleanseSubElements(newOperation);

        if (this.language != null) {
            this.language.setID(0);
            this.language.setVersion(0);
            this.language.setOperation(newOperation);
            this.language.cleanseSubElements(newOperation);
        }
    }
}
