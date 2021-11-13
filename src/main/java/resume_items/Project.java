package resume_items;

/**
 * This class represents a project.
 */
public final class Project implements ResumeItem {
	private int length = 1;
	private String texString;
	private int year;

	/**
	 * Constructs a Project.
	 * 
	 * @param name         the title of the project
	 * @param year         the year the project was completed
	 * @param bulletpoints bullet points describing the project
	 */
	public Project(String name, int year, String... bulletpoints) {
		this(name, null, year, bulletpoints);
	}

	/**
	 * Constructs a Project.
	 * 
	 * @param name         the title of the project
	 * @param organization the organization for which the project was created (optional; will not be
	 *                     displayed if null)
	 * @param year         the year the project was completed
	 * @param bulletpoints bullet points describing the project
	 */
	public Project(String name, String organization, int year, String... bulletpoints) {
		this.year = year;
		StringBuilder sb = new StringBuilder("\\textbf{");
		sb.append(ResumeItem.escape(name));
		sb.append("}");
		if (organization != null) {
			sb.append(", ");
			sb.append(ResumeItem.escape(organization));
		}
		sb.append(" \\hfill ");
		sb.append(year);
		for (String bulletpoint : bulletpoints) {
			length += ((bulletpoint.length() / LINE_LENGTH) + 1);
			sb.append(ResumeItem.makeBulletpoint(bulletpoint));
		}
		sb.append("\n\n");
		texString = sb.toString();
	}

	/**
	 * @return the year the project was completed
	 */
	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return texString;
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public void setLength(int length) {
		this.length = length;
	}
}
