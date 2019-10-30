

/**
 * NameComponent Class file
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
 * Name Component Class
 *
 * This class contains the components of a NameEntry.
 *
 * @author Robbie Hott
 */
public class NameComponent extends AbstractOrderedComponent {

    /**
     * Constructor.
     */
    public NameComponent() {
        super();
        this.setMaxDateCount(0);
        this.setDataType("NameComponent");
    }
}
