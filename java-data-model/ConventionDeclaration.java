

/**
 * ConventionDeclaration Class
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
 * ConventionDeclaration Class
 *
 * Class that holds ConventionDeclaration Information
 *
 * @author Robbie Hott
 */
public class ConventionDeclaration extends AbstractTextData {

    /**
     * Constructor
     */
    public ConventionDeclaration() {
        super();
        this.setDataType("ConventionDeclaration");
        this.setMaxDateCount(0);
    }

}
