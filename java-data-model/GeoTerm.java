

/**
 * Geographical Term authority class file
 *
 * Contains the data class for the place entries the geo names controlled vocabulary. These objects are
 * contained in Place which is contained in Constellation.
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

/**
 * Geographical Term
 *
 * Geographical authority term data storage class. This corresponds to table geo_place.
 *
 * This is somewhat akin to class Term. It does NOT extend class AbstractData.
 *
 * @author Robbie Hott
 * @author Tom Laudeman
 *
 */
public class GeoTerm {

    /**
     * Name
     *
     * The display name of this geographical place
     *
     * @var string name
     */
    private String name;

    /**
     * Id (database record id)
     *
     * The ID of this geographical term in SNAC's database
     *
     * @var integer id
     */
    private int id;

    /**
     * Latitute
     *
     * The latitude of this geographical place
     *
     * @var float Latitude
     */
    private double latitude;

    /**
     * Longitude
     *
     * The longitude of this geographical place
     *
     * @var float Longitude
     */
    private double longitude;

    /**
     * Administration Code
     *
     * The administration code of this geographical place.  This is usually a string denoting
     * the state-level code of the place.
     *
     * @var string administration code
     */
    private String administrationCode;

    /**
     * Country Code
     *
     * The country code for this geographical place.  This is usually the 2-digit country code.
     *
     * @var string country code
     */
    private String countryCode;

    /**
     * Persistent Identifier from the External Vocabulary
     *
     * This is the persistent identifier, a URI, to the external controlled vocabulary source
     * for this geographical place.  For example, in GeoNames, this would be the full URI including the
     * GeoName's ID for the place that will resolve to the GeoNames page for this place.
     *
     * From EAC-CPF tag(s):
     *
     * * placeEntry/@vocabularySource
     *
     * @var string vocabulary source uri
     */
    private String uri;


    /**
     * Set the latitude
     *
     */
    public void setLatitude(double lat) {

        this.latitude = lat;
    }

    /**
     * Set the longitude
     *
     */
    public void setLongitude(double lon) {

        this.longitude = lon;
    }

    /**
     * Set the administration code
     *
     */
    public void setAdministrationCode(String code) {

        this.administrationCode = code;
    }

    /**
     * Set the country code
     *
     */
    public void setCountryCode(String code) {

        this.countryCode = code;
    }


    /**
     * Set URI
     *
     * Set the canonical URI for this geographical place from its controlled vocabulary
     *
     */
    public void setURI(String uri) {

        this.uri = uri;
    }

    /**
     * Set name
     *
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Set id
     *
     */
    public void setID(int id) {

        this.id = id;
    }

    /**
     * Get the Longitude
     *
     * @return float longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Get the Latitude
     *
     * @return float latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Get the Administration Code
     *
     * @return string administration code
     */
    public String getAdministrationCode() {
        return this.administrationCode;
    }

    /**
     * Get the Country Code
     *
     * Get the 2-character country code
     *
     * @return string country code
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * Get the URI
     *
     * Get the canonical URI for this geographical term
     *
     * @return string URI
     */
    public String getURI() {
        return this.uri;
    }

    /**
     * Get name
     *
     * @return string name  Name of this place
     */
    public String getName() {

        return this.name;
    }

    /**
     * Get id
     *
     * @return string id Id of this place
     */
    public int getID() {

        return this.id;
    }

    /**
     * Is Empty
     *
     * Check whether or not this Geoterm object is empty (all null values).
     *
     * @return boolean True if the term is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (this.name == null &&
            this.id == 0 &&
            this.latitude == 0.0 &&
            this.longitude == 0.0 &&
            this.administrationCode == null &&
            this.countryCode == null)
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
        // Don't consider it if it's not a GeoTerm object
        if (other != null && other instanceof GeoTerm) {

            GeoTerm g = (GeoTerm) other;
            // Check IDs first
            if (g.getID() != 0 && this.getID() != 0) {
                if (g.getID() == this.getID())
                    return true;
                    else
                        // If they both have IDs, but they are different, no match
                        return false;
            }

            if (this.getLatitude() != g.getLatitude() ||
                    this.getLongitude() != g.getLongitude()) {
                        return false;
            }


            if ((this.getName() != null && !this.getName().equals(g.getName())) ||
                    (this.getName() == null && g.getName() != null))
                return false;
            if ((this.getURI() != null && !this.getURI().equals(g.getURI())) ||
                    (this.getURI() == null && g.getURI() != null))
                return false;
            if ((this.getAdministrationCode() != null && !this.getAdministrationCode().equals(g.getAdministrationCode())) ||
                    (this.getAdministrationCode() == null && g.getAdministrationCode() != null))
                return false;
            if ((this.getCountryCode() != null && !this.getCountryCode().equals(g.getCountryCode())) ||
                    (this.getCountryCode() == null && g.getCountryCode() != null))
                return false;
        } else {
            return false;
        }
        return true;
    }

}
