package personal_info;

import java.util.List;
import java.util.Map;
import java.util.Set;

import resume_items.*;

/**
 * An implementation of this interface represents personal info for a particular person's resume.
 */
public interface ResumeInfo {
	/**
	 * This method returns the base filename that will be used for the output .tex and .pdf files.
	 * 
	 * @return the base filename that will be used for the output .tex and .pdf files
	 */
	public String getFileName();

	/**
	 * This method returns the name to be displayed in the resume header.
	 * 
	 * @return the name to be displayed in the resume header
	 */
	public String getName();

	/**
	 * This method returns the first line of the address to be displayed in the resume header, or
	 * null if the address should not be displayed.
	 * 
	 * @return the first line of the address to be displayed in the resume header, or null if the
	 *         address should not be displayed
	 */
	public String getAddressLine1();

	/**
	 * This method returns the second line of the address to be displayed in the resume header, or
	 * null if the address should not be displayed.
	 * 
	 * @return the second line of the address to be displayed in the resume header, or null if the
	 *         address should not be displayed
	 */
	public String getAddressLine2();

	/**
	 * This method returns the phone number to be displayed in the resume header, or null if the
	 * phone number should not be displayed.
	 * 
	 * @return the phone number to be displayed in the resume header, or null if the phone number
	 *         should not be displayed
	 */
	public String getPhone();

	/**
	 * This method returns the email address to be displayed in the resume header.
	 * 
	 * @return the email address to be displayed in the resume header
	 */
	public String getEmail();

	/**
	 * This method returns the LinkedIn profile to be displayed in the resume header.
	 * 
	 * @return the LinkedIn profile to be displayed in the resume header
	 */
	public String getLinkedIn();

	/**
	 * This method returns the number of lines of text that are available to be used by jobs or
	 * projects. The default implementation estimates the value based on the number of degrees and
	 * certifications and the number of rows used by technical skill, but it can be overridden if
	 * the estimate is found to be incorrect.
	 * 
	 * @return the number of lines of text that are available to be used by jobs or projects
	 */
	public default int getAvailableLines() {
		return 41 - getDegrees().length - getCertifications().length - getTechnicalSkills().length;
	}

	/**
	 * This method returns an array containing all of the Degrees that will be displayed in the
	 * Education section of the resume. The Degrees in this array will be displayed in the order
	 * they are given, regardless of the user's selected keywords.
	 * 
	 * @return an array containing all of the Degrees that will be displayed in the Education
	 *         section of the resume
	 */
	public Degree[] getDegrees();

	/**
	 * This method returns an array containing all of the Certifications that will be displayed in
	 * the Certifications section of the resume. The Certifications in this array will be displayed
	 * in the order they are given, regardless of the user's selected keywords.
	 * 
	 * @return an array containing all of the Certifications that will be displayed in the
	 *         Certifications section of the resume
	 */
	public Certification[] getCertifications();

	/**
	 * This method returns a 2-dimensional array containing all of the TechnicalSkills that will be
	 * displayed in the Technical Skills section of the resume. The TechnicalSkills in this array
	 * will be displayed in tabular format in the order they are given, regardless of the user's
	 * selected keywords.
	 * 
	 * @return an array containing all of the TechnicalSkills that will be displayed in the
	 *         Technical Skills section of the resume
	 */
	public TechnicalSkill[][] getTechnicalSkills();

	/**
	 * This method returns a List containing all of the Jobs and Projects that can be displayed in
	 * the Relevant Work Experience and Projects sections of the resume. The Jobs and Projects
	 * should be ordered by priority, with the most important at the beginning of the list and the
	 * least important at the end of the list; this priority will be used to determine which items
	 * to add if there is remaining space after including all items related to the user's selected
	 * keywords or which items to remove if there is not enough space to include all items related
	 * to the user's selected keywords.
	 * 
	 * @return a List containing all of the Jobs and Projects that can be displayed in the Relevant
	 *         Work Experience and Projects sections of the resume
	 */
	public List<ResumeItem> getJobsAndProjects();

	/**
	 * This method returns a Map which maps String keywords to Sets of relevant Jobs and Projects.
	 * The null keyword is used to indicate Jobs and Projects that should always be included
	 * regardless of the user's selected keywords.
	 * 
	 * @return a Map which maps String keywords to Sets of relevant Jobs and Projects
	 */
	public Map<String, Set<ResumeItem>> getJobsAndProjectsByKeyword();
}
