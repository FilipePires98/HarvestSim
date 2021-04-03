# Harvest Simulation
A Multi-Threaded Harvest Simulator
## Description

The goal of this project is to provide a system focused on an architecture where concurrency (processes and threads) are a key aspect.
The chosen use case is an agriculture harvest simulation, where multiple farmers (threads) work together to manage the harvest and storage of corn cobs (shared resources).

The programming tools used are: Java for the concurrent application; Java Swing for the UI.
There are two main entities: the Control Center (CC) and the Farm Infrastructure (FI).
The CC is responsible for supervising the harvest.
The FI is the infrastructure for the agricultural harvest.
The CC and the FI are implemented as two different processes and the communication between them is through sockets.

The Control Center UI allows the user to follow the simulation in real-time:
![UserInterface1](https://github.com/FilipePires98/HarvestSim/blob/master/docs/img/UserInterface_CC_2.png)

## Repository Structure

/docs - contains project report and diagrams

/src - contains the source code of the simulator, written in Java

## Instructions to Build and Run

1. Have installed Java SE8.
2. Have installed NetBeans or other IDE (only tested with Netbeans).
3. Open the project folder 'PA1_P1G07' on your IDE.
4. Run the Main class and enjoy the Harvest Simulation.

## Authors

The authors of this repository are Filipe Pires and João Alegria, and the project was developed for the Software Architecture Course of the Master's degree in Informatics Engineering of the University of Aveiro.

For further information, please read our [report](https://github.com/FilipePires98/HarvestSim/blob/master/docs/report.pdf) or contact us at filipesnetopires@ua.pt or joao.p@ua.pt.


