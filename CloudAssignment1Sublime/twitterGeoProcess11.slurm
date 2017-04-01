#!/bin/bash
#SBATCH -p cloud
#SBATCH --time=01:00:00
#SBATCH --nodes=1
#SBATCH --ntasks-per-node=1
module load Java/1.8.0_71
module load OpenMPI

javac -cp ./gson.2.6.2.jar:./mpi.jar:. TwitterGeoProcessing.java
mpirun -np 1 java -cp ./gson.2.6.2.jar:./mpi.jar:. TwitterGeoProcessing
