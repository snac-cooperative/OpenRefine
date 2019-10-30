
/**
 * Snac Source File
 *
 * Contains the data class for source information
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
 * Source
 *
 * A "source" is a citation source, and has qualities of an authority file although every
 * source is independent, even if it seems to be a duplicate.  This appears to derive from
 * /eac-cpf/control/source in the CPF. Going forward we use it for all sources.  For example,
 * SNACControlMetadata->citation is a Source object. Constellation->sources is a list of sources.
 *
 * @author Robbie Hott
 *
 */
public class Source extends AbstractData {

    /**
     * Language
     */
    private Language language;

    /**
     * @var string Display name of this source.
     */
    private String displayName;

    /**
     * @var string Text of this source.
     */
    private String text;

    /**
     * @var string Note related to this source
     */
    private String note;

    /**
     * @var string URI of this source
     */
    private String uri;

    /**
     * @var string Citation of this source.
     */
    private String citation;

    /**
     * @var snacdataTerm Type of this source
     */
    private Term type;

    /**
     * Constructor
     *
     */
    public Source() {
        super();
        this.setMaxDateCount(0);
    }

    /**
     * Get Language
     *
     * Get the language this source was written in
     *
     * @return snacdataLanguage Language of this source
     *
     */
    public Language getLanguage()
    {
        return this.language;
    }

    /**
     * Get the note of this source
     *
     * @return string The note attached to this source
     *
     */
    public String getNote()
    {
        return this.note;
    }

    /**
     * Get the text of this source
     *
     * @return string The description text/xml
     *
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * Get the citation of this source
     *
     * @return string The citation 
     *
     */
    public String getCitation()
    {
        return this.citation;
    }


    /**
     * Get the display name of this source
     *
     * @return string The display name of the source
     *
     */
    public String getDisplayName()
    {
        return this.displayName;
    }

    /**
     * Get the URI of this source
     *
     * @return string The uri of this source
     */
    public String getURI() {
        return this.uri;
    }

    /**
     * Get the type of this source
     *
     * @return snacdataTerm The type of this source
     */
    public Term getType() {
        return this.type;
    }

    /**
     * Set the language of this source
     */
    public void setLanguage(Language language) {

        this.language = language;
    }

    /**
     * Set the text/xml of this Source
     */
    public void setText(String text) {

        this.text = text;
    }

    /**
     * Set the citation of this Source
     */
    public void setCitation(String citation) {

        this.citation = citation;
    }

    /**
     * Set the display name of this Source
     */
    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    /**
     * Set the note of this Source
     */
    public void setNote(String note) {

        this.note = note;
    }

    /**
     * Set the URI of this source
     */
    public void setURI(String uri) {
        this.uri = uri;
    }

    /**
     * Set the type of this source
     */
    public void setType(Term type) {
        this.type = type;
    }

    /**
     *
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof Source))
            return false;

        if (!super.equals(other))
            return false;

        Source s = (Source) other;
        if ((this.getDisplayName() != null && ! this.getDisplayName().equals(s.getDisplayName())) ||
                (this.getDisplayName() == null && s.getDisplayName() != null))
            return false;
        if ((this.getText() != null && ! this.getText().equals(s.getText())) ||
                (this.getText() == null && s.getText() != null))
            return false;
        if ((this.getURI() != null && ! this.getURI().equals(s.getURI())) ||
                (this.getURI() == null && s.getURI() != null))
            return false;
        if ((this.getNote() != null && ! this.getNote().equals(s.getNote())) ||
                (this.getNote() == null && s.getNote() != null))
            return false;

        // Right now, we will ignore Type, since it is always "simple"
        //if ((this.getType() != null && ! this.getType()->equals(other->getType())) ||
        //         (this.getType() == null && other->getType() != null))
        //    return false;

        if ((this.getLanguage() != null && ! this.getLanguage().equals(s.getLanguage())) ||
                 (this.getLanguage() == null && s.getLanguage() != null))
            return false;

        return true;
    }

}
