package com.company;

import java.util.*;


 class DPQ {
    private int dist[];
    private ArrayList<Node> route = new ArrayList<>();
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V;
    List<List<Node> > adj;

    public DPQ(int V)
    {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    public void dijkstra(List<List<Node> > adj, int src)
    {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        pq.add(new Node(src, 0));

        dist[src] = 0;
        while (settled.size() != V) {


            int u = pq.remove().node;

            settled.add(u);

            e_Neighbours(u);
        }
    }


    private void e_Neighbours(int u)
    {
        int edgeDistance = -1;
        int newDistance = -1;



        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been iterated
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node]) {
                    dist[v.node] = newDistance;
                    route.add(new Node(v.node, dist[v.node], v.name));

                }
                    // Add the current node to the queue
                    pq.add(new Node(v.node, dist[v.node]));



            }
        }
    }


    public static void main(String arg[])
    {
        int V = 5;
        int source = 0;


        List<List<Node> > adj = new ArrayList<List<Node> >();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

//inputs
        System.out.println("The algorithm travels the shortest distance but does not show the actual pathway");
        //TODO: fix the route
        adj.get(0).add(new Node(1, 9,"Cohen"));
        adj.get(0).add(new Node(2, 6,"Boys Dorm"));
        adj.get(0).add(new Node(3, 5,"Chapel"));
        adj.get(0).add(new Node(4, 3,"Girls Dorm"));

        adj.get(2).add(new Node(1, 2,"Science Building"));
        adj.get(2).add(new Node(3, 4,"Rooks"));

        DPQ dpq = new DPQ(V);
        dpq.dijkstra(adj, source);


        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]+" and visits: "+dpq.route);
    }
}

class Node implements Comparator<Node> {
    public int node;
    public int cost;
    public String name;

    public Node()
    {
    }

    public Node(int node, int cost,String name)
    {
        this.node = node;
        this.cost = cost;
        this.name = name;
    }

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
        this.name = name;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}