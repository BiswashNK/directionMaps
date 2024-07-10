import java.io.FileNotFoundException;

/**
 * This program can be used to test the MyMaps class methods.
 *
 * @author Dr.C.
 */
public class MapsTest {
   public static void main(String[] args) throws FileNotFoundException {
      MyMaps fallTrip = new MyMaps("directions0.txt");

      System.out.println("\nDirections from source to destination: \n" + fallTrip.getDirections());
      System.out.println("\nDirections from source to destination: \n" + fallTrip.getDirections());
      System.out.println("\nDirections from source to destination: \n" + fallTrip.getDirections());
      System.out.printf("Total time: %.2f minutes\n", fallTrip.travelTime()); // Expected: 16.75 minutes
      System.out.printf("Total time: %.2f minutes\n", fallTrip.travelTime());
      System.out.printf("Total time: %.2f minutes\n", fallTrip.travelTime());
      System.out.println("\nDirections from source to destination: \n" + fallTrip.getDirections());
      System.out.println("\nDirections from destination to source: \n" + fallTrip.returnTrip());
      System.out.printf("Total time: %.2f minutes", fallTrip.returnTripTime());
      // Expected: 16.25 minutes

      System.out.println("\nDirections from destination to source: \n" + fallTrip.returnTrip());

   }
}