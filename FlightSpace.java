package Model;

import java.util.ArrayList;
import java.util.List;

public class FlightSpace {
    List<SeatCluster> seatClusters = new ArrayList<SeatCluster>();

    int availableWindowSeat = 0;
    int availableAileSeat = 0;
    int availableCenterSeat = 0;
    int numOfSeatClusters = 0;

    int lastPlacedWindowSeatCluster = -1;
    int lastPlacedWindowSeatRow = -1;
    int lastPlacedAileSeatCluster = -1;
    int lastPlacedAileSeatRow = -1;
    int lastPlacedCenterSeatCluster = -1;
    int lastPlacedCenterSeatRow = -1;
    
    FlightSpace(List<Integer[][]> seatClusters)
    {
        this.numOfSeatClusters = seatClusters.size();
        
        for (int i=0; i<numOfSeatClusters; i++)
        {
            int seatClusterAvailableWindowSeat = 0;
            int seatClusterAvailableAileSeat = 0;
            int seatClusterAvailableCenterSeat = 0;

            Integer[][] seatCluster = seatClusters.get(i);
            
            int rowSize = seatCluster.length;
            int columnSize = seatCluster[0].length;

            String[][] seatStatus = new String[rowSize][columnSize];
            
            for (int x=0; x<rowSize; x++)
            {
                for (int y=0; y<columnSize; y++)
                {
                    if (x == 0)
                    {
                        if (i == 0)
                        {
                            seatStatus[x][y] = "W";
                            seatClusterAvailableWindowSeat++;
                        }
                        else
                        {
                            seatStatus[x][y] = "A";
                            seatClusterAvailableAileSeat++;
                        }
                    }
                    else if (x == columnSize-1)
                    {
                        if (i == numOfSeatClusters-1)
                        {
                            seatStatus[x][y] = "W";
                            seatClusterAvailableWindowSeat++;
                        }
                        else
                        {
                            seatStatus[x][y] = "A";
                            seatClusterAvailableAileSeat++;
                        }
                    }
                    else
                    {
                        seatStatus[x][y] = "C";
                        seatClusterAvailableCenterSeat++;
                    }
                }
            }
            SeatCluster seatClusterObject = new SeatCluster(seatStatus, seatClusterAvailableWindowSeat, seatClusterAvailableAileSeat, seatClusterAvailableCenterSeat);
            this.seatClusters.add(seatClusterObject);
            
            availableWindowSeat = availableWindowSeat + seatClusterAvailableWindowSeat;
            availableAileSeat = availableAileSeat + seatClusterAvailableAileSeat;
            availableCenterSeat = availableCenterSeat + seatClusterAvailableCenterSeat;
        }
    }

    public void addPeopleInFlight(int peopleInQueue)
    {
        for (int i=0; i<peopleInQueue; i++)
        {
            if (availableAileSeat != 0)
            {
                placeInAileSeat(lastPlacedAileSeatCluster, lastPlacedAileSeatRow);
                availableAileSeat --;
            }
            else if (availableWindowSeat != 0)
            {
                placeInWindowSeat(lastPlacedWindowSeatCluster, lastPlacedWindowSeatRow);
                availableWindowSeat --;
            }
            else if (availableCenterSeat != 0)
            {
                placeInCenterSeat(lastPlacedCenterSeatCluster, lastPlacedCenterSeatRow);
                availableCenterSeat --;
            }
            else
            {
                //TODO Already full error message
            }
        }
    }

    private void placeInAileSeat(int lastPlacedAileSeatCluster, int lastPlacedAileSeatRow) 
    {
        if (lastPlacedAileSeatCluster == -1 || lastPlacedAileSeatRow == -1)
        {
            this.lastPlacedAileSeatRow = seatClusters.get(0).addAilePassanger(0);
            this.lastPlacedAileSeatCluster = 0;
        }
        else
        {
            int tempCheckRow = seatClusters.get(lastPlacedAileSeatCluster).addAilePassanger(lastPlacedAileSeatRow);
            int tempLastCluster = lastPlacedAileSeatCluster;
            while (tempCheckRow == -1)
            {
                if (tempLastCluster == numOfSeatClusters-1)
                {
                    tempLastCluster = 0;
                    tempCheckRow = seatClusters.get(tempLastCluster).addAilePassanger(lastPlacedAileSeatRow + 1);
                    if (tempCheckRow != -1)
                    {
                        this.lastPlacedAileSeatCluster = 0;
                    }
                }
                else
                {
                    tempCheckRow = seatClusters.get(tempLastCluster++).addAilePassanger(lastPlacedAileSeatRow);
                    if (tempCheckRow != -1)
                    {
                        this.lastPlacedAileSeatCluster = tempLastCluster;
                    }
                }
            }
            this.lastPlacedAileSeatRow = tempCheckRow;
        }
    }

    private void placeInWindowSeat(int lastPlacedWindowSeatCluster, int lastPlacedWindowSeatRow) 
    {
        if (lastPlacedWindowSeatCluster == -1 || lastPlacedWindowSeatRow == -1)
        {
            this.lastPlacedWindowSeatRow = seatClusters.get(0).addAilePassanger(0);
            this.lastPlacedWindowSeatCluster = 0;
        }
        else
        {
            int tempCheckRow = seatClusters.get(lastPlacedWindowSeatCluster).addAilePassanger(lastPlacedWindowSeatRow);
            int tempLastCluster = lastPlacedWindowSeatCluster;
            while (tempCheckRow == -1)
            {
                if (tempLastCluster == numOfSeatClusters-1)
                {
                    tempLastCluster = 0;
                    tempCheckRow = seatClusters.get(tempLastCluster).addAilePassanger(lastPlacedWindowSeatRow + 1);
                    if (tempCheckRow != -1)
                    {
                        this.lastPlacedWindowSeatCluster = 0;
                    }
                }
                else
                {
                    tempCheckRow = seatClusters.get(tempLastCluster++).addAilePassanger(lastPlacedWindowSeatRow);
                    if (tempCheckRow != -1)
                    {
                        this.lastPlacedWindowSeatCluster = tempLastCluster;
                    }
                }
            }
            this.lastPlacedWindowSeatRow = tempCheckRow;
        }        
    }

    private void placeInCenterSeat(int lastPlacedCenterSeatCluster, int lastPlacedCenterSeatRow) 
    {
        if (lastPlacedCenterSeatCluster == -1 || lastPlacedCenterSeatRow == -1)
        {
            this.lastPlacedCenterSeatRow = seatClusters.get(0).addAilePassanger(0);
            this.lastPlacedCenterSeatCluster = 0;
        }
        else
        {
            int tempCheckRow = seatClusters.get(lastPlacedCenterSeatCluster).addAilePassanger(lastPlacedCenterSeatRow);
            int tempLastCluster = lastPlacedCenterSeatCluster;
            while (tempCheckRow == -1)
            {
                if (tempLastCluster == numOfSeatClusters-1)
                {
                    tempLastCluster = 0;
                    tempCheckRow = seatClusters.get(tempLastCluster).addAilePassanger(lastPlacedCenterSeatRow + 1);
                    if (tempCheckRow != -1)
                    {
                        this.lastPlacedCenterSeatCluster = 0;
                    }
                }
                else
                {
                    tempCheckRow = seatClusters.get(tempLastCluster++).addAilePassanger(lastPlacedWindowSeatRow);
                    if (tempCheckRow != -1)
                    {
                        this.lastPlacedCenterSeatCluster = tempLastCluster;
                    }
                }
            }
            this.lastPlacedCenterSeatRow = tempCheckRow;
        }
    }
}
