# SLCC2430_Proj2
Sorting Algorithms
Team: 2

Members: Alexander Branch, Julian Cloward, Logan Chess 

Course: Salt Lake Community College, CS 2430, Section 502

Project: Programming Project 2: Sorting Algorithms, Efficency, and Performance

Spring 2026

---

Program implements sorting algorithms and collects algorithm element comparison count with all permutations of a specified length. Displaying best and worst permutation cases for each algorithm as well as the average comparison count. 

---

Repository layout

```
SLCC_CS2430_Proj2/
	src/
    algorithmPerformance/
      Algorithm.java
      HeapSort.java
      PermutationGenerator.java
      QuickSort.java
      ShakerSort.java
      SortResult.java
      TestDriver.java
      Verification.java
	
	docs/
		CONTRIBUTIONS.md
        Programming Project 2 Report.docx
    	Project 2 Plan.xlsn
		Project 2 Scope.docx
    	README.md
    	Results & Questions.docx
    	SortDriver UML.png
    	screen recording title slide.pptx

  .gitignore
  SLCC_CS2430_Proj2.jar

```
---

Prerequisites

- Java 17 or later (JDK or JRE). Run the following command to verify:
```bat
java -version
```

---

Setup

Download these files from the repository and store in a project directory location:
```
SLCC_CS2430_Proj2/
	src/
    algorithmPerformance/
      Algorithm.java
      HeapSort.java
      PermutationGenerator.java
      QuickSort.java
      ShakerSort.java
      SortResult.java
      TestDriver.java

  SLCC_CS2430_Proj2.jar
```
	
**Note**: `SLCC_CS2430.jar` MUST be in the same root folder as `src/` for file IO to properly execute.
 
 ---

Running the packaged JAR on Windows Command Prompt

1. Set the working directory to the project root location

Open Command Prompt and change the working directory to the project root. Example if the project is in `C:\Projects\SLCC_CS2430_Proj2`:
```bat
cd /d C:\Projects\SLCC_CS2430_Proj2
```
Use `/d` to change drive letters if needed. If the path contains spaces, wrap it in quotes:
```bat
cd /d "C:\My Projects\SLCC_CS2430_Proj2"
```

---

2. Run the runnable JAR

In the command prompt window, enter:
```bat
java -jar SLCC_CS2430_Proj2.jar
```
This launches the program exactly as packaged.

---

Running the project from an IDE such as Eclipse. Ensure you are using Java SE 17+.

1. Open a project folder with a ```src/``` directory in the IDE
2. Import all files from the downloaded ```src/``` directory into the IDE ```project/src/``` location
3. Open and run SetOperationsDemo.java
4. (Optional) To run the testing program:
   A. If you have not already, download additional repo file:
	src/
	  algorithmPerformance/
       Verification.java
   B. Open and run `Verification.java`.
   This file runs tests on all algorithms within the program testing for edge cases and stability:

   **Permutation Tests:**
   - Array Size 1-6
   - Array contains duplicate values

   **Algorithm Tests:**
   - Correctness
   - Empty Array
   - Single Value
   - Two values
   - Revursed Values

