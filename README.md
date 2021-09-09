# Resume Maker

This app is used to create a custom resume based on selected keywords.

#### Running the app:
1. Verify that a TeX distribution is installed by opening a terminal and running `pdflatex --version`. If the command is not recognized, download [MiKTeX](https://miktex.org/download).
2. Verify that a Java Runtime Environment (JRE) is installed by opening a terminal and running `java -version`. If the command is not recognized, or if the version is earlier than 1.8, download a [JRE](https://www.oracle.com/java/technologies/javase-jre8-downloads.html).
3. Download ResumeMaker.jar.
4. Open a terminal, `cd` into the folder containing ResumeMaker.jar, and run `java -jar ResumeMaker.jar <keywords>`, replacing `<keywords>` with any relevant keywords from the available options.
    - The available keywords are "Java", "C", "WebDesign", "ComputerHardware", and "Python".
    - A list of available keywords can also be found by running `java -jar ResumeMaker.jar --help`.
5. If prompted to install needed LaTeX packages, click Install on each prompt.
    
#### Making your own resume:
1. Download the src folder.
2. Create a new class in the personal_info package which implements ResumeInfo and implement all required methods in that class.
3. Modify the first line of the main method in the Main class to use your ResumeInfo implementation.
4. Package the program as a runnable JAR file. Instructions for doing this in [Eclipse](https://www.eclipse.org/downloads/) can be found [here](https://www.wikihow.com/Create-an-Executable-File-from-Eclipse).
5. Run the JAR file as described above.
