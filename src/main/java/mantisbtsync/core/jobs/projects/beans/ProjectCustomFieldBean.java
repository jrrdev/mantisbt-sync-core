/**
 *
 */
package mantisbtsync.core.jobs.projects.beans;

/**
 * Bean to store the data related to a project
 * custom field.
 *
 * @author jdevarulrajah
 *
 */
public final class ProjectCustomFieldBean {

	private Integer id;

	private String name;

	private Integer typeId;

	private String possibleValues;

	private String defaultValue;

	private String validRegexp;

	private Integer projectId;

	/**
	 * Default constructor.
	 */
	public ProjectCustomFieldBean() {
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param type the type to set
	 */
	public void setTypeId(final Integer type) {
		this.typeId = type;
	}

	/**
	 * @return the possibleValues
	 */
	public String getPossibleValues() {
		return possibleValues;
	}

	/**
	 * @param possibleValues the possibleValues to set
	 */
	public void setPossibleValues(final String possibleValues) {
		this.possibleValues = possibleValues;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(final String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the validRegexp
	 */
	public String getValidRegexp() {
		return validRegexp;
	}

	/**
	 * @param validRegexp the validRegexp to set
	 */
	public void setValidRegexp(final String validRegexp) {
		this.validRegexp = validRegexp;
	}

	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(final Integer projectId) {
		this.projectId = projectId;
	}
}