/* File : VertexDataBase.java
 * ________________________________________________________________________________________________
 * This class keeps track of Vertex that are accessible through vertex no.
 * ________________________________________________________________________________________________
 * @author : Shankul Jain
 */

import java.util.*;
import java.io.*;

public class VertexDataBase{
	
/*Constructor : VertexDataBase(filename) */
/**
 * creates a new VertexDataBase and initializes it using the data in specified file.
 * Data of graph is in Adjecency list representation.
 * Constructor throws an error exception if file is not found or could not be read.
 * A Map<Integer,Vertex> inventory and ArrayList<Integer> verticesList is used to
 * keep track of data. verticesList is used to select a vertex at random while hashmap
 * returns the actual vertex.
 */
	public VertexDataBase(String filename) throws IOException{
	
		BufferedReader rd = new BufferedReader(new FileReader(filename));
		
		while(true){
			String str = rd.readLine();
			if(str == null) break;
			Vertex vertex = new Vertex(str);
			inventory.put(vertex.getMyVertexNo(),vertex);
			verticesList.add(vertex.getMyVertexNo());
		}
		
	}
	
/*Method : getRandomVertex() */	
/** returns a object of class Vertex Randomly at uniform */
	public Vertex getRandomVertex(){
		return inventory.get(verticesList.get(rgen.nextInt(verticesList.size())));
	}
	
/*Method : getRandomEdgeVertex(Vertex) */	
/** returns the vertex that has edge with vertex v randomly at uniform */
	public Vertex getRandomEdgeVertex(Vertex v){
		return inventory.get(v.getRandomEdgeVertexNo());
	}
	
/* Method : contractAndUpdateDataBase(Vertex v,Vertex w) */
/** 
 * contracts the given vertices v and w to v and makes the required changes
 * in neighbour vertices list of v and update the other vertices' neighbour
 * vertices list that has w as their neighbour vertex.
 * And after updation it removes the vertex w from database.
 */
	public void contractAndUpdateDataBase(Vertex v, Vertex w){
		v.updateNeighbourVertices(w.getMyVertexNo(),w.getNeighbourVerticesList());
		updateOtherVertices(w,v.getMyVertexNo());
		inventory.remove(w.getMyVertexNo());
		verticesList.remove(w.getMyVertexNo());
	}
	
/* Method : updateOtherVertices(Vertex w, int otherVertexNo) */
/**
 *  this method updates the neighbour vertices list of those vertices
 *  which has w as their neighbour vertex. this is done by through iterating
 *  over w's neighbour vertices list and replacing the vertex no of w with
 *  other vertex no that is v's vertex no.
 */
	private void updateOtherVertices(Vertex w,int otherVertexNo){
		ArrayList<Integer> wList = w.getNeighbourVerticesList();
		for(int i : wList){
			Vertex x = inventory.get(i);
			x.replaceVertexNoWithAnotherVertexNo(w.getMyVertexNo(),otherVertexNo);
		}
	}
	
/* Method : nEdges(Vertex v) */
/**
 * this method returns the no of edges with v as one of endpoint of edge.
 */
	public int nEdges(Vertex v){
		return v.nEdges();
	}

/* Method : verticesInDataBase() */
/**
 * this method returns the size of the inventory
 */
	public int verticesInDataBase(){
		return inventory.size();
	}
	
/* private instance variables */
	private Map<Integer,Vertex> inventory = new HashMap<Integer,Vertex>();
	private ArrayList<Integer> verticesList = new ArrayList<Integer>();
	private Random rgen = new Random();
}