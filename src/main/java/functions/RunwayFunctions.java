package functions;

import entities.*;

import java.util.*;

public class RunwayFunctions<E extends Airplane> {
    private ArrayList<Runway<E>> runways;
    private ArrayList<String> command;

    public RunwayFunctions() {
        this.runways = new ArrayList<>();
        this.command = new ArrayList<>();
    }
    public RunwayFunctions(ArrayList<String> command) {
        this();
        this.command = command;
    }

    public ArrayList<String> getCommand() {
        return this.command;
    }
    public void setCommand(ArrayList<String> command) {
        this.command = command;
    }
    public ArrayList<Runway<E>> getRunways() {
        return this.runways;
    }
    public void setRunways(ArrayList<Runway<E>> runways) {
        this.runways = runways;
    }

    public void addRunway(Runway<E> runway) {
        runways.add(runway);
    }
    // used chat to transform form iterative search in stream searches
    public Runway<E> findRunway(String idRunway) {
        return this.runways.stream().filter(runway -> runway.getIdRunway().equals(idRunway))
                .findFirst().orElse(null);
    }
    public E findFlight(String idFlight) {
        return this.runways.stream().flatMap(runway -> runway.getAirplanes().stream())
                .filter(airplane -> airplane.getIdFlight().equals(idFlight)).findFirst().orElse(null);
    }
}
