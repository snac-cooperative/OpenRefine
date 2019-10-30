
/**
 * Name Entry File
 *
 * Contains the information about an individual name entry.
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

import java.util.ArrayList;
import java.util.List;

/**
 * NameEntry Class
 *
 * Storage class for name entries.
 *
 * @author Robbie Hott
 *
 */
public class NameEntry extends AbstractData {

    /**
     * Original string for the name
     *
     * From EAC-CPF tag(s):
     *
     * * nameEntry/part
     *
     * @var string Original name given in this entry
     */
    private String original;

    /**
     * Preference Score
     *
     * From EAC-CPF tag(s):
     *
     * * nameEntry/@preferenceScore
     *
     * @var float Preference score given to this entry
     */
    private double preferenceScore;

    /**
     * Component List
     *
     * @var snacdataNameComponent[] List of Name Components
     */
    private List<NameComponent> components;

    /**
     * Contributor List
     *
     * From EAC-CPF tag(s):
     *
     * 'type' as a string:
     * * nameEntry/alternativeForm
     * * nameEntry/authorizedForm
     *
     * 'contributor' name value as a string
     *
     * @var snacdataContributor[] List of Contributor
     */
    private List<Contributor> contributors;

    /**
     * Language
     *
     * From EAC-CPF tag(s):
     *
     * * nameEntry/@lang
     * * nameEntry/@scriptcode
     *
     * @var snacdataLanguage Language of the entry
     */
    private Language language;

    /**
     * Constructor.
     *
     * @return NameEntry object
     *
     */
    public NameEntry() {
        super();
        this.setDataType("NameEntry");
        this.components = new ArrayList<>();
        this.contributors = new ArrayList<>();
        this.setMaxDateCount(1);
    }

    /**
     * Get the original
     *
     * Get the original (full combined nameString/header) for this name Entry
     *
     * @return string Original name given in this entry
     *
     *
     */
    public String getOriginal()
    {
        return this.original;
    }

    /**
     * Get the SNAC preference score
     *
     *  Get the preference score for display of this name Entry
     *
     * @return float Preference score given to this entry
     *
     *
     */
    public double getPreferenceScore()
    {
        return this.preferenceScore;
    }

    /**
     * Get the list of components for this name entry
     *
     * @return snacdataNameComponent[] Components providing this name entry including their type for this name entry
     *
     */
    public List<NameComponent> getComponents()
    {
        return this.components;
    }

    /**
     * Get the list of contributors for this name entry
     *
     * @return snacdataContributor[] Contributors providing this name entry including their type for this name entry
     *
     */
    public List<Contributor> getContributors()
    {
        return this.contributors;
    }

    /**
     * Get the language that this name entry is written in (language and script)
     *
     * @return snacdataLanguage Language of the entry. If you then call the Language object's getLanguage()
     * it returns a Term object. Language getScript() returns a Term object for the script.
     *
     */
    public Language getLanguage()
    {
        return this.language;
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
        return "Name Entry: " + this.original;
    }

    /**
     * Set the original name.
     *
     */
    public void setOriginal(String original) {

        this.original = original;
    }

    /**
     * Set the language
     *
     */
    public void setLanguage(Language lang) {
        this.language = lang;
    }

    /**
     * Add component to the list of components.
     *
     */
    public void addComponent(NameComponent component) {

        this.components.add(component);
    }

    /**
     * Add contributor to the list of contributors.
     *
     */
    public void addContributor(Contributor contributor) {

        this.contributors.add(contributor);
    }

    public void setComponents(List<NameComponent> components) {
        this.components = new ArrayList<>(components);
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = new ArrayList<>(contributors);
    }

    /**
     * Set the preference score.
     *
     */
    public void setPreferenceScore(double score) {

        this.preferenceScore = score;
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof NameEntry))
            return false;

        if (!super.equals(other))
            return false;

        NameEntry n = (NameEntry) other;
        if ((this.getOriginal() != null && ! this.getOriginal().equals(n.getOriginal())) ||
                (this.getOriginal() == null && n.getOriginal() != null))
            return false;
        if (this.getPreferenceScore() != n.getPreferenceScore())
            return false;

        if ((this.getLanguage() != null && ! this.getLanguage().equals(n.getLanguage())) ||
                 (this.getLanguage() == null && n.getLanguage() != null))
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

        for (Contributor c : this.contributors) {
            c.setID(0);
            c.setVersion(0);
            c.setOperation(newOperation);
            c.cleanseSubElements(newOperation);
        }

        for (NameComponent c : this.components) {
            c.setID(0);
            c.setVersion(0);
            c.setOperation(newOperation);
            c.cleanseSubElements(newOperation);
        }
        if (this.language != null) {
            this.language.setID(0);
            this.language.setVersion(0);
            this.language.setOperation(newOperation);
            this.language.cleanseSubElements(newOperation);
        }
    }
}
