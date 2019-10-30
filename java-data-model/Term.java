

/**
 * Term Class that holds simple database terms.
 *
 * Holds the information for an individual term in a controlled vocabulary. 
 *
 * License:
 *
 *
 * @author Robbie Hott
 * @author Tom Laudeman
 * @license https://opensource.org/licenses/BSD-3-Clause BSD 3-Clause
 * @copyright 2015 the Rector and Visitors of the University of Virginia, and
 *            the Regents of the University of California
 */
package org.snaccooperative.data;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Vocabulary Term
 *
 * This class contains the data associated with one controlled vocabulary term's data.  At first, this
 * includes database IDs and vocabulary terms.
 *
 * @author Robbie Hott
 * @author Tom Laudeman
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Term {


    /**
     * @var string type Vocabulary type
     * 
     * Vocabulary type of this term.
     *
     * This type is based on the storage of the vocabulary in our system, in which the vocabulary is grouped by what area
     * it is describing (such as a gender, or script code).  The list of types is:
     * 
     * * record_type, 
     * * script_code, 
     * * entity_type, 
     * * event_type, 
     * * name_type,
     * * occupation, 
     * * language_code, 
     * * gender, 
     * * nationality, 
     * * maintenance_status, 
     * * agent_type, 
     * * document_role,
     * * document_type, 
     * * function_type, 
     * * function, 
     * * subject, 
     * * date_type, 
     * * relation_type, 
     * * place_match, 
     * * source_type
     * 
     */
    protected String type;

    /**
     * @var int id vocabulary ID for this term
     * 
     * This is the ID in vocabulary store (postgres)
     */
    protected int id;

    /**
     * @var string term The term 
     * 
     * This is the value (in any language) for this particular Term object.
     */
    protected String term;
    
    /**
     * @var string uri The full URI for this controlled vocabulary term
     */
    protected String uri;
    
    /**
     * @var string description The description 
     * 
     * This is the description (in any language) for this particular vocabulary term
     */
    protected String description;
    
    /**
     * Set the type 
     * 
     * Set the type for this vocabulary term. Objects using this term will match their type against this. User
     * interface will use this constrain vocabulary term selection only to appropriate values.
     * 
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Get the type
     * 
     * Get the type for this vocabulary term. Objects using this term will match their type against this. User
     * interface will use this constrain vocabulary term selection only to appropriate values.
     * 
     * @return string The vocabulary type of this Term.
     */
    public String getType()
    {
        return this.type;
    }

    /**
     * Get the ID of this vocab term
     *
     *  @return int ID of this vocab term
     */
    public int getID() {
        return this.id;
    }

    /**
     * Set the ID of this vocab term
     *
     */
    public void setID(int id) {
        this.id = id;
    }
    
    /**
     * Get the term of this vocab term
     * 
     * This is a human-readable text string of this Term.  It may be in any language available to the system.
     *
     *  @return string term of this vocab term
     */
    public String getTerm() {
        return this.term;
    }

    /**
     * Set the term of this vocab term
     *
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Get the URI of this vocab term
     *
     *  @return string URI of this vocab term
     */
    public String getURI() {
        return this.uri;
    }

    /**
     * Set the URI of this vocab term
     *
     */
    public void setURI(String uri) {
        this.uri = uri;
    }
    
    /**
     * Get the description of this vocab term
     *
     *  @return string Description of this vocab term
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description of this vocab term
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Is the term empty
     * 
     * Check whether or not this term object is empty (all null values).
     *
     * @return boolean True if the term is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (this.uri == null && this.description == null &&
            this.id == 0 && this.term == null)
            return true;
        return false;
    }

    /**
     * is Equal
     *
     * Checks whether the given parameter is the same as this object. If
     * the IDs match, then that is taken as priority above any other data.  Else,
     * everything must match.
     *
     * @return boolean true if equal, false otherwise
     */
    public boolean equals(Object other) {
        // Don't consider it if it's not a Term object
        if (other != null && other instanceof Term) {
            // Check IDs first
            Term t = (Term) other;
            if (t.getID() != 0 && this.getID() != 0) {
                if (t.getID() == this.getID())
                    return true;
                else
                    // If they both have IDs, but they are different, no match
                    return false;
            }

            if (this.getURI().equals(t.getURI()) &&
                this.getTerm().equals(t.getTerm()) &&
                this.getDescription().equals(t.getDescription())) {
                return true;
            }
        }
        return false;
    }

}
