#!/bin/bash
#SBATCH -p cloud
#SBATCH --time=00:50:00
#SBATCH --ntasks=1
#SBATCH --cpus-per-task=8

module load Java/1.8.0_71
module load mpj/0.44

javac -cp .:$MPJ_HOME/lib/mpj.jar ImprovedGEDDoubleChar.java
mpjrun.sh -np 1 -cp .:$MPJ_HOME/lib/mpj.jar ImprovedGEDDoubleChar
