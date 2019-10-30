

/**
 * Resource File
 *
 * Contains the data class for the resources.
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Resource
 *
 * Data storage class for external (archival) Resources.
 *
 * @author Robbie Hott
 *
 */
public class Resource extends AbstractData {

    /**
     * Document Type
     *
     * From EAC-CPF tag(s):
     *
     * * resourceRelation/@role
     *
     * @var snacdataTerm Document type
     */
    private Term documentType = null;

    /**
     * Link Type
     *
     * From EAC-CPF tag(s):
     *
     * Daniel says this is only a hard coded 'simple' and we don't need to store it, but we will hard code it
     * in the export template.
     *
     * (old comment:) resourceRelation/@type
     * Actually: resourceRelation@xlink:type
     *
     * 'linkType' => 'simple',
     *
     * @var snacdataTerm Link type
     */
    private Term linkType = null;

    /**
     * Entry Type
     *
     * The ANF use this field to repeat (in short form) the document type from @role, such as
     * "archival" for "ArchivalResource"
     *
     * From EAC-CPF tag(s):
     *
     * * resourceRelation/relationEntry/@localType
     *
     * @var snacdataTerm Relation entry type
     *
     */
    private Term entryType = null;

    /**
     * Link URI
     *
     * From EAC-CPF tag(s):
     *
     * * resourceRelation/@href
     *
     * @var string Link to external resource
     */
    private String link = null;

    /**
     * XML source
     *
     * From EAC-CPF tag(s):
     *
     * * resourceRelation/objectXMLWrap
     *
     * @var string XML source of the resource relation
     */
    private String source = null;

    /**
     * Title of the archival resource
     *
     * @var string Resource title
     */
    private String title = null;

    /**
     * Abstract of the archival resource
     *
     *
     * @var string Abstract describing the resource
     */
    private String abstractStr;


    /**
     * Extent of the resource
     *
     * @var string Extent of the materials, for example "1 box", "3 linear feet"
     */
    private String extent = null;


    /**
     * Repository
     *
     * @var snacdataRepository The holding repository of this resource
     */
    private Constellation repository = null;


    /**
     * Date of the resource
     *
     * @var string Date of resource
     */
    private String date = null;

    /**
     * Display Entry, a descriptive summary name of the resource
     *
     * @var string Display Entry
     */
    private String displayEntry = null;


    /**
     * Origination (creator) of the resource
     * @var string[] List of origination names (names of the creators) of this resource.
     */
    private List<OriginationName> originationNames = null;


    /**
    * Languages of this Resource
    *
    * @var snacdataLanguage[] Languages in which this resource was written
    */
    private List<Language> languages = null;

    /**
     * Constructor
     *
     */
    public Resource() {
        super();
        this.setDataType("Resource");
        this.setMaxDateCount(0);
        this.originationNames = new ArrayList<>();
        this.languages = new ArrayList<>();
    }

    /**
     * Get title of the archival resource
     *
     * @return Resource title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set title of the archival resource
     *
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get abstract of the archival resource
     *
     *
     * @return string Abstract describing the resource
     */
    public String getAbstract() {
        return this.abstractStr;
    }

    /**
     * Set abstract of the archival resource
     *
     *
     */
    public void setAbstract(String abstractStr) {
        this.abstractStr = abstractStr;
    }

    /**
     * Get extent of the resource
     *
     * @return string Extent of the materials, for example "1 box", "3 linear feet"
     */
    public String getExtent() {
        return this.extent;
    }

    /**
     * Set extent of the resource
     *
     */
    public void setExtent(String extent) {
        this.extent = extent;
    }

    /**
     * Get date of the archival resource
     *
     * @return string Date of the resource
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Set date of the archival resource
     *
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get display entry of the resource
     *
     * @return string Display Entry
     */
     public String getDisplayEntry() {
         return this.displayEntry;
     }

     /**
      * Set display entry of the resource
      *
      */
     public void setDisplayEntry(String displayEntry) {
         this.displayEntry = displayEntry;
     }

    /**
     * Get repository
     *
     */
    public Constellation getRepository() {
        return this.repository;
    }

    /**
     * Set repository
     *
     */
    public void setRepository(Constellation repository) {
        this.repository = repository;
    }

    /**
     * Get list of origination (creator) of the resource
     *
     * @return snacdataRROriginationName[] List of origination names (names of the creators) of this resource.
     */
    public List<OriginationName> getOriginationNames() {
        return this.originationNames;
    }

    /**
     * Add an origination (creator) of the resource
     *
     */
    public void addOriginationName(OriginationName originationName) {
        this.originationNames.add(originationName);
    }

    public void setOriginationNames(List<OriginationName> names) {
        this.originationNames = new ArrayList<>(names);
    }

    /**
     * Get the Languages
     *
     * Returns the list of languages in which this resource was written
     *
     * @return snacdataLanguage[] Languages and scripts used
     *
     */
    public List<Language> getLanguages()
    {
        return this.languages;
    }

    public void setLanguages(List<Language> langs) {
        this.languages = new ArrayList<>(langs);
    }

    /**
     * Get the document type
     *
     *  Get the document type for the document pointed to by this relation, such as "ArchivalResource"
     *
     * * resourceRelation/@role
     *
     * @return snacdataTerm Document type
     *
     */
    public Term getDocumentType()
    {
        return this.documentType;
    }


    /**
    * Get Secondary Type
    *
    * Get the secondary type of the document pointed to by this relation.  The ANF use
    * this field to repeat (in short form) the document type from @role, such as
    * "archival" for "ArchivalResource"
    *
    * * resourceRelation/relationEntry/@localType
    *
    * @return snacdataTerm Relation entry type
    *
    */
    public Term getEntryType()
    {
        return this.entryType;
    }


    /**
     * Set the entry type
     *
     * The ANF use this field to repeat (in short form) the document type from @role, such as
     * "archival" for "ArchivalResource"
     *
     */
    public void setEntryType(Term type) {
        this.entryType = type;
    }

    /**
     * Get the xlink type
     *
     * This should not be used, as it is always "simple"
     *
     * Daniel says this is only a hard code 'simple' and we don't need to store it, but we will hard code it
     * in the export template.
     *
     * (old comment:) resourceRelation/@type
     * Actually: resourceRelation@xlink:type
     *
     * 'linkType' => 'simple',
     *
     * @return snacdataTerm Link type
     * @deprecated
     *
     */
    public Term getLinkType()
    {
        return this.linkType;
    }

    /**
     * Get URI Link
     *
     * Get the URI link for the document pointed to by this relation
     *
     * * resourceRelation/@href
     *
     * @return string Link to external resource
     *
     */
    public String getLink()
    {
        return this.link;
    }

    /**
     * Get the source XML of this relation
     *
     * * resourceRelation/objectXMLWrap
     *
     * @return string XML source of the resource relation
     *
     */
    public String getSource()
    {
        return this.source;
    }


    /**
     * Set the document type for this relation
     *
     */
    public void setDocumentType(Term type) {

        this.documentType = type;
    }

    /**
     * Set the HREF link for this resource relation
     *
     */
    public void setLink(String href) {

        this.link = href;
    }

    /**
     * Set the link type for this relation
     *
     */
    public void setLinkType(Term type) {

        this.linkType = type;
    }

    /**
     * Set the XML source of this resource relation
     *
     */
    public void setSource(String xml) {

        this.source = xml;
    }

    /**
     * Add language
     *
     * Add a language used by this resource.
     *
     */
    public void addLanguage(Language language) {
        this.languages.add(language);
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof Resource))
            return false;


        if (!super.equals(other))
            return false;

        Resource r = (Resource) other;
        if ((this.getTitle() != null && ! this.getTitle().equals(r.getTitle())) ||
                (this.getTitle() == null && r.getTitle() != null))
            return false;
        if ((this.getAbstract() != null && ! this.getAbstract().equals(r.getAbstract())) ||
                (this.getAbstract() == null && r.getAbstract() != null))
            return false;
        if ((this.getSource() != null && ! this.getSource().equals(r.getSource())) ||
                (this.getSource() == null && r.getSource() != null))
            return false;
        if ((this.getExtent() != null && ! this.getExtent().equals(r.getExtent())) ||
                (this.getExtent() == null && r.getExtent() != null))
            return false;
        if ((this.getLink() != null && ! this.getLink().equals(r.getLink())) ||
                (this.getLink() == null && r.getLink() != null))
            return false;

        if ((this.getDocumentType() != null && ! this.getDocumentType().equals(r.getDocumentType())) ||
                 (this.getDocumentType() == null && r.getDocumentType() != null))
            return false;
        if ((this.getEntryType() != null && ! this.getEntryType().equals(r.getEntryType())) ||
                 (this.getEntryType() == null && r.getEntryType() != null))
            return false;
        if ((this.getLinkType() != null && ! this.getLinkType().equals(r.getLinkType())) ||
                 (this.getLinkType() == null && r.getLinkType() != null))
            return false;

        if ((this.getRepository() != null && ! this.getRepository().equals(r.getRepository())) ||
                 (this.getRepository() == null && r.getRepository() != null))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Resource.toJSON(this).hashCode();
    }

    public static Resource fromJSON(String s) {
        try {
            ObjectMapper om = new ObjectMapper();
            Resource r = om.readValue(s, Resource.class);
            return r;
        } catch (Exception e) {
            return null;
        }
    }

    public static String toJSON(Resource r) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationFeature.INDENT_OUTPUT);
            return om.writeValueAsString(r);
        } catch (Exception e) {
            return null;
        }
    }

    public static Resource readFromFile(File file) {
        try {
            ObjectMapper om = new ObjectMapper();
            //om.enable(SerializationFeature.INDENT_OUTPUT);
            Resource r = om.readValue(file, Resource.class);

            return r;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Resource r = Resource.readFromFile(new File("test/resource.json"));
        Resource r2 = Resource.readFromFile(new File("test/resource.json"));

        System.out.println(r.equals(r2));
    }
}
