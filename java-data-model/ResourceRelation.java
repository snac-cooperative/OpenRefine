

/**
 * Resource Relation File
 *
 * Contains the data class for the resource relations.
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
 * Resource Relation
 *
 * Data storage class for relationships of an Identity Constellation to an external (archival) Resource.
 *
 * @author Robbie Hott
 *
 */
public class ResourceRelation extends AbstractData {

    /**
     * Resource to which this is related
     * @var snacdataResource The resource that this relation points to
     */
    private Resource resource = null;

    /**
     * Role
     *
     * From EAC-CPF tag(s):
     *
     * * resourceRelation/@arcrole
     *
     * @var snacdataTerm Role in of the relation
     */
    private Term role = null;

    /**
     * Content
     *
     * From EAC-CPF tag(s):
     *
     * * resourceRelation/resourceEntry
     *
     * @var string Content in the relation
     */
    private String content = null;

    /**
     * Descriptive Note
     *
     * From EAC-CPF tag(s):
     *
     * * resourceRelation/descriptiveNote
     *
     * @var string Note attached to relation
     */
    private String note = null;

    /**
     * Constructor
     *
     */
    public ResourceRelation() {
        super();
        this.setDataType("ResourceRelation");
        this.setMaxDateCount(0);
    }


    /**
     * Get Role
     *
     * Get the role the constellation played with respect to this resource,
     * such as "authorOf" or ""
     *
     * * resourceRelation/@arcrole
     *
     * @return snacdataTerm Role in of the relation
     *
     */
    public Term getRole()
    {
        return this.role;
    }

    /**
     * Get the resource of this relation
     *
     * @return snacdataResource The resource pointed to by this relation
     *
     */
    public Resource getResource()
    {
        return this.resource;
    }

    /**
     * Get the text/xml content of this relation
     *
     * * resourceRelation/resourceEntry
     *
     * @return string Content in the relation
     *
     */
    public String getContent()
    {
        return this.content;
    }

    /**
     * Get the human-readable descriptive note for this relation
     *
     * * resourceRelation/descriptiveNote
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
        return "Resource Relation: " + (this.role != null ? this.role.getTerm() + " " : "") + (this.resource != null ? this.resource.getTitle() + " " : this.content);
    }


    /**
     * Set the resource for this relation
     *
     */
    public void setResource(Resource resource) {

        this.resource = resource;
    }

    /**
     * Set the role of this resource relation
     *
     */
    public void setRole(Term role) {

        this.role = role;
    }

    /**
     * Set the content for this relation
     *
     */
    public void setContent(String content) {

        this.content = content;
    }

    /**
     * Set the note for this resource relation
     *
     */
    public void setNote(String note) {

        this.note = note;
    }


    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof ResourceRelation))
            return false;

        if (!super.equals(other))
            return false;

        ResourceRelation r = (ResourceRelation) other;
        if ((this.getContent() != null && ! this.getContent().equals(r.getContent())) ||
                (this.getContent() == null && r.getContent() != null))
            return false;
        if ((this.getNote() != null && ! this.getNote().equals(r.getNote())) ||
                (this.getNote() == null && r.getNote() != null))
            return false;

        if ((this.getResource() != null && ! this.getResource().equals(r.getResource())) ||
                 (this.getResource() == null && r.getResource() != null))
            return false;
        if ((this.getRole() != null && ! this.getRole().equals(r.getRole())) ||
                 (this.getRole() == null && r.getRole() != null))
            return false;

        return true;
    }
}
