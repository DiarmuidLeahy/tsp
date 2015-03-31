package travellingSalesmanProblem;

//import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Stack;
import lab5.FileIO;
 
public class AltenateSolution extends TSP
{
    private int numberOfNodes;
    private Stack<Integer> stack;
 
    public AltenateSolution()
    {
        stack = new Stack<Integer>();
    }
 
    public void tsp(int adjacencyMatrix[][])
    {
        numberOfNodes = adjacencyMatrix[1].length -1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        System.out.print(1 + ".");
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes)
            {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)
                {
                    if (min > adjacencyMatrix[element][i])
                    {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag)
            {
                visited[dst] = 1;
                stack.push(dst);
                System.out.print(dst + ".");
                minFlag = false;
                continue;
            }
            stack.pop();
        }
    }
 
    public static void main(String args[])
    {
        int number_of_nodes;
        FileIO reader = new FileIO();
		String[] inputs = reader.load("C:/Users/Derri_000/Documents/College/Algorithms & data structures2(cs211)/alltowns.txt");
		Node [] towns = new Node [80];
		for(int i=0;i<80;i++)
		{
			towns[i] = new Node();
		}
		int x = 0,pos = 0;   
        for(String y : inputs)
        {
            switch (pos % 4)
            {
                case (0):towns[x].number = Integer.parseInt(y);pos++;break;
                case (1):towns[x].name = y;pos++;break;
                case (2):towns[x].longitude = Double.parseDouble(y);pos++;break;
                case (3):towns[x].latitude = Double.parseDouble(y);pos++;x++;break;
            }
        }
        try
        {
            number_of_nodes = towns.length;
            int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
            System.out.println("Enter the adjacency matrix");
            for (int i = 0; i < number_of_nodes; i++)
            {
                for (int j = 0; j < number_of_nodes; j++)
                {
                    //System.out.println((int)dist(towns[i].latitude,towns[i].longitude,towns[j].latitude,towns[j].longitude));
                	adjacency_matrix[i][j] = (int)dist(towns[i].latitude,towns[i].longitude,towns[j].latitude,towns[j].longitude);
                }
            }
            for (int i = 0; i < number_of_nodes; i++)
            {
                for (int j = 0; j <= number_of_nodes; j++)
                {
                    if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
                    {
                        adjacency_matrix[j][i] = 1;
                    }
                }
            }
            System.out.println("the citys are visited as follows");
            AltenateSolution tspNearestNeighbour = new AltenateSolution();
            tspNearestNeighbour.tsp(adjacency_matrix);
            
        } catch (InputMismatchException inputMismatch)
         {
             System.out.println("Wrong Input format");
         }
        
    }
}
