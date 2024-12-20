# Routing Wires

## Description
The Routing Wires project is designed to simulate wire routing on a circuit board while avoiding obstacles and ensuring efficient connections between endpoints. This program uses algorithms like Breadth-First Search (BFS) and backtracking to achieve accurate and optimized results.

## Key Features
- **Efficient Pathfinding**: Utilizes BFS to find the shortest path between endpoints on the board.
- **Obstacle Avoidance**: Ensures wires do not overlap with obstacles or other wires.
- **Backtracking Mechanism**: Correctly adjusts wire placements if conflicts arise during routing.
- **Custom Test Cases**: Supports multiple board configurations, including solvable and unsolvable setups.

## Performance
- **Time Complexity**: Overall complexity is **O(n * (E + V))**, where:
    - `n` = number of wires
    - `E` = edges (connections between coordinates)
    - `V` = vertices (coordinates on the board)

## Highlights
This project demonstrates a real-world application of graph traversal and optimization techniques, making it a great example of algorithmic problem-solving in Java.
