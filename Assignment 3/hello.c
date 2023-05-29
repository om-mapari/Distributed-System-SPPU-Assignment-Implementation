#include <stdio.h>
#include "mpi.h"
int main(int argc, char *argv[])
{
    int rank, size, len;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    printf("Hello, world, I am %d of %d\n", rank, size);
    MPI_Finalize();
    return 0;
}

// nllabc2d22@nllabc2d-22:~/opt/openmpi/bin$ mpicc hello.c

// Execute the program using 2 cores
// nllabc2d22@nllabc2d-22:~/opt/openmpi/bin$ mpirun -np 2 ./a.out
// Hello, world, I am 0 of 2
// Hello, world, I am 1 of 2


// Execute the program using 4 cores
// nllabc2d22@nllabc2d-22:~/opt/openmpi/bin$ mpirun -np 4 ./a.out
// Hello, world, I am 0 of 4
// Hello, world, I am 3 of 4
// Hello, world, I am 1 of 4
// Hello, world, I am 2 of 4




// Installation of OPENMPI
// 1. Download openmpi-4.1.4.tar.bz2 from http://www.open-mpi.org in a folder say LP5.
// 2. Goto the terminal (Command prompt)
// 3. update using
//  sudo apt-get update
//  sudo apt install gcc {if not already installed}
// 4. Goto the directory which contains the downloaded file
// 5. Extract the files using
//  tar -jxf openmpi-4.1.4.tar.bz2
// 6. The directory openmpi-4.1.4 is created
// 7. Configure, compile and install by executing the following commands
//  ./configure --prefix=$HOME/opt/openmpi
//  make all
//  make install
// 8. Now openmpi folder is created in ‘opt‘ folder of Home directory.
// 9. Now the folder LP5 can be deleted (optional)
// 10. Update the PATH and LD_LIBRARY_PATH environment variable using
// echo "export PATH=\$PATH:\$HOME/opt/openmpi/bin" >> $HOME/.bashrc
// echo "export LD_LIBRARY_PATH=\$LD_LIBRARY_PATH:\$HOME/opt/openmpi/lib">>$HOME/.bashrc
// 11. Compile the program using
//  mpicc name of the program
// 12. Execute the program using
//  mpirun -np N ./a.out



// To understand the codes involving MPI (Message Passing Interface) for parallel computation, several concepts are important:

// Parallel Computing: It refers to the simultaneous execution of multiple tasks or computations in a coordinated manner. It aims to solve complex problems faster by dividing them into smaller subproblems that can be solved concurrently.

// MPI (Message Passing Interface): It is a standard communication protocol and library for parallel computing. MPI allows multiple processes to communicate and exchange data by sending messages to each other.

// Process: In parallel computing, a process refers to an instance of a program or a task running concurrently with other processes. Each process has its own memory space and can execute instructions independently.

// Rank: In MPI, each process is assigned a unique identifier called a rank. Ranks are used to distinguish different processes and determine their roles in collective operations.

// Communication Modes in MPI: MPI provides various communication modes for exchanging messages between processes, including point-to-point communication and collective communication.

// Point-to-Point Communication: It involves communication between two specific processes. MPI_Send and MPI_Recv are the primary functions for point-to-point communication. The sender process uses MPI_Send to send a message, while the receiver process uses MPI_Recv to receive the message.

// Collective Communication: It involves communication among a group of processes. Collective operations are designed to synchronize the actions of multiple processes and perform operations collectively, such as broadcasting, scattering, gathering, and reducing data across processes. Examples of collective operations in MPI include MPI_Bcast, MPI_Scatter, MPI_Gather, and MPI_Reduce.

// Master-Slave Programming Model: The codes you provided follow a master-slave programming model. In this model, there is a master process that coordinates the work and distributes tasks to multiple slave processes. The slaves perform their assigned tasks and communicate the results back to the master process.

// Initialization and Finalization: MPI_Init and MPI_Finalize are functions used to initialize and finalize the MPI environment. MPI_Init initializes the MPI execution environment, and MPI_Finalize cleans up and terminates MPI.

// Process Management: Functions like MPI_Comm_rank and MPI_Comm_size are used to retrieve the rank of a process and the total number of processes in the MPI communicator.

// Sending and Receiving Data: MPI_Send and MPI_Recv functions are used for point-to-point communication between processes. They allow processes to send and receive data by specifying the data buffer, size, data type, source process, destination process, and communication tag.

// Parallel Computation Strategy: The codes you provided demonstrate a simple parallel computation strategy where the master process distributes portions of an array to the slave processes. Each process calculates its local sum, and the master process collects the local sums to calculate the final sum.

// Understanding these concepts will provide you with a foundation to comprehend and develop parallel programs using MPI.




// Explain me concept required for this assignment 

// Develop a distributed system, to find sum of N elements in an array by distributing N/n elements to n number of processors MPI or OpenMP. Demonstrate by displaying the intermediate sums calculated at different processors. 

// To develop a distributed system for finding the sum of N elements in an array using MPI or OpenMP, you need to understand the following concepts:

// 1. Parallel Computing: Understanding parallel computing and its benefits is crucial. It involves breaking down a task into smaller subtasks that can be executed simultaneously, thereby improving performance and efficiency.

// 2. Distributed Systems: A distributed system consists of multiple interconnected processors or machines working together to solve a common task. Each processor or machine operates independently but communicates and collaborates with others to achieve a collective goal.

// 3. MPI (Message Passing Interface): MPI is a standard communication protocol and library for parallel computing. It provides functions for point-to-point and collective communication among processes in a distributed system. You need to understand MPI concepts such as process ranks, communication modes, and collective operations.

// 4. OpenMP (Open Multi-Processing): OpenMP is an API (Application Programming Interface) for shared-memory parallel programming. It enables parallelism within a single machine using multiple threads. Understanding concepts like thread creation, synchronization, and parallel loops will be helpful if you choose OpenMP for the assignment.

// 5. Load Balancing: Efficient load balancing is important in a distributed system to distribute the workload evenly across processors. You need to consider load balancing strategies to ensure that the array elements are evenly distributed among the processors for computation.

// 6. Array Partitioning: You need to understand how to divide the input array into smaller subsets and distribute them to different processors or threads. Dividing the array evenly among processors or threads is essential to achieve a balanced workload distribution.

// 7. Intermediate Sum Calculation: Each processor or thread will calculate the sum of its assigned subset of array elements. You should keep track of these intermediate sums at each processor or thread to monitor the progress and verify correctness.

// 8. Synchronization: In a distributed system, synchronization mechanisms are necessary to coordinate the activities of different processors or threads. You may need to use synchronization primitives such as barriers or collective operations to ensure proper synchronization and avoid race conditions.

// 9. Result Aggregation: After calculating the intermediate sums, you need to aggregate the results from all processors or threads to obtain the final sum of the entire array. Understanding collective operations like reduction, gather, or allreduce will be helpful in this step.

// 10. Error Handling and Debugging: In a distributed system, it is important to handle errors, diagnose issues, and debug the code. Familiarize yourself with techniques for error handling and debugging in the chosen parallel programming model (MPI or OpenMP).

// By grasping these concepts, you will be well-prepared to design and implement a distributed system for finding the sum of array elements. It will involve partitioning the array, distributing the workload, calculating intermediate sums, aggregating results, and handling synchronization and error scenarios effectively.