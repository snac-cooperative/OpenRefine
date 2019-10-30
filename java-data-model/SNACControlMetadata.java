

/**
 * SNAC Control Metadata Object class.
 *
 * Contains the snac control metadata class.
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
 * SNAC Control Metadata class
 *
 * This class contains the snac control metadata block associated with any other piece of
 * data.  It is included in the AbstractData class and is therefore allowed on
 * any piece of data that inherits from the abstract class.
 *
 * @author Robbie Hott
 */
public class SNACControlMetadata extends AbstractData {


    /**
     * @var snacdataSource the citation source object
     */
    private Source citation;

    /**
     * @var string sub citation, the exact location in the source
     */
    private String subCitation;

    /**
     * Source Data
     *
     * We put original strings in here. For example an original place string.
     *
     * @var string source data, the "as recorded" data: exactly what was found in the source
     */
    private String sourceData;

    /**
     * @var snacdataTerm the descriptive rule associated with formulating the data
     */
    private Term descriptiveRule;

    /**
     * @var snacdataLanguage the language associated with this citation/data
     */
    private Language language;

    /**
     * @var string human-readable note associated with this data/metadata/citation
     */
    private String note;

    /**
     * @var string human-readable string representing the object pointed to by this SCM
     */
    private String object;

    /**
     * Constructor
     *
     * The associative array data varies depending on the object being created, but is always consistent
     * between toArray() and fromArray() for each object. By and large, outside an object, nothing cares about
     * the internal structure of the data array. The standard way to create one of these objects is to
     * instantiate with no data, and then use the getters to set the object's properties.
     *
     */
    public SNACControlMetadata() {
        super();
        this.setMaxDateCount(0);
        this.snacControlMetadata = null;
    }

    /**
     * Get the citation
     *
     * @return snacdataSource the citation source
     */
    public Source getCitation() {
        return this.citation;
    }

    /**
     * Get the subcitation
     *
     * @return string sub citation, the exact location in the source
     */
    public String getSubCitation() {
        return this.subCitation;
    }

    /**
     * Get the source data
     *
     * @return string source data, the "as recorded" data: exactly what was found in the source
     */
    public String getSourceData() {
        return this.sourceData;
    }

    /**
     * Get the descriptive rule
     *
     * @return snacdataTerm the descriptive rule associated with formulating the data
     */
    public Term getDescriptiveRule() {
        return this.descriptiveRule;
    }

    /**
     * Get the language
     *
     * @return snacdataLanguage the language associated with this citation/data
     */
    public Language getLanguage() {
        return this.language;
    }

    /**
     * Get the human readable note
     *
     * @return string human-readable note associated with this data/metadata/citation
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Get the human-readable object string
     *
     * @return string The human-readable object string this SCM is attached to
     */
    public String getObject() {
        return this.object;
    }

    /**
     * Set the human-readable object string
     *
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Set the citation
     *
     */
    public void setCitation(Source citation) {
        this.citation = citation;
    }

    /**
     * Set the subcitation
     *
     */
    public void setSubCitation(String subCitation) {
        this.subCitation = subCitation;
    }

    /**
     * Set the source data
     *
     */
    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    /**
     * Set the descriptive rule
     *
     */
    public void setDescriptiveRule(Term rule) {
        this.descriptiveRule = rule;
    }

    /**
     * Set the language
     *
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Set the human readable note
     *
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     *
     *
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof SNACControlMetadata))
            return false;

        if (! super.equals(other))
            return false;

        SNACControlMetadata s = (SNACControlMetadata) other;
        if ((this.getSubCitation() != null && ! this.getSubCitation().equals(s.getSubCitation())) ||
                (this.getSubCitation() == null && s.getSubCitation() != null))
            return false;
        if ((this.getSourceData() != null && ! this.getSourceData().equals(s.getSourceData())) ||
                (this.getSourceData() == null && s.getSourceData() != null))
            return false;
        if ((this.getNote() != null && ! this.getNote().equals(s.getNote())) ||
                (this.getNote() == null && s.getNote() != null))
            return false;

        // Citations are special. They are Source objects, but they may not be completely filled in.  In fact, the only thing we may know
        // about them within an SCM is their ID.  So, for equality, we may only check ID.
        if ((this.getCitation() != null && s.getCitation() == null) ||
            (this.getCitation() == null && s.getCitation() != null) ||
            (this.getCitation() != null && s.getCitation() != null && this.getCitation().getID() != s.getCitation().getID()))
            return false;

        if ((this.getDescriptiveRule() != null && !this.getDescriptiveRule().equals(s.getDescriptiveRule())) ||
                 (this.getDescriptiveRule() == null && s.getDescriptiveRule() != null))
            return false;

        if ((this.getLanguage() != null && ! this.getLanguage().equals(s.getLanguage())) ||
                 (this.getLanguage() == null && s.getLanguage() != null))
            return false;

        return true;
    }

}
