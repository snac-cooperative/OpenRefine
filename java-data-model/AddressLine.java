

/**
 * AddressLine Class file
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
 * Address Line Class
 *
 * This class contains the parts of a place's address.
 *
 * @author Robbie Hott
 */
public class AddressLine extends AbstractOrderedComponent {

    /**
     * Constructor.
     */
    public AddressLine() {
        super();
        this.setMaxDateCount(0);
        this.setDataType("AddressLine");
    }
}
