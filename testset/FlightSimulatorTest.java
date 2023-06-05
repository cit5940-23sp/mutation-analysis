import static org.junit.Assert.*;

import org.junit.Test;

public class FlightSimulatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testPlanesNull() {
        FlightSimulator.simulateFlights(null, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroSteps() {
        Airplane plane = new Airplane(5, 5, 2, 1);
        Airplane[] planes = new Airplane[1];
        planes[0] = plane;

        FlightSimulator.simulateFlights(planes, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeDistance() {
        Airplane plane = new Airplane(5, 5, 2, 1);
        Airplane[] planes = new Airplane[1];
        planes[0] = plane;

        FlightSimulator.simulateFlights(planes, 1, -100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullElementInPlanesArray() {
        Airplane[] planes = new Airplane[1];
        planes[0] = null;

        FlightSimulator.simulateFlights(planes, 10, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeVelocity() {
        Airplane plane = new Airplane(5, 5, 2, -100);
        Airplane[] planes = new Airplane[1];
        planes[0] = plane;

        FlightSimulator.simulateFlights(planes, 100, 100);
    }

    @Test
    public void testTwoPlanesEqualsSafeDistance() {
        Airplane plane1 = new Airplane(1, 1, 2, 2);
        Airplane plane2 = new Airplane(4, 5, 2, 2);
        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;

        assertFalse(FlightSimulator.simulateFlights(planes, 100, 5));
    }

    @Test
    public void testTwoPlanesLessThanSafeDistance() {
        Airplane plane1 = new Airplane(0, 0, 90, 10);
        Airplane plane2 = new Airplane(30, 40, 90, 10);
        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;

        assertFalse(FlightSimulator.simulateFlights(planes, 100, 100));
    }

    @Test
    public void testTwoPlanesComingTowardsEachOtherSafe() {
        Airplane plane1 = new Airplane(0, 0, 0, 10);
        Airplane plane2 = new Airplane(100, 0, 180, 10);
        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;

        assertTrue(FlightSimulator.simulateFlights(planes, 2, 50));
    }

    @Test
    public void testTwoPlanesComingTowardsEachOtherNotSafe() {
        Airplane plane1 = new Airplane(0, 0, 0, 10);
        Airplane plane2 = new Airplane(100, 0, 180, 10);
        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;

        assertFalse(FlightSimulator.simulateFlights(planes, 3, 50));
    }

    @Test
    public void testTwoPlanesGoingAwayFromEachOther() {
        Airplane plane1 = new Airplane(100, 0, 180, 10);
        Airplane plane2 = new Airplane(200, 0, 0, 10);
        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;

        assertTrue(FlightSimulator.simulateFlights(planes, 10, 50));
    }

    @Test
    public void testTwoPlanesCrashingIntoEachOtherEventually() {
        Airplane plane1 = new Airplane(0, 100, 0, 10);
        Airplane plane2 = new Airplane(100, 0, 90, 10);
        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;

        assertFalse(FlightSimulator.simulateFlights(planes, 10, 10));
    }

    @Test
    public void testTwoPlanesStopBeforeCrashingIntoEachOther() {
        Airplane plane1 = new Airplane(0, 100, 0, 10);
        Airplane plane2 = new Airplane(100, 0, 90, 10);
        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;

        assertTrue(FlightSimulator.simulateFlights(planes, 5, 10));
    }

    @Test
    public void testThreePlanesCrashingIntoOneAnother() {
        Airplane plane1 = new Airplane(0, 0, 0, 10);
        Airplane plane2 = new Airplane(200, 0, 180, 10);
        Airplane plane3 = new Airplane(100, 100, 270, 10);
        Airplane[] planes = new Airplane[3];
        planes[0] = plane1;
        planes[1] = plane2;
        planes[2] = plane3;

        assertFalse(FlightSimulator.simulateFlights(planes, 10, 1));
    }

    @Test
    public void testEmptyPlanesArray() {
        Airplane[] planes = new Airplane[0];

        assertTrue(FlightSimulator.simulateFlights(planes, 10, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroStepsXORNegativeSafeDistance() {
        Airplane plane = new Airplane(5, 5, 0, 1);
        Airplane[] planes = new Airplane[1];
        planes[0] = plane;

        FlightSimulator.simulateFlights(planes, 0, -1);
    }


    @Test
    public void testOnePlaneCatchingUpWithTheOther() {
        Airplane plane1 = new Airplane(0, 20, 90, 1);
        Airplane plane2 = new Airplane(0, 0, 90, 3);
        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;

        assertFalse(FlightSimulator.simulateFlights(planes, 10, 1));
    }

    @Test
    public void testYValueMultiplication() {
        Airplane plane1 = new Airplane(0, 9, 270, 2);
        Airplane plane2 = new Airplane(0, 0, 90, 2);
        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;

        assertFalse(FlightSimulator.simulateFlights(planes, 2, 2));
    }

    @Test
    public void testNegativeXValueOrigin() {

        Airplane plane1 = new Airplane(2.0, 0.0, 180, 1.0); // starting at (1,0), heading straight up
        Airplane plane2 = new Airplane(-2.0, 0.0, 0, 1.0); // starting at (-1,0), heading straight up

        Airplane[] planes = new Airplane[2];
        planes[0] = plane1;
        planes[1] = plane2;


        assertFalse(FlightSimulator.simulateFlights(planes, 2, 2.0));
    }








}
