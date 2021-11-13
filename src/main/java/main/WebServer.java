package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import personal_info.ChrisHartungResumeInfo;
import personal_info.ResumeInfo;
import selector.ResumeItemSelector;

@RestController
@SpringBootApplication
public class WebServer {
	private static ResumeInfo info = new ChrisHartungResumeInfo();

	/**
	 * This method returns a link to a resume built based keywords specified in the URL. If multiple
	 * keywords are specified, they should be separated by dashes.
	 * 
	 * @param keywordsStr the keywords to use, separated by dashes
	 * @returna link to a resume built based keywords specified in the URL
	 */
	@RequestMapping(value = "/compile/{keywordsStr}", method = RequestMethod.GET)
	@CrossOrigin
	public static String compile(String keywordsStr) {
		String[] keywords = keywordsStr.split("-");
		ResumeItemSelector selector = new ResumeItemSelector(info, keywords);
		try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
			Main.printTexFile(pw, info, selector);
			return "{\"status\":200, \"link\":\"" + URLEncoder.encode(sw.toString(), "UTF-8")
					+ "\"}";
		}
		// return error message if an error occurs in writing the tex file
		catch (IOException e) {
			return "{\"status\":500, \"message\":\"error in writing tex file\"}";
		}
	}

	/**
	 * This method returns a list of valid keywords.
	 * 
	 * @return a list of valid keywords
	 */
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	@CrossOrigin
	public static String help() {
		Set<String> keySet = info.getJobsAndProjectsByKeyword().keySet();
		keySet.remove(null);
		List<String> keywords = new ArrayList<>(keySet);
		keywords.replaceAll(s -> '\"' + s + '\"');
		return "{\"status\":200,\"keywords\":" + keywords.toString() + "}";
	}

	/**
	 * This method runs the app.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebServer.class, args);
	}

}
