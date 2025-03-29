package entities;

import java.time.LocalTime;

public class WideBodyAirplane extends Airplane {
    public WideBodyAirplane() {
        super();
    }
    public WideBodyAirplane(String model, String idFlight, String departure, String destination, LocalTime wantedTime, LocalTime actualTime, EnumStatus status, Boolean urgent) {
        super(model, idFlight, departure, destination, wantedTime, actualTime, status, urgent);
    }

    @Override
    public String toString() {
        return "Wide Body - " + super.toString();
    }
}
