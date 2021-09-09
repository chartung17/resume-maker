package resume_items;

/**
 * This class represents a job.
 */
public final class Job implements ResumeItem {
	private int length = 1;
	private String texString;
	private int startYear, endYear;

	/**
	 * A constant used to represent Present for ongoing jobs.
	 */
	public static final int PRESENT = Integer.MAX_VALUE;

	/**
	 * Constructs a Job
	 * 
	 * @param role         the job title
	 * @param company      the company
	 * @param location     the city and state where the job was located
	 * @param startYear    the year the job was started
	 * @param endYear      the year the job ended, or the constant Job.PRESENT for an ongoing job
	 * @param bulletpoints bullet points describing job duties and accomplishments
	 */
	public Job(String role, String company, String location, int startYear, int endYear,
			String... bulletpoints) {
		this.startYear = startYear;
		this.endYear = endYear;
		StringBuilder sb = new StringBuilder("\\textbf{");
		sb.append(ResumeItem.escape(role));
		sb.append("}, ");
		sb.append(ResumeItem.escape(company));
		sb.append(" (");
		sb.append(ResumeItem.escape(location));
		sb.append(") \\hfill ");
		sb.append(startYear);
		if (startYear != endYear) {
			sb.append(" - ");
			sb.append((endYear == PRESENT) ? "Present" : endYear);
		}
		for (String bulletpoint : bulletpoints) {
			length += ((bulletpoint.length() / LINE_LENGTH) + 1);
			sb.append(ResumeItem.makeBulletpoint(bulletpoint));
		}
		sb.append("\n\n");
		texString = sb.toString();
	}

	/**
	 * @return the year the job started
	 */
	public int getStartYear() {
		return startYear;
	}

	/**
	 * @return the year the job ended
	 */
	public int getEndYear() {
		return endYear;
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
