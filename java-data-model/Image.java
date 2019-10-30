

/**
 * Image Class file
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
 * Image Class
 *
 * Image class that holds a URL and citation information
 *
 * @author Robbie Hott
 */
public class Image extends AbstractData {

    /**
     * var string URL
     */
    protected String url;

    /**
     * var string information
     */
    protected String info;

    /**
     * var string information url
     */
    protected String infoURL;

    /**
     * var string author
     */
    protected String author;

    /**
     * var string author's URl
     */
    protected String authorURL;

    /**
     * var string license
     */
    protected String license;

    /**
     * var string license's URl
     */
    protected String licenseURL;

    public Image() {
        super();
        this.setDataType("Image");
        this.setMaxDateCount(0);
    }

    /**
     * Get the author of this object
     *
     *  @return string author of this object
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Set the author of this object
     *
     */
    public void setAuthor(String author) {
        this.author = author;
    }


    /**
     * Get the authorURL of this object
     *
     *  @return string authorURL of this object
     */
    public String getAuthorURL() {
        return this.authorURL;
    }

    /**
     * Set the authorURL of this object
     *
     */
    public void setAuthorURL(String authorURL) {
        this.authorURL = authorURL;
    }


    /**
     * Get the license of this object
     *
     *  @return string license of this object
     */
    public String getLicense() {
        return this.license;
    }

    /**
     * Set the license of this object
     *
     */
    public void setLicense(String license) {
        this.license = license;
    }


    /**
     * Get the licenseURL of this object
     *
     *  @return string licenseURL of this object
     */
    public String getLicenseURL() {
        return this.licenseURL;
    }

    /**
     * Set the licenseURL of this object
     *
     */
    public void setLicenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
    }

    /**
     * Get the url of this object
     *
     *  @return string url of this object
     */
    public String getURL() {
        return this.url;
    }

    /**
     * Set the url of this object
     *
     */
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Get the info url of this object
     *
     *  @return string info url of this object
     */
    public String getInfoURL() {
        return this.infoURL;
    }

    /**
     * Set the info url of this object
     *
     */
    public void setInfoURL(String url) {
        this.infoURL = url;
    }

    /**
     * Get the info of this object
     *
     *  @return string info of this object
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * Set the info of this object
     *
     */
    public void setInfo(String info) {
        this.info = info;
    }


    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof Image))
            return false;

        if (!super.equals(other))
            return false;

        Image i = (Image) other;

        if ((this.getURL() != null && !this.getURL().equals(i.getURL())) ||
                (this.getURL() == null && i.getURL() != null))
            return false;

        if ((this.getInfo() != null && !this.getInfo().equals(i.getInfo())) ||
                (this.getInfo() == null && i.getInfo() != null))
            return false;

        if ((this.getInfoURL() != null && !this.getInfoURL().equals(i.getInfoURL())) ||
                (this.getInfoURL() == null && i.getInfoURL() != null))
            return false;

        if ((this.getAuthor() != null && !this.getAuthor().equals(i.getAuthor())) ||
                (this.getAuthor() == null && i.getAuthor() != null))
            return false;

        if ((this.getAuthorURL() != null && !this.getAuthorURL().equals(i.getAuthorURL())) ||
                (this.getAuthorURL() == null && i.getAuthorURL() != null))
            return false;

        if ((this.getLicense() != null && !this.getLicense().equals(i.getLicense())) ||
                (this.getLicense() == null && i.getLicense() != null))
            return false;

        if ((this.getLicenseURL() != null && !this.getLicenseURL().equals(i.getLicenseURL())) ||
                (this.getLicenseURL() == null && i.getLicenseURL() != null))
            return false;

        return true;
    }

}
