Which class should tell how many parking spots required for vehicle ?
Option 1: Vehicle Knows Its Own Requirements (Most Common)
Option 2: Strategy Pattern (Most Flexible)

How to find Consecutive Spots in a Parking Lot? Let's say Truck need 4 parking spots.

Should I implement singleton object of ParkingLot class ?
No, do NOT implement the Singleton Pattern for the ParkingLot class.
Here is why avoiding the Singleton pattern is generally preferred in modern software design and interviews:
1. Testability (The Biggest Issue)
   Singleton: Global state persists between tests. If Test A parks a car, Test B might fail because the lot is full. You have to manually add "reset" methods just for testing, which is messy.
   Instance: You can just do new ParkingLot(...) in every @BeforeEach method of your unit tests. Each test gets a fresh, clean parking lot.
2. Scalability & Real World
   Singleton: Assumes there will only ever be one parking lot in the entire JVM.
   Reality: What if your company manages 50 parking lots in the city? With a Singleton, you can't model "Downtown Lot" and "Airport Lot" simultaneously in the same application. You want to be able to create instances of ParkingLot.
3. Dependency Injection (DI)
   Modern frameworks (Spring, Guice) manage "Singletons" for you. You declare ParkingLot as a normal class, and the framework ensures only one instance is created and shared (Application Scope).
   Hardcoding private static ParkingLot instance inside the class is an outdated, rigid way to enforce this.

### Let's say I have multiple entry and exits, then do I need singleton ?
Even with multiple Entry/Exit points, you still do NOT need to implement the Singleton pattern inside the ParkingLot class.
However, you DO need a shared instance of the ParkingLot object to be passed to all those entry/exit points.
#### The Conceptual Model
Think of ParkingLot not as the "Gate" but as the "Database" or "State Manager" of the system.
* EntryGate 1: Needs access to the central ParkingLot to check availability.
* EntryGate 2: Needs access to the same ParkingLot instance.
* ExitGate 1: Needs access to the same ParkingLot instance to process payments/free spots.
```
public class EntryGate {
private final ParkingLot parkingLot; // Shared reference

    public EntryGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket processEntry(Vehicle v) {
        return parkingLot.parkVehicle(v);
    }
}

public class Client {
public static void main(String[] args) {

// 1. Create the ONE shared state (The "Singleton" in concept, but not code pattern)
ParkingLot sharedLot = new ParkingLot(...);

        // 2. Inject it into multiple gates
        EntryGate gateNorth = new EntryGate(sharedLot);
        EntryGate gateSouth = new EntryGate(sharedLot);
        ExitGate gateEast = new ExitGate(sharedLot);

        // 3. Operate
        gateNorth.processEntry(new Car("A")); // Uses sharedLot
        gateSouth.processEntry(new Car("B")); // Uses same sharedLot
    }
}
```