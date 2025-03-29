package org;

import entities.*;
import exceptions.*;
import functions.*;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class Main {
    private LocalTime maneuverTimestamp;

    public void readCommands(String arg, RunwayFunctions<Airplane> runways) {
        String inFilePath = "src/main/resources/" + arg + "/input.in";
        try {
            Scanner scanner = new Scanner(new File(inFilePath));
            String line = null;
            do {
                line = scanner.nextLine();
                this.parseCommand(arg, line, runways);
            } while (!line.contains("exit"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void parseCommand(String arg, String command, RunwayFunctions<Airplane> runways) {
        ArrayList<String> components = new ArrayList<String>();
        for (String component : command.split(" - ")) {
            components.add(component);
        }
        String cmd = components.get(1);

        try {
            String outFilePathFlightInfo = "src/main/resources/" + arg + "/flight_info.out";
            FileOutputStream outFileFlightInfo = null;
            PrintStream streamFlightInfo = null;

            switch (cmd) {
                case "add_runway_in_use": {
                    AddRunwayInUse addRunwayInUse = new AddRunwayInUse(components);
                    addRunwayInUse.addRunwayInUseFunction(runways);
                    break;
                }
                case "allocate_plane": {
                    AllocatePlane allocatePlane = new AllocatePlane(components);
                    allocatePlane.allocatePlaneFunction(runways);
                    break;
                }
                case "permission_for_maneuver": {
                    PermissionForManeuver permissionForManeuver = new PermissionForManeuver(components);
                    this.maneuverTimestamp = permissionForManeuver.permissionForManeuverFunction(runways, this.maneuverTimestamp);
                    break;
                }
                case "runway_info": {
                    Boolean makeFree = false;
                    if (this.maneuverTimestamp.isBefore(LocalTime.parse(components.get(0)))) {
                        makeFree = true;
                    }
                    RunwayInfo runwayInfo = new RunwayInfo(components);
                    runwayInfo.runwayInfoFunction(arg, runways, makeFree);
                    break;
                }
                case "flight_info": {
                    outFileFlightInfo = new FileOutputStream(outFilePathFlightInfo, true);
                    streamFlightInfo = new PrintStream(outFileFlightInfo);

                    FlightInfo flightInfo = new FlightInfo(components);
                    flightInfo.flightInfoFunction(runways, streamFlightInfo);
                    break;
                }
                case "exit": {
                    outFileFlightInfo.close();
                    break;
                }
            }
        } catch (UnavailableRunwayException | IncorrectRunwayException ex) {
            try {
                String outFilePathBoardExceptions = "src/main/resources/" + arg + "/board_exceptions.out";
                FileOutputStream outFileBoardExceptions = new FileOutputStream(outFilePathBoardExceptions, true);
                PrintStream streamBoardExceptions = new PrintStream(outFileBoardExceptions);
                streamBoardExceptions.println(ex.getMessage());
                outFileBoardExceptions.close();
            } catch (IOException ex2) {
                System.out.println(ex2.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        RunwayFunctions<Airplane> runways = new RunwayFunctions<>();
        Main main = new Main();
        main.maneuverTimestamp = LocalTime.of(0, 0, 0);
        main.readCommands(args[0], runways);
    }
}