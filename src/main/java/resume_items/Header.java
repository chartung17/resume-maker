package resume_items;

import java.io.PrintWriter;

import personal_info.ResumeInfo;

/**
 * This class is used to write the header for a cover letter or resume.
 */
public class Header {
	/**
	 * This method is used to write the header for a cover letter or resume.
	 * 
	 * @param pw the PrintWriter to write to
	 * @param info the resume info to use in the header
	 */
	public static void printHeader(PrintWriter pw, ResumeInfo info) {
		// write tex file header
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
		
		// write resume/cover letter header
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
				+ "\n");
	}
}
