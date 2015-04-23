import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class TopoSort {

	public static void main(String[] args) throws FileNotFoundException {
		Read toRead = new Read(new File("input.txt"));
                toRead.read();
		Graph g = new Graph(toRead.getData());
		g.run();
	}
   
	public static class Graph {
		private List<Vertex> graph;
		private List<List<Integer>> data;
public void run()
{
	createVertices();
	addEdges();
	
	List<Integer> a =topo_sort(enqueueZeroIndegree());
	if (a!=null)
	{
		for (int number : a)
		{
			System.out.print(number+" ");
		}
		System.out.println();
	}
	
	else 
		System.out.println("Cycle Found");
}
		public  Queue<Vertex> enqueueZeroIndegree()
		   {
			   Queue<Vertex> q = new LinkedList<Vertex>();
			   for ( int i= 0 ; i < graph.size() ; i++)
			   { 
				   Vertex v = graph.get(i);
				   if ( v.indegree==0)
				   {
                                      
					   q.add(v);
				   }
			   }
			   return q;
		   }
		public List<Integer> topo_sort(Queue<Vertex> q)
		{
			List<Integer> a = new ArrayList<Integer>();
			int counter = 0;
                        
			while (!q.isEmpty())
			{
				Vertex v = q.remove();
                                
                                
				a.add(v.id);
				v.topoNo = ++counter;
				for (Vertex w : v.edgeList)
				{
					w.indegree--;
					if (w.indegree == 0)
					{
						q.add(w);
					}
				}
			}
			
			return a;
		}
		public Graph(List<List<Integer>> adjacentList) {
			graph = new ArrayList<>();
			data = adjacentList;
		}

		public List<Vertex> getGraph() {

			return graph;
		}

		public void createVertices() {
			for (int i = 0; i < data.size(); i++) {
				graph.add(new Vertex(i + 1));
			}
		}

		public void addEdges() {
			for (int i = 0; i < data.size(); i++) {
				for (int j = 1; j < data.get(i).size(); j++) {
					int vertexID = data.get(i).get(j);
					graph.get(i).addEdge(getVertex(vertexID));
				}
			}
		}

		private Vertex getVertex(int x) {
			return graph.get(x - 1);
		}

		public static class Vertex {

			private int id;
			private int topoNo;
			private int indegree;
			private List<Vertex> edgeList;

			private Vertex(int id) {
				this.id = id;
				indegree = 0;
				edgeList = new ArrayList<>();

			}
            private int getId()
            {
            	return this.id;
            }
			private void addEdge(Vertex to) {
				edgeList.add(to);
				to.indegree++;

			}
		}
	}

	public static class Read {
		private Scanner in;
		private List<List<Integer>> data;

		public Read(File fileName) throws FileNotFoundException {
			in = new Scanner(fileName);
			data = new ArrayList<List<Integer>>();
		}

		public List<List<Integer>> getData() {
			return data;
		}
                
		public void read() {
			try {
                           
				while (in.hasNextLine()) {
					List temp = new ArrayList<Integer>();
					String line = in.nextLine();
					String[] a = line.trim().split(" ");
					for (int i = 0; i < a.length; i++) {
						temp.add(Integer.parseInt(a[i]));
                                               
					}
					data.add(temp);

				}
			} catch (NumberFormatException e) {
				System.out.println("Input has to be number");
			}
		}
	}
}
