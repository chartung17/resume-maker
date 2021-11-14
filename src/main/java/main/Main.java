package main;

import java.io.IOException;
import java.io.PrintWriter;

import personal_info.*;
import resume_items.*;
import selector.ResumeItemSelector;

/**
 * This class is used to run the resume maker.
 */
public class Main {
	public static ResumeInfo INFO = new ChrisHartungResumeInfo(); // change this line to construct
																	// someone else's resume

	/**
	 * This method runs the resume maker.
	 * 
	 * @param keywords the keywords to use, or the single String "--help" to display info about the
	 *                 program.
	 */
	public static void main(String[] keywords) {
		// display help info when --help is used
		if ((keywords.length == 1) && ("--help".equals(keywords[0]))) {
			System.out.println(
					"To construct a resume, run this program with any relevant keywords as command line arguments.");
			System.out.println("The following keywords are available:");
			for (String keyword : INFO.getJobsAndProjectsByKeyword().keySet()) {
				if (keyword != null)
					System.out.print("\"" + keyword + "\" ");
			}
			System.out.println();
			System.exit(0);
		}

		// write tex file
		String texFile = INFO.getFileName() + ".tex";
		try (PrintWriter pw = new PrintWriter(texFile)) {
			printTexFile(pw, keywords);
		}
		// display error message if an error occurs in writing the tex file
		catch (IOException e) {
			System.out.println("Error: unable to write tex file");
			System.exit(1);
		}

		// run pdflatex to compile the tex file into a pdf, then open the pdf
		PdfWriter.printPdf(texFile);
	}

	public static void printTexFile(PrintWriter pw, String[] keywords)
			throws IOException {
		ResumeItemSelector selector = new ResumeItemSelector(INFO, keywords);
		
		// write header
		Header.printHeader(pw, INFO);

		// write Education section
		pw.print("\\sectionheader{EDUCATION}\n\n");
		for (Degree degree : INFO.getDegrees()) {
			pw.print(degree);
		}

		// write Certifications section if applicable
		if (INFO.getCertifications() != null) {
			pw.print("\\sectionheader{CERTIFICATIONS}\n\n");
			for (Certification certification : INFO.getCertifications()) {
				pw.print(certification);
			}
		}

		// write Technical Skills section
		pw.print("\\sectionheader{TECHNICAL SKILLS}\n" + "\n" + "\\begin{tabular}{ @{} ");
		TechnicalSkill[][] skills = INFO.getTechnicalSkills();
		int numRows = skills.length;
		int numCols = skills[0].length;
		for (int i = 0; i < numCols; i++) {
			pw.print("l ");
		}
		pw.print("@{} }\n");
		StringBuilder sb = new StringBuilder();
		int length = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				sb.append(skills[i][j]);
				sb.append(" & \n");
			}
			length = sb.length();
			sb.replace(length - 3, length - 2, "\\\\");
		}
		length = sb.length();
		sb.replace(length - 4, length, "\n");
		pw.print(sb.toString());
		pw.print("\\end{tabular}\n" + "\n" + "\\sectionheader{RELEVANT WORK EXPERIENCE}\n" + "\n");

		// write Relevant Work Experience section
		for (Job job : selector.selectJobs()) {
			pw.print(job);
		}

		// write Projects section
		pw.print("\\sectionheader{PROJECTS}\n\n");
		for (Project project : selector.selectProjects()) {
			pw.print(project);
		}

		// finish writing tex file
		pw.print("\\end{document}");
		if (pw.checkError())
			throw new IOException("Error writing tex file");
	}
}
