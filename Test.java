package Model;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String args[])
    {
        List<Integer[][]> seatClusters = new ArrayList<>();
        seatClusters.add(new Integer[3][3]);
        seatClusters.add(new Integer[4][5]);
        seatClusters.add(new Integer[6][3]);
        
        FlightSpace flightSpace = new FlightSpace(seatClusters);
        flightSpace.addPeopleInFlight(24);
    }
}
