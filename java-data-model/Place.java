

/**
 * Place class file
 *
 * Contains the place data class.
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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Place
 *
 * The place data class contains information about places throughout the Constellation structure.  In EAC-CPF terms, these
 * objects are used anywhere a PlaceEntry may be used.  Therefore, they contain the bits of information from the CPF
 * description's Place tags (which may contain repeatable placeEntry tags) and information from placeEntry tags themselves.
 * If there is a known controlled vocabulary (canonical) geographical place associated with this Place, that term should be
 * pulled using a GeoTerm and stored appropriately.  (In SNAC EAC-CPF, this would be a <snac:placeEntry> contained inside a
 * placeEntry tag that is a GeoNames match).
 *
 * When generating EAC-CPF from the Constellation structure, any Places in the top-level Constellation must be created as
 * <place> wrapped <placeEntry> tags.  Everywhere else, these Places must be generated as <placeEntry> tags only.
 *
 *
 * @author Robbie Hott
 * @author Tom Laudeman
 *
 */
public class Place extends AbstractData {

    /**
     * Original place string from CPF
     *
     * This is the original string from the CPF <placeEntry> tag. Once a place is linked to a controlled vocab,
     * this string becomes unnecessary, or is filled with the name from the GeoTerm associated with this object.
     *
     * @var string original
     */
    private String original;

    /**
     * Descriptive Note
     *
     * From EAC-CPF tag(s):
     *
     * * place/descriptiveNote
     *
     * @var string note Descriptive note
     */
    private String note;

    /**
     * Type of the place
     *
     * This is a controlled-vocabulary place type term.
     *
     * From EAC-CPF tag(s):
     *
     * * place/@localType
     *
     * @var snacdataTerm type Type of the place
     */
    private Term type;

    /**
     * Role of the place
     *
     * This is a controlled-vocabulary place role term.
     * From EAC-CPF tag(s):
     *
     * * place/placeRole
     *
     * @var snacdataTerm role Place role
     */
    private Term role;

    /**
     * Match confidence score
     *
     * This is a machine-generated confidence score that the GeoTerm linked to this
     * place is actually the place referred to by the "original" string.
     *
     * @var float score machine-generated match confidence score
     */
    private double score;

    /**
     * Confirmed Geographical Place
     *
     * This boolean states whether the linked GeoTerm was human-vetted as a match.  If a human made
     * the match, this is true, else it is false.
     *
     * @var boolean confirmed
     */
    private boolean confirmed;

    /**
     * Geographical Place Term
     *
     * @var snacdataGeoTerm geoTerm The controlled vocabulary term object for the geographical place
     */
    @JsonProperty("geoplace")
    private GeoTerm geoTerm;

    /**
     * Address Lines
     *
     * From EAC-CPF tag(s):
     *
     * * place/address/addressLine
     *
     * @var snacdataAddressLine[] address Address of this place
     */
    private List<AddressLine> address;

    /**
     * Constructor
     *
     * Set up the Place object.  Read an associative array as a parameter.  This object may only have one
     * date, so we use the parent's method to limit dates to 1.
     *
     */
    public Place() {
        super();
        this.setDataType("Place");
        this.setMaxDateCount(1);
        this.address = new ArrayList<>();

        // Set some default values
        this.confirmed = false;
        this.score = 0;
    }

    /**
     * Set the original place name
     *
     */
    public void setOriginal(String original) {

        this.original = original;
    }


    /**
     * Set the descriptive note
     *
     */
    public void setNote(String note) {

        this.note = note;
    }

    /**
     * Set the place type
     *
     */
    public void setType(Term type) {

        this.type = type;
    }

    /**
     * Set the place role
     *
     */
    public void setRole(Term role) {

        this.role = role;
    }

    /**
     * Set the Match Score
     *
     * Set the machine-generated match confidence score between the GeoTerm
     * and this place.
     *
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Set Confirmed
     *
     * Set the confirmation status of this place by parameter.  This confirmation status
     * is true if a human has confirmed that the GeoTerm is actually this Place.
     *
     * This method will return false if trying to confirm a place that has no GeoTerm.
     *
     * @return boolean True if the confirmed flag was set, false otherwise
     */
    public boolean setConfirmed(boolean confirmed) {
        if (this.geoTerm == null)
            return false;
        this.confirmed = confirmed;
        return true;
    }

    /**
     * Confirm this place
     *
     * Confirm that this place is actually the GeoTerm listed, by human inspection.
     *
     * This method will return false if trying to confirm a place that has no GeoTerm.
     *
     * @return boolean True if confirmed flag was set, false otherwise
     */
    public boolean confirm() {
        if (this.getGeoTerm() == null)
            return false;
        this.confirmed = true;
        return true;
    }

    /**
     * Deconfirm this place
     *
     * Remove confirmation that this place is actually the GeoTerm listed.
     */
    public void deconfirm() {
        this.confirmed = false;
    }

    /**
     * Set the Geographical Term
     *
     * Set the canonical geographical term associated with this place
     *
     */
    public void setGeoTerm(GeoTerm geoTerm) {
        this.geoTerm = geoTerm;
    }

    /**
     * Add an address line
     *
     * Add an address line to this place
     *
     */
    public void addAddressLine(AddressLine addressLine) {
        this.address.add(addressLine);
    }

    /**
     * Set address
     *
     * Sets the address this set
     *
     */
    public void setAddress(List<AddressLine> address) {
        this.address = address;
    }

    /**
     * Get Address
     *
     * Gets the address associated with this place
     *
     * @return snacdataAddressLine[] The address as a set of lines
     */
    public List<AddressLine> getAddress() {
        return this.address;
    }

    /**
     * Get the original place name
     *
     * @return string original original place name
     */
    public String getOriginal() {

        return this.original;
    }

    /**
     * Get descriptive note
     *
     * Get the descriptive note
     *
     * @return string descriptive note
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Get Type
     *
     * Get the type of this place
     *
     * @return snacdataTerm Type term for this place
     */
    public Term getType() {
        return this.type;
    }

    /**
     * Get Role
     *
     * Get the role associated with this place
     *
     * @return snacdataTerm Role term for this place
     */
    public Term getRole() {
        return this.role;
    }

    /**
     * Get Confirmation Status
     *
     * Get the confirmation status for this place.  This returns true if the GeoTerm has been
     * human-vetted to match this place, false otherwise.
     *
     * @return boolean true if human-matched GeoTerm, false otherwise
     */
    public boolean getConfirmed() {
        return this.confirmed;
    }

    /**
     * Get Match Confidence Score
     *
     * Get the machine-generated match score between the associated GeoTerm and this place.
     *
     * @return float match score, or 0 if no GeoTerm present
     */
    public double getScore() {
        if (this.geoTerm == null)
            return 0;
        return this.score;
    }

    /**
     * Get the Geographical Term
     *
     * Get the canonical geographic term for this place
     *
     * @return snacdataGeoTerm The geographic term, or null if none exists
     */
    public GeoTerm getGeoTerm() {
        return this.geoTerm;
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
        if (this.geoTerm == null)
            return "Place: " + this.original;
        return "Place: " + this.geoTerm.getName();
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof Place))
            return false;

        if (!super.equals(other))
            return false;

        Place p = (Place) other;

        if ((this.getOriginal() != null && ! this.getOriginal().equals(p.getOriginal())) ||
                (this.getOriginal() == null && p.getOriginal() != null))
            return false;
        if (this.getScore() != p.getScore())
            return false;
        if (this.getConfirmed() != p.getConfirmed())
            return false;
        if ((this.getNote() != null && ! this.getNote().equals(p.getNote())) ||
                (this.getNote() == null && p.getNote() != null))
            return false;

        if ((this.getType() != null && ! this.getType().equals(p.getType())) ||
                 (this.getType() == null && p.getType() != null))
            return false;
        if ((this.getRole() != null && ! this.getRole().equals(p.getRole())) ||
                 (this.getRole() == null && p.getRole() != null))
            return false;
        if ((this.getGeoTerm() != null && ! this.getGeoTerm().equals(p.getGeoTerm())) ||
                 (this.getGeoTerm() == null && p.getGeoTerm() != null))
            return false;

        if (!this.getAddress().containsAll(p.getAddress()))
            return false;

        return true;
    }

    /**
     * Cleanse all sub-elements
     *
     * Removes the ID and Version from sub-elements and updates the operation to be
     * INSERT.  If the operation is specified by the parameter, this method
     * will use that operation instead of INSERT.
     *
     */
    public void cleanseSubElements(String operation) {
        String newOperation = AbstractData.OPERATION_INSERT;
        if (operation != null) {
            newOperation = operation;
        }

        super.cleanseSubElements(newOperation);

        for (AddressLine a : this.address) {
            a.setID(0);
            a.setVersion(0);
            a.setOperation(newOperation);
            a.cleanseSubElements(newOperation);
        }
    }
}
