package selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import personal_info.ResumeInfo;
import resume_items.Job;
import resume_items.Project;
import resume_items.ResumeItem;

/**
 * This class is used to select the Jobs and Projects to display based on the user's selected
 * keywords.
 */
public class ResumeItemSelector {
	private int length = 0;
	private int maxLength;
	private final Set<ResumeItem> itemsToUse = new HashSet<>();
	private final List<ResumeItem> list;

	/**
	 * Constructs a ResumeItemSelector
	 * 
	 * @param info     the ResumeInfo to use
	 * @param keywords the keywords to focus on
	 */
	public ResumeItemSelector(ResumeInfo info, String[] keywords) {
		Map<String, Set<ResumeItem>> map = info.getJobsAndProjectsByKeyword();
		list = info.getJobsAndProjects();
		Set<ResumeItem> next = map.get(null);
		if (next != null)
			itemsToUse.addAll(next);
		for (String keyword : keywords) {
			next = map.get(keyword);
			if (next != null)
				itemsToUse.addAll(next);
		}

		maxLength = info.getAvailableLines();
		for (ResumeItem item : itemsToUse) {
			length += item.getLength();
		}
		if (length > maxLength) {
			for (int i = list.size() - 1; i >= 0; i--) {
				ResumeItem item = list.get(i);
				if (itemsToUse.remove(item)) {
					length -= item.getLength();
					if (length <= maxLength)
						break;
				}
			}
		}
		if (length < maxLength) {
			for (int i = 0; i < list.size(); i++) {
				ResumeItem item = list.get(i);
				if (length + item.getLength() <= maxLength) {
					if (itemsToUse.add(item)) {
						length += item.getLength();
						if (length == maxLength)
							break;
					}
				}
			}
		}
	}

	/**
	 * This method returns a list of Jobs that should be displayed, sorted in the order they should
	 * be displayed. Jobs are ordered chronologically, with the most recent jobs appearing first;
	 * jobs with the same start and end years are ordered by priority.
	 * 
	 * @return a list of Jobs that should be displayed
	 */
	public List<Job> selectJobs() {
		List<Job> jobs = new ArrayList<>();
		for (ResumeItem item : itemsToUse) {
			if (item instanceof Job)
				jobs.add((Job) item);
		}
		Collections.sort(jobs, (a, b) -> {
			if (a.getEndYear() != b.getEndYear())
				return b.getEndYear() - a.getEndYear();
			if (a.getStartYear() != b.getStartYear())
				return b.getStartYear() - a.getStartYear();
			return list.indexOf(a) - list.indexOf(b);
		});
		return jobs;
	}

	/**
	 * This method returns a list of Projects that should be displayed, sorted in the order they
	 * should be displayed. Projects are ordered chronologically, with the most recent projects
	 * appearing first; jobs with the same year are ordered by priority.
	 * 
	 * @return a list of Projects that should be displayed
	 */
	public List<Project> selectProjects() {
		List<Project> projects = new ArrayList<>();
		for (ResumeItem item : itemsToUse) {
			if (item instanceof Project)
				projects.add((Project) item);
		}
		Collections.sort(projects, (a, b) -> {
			if (a.getYear() != b.getYear())
				return b.getYear() - a.getYear();
			return list.indexOf(a) - list.indexOf(b);
		});
		return projects;
	}
}
