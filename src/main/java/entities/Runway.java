package entities;

import java.io.*;
import java.util.*;

public class Runway<E extends Airplane> {
    private String idRunway;
    private String usage;
    private String acceptedAirplane;
    private PriorityQueue<E> airplanes;
    private String status;

    public Runway() {}
    public Runway(String idRunway, String usage, String acceptedAirplane, Comparator<E> comparator) {
        this.idRunway = idRunway;
        this.usage = usage;
        this.acceptedAirplane = acceptedAirplane;
        this.airplanes = new PriorityQueue<E>(comparator);
        this.status = "FREE";
    }

    public String getIdRunway() {
        return this.idRunway;
    }
    public void setIdRunway(String idRunway) {
        this.idRunway = idRunway;
    }
    public PriorityQueue<E> getAirplanes() {
        return this.airplanes;
    }
    public void setAirplanes(PriorityQueue<E> airplanes) {
        this.airplanes = airplanes;
    }
    public String getUsage() {
        return this.usage;
    }
    public void setUsage(String usage) {
        this.usage = usage;
    }
    public String getAcceptedAirplane() {
        return this.acceptedAirplane;
    }
    public void setAcceptedAirplane(String acceptedAirplane) {
        this.acceptedAirplane = acceptedAirplane;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void addAirplane(E airplane) {
        airplanes.add(airplane);
    }
    public String toString() {
        String ret = this.idRunway + " - " + this.status + "\n";
        PriorityQueue<E> aux = new PriorityQueue<>(this.airplanes);
        while (!aux.isEmpty()) {
            E airplane = aux.poll();
            if (airplane.getStatus().equals(Airplane.EnumStatus.WAITING_FOR_TAKEOFF) ||
                    airplane.getStatus().equals(Airplane.EnumStatus.WAITING_FOR_LANDING)) {
                ret += airplane + "\n";
            }
        }
        return ret;
    }
}
