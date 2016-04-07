package de.fu_berlin.agdb.importer.noaa.core;

import de.fu_berlin.agdb.importer.payload.LocationWeatherData;

import java.io.File;
import java.util.List;

public abstract class DataFileHandler {

    private File file;

    public DataFileHandler(File file) {
        this.file = file;
    }

    public abstract List<LocationWeatherData> handleDataFile();

    protected File getFile() {
        return file;
    }
}
