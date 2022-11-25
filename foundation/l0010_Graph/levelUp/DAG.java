import java.util.ArrayList;
import java.util.LinkedList;

public class DAG {
    public static class Edge {
        int src;
        int nbr;
        int weight;

        Edge(int src, int nbr, int weight) {
            this.src = src;
            this.nbr = nbr;
            this.weight = weight;
        }
    }

    // ----------Construction--------------
    public static void construction() {
        int N = 8;
        // Here, each index of an array of name graph is able to hold ArrayList but
        // actually not holding any ArrayList.
        ArrayList<Edge>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>(); // Now here, array is actually holding an empty arraylist.

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        // boolean[] vis = new boolean[N];
        // preorder(graph, 0, vis, 0, "");
    }

    // ---------------Addition of Edge--------------
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
    }

    // ---------------- Display ----------------
    public static void display(ArrayList<Edge>[] graph, int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " ---> ");
            for (Edge e : graph[i])
                System.out.print("(" + e.nbr + ", " + e.weight + ")");

            System.out.println();
        }
    }

    // ------------- Remove Edge -------------
    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int idx1 = findEdge(graph, u, v);

        graph[u].remove(idx1);
    }

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        ArrayList<Edge> list = graph[u];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).nbr == v)
                return i;
        }
        return -1;
    }


    // ------------ Topological Sort -------------
    // Using DFS----------
    public static void topologicalSort(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        ArrayList<Integer> topo = new ArrayList<>();
        for(int i = 0; i < graph.length; i++) {
            if(!vis[i]) 
                dfs_topo(graph, i, vis, topo);
        }

        for(int i = topo.size() - 1; i >= 0; i--) 
            System.out.println(topo.get(i));
    }

    public static void dfs_topo(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> topo) {
        vis[src] = true;

        for(Edge e : graph[src]) {
            if(!vis[e.nbr]) 
                dfs_topo(graph, e.nbr, vis, topo);
        }

        topo.add(src);
    } 

    // Using BFS----------
    public static void topoSortBFS(ArrayList<Edge>[] graph) {
        int V = graph.length;
        int[] indegree = new int[V];

        for(int i = 0; i < V; i++) 
            for(Edge e : graph[i]) 
                indegree[e.nbr]++;

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> topo = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
            if(indegree[i] == 0) 
                que.addLast(i);
        }

        while(que.size() != 0) {
            int ele = que.removeFirst();
            topo.add(ele);

            for(Edge e : graph[ele]) {
                indegree[e.nbr]--;
                if(indegree[e.nbr] == 0)
                    que.addLast(e.nbr);
            }
        }
    }
}
