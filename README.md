# Sliding Puzzle Solver with Pathfinding Algorithms

## Overview

This project provides a solution for solving sliding puzzles, which are commonly found in video games. The goal is to find the shortest path from a starting point ('S') to a finish ('F') on a grid where the player slides across frictionless ice, and must navigate around obstacles ('0') and walls. The project implements various algorithms to determine the shortest path and outputs the sequence of moves required to solve the puzzle.

The grid is read from an input file, and the solver outputs the solution with all the necessary steps. The project includes performance analysis, algorithm selection, and an evaluation of the data structures used.

## Sliding Puzzle Problem

### The Problem:
- The player starts at the position labeled "S" and must reach the position labeled "F".
- The player can move in one of the four cardinal directions (up, down, left, right).
- The player slides in the chosen direction until they hit a wall or an obstacle ('0'), and must navigate through the grid accordingly.

The objective is to find the shortest path from "S" to "F" by implementing efficient pathfinding algorithms.

## Tasks

### Task 1: Project Setup
Set up the project in either Java or C++. This forms the base for the puzzle solver, where the input, processing, and solution generation will be handled.

### Task 2: Data Structure for Map Representation
Implement a data structure to represent the puzzle grid (map). The grid will contain cells marked as:
- `.` for frictionless ice (empty squares)
- `0` for rocks (obstacles)
- `S` for the start position
- `F` for the finish position

This structure should support efficient pathfinding.

### Task 3: Map Parser
Write a parser to read the grid from an input file. The input file format contains symbols that represent ice, rocks, the start, and finish. The parser will determine the width, height, and the locations of rocks, the start, and finish.

### Task 4: Pathfinding Algorithm
Implement a pathfinding algorithm (e.g., A*, BFS, or Dijkstra) to find the shortest path from "S" to "F" in the puzzle grid. The algorithm should take into account the sliding mechanic, where the player continues moving until they hit a wall or obstacle. If a solution exists, it should output all steps.


### Task 5: Performance Report
Write a brief report discussing:
- The choice of data structures and pathfinding algorithm.
- Example run of the algorithm on a small benchmark, detailing each step and output.
- Performance analysis (both theoretical and empirical) of the algorithm's efficiency using Big-O notation and practical testing.

## Installation and Usage

### Prerequisites:
- Java (JDK 11+) or C++ (G++ or MSVC)
- A text editor or IDE for writing and compiling code (e.g., IntelliJ, Eclipse, Visual Studio)

### Running the Project:
1. Clone the repository:
   ```bash
   git clone https://github.com/sara7869/Sliding-Puzzle-Solver-with-Pathfinding-Algorithms.git
2. Compile the project using your preferred IDE or the command line.

3. Run the solver with a map file as input:

4. The solution will print the shortest path step-by-step.

