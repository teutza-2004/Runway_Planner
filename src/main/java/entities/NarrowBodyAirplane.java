package entities;

import java.time.LocalTime;

public class NarrowBodyAirplane extends Airplane {
    public NarrowBodyAirplane() {
        super();
    }
    public NarrowBodyAirplane(String model, String idFlight, String departure, String destination, LocalTime wantedTime, LocalTime actualTime, EnumStatus status, Boolean urgent) {
        super(model, idFlight, departure, destination, wantedTime, actualTime, status, urgent);
    }

    @Override
    public String toString() {
        return "Narrow Body - " + super.toString();
    }
}
