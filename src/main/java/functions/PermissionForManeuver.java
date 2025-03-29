package functions;

import entities.*;
import exceptions.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PermissionForManeuver<E extends Airplane> extends RunwayFunctions<E> {
    private LocalTime timestamp;
    private String idRunway;

    public PermissionForManeuver() {
        super();
    }
    public PermissionForManeuver(ArrayList<String> command) {
        super(command);
        try {
            this.timestamp = LocalTime.parse(super.getCommand().get(0));
            this.idRunway = super.getCommand().get(2);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public LocalTime permissionForManeuverFunction(RunwayFunctions<E> runways, LocalTime maneuverTimestamp) throws UnavailableRunwayException{
        if (this.timestamp.isBefore(maneuverTimestamp) || this.timestamp.equals(maneuverTimestamp)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            throw new UnavailableRunwayException(this.timestamp.format(formatter) + " | The chosen runway for maneuver is currently occupied");
        }
        Runway<E> runway = runways.findRunway(this.idRunway);
        PriorityQueue<E> aux = new PriorityQueue<>(runway.getAirplanes());
        E airplane = null;
        do {
            airplane = aux.poll();
        } while (!aux.isEmpty() && !airplane.getStatus().equals(E.EnumStatus.WAITING_FOR_LANDING) && !airplane.getStatus().equals(E.EnumStatus.WAITING_FOR_TAKEOFF));
        runway.getAirplanes().remove(airplane);
        if (airplane.getStatus().equals(E.EnumStatus.WAITING_FOR_LANDING)) {
            airplane.setStatus(E.EnumStatus.LANDED);
        }
        if (airplane.getStatus().equals(E.EnumStatus.WAITING_FOR_TAKEOFF)) {
            airplane.setStatus(E.EnumStatus.DEPARTED);
        }
        airplane.setActualTime(this.timestamp);
        runway.addAirplane(airplane); // added back for flight info
        runway.setStatus("OCCUPIED");
        if (runway.getUsage().equals("takeoff")){
            return this.timestamp.plusMinutes(5);
        }
        else if (runway.getUsage().equals("landing")){
            return this.timestamp.plusMinutes(10);
        }
        return maneuverTimestamp;
    }
}
