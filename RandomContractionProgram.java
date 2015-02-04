 /*
 * File : RandomContractionProgram.java
 * Date : 24 June 2014
 * __________________________________________________________________________________________________
 * This program is an implementation of Random contraction Algorithm (Karger's Algorithm).
 * When finished it will print no of cuts on console
 *___________________________________________________________________________________________________
 * Run this program several times to get the minimum cuts as contraction occurs randomly.
 * __________________________________________________________________________________________________
 * @author : Shankul Jain
 */


import java.io.*;

class RandomContractionProgram{
	
/* Database file */
/** this file contains the data of a undirected graph with 200 vertices in Adjecency list representation*/	
	private static final String filename = "kargerMinCut.txt";
	

/* Method : main(String [] args) */
/**
 * This is the main method of the program that basically does nothing.
 * it is responsible for reading in the data.
 */
	public static void main(String [] args) throws IOException{
		db = new VertexDataBase(filename);
		while(db.verticesInDataBase()>2){
			contractRandomEdge();
		}
		System.out.println(minCuts());
	}	
	
/* Method : contractRandomEdge() */
/**
 * This method selects a vertex v randomly at uniform from the database
 * and another vertex w that has edge with v randomly
 * and then makes a call to method that contracts these 2 vertices into 1
 * and make the necessary changes to database.
 */
	private static void contractRandomEdge(){
		Vertex v = db.getRandomVertex();
		Vertex w = db.getRandomEdgeVertex(v);
		db.contractAndUpdateDataBase(v,w);
	}
	
/* Method : minCuts */
/**
 * when all vertices has been contracted and only two vertices are left
 * this method returns the no of edges between these vertices.
 */
	private static int minCuts(){
		Vertex v = db.getRandomVertex();
		return db.nEdges(v);
	}
	
	/* private class variable */
	private static VertexDataBase db;
}