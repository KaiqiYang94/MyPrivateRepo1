import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import mpi.*;


public class MPICommands {

	public static int mainProcessRank = 0;
	private static int tag = 10;
	protected static final int GRIDDATASIZE = 5;
	protected static final int GRIDRESULTSIZE = 2;

	protected static final char GRIDDATACMD = 'G';
	protected static final char SINGLETWITTERCMD = 'S';
	protected static final char RESULTCMD = 'R';
	protected static final char FINISHECMD = 'F';
	
	public static char ReceiveCommandType (ArrayList<GeoGrid> geoGrids) throws Exception {
		char[] commandType = new char[1];
		MPI.COMM_WORLD.recv(commandType, 1, MPI.CHAR, mainProcessRank, tag);
		//System.out.println(indentation() + "The received commandType is " + commandType[0] + ". ");

		return commandType[0];

	}


	public static void BcastGridData (ArrayList<GeoGrid> geoGrids) throws Exception {
		char[] commandType = new char[1];
		commandType[0] = GRIDDATACMD;
		int[] gridSize = new int[1];
		gridSize[0] = geoGrids.size();
		double[] gridData = new double[GRIDDATASIZE];

		int size = MPI.COMM_WORLD.getSize() ;

		for (int i = 0 ; i < size ; i++) {
			// command type
			MPI.COMM_WORLD.send(commandType, 1, MPI.CHAR, i, tag);
			// Grid count
			MPI.COMM_WORLD.send(gridSize, 1, MPI.INT, i, tag);
			//System.out.println(indentation() + "The sent data is <" + geoGrids.size() + ">. ");


			// the grid data
			for (GeoGrid geoGrid : geoGrids) {
				geoGrid.toArray(gridData);
				
				MPI.COMM_WORLD.send(gridData, GRIDDATASIZE, MPI.DOUBLE, i, tag);
			}
		}
	}

	public static void ResvGridData (ArrayList<GeoGrid> geoGrids) throws Exception {
		int[] numberOfGird = new int[1];
		// get the dataSize
		MPI.COMM_WORLD.recv(numberOfGird, 1, MPI.INT, mainProcessRank, tag);

		//System.out.println(indentation() + "The received data is <" + numberOfGird[0] + ">. ");

		double[] gridData = new double[GRIDDATASIZE];

		for (int i = 0 ; i < numberOfGird[0] ; i++) {

			MPI.COMM_WORLD.recv(gridData, GRIDDATASIZE, MPI.DOUBLE, mainProcessRank, tag);

			//System.out.println(indentation() + "The received data is <" + gridData[0] + " " + gridData[1] + ">. ");
			geoGrids.add(new GeoGrid(gridData));

		}
	}

	public static void BcastFinished () throws Exception {
		char[] commandType = new char[1];
		commandType[0] = FINISHECMD;

		int size = MPI.COMM_WORLD.getSize() ;

		for (int i = mainProcessRank + 1 ; i < size ; i++) {
			// command type
			MPI.COMM_WORLD.send(commandType, 1, MPI.CHAR, i, tag);
		}
	}

	public static void SendSingleTwitter (String msg, int targetRank) throws Exception {
		char[] commandType = new char[1];
		commandType[0] = SINGLETWITTERCMD;
		int[] gridSize = new int[1];
		gridSize[0] = msg.length();

		// command type
		MPI.COMM_WORLD.send(commandType, 1, MPI.CHAR, targetRank, tag);
		// string length
		MPI.COMM_WORLD.send(gridSize, 1, MPI.INT, targetRank, tag);

		//System.out.println(indentation() + "The sent data is <" + gridSize[0] + "> to rank " + targetRank +" ");
		MPI.COMM_WORLD.send(msg.toCharArray(), msg.length(), MPI.CHAR, targetRank, tag);
	}

	public static String ResvSingleTwitter () throws Exception {
		int[] strLength = new int[1];
		// get the dataSize
		MPI.COMM_WORLD.recv(strLength, 1, MPI.INT, mainProcessRank, tag);

		//System.out.println(indentation() + "The received string Length is <" + strLength[0] + ">. ");

		char[] msg = new char[strLength[0]];

		MPI.COMM_WORLD.recv(msg, strLength[0], MPI.CHAR, mainProcessRank, tag);

		//System.out.println(indentation() + "The received string is <" + new String(msg) + ">. ");

		return new String(msg);

	}


	public static void SendResults (ArrayList<GeoGrid> geoGrids) throws Exception {
		char[] commandType = new char[1];
		commandType[0] = RESULTCMD;
		int[] gridSize = new int[1];
		gridSize[0] = geoGrids.size();
		double[] gridData = new double[GRIDRESULTSIZE];
	
		// command type
		MPI.COMM_WORLD.send(commandType, 1, MPI.CHAR, mainProcessRank, tag);
		// Grid count
		MPI.COMM_WORLD.send(gridSize, 1, MPI.INT, mainProcessRank, tag);
		//System.out.println(indentation() + "The sent data is <" + geoGrids.size() + ">. ");

		// the grid data
		for (GeoGrid geoGrid : geoGrids) {
			geoGrid.toResult(gridData);
			
			MPI.COMM_WORLD.send(gridData, GRIDRESULTSIZE, MPI.DOUBLE, mainProcessRank, tag);
		}
		
	}

	public static void ResvResults (ArrayList<GeoGrid> geoGrids) throws Exception {

		int size = MPI.COMM_WORLD.getSize() ;
		for (int i = mainProcessRank + 1 ; i < size ; i++) {
			char[] commandType = new char[1];
			int[] numberOfGird = new int[1];
			// command type
			MPI.COMM_WORLD.recv(commandType, 1, MPI.CHAR, i, tag);

			// get the dataSize
			MPI.COMM_WORLD.recv(numberOfGird, 1, MPI.INT, i, tag);

			//System.out.println(indentation() + "The received data is <" + numberOfGird[0] + ">. ");

			double[] gridData = new double[GRIDRESULTSIZE];

			for (int j = 0 ; j < numberOfGird[0] ; j++) {

				MPI.COMM_WORLD.recv(gridData, GRIDRESULTSIZE, MPI.DOUBLE, i, tag);

				//System.out.println(indentation() + "The received data is <" + gridData[0] + " " + gridData[1] + ">. ");
				//geoGrids.add(new GeoGrid(gridData));
				for(GeoGrid geoGrid: geoGrids)
				{
					if (geoGrid.internalID == gridData[0]) {
						geoGrid.Counter += gridData[1];
						break;
					}
				}

			}
		}



	}

	
	public static String indentation() {
		try {
			int myrank = MPI.COMM_WORLD.getRank() ;
			return "Rank " + myrank + ": ";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
