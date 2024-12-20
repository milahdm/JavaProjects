import java.util.*;

public class Routing {

    /**
     * TODO
     * <p>
     * The findPaths function takes a board and a list of goals that contain
     * endpoints that need to be connected. The function returns a list of
     * Paths that connect the points.
     */

    /**
     * Finds paths connecting specified endpoints on a board using backtracking to handle conflicts.
     *
     * @param board the board with vertices and obstacles
     * @param goals list of endpoints that need to be connected
     * @return list of wires if successful, null if unable to place all wires
     */

    // O(n * (E+V))
    public static ArrayList<Wire> findPaths(Board board, ArrayList<Endpoints> goals) {
        ArrayList<Wire> wires = new ArrayList<>();
        if (placeWiresBacktrack(board, new LinkedList<>(goals), wires)) {
            return wires;
        } else {
            System.out.println("Unable to place all wires.");
            return null;
        }
    }


    /**
     * Attempts to place each wire, rearranges previous wires if necessary, and uses backtracking.
     *
     * @param board the board
     * @param goals list of endpoint pairs
     * @param wires list of successfully placed wires
     * @return true if all wires are successfully placed
     */

    // O(n * (E+V))
    private static boolean placeWiresBacktrack(Board board, LinkedList<Endpoints> goals, ArrayList<Wire> wires) {
        if (goals.isEmpty()) {
            return true;  // All wires placed successfully
        }

        int size = goals.size();

        for (int i = 0; i < size; i++) {
            Endpoints endpoints = goals.poll();
            assert endpoints != null;
            Wire wire = bfs(board, endpoints.start, endpoints.end, endpoints.id);
            if (wire != null && checkWirePlacement(board, wire)) {
                board.placeWire(wire);
                wires.add(wire);
                // Recursively attempt to place the next wire
                if (placeWiresBacktrack(board, goals, wires)) {
                    return true;
                }
                // Backtracking done here
                board.removeWire(wire);
                wires.remove(wire);
            }

            goals.offer(endpoints);
        }
        return false;
    }

    /**
     * BFS to find the shortest path between two points
     *
     * @param board the board
     * @param start starting coordinate
     * @param end ending coordinate
     * @param wireId the identifier of the wire
     * @return the wire representing the path, or null if no path is found
     */

    // O(E + V) edges + vertices
    private static Wire bfs(Board board, Coord start, Coord end, int wireId) {
        Queue<Coord> queue = new LinkedList<>();
        Map<Coord, Coord> parent = new HashMap<>();
        Set<Coord> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coord current = queue.poll();
            if (current.equals(end)) {
                return new Wire(wireId, reversePath(parent, end));
            }

            for (Coord neighbor : board.adj(current)) {
                if (!visited.contains(neighbor) && (board.getValue(neighbor) == 0 || board.getValue(neighbor) == wireId)) {
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return null;
    }


    /**
     * Reverses the path from end to start using the parent map and returns it in the correct order.
     *
     * @param parent Map of parent-child relationships built during BFS
     * @param end Ending coordinate of the path
     * @return List of coordinates representing the path from start to end
     */

    // O(n) where n is the number of coordinates
    private static List<Coord> reversePath(Map<Coord, Coord> parent, Coord end) {
        List<Coord> path = new ArrayList<>();
        Coord step = end;
        while (step != null) {
            path.add(step);
            step = parent.get(step);
        }
        Collections.reverse(path);
        return path;
    }


    /**
     * Checks if a wire can be placed on the board without overlapping with obstacles or other wires.
     *
     * @param board the board
     * @param wire the wire to check
     * @return true if the wire can be placed, false otherwise
     */

    // O(n) for n number of coordinates in the wire
    private static boolean checkWirePlacement(Board board, Wire wire) {
        for (Coord coord : wire.getPoints()) {
            if (board.isObstacle(coord) || (board.isOccupied(coord) && board.getValue(coord) != wire.id)) {
                return false;
            }
        }
        return true;
    }
}







//    /**
//     * Recursively attempts to place each wire and backtracks if necessary, using a shared parent map.
//     *
//     * @param board the board with vertices and obstacles
//     * @param goals list of endpoint pairs
//     * @param wires current list of successfully placed wires
//     * @param index current index in the goals list
//     * @param parent shared map of parent relationships from BFS
//     * @return true if all wires from the current index onwards are placed successfully
//     */
//    private static boolean placeWiresBacktrack(Board board, ArrayList<Endpoints> goals, ArrayList<Wire> wires, int index, Map<Coord, Coord> parent) {
//        if (index == goals.size()) {
//            return true;  // All wires placed successfully
//        }
//
//        Endpoints endpoints = goals.get(index);
//        Wire wire = bfs(board, endpoints.start, endpoints.end, endpoints.id);
//        if (wire != null) {
//            board.placeWire(wire);  // Place the wire on the board
//            wires.add(wire);
//            if (placeWiresBacktrack(board, goals, wires, index + 1, parent)) {
//                return true;  // Successfully placed all subsequent wires
//            }
//            // Backtrack to remove the wire and try the next possibility
//            board.removeWire(wire);
//            wires.remove(wires.size() - 1);
//        }
//        return false;  // Unable to place this wire or any configuration thereof
//    }






//    public static ArrayList<Wire>
//    findPaths(Board board, ArrayList<Endpoints> goals) {
//        ArrayList<Wire> wires = new ArrayList<>();
//
//        for (Endpoints point: goals)
//        {
//            Wire wire = bfs(board, point.start, point.end, point.id); // search for a path.
//            if (wire != null)
//            {
//                wires.add(wire); // add new wire to list
//
//            }
//        }
//
//        return wires;  // returns the array list wire, which is an arraylist of coordinates representing the wire path
//    }

//    public static ArrayList<Wire> findPaths(Board board, ArrayList<Endpoints> goals) {
//        ArrayList<Wire> wires = new ArrayList<>();
//        LinkedList<Wire> tempRemovedWires = new LinkedList<>();
//
//        for (Endpoints point : goals) {
//            if (!attemptPlaceWire(board, point, wires, tempRemovedWires)) {
//                System.out.println("Failed to place all wires due to crossing paths or lack of available routes.");
//                return new ArrayList<>();  // Return an empty list or handle the failure as appropriate
//            }
//        }
//
//        return wires;
//    }

//    /**
//     * Finds paths connecting specified endpoints on a board. Tries to reroute wires if initial placement fails due to path blockages.
//     *
//     * @param board the board with vertices and obstacles
//     * @param goals list of endpoints that need to be connected
//     * @return list of wires representing the paths between the endpoints
//     */
//    public static ArrayList<Wire> findPaths(Board board, ArrayList<Endpoints> goals) {
//        ArrayList<Wire> wires = new ArrayList<>();
//        boolean allWiresPlaced = false;
//        int attemptCount = 0;
//        int maxAttempts = 10; // Maximum number of attempts to place all wires before giving up
//
//        while (!allWiresPlaced && attemptCount < maxAttempts) {
//            wires.clear(); // Clear previous attempts
//            allWiresPlaced = placeAllWires(board, goals, wires);
//
//            if (!allWiresPlaced) {
//                System.out.println("Not all wires could be placed, retrying...");
//                attemptCount++;
//            } else {
//                System.out.println("All wires successfully placed.");
//                break;
//            }
//        }
//
//        if (attemptCount == maxAttempts) {
//            System.out.println("Failed to place all wires after " + maxAttempts + " attempts.");
//        }
//
//        return wires;
//    }
//
//
//
//    /**
//     * Finds paths connecting specified endpoints on a board. Tries to reroute wires if initial placement fails due to path blockages.
//     *
//     * @param board the board with vertices and obstacles
//     * @param goals list of endpoints that need to be connected
//     * @param wires list of wires
//     * @return boolean that indicates whether or not all wires were placed
//     */
//    private static boolean placeAllWires(Board board, ArrayList<Endpoints> goals, ArrayList<Wire> wires) {
//        ArrayList<Wire> tempRemovedWires = new ArrayList<>();
//
//        for (Endpoints point : goals) {
//            Wire wire = bfs(board, point.start, point.end, point.id);
//            if (wire == null) {
//                if (!handleWirePlacementFailure(board, wires, tempRemovedWires, point)) {
//                    return false; // Return false indicating not all wires could be placed
//                }
//            } else {
//                wires.add(wire); // Successfully placed wire
//                board.placeWire(wire);
//            }
//        }
//        return true;
//    }






//
//    /**
//     * This method hands the situation in which the wires were not successfully placed. it removes the last wire and
//     *
//     * @param board the board with vertices and obstacles
//     * @param tempRemovedWires - list of temporalrily removed wires to handle for cross-over
//     * @param wires list of wires
//     * @return boolean that indicates whether or not all wires were placed
//     */
//    private static boolean handleWirePlacementFailure(Board board, ArrayList<Wire> wires, ArrayList<Wire> tempRemovedWires, Endpoints point) {
//        // Try removing last few wires and reroute
//        while (!wires.isEmpty()) {
//            Wire lastWire = wires.remove(wires.size() - 1);
//            board.removeWire(lastWire);
//            tempRemovedWires.add(0, lastWire);  // Reverse order rerouting
//
//            Wire newWire = bfs(board, point.start, point.end, point.id);
//            if (newWire != null) {
//                wires.add(newWire);
//                board.placeWire(newWire);
//                return rerouteRemovedWires(board, wires, tempRemovedWires);
//            }
//        }
//        return false; // No solution found, even after removing all wires
//    }
//
//    /**
//     * This method retunrs
//     *
//     * @param board the board with vertices and obstacles
//     * @param tempRemovedWires list of removed wires that were not able to be placed
//     * @param wires list of wires
//     * @return boolean that indicates whether or not the rerouted wires were successfully placed
//     */
//
//    private static boolean rerouteRemovedWires(Board board, ArrayList<Wire> wires, ArrayList<Wire> tempRemovedWires) {
//        while (!tempRemovedWires.isEmpty()) {
//            Wire wireToReroute = tempRemovedWires.remove(0);
//            Wire reroutedWire = bfs(board, wireToReroute.start(), wireToReroute.end(), wireToReroute.id);
//            if (reroutedWire == null) {
//                return false; // Failed to reroute a wire
//            }
//            wires.add(reroutedWire);
//            board.placeWire(reroutedWire);
//        }
//        return true;
//    }




