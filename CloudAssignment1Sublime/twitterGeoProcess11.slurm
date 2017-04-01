#!/bin/bash
#SBATCH -p cloud
#SBATCH --time=01:00:00
#SBATCH --nodes=1
#SBATCH --ntasks-per-node=1
module load Java/1.8.0_71
module load OpenMPI/2.0.2-GCC-6.2.0
mpirun -np 8 java -cp ./JAR/*:. TwitterGeoProcessing
