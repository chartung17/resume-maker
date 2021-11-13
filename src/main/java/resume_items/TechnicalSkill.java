package resume_items;

/**
 * This class represents a category of technical skills.
 */
public class TechnicalSkill {
	private String texString;

	/**
	 * Constructs a TechnicalSkill.
	 * 
	 * @param category         the category of job skills
	 * @param skills any skills that fit in the category
	 */
	public TechnicalSkill(String category, String... skills) {
		StringBuilder sb = new StringBuilder("\\textbf{");
		sb.append(ResumeItem.escape(category));
		sb.append(":} ");
		sb.append(ResumeItem.escape(skills[0]));
		for (int i=1; i<skills.length;i++) {
			sb.append(", ");
			sb.append(skills[i]);
		}
		texString = sb.toString();
	}

	@Override
	public String toString() {
		return texString;
	}
}
