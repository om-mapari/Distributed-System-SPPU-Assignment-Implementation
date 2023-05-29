#include <mpich/mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

// size of array
#define n 10

int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

// Temporary array for slave process
int a2[1000];

int main(int argc, char *argv[])
{

    int pid, np,
        elements_per_process,
        n_elements_recieved;
    // np -> no. of processes
    // pid -> process id

    MPI_Status status;

    // Creation of parallel processes
    MPI_Init(&argc, &argv);

    // find out process ID,
    // and how many processes were started
    MPI_Comm_rank(MPI_COMM_WORLD, &pid);
    MPI_Comm_size(MPI_COMM_WORLD, &np);

    // master process
    if (pid == 0)
    {
        int index, i;
        elements_per_process = n / np;

        // check if more than 1 processes are run
        if (np > 1)
        {
            // distributes the portion of array
            // to child processes to calculate
            // their partial sums
            for (i = 1; i < np - 1; i++)
            {
                index = i * elements_per_process;

                MPI_Send(&elements_per_process, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
                MPI_Send(&a[index], elements_per_process, MPI_INT, i, 0, MPI_COMM_WORLD);
            }

            // last process adds remaining elements
            index = i * elements_per_process;
            int elements_left = n - index;

            MPI_Send(&elements_left, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&a[index], elements_left, MPI_INT, i, 0, MPI_COMM_WORLD);
        }

        // master process add its own sub array
        int sum = 0;
        for (i = 0; i < elements_per_process; i++)
            sum += a[i];

        // collects partial sums from other processes
        int tmp;
        for (i = 1; i < np; i++)
        {
            MPI_Recv(&tmp, 1, MPI_INT, MPI_ANY_SOURCE, 0, MPI_COMM_WORLD, &status);
            int sender = status.MPI_SOURCE;

            sum += tmp;
        }

        // prints the final sum of array
        printf("Sum of array is: %d\n", sum);
    }

    // slave processes
    else
    {
        MPI_Recv(&n_elements_recieved, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

        // stores the received array segment
        // in local array a2
        MPI_Recv(&a2, n_elements_recieved, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

        // calculates its partial sum
        int partial_sum = 0;
        for (int i = 0; i < n_elements_recieved; i++)
            partial_sum += a2[i];

        // sends the partial sum to the root process
        MPI_Send(&partial_sum, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
    }

    // cleans up all MPI state before exit of process
    MPI_Finalize();

    return 0;
}



// Compile the program using
//  mpicc name of the program
// Execute the program using
//  mpirun -np 4 ./a.out

// This code demonstrates an example of parallel computation using MPI (Message Passing Interface) to calculate the sum of an array. It involves a master process (rank 0) and multiple slave processes (ranks 1 to np-1).

// Here's a step-by-step explanation of the code:

// 1. The code starts by including the necessary header files (`mpi.h`, `stdio.h`, `stdlib.h`, `unistd.h`).

// 2. The array `a` is declared with values to be summed.

// 3. The main function initializes MPI and retrieves the process ID (`pid`) and the total number of processes (`np`).

// 4. The master process (rank 0) handles the distribution of work among the processes and calculates the final sum. The master process follows these steps:

//    - It calculates the number of elements to be processed per process (`elements_per_process`) by dividing the total number of elements (`n`) by the number of processes (`np`).
   
//    - If there is more than one process (`np > 1`), the master process sends portions of the array to the slave processes using MPI_Send. It sends the number of elements each slave process should process (`elements_per_process`) and the corresponding portion of the array (`a[index]`).
   
//    - The last process receives the remaining elements (`elements_left`) and the corresponding portion of the array.
   
//    - The master process calculates the sum of its own subarray (`sum`) by iterating over its elements.
   
//    - The master process then receives the partial sums calculated by the slave processes using MPI_Recv. It collects the partial sums from each slave process and adds them to the `sum`.
   
//    - Finally, the master process prints the final sum of the array.

// 5. The slave processes (ranks 1 to np-1) handle the computation of partial sums. Each slave process follows these steps:

//    - The slave process receives the number of elements it should process (`n_elements_recieved`) and the corresponding portion of the array (`a2`) using MPI_Recv.
   
//    - The slave process calculates its partial sum (`partial_sum`) by iterating over its portion of the array.
   
//    - The slave process sends its partial sum to the master process using MPI_Send.

// 6. The code cleans up MPI resources and exits.

// In summary, the code distributes the work of summing an array among multiple processes using MPI. The master process divides the array into smaller portions and assigns each portion to a slave process. The slave processes calculate their partial sums, and the master process collects and adds these partial sums to obtain the final sum of the array.