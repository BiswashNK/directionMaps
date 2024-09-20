The MyMaps program reads a file containing direction data, stores this information in queues and stacks, and provides methods to retrieve the travel directions, compute the travel time, and generate directions for the return trip. Below is a breakdown of the key components and methods of the program:
Key Components:

    Queues:
        distanceQueue: Stores the distances traveled.
        directionQueue: Stores the directions (e.g., North, South).
        streetQueue: Stores the street names traveled.
        returnDirectionQueue: Stores the directions for the return trip.

    Stacks:
        distanceStackInt: Stores the distances (used for reversing the trip).
        directionStackInt: Stores the directions (used for reversing the trip).
        streetStackInt: Stores the street names (used for reversing the trip).

Key Methods:

    getDirections():
        Constructs the forward travel directions from the stored queues.
        Pushes data into the stacks for future use in return trip calculation.
        Ensures no unnecessary turns by checking if the previous direction is the same as the current.

    returnTrip():
        Uses the stacks to generate directions for the return trip.
        Reverses the directions (e.g., North becomes South) and reconstructs the trip in reverse order.

    travelTime():
        Calculates the total travel time for the trip based on distance and direction changes.
        Adds additional time (0.5 minutes) when the direction changes between specific pairs of directions (e.g., North to West).

    returnTripTime():
        Similar to travelTime(), but calculates the travel time for the return trip.

Example Output:

When run, the program will output:

    Directions: Step-by-step travel directions with distance and street names.
    Travel Time: The total time taken to reach the destination and the return trip time, considering both distance and directional changes.

Enhancements or Considerations:

    Input File Format: Ensure the file contains data in the correct format (e.g., distance, direction, and street name) to avoid parsing errors.
    Efficiency: The program pushes and pops items between queues and stacks, which may have some overhead but ensures correct handling of the return trip.
