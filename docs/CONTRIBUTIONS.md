# CONTRIBUTIONS.md
**CS 2430-502 | Programming Project 1: Sets, Multisets, and Natural-Language Data Queries**

---

## Team Members & Roles

| Name          | Role                  |
|---------------|-----------------------|
| Alex Branch    | Implementation Lead  |
| Logan Chess    | Verification Lead    |
| Julian Cloward | Communications Lead  |

---

## Responsibilities & Evidence

### Logan Chess — Verification Lead
Owned test plan, edge cases, and verification evidence.


**Contributions:**
- Defined the universal set structure within the `SetOperationsDemo.java`
- Designed and implemented `Student.java` — the implementation of the Student data unit.
- Designed and implemented `BitStringSet.java` — boolean[] bit-string representation of ordinary sets
- Implemented all ordinary set operations: complement, union, intersection, difference, symmetric difference
- Authored Introduction section of the team report
- Authored Discussion questions 1–4 of the team report

**Evidence Pointers:**
- `MergeSort.java`
    - Commit `6e0303585ead7418220c1b7ede9e210104838d30`
- `QuickSort.java`
    - Commit `7392e70057701b17c21dfc0b4a10bcb2a66f8195`
- Part 4 implementation within `TestDriver.java`
    - Commit `20c2f439db54f202c5fe416ec6a81eb660482410`
- UML Diagram of Source Code:
    - Commit `2dd15485a663fdb1b02cc97b493bfc713222e6cb`
- Report sections: Introduction, Discussion §1–4
    - Commit `TBD`
    - Commit `TBD`

---

### Julian Cloward — Communications Lead
Owned ...

**Contributions:**
- Designed `Test.java` — implementation for representative cases and edge cases
- Implementation of `BagC-F`
- Created runnable .jar file to run demo
- UML diagram for Part 1
- Video assembly and upload
- Results section and Appendix for team report

**Evidence Pointers:**
- UML diagram
    - Commit `874a38fa609d21456d9019109fdc80a392179a17`
- `Test.java` & Bag files
    - Commit `2d8b2933329e451679e73b928576c00b7f0c6ee0`
- 'setOpsDemo.jar'
    - Commit `55828263e8ab4bf45247c212e401eb0ad5146232`
- Report Sections: Results, Appendix
    - Commit `1ab868c4c6258a1670504035500a5fb746349c71`

---

### Alex Branch — Implementation Lead
Owned overall branch health, implementation of permutation generator, initial implementation of test driver, and several sections in the final report and video.

**Contributions:**
- Implementation Lead: managed main branch health, ensured overall code structure could be easily imported into an IDE, compiled, and run without issues
- Project setup: Wrote scoping document for project
- Code contributions: Wrote the code for permutation generator, set up the overall structure for the program by writing the `SortResult` class and the inital implementation of `TestDriver`
- Final report: Wrote the introduction, algorithm summaries, and methods
- Final video: recorded sections for permutation generator and comparison counters

**Evidence Pointers:**
- project scoping document: Commit 3/9 - abe5089
- refactor project into single package: commits 3/10 - 88c71e7, 532ac70, 2389570, cd8718b
- permutation generator: Commit 3/10 - 88c71e7
- permutation generator update (toString): commit 3/21 - 015013b
- testDriver, sortResult: commit 3/11 - b5d5f4f 
- report writing: commit 3/21 - 8dfb9ab
