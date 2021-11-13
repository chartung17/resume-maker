package resume_items;

/**
 * This interface represents any resume item that can be either included or excluded depending on
 * user input, namely Jobs and Projects.
 */
public interface ResumeItem {
	/**
	 * The approximate number of characters that can fit on a line of text.
	 */
	public static int LINE_LENGTH = 115;

	/**
	 * Returns the number of lines of text needed to display this resume item.
	 * 
	 * @return the number of lines of text needed to display this resume item
	 */
	public int getLength();

	/**
	 * Sets the length of the resume item. The length is automatically set to an estimated length
	 * value in the constructor; this method can be used to correct an inaccurate estimate.
	 * 
	 * @param length the number of lines of text needed to display this resume item
	 */
	public void setLength(int length);

	/**
	 * This method takes an unformatted String bulletpoint and returns the bulletpoint with LaTeX
	 * formatting added.
	 * 
	 * @param bulletpoint
	 * @return
	 */
	public static String makeBulletpoint(String bulletpoint) {
		return "\n\n\\bulletpoint{" + formatLinks(escape(bulletpoint)) + "}";
	}

	/**
	 * This method searches the given String for characters that have special meaning in LaTeX and
	 * replaces them with the appropriate escape sequences.
	 * 
	 * @param str the String to search
	 */
	public static String escape(String str) {
		StringBuilder sb = new StringBuilder(str);
		for (int i = sb.length() - 1; i >= 0; i--) {
			char c = sb.charAt(i);
			switch (c) {
			case '#':
			case '$':
			case '%':
			case '&':
			case '~':
			case '_':
			case '^':
			case '{':
			case '}':
				sb.insert(i, '\\');
				break;
			case '>':
			case '<':
				sb.replace(i, i + 1, "$" + c + "$");
				break;
			case '\\':
				sb.replace(i, i + 1, "$\\backslash$");
			}
		}
		return sb.toString();
	}
	
	public static String formatLinks(String str) {
		StringBuilder sb = new StringBuilder();
		String[] words = str.split(" ");
		for (String word : words) {
			if (word.startsWith("http://") || word.startsWith("https://")) {
				sb.append("\\href{");
				sb.append(word);
				sb.append("}{");
				sb.append(word);
				sb.append("}");
			} else if (word.contains("github.com") || word.contains("github.io")) {
				sb.append("\\href{https://");
				sb.append(word);
				sb.append("}{");
				sb.append(word);
				sb.append("}");
			} else {
				sb.append(word);
			}
			sb.append(" ");
		}
		return sb.toString();
	}
}
