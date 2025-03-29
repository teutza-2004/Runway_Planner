package functions;

import entities.*;
import exceptions.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AllocatePlane<E extends Airplane> extends RunwayFunctions<E> {
    private LocalTime timestamp;
    private String planeType;
    private String model;
    private String idFlight;
    private String departure;
    private String destination;
    private LocalTime wantedTime;
    private String idRunway;
    private E.EnumStatus status;
    private String usage;
    private Boolean urgent;

    public AllocatePlane() {
        super();
    }
    public AllocatePlane(ArrayList<String> command) {
        super(command);
        try {
            this.timestamp = LocalTime.parse(super.getCommand().get(0));
            this.planeType = super.getCommand().get(2);
            if (!this.planeType.equals("wide body") && !this.planeType.equals("narrow body")) {
                throw new Exception("Invalid airplane model");
            }
            this.model = super.getCommand().get(3);
            this.idFlight = super.getCommand().get(4);
            this.departure = super.getCommand().get(5);
            if (this.departure.equals("Bucharest")) {
                this.usage = "takeoff";
                this.status = E.EnumStatus.WAITING_FOR_TAKEOFF;
            }
            this.destination = super.getCommand().get(6);
            if (this.destination.equals("Bucharest")) {
                this.usage = "landing";
                this.status = E.EnumStatus.WAITING_FOR_LANDING;
            }
            this.wantedTime = LocalTime.parse(super.getCommand().get(7));
            this.idRunway = super.getCommand().get(8);
            this.urgent = false;
            if (super.getCommand().get(9).equals("urgent")) {
                this.urgent = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public E createAirplane() {
        if (this.planeType.equals("wide body")) {
            return (E) new WideBodyAirplane(this.model, this.idFlight, this.departure, this.destination, this.wantedTime, null, this.status, this.urgent);
        } else if (this.planeType.equals("narrow body")) {
            return (E) new NarrowBodyAirplane(this.model, this.idFlight, this.departure, this.destination, this.wantedTime, null, this.status, this.urgent);
        }
        return null;
    }

    public void allocatePlaneFunction(RunwayFunctions<E> runways) throws IncorrectRunwayException {
        E airplane = createAirplane();
        Runway<E> runway = runways.findRunway(this.idRunway);
        if (!runway.getUsage().equals(this.usage)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            throw new IncorrectRunwayException(this.timestamp.format(formatter) + " | The chosen runway for allocating the plane is incorrect");
        }
        runway.addAirplane(airplane);
    }
}
