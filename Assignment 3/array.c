#include <stdio.h>
#include "mpi.h"

int main(int argc, char *argv[])
{
    int rank, size;
    int num[20]; // N=20, n=4
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    for (int i = 0; i < 20; i++)
        num[i] = i + 1;
    if (rank == 0)
    {
        int s[4];
        printf("Distribution at rank %d \n", rank);
        for (int i = 1; i < 4; i++)
            MPI_Send(&num[i * 5], 5, MPI_INT, i, 1, MPI_COMM_WORLD); // N/n i.e. 20/4=5
        int sum = 0, local_sum = 0;
        for (int i = 0; i < 5; i++)
        {
            local_sum = local_sum + num[i];
        }
        for (int i = 1; i < 4; i++)
        {
            MPI_Recv(&s[i], 1, MPI_INT, i, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        }
        printf("local sum at rank %d is %d\n", rank, local_sum);

        sum = local_sum;
        for (int i = 1; i < 4; i++)
            sum = sum + s[i];
        printf("final sum = %d\n\n", sum);
    }
    else
    {
        int k[5];
        MPI_Recv(k, 5, MPI_INT, 0, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        int local_sum = 0;
        for (int i = 0; i < 5; i++)
        {
            local_sum = local_sum + k[i];
        }
        printf("local sum at rank %d is %d\n", rank, local_sum);
        MPI_Send(&local_sum, 1, MPI_INT, 0, 1, MPI_COMM_WORLD);
    }
    MPI_Finalize();
    return 0;
}


// Compile the program using
//  mpicc name of the program
// Execute the program using
//  mpirun -np 4 ./a.out



// #include <stdio.h>
// #include "mpi.h"

// int main(int argc, char *argv[])
// {
//     int rank, size;
//     int num[5]; // N=20, n=4
//     MPI_Init(&argc, &argv);
//     MPI_Comm_rank(MPI_COMM_WORLD, &rank);
//     MPI_Comm_size(MPI_COMM_WORLD, &size);

//     if (rank == 0)
//     {
//         int s[size];
//         printf("Distribution at rank %d \n", rank);
//         for (int i = 1; i < size; i++)
//             MPI_Send(&num[(i - 1) * 5], 5, MPI_INT, i, 1, MPI_COMM_WORLD); // N/n i.e. 20/4=5

//         int sum = 0, local_sum = 0;
//         for (int i = 0; i < 5; i++)
//         {
//             local_sum = local_sum + num[i];
//         }

//         for (int i = 1; i < size; i++)
//         {
//             MPI_Recv(&s[i], 1, MPI_INT, i, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
//         }

//         printf("local sum at rank %d is %d\n", rank, local_sum);

//         sum = local_sum;
//         for (int i = 1; i < size; i++)
//             sum = sum + s[i];

//         printf("final sum = %d\n\n", sum);
//     }
//     else
//     {
//         int k[5];
//         MPI_Recv(k, 5, MPI_INT, 0, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

//         int local_sum = 0;
//         for (int i = 0; i < 5; i++)
//         {
//             local_sum = local_sum + k[i];
//         }

//         printf("local sum at rank %d is %d\n", rank, local_sum);

//         MPI_Send(&local_sum, 1, MPI_INT, 0, 1, MPI_COMM_WORLD);
//     }

//     MPI_Finalize();
//     return 0;
// }



// This code demonstrates an example of parallel computation using MPI (Message Passing Interface) to distribute a portion of an array among multiple processes and calculate the local and final sums. 

// Let's go through the code step by step:

// 1. The code includes the necessary header files (`stdio.h` and `mpi.h`).

// 2. The main function initializes MPI and retrieves the process rank (`rank`) and the total number of processes (`size`).

// 3. An array `num` of size 20 is declared and initialized with values from 1 to 20.

// 4. The code checks if the process rank is 0 (the master process).

// 5. For the master process (rank 0):

//    - An array `s` of size 4 is declared to store the local sums received from the other processes.
   
//    - The code enters a loop to distribute portions of the array to the other processes. It uses `MPI_Send` to send a subset of `num` starting from index `i * 5` with a size of 5 to each process with rank `i`. This ensures each process receives a non-overlapping subset of the array.
   
//    - The master process calculates its own local sum by summing the first 5 elements of `num`.
   
//    - The master process then enters another loop to receive the local sums calculated by the other processes. It uses `MPI_Recv` to receive the local sums (`s[i]`) from each process with rank `i` using the tag `1`.
   
//    - The code prints the master process's local sum and calculates the final sum by adding the local sums received from the other processes.
   
//    - Finally, the code prints the final sum.
   
// 6. For the other processes (slave processes) with ranks greater than 0:

//    - An array `k` of size 5 is declared to store the subset of the array received from the master process.
   
//    - The slave process uses `MPI_Recv` to receive the subset of the array (`k`) sent by the master process using the tag `1`.
   
//    - The slave process calculates its local sum by summing the elements of `k`.
   
//    - The slave process uses `MPI_Send` to send its local sum (`local_sum`) back to the master process using the tag `1`.
   
//    - Finally, the slave process prints its local sum.
   
// 7. The code concludes by calling `MPI_Finalize()` to clean up MPI resources and returns 0.

// In summary, the code distributes a portion of an array among multiple processes using MPI. The master process distributes subsets of the array to the slave processes, and each process calculates its local sum. The local sums are then sent back to the master process, which calculates the final sum by adding all the local sums received from the slave processes. The local and final sums are printed accordingly.