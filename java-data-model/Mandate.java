

/**
 * Mandate Class
 *
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
 * Mandate Class
 *
 * Class that holds Mandate Information
 *
 * @author Robbie Hott
 */
public class Mandate extends AbstractTextData {

    /**
     * Constructor
     */
    public Mandate() {
        super();
        this.setDataType("Mandate");
        this.setMaxDateCount(0);
    }

}
