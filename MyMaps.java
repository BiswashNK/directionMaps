
/**
Date: 25 October 2023
Course: CSCI 2073
Description: This program processes an input text file containing directions for getting
from one place to another. It returns the direction to the destination and the direction for the return trip and their consecutive trip time.
On my honor, I have neither given nor received unauthorized help while
completing this assignment.
Name: Biswash Kunwar 
CWID: 30158447
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class reads a file for getting one place to another.It returns the
 * direction to the destination and the direction for the return trip * and
 * their consecutive trip time.
 */
public class MyMaps {
  /* private variable to store a queue of distance traveled */
  private Queue<Double> distanceQueue = new LinkedList<>();
  /* private variable to store a queue of direction traveled */
  private Queue<String> directionQueue = new LinkedList<>();
  /* private variable to store a queue of street traveled */
  private Queue<String> streetQueue = new LinkedList<>();
  /* private variable to store a queue of return direction */
  private Queue<String> returnDirectionQueue = new LinkedList<>();
  /* private variable to store a stack of distance traveled */
  private StackInt<Double> distanceStackInt = new LinkedStack<>();
  /* private variable to store a stack of direction traveled */
  private StackInt<String> directionStackInt = new LinkedStack<>();
  /* private variable to store a stack of street traveled */
  private StackInt<String> streetStackInt = new LinkedStack<>();

  /**
   * Constructs a MyMaps object by reading data from the specified file.
   *
   * @param filename The name of the file containing direction and distance data.
   */
  public MyMaps(String filename) {
    try {
      File inputFile = new File(filename);
      Scanner stdin = new Scanner(inputFile);
      while (stdin.hasNextLine()) {
        String line = stdin.nextLine();
        Scanner in = new Scanner(line);

        distanceQueue.add(Double.parseDouble(in.next().trim()));

        String direction = in.next().trim();
        if (direction.equals("NO")) {
          directionQueue.add("North");
        } else if (direction.equals("SO")) {
          directionQueue.add("South");
        } else if (direction.equals("EA")) {
          directionQueue.add("East");
        } else if (direction.equals("WE")) {
          directionQueue.add("West");
        }

        streetQueue.add(in.nextLine());
        in.close();
      }
      stdin.close();
    } catch (FileNotFoundException e) {
      System.out.println(filename);
    }

  }

  /**
   * Retrieves and returns the directions for the given route.
   *
   * @return A string containing the directions for the route.
   */

  public String getDirections() {
    StringBuilder result = new StringBuilder();
    Queue<String> tempDir = new LinkedList<>();
    Queue<String> tempSt = new LinkedList<>();
    Queue<Double> tempDis = new LinkedList<>();
    String prevDirection = "";
    while (!directionStackInt.empty()) {
      directionStackInt.pop();
      distanceStackInt.pop();
      streetStackInt.pop();
    }
    while (directionQueue.peek() != null) {
      String direction = directionQueue.remove();

      if (direction.equals(prevDirection)) {
        distanceQueue.remove();
        streetQueue.remove();
        direction = directionQueue.remove();
      }
      tempDir.add(direction);
      directionStackInt.push(direction);
      prevDirection = direction;
      String street = streetQueue.remove();
      tempSt.add(street);
      streetStackInt.push(street);
      Double distance = distanceQueue.remove();
      tempDis.add(distance);
      distanceStackInt.push(distance);

      result.append("Turn " + direction + " and travel " + distance + " mile(s) on" + street + "\n");

    }
    while (tempDir.peek() != null) {
      directionQueue.add(tempDir.remove());
      distanceQueue.add(tempDis.remove());
      streetQueue.add(tempSt.remove());
    }
    return result.toString();
  }

  /**
   * Retrieves and returns the directions for the return trip.
   *
   * @return A string containing the directions for the return trip.
   */

  public String returnTrip() {
    getDirections();

    StringBuilder result = new StringBuilder();
    StackInt<String> tempDir = new LinkedStack<>();
    StackInt<Double> tempDis = new LinkedStack<>();
    StackInt<String> tempSt = new LinkedStack<>();
    while (!directionStackInt.empty()) {
      String direction = directionStackInt.pop();
      tempDir.push(direction);
      if (direction.equals("North")) {
        direction = "South";
      } else if (direction.equals("South")) {
        direction = "North";
      } else if (direction.equals("East")) {
        direction = "West";
      } else if (direction.equals("West")) {
        direction = "East";
      }
      returnDirectionQueue.add(direction);
      String street = streetStackInt.pop();
      tempSt.push(street);
      Double distance = distanceStackInt.pop();
      tempDis.push(distance);
      result.append("Turn " + direction + " and travel " + distance + " mile(s) on" + street + "\n");

    }
    while (!tempDir.empty()) {
      directionStackInt.push(tempDir.pop());
      distanceStackInt.push(tempDis.pop());
      streetStackInt.push(tempSt.pop());
    }
    return result.toString();
  }

  /**
   * Calculates and returns the estimated travel time for the route.
   *
   * @return The estimated travel time in hours based on the provided data.
   */
  public double travelTime() {
    getDirections();
    Double result = 0.0;
    StackInt<Double> tempDis = new LinkedStack<>();
    StackInt<String> tempDir = new LinkedStack<>();
    String prevDir = "";
    while (!distanceStackInt.empty()) {
      Double dis = distanceStackInt.pop();
      result += dis * 1.5;
      tempDis.push(dis);

      String currDir = directionStackInt.pop();
      tempDir.push(currDir);
      if (currDir.equals("North") && prevDir.equals("West")) {
        result += 0.5;
      }
      if (currDir.equals("West") && prevDir.equals("South")) {
        result += 0.5;
      }
      if (currDir.equals("South") && prevDir.equals("East")) {
        result += 0.5;
      }
      if (currDir.equals("East") && prevDir.equals("North")) {
        result += 0.5;
      }
      prevDir = currDir;

    }
    while (!tempDir.empty()) {
      directionStackInt.push(tempDir.pop());
      distanceStackInt.push(tempDis.pop());
    }

    return result;
  }

  /**
   * Calculates and returns the estimated travel time for the return trip.
   *
   * @return The estimated travel time for the return trip in hours.
   */
  public double returnTripTime() {
    returnTrip();
    Double result = 0.0;
    Queue<Double> tempDis = new LinkedList<>();
    Queue<String> tempDir = new LinkedList<>();
    String prevDir = "";
    while (returnDirectionQueue.peek() != null && distanceQueue.peek() != null) {
      Double dis = distanceQueue.remove();
      result += dis * 1.5;
      tempDis.add(dis);

      String currDir = returnDirectionQueue.remove();
      tempDir.add(currDir);
      if (currDir.equals("North") && prevDir.equals("East")) {
        result += 0.5;
      }
      if (currDir.equals("West") && prevDir.equals("North")) {
        result += 0.5;
      }
      if (currDir.equals("South") && prevDir.equals("West")) {
        result += 0.5;
      }
      if (currDir.equals("East") && prevDir.equals("South")) {
        result += 0.5;
      }
      prevDir = currDir;

    }
    while (tempDir.peek() != null) {
      returnDirectionQueue.add(tempDir.remove());
      distanceQueue.add(tempDis.remove());
    }

    return result;
  }

}
