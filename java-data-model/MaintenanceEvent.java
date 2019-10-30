

/**
 * Maintenance Event File
 *
 * Contains the information about an individual maintenance event.
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
 * Maintenance Event Class
 *
 * Data storage class for maintenance events on an identity constellation.
 *
 * @author Robbie Hott
 *
 */
public class MaintenanceEvent extends AbstractData {

    /**
     * Event type
     *
     * From EAC-CPF tag(s):
     *
     * * maintenanceEvent/eventType
     *
     * @var snacdataTerm Event type
     */
    private Term eventType;

    /**
     * Human-Readable Time
     *
     * From EAC-CPF tag(s):
     *
     * * maintenanceEvent/eventDateTime
     *
     * @var string Date and Time string of the event
     */
    private String eventDateTime;

    /**
     * Standard Date time
     *
     * From EAC-CPF tag(s):
     *
     * * maintenanceEvent/eventDateTime/@standardDateTime
     *
     * @var string Standardized date time of the event
     *
     */
    private String standardDateTime;

    /**
     * Agent Type
     *
     * From EAC-CPF tag(s):
     *
     * * maintenanceEvent/agentType
     *
     * @var snacdataTerm Type of the agent performing the event
     */
    private Term agentType;

    /**
     * Agent
     *
     * From EAC-CPF tag(s):
     *
     * * maintenanceEvent/agent
     *
     * @var string Agent that performed the event
     */
    private String agent;

    /**
     * Description
     *
     * From EAC-CPF tag(s):
     *
     * * maintenanceEvent/eventDescription
     *
     * @var string Description of the event
     */
    private String eventDescription;

    /**
     * Constructor
     *
     */
    public MaintenanceEvent() {
        super();
        this.setDataType("MaintenanceEvent");
        this.setMaxDateCount(0);
    }

    /**
     * Set the event type.
     *
     */
    public void setEventType(Term eventType) {

        this.eventType = eventType;
    }

    /**
     * Set the date and time of the event.
     *
     */
    public void setEventDateTime(String eventDateTime) {

        this.eventDateTime = eventDateTime;
    }

    /**
     * Set the standardized date and time of the event.
     *
     */
    public void setStandardDateTime(String eventDateTime) {

        this.standardDateTime = eventDateTime;
    }

    /**
     * Set the agent type.
     *
     */
    public void setAgentType(Term agentType) {

        this.agentType = agentType;
    }

    /**
     * Set the agent that performed the event.
     *
     */
    public void setAgent(String agent) {

        this.agent = agent;
    }

    /**
     * Set the event description.
     *
     */
    public void setEventDescription(String eventDescription) {

        this.eventDescription = eventDescription;
    }

    /**
     * Get the event type
     *
     * @return snacdataTerm event type
     */
    public Term getEventType() {
        return this.eventType;
    }

    /**
     * Get the event date time
     *
     * @return string date and time string
     */
    public String getEventDateTime() {
        return this.eventDateTime;
    }

    /**
     * Get the event description
     *
     * @return string description
     */
    public String getEventDescription() {
        return this.eventDescription;
    }

    /**
     * Get the agent type
     *
     * @return snacdataTerm agent type
     */
    public Term getAgentType() {
        return this.agentType;
    }

    /**
     * Get the agent
     *
     * @return string agent name
     */
    public String getAgent() {
        return this.agent;
    }

    /**
     * Get the standard date and time
     *
     * @return string standardized date and time
     */
    public String getStandardDateTime() {
        return this.standardDateTime;
    }

    /**
     *
     * {@inheritDoc}
     *
     */
    public boolean equals(Object other) {

        if (other == null || ! (other instanceof MaintenanceEvent))
            return false;

        if (!super.equals(other))
            return false;

        MaintenanceEvent m = (MaintenanceEvent) other;
        if ((this.getEventDateTime() != null && ! this.getEventDateTime().equals(m.getEventDateTime())) ||
                (this.getEventDateTime() == null && m.getEventDateTime() != null))
            return false;
        if ((this.getStandardDateTime() != null && ! this.getStandardDateTime().equals(m.getStandardDateTime())) ||
                (this.getStandardDateTime() == null && m.getStandardDateTime() != null))
            return false;
        if ((this.getAgent() != null && ! this.getAgent().equals(m.getAgent())) ||
                (this.getAgent() == null && m.getAgent() != null))
            return false;
        if ((this.getEventDescription() != null && ! this.getEventDescription().equals(m.getEventDescription())) ||
                (this.getEventDescription() == null && m.getEventDescription() != null))
            return false;

        if ((this.getEventType() != null && ! this.getEventType().equals(m.getEventType())) ||
                 (this.getEventType() == null && m.getEventType() != null))
            return false;
        if ((this.getAgentType() != null && ! this.getAgentType().equals(m.getAgentType())) ||
                 (this.getAgentType() == null && m.getAgentType() != null))
            return false;

        return true;
    }
}
