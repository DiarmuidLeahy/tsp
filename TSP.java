package travellingSalesmanProblem;
import lab5.FileIO;

import java.util.ArrayList;
public class TSP
{
	public static final double R = 6372.8;// In kilometers
	public static void main(String args[])
	{
		FileIO reader = new FileIO();
		//String route="";
		ArrayList <Node> nodes = new ArrayList <Node>();
		String[] inputs = reader.load("C:/Users/Derri_000/Documents/College/Algorithms & data structures2(cs211)/alltowns.txt");
		Node [] towns = new Node [80];
		for(int i=0;i<towns.length;i++)
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
		for(int i=0;i<towns.length;i++)
		{
			nodes.add(towns[i]);
		}
		System.out.println("Distance between "+towns[2].name+" and "+towns[6].name+" is "+String.format("%.3f",dist(towns[2].latitude,towns[2].longitude,towns[6].latitude,towns[6].longitude))+"km");
		System.out.println("Closest town to "+towns[4].name+" is "+findClosest(towns[4],towns).name+" with a distance of "+String.format("%.3f", dist(towns[4].latitude,towns[4].longitude,findClosest(towns[4],towns).latitude,findClosest(towns[4],towns).longitude))+"km");
		System.out.println((int)Math.round(dist(towns[2].latitude,towns[2].longitude,towns[6].latitude,towns[6].longitude)));
		for(int i=0;i<towns.length;i++)
		{
			for(int j=0;j<towns.length;j++)
			{				
				System.out.println("distance from "+towns[i].name+ " to "+towns[j].name+" is "+(int)Math.round(dist(towns[i].latitude,towns[i].longitude,towns[j].latitude,towns[j].longitude)));			
			}
		}
	}
	/**
	 * This method calculates the distance between two towns when the longitudes & latitudes are entered
	 * @param lat1 latitude of first town
	 * @param lon1 longitude of first town
	 * @param lat2 latitude of second town
	 * @param lon2 longitude of second town
	 * @return double distance in kilometers
	 */
	public static double dist(double lat1, double lon1, double lat2, double lon2)
	{
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}
	/**
	 * This method returns the town which is closest to the current town entered
	 * @param current This is the town of which you want to find the closest other town to
	 * @param list This is an array of all possible towns
	 * @return Node representing the closest town
	 */
	public static Node findClosest(Node current,Node[] list)
	{
		Node closest=new Node();
		double length=Double.MAX_VALUE;	//arbitrary length great enough so that all distances will be smaller than it
		for(int i=0;i<list.length;i++)
		{
			//System.out.println(dist(current.latitude,current.longitude,list[i].latitude,list[i].longitude));
			if(dist(current.latitude,current.longitude,list[i].latitude,list[i].longitude)<length && dist(current.latitude,current.longitude,list[i].latitude,list[i].longitude)!=0.0)
			{
				length=dist(current.latitude,current.longitude,list[i].latitude,list[i].longitude);
				closest=list[i];
			}
		}
		return closest;
	}
}
