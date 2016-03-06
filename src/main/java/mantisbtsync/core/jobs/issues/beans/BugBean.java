/**
 *
 */
package mantisbtsync.core.jobs.issues.beans;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jdevarulrajah
 *
 */
public class BugBean {

	private BigInteger id;

	private BigInteger projectId;

	private BigInteger reporterId;

	private BigInteger handlerId;

	private BigInteger priorityId;

	private BigInteger severityId;

	private BigInteger statusId;

	private BigInteger resolutionId;

	private String description;

	private String stepsToReproduce;

	private String additionalInformation;

	private String platform;

	private String version;

	private String fixedInVersion;

	private String targetVersion;

	private String summary;

	private String category;

	private Timestamp dateSubmitted;

	private Timestamp lastUpdated;

	private final List<BugNoteBean> notes = new ArrayList<BugNoteBean>();

	private final List<BugCustomFieldValue> customFields = new ArrayList<BugCustomFieldValue>();

	private final List<BugHistoryBean> history = new ArrayList<BugHistoryBean>();

	/**
	 * Default constructor.
	 */
	public BugBean() {
	}

	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final BigInteger id) {
		this.id = id;
	}

	/**
	 * @return the projectId
	 */
	public BigInteger getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(final BigInteger projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the reporterId
	 */
	public BigInteger getReporterId() {
		return reporterId;
	}

	/**
	 * @param reporterId the reporterId to set
	 */
	public void setReporterId(final BigInteger reporterId) {
		this.reporterId = reporterId;
	}

	/**
	 * @return the handlerId
	 */
	public BigInteger getHandlerId() {
		return handlerId;
	}

	/**
	 * @param handlerId the handlerId to set
	 */
	public void setHandlerId(final BigInteger handlerId) {
		this.handlerId = handlerId;
	}

	/**
	 * @return the priorityId
	 */
	public BigInteger getPriorityId() {
		return priorityId;
	}

	/**
	 * @param priorityId the priorityId to set
	 */
	public void setPriorityId(final BigInteger priorityId) {
		this.priorityId = priorityId;
	}

	/**
	 * @return the severityId
	 */
	public BigInteger getSeverityId() {
		return severityId;
	}

	/**
	 * @param severityId the severityId to set
	 */
	public void setSeverityId(final BigInteger severityId) {
		this.severityId = severityId;
	}

	/**
	 * @return the statusId
	 */
	public BigInteger getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(final BigInteger statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the resolutionId
	 */
	public BigInteger getResolutionId() {
		return resolutionId;
	}

	/**
	 * @param resolutionId the resolutionId to set
	 */
	public void setResolutionId(final BigInteger resolutionId) {
		this.resolutionId = resolutionId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the stepsToReproduce
	 */
	public String getStepsToReproduce() {
		return stepsToReproduce;
	}

	/**
	 * @param stepsToReproduce the stepsToReproduce to set
	 */
	public void setStepsToReproduce(final String stepsToReproduce) {
		this.stepsToReproduce = stepsToReproduce;
	}

	/**
	 * @return the additionalInformation
	 */
	public String getAdditionalInformation() {
		return additionalInformation;
	}

	/**
	 * @param additionalInformation the additionalInformation to set
	 */
	public void setAdditionalInformation(final String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(final String platform) {
		this.platform = platform;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(final String version) {
		this.version = version;
	}

	/**
	 * @return the fixedInVersion
	 */
	public String getFixedInVersion() {
		return fixedInVersion;
	}

	/**
	 * @param fixedInVersion the fixedInVersion to set
	 */
	public void setFixedInVersion(final String fixedInVersion) {
		this.fixedInVersion = fixedInVersion;
	}

	/**
	 * @return the targetVersion
	 */
	public String getTargetVersion() {
		return targetVersion;
	}

	/**
	 * @param targetVersion the targetVersion to set
	 */
	public void setTargetVersion(final String targetVersion) {
		this.targetVersion = targetVersion;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(final String category) {
		this.category = category;
	}

	/**
	 * @return the dateSubmitted
	 */
	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}

	/**
	 * @param dateSubmitted the dateSubmitted to set
	 */
	public void setDateSubmitted(final Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	/**
	 * @return the lastUpdated
	 */
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(final Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the notes
	 */
	public List<BugNoteBean> getNotes() {
		return notes;
	}

	/**
	 * @return the customFields
	 */
	public List<BugCustomFieldValue> getCustomFields() {
		return customFields;
	}

	/**
	 * @return the history
	 */
	public List<BugHistoryBean> getHistory() {
		return history;
	}
}