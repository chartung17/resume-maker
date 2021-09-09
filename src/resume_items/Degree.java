package resume_items;

import java.text.DecimalFormat;

/**
 * This class represents an academic degree.
 */
public class Degree {
	private String texString;
	private DecimalFormat gpaFormat = new DecimalFormat("0.00");

	/**
	 * Constructs a Degree.
	 * 
	 * @param name   the title of the degree
	 * @param school the school which granted the degree
	 * @param year   the year the degree was completed
	 */
	public Degree(String name, String school, int year) {
		this(name, school, -1.0, -1.0, year);
	}

	/**
	 * Constructs a Degree.
	 * 
	 * @param name   the title of the degree
	 * @param school the school which granted the degree
	 * @param gpa    the GPA earned, or any negative value to not display GPA
	 * @param maxGpa the maximum possible GPA
	 * @param year   the year the degree was completed
	 */
	public Degree(String name, String school, double gpa, double maxGpa, int year) {
		StringBuilder sb = new StringBuilder("\\textbf{");
		sb.append(ResumeItem.escape(name));
		sb.append("}, ");
		sb.append(ResumeItem.escape(school));
		if (gpa >= 0) {
			sb.append(", GPA ");
			sb.append(gpaFormat.format(gpa));
			sb.append("/");
			sb.append(gpaFormat.format(maxGpa));
		}
		sb.append(" \\hfill ");
		sb.append(year);
		sb.append("\n\n");
		texString = sb.toString();
	}

	@Override
	public String toString() {
		return texString;
	}
}
