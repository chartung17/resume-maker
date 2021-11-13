package personal_info;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import resume_items.*;

/**
 * This class represents personal info for Chris Hartung's resume.
 */
public class ChrisHartungResumeInfo implements ResumeInfo {
	// define all jobs
	private static final Job RAYTHEON = new Job("Software Engineer",
			"Raytheon Intelligence & Space", "Aurora, CO", 2021, Job.PRESENT,
			"Processed data from satellite ground stations for the GPS Next-Generation Operational Control System"
					+ " (GPS OCX)",
			"Developed Java programs to automate processing and visualizing GPS data");
	private static final Job HEAD_TA = new Job("Head Teaching Assistant",
			"University of Pennsylvania", "Philadelphia, PA", 2021, 2021,
			"Led team of 12 teaching assistants, assigned grading, and assisted teaching assistants with grading when needed",
			"Assisted with grading homework assignments and exams and processing regrade requests",
			"Instructed students in algorithms through office hours (2 hours weekly), recitations (weekly), and "
					+ "Piazza discussion forums (5-10 questions per week)");
	private static final Job TA = new Job("Teaching Assistant", "University of Pennsylvania",
			"Philadelphia, PA", 2020, 2021,
			"Instructed students in combinatorics, statistics, and graph theory "
					+ "through office hours (2 hours weekly), recitations (8 times per semester), and "
					+ "Piazza discussion forums (10-20 questions per week); processed regrade requests");
	private static final Job EASTCOR = new Job("Technical Specialist",
			"EastCor/Concentric Engineering", "Easton, MD", 2010, 2016,
			"Assembled circuit boards, computers, and battery boxes utilizing wiring schematics and assembly diagrams",
			"Organized warehouse, counted inventory items monthly");
	private static final Job INVENTORY = new Job("Inventory Control Clerk",
			"Concentric Engineering", "Easton, MD", 2014, 2015,
			"Managed database of over 10,000 inventory items",
			"Developed bills of materials and manufacture/sales/purchase orders using the Fishbowl Inventory system",
			"Implemented new organizational system for inventory, allowing parts to be picked and delivered to engineering"
					+ " and mechanical teams in less than half the time previously required");
	private static final Job ANALYST = new Job("Analyst", "Concentric Engineering", "Easton, MD",
			2013, 2014,
			"Analyzed data from Fast Fourier Transform plots for system functionality across various environments",
			"Automated portions of data analysis process, allowing data to be analyzed three times faster than before",
			"Tested hardware and software functionality in shop and field conditions",
			"Troubleshot mechanical and electrical systems to determine point of failure and common cause of failure");

	// define all projects
	private static final Project PHOTOMOSAIC = new Project("Photomosaic Maker", 2021,
			"Developed a Java program to quickly create photomosaics. Utilized the "
					+ "singleton and immutable object design patterns, as well as multithreading, the Swing "
					+ "toolkit, and a custom implementation of the octree data structure. Source code at "
					+ "github.com/chartung17/photomosaic");
	private static final Project CHESS = new Project("Chess", 2021,
			"Programmed a game of chess in Java using the mediator design pattern, "
					+ "then used Spring and React to turn it into a web app. Web app at "
					+ "chartung17.github.io/chess and source code at github.com/chartung17/chess");
	private static final Project SUDOKU = new Project("Sudoku Solver", 2021,
			"Wrote programs in both Python and C to quickly solve most Sudoku puzzles. "
					+ "Source code at \n\n github.com/chartung17/sudoku-solver");
	private static final Project ELECTECON = new Project("ElectEcon", "University of Pennsylvania",
			2020, "Developed a web app to analyze patterns in election and GDP data using "
					+ "React.js, Node.js, and MySQL. Source code at github.com/chartung17/electecon");
	private static final Project TESTS = new Project("Test Suite", "University of Pennsylvania",
			2020,
			"Developed C++ programs using the LLVM compiler infrastructure to perform both dynamic and static analysis"
					+ " to detect divide-by-zero errors");
	private static final Project PORTFOLIO = new Project("Portfolio", "freeCodeCamp", 2020,
			"Wrote web pages using HTML, CSS, Javascript, and React. Portfolio and links to other projects"
					+ " at chartung17.github.io");
	private static final Project HANDSHAKE = new Project("Three-Way Handshake",
			"University of Pennsylvania", 2020,
			"Created C programs to establish socket connection between clients and "
					+ "server using a three-way handshake, including a single-threaded server, a "
					+ "multithreaded server, and an event driven server");
	private static final Project PARKING = new Project("Parking", "University of Pennsylvania",
			2020, "Developed Java program to analyze data on parking violations and market "
					+ "value of houses in Philadelphia ZIP codes");
	private static final Project ASSEMBLER = new Project("Assembler/Disassembler",
			"University of Pennsylvania", 2020,
			"Designed two C programs to translate from assembly to machine code and vice versa for the LC4 computer");
	private static final Project GO = new Project("Go Game", "University of Pennsylvania", 2019,
			"Programmed portions of a game of Go in Java, including a user interface "
					+ "developed with the Swing toolkit and a single-player mode utilizing multithreading. "
					+ "Game and source code at  chartung17.github.io/gogame.html");
	private static final Project HEART = new Project("Heart Disease Predictor",
			"University of Pennsylvania", 2020,
			"Wrote Python program to predict occurences of heart disease based on other health factors, utilizing"
					+ " the numpy, pandas, and sklearn packages");
	private static final Project ROBOT = new Project("Potential Field Controller",
			"University of Pennsylvania", 2021,
			"Wrote a Python programming which uses artificial potential fields to navigate a simulated robot from"
					+ " its starting position to a given target while avoiding both static and dynamic obstacles");
	private static final Project STEGANOGRAPHY = new Project("Steganography", 2021,
			"Developed program with React front end and Java back end to hide messages within image files");

	// correct length estimates
	static {
		PORTFOLIO.setLength(2);
	}

	@Override
	public Degree[] getDegrees() {
		return new Degree[] {
				new Degree("Master of Computer and Information Technology",
						"University of Pennsylvania", 4, 4, 2021),
				new Degree("M.A. Theology and Christian Ministry",
						"Franciscan University of Steubenville", 3.9, 4, 2016),
				new Degree("B.S Economics, B.A. Philosophy & Ancient Greek and Roman Studies",
						"University of Delaware", 3.96, 4, 2013) };
	}

	@Override
	public Certification[] getCertifications() {
		return new Certification[] {
				new Certification("Oracle Certified Professional Java SE 8 Programmer", "Oracle",
						2021),
				new Certification("Certified Associate in Python Programming", "Python Institute",
						2021),
				new Certification(
						"Certifications in Responsive Web Design, Javascript Algorithms and Data Structures",
						"freeCodeCamp", 2020) };
	}

	@Override
	public TechnicalSkill[][] getTechnicalSkills() {
		return new TechnicalSkill[][] {
				{ new TechnicalSkill("Languages", "Java", "Python", "C", "C++"),
						new TechnicalSkill("Database Management", "SQL", "MongoDB (Beginner)",
								"Neo4j (beginner)") },
				{ new TechnicalSkill("Other", "Eclipse", "Git", "LLVM (beginner)"),
						new TechnicalSkill("Web Design", "HTML", "CSS", "Javascript", "React.js",
								"Spring Framework (beginner)") } };
	}

	@Override
	public List<ResumeItem> getJobsAndProjects() {
		return Arrays.asList(RAYTHEON, HEAD_TA, TA, ANALYST, PHOTOMOSAIC, CHESS, ELECTECON,
				STEGANOGRAPHY, ASSEMBLER, PORTFOLIO, TESTS, ROBOT, GO, HEART, HANDSHAKE, SUDOKU,
				PARKING, EASTCOR, INVENTORY);
	}

	@Override
	public Map<String, Set<ResumeItem>> getJobsAndProjectsByKeyword() {
		Map<String, Set<ResumeItem>> map = new HashMap<>();
		map.put(null, new HashSet<>(Arrays.asList(RAYTHEON, HEAD_TA, TA, ANALYST)));
		map.put("C", new HashSet<>(Arrays.asList(SUDOKU, HANDSHAKE, ASSEMBLER, TESTS)));
		map.put("Java",
				new HashSet<>(Arrays.asList(PHOTOMOSAIC, CHESS, PARKING, GO, STEGANOGRAPHY)));
		map.put("Python", new HashSet<>(Arrays.asList(SUDOKU, HEART, ROBOT)));
		map.put("WebDesign",
				new HashSet<>(Arrays.asList(ELECTECON, PORTFOLIO, STEGANOGRAPHY, CHESS)));
		map.put("ComputerHardware", new HashSet<>(Arrays.asList(EASTCOR, INVENTORY)));
		map.put("Data", new HashSet<>(Arrays.asList(ELECTECON, ANALYST, HEART)));
		return map;
	}

	@Override
	public String getFileName() {
		return "Chris Hartung resume";
	}

	@Override
	public String getName() {
		return "Christopher Hartung";
	}

	@Override
	public String getAddressLine1() {
		return null;
	}

	@Override
	public String getAddressLine2() {
		return "Denver, CO";
	}

	@Override
	public String getPhone() {
		return null;
	}

	@Override
	public String getEmail() {
		return "chartung@alumni.upenn.edu";
	}

	@Override
	public String getLinkedIn() {
		return "linkedin.com/in/chartung17";
	}
}
