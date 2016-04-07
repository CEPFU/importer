package de.fu_berlin.agdb.importer.forecastio;

import de.fu_berlin.agdb.importer.AWeatherImporter;
import de.fu_berlin.agdb.importer.payload.LocationMetaData;
import de.fu_berlin.agdb.importer.payload.LocationWeatherData;
import de.fu_berlin.agdb.importer.forecastio.core.IWorkProvider;
import de.fu_berlin.agdb.importer.forecastio.core.ForecastIODataLoaderWorker;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by riva on 03.01.16.
 */
public class ForecastIOImporter extends AWeatherImporter implements IWorkProvider {
    private static final Logger logger = LogManager.getLogger(ForecastIOImporter.class);

    private static final int NUMBER_OF_THREADS = 10;

    private ArrayList<Thread> threadPool;

    private ArrayList<LocationWeatherData> accumulatedData;
    private List<LocationMetaData> locationsToBeDone;

    public ForecastIOImporter() {
        threadPool = new ArrayList<Thread>();
    }

    @Override
    protected List<LocationWeatherData> getWeatherDataForLocations(List<LocationMetaData> locations) {
        locationsToBeDone = locations;

        accumulatedData = new ArrayList<LocationWeatherData>();

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Thread thread = new Thread(new ForecastIODataLoaderWorker(this));
            threadPool.add(thread);
            thread.start();
        }

        while (!threadPool.isEmpty()) {
            try {
                Thread thread = threadPool.get(0);
                thread.join();
                threadPool.remove(thread);
            } catch (InterruptedException e) {
                logger.error("Interrupted while waiting for thread:", e);
            }
        }

        return accumulatedData;
    }

    @Override
    protected long getServiceTimeout() {
        //30 minutes
        return 30 * 60 * 1000;
    }

    @Override
    public LocationMetaData getWork() {
        synchronized (locationsToBeDone) {
            if (!locationsToBeDone.isEmpty()) {
                LocationMetaData work = locationsToBeDone.get(0);
                locationsToBeDone.remove(work);
                return work;
            } else {
                return null;
            }
        }
    }

    @Override
    public void deliverResult(List<LocationWeatherData> dataForLocation) {
        synchronized (accumulatedData) {
            if (dataForLocation != null) {
                accumulatedData.addAll(dataForLocation);
            }
        }
    }
}
