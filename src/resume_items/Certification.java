package resume_items;

/**
 * This class represents a certification.
 */
public class Certification {
	private String texString;

	/**
	 * Constructs a Certification.
	 * 
	 * @param name         the title of the certification
	 * @param organization the organization which granted the certification
	 * @param year         the year the certification was completed
	 */
	public Certification(String name, String organization, int year) {
		StringBuilder sb = new StringBuilder("\\textbf{");
		sb.append(ResumeItem.escape(name));
		sb.append("}, ");
		sb.append(ResumeItem.escape(organization));
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
