# Binomial Queue Implementation

## Description
This project implements a **Binomial Queue**, a priority queue based on a collection of Binomial Heaps. It efficiently supports insertion, deletion, and merging of heaps while maintaining the heap property.

The project also includes a **Persistent List (PList)** utility for managing linked list-like data structures in a functional programming style.

## Key Features
- **BinomialQueue Class**: Implements the main operations of a binomial queue, including:
    - `push`: Insert an element into the queue.
    - `pop`: Remove and return the maximum element from the queue.
    - `isHeap`: Verify that the queue satisfies the heap property.
    - `merge`: Combine two sorted forests of heaps.
- **BinomialHeap Class**: Represents a single heap node with methods like:
    - `link`: Merge two heaps of the same height.
    - `isHeap`: Verify the heap property for a subtree.
- **Persistent List (PList)**:
    - Functional linked list operations like `addFront`, `getFirst`, `getNext`, `remove`, `reverse`, and `find_max`.

## Highlights
- **Object-Oriented Design**: Modular design with clear separation of concerns between BinomialQueue, BinomialHeap, and PList.
- **Time Complexity**:
    - `push`: O(log n)
    - `pop`: O(log n)
    - `merge`: O(log n)