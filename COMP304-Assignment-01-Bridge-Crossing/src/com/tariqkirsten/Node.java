package com.tariqkirsten;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Node implements Comparable<Node>{

	public LinkedList<Integer> rightSideOfBridge; //holds the value of what is on the right hand side
	public LinkedList<Integer> leftSideOfBridge;
	public LinkedList<Node> childrenNodes;
	public LinkedList<Node> parentNode;
	public LinkedList<Edge> incomingEdges,outgoingEdges;
	public String label,type;
	public int hValue;
	public int gValue;
	public int fValue;
	public boolean visited;
	public Node()
	{
		rightSideOfBridge = new LinkedList<Integer>();
		leftSideOfBridge = new LinkedList<Integer>();
		parentNode = new LinkedList<Node>();
		childrenNodes = new LinkedList<Node>();
		incomingEdges = new LinkedList<Edge>();
		outgoingEdges = new LinkedList<Edge>();
		
		
	}
	public void setHValue(int h)
	{
		hValue = h;
	}
	
	public void setGValue(int g)
	{
		 gValue = g;
	}
	
	public void calcFValue()
	{
		fValue = hValue + gValue;
	}
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		if(fValue<o.fValue)
		{
			return -1;
		}
		if(fValue == o.fValue)
		{
			return 0;
		}
		else {
			return 1;
		}
		
	}
	
	public boolean Equals(Object e)
	{
		Node x = (Node) e;
		return label.compareTo(x.label) == 0;
		
	}

	
	

}
