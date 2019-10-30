
/**
 * SNAC Date File
 *
 * Contains the date storage class.
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
 * SNACDate class
 *
 * Storage class for dates.
 *
 * @author Robbie Hott
 *
 */
public class SNACDate extends AbstractData {

    /**
     * Begin Date
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/fromDate/@standardDate
     * * date/@standardDate
     *
     * @var string Begin date (if range)
     */
    private String fromDate;

    /**
     * Original begin date string
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/fromDate/
     * * date/
     *
     * @var string Original string given for the from date
     */
    private String fromDateOriginal;

    /**
     * Begin type
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/fromDate/@localType
     * * date/@localType
     *
     * @var snacdataTerm Type of the from date, a full Term object.
     */
    private Term fromType;

    /**
     * Begin date is in BC
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/fromDate/@standardDate (if negative)
     * * date/@standardDate (if negative)
     *
     * @var boolean If the from date is BC
     */
    private boolean fromBC = false;

    /**
     * Range of fuzziness for begin date
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/fromDate/@notBefore
     * * dateRange/fromDate/@notAfter
     * * date/@notBefore
     * * date/@notAfter
     *
     * var string[] From date range
     */
    private Map<String, String> fromRange = new HashMap<>();

    /**
     * End date
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/toDate/@standardDate
     *
     * @var string End date (if range)
     */
    private String toDate;

    /**
     * End date original string
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/toDate/
     *
     * @var string Original string given for the to date
     */
    private String toDateOriginal;

    /**
     * End date type
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/toDate/@localType
     *
     * @var snacdataTerm Type of the "to date", a full Term object.
     */
    private Term toType;

    /**
     * End date is in BC
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/toDate/@standardDate (if negative)
     *
     * @var boolean If the to date is BC
     */
    private boolean toBC = false;

    /**
     * Fuzzy range on End date
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/toDate/@notBefore
     * * dateRange/toDate/@notAfter
     *
     * var string[] To date range
     */
    private Map<String, String> toRange = new HashMap<>();
    //private toRange = array ("notBefore" => null, "notAfter" => null);

    /**
     * If this is a dateRange or just a date
     *
     * @var boolean If this SNACDate object contains a range or a single date
     */
    private boolean isRange = false;

    /**
     * Descriptive Note
     *
     * From EAC-CPF tag(s):
     *
     * * dateRange/descriptiveNote
     * * date/descriptiveNote
     *
     * @var string Note about this date
     */
    private String note;


    public SNACDate() {
        super();
        fromRange.put("notBefore", null);
        fromRange.put("notAfter", null);
        toRange.put("notBefore", null);
        toRange.put("notAfter", null);
    }

    /**
     * Get the machine-parseable from date
     *
     * @return string Begin date (if range)
     */
    public String getFromDate()
    {
        return this.fromDate;
    }

    /**
     * Get the originally-entered human from date
     *
     * There intentionally is no setFromDateOriginal(). Instead call setFromDate() or setDate()
     *
     * @return string Original string given for the from date
     */
    public String getFromDateOriginal()
    {
        return this.fromDateOriginal;
    }

    /**
     * Get from date type
     *
     * Get the type of the from date, such as "Birth"
     *
     * @return snacdataTerm The type for the "from date", a full Term object.
     */
    public Term getFromType()
    {
        return this.fromType;
    }


    /**
     * Is From date BC?
     *
     * Tells if the from date is a BC date. True if in BC, false otherwise.
     *
     * @return boolean If the from date is BC
     */
    public boolean getFromBC()
    {
        return this.fromBC;
    }

    /**
     * Get Begin date fuzzy range
     *
     * Get the fuzziness range on the from date, if it exists
     *
     * @return string[] From date range, array ("notBefore" => null, "notAfter" => null);
     */
    public Map<String, String> getFromRange()
    {
        return this.fromRange;
    }

    /**
     * Get End date
     *
     * Get the machine-actionable to/end date
     *
     * @return string End date (if range)
     */
    public String getToDate()
    {
        return this.toDate;
    }

    /**
     * Get the human-entered end date
     *
     * There intentionally is no setToDateOriginal(). Instead call setToDate().
     *
     * @return string Original string given for the to date
     */
    public String getToDateOriginal()
    {
        return this.toDateOriginal;
    }

    /**
     * Get End date type
     *
     * Get the type of the end date, such as "Death"
     *
     * @return snacdataTerm Type of the "to date", a full Term object.
     */
    public Term getToType()
    {
        return this.toType;
    }

    /**
     * Is To date BC?
     *
     * Tells whether the end date is in BC. True if in BC, false otherwise.
     *
     * @return boolean If the to date is BC
     */
    public boolean getToBC()
    {
        return this.toBC;
    }

    /**
     * Get End date fuzzy range
     *
     * Get the fuzziness range for the end date, if it exists
     *
     * @return string[] To date range, array ("notBefore" => null, "notAfter" => null);
     */
    public Map<String,String> getToRange()
    {
        return this.toRange;
    }

    /**
     * Is this date a range
     *
     * Tells whether this SNACDate object contains a range (from-to) or just a single date (from)
     *
     * Something has broken a couple of times round tripping isRange to the database and back. Make absolutely
     * certain that we only return true and false by only returning true and false based on the truthiness of
     * this.isRange rather than its actual value.
     *
     * @return boolean If this SNACDate object contains a range or a single date
     */
    public boolean getIsRange()
    {
        return this.isRange;
    }

    /**
     * Get descriptive note for this date
     *
     *
     * @return string Note about this date
     */
    public String getNote()
    {
        return this.note;
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
        String str = "Date: ";
        if (this.fromDate != null)
            str += this.fromDate;
        else
            str += this.fromDateOriginal;

        if (this.fromType != null)
            str += " ("+this.fromType.getTerm()+")";

        if (this.isRange) {
            str += " - ";
            if (this.toDate != null)
                str += this.toDate;
            else
                str += this.toDateOriginal;

        if (this.toType != null)
            str += " ("+this.toType.getTerm()+")";
        }

        return str;
    }

    /**
     * Set whether or not this is a date range.
     *
     * Test the truthiness of isRange because this keeps breaking. Postgres and php have a disagreement on
     * what is true, and even using DBUtil functions to convert has not solved the problem
     */
    public void setRange(boolean isRange) {
        this.isRange = isRange;
    }

    /**
     * Set the from date BC
     *
     * Set the before common epoch boolean property of a from date.
     */
    public void setFromBC(boolean arg)
    {
        this.fromBC = arg;
    }

    /**
     * Set the to date BC
     *
     * Set the before common epoch boolean property of a to date.
     */
    public void setToBC(boolean arg)
    {
        this.toBC = arg;
    }

    /**
     * Set the date BC
     *
     * Set the before common epoch boolean property of a single date. It is likely that implementation of a
     * single date vs date range is a from date and isRange = false. Regardless of implementation, this
     * function changes the BC for a single date.
     *
     * Current implementation is to call setFromBC().
     *
     * Use this when you want to set (or clear) the BC boolean (fromBC) after calling setDate().
     */
    public void setBC(boolean arg)
    {
        this.setFromBC(arg);
    }

    /**
     * Set the "from date"
     *
     * Set the from date in this object, as well as setting some related private variables.
     *
     * If you need to set BC, but you have BC in a separate field, call this with the non-BC annotated date,
     * then call setFromBC(), setToBC() or setBC().
     */
    public void setFromDate(String original, String standardDate, Term type) {
        this.fromDate = this.parseBC(standardDate);
        this.fromBC = !this.fromDate.equals(standardDate);
        this.fromDateOriginal = original;
        this.fromType = type;
    }

    /**
     * Set the fuzzy range around the from date
     */
    public void setFromDateRange(String notBefore, String notAfter) {
        this.fromRange.put("notBefore",notBefore);
        this.fromRange.put("notAfter",notAfter);
    }

    /**
     * Set the "to date"
     *
     * Set the to date in this object, as well as setting some related private variables.
     */
    public void setToDate(String original, String standardDate, Term type) {
        this.toDate = this.parseBC(standardDate);
        this.toBC = !this.toDate.equals(standardDate);
        this.toDateOriginal = original;
        this.toType = type;
    }

    /**
     * Set the fuzzy range around the to date
     */
    public void setToDateRange(String notBefore, String notAfter) {
        this.toRange.put("notBefore", notBefore);
        this.toRange.put("notAfter", notAfter);
    }

    /**
     * Set the "single date"
     *
     * Set this object as a single date. Single date is current the from date, and the date isRange is set
     * to false. This sets several private variables.
     */
    public void setDate(String original, String standardDate, Term type) {
        this.setFromDate(original, standardDate, type);
        this.isRange = false;
    }

    /**
     * Set the fuzzy range around the single date
     */
    public void setDateRange(String notBefore, String notAfter) {
        this.setFromDateRange(notBefore, notAfter);
    }

    /**
     * Set note about this date
     *
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Parse a date for BC
     *
     * Parse the given standard date string and determine if the date is BC and strip the date out if possible.
     */
    public String parseBC(String standardDate) {

        String tmp = standardDate;
        // If the standardDate starts with a minus sign, it is BC
        if (standardDate.charAt(0) == '-') {
            tmp = standardDate.substring(1);
        }
        return tmp;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof SNACDate))
            return false;

        if (! super.equals(other))
            return false;

        SNACDate d = (SNACDate) other;
        if (this.getFromBC() != d.getFromBC())
            return false;
        if (this.getToBC() != d.getToBC())
            return false;
        if (this.getIsRange() != d.getIsRange())
            return false;
        if ((this.getFromDate() != null && ! this.getFromDate().equals(d.getFromDate())) ||
                (this.getFromDate() == null && d.getFromDate() != null))
            return false;
        if ((this.getFromDateOriginal() != null && ! this.getFromDateOriginal().equals(d.getFromDateOriginal())) ||
                (this.getFromDateOriginal() == null && d.getFromDateOriginal() != null))
            return false;
        if ((this.getToDateOriginal() != null && ! this.getToDateOriginal().equals(d.getToDateOriginal())) ||
                (this.getToDateOriginal() == null && d.getToDateOriginal() != null))
            return false;
        if ((this.getToDate() != null && ! this.getToDate().equals(d.getToDate())) ||
                (this.getToDate() == null && d.getToDate() != null))
            return false;
        if ((this.getNote() != null && ! this.getNote().equals(d.getNote())) ||
                (this.getNote() == null && d.getNote() != null))
            return false;


        // handle ranges
        if (this.getFromRange() != null && d.getFromRange() != null) {
            if (!this.getFromRange().get("notAfter").equals(d.getFromRange().get("notAfter")) ||
                    !this.getFromRange().get("notBefore").equals(d.getFromRange().get("notBefore")))
                return false;
        } else if ((this.getFromRange() == null && d.getFromRange() != null) ||
           (this.getFromRange() != null && d.getFromRange() == null)) {
            return false;
        }

        if (this.getToRange() != null && d.getToRange() != null) {
            if (!this.getToRange().get("notAfter").equals(d.getToRange().get("notAfter")) ||
                    !this.getToRange().get("notBefore").equals(d.getToRange().get("notBefore")))
                return false;
        } else if ((this.getToRange() == null && d.getToRange() != null) ||
                (this.getToRange() != null && d.getToRange() == null)) {
            return false;
        }


        if ((this.getFromType() != null && ! this.getFromType().equals(d.getFromType())) ||
                 (this.getFromType() == null && d.getFromType() != null))
            return false;
        if ((this.getToType() != null && ! this.getToType().equals(d.getToType())) ||
                 (this.getToType() == null && d.getToType() != null))
            return false;

        return true;
    }
}
