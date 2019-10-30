

/**
 * LegalStatus Class
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
 * LegalStatus Class
 *
 * Class that holds LegalStatus Information
 *
 * @author Robbie Hott
 */
public class LegalStatus extends AbstractTermData {

    /**
     * Constructor
     */
    public LegalStatus() {
        super();
        this.setDataType("legalStatus");
        this.setMaxDateCount(0);
    }

}
