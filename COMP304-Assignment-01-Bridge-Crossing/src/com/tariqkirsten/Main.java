package com.tariqkirsten;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Main {
	
	public static void main(String[] args)
	{
		System.out.println("Dangerous Crossing Problem");
		System.out.print("Enter number of people: ");
		Scanner scan = new Scanner(System.in);
		int numOfPeople = scan.nextInt();
		int[] inputArray = new int[numOfPeople];
		for(int i =0;i<numOfPeople;i++)
		{
			System.out.print("Enter Person "+(i+1)+"'s travel time: ");
			inputArray[i] = scan.nextInt();
		
		}
     	System.out.println();
		List<Integer> inputList = new ArrayList<Integer>();
		for(int i : inputArray)
		{
			inputList.add(Integer.valueOf(i));
		}
		int runner = Collections.min(inputList);
		inputList.remove(Integer.valueOf(runner));
		
		
		
		
		
		
		LinkedList<Node> nodeList = new LinkedList<Node>();
		LinkedList<Edge> edgesList = new LinkedList<Edge>();
		 Node initalNode = new Node();
		 initalNode.label = "Initial Node";
		 initalNode.type = "Inital";
		 nodeList.add(initalNode);
		Combination c = new Combination(nodeList);
		int array[] = new int[inputList.size()];
		 for(int i=0;i<array.length;i++) {
	         array[i] = inputList.get(i).intValue();
		 }
		
		int r = array.length-1;
		int n = array.length;
		int f = 1;
		while(r-f !=0)
		{
			c.generateCombinations(array, n, r-f); 
			f++;
		}
		c.generateCombinations(array, n, r);
		c.generateCombinations(array, n, r+1); //gives us final node combination
		nodeList.peekLast().type = "Goal";
		

		for(int i = 0;i<nodeList.size();i++)
		{
		   Node currentNode = nodeList.get(i);
		   int numOfPeopleLeft = inputArray.length - currentNode.rightSideOfBridge.size();
		   if(currentNode.type=="Goal")
		   {
			   currentNode.setHValue(numOfPeopleLeft-1);
		   }
		   else
		   {
			   currentNode.setHValue(numOfPeopleLeft);
		   }
		   
		   for(int j = 0;j<nodeList.size();j++)
			{
			   Node selectedNode = nodeList.get(j);
			   if(currentNode.label.equals(selectedNode.label))
			   {
				   continue;
			   }
			   else
			   {
				   if(!Collections.disjoint(nodeList.get(j).rightSideOfBridge, currentNode.rightSideOfBridge) && nodeList.get(j).rightSideOfBridge.size()==currentNode.rightSideOfBridge.size()+1)
				   {
					   LinkedList<Integer> union = new LinkedList<Integer>();
					   union.addAll(currentNode.rightSideOfBridge);
					   union.addAll(selectedNode.rightSideOfBridge);
					   
					   LinkedList<Integer> intersection = new LinkedList<Integer>();
					   intersection.addAll(currentNode.rightSideOfBridge);
					   intersection.retainAll(selectedNode.rightSideOfBridge);
					   
					   union.removeAll(intersection);
					 
					   int cost = union.getFirst() + runner;
					   
					   if(selectedNode.type=="Goal") 
					   {
						   cost -=runner;
					   }
					  
					   Edge e = new Edge(currentNode.label+selectedNode.label, currentNode, selectedNode, cost);
					  selectedNode.parentNode.add(currentNode);
					   currentNode.childrenNodes.add(selectedNode);
				   }
				  
				  
				  
			   }
			   
			}
		   
		}
		
		for(int i = 0;i <nodeList.size();i++)
		{
			Node currentNode = nodeList.get(i);
			for(int j = 0; j<nodeList.size();j++)
			{
				Node selectedNode = nodeList.get(j);
				 if(currentNode.label=="A"&&selectedNode.label=="F") 
				   {
					   int cost = Collections.max(selectedNode.rightSideOfBridge) + Collections.min(currentNode.rightSideOfBridge);
					   Edge e = new Edge(currentNode.label+selectedNode.label, currentNode, selectedNode, cost);
						  selectedNode.parentNode.add(currentNode);
						   currentNode.childrenNodes.add(selectedNode);
						   
				   }
			}
		}
		
		for(int i = 0;i<nodeList.size();i++)
		{
		   Node currentNode = nodeList.get(i);
		   
		if(currentNode.parentNode.size()==0 && currentNode.label!="Initial Node")
		{
			   LinkedList<Integer> union = new LinkedList<Integer>();
			   union.addAll(initalNode.rightSideOfBridge);
			   union.addAll(currentNode.rightSideOfBridge);
			   
			   LinkedList<Integer> intersection = new LinkedList<Integer>();
			   intersection.addAll(initalNode.rightSideOfBridge);
			   intersection.retainAll(currentNode.rightSideOfBridge);
			   
			   union.removeAll(intersection);
			   
			   int cost = union.getFirst() + runner;
			   
			   
			   Edge e = new Edge(initalNode.label+currentNode.label, initalNode, currentNode, cost);
			   edgesList.add(e);
			currentNode.parentNode.add(initalNode);
			initalNode.childrenNodes.add(currentNode);
		}
	} 
		while(true)
		{
			System.out.println("Choose an algorithm to apply to set:\n1.BFS\n2.DFS\n3.A* search");
			int algoChoice = scan.nextInt();
			switch(algoChoice)
			{
			case 1:
				BreadthFirstSearch(initalNode);
				clearNodeStates(nodeList);
				break;
			case 2:
				DFS(initalNode);
				clearNodeStates(nodeList);
				break;
			case 3:
				AStarSearch(initalNode);
				clearNodeStates(nodeList);
				break;
				default:
					System.out.println("Invalid option!");
					break;
			}
		}
		
		
	}
	
	public static void BreadthFirstSearch(Node initialNode)
	{
		System.out.print("BFS: ");
		if (initialNode.childrenNodes.isEmpty());
		boolean found =false;

		Node root = initialNode;
		if (root==null)
		{
			
		}

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		root.visited = true;
		Node n =null;
		while (!queue.isEmpty() )
		{
			root = queue.peek();
			for (Node each : root.childrenNodes)
			{

				if (!each.visited&&!found)
				{
					each.visited = true;
					
					queue.add(each);
					if(each.type == "Goal") {
						found = true;
						
					}
					
				}
			}
			Node current= queue.remove();
			n = current;
			Node temp = queue.peek();
	            
			System.out.print(current.label+""+current.rightSideOfBridge+"--->");
		}
		System.out.println(n.label+" is the Goal Node");
	
	}
	

	
	
	// prints all not yet visited vertices reachable from s 
    static void DFS(Node s,String wantedNode) 
    { 
        // Initially mark all vertices as not visited 
  
        // Create a stack for DFS 
        Stack<Node> stack = new Stack<>(); 
          
        // Push the current source node 
        stack.push(s); 
          
        while(stack.empty() == false) 
        { 
            // Pop a vertex from stack and print it 
            s = stack.peek(); 
            stack.pop(); 
              
            // Stack may contain same vertex twice. So 
            // we need to print the popped item only 
            // if it is not visited. 
            if(s.visited == false) 
            { 
                System.out.print(s.label + s.rightSideOfBridge+ "-->"); 
                s.visited = true;
                if(s.label == wantedNode )
                {
                	break;
                }
            } 
              
            // Get all adjacent vertices of the popped vertex s 
            // If a adjacent has not been visited, then puah it 
            // to the stack. 
            Iterator<Node> itr = s.childrenNodes.iterator(); 
              
            while (itr.hasNext())  
            { 
                Node v = itr.next(); 
                if(!v.visited) 
                    stack.push(v); 
            } 
            
              
        } 
        System.out.println("Goal has been found");
    } 
    
    static void DFS(Node s) 
    { 
        // Initially mark all vertices as not visited 
  
        // Create a stack for DFS 
        Stack<Node> stack = new Stack<>(); 
          
        // Push the current source node 
        stack.push(s); 
          
        while(stack.empty() == false && s.type!="Goal") 
        { 
            // Pop a vertex from stack and print it 
            s = stack.peek(); 
            stack.pop(); 
              
            // Stack may contain same vertex twice. So 
            // we need to print the popped item only 
            // if it is not visited. 
            if(s.visited == false) 
            { 
            	
            	
            	
                s.visited = true;
            } 
              
            // Get all adjacent vertices of the popped vertex s 
            // If a adjacent has not been visited, then puah it 
            // to the stack. 
            Iterator<Node> itr = s.childrenNodes.iterator(); 
              
            while (itr.hasNext())  
            { 
                Node v = itr.next(); 
                if(!v.visited) 
                    stack.push(v); 
            } 
         
            System.out.print(s.label + s.rightSideOfBridge+"--->"); 
           
              
        } 
        System.out.println("Goal has been found");
    } 
	
    static void AStarSearch(Node start)
    {
    	System.out.print("A* Search: \nOptimal Trace:");
    	PriorityQueue<Node> queue = new PriorityQueue<Node>();    	
    	HashSet<Node> searched = new HashSet<Node>();
    	PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>();
    	int totalCostSoFar = 0;
    	start.gValue = 0;
    	start.calcFValue();
    	queue.add(start);
    	totalCostSoFar += start.gValue;
    	Node n = queue.peek();
    	while(n.type!="Goal")
    	{
    		n = queue.poll();
    		if(!searched.contains(n))
    		{
    		totalCostSoFar = n.gValue;
    		searched.add(n);
    		System.out.print(n.label+"("+n.fValue+")"+"--->");
    		for (Edge edge : n.outgoingEdges) {
    			edgeQueue.add(edge);
    		}
        	
        	while(!edgeQueue.isEmpty())
        	{
        		Edge e = edgeQueue.poll();
            	Node currentNode = e.getDestination();
                currentNode.gValue = e.getWeight()+totalCostSoFar;
            	if(currentNode.label =="F"){
            		currentNode.gValue -=2;
            	}
            	currentNode.calcFValue();
            	queue.add(currentNode);
            	
        	}
    		}
    	}
    
    	
    	
    	System.out.println(n.label + " is the  "+n.type +" node with a total time of " + totalCostSoFar );
    }
	
	public static void clearNodeStates(LinkedList<Node> nl)
	{
		for (Node each : nl)
		{
			each.visited = false;
			}
		
	}
	
}
