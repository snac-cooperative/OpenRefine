

/**
 * Reconciliation Result class.
 *
 * Contains the data class for the reconciliation results.
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

import java.util.HashMap;
import java.util.Map;

/**
 * Reconciliation Result class
 *
 * This class holds the data associated with a reconcilation result.  That includes
 * the score for that result, the identity associated with that result, and any
 * additional information related to the process of obtaining the results.
 *
 * @author Robbie Hott
 */
public class ReconciliationResult extends AbstractData {


    /**
     * @var float strength of this result
     */
    private double strength = 0;
    /**
     * @var snacdataConstellation Identity for this result
     */
    private Constellation identity;
    /**
     * @var string[] List of properties for this result
     */
    private Map<String, String> properties;
    /**
     * @var float[] Score vector for this result
     */
    private Map<String, Double> vector;



    /**
     * Constructor
     *
     */
    public ReconciliationResult() {
        super();
        this.setDataType("ReconciliationResult");
        this.properties = new HashMap<>();
        this.vector = new HashMap<>();
    }

    /**
     * Set overall strength for this result
     */
    public void setStrength(double strength) {
        this.strength = strength;
    }

    /**
     * Get overall strength for this result
     * @return float strength
     */
    public double getStrength() {
        return this.strength;
    }

    /**
     * Set the identity constellation for this result
     */
    public void setIdentity(Constellation id) {
        this.identity = id;
    }

    /**
     * Get the constellation associated with this result
     * @return snacdataConstellation Identity constellation
     */
    public Constellation getIdentity() {
        return this.identity;
    }

    /**
     * Set a property value for this result
     */
    public void setProperty(String name, String value) {
        this.properties.put(name ,value);
    }

    /**
     * Get a property for this result
     * @return mixed|NULL Value of the property or null if not found
     */
    public String getProperty(String name) {
        if (this.properties.containsKey(name))
            return this.properties.get(name);
        return null;
    }

    /**
     * Set the score for one test
     */
    public void setScore(String test, double score) {
        this.vector.put(test, score);
    }

    /**
     * Get the score for one test
     *
     * @return float The score, or 0 if not run
     */
    public double getScore(String test) {
        if (this.vector.containsKey(test))
            return this.vector.get(test);
        return 0;
    }

    /**
     * Get the score vector for this result
     * @return string[] score vector
     */
    public Map<String, Double> getVector() {
        return this.vector;
    }

    public void setVector(Map<String, Double> vec) {
        this.vector = new HashMap<String, Double>();
        this.vector.putAll(vec);
    }

    /**
     * Set multiple properties for this result
     *
     */
    public void setMultipleProperties(Map<String, String> properties) {
        this.properties.putAll(properties);
    }

    /**
     * Get all properties for this result
     *
     * @return string[][] List of properties
     */
    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> prop) {
        this.properties = new HashMap<>();
        this.properties.putAll(prop);
    }
}
