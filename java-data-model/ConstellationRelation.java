
/**
 * Constellation Relation File
 *
 * Contains the data class for the constellation relations.
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
 * Constellation Relationship.  See the abstract parent class for common methods setDBInfo() and getDBInfo().
 *
 * Data class to store the information about a relationship between Constellations
 *
 * @author Robbie Hott
 *
 */
public class ConstellationRelation extends AbstractData {

    /**
     * Source Constellation ID
     *
     * Postgres ID (source)
     *
     * Add commentary. Why is this necessary? This should be parent::getARK() and if that is true, we can get
     * it at any time.
     *
     * @var int Source constellation ID
     */
    private int sourceConstellation;

    /**
     * Target Constellation ID
     *
     * Postgres ID (target)
     *
     * @var int Target constellation ID
     */
    private int targetConstellation;

    /**
     * ArkID of source
     *
     * @var string Source constellation ARK ID
     */
    private String sourceArkID = null;

    /**
     * ArkID of the target
     *
     * From EAC-CPF tag(s):
     *
     * * cpfRelation/@href
     *
     * @var string Target constellation ARK ID
     */
    private String targetArkID = null;

    /**
     * Entity type of the target
     *
     * From EAC-CPF tag(s):
     *
     * * cpfRelation/@role
     *
     * @var snacdataTerm Target entity type
     */
    private Term targetEntityType = null;

    /**
     * Type of the relation
     *
     * From EAC-CPF tag(s):
     *
     * * cpfRelation/@arcrole
     *
     * @var snacdataTerm Type of the constellation
     */
    private Term type = null;

    /**
     * Alternate Type (unused)
     *
     * From EAC-CPF tag(s):
     *
     * * cpfRelation/@type cpfRelation@xlink:type
     *
     * The only value this ever has is "simple". Daniel says not to save it, and implicitly hard code when
     * serializing export.
     *
     * @var snacdataTerm Alternate type
     */
    private Term altType = null;

    /**
     * Type of the relation
     *
     * From EAC-CPF tag(s):
     *
     * * cpfRelation/@cpfRelationType
     *
     * @var snacdataTerm CPF Relation Type
     */
    private Term cpfRelationType = null;

    /**
     * Content in the relation tag
     *
     * From EAC-CPF tag(s):
     *
     * * cpfRelation/relationEntry
     *
     * @var string Content of the relation
     */
    private String content = null;

    /**
     * Descriptive Note
     *
     * Descriptive note for the relation.
     *
     * From EAC-CPF tag(s):
     *
     * * cpfRelation/descriptiveNote
     *
     * @var string Note attached to relation
     */
    private String note = null;

    /**
     * Constructor
     */
    public ConstellationRelation() {
        super();
        this.setDataType("Relation");
        this.setMaxDateCount(1);
    }

    /**
     * Get the Source Constellation's ID
     *
     * @return int Source constellation ID
     *
     */
    public int getSourceConstellation()
    {
        return this.sourceConstellation;
    }

    /**
     * Set the Source Constellation's ID
     */
    public void setSourceConstellation(int sourceConstellation)
    {
        this.sourceConstellation = sourceConstellation;
    }


    /**
     * Get the Target Constellation's ID
     *
     * @return int Target constellation ID
     *
     */
    public int getTargetConstellation()
    {
        return this.targetConstellation;
    }

    /**
     * Get the Source Constellation's ARK ID
     *
     * Needs more commentary. Why does this exist instead of using parent::getARK()?
     *
     * @return string Source constellation ARK ID
     *
     */
    public String  getSourceArkID()
    {
        return this.sourceArkID;
    }

    /**
     * Set the Source Constellation's ARK ID
     *
     * Needs more commentary. Why does this exist instead of using parent::setARK()?
     */
    public void setSourceArkID(String sourceArkID)
    {
        this.sourceArkID = sourceArkID;
    }

    /**
     * Get the Target Constellation's ARK ID
     *
     * @return string Target constellation ARK ID
     *
     */
    public String getTargetArkID()
    {
        return this.targetArkID;
    }

    /**
     * Get the Target Constellation's Entity Type
     *
     * * cpfRelation/@role
     *
     * @return snacdataTerm Target entity type
     *
     */
    public Term getTargetEntityType()
    {
        return this.targetEntityType;
    }

    /**
     * Get the type of this relation (such as sameAs, correspondedWith, etc)
     *
     * * cpfRelation/@arcrole
     *
     * @return snacdataTerm Type of the constellation
     *
     */
    public Term getType()
    {
        return this.type;
    }

    /**
     * Get the xlink type.
     *
     * This should not be used, as xlink type should always be simple
     *
     * * cpfRelation/@type cpfRelation@xlink:type
     *
     * The only value this ever has is "simple". Daniel says not to save it, and implicitly hard code when
     * serializing export.
     *
     * @return snacdataTerm Alternate type
     *
     */
    public Term getAltType()
    {
        return this.altType;
    }

    /**
     * Get the secondary Relation type
     *
     * ANF Used this as a second way of describing
     * the normal relation type.  That is, "associative" for "associatedWith", and "temporal-after"
     * for "isSucceededBy"
     *
     * * cpfRelation/@cpfRelationType
     *
     * @return snacdataTerm CPF Relation Type
     *
     */
    public Term getCpfRelationType()
    {
        return this.cpfRelationType;
    }

    /**
     * Get the text/xml content of this relation
     *
     * * cpfRelation/relationEntry
     *
     * @return string Content of the relation
     *
     */
    public String getContent()
    {
        return this.content;
    }

    /**
     * Get descriptive note
     *
     * Get the human readable descriptive note for this relation
     *
     * * cpfRelation/descriptiveNote
     *
     * @return string Note attached to relation
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
        return "Relation: " + (this.type != null ? this.type.getTerm() + " " : "") + this.content;
    }

    /**
     * Set the target constellation numeric id
     */
    public void setTargetConstellation(int targetConstellation)
    {
        this.targetConstellation = targetConstellation;
    }

    /**
     * Set the target ARK ID
     */
    public void setTargetArkID(String ark) {

        this.targetArkID = ark;
    }

    /**
     * Set the target entity type
     *
     * Feb 8 2016 renamed from setTargetType() to setTargetEntityType() in order to match the variable name,
     * and to match getTargetEntityType().
     */
    public void setTargetEntityType(Term type) {
        this.targetEntityType = type;
    }

    /**
     * Set the relation type
     */
    public void setType(Term type) {

        this.type = type;
    }

    /**
     * Set the CPF Relation type
     */
    public void setCPFRelationType(Term type) {
        this.cpfRelationType = type;
    }

    /**
     * Set the relation's alternate type
     *
     * cpfRelation/@type cpfRelation@xlink:type
     *
     * The only value this ever has is "simple". Daniel says not to save it, and implicitly hard code when
     * serializing export.
     */
    public void setAltType(Term type) {

        this.altType = type;
    }

    /**
     * Set the content of the relation
     */
    public void setContent(String content) {

        this.content = content;
    }

    /**
     * Set the note for this constellation relation
     */
    public void setNote(String note) {

        this.note = note;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof ConstellationRelation))
            return false;

        if (!super.equals(other))
            return false;

        ConstellationRelation c = (ConstellationRelation) other;
        if (this.getTargetConstellation() != c.getTargetConstellation())
            return false;
        if ((this.getSourceArkID() != null && !(this.getSourceArkID().equals(c.getSourceArkID()))) ||
                (this.getSourceArkID() == null && c.getSourceArkID() != null))
            return false;
        if ((this.getTargetArkID() != null && !(this.getTargetArkID().equals(c.getTargetArkID()))) ||
                (this.getTargetArkID() == null && c.getTargetArkID() != null))
            return false;
        if ((this.getContent() != null && !(this.getContent().equals(c.getContent()))) ||
                (this.getContent() == null && c.getContent() != null))
            return false;
        if ((this.getNote() != null && !(this.getNote().equals(c.getNote()))) ||
                (this.getNote() == null && c.getNote() != null))
            return false;

        if ((this.getType() != null && !(this.getType().equals(c.getType()))) ||
                (this.getType() == null && c.getType() != null))
            return false;

        if ((this.getAltType() != null && !(this.getAltType().equals(c.getAltType()))) ||
                (this.getAltType() == null && c.getAltType() != null))
            return false;

        if ((this.getCpfRelationType() != null && !(this.getCpfRelationType().equals(c.getCpfRelationType()))) ||
                (this.getCpfRelationType() == null && c.getCpfRelationType() != null))
            return false;

        return true;
    }
}
