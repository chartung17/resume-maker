package main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to compile a tex file into a pdf file and then open the pdf file.
 */
public class PdfWriter {
	/**
	 * This method is used to compile a tex file into a pdf file and then open the pdf file.
	 * 
	 * @param texFile the tex file to compile
	 */
	static void printPdf(String texFile) {
		try {
			File pdfFile = new File(texFile.substring(0, texFile.lastIndexOf(".tex")) + ".pdf");
			Process proc = new ProcessBuilder().command("pdflatex", texFile).inheritIO().start();
			// display error message if pdflatex has an error or if pdf was not created
			if ((proc.waitFor() != 0) || !pdfFile.exists())
				throw new IOException();
			Desktop.getDesktop().open(pdfFile);
		} catch (Exception e) {
			System.out.println("Error in writing pdf file");
			System.out.println(texFile
					+ " has been written successfully and can be compiled using any LaTeX compiler.");
			System.exit(1);
		}
	}
}
