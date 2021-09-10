package main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import personal_info.*;
import resume_items.*;
import selector.ResumeItemSelector;

/**
 * This class is used to run the resume maker.
 */
public class Main {
	/**
	 * This method runs the resume maker.
	 * 
	 * @param keywords the keywords to use, or the single String "--help" to display info about the
	 *                 program.
	 */
	public static void main(String[] keywords) {
		ResumeInfo info = new ChrisHartungResumeInfo(); // change this line to construct someone
														// else's resume

		// display help info when --help is used
		if ((keywords.length == 1) && ("--help".equals(keywords[0]))) {
			System.out.println(
					"To construct a resume, run this program with any relevant keywords as command line arguments.");
			System.out.println("The following keywords are available:");
			for (String keyword : info.getJobsAndProjectsByKeyword().keySet()) {
				if (keyword != null)
					System.out.print("\"" + keyword + "\" ");
			}
			System.out.println();
			System.exit(0);
		}
		
		// write tex file header
		String texFile = info.getFileName() + ".tex";
		File pdfFile = new File(info.getFileName() + ".pdf");
		ResumeItemSelector selector = new ResumeItemSelector(info, keywords);
		try (PrintWriter pw = new PrintWriter(texFile);) {
			pw.print("\\documentclass{article}\n"
					+ "\\usepackage[utf8]{inputenc}\n"
					+ "\\usepackage{hyperref}\n"
					+ "\n"
					+ "\\addtolength{\\oddsidemargin}{-1.2in}\n"
					+ "\\addtolength{\\evensidemargin}{-1.2in}\n"
					+ "\\addtolength{\\textwidth}{2.4in}\n"
					+ "\n"
					+ "\\addtolength{\\topmargin}{-1.2in}\n"
					+ "\\addtolength{\\textheight}{2.6in}\n"
					+ "\n"
					+ "\\setlength{\\parindent}{0px}\n"
					+ "\n"
					+ "\\renewcommand{\\familydefault}{\\sfdefault}\n"
					+ "\n"
					+ "\\newcommand{\\sectionheader}[1]{\\vspace{-10px}\\section*{#1}\\vspace{-8px}}\n"
					+ "\n"
					+ "\\newcommand{\\bulletpoint}[1]{\\vspace{-\\topsep}\n"
					+ "\\begin{itemize}\n"
					+ "\\begin{samepage}\n"
					+ "  \\setlength{\\parskip}{0pt}\n"
					+ "  \\item #1\n"
					+ " \\end{samepage}\n"
					+ "\\end{itemize}\n"
					+ "\\vspace{-\\topsep}}\n"
					+ "\n"
					+ "% This code, used to enforce a one-page limit, was copied from\n"
					+ "% https://stackoverflow.com/questions/2720534/force-a-maximum-number-of-pages-in-latex\n"
					+ "\\makeatletter\n"
					+ "\\newcounter{pagecount}\n"
					+ "\\newcommand{\\limitpages}[1]{\n"
					+ "    \\setcounter{pagecount}{0}%\n"
					+ "    \\gdef\\maxpages{#1}%\n"
					+ "    \\ifx\\latex@outputpage\\@undefined\\relax%\n"
					+ "        \\global\\let\\latex@outputpage\\@outputpage%\n"
					+ "    \\fi%\n"
					+ "    \\gdef\\@outputpage{%\n"
					+ "        \\addtocounter{pagecount}{1}%\n"
					+ "        \\ifnum\\value{pagecount}>\\maxpages\\relax%\n"
					+ "            % Do not output the page\n"
					+ "        \\else%\n"
					+ "            \\latex@outputpage%\n"
					+ "        \\fi%\n"
					+ "    }%\n"
					+ "}\n"
					+ "\\makeatother\n"
					+ "\n"
					+ "\\begin{document}\n"
					+ "\n"
					+ "\\limitpages{1}\n"
					+ "\n"
					+ "\\pagestyle{empty}\n"
					+ "\n"
					+ "\\begin{center}\n"
					+ "    \\textbf{\\Huge ");
			
			// write resume header
			pw.print(info.getName());
			pw.print("}\n    \\vspace{3px}\n    \n    ");
			String addressLine1 = info.getAddressLine1();
			String addressLine2 = info.getAddressLine2();
			String phone = info.getPhone();
			String email = info.getEmail();
			String linkedin = info.getLinkedIn();
			if (addressLine1 != null)
				pw.print(addressLine1 + " $\\cdot$ ");
			if (addressLine2 != null)
				pw.print(addressLine2 + " $\\cdot$ ");
			if (phone != null)
				pw.print(phone + " $\\cdot$ ");
			pw.print(email + " $\\cdot$ ");
			pw.print("\\href{https://" + linkedin + "}{" + linkedin);
			pw.print("}\n"
					+ "    \n"
					+ "    \\rule{7.15in}{2px}\n"
					+ "\\end{center}\n"
					+ "\\vspace{-10px}\n"
					+ "\n"
					+ "\\sectionheader{EDUCATION}\n"
					+ "\n");
			
			// write Education section
			for (Degree degree : info.getDegrees()) {
				pw.print(degree);
			}
			
			// write Certifications section if applicable
			if (info.getCertifications() != null) {
				pw.print("\\sectionheader{CERTIFICATIONS}\n\n");
				for (Certification certification : info.getCertifications()) {
					pw.print(certification);
				}
			}
			
			// write Technical Skills section
			pw.print("\\sectionheader{TECHNICAL SKILLS}\n"
					+ "\n"
					+ "\\begin{tabular}{ @{} ");
			TechnicalSkill[][] skills = info.getTechnicalSkills();
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
			pw.print("\\end{tabular}\n"
					+ "\n"
					+ "\\sectionheader{RELEVANT WORK EXPERIENCE}\n"
					+ "\n");
			
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
				throw new IOException("Error writing to " + texFile);
		} 
		// display error message if an error occurs in writing the tex file
		catch (IOException e) {
			System.out.println("Error: unable to write tex file");
			System.exit(1);
		}
		
		// run pdflatex to compile the tex file into a pdf, then open the pdf
		try {
			Process proc = new ProcessBuilder().command("pdflatex", texFile).inheritIO().start();
			// display error message if pdflatex has an error or if pdf was not created
			if ((proc.waitFor() != 0) || !pdfFile.exists())
				throw new IOException();
			Desktop.getDesktop().open(pdfFile);
		} catch (Exception e) {
			System.out.println("Error: unable to write pdf file");
			System.out.println(texFile
					+ " has been written successfully and can be compiled using any LaTeX compiler.");
			System.exit(1);
		}
	}
}
