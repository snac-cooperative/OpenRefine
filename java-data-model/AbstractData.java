

/**
 * Abstract data object class.
 *
 * Contains the abstract class for the basis for any other data classes.
 *
 * License:
 *
 *
 * @author Robbie Hott
 * @author Tom Laudeman
 * @license https://opensource.org/licenses/BSD-3-Clause BSD 3-Clause
 * @copyright 2015 the Rector and Visitors of the University of Virginia, and
 *            the Regents of the University of California
 */

package org.snaccooperative.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
//this is test
/**
 * Abstract data class
 *
 * This class provides methods to read, construct, and export to JSON.  It also
 * requires inheriting classes to include toArray and fromArray functions that
 * will convert the data object to and from associative arrays.  It provides a
 * default constructor that may take an array as a parameter to fill the object.
 *
 * @author Robbie Hott
 * @author Tom Laudeman
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public abstract class AbstractData {

    /**
     * Constants associated with all data
     * @var string OPERATION_INSERT the insert operation
     */
    public static final String OPERATION_INSERT = "insert";
    /**
     * Constants associated with all data
     * @var string OPERATION_UPDATE the update operation
     */
    public static final String OPERATION_UPDATE = "update";
    /**
     * Constants associated with all data
     * @var string OPERATION_DELETE the delete operation
     */
    public static final String OPERATION_DELETE = "delete";

    public static final int MAX_LIST_SIZE = Integer.MAX_VALUE;

    /**
     * @var string dataType The data type of this object.
     *
     * This should be overwritten by any inheriting/child class
     */
    @JsonInclude(JsonInclude.Include.ALWAYS)
    protected String dataType;

    /**
     *
     * The record id, or constellation id for this class. This has two different meanings, depending on the
     * class. For Constellation.php this is the ic_id of the constellation aka version_history.ic_id. For
     * all other classes this is table.id, which is the record id, not the constellation id.
     *
     * @var int id
     */
    protected int id = 0;


    /**
     *
     * The record version number, or constellation version (max) for this class. For Constellation.php this is
     * the "constellation version number" aka max(version) aka max(version_history.id). For all other classes,
     * this is the table.version which is a per-record version number, <= the constellation version number.
     *
     * @var int version
     */
    protected int version = 0;


    /**
     * @var snacdataSNACDate[] dateList Universal date object list.
     *
     *
     */
    @JsonProperty("dates")
    protected List<SNACDate> dateList;

    /**
     * How many dates might be in the dateList. Objects with no dates set this to some number greater than zero.
     *
     * @var int maxDateCount maximum number of dates allowed in this object
     */
    protected int maxDateCount = 0;


    /**
     * var snacdataSNACControlMetadata[] snacControlMetadata The snac control metadata entries for this piece of data.
     */
    protected List<SNACControlMetadata> snacControlMetadata;

    /**
     * @var string Operation for this object.  Must be set to one of the constant values or null.
     */
    protected String operation;

    /**
     * Constructor
     */
    public AbstractData() {
        this.snacControlMetadata = new ArrayList<>();
        this.dateList = new ArrayList<>();
    }

    /**
     * Is Equal
     *
     * This function tests whether the current object is equal to the parameter.  They
     * must match exactly.  It allows for a parameter to enable skipping of the ID/version/operation
     * matching.
     */
    public boolean equals(Object other) {

        if (other == null || !(other instanceof AbstractData))
            return false;

        AbstractData a = (AbstractData) other;
        if (this.getID() != a.getID())
            return false;
        if (this.getVersion() != a.getVersion())
            return false;
        //if (this.getOperation() != other->getOperation())
        //    return false;


        if (this.getMaxDateCount() != a.getMaxDateCount())
            return false;

        if (this.getMaxDateCount() > 0) {
            if (!(this.getDateList().containsAll(a.getDateList()) && a.getDateList().containsAll(this.getDateList())))
                return false;
        }

        // If all the tests pass, they are equal
        return true;
    }

    /**
     * Array Equality
     *
     * Checks that two arrays are equal.  Specifically, tests that the second array has all
     * the same objects as the first.
     *
     * @param snacdataAbstractData[] first first array
     * @param snacdataAbstractData[] second second array
     * @param boolean strict optional whether or not to check ID/Version/Operation
     * @param boolean checkSubcomponents optional Whether or not to check SNACControlMetadata, nameEntries contributors & components
     * @return boolean true if equal, false otherwise
    protected function checkArrayEqual(first, second, strict = true, checkSubcomponents = true) {
        if (first == null && second == null)
            return true;
        if (first == null || second == null)
            return false;
        if (count(first) != count(second))
            return false;

        tmp = array();

        foreach (first as data) {
            foreach (second as k => odata) {
                if (((data == null && odata == null) || (data != null && data->equals(odata, strict, checkSubcomponents)))
                        && !isset(tmp[k])) {
                    tmp[k] = true;
                }
            }
        }

        count = count(tmp);
        unset(tmp);

        if (count != count(second))
            return false;

        return true;

    }

    /**
     * diff Array
     *
     * Goes through arrays of AbstractData and performs a diff.  It returns an array of
     * three different arrays: intersection (the shared components), first (the items
     * of the first not appearing in the second), and second (the items of the
     * second not appearing in the first).
     *
     * @param snacdataAbstractData[] first first array
     * @param snacdataAbstractData[] second second array
     * @param boolean strict optional whether or not to check ID/Version/Operation
     * @return mixed[] An associative array of AbstractData[] with "intersection," "first," and "second" keys
    protected function diffArray(first, second, strict = true) {
        return = array(
            "intersection" => array(),
            "first" => array(),
            "second" => array()
        );

        if (first == null && second == null)
            return return;
        if (first == null) {
            return["second"] = second;
            return return;
        }
        if (second == null) {
            return["first"] = first;
            return return;
        }

        tmp = array();
        foreach (first as data) {
            seen = false;
            foreach (second as k => odata) {
                if (data != null && data->equals(odata, strict)
                        && !isset(tmp[k])) {
                    // in case there are duplicates in first
                    tmp[k] = true;
                    seen = true;
                    array_push(return["intersection"], data);
                }
            }
            if (!seen) {
                array_push(return["first"], data);
            }
        }

        foreach (second as k => odata) {
            // make use of our key-bitmap to not have an inner loop
            if (!isset(tmp[k])) {
                array_push(return["second"], odata);
            }
        }

        return return;

    }*/

    /**
     * Set the data type for this object
     */
    protected void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Get the type of this object
     *
     * @return string The type of this object
     *
     */
    public String getDataType() {
        return this.dataType;
    }

    /**
     * Set the number of date objects we can have in the list of dates.
     *
     */
    protected void setMaxDateCount(int count)
    {
        this.maxDateCount = count;
    }

    /**
     * Get the number of date objects we can have in the list of dates.
     *
     * @return integer count The number of dates supported.
     *
     */
    protected int getMaxDateCount()
    {
        return this.maxDateCount;
    }

    /**
     * Get the list of dates.
     *
     * @return snacdataSNACDate[] Returns a list of SNACDate objects, or an empty list if there are no
     * dates. If someone has called unsetDateList() then it won't be a list and the calling code is expecting
     * a list, always, even if empty.
     *
     */
    public List<SNACDate> getDateList()
    {
        return this.dateList;
    }

    /**
     * Add a date object to our list of dates. Succeeds only if the object allows dates, or has room based on
     * maxDateCount
     *
     * @return boolean true on success, false on failure
     *
     */
    public boolean addDate(SNACDate dateObj)
    {
        if (this.maxDateCount != 0 &&
                this.dateList.size() < this.maxDateCount) {
            this.dateList.add(dateObj);
            return true;
        }
        return false;
    }

    /**
     * Set the Date List
     *
     * Sets the date list to be the given list of SNACDate objects, if this object can legally
     * hold that many dates.
     *
     * @return boolean true on success, false on failure
     *
     */
    public boolean setDateList(List<SNACDate> dateList)
    {
        if (this.maxDateCount > 0 && dateList.size() < this.maxDateCount) {
            this.dateList = dateList;
            return true;
        }
        return false;
    }


    /**
     * Set this object's database info in a single setter call, equivalent to setVersion(version); setID(id);
     *
     * Either or both keys may be empty, so there is no obvious sanity check. When a new Constellation object
     * is created by parsing a CPF file, both keys will be empty.
     *
     */
    protected void setDBInfo(int version, int id)
    {
        if (version != 0)
        {
            this.version = version;
        }
        if (id != 0)
        {
            this.id = id;
        }
    }


    /**
     * Get the dbInfo, returning a list with version and id. Do not return a list with keys unless you have a good reason. The
     * variable this.id is *not* compatible with vhInfo in DBUtils, except for class Constellation. All
     * other objects are not vhInfo compatible, so it is best that we do not return a vhInfo associative
     * list.
     *
     * @return string[] An array of version and id. For class Constellation, version is the max(version) aka
     * *the* version of the constellation aka max(version_history.id). For all other classes, version is the
     * version of each object (SQL record), and id is the table.id, not the constellation id.
     *
     */
    protected int[] getDBInfo()
    {
        return new int[] {this.version, this.id};
    }


    /**
     * Get the ID of this data structure. See comments for getDBInfo(). Class constellation this is ic_id. All
     * other classes this is table.id.
     *
     *  @return int ID of this structure
     */
    public int getID() {
        return this.id;
    }


    /**
     * Set the id of this object. See comments for setDBInfo(). Class constellation this is ic_id. All
     * other classes this is table.id.
     *
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Get the version number of this. For constellation this is *the* constellation version
     * aka max(version) aka max(version_history.id). For all other objects this is table.version for each
     * record (object).
     *
     *  @return int The version of this object.
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Set the version number of this object. For constellation this is *the* constellation version aka
     * max(version) aka max(version_history.id). For all other objects this is table.version for each record
     * (object).
     *
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Add a piece of snac control metadata to this structure
     *
     */
    public void addSNACControlMetadata(SNACControlMetadata metadata) {
        this.snacControlMetadata.add(metadata);
    }

    /**
     * Set all the snac control metadata for this structure
     *
     */
    public void setSnacControlMetadata(List<SNACControlMetadata> metadata) {
        this.snacControlMetadata = new ArrayList<>(metadata);
    }

    /**
     * Get all snac control metadata for this structure
     *
     * @return snacdataSNACControlMetadata[] Array of snac control metadata about this data
     */
    public List<SNACControlMetadata> getSnacControlMetadata() {
        return this.snacControlMetadata;
    }

    /**
     * Set the operation for this data
     *
     * @return boolean true on success, false on failure
     */
    public boolean setOperation(String operation) {
        if (operation != null && (operation.equals(AbstractData.OPERATION_UPDATE) ||
            operation.equals(AbstractData.OPERATION_DELETE) ||
            operation.equals(AbstractData.OPERATION_INSERT))) {
                this.operation = operation;
                return true;
        }
        return false;
    }

    /**
     * Get the operation for this data object
     *
     * @return string the operation, or null if no operation
     */
    public String getOperation() {
        return this.operation;
    }

    /**
     * Cleanse sub elements
     */
    public void cleanseSubElements(String operation) {
        String newOperation = AbstractData.OPERATION_INSERT;
        if (operation != null) {
            newOperation = operation;
        }
        if (this.dateList != null) {
            for (SNACDate d : this.dateList) {
                d.setID(0);
                d.setVersion(0);
                d.setOperation(newOperation);
                d.cleanseSubElements(newOperation);
            }
        }
        if (snacControlMetadata != null) {
            for (SNACControlMetadata scm : snacControlMetadata) {
                scm.setID(0);
                scm.setVersion(0);
                scm.setOperation(newOperation);
                scm.cleanseSubElements(newOperation);
            }
        }
    }
}
