/*
 * Copyright (c) 2011      Cisco Systems, Inc.  All rights reserved.
 *
 * Simple ring test program
 */

import mpi.* ;

class Ring {
	static public void main(String[] args) throws MPIException {


		MPI.Init(args) ;

		int source;  // Rank of sender
		int dest;    // Rank of receiver
		int tag = 50;  // Tag for messages
		int next;
		int prev;
		double message[] = new double [2];

		int myrank = MPI.COMM_WORLD.getRank() ;
		int size = MPI.COMM_WORLD.getSize() ;

		/* Calculate the rank of the next process in the ring.  Use the
		   modulus operator so that the last process "wraps around" to
		   rank zero. */

		next = (myrank + 1) % size;
		prev = (myrank + size - 1) % size;

		/* If we are the "master" process (i.e., MPI_COMM_WORLD rank 0),
		   put the number of times to go around the ring in the
		   message. */

		if (0 == myrank) {
			message[0] = 10;
			message[1] = 10.0001;

			System.out.println("Process 0 sending <" + message[0] + "," + message[1] + "> to rank " + next + " (" + size + " processes in ring)");
			MPI.COMM_WORLD.send(message, 2, MPI.DOUBLE, next, tag);
		}

		/* Pass the message around the ring.  The exit mechanism works as
		   follows: the message (a positive integer) is passed around the
		   ring.  Each time it passes rank 0, it is decremented.  When
		   each processes receives a message containing a 0 value, it
		   passes the message on to the next process and then quits.  By
		   passing the 0 message first, every process gets the 0 message
		   and can quit normally. */

		while (true) {
			MPI.COMM_WORLD.recv(message, 2, MPI.DOUBLE, prev, tag);

			System.out.println("Process " + myrank + " receives value: <" + message[0] + "," + message[1] + ">");

			if (0 == myrank) {
				--message[0];
				System.out.println("Process 0 decremented value: <" + message[0] + "," + message[1] + ">");
			}

			MPI.COMM_WORLD.send(message, 2, MPI.DOUBLE, next, tag);
			if (0 == message[0]) {
				System.out.println("Process " + myrank + " exiting");
				break;
			}
		}

		/* The last process does one extra send to process 0, which needs
		   to be received before the program can exit */

		if (0 == myrank) {
			MPI.COMM_WORLD.recv(message, 2, MPI.DOUBLE, prev, tag);
		}

		MPI.Finalize();
	}
}
