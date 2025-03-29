package functions;

import entities.*;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RunwayInfo<E extends Airplane> extends RunwayFunctions<E> {
    private LocalTime timestamp;
    private String idRunway;

    public RunwayInfo() {
        super();
    }
    public RunwayInfo(ArrayList<String> command) {
        super(command);
        try {
            this.timestamp = LocalTime.parse(super.getCommand().get(0));
            this.idRunway = super.getCommand().get(2);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void runwayInfoFunction(String arg, RunwayFunctions<E> runways, Boolean makeFree) {
        Runway<E> runway = runways.findRunway(this.idRunway);
        if (makeFree) {
            runway.setStatus("FREE");
        }
        try {
            DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("HH");
            DateTimeFormatter minuteFormat = DateTimeFormatter.ofPattern("mm");
            DateTimeFormatter secondFormat = DateTimeFormatter.ofPattern("ss");
            String outFilePathRunwayInfo = "src/main/resources/" + arg + "/runway_info_" + this.idRunway + "_" +
                    this.timestamp.format(hourFormat) + "-" + this.timestamp.format(minuteFormat) + "-" + this.timestamp.format(secondFormat) + ".out";
            FileOutputStream outFileRunwayInfo = new FileOutputStream(outFilePathRunwayInfo);
            PrintStream streamRunwayInfo = new PrintStream(outFileRunwayInfo);

            streamRunwayInfo.print(runway);
            outFileRunwayInfo.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
