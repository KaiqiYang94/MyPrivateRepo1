#!/bin/bash
#SBATCH -p cloud
#SBATCH --time=01:00:00
#SBATCH --ntasks=1 
#SBATCH --cpus-per-task=1

module load Java/1.8.0_71
module load mpj/0.44

javac -cp .:$MPJ_HOME/lib/mpj.jar:./gson.2.6.2.jar TwitterGeoProcessing.java
mpjrun.sh -np 1 -cp .:$MPJ_HOME/lib/mpj.jar:./gson.2.6.2.jar TwitterGeoProcessing
