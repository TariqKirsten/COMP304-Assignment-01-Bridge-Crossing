package com.tariqkirsten;

import java.util.LinkedList;


public class Combination { 

	private LinkedList<Node> nodeList;
	private LinkedList<Integer> tempRightSide = new LinkedList<Integer>();
	private LinkedList<String> alpha = new LinkedList<String>();
	
	
	public Combination(LinkedList<Node> nodeList)
	{
		this.nodeList = nodeList;
		alpha.add("A");
		alpha.add("B");
		alpha.add("C");
		alpha.add("D");
		alpha.add("E");
		alpha.add("F");
		alpha.add("G");
		alpha.add("H");
		alpha.add("I");
		alpha.add("J");
		alpha.add("K");
		alpha.add("L");
		alpha.add("M");
		alpha.add("N");
		alpha.add("O");
		alpha.add("P");
		alpha.add("Q");
		alpha.add("R");
		alpha.add("S");
		
		
		
	}
	 void combinationUtil(int arr[], int data[], int start, 
								int end, int index, int r) 
	{ 
		
		if (index == r) 
		{ 
			for (int j=0; j<r; j++) 
			{
				tempRightSide.add(data[j]);
			}
			Node newNode = new Node();
			newNode.label = alpha.remove();
			newNode.rightSideOfBridge.addAll(tempRightSide);
			nodeList.add(newNode);
			tempRightSide.clear();
			return; 
		} 

		for (int i=start; i<=end && end-i+1 >= r-index; i++) 
		{ 
			data[index] = arr[i]; 
			combinationUtil(arr, data, i+1, end, index+1, r); 
		} 
	} 

	
	 void generateCombinations(int arr[], int n, int r) 
	{ 
		
		int data[]=new int[r]; 
		combinationUtil(arr, data, 0, n-1, 0, r); 
	} 



} 

