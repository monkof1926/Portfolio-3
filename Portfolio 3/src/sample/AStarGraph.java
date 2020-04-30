package sample;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class AStarGraph {
    public String Name;
    public ArrayList<Vertex> vertices;
    public AStarGraph() {
        vertices=new ArrayList<Vertex>();
    }
    public void addvertex(Vertex v) {
        vertices.add(v);
    }
    public void newconnection(Vertex v1, Vertex v2, Double dist) {
        v1.addOutEdge(v2,dist);
        v2.addOutEdge(v1,dist);
    }

    public boolean A_Star(Vertex start, Vertex destination, boolean Euclidean) {
        if (start == null || destination == null)
            return false;
        PriorityQueue<Vertex> Openlist = new PriorityQueue<Vertex>();
        ArrayList<Vertex> Closedlist = new ArrayList<Vertex>();
        Openlist.offer(start);
        Vertex Current;
        ArrayList<Vertex> CurrentNeighbors;
        Vertex Neighbor;
        //Initialize h with chosen heuristic
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setPrev(null);
            vertices.get(i).setg(Double.POSITIVE_INFINITY);
            if(Euclidean) {
                vertices.get(i).seth(Euclidean(vertices.get(i), destination));
            }else{
                vertices.get(i).seth(Manhattan(vertices.get(i), destination));
            }
            }
        start.setg(0.0);
        start.calculatef();
        //Start algorithm
        System.out.println("Start Algorithm");
        //Implement the Astar algorithm
        Current = null;
        while (!Openlist.isEmpty()) {
            Current = Openlist.remove();
            if (Current == destination)
                return true;
            Closedlist.add(Current);
            for (int i = 0; i < Current.getNeighbours().size(); i++) {

                Vertex v = Current.getNeighbours().get(i);
                Double tempgofv = Current.getg() + Current.getNeighbourDistance().get(i);
                if (tempgofv < v.getg()) {
                    v.setPrev(Current);
                    v.setg(tempgofv);
                    v.calculatef();
                    if (!Closedlist.contains(v) && !Openlist.contains(v)) {
                        Openlist.offer(v);
                    }
                    if (Openlist.contains(v)) {
                        Openlist.remove(v);
                        Openlist.offer(v);
                    }

                }
            }
        }
            return false;
        }
    public void Vertexx(String id){
        Name=id;
    }
    public Double Manhattan(Vertex from,Vertex destination){
        double x0 = from.getx();
        double y0 = from.gety();
        double x1 = destination.getx();
        double y1  = destination.gety();
        double distance = Math.abs(x1-x0)+Math.abs(y1-y0);
        return distance;
    }
    public Double Euclidean( Vertex from,Vertex to){
        double x0 = from.getx();
        double y0 = from.gety();
        double x1 = to.getx();
        double y1 = to.gety();
        double Edistance = Math.sqrt((y1-y0)*(y1-y0+(x1-x0)*(x1-x0)));
        return Edistance;
    }
}

class Vertex implements Comparable<Vertex>{
    public String Name;
    private String id;
    private ArrayList<Vertex> Neighbours=new ArrayList<Vertex>();
    private ArrayList<Double> NeighbourDistance =new ArrayList<Double>();
    private Double f;
    private Double g;
    private Double h;
    private Integer x;
    private Integer y;
    private Vertex prev =null;
    public Vertex(String id, int x_cor,int y_cor){
        this.id=id;
        this.x=x_cor;
        this.y = y_cor;
        f=Double.POSITIVE_INFINITY;
        g=Double.POSITIVE_INFINITY;
        h=0.0;
    }
    public void addOutEdge(Vertex toV, Double dist){
        Neighbours.add(toV);
        NeighbourDistance.add(dist);
    }
    public ArrayList<Vertex> getNeighbours(){
        return Neighbours;
    }
    public ArrayList<Double> getNeighbourDistance(){
        return NeighbourDistance;
    }
    public String getid(){ return id;};
    public Integer getx(){ return x; }
    public Integer gety(){return y; }  // now it is return x which is an error!
    public Double getf() { return f; }
    public void calculatef(){ f=g+h; }

    public Double getg() { return g; }

    public void setg(Double newg){ g=newg; }
    public Double geth(){ return h; }
    public void seth(Double estimate){ h=estimate; }
    public Vertex getPrev() { return prev; }
    public void setPrev(Vertex v){prev=v;}
    public void printVertex(){
        System.out.println(id + " g: "+g+ ", h: "+h+", f: "+f);
    }
    @Override
    public int compareTo(Vertex vertex) {
        if (this.f > vertex.f)
            return 1;
        if (this.f < vertex.f)
            return -1;
        return 0;
    }
}
