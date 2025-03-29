package entities;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Airplane {
    private String model;
    private String idFlight;
    private String departure;
    private String destination;
    private LocalTime wantedTime;
    private LocalTime actualTime;
    public enum EnumStatus{
        WAITING_FOR_TAKEOFF,
        DEPARTED,
        WAITING_FOR_LANDING,
        LANDED};
    private EnumStatus status;
    private Boolean urgent;

    public Airplane() {}
    public Airplane(String model, String idFlight, String departure, String destination, LocalTime wantedTime, LocalTime actualTime, EnumStatus status, Boolean urgent) {
        this.model = model;
        this.idFlight = idFlight;
        this.departure = departure;
        this.destination = destination;
        this.wantedTime = wantedTime;
        this.actualTime = actualTime;
        this.status = status;
        this.urgent = urgent;
    }

    public String getModel() {
        return this.model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getIdFlight() {
        return this.idFlight;
    }
    public void setIdFlight(String idFlight) {
        this.idFlight = idFlight;
    }
    public String getDeparture() {
        return this.departure;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    public String getDestination() {
        return this.destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public LocalTime getWantedTime() {
        return this.wantedTime;
    }
    public void setWantedTime(LocalTime wantedTime) {
        this.wantedTime = wantedTime;
    }
    public LocalTime getActualTime() {
        return this.actualTime;
    }
    public void setActualTime(LocalTime actualTime) {
        this.actualTime = actualTime;
    }
    public EnumStatus getStatus() {
        return this.status;
    }
    public void setStatus(EnumStatus status) {
        this.status = status;
    }
    public Boolean getUrgent() {
        return this.urgent;
    }
    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String ret = this.model + " - " + this.idFlight + " - " + this.departure + " - " + this.destination +
                " - " + this.status + " - " + this.wantedTime.format(formatter);
        if (this.actualTime != null) {
            ret += " - " + this.actualTime.format(formatter);
        }
        return  ret;
    }
}
