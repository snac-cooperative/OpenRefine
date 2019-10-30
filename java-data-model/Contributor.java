
/**
 * Contributor File
 *
 * Contains the data class for the contributors to names
 *
 * License:
 *
 *
 * @author Tom Laudeman
 * @author Robbie Hott
 * @license https://opensource.org/licenses/BSD-3-Clause BSD 3-Clause
 * @copyright 2015 the Rector and Visitors of the University of Virginia, and
 *            the Regents of the University of California
 */
package org.snaccooperative.data;

/**
 * Contributor Class
 *
 * Stores the contributor name (string) and type (a Term object)
 *
 * @author Tom Laudeman
 * @author Robbie Hott
 *
 */
public class Contributor extends AbstractData {

    /**
     * @var snacdataTerm Type of the contributor
     *
     *
     * From EAC-CPF tag(s):
     * vocabulary id for strings:
     * nameEntry/alternativeForm
     * nameEntry/authorizedForm
     *
     */
    private Term type = null;

    /**
     * @var snacdataTerm Rule the contributor used to define this name entry
     *
     */
    private Term rule = null;

    /**
     * @var string Name of the contributor.
     *
     * A simple string.
     */
    private String name = null;

    /**
     * Constructor
     */
    public Contributor() {
        super();
        this.setMaxDateCount(0);
        this.setDataType("Contributor");
    }

    /**
     * Get the type controlled vocab
     *
     * @return snacdataTerm Type controlled vocabulary term
     *
     */
    public Term getType()
    {
        return this.type;
    }

    /**
     * Set the type controlled vocab
     */
    public void setType(Term type)
    {
        this.type = type;
    }

    /**
     * Get the rule controlled vocab
     *
     * @return snacdataTerm Rule controlled vocabulary term
     *
     */
    public Term getRule()
    {
        return this.rule;
    }

    /**
     * Set the rule controlled vocab
     */
    public void setRule(Term rule)
    {
        this.rule = rule;
    }

    /**
     * Get the name
     *
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Set the name
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     *
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || !(other instanceof Contributor))
            return false;

        if (!super.equals(other))
            return false;

        Contributor c = (Contributor) other;
        if ((this.getName() != null && !(this.getName().equals(c.getName()))) ||
                (this.getName() == null && c.getName() != null))
            return false;

        if ((this.getType() != null && !(this.getType().equals(c.getType()))) ||
                (this.getType() == null && c.getType() != null))
            return false;

        return true;
    }
}
