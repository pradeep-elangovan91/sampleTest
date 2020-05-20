package Model;

class SeatCluster {
    String[][] seatStatus;

    int availableWindowSeat;
    int availableAileSeat;
    int availableCenterSeat;  

    SeatCluster(String[][] seatStatus, int seatClusterAvailableWindowSeat, int seatClusterAvailableAileSeat, int seatClusterAvailableCenterSeat) 
    {
        this.seatStatus = seatStatus;
        this.availableAileSeat = seatClusterAvailableAileSeat;
        this.availableCenterSeat = seatClusterAvailableCenterSeat;
        this.availableWindowSeat = seatClusterAvailableWindowSeat;
    }

    public int addAilePassanger(int row) 
    {
        return availableAileSeat == 0 ? -1 : replaceWith(row, "A");
    }

    public int addWindowPassanger(int row) 
    {
        return availableWindowSeat == 0 ? -1 : replaceWith(row, "W");
    }

    public int addCenterPassanger(int row) 
    {
        return availableCenterSeat == 0 ? -1 : replaceWith(row, "C");
    }

    int replaceWith(int row, String replaceString)
    {
        if (row < seatStatus.length)
        {
            for (int i=0; i<seatStatus[row].length; i++)
            {
                if (replaceString.equals(seatStatus[row][i]))
                {
                    switch(replaceString)
                    {
                        case "W":
                            availableWindowSeat --;
                            break;
                        case "A":
                            availableAileSeat --;
                            break;
                        case "C":
                            availableCenterSeat --;
                    }
                    seatStatus[row][i] = "O";
                    return row;
                }
            }
        }        
        return -1;
    }
}
