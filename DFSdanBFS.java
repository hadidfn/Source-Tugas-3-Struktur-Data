import java.util.*;

public class DFSdanBFS {
    
    private Map<String, List<String>> adj;
    private Set<String> visited; 
    
    // Inisialisasi Graf
    public DFSdanBFS() {
        adj = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            adj.put("a" + i, new LinkedList<>());
        }
        
        adj.get("a1").addAll(Arrays.asList("a2", "a3"));
        adj.get("a2").addAll(Arrays.asList("a4", "a5"));
        adj.get("a3").add("a6");
        adj.get("a4").add("a7");
        adj.get("a5").add("a8");
    }

    private boolean dfsRecursive(String currentNode, String targetNode, List<String> path) {
        visited.add(currentNode);
        path.add(currentNode); 
        System.out.println("  Mengunjungi Node DFS: " + currentNode);

        if (currentNode.equals(targetNode)) {
            System.out.println("TARGET DITEMUKAN di node: " + currentNode);
            return true;
        }

        for (String neighbor : adj.getOrDefault(currentNode, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                if (dfsRecursive(neighbor, targetNode, path)) {
                    return true; 
                }
            }
        }
        
        return false; 
    }

    public void runDFS(String start, String target) {
        System.out.println("\n_____________________________________");
        System.out.println("Mulai DEPTH-FIRST SEARCH (DFS) ");
        System.out.println("Dari " + start + " untuk mencari " + target);
        System.out.println("_____________________________________");
        visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        
        boolean found = dfsRecursive(start, target, path);
        
        if (found) {
            System.out.println("\nPencarian DFS Berhasil! Jalur yang Dilalui: " + String.join(" -> ", path));
        } else {
            System.out.println("\nPencarian DFS Gagal. Target tidak ditemukan.");
        }
        System.out.println("----------------------------------------------");
    }


    public boolean runBFS(String startNode, String targetNode) {
        System.out.println("\n_____________________________________");
        System.out.println("Mulai BREADTH-FIRST SEARCH (BFS) ");
        System.out.println("Dari " + startNode + " untuk mencari " + targetNode);
        System.out.println("_____________________________________");
        
        Set<String> visitedBFS = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>(); // Untuk rekonstruksi jalur

        visitedBFS.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            String currentNode = queue.poll();
            System.out.println("  Mengunjungi Node BFS: " + currentNode);

            if (currentNode.equals(targetNode)) {
                System.out.println("TARGET DITEMUKAN di node: " + currentNode);

                List<String> path = new LinkedList<>();
                String current = targetNode;
                while (current != null) {
                    path.add(0, current); 
                    current = parentMap.get(current);
                }
                System.out.println("\nPencarian BFS Berhasil! Jalur terpendek: " + String.join(" -> ", path));
                System.out.println("----------------------------------------------");
                return true;
            }

            for (String neighbor : adj.getOrDefault(currentNode, Collections.emptyList())) {
                if (!visitedBFS.contains(neighbor)) {
                    visitedBFS.add(neighbor);
                    queue.add(neighbor);
                    parentMap.put(neighbor, currentNode); // Catat induk
                }
            }
        }
        
        System.out.println("\nPencarian BFS Gagal. Target tidak ditemukan.");
        System.out.println("----------------------------------------------");
        return false;
    }


    public static void main(String[] args) {
        DFSdanBFS program = new DFSdanBFS();
        String start = "a1";
        String target = "a4";

        program.runDFS(start, target); 

        program.runBFS(start, target);
    }
}