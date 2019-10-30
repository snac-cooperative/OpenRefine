

/**
 * Identity Constellation File
 *
 * Contains the constellation information for an entire entity.
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

    /*        "dataType" => "Constellation",
            "status" => this.status,
            "ark" => this.ark,
            "entityType" => this.entityType == null ? null : this.entityType->toArray(shorten),
            "otherRecordIDs" => array(),
            "entityIDs" => array(),
            "maintenanceStatus" => this.maintenanceStatus == null ? null : this.maintenanceStatus->toArray(shorten),
            "maintenanceAgency" => this.maintenanceAgency,
            "maintenanceEvents" => array(),
            "sources" => array(),
            "legalStatuses" => array(),
            "conventionDeclarations" => array(),
            "languagesUsed" => array(),
            "nameEntries" => array(),
            "preferredNameEntry" => this.preferredNameEntry == null ? null : this.preferredNameEntry->toArray(shorten),
            "occupations" => array(),
            "biogHists" => array(),
            "relations" => array(),
            "resourceRelations" => array(),
            "functions" => array(),
            "places" => array(),
            "subjects" => array(),
            "nationalities" => array(),
            "genders" => array(),
            "generalContexts" => array(),
            "structureOrGenealogies" => array(),
            "mandates" => array(),
            "images" => array(),
            "flags" => this.flags
        );*/

/**
 * Identity Constellation
 *
 * Stores all the information related to an identity constellation.  This is the root of an identity
 * constellation, and has fields and methods to store all parts of the constellation.  Any of them may be left
 * null, if they are unused.
 *
 * @author Robbie Hott
 *
 */

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Constellation extends AbstractData {

    /**
     * ARK ID
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/control/recordId
     *
     * @var string ARK identifier
     */
    private String ark = null;

    /**
     * Entity Type
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/identity/entityType
     *
     * @var snacdataTerm Entity type
     */
    private Term entityType = null;

    /**
     * Other Record ID List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/control/otherRecordId
     * * cpfRelation where type=sameAs
     *
     * @var snacdataSameAs[] Other record IDs by which this constellation may be known
     */
    private List<SameAs> otherRecordIDs = null;


    /**
    * EntityID List
    *
    * From EAC-CPF tag(s):
    *
    * * eac-cpf/cpfDescription/identity/entityID
    *
    * @var snacdataEntityId[] Other external record IDs by which this constellation may be known
    */
    private List<EntityId> entityIDs = null;

    /**
     * Maintenace Status
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/control/maintenanceStatus
     *
     * @var snacdataTerm Current maintenance status
     */
    private Term maintenanceStatus = null;

    /**
     * Maintenance Agency
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/control/maintenanceAgency/agencyName
     *
     * @var string Latest maintenance agency
     */
    private String maintenanceAgency = null;

    /**
     * Maintenance Event List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/control/maintenanceHistory/maintenanceEvent/*
     *
     * @var snacdataMaintenanceEvent[] List of maintenance events performed on this constellation
     */
    private List<MaintenanceEvent> maintenanceEvents = null;

    /**
     * Source list
     *
     * From EAC-CPF tag(s):
     *
     * * /eac-cpf/control/sources/source/@type
     * * /eac-cpf/control/sources/source/@href
     *
     * @var snacdataSource[] List of sources
     */
    private List<Source> sources = null;

    /**
     * Legal Status List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/legalStatus/term
     * * eac-cpf/cpfDescription/description/legalStatus/@vocabularySource
     *
     *
     * @var snacdataLegalStatus[] List of legal statuses
     */
    private List<LegalStatus> legalStatuses = null;

    /**
     * Convention Declaration List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/control/conventionDeclaration
     *
     * @var snacdataConventionDeclaration[] Convention declarations
     */
    private List<ConventionDeclaration> conventionDeclarations = null;

    /**
     * Languages Used List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/languageUsed/language
     * * eac-cpf/cpfDescription/description/languageUsed/language/@languageCode
     * * eac-cpf/cpfDescription/description/languageUsed/script
     * * eac-cpf/cpfDescription/description/languageUsed/script/@scriptCode
     *
     * @var snacdataLanguage[] Languages used by the identity described
     */
    private List<Language> languagesUsed = null;

    /**
     * Name Entry List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/identity/nameEntry
     *
     * @var snacdataNameEntry[] List of name entries for this constellation
     */
    private List<NameEntry> nameEntries = null;

    /**
     * Preferred Name Entry
     *
     * @var snacdataNameEntry|null The preferred name entry on view (based on the viewing user)
     */
    private NameEntry preferredNameEntry = null;

    /**
     * Occupation List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/occupation/*
     *
     * @var snacdataOccupation[] List of occupations
     */
    private List<Occupation> occupations = null;

    /**
     * BiogHist List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/biogHist
     *
     * @var snacdataBiogHist[] BiogHist entries for this constellation (in XML strings)
     */
    private List<BiogHist> biogHists = null;

    /**
     * Constellation Relation List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfdescription/relations/cpfrelation/*
     *
     * @var snacdataconstellationrelation[] constellation relations
     */
    private List<ConstellationRelation> relations = null;

    /**
     * Resource Relation List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/relations/resourceRelation/*
     *
     * @var snacdataResourceRelation[] Resource relations
     */
    private List<ResourceRelation> resourceRelations = null;

    /**
     * Function list
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/function/*
     *
     * @var snacdataSNACFunction[] Functions
     */
    private List<SNACFunction> functions = null;

    /**
     * Place list
     *
     * A list of Place objects.
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/place/*
     *
     * @var snacdataPlace[] Places
     */
    private List<Place> places = null;

    /**
     * Subject List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/localDescription/@localType=AssociatedSubject/term
     *
     * @var snacdataSubject[] Subjects
     */
    private List<Subject> subjects = null;

    /**
     * Nationality List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/localDescription/@localType=nationalityOfEntity/term
     *
     * @var snacdataNationality[] nationalities of this entity
     */
    private List<Nationality> nationalities = null;

    /**
     * Gender List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/localDescription/@localType=gender/term
     *
     * @var snacdataGender[] Gender
     */
    private List<Gender> genders = null;

    /**
     * General Contexts List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/generalContext
     *
     * @var snacdataGeneralContext[] General Contexts
     */
    private List<GeneralContext> generalContexts = null;

    /**
     * Structure or Genealogies List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/structureOrGenealogy
     *
     * @var snacdataStructureOrGenealogy[] Structure Or Genealogy information
     */
    private List<StructureOrGenealogy> structureOrGenealogies = null;

    /**
     * Mandate List
     *
     * From EAC-CPF tag(s):
     *
     * * eac-cpf/cpfDescription/description/mandate
     *
     * @var snacdataMandate[] Mandates
     */
    private List<Mandate> mandates = null;

    /**
     * Status of the constellation
     *
     * The status of the constellation in the system.  This allows the system to note whether the
     * constellation is editable by a given user.
     *
     * @var string|null Status of the constellation
     */
    private String status = null;

    /**
     * Constellation Flags
     *
     * Additional information as part of the Constellation.
     *
     * @var string[] An array of flags
     */
    private List<String> flags = null;

    /**
     * Images for this constellation
     *
     * @var snacdataImage[] Images
     */
    private List<Image> images = null;

    /**
     * Constructor for the class.
     *
     */
    public Constellation() {
        super();
        this.setDataType("Constellation");
        this.setMaxDateCount(AbstractData.MAX_LIST_SIZE);
        this.otherRecordIDs = new ArrayList<>();
        this.sources = new ArrayList<>();
        this.maintenanceEvents = new ArrayList<>();
        this.nameEntries = new ArrayList<>();
        this.biogHists = new ArrayList<>();
        this.occupations = new ArrayList<>();
        this.relations = new ArrayList<>();
        this.resourceRelations = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.places = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.legalStatuses = new ArrayList<>();
        this.genders = new ArrayList<>();
        this.nationalities = new ArrayList<>();
        this.languagesUsed = new ArrayList<>();
        this.conventionDeclarations = new ArrayList<>();
        this.generalContexts = new ArrayList<>();
        this.structureOrGenealogies = new ArrayList<>();
        this.mandates = new ArrayList<>();
        this.entityIDs = new ArrayList<>();
        this.images = new ArrayList<>();
        this.flags = new ArrayList<>();
    }

    /**
     * Get the ARK identifier URI
     *
     * @return string ARK identifier
     *
     */
    public String getArk()
    {
        return this.ark;
    }

    /**
     * Get the entity type
     *
     * @return snacdataTerm Entity type
     *
     */
    public Term getEntityType()
    {
        return this.entityType;
    }

    /**
     * Get the other record IDs
     *
     * @return snacdataSameAs[] Other record IDs by which this constellation may be known
     *
     */
    public List<SameAs> getOtherRecordIDs()
    {
        return this.otherRecordIDs;
    }

    /**
    * Get the other entityIDs
    *
    * @return snacdataEntityId[] Other entity IDs by which this constellation may be known
    *
    */
    public List<EntityId> getEntityIDs()
    {
        return this.entityIDs;
    }

    /**
     * Get the maintenance Status
     *
     * @return snacdataTerm Current maintenance status
     *
     */
    public Term getMaintenanceStatus()
    {
        return this.maintenanceStatus;
    }

    /**
     * Get the maintenance agency
     *
     * @return string Latest maintenance agency
     *
     */
    public String getMaintenanceAgency()
    {
        return this.maintenanceAgency;
    }

    /**
     * Get the list of maintenance events
     *
     * @return snacdataMaintenanceEvent[] List of maintenance events performed on this constellation
     *
     */
    public List<MaintenanceEvent> getMaintenanceEvents()
    {
        return this.maintenanceEvents;
    }

    /**
     * Get the list of sources
     *
     * @return snacdataSource[] List of sources
     *
     */
    public List<Source> getSources()
    {
        return this.sources;
    }

    /**
     * Get the list of legal statuses
     *
     * @return snacdataLegalStatus[] List of legal statuses
     *
     */
    public List<LegalStatus> getLegalStatuses()
    {
        return this.legalStatuses;
    }

    /**
     * Get the convention declarations
     *
     * @return snacdataConventionDeclaration[] Convention declarations
     *
     */
    public List<ConventionDeclaration> getConventionDeclarations()
    {
        return this.conventionDeclarations;
    }

    /**
     * Get the Languages Used
     *
     * @return snacdataLanguage[] Languages and scripts used by the identity described
     *
     */
    public List<Language> getLanguagesUsed()
    {
        return this.languagesUsed;
    }


    /**
     * Get the name entries
     *
     * @return snacdataNameEntry[] List of name entries for this constellation
     *
     */
    public List<NameEntry> getNameEntries()
    {
        return this.nameEntries;
    }

    /**
     * Get the preferred name
     *
     * Gets the preferred name entry for this constellation.  If the preferred
     * name entry has been set by the server, that one is returned.  If not, it
     * gets the nameEntry in this constellation with the highest score, or the
     * first one if the scores are equal, or null if there is no name entry
     *
     * @return snacdataNameEntry Preferred name entry for this constellation
     *
     */
    public NameEntry getPreferredNameEntry()
    {
        if (this.nameEntries.size() < 1)
            return null;

        if (this.preferredNameEntry != null)
            return this.preferredNameEntry;

        double max = 0;
        int id = 0;
        for (int i = 0; i < nameEntries.size(); i++) {
            if (nameEntries.get(i).getPreferenceScore() > max) {
                max = nameEntries.get(i).getPreferenceScore();
                id = i;
            }
        }

        return this.nameEntries.get(id);
    }

    /**
     * Get the occupations
     *
     * @return snacdataOccupation[] List of occupations
     *
     */
    public List<Occupation> getOccupations()
    {
        return this.occupations;
    }

    /**
     * Get the list of BiogHists
     * Each BiogHist is presumed to be a translation in a
     * specific language.
     *
     * @return snacdataBiogHist[] An array of BiogHist ordered by language 3 letter code, or an empty list
     * if no BiogHist exists for this Constellation
     */
    public List<BiogHist> getBiogHistList()
    {
        return this.biogHists;
    }


    /**
     * Get the BiogHist
     *
     * This will by default get the first BiogHist for the entity.  If another
     * language is desired, it may be passed as a parameter.  In that case,
     * the biogHist will be given for that language.  If no biogHist exists
     * for that language, the first will be returned.
     *
     *
     * @return snacdataBiogHist The desired BiogHist for this language, the first
     * BiogHist, or null if no BiogHist exists for this Constellation
     */
    public BiogHist getBiogHist(Language language)
    {
        if (this.biogHists.size() > 0) {
            if (language == null) {
                // No language, so return the first
                return this.biogHists.get(0);
            } else {
                // We have a language.  Start from the end, return matching language or first
                // entry
                int i = this.biogHists.size() - 1;
                for (; i >= 0; i--) {
                    // If languages match, then break and return this biogHist.
                    if (this.biogHists.get(i).getLanguage().getLanguage().getID() ==
                        language.getLanguage().getID())
                        break;
                }
                // Will return either the appropriate biogHist or the biogHist[0]
                return this.biogHists.get(i);
            }
        }
        return null;
    }

    /**
     * Get the constellation relations
     *
     * @return snacdataConstellationRelation[] Constellation relations
     *
     */
    public List<ConstellationRelation> getRelations()
    {
        return this.relations;
    }

    /**
     * Get the resource relations
     *
     * @var snacdataResourceRelation[] Resource relations
     */
    public List<ResourceRelation> getResourceRelations()
    {
        return this.resourceRelations;
    }

    /**
     * Get the Functions
     *
     * @return snacdataSNACFunction[] Functions
     *
     */
    public List<SNACFunction> getFunctions()
    {
        return this.functions;
    }

    /**
     * Get the places
     *
     * Get the places associated with this identity constellation
     *
     * @return snacdataPlace[] Places
     *
     */
    public List<Place> getPlaces()
    {
        return this.places;
    }

    /**
     * Get the subjects
     *
     * @return snacdataSubject[] Subjects
     *
     */
    public List<Subject> getSubjects()
    {
        return this.subjects;
    }

    /**
     * Get all nationalities
     *
     * @return snacdataNationality[] nationalities
     *
     */
    public List<Nationality> getNationalities()
    {
        return this.nationalities;
    }

    /**
     * Get all genders
     *
     * @return snacdataGender[] all genders
     *
     */
    public List<Gender> getGenders()
    {
        return this.genders;
    }

    /**
     * Get all the general contexts
     *
     * @return snacdataGeneralContext[] General Contexts
     *
     */
    public List<GeneralContext> getGeneralContexts()
    {
        return this.generalContexts;
    }

    /**
     * Get the structureOrGenealogies
     *
     * @return snacdataStructureOrGenealogy[] list of Structure Or Genealogy information
     *
     */
    public List<StructureOrGenealogy> getStructureOrGenealogies()
    {
        return this.structureOrGenealogies;
    }

    /**
     * Get the mandates
     *
     * @return snacdataMandate[] list of Mandates
     *
     */
    public List<Mandate> getMandates()
    {
        return this.mandates;
    }

    /**
     * Get the Status
     *
     * Get the status for this Constellation object
     *
     * @return string|NULL Status if one is set, or null if the status is empty
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Get images
     *
     * Returns the list of images associated with this Constellation
     *
     * @return snacdataImage[] List of Image objects
     */
    public List<Image> getImages() {
        return this.images;
    }

    /**
     * Get flags
     *
     * Returns the list of flags associated with this Constellation
     *
     * @return string[] List of Flags
     */
    public List<String> getFlags() {
        return this.flags;
    }

    /**
     * Has Flag Set
     *
     * Checks whether the flag is set and returns true/false.
     *
     * @return boolean True if the flag is set, false otherwise
     */
    public boolean hasFlag(String flag) {
        return this.flags.contains(flag);
    }

    /**
     * Set Flag
     *
     * Sets the given flag in the constellation, if it hasn't already been set
     *
     */
    public void setFlag(String flag) {
        if (!this.hasFlag(flag))
            this.flags.add(flag);
    }


    /**
     * Set the ARK ID
     *
     */
    public void setArk(String ark) {

        this.ark = ark;
    }

    /**
     * Set Entity type
     *
     */
    public void setEntityType(Term type) {

        this.entityType = type;
    }

    /**
     * Adds an alternate record id
     *
     */
    public void addOtherRecordID(SameAs other) {

        this.otherRecordIDs.add(other);
    }

    public void setOtherRecordIDs(List<SameAs> others) {
        this.otherRecordIDs = new ArrayList<>(others);
    }

    /**
     * Add an alternate entityId
     *
     */
    public void addEntityID(EntityId other) {

        this.entityIDs.add(other);
    }

    public void setEntityIDs(List<EntityId> others) {
        this.entityIDs = new ArrayList<>(others);
    }

    /**
     * Set maintenance status
     *
     */
    public void setMaintenanceStatus(Term status) {

        this.maintenanceStatus = status;
    }

    /**
     * Set maintenance agency
     *
     */
    public void setMaintenanceAgency(String agency) {

        this.maintenanceAgency = agency;
    }

    /**
     * Adds a source to the list of sources for this constellation
     *
     */
    public void addSource(Source source) {

        this.sources.add(source);
    }


    /**
     * Sets all sources to the list of sources fin the parameter
     *
     */
    public void setSources(List<Source> sources) {

        this.sources = new ArrayList<>(sources);
    }

    /**
     * Add a maintenance event
     *
     */
    public void addMaintenanceEvent(MaintenanceEvent event) {

        this.maintenanceEvents.add(event);
    }

    public void setMaintenanceEvents(List<MaintenanceEvent> m) {
        this.maintenanceEvents = new ArrayList<>(m);
    }

    /**
     * Add a convention declaration
     *
     */
    public void addConventionDeclaration(ConventionDeclaration declaration) {

        this.conventionDeclarations.add(declaration);
    }

    public void setConventionDeclarations(List<ConventionDeclaration> c) {
        this.conventionDeclarations = new ArrayList<>(c);
    }

    /**
     * Adds a name entry to the known entries for this constellation
     *
     */
    public void addNameEntry(NameEntry nameEntry) {

        this.nameEntries.add(nameEntry);
    }

    /**
     * Set the list of name entries
     *
     * Sets the list of name entries to the list provided.  This will overwrite any other
     * name entries currently set for this constellation.
     *
     */
    public void setNameEntries(List<NameEntry> nameEntries) {

        this.nameEntries = new ArrayList<>(nameEntries);
    }

    /**
     * Set the preferred name entry
     *
     * Sets the preferred name entry for this constellation.  The name entry to prefer
     * MUST already be in the list of name entries.  If it does not appear, this method
     * will fail.
     */
    public boolean setPreferredNameEntry(NameEntry nameEntry) {
        if (this.nameEntries.contains(nameEntry)) {
            this.preferredNameEntry = nameEntry;
            return true;
        }
        return false;

    }

    /**
     * Add biogHist entry
     *
     */
    public void addBiogHist(BiogHist biog) {

        this.biogHists.add(biog);
    }

    /**
     * Remove all BiogHist entries
     */
    public void removeAllBiogHists() {
        this.biogHists = new ArrayList<>();
    }

    public void setBiogHists(List<BiogHist> bios) {
        this.biogHists = new ArrayList<>(bios);
    }

    /**
     * Add occupation
     *
     */
    public void addOccupation(Occupation occupation) {

        this.occupations.add(occupation);
    }

    public void setOccupations(List<Occupation> occs) {
        this.occupations = new ArrayList<>(occs);
    }

    /**
     * Add function
     *
     */
    public void addFunction(SNACFunction function) {

        this.functions.add(function);
    }

    public void setFunctions(List<SNACFunction> funs) {
        this.functions = new ArrayList<>(funs);
    }

    /**
     * Add Language Used
     *
     * Add a language used by this constellation's identity.
     *
     */
    public void addLanguageUsed(Language language) {
        this.languagesUsed.add(language);
    }

    public void setLanguagesUsed(List<Language> langs) {
        this.languagesUsed = new ArrayList<>(langs);
    }


    /**
     * Add a subject
     *
     */
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void setSubjects(List<Subject> subs) {
        this.subjects = new ArrayList<>(subs);
    }

    /**
     * Add a nationality
     *
     */
    public void addNationality(Nationality nationality) {
        this.nationalities.add(nationality);
    }

    public void setNationalities(List<Nationality> nats) {
        this.nationalities = new ArrayList<>(nats);
    }

    /**
     * Add a gender
     *
     */
    public void addGender(Gender gender) {
        this.genders.add(gender);
    }

    /**
     * Set the gender
     *
     * Set the gender of this Constellation to be this sole gender.
     * Removes all the other genders.
     *
     */
    public void setGender(Gender gender) {
        this.genders = new ArrayList<>();
        this.genders.add(gender);
    }

    public void setGenders(List<Gender> gens) {
        this.genders = new ArrayList<>(gens);
    }

    /**
     * Add relation to another constellation
     *
     */
    public void addRelation(ConstellationRelation relation) {

        this.relations.add(relation);
    }

    /**
     * Empty Constellation Relations
     *
     * Drops all constellation relations for this constellation
     *
     */
    public void emptyRelations() {
        this.relations = new ArrayList<>();
    }

    public void setRelations(List<ConstellationRelation> rels) {
        this.relations = new ArrayList<>(rels);
    }


    /**
     * Add relation to a resource
     */
    public void addResourceRelation(ResourceRelation relation) {

        this.resourceRelations.add(relation);
    }

    /**
     * Empty Resource Relations
     *
     * Drop all resource relations for this constellation.
     *
     */
    public void emptyResourceRelations() {
        this.resourceRelations = new ArrayList<>();
    }

    public void setResourceRelations(List<ResourceRelation> rels) {
        this.resourceRelations = new ArrayList<>(rels);
    }

    /**
     * Add a place
     *
     */
    public void addPlace(Place place) {

        this.places.add(place);
    }

    public void setPlaces(List<Place> places) {
        this.places = new ArrayList<>(places);
    }

    /**
     * Add a general context
     *
     */
    public void addGeneralContext(GeneralContext context) {
        this.generalContexts.add(context);
    }

    public void setGeneralContexts(List<GeneralContext> gcs) {
        this.generalContexts = new ArrayList<>(gcs);
    }

    /**
     * Add a structure or genealogy
     *
     */
    public void addStructureOrGenealogy(StructureOrGenealogy structure) {
        this.structureOrGenealogies.add(structure);
    }

    public void setStructureOrGenealogies(List<StructureOrGenealogy> sogs) {
        this.structureOrGenealogies = new ArrayList<>(sogs);
    }

    /**
     * Add a legal status
     *
     */
    public void addLegalStatus(LegalStatus legalStatus) {
        this.legalStatuses.add(legalStatus);
    }

    public void setLegalStatuses(List<LegalStatus> ls) {
        this.legalStatuses = new ArrayList<>(ls);
    }

    /**
     * Add a mandate
     *
     */
    public void addMandate(Mandate mandate) {
        this.mandates.add(mandate);
    }

    public void setMandates(List<Mandate> mans) {
        this.mandates = new ArrayList<>(mans);
    }

    /**
     * Set the Status
     *
     * Set the status of this Constellation object
     *
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Add an image
     *
     */
    public void addImage(Image image) {
        this.images.add(image);
    }

    public void setImages(List<Image> ims) {
        this.images = new ArrayList<>(ims);
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof Constellation))
            return false;

        if (!super.equals(other))
            return false;

        Constellation c = (Constellation) other;

        if ((this.getArk() != null && ! this.getArk().equals(c.getArk())) ||
                (this.getArk() == null && c.getArk() != null))
            return false;

        if ((this.getEntityType() != null && ! this.getEntityType().equals(c.getEntityType())) ||
                 (this.getEntityType() == null && c.getEntityType() != null))
            return false;

        /**
         * Currently, we are not checking the maintenance events or images for equality
        *    if (this.getMaintenanceAgency() != other->getMaintenanceAgency())
        *        return false;
        *    if ((this.getMaintenanceStatus() != null && ! this.getMaintenanceStatus()->equals(other->getMaintenanceStatus(), strict, checkSubcomponents)) ||
        *         (this.getMaintenanceStatus() == null && other->getMaintenanceStatus() != null))
        *        return false;
        *    if (!this.checkArrayEqual(this.getMaintenanceEvents(), other->getMaintenanceEvents(), strict, checkSubcomponents))
        *        return false;
        *    if (!this.checkArrayEqual(this.getImages(), other->getImages(), strict, checkSubcomponents))
        *        return false;
        **/

        boolean strict = true;
        boolean checkSubcomponents = true;

        if (!this.checkArrayEqual(this.getOtherRecordIDs(), c.getOtherRecordIDs(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getEntityIDs(), c.getEntityIDs(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getSources(), c.getSources(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getLegalStatuses(), c.getLegalStatuses(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getConventionDeclarations(), c.getConventionDeclarations(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getLanguagesUsed(), c.getLanguagesUsed(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getNameEntries(), c.getNameEntries(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getOccupations(), c.getOccupations(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getBiogHistList(), c.getBiogHistList(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getRelations(), c.getRelations(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getResourceRelations(), c.getResourceRelations(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getFunctions(), c.getFunctions(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getPlaces(), c.getPlaces(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getSubjects(), c.getSubjects(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getNationalities(), c.getNationalities(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getGenders(), c.getGenders(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getGeneralContexts(), c.getGeneralContexts(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getStructureOrGenealogies(), c.getStructureOrGenealogies(), strict, checkSubcomponents))
            return false;
        if (!this.checkArrayEqual(this.getMandates(), c.getMandates(), strict, checkSubcomponents))
            return false;

        return true;
    }

    private boolean checkArrayEqual(List a, List b, boolean strict, boolean checkSub) {
        // note: checkSub and strict are currently unused.
        return a.containsAll(b) && b.containsAll(a);
    }

    /**
     * Is Constellation Empty
     *
     * Checkes whether this constellation is empty.
     *
     * @return boolean True if empty, false otherwise
     */
    public boolean isEmpty() {
        Constellation emptyConstellation = new Constellation();

        return this.equals(emptyConstellation);
    }

    /**
     * Collect SCM Citations by Source
     *
     * This method parses through each section of the constellation, looking for SCMs
     * and attaching them to the Source they cite.  This allows for the caller to ask
     * for the list of sources (and associated SCMs) for this constellation.
     *
     * @return snacdataSource[] A list of Source objects with their SCMs filled out
    public void collateAllSCMCitationsBySource() {
        sources = array();
        // Do a deep copy of the sources
        foreach (this.sources as source) {
            sources[source->getID()] = new snacdataSource(source->toArray());
        }
        sources[0] = new snacdataSource();
        sources[0]->setDisplayName("Unknown Source");

        parent::collateSCMCitationsBySource(sources);

        foreach (this.mandates as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.structureOrGenealogies as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.generalContexts as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.biogHists as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.conventionDeclarations as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.nationalities as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.otherRecordIDs as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.entityIDs as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.languagesUsed as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.legalStatuses as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.sources as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.genders as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.nameEntries as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.occupations as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.relations as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.resourceRelations as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.functions as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.places as &element)
            element->collateSCMCitationsBySource(sources);

        foreach (this.subjects as &element)
            element->collateSCMCitationsBySource(sources);


        return sources;
    }
     */

    /**
     * Update All SCM Citations
     *
     * This method goes through each section of the constellation, looking for SCMs and
     * updating their citations.  If the SCM points to oldSource, this method will update
     * it to point to newSource.
     *
     * @param  snacdataSource oldSource Source to replace
     * @param  snacdataSource newSource Source to replace with
    public function updateAllSCMCitations(oldSource, newSource) {
        parent::updateSCMCitation(oldSource, newSource);

        foreach (this.mandates as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.structureOrGenealogies as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.generalContexts as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.biogHists as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.conventionDeclarations as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.nationalities as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.otherRecordIDs as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.entityIDs as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.languagesUsed as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.legalStatuses as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.sources as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.genders as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.nameEntries as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.occupations as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.relations as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.resourceRelations as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.functions as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.places as &element)
            element->updateSCMCitation(oldSource, newSource);

        foreach (this.subjects as &element)
            element->updateSCMCitation(oldSource, newSource);
    }
     */

    /**
     * Perform a diff
     *
     * Compares this constellation to the "other."  This method produces a "diff" of the Constellation,
     * creating three new constellations.  First, it produces an intersection, which contains all bits
     * that in both this and other (note first-level data must be the same -- it does not make sense to
     * keep Name Components or SCMs that are the same without their containing NameEntry).  Second,
     * the "this" and "other" return Constellations contain the parts of `this` and `other` that are NOT
     * included in the intersection.  If any of the return constellations would be empty, they will be
     * returned as `null` instead.
     *
     * This method does NOT diff maintenance history, maintenance status, or images.
     *
     * @param  snacdataConstellation other Constellation object to diff
     * @param boolean strict optional If true, will check IDs and Versions.  Else (default) only checks data
     * @param boolean checkSubcomponents optional Whether or not to check SNACControlMetadata, nameEntries contributors & components
     * @return snacdataConstellation[] Associative array of "intersection," "this," and "other" Constellations.
    public Constellation[] diff(Constellation other, boolean strict, boolean checkSubcomponents) {
        return = array (
            "intersection" => null,
            "this" => null,
            "other" => null
        );

        if (other == null || ! (other instanceof Constellation)) {
            return["this"] = this;
            return return;
        }

        intersection = new snacdataConstellation();
        first = new snacdataConstellation();
        second = new snacdataConstellation();


        if (this.getArk() === other->getArk()) {
            intersection->setArkID(this.getArk());
        }

        if (this.getEntityType() != null && this.getEntityType()->equals(other->getEntityType(), strict, checkSubcomponents)) {
            intersection->setEntityType(this.getEntityType());
        }

        result = this.diffArray(this.getOtherRecordIDs(), other->getOtherRecordIDs(), strict, checkSubcomponents);
        intersection->otherRecordIDs = result["intersection"];
        first->otherRecordIDs = result["first"];
        second->otherRecordIDs = result["second"];

        result = this.diffArray(this.getEntityIDs(), other->getEntityIDs(), strict, checkSubcomponents);
        intersection->entityIDs = result["intersection"];
        first->entityIDs = result["first"];
        second->entityIDs = result["second"];

        result = this.diffArray(this.getSources(), other->getSources(), strict, checkSubcomponents);
        intersection->sources = result["intersection"];
        first->sources = result["first"];
        second->sources = result["second"];

        result = this.diffArray(this.getLegalStatuses(), other->getLegalStatuses(), strict, checkSubcomponents);
        intersection->legalStatuses = result["intersection"];
        first->legalStatuses = result["first"];
        second->legalStatuses = result["second"];

        result = this.diffArray(this.getConventionDeclarations(), other->getConventionDeclarations(), strict, checkSubcomponents);
        intersection->conventionDeclarations = result["intersection"];
        first->conventionDeclarations = result["first"];
        second->conventionDeclarations = result["second"];

        result = this.diffArray(this.getLanguagesUsed(), other->getLanguagesUsed(), strict, checkSubcomponents);
        intersection->languagesUsed = result["intersection"];
        first->languagesUsed = result["first"];
        second->languagesUsed = result["second"];

        result = this.diffArray(this.getNameEntries(), other->getNameEntries(), strict, checkSubcomponents);
        intersection->nameEntries = result["intersection"];
        first->nameEntries = result["first"];
        second->nameEntries = result["second"];

        result = this.diffArray(this.getOccupations(), other->getOccupations(), strict, checkSubcomponents);
        intersection->occupations = result["intersection"];
        first->occupations = result["first"];
        second->occupations = result["second"];

        result = this.diffArray(this.getBiogHistList(), other->getBiogHistList(), strict, checkSubcomponents);
        intersection->biogHists = result["intersection"];
        first->biogHists = result["first"];
        second->biogHists = result["second"];

        result = this.diffArray(this.getRelations(), other->getRelations(), strict, checkSubcomponents);
        intersection->relations = result["intersection"];
        first->relations = result["first"];
        second->relations = result["second"];

        result = this.diffArray(this.getResourceRelations(), other->getResourceRelations(), strict, checkSubcomponents);
        intersection->resourceRelations = result["intersection"];
        first->resourceRelations = result["first"];
        second->resourceRelations = result["second"];

        result = this.diffArray(this.getFunctions(), other->getFunctions(), strict, checkSubcomponents);
        intersection->functions = result["intersection"];
        first->functions = result["first"];
        second->functions = result["second"];

        result = this.diffArray(this.getPlaces(), other->getPlaces(), strict, checkSubcomponents);
        intersection->places = result["intersection"];
        first->places = result["first"];
        second->places = result["second"];

        result = this.diffArray(this.getSubjects(), other->getSubjects(), strict, checkSubcomponents);
        intersection->subjects = result["intersection"];
        first->subjects = result["first"];
        second->subjects = result["second"];

        result = this.diffArray(this.getNationalities(), other->getNationalities(), strict, checkSubcomponents);
        intersection->nationalities = result["intersection"];
        first->nationalities = result["first"];
        second->nationalities = result["second"];

        result = this.diffArray(this.getGenders(), other->getGenders(), strict, checkSubcomponents);
        intersection->genders = result["intersection"];
        first->genders = result["first"];
        second->genders = result["second"];

        result = this.diffArray(this.getGeneralContexts(), other->getGeneralContexts(), strict, checkSubcomponents);
        intersection->generalContexts = result["intersection"];
        first->generalContexts = result["first"];
        second->generalContexts = result["second"];

        result = this.diffArray(this.getStructureOrGenealogies(), other->getStructureOrGenealogies(), strict, checkSubcomponents);
        intersection->structureOrGenealogies = result["intersection"];
        first->structureOrGenealogies = result["first"];
        second->structureOrGenealogies = result["second"];

        result = this.diffArray(this.getMandates(), other->getMandates(), strict, checkSubcomponents);
        intersection->mandates = result["intersection"];
        first->mandates = result["first"];
        second->mandates = result["second"];

        result = this.diffArray(this.getDateList(), other->getDateList(), strict, checkSubcomponents);
        intersection->dateList = result["intersection"];
        first->dateList = result["first"];
        second->dateList = result["second"];

        result = this.diffArray(this.getSNACControlMetadata(), other->getSNACControlMetadata(), strict, checkSubcomponents);
        intersection->snacControlMetadata = result["intersection"];
        first->snacControlMetadata = result["first"];
        second->snacControlMetadata = result["second"];

        if (!intersection->isEmpty())
            return["intersection"] = intersection;

        if (!first->isEmpty()) {
            first->setID(this.getID());
            first->setVersion(this.getVersion());
            first->setArkID(this.getArk());
            first->setEntityType(this.getEntityType());
            return["this"] = first;
        }

        if (!second->isEmpty()) {
            second->setID(other->getID());
            second->setVersion(other->getVersion());
            second->setArkID(other->getArk());
            second->setEntityType(other->getEntityType());
            return["other"] = second;
        }

        return return;
    }
     */

    /**
     * Combine Into
     *
     * Combines the data from the Constellation passed in into this Constellation,
     * removing IDs and version numbers for all elements except sources.  Sets all
     * other operations to INSERT so they are considered new in this Constellation.
     *
     * @return boolean True if success, false otherwise (currently no failure)
    public void combine(Constellation other) {
        //      merge the sources, keeping a list of other's sources
        // do a diff of this and other (strict = false)
        // update the "other" sources, if we can?
        // foreach over the other and combine with this (they won't overlap: not in intersection)
        //      - remove other's ID, Version, and set operation = INSERT
        // return

        diff = this.diff(other, false);
        combine = diff["other"];

        if (combine == null || combine->isEmpty()) {
            return true;
        }

        // Dates, gained by AbstractData
        foreach (combine->getDateList() as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addDate(element);
        }

        // SCM, gained by AbstractData (but shouldn't be used on the high-level constellation)
        foreach (combine->snacControlMetadata as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addSNACControlMetadata(element);
        }

        foreach (combine->sources as &element) {
            // Sources need their IDs in tact if we plan to fix up SCMs
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addSource(element);
        }

        foreach (combine->mandates as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addMandate(element);
        }

        foreach (combine->structureOrGenealogies as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addStructureOrGenealogy(element);
        }

        foreach (combine->generalContexts as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addGeneralContext(element);
        }

        foreach (combine->biogHists as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            // Add if a new one, append to the first bioghist if not
            if (empty(this.biogHists)) {
                this.addBiogHist(element);
            } else {
                this.biogHists[0]->append(element);
            }
        }

        foreach (combine->conventionDeclarations as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addConventionDeclaration(element);
        }

        foreach (combine->nationalities as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addNationality(element);
        }

        foreach (combine->otherRecordIDs as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addOtherRecordID(element);
        }

        foreach (combine->entityIDs as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addEntityID(element);
        }

        foreach (combine->languagesUsed as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addLanguageUsed(element);
        }

        foreach (combine->legalStatuses as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addLegalStatus(element);
        }

        foreach (combine->genders as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addGender(element);
        }

        foreach (combine->nameEntries as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addNameEntry(element);
        }

        foreach (combine->occupations as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addOccupation(element);
        }

        foreach (combine->relations as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addRelation(element);
        }

        foreach (combine->resourceRelations as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addResourceRelation(element);
        }

        foreach (combine->functions as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addFunction(element);
        }

        foreach (combine->places as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addPlace(element);
        }

        foreach (combine->subjects as &element) {
            element->setID(null);
            element->setVersion(null);
            element->setOperation(snacdataAbstractData::OPERATION_INSERT);
            element->cleanseSubElements();
            this.addSubject(element);
        }

        return true;
    }
     */

    public void cleanseSubElements(String operation) {
        super.cleanseSubElements(operation);


        // SCM, gained by AbstractData (but shouldn't be used on the high-level constellation)
        for (AbstractData element : snacControlMetadata) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : sources) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : mandates) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : structureOrGenealogies) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : generalContexts) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : biogHists) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : conventionDeclarations) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : nationalities) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : otherRecordIDs) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : entityIDs) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : languagesUsed) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : legalStatuses) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : genders) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : nameEntries) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : occupations) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : relations) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : resourceRelations) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : functions) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : places) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        for (AbstractData element : subjects) {
            element.setID(0);
            element.setVersion(0);
            element.setOperation(operation);
            element.cleanseSubElements(operation);
        }

        return;
    }

    public static void main(String[] args) {
        String test = "{\n            \"dataType\": \"SNACDate\",\n            \"fromDate\": \"1860-08-13\",\n            \"fromDateOriginal\": \"1860-08-13\",\n            \"fromType\": {\n                \"id\": \"689\",\n                \"term\": \"Birth\",\n                \"uri\": \"http:\\/\\/socialarchive.iath.virginia.edu\\/control\\/term#Birth\",\n                \"type\": \"date_type\"\n            },\n            \"toDate\": \"1926-11-03\",\n            \"toDateOriginal\": \"1926-11-03\",\n            \"toType\": {\n                \"id\": \"690\",\n                \"term\": \"Death\",\n                \"uri\": \"http:\\/\\/socialarchive.iath.virginia.edu\\/control\\/term#Death\",\n                \"type\": \"date_type\"\n            },\n            \"isRange\": true,\n            \"id\": \"25298391\",\n            \"version\": \"3694520\"\n        "
                + ",\n" +
                "            \"snacControlMetadata\": [\n" +
                "                {\n" +
                "                    \"dataType\": \"SNACControlMetadata\",\n" +
                "                    \"citation\": null,\n" +
                "                    \"subCitation\": null,\n" +
                "                    \"sourceData\": \"Francs-Bourgeois (rue des)\",\n" +
                "                    \"descriptiveRule\": null,\n" +
                "                    \"language\": null,\n" +
                "                    \"object\": null,\n" +
                "                    \"note\": \"Parsed from SNAC EAC-CPF.\",\n" +
                "                    \"id\": \"0\",\n" +
                "                    \"version\": \"0\",\n" +
                "                    \"operation\": null,\n" +
                "                    \"dates\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"dataType\": \"SNACControlMetadata\",\n" +
                "                    \"citation\": null,\n" +
                "                    \"subCitation\": null,\n" +
                "                    \"sourceData\": \"SECOND Francs-Bourgeois (rue des)\",\n" +
                "                    \"descriptiveRule\": null,\n" +
                "                    \"language\": null,\n" +
                "                    \"object\": null,\n" +
                "                    \"note\": \"Parsed from SNAC EAC-CPF.\",\n" +
                "                    \"id\": \"8280096\",\n" +
                "                    \"version\": \"1201577\",\n" +
                "                    \"operation\": null,\n" +
                "                    \"dates\": []\n" +
                "                }\n" +
                "]" + "}";

        try {
            ObjectMapper om = new ObjectMapper();
            SNACDate d = om.readValue(test, SNACDate.class);
            //System.out.println(d.toString());

            for (SNACControlMetadata s : d.getSnacControlMetadata()) {
                //System.out.println(s.getSourceData());
            }
            om.enable(SerializationFeature.INDENT_OUTPUT);
            //System.out.println(om.writeValueAsString(d));

            Constellation c = om.readValue(new File("test/washington.json"), Constellation.class);
            System.out.println(om.writeValueAsString(c));
            System.out.println(c.getDataType());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Constellation readFromFile(File file) {
        try {
            ObjectMapper om = new ObjectMapper();
            //om.enable(SerializationFeature.INDENT_OUTPUT);
            Constellation c = om.readValue(file, Constellation.class);

            return c;
        } catch (Exception e) {
            return null;
        }
    }

    public static Constellation fromJSON(String s) {
        try {
            ObjectMapper om = new ObjectMapper();
            Constellation c = om.readValue(s, Constellation.class);
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    public static String toJSON(Constellation c) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationFeature.INDENT_OUTPUT);
            return om.writeValueAsString(c);
        } catch (Exception e) {
            return null;
        }
    }
}
