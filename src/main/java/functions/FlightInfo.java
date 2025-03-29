package functions;

import entities.*;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlightInfo<E extends Airplane> extends RunwayFunctions<E> {
    private LocalTime timestamp;
    private String idFlight;

    public FlightInfo() {
        super();
    }
    public FlightInfo(ArrayList<String> command) {
        super(command);
        try {
            this.timestamp = LocalTime.parse(super.getCommand().get(0));
            this.idFlight = super.getCommand().get(2);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void flightInfoFunction(RunwayFunctions<E> runways, PrintStream fileOutFlightInfo) {
        E airplane = runways.findFlight(this.idFlight);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        fileOutFlightInfo.println(this.timestamp.format(formatter) + " | " + airplane);
    }
}
