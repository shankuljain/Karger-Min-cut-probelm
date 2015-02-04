/*
 * File : Vertex.java
 * ________________________________________________________________________________________________
 * This class keeps track of information about a single Vertex.
 * Vertex no is stored in myVertexNo and neighbor vertices no are stored in ArrayList<Integer>.
 * ________________________________________________________________________________________________
 * @author : Shankul Jain
 */

import java.util.*;

public class Vertex{
	
/* Constructor : Vertex(line) */
/**
 * This method creates the new object of the class Vertex
 * and calls the method parseLine(line) that reads the information
 * from the line and stores it in required format.
 */
	public Vertex(String line){
		parseLine(line);
	}
	
/* Method : parseLine(line) */
/**
 * This method processes the given line and stores the first integer to vertex no
 * and other values to neighbour vertices list.
 * 
 * if() statement in this method makes sure that there are no self-loops.
 */
	private void parseLine(String line){
		StringTokenizer token = new StringTokenizer(line);
		myVertexNo = Integer.parseInt(token.nextToken());
		while(token.hasMoreTokens()){
			int vertexNo = Integer.parseInt(token.nextToken());
			if(vertexNo != myVertexNo){
				neighbourVerticesList.add(vertexNo);
			}
		}
	}
	
/* Method : getMyVertexNo() */
/**
 * returns myVertexNo as Integer object instead of primitive data-type
 * because sometimes there is a need to remove the vertex from database
 * and remove method in arrayList should have argument as object to remove
 * that object.
 * 
 * We dont want to remove the element at the index returned by this method.
 */
	public Integer getMyVertexNo(){
		return myVertexNo;
	}
	
/* Method : getRandomEdgeVertexNo() */
/** returns the vertex no of any edge from myVertexNo randomly at uniform */
	public int getRandomEdgeVertexNo(){
		return neighbourVerticesList.get(rgen.nextInt(neighbourVerticesList.size()));
	}
	
/* Method : updateNeighbourVertices(Integer vertexNo, ArrayList<Integer> list) */	
/**
 * After contracting two vertices update neighborhood vertices list for one
 * and delete the other vertex from database. since neighbourVerticesList is 
 * an ArrayList<Integer>, to remove an object from this list the argument in remove
 * method should be a object rather than a primitive data-type.
 * 
 * while contracting two vertices there are chances of forming the self-loops,
 * this method also makes sure that all the self-loops are removed as soon as
 * they are formed.
 */
	public void updateNeighbourVertices(Integer vertexNo, ArrayList<Integer> list){
		
		while(neighbourVerticesList.remove(vertexNo));
		
		for(Integer i : list){
			if(i!=myVertexNo){
				neighbourVerticesList.add(i);
			}
		}
	}
	
/* Method : replaceVertexNoWithAnotherVertexNO(int initialVertexNo, int otherVertexNo) */
/**
 * After contraction of two vertices other vertices need to know that
 * these two vertices has combined to one and only known by vertex no of first vertex
 * so the verices that has the edges with second vertex, they need to update there 
 * neighbourVerticesList and change the vertex no of second vertex with vertex no
 * of first vertex.
 */
	public void replaceVertexNoWithAnotherVertexNo(int initialVertexNo, int otherVertexNo){
		int index = -1;
		while(true){
			index = neighbourVerticesList.indexOf(initialVertexNo);
			if(index == -1) break;
			neighbourVerticesList.set(index, otherVertexNo);
		} 
	}
	
/* Method : getNeighbourVerticesList() */
/** this method returns the neighboursVerticesList of a Vertex v */
	public ArrayList<Integer> getNeighbourVerticesList(){
		return neighbourVerticesList;
	}

/* Method : nEdges() */
/** this method returns the no of edges from a Vertex v */
	public int nEdges(){
		return neighbourVerticesList.size();
	}
	
/* private instance variables */
	private int myVertexNo;
	private ArrayList<Integer> neighbourVerticesList = new ArrayList<Integer>();
	private Random rgen = new Random();
}