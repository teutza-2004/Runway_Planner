package functions;

import comparators.*;
import entities.*;

import java.time.LocalTime;
import java.util.*;

public class AddRunwayInUse <E extends Airplane> extends RunwayFunctions<E> {
    private LocalTime timestamp;
    private String idRunway;
    private String usage;
    private String acceptedAirplane;

    public AddRunwayInUse() {
        super();
    }
    public AddRunwayInUse(ArrayList<String> command) {
        super(command);
        try {
            this.timestamp = LocalTime.parse(super.getCommand().get(0));
            this.idRunway = super.getCommand().get(2);
            this.usage = super.getCommand().get(3);
            if (!this.usage.equals("landing") && !this.usage.equals("takeoff")) {
                throw new Exception("Invalid runway usage");
            }
            this.acceptedAirplane = super.getCommand().get(4);
            if (!this.acceptedAirplane.equals("narrow body") && !this.acceptedAirplane.equals("wide body")) {
                throw new Exception("Invalid accepted airplane");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addRunwayInUseFunction(RunwayFunctions<E> runways) throws Exception {
        if (this.usage.equals("landing")) {
            runways.addRunway(new Runway<E>(this.idRunway, this.usage, this.acceptedAirplane, new LandingComparator()));
        } else if (this.usage.equals("takeoff")) {
            runways.addRunway(new Runway<E>(this.idRunway, this.usage, this.acceptedAirplane, new TakeoffComparator()));
        } else {
            throw new Exception("Invalid usage type: " + usage);
        }
    }
}
