package de.fu_berlin.agdb.importer.forecastio.core;

import com.github.dvdme.ForecastIOLib.ForecastIO;
import de.fu_berlin.agdb.importer.payload.DataType;
import de.fu_berlin.agdb.importer.payload.LocationMetaData;
import de.fu_berlin.agdb.importer.payload.LocationWeatherData;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by riva on 03.01.16.
 */
public class ForecastIODataLoaderWorker implements Runnable {

    private static final Logger logger = LogManager.getLogger(ForecastIODataLoaderWorker.class);


    private IWorkProvider workProvider;

    public ForecastIODataLoaderWorker(IWorkProvider workProvider) {
        this.workProvider = workProvider;
    }

    @Override
    public void run() {

        LocationMetaData locationMetaData = null;
        while ((locationMetaData = workProvider.getWork()) != null) {
            try {
                List<LocationWeatherData> dataForLocation = loadDataForLocation(locationMetaData);
                workProvider.deliverResult(dataForLocation);
            } catch (ParseException | IOException e) {
                logger.error("Error while loading data");
                e.printStackTrace();
            }
        }
    }

    private List<LocationWeatherData> loadDataForLocation(LocationMetaData locationMetaData) throws ParseException, ClientProtocolException, IOException {
        ArrayList<LocationWeatherData> locationWeatherDataList = new ArrayList<LocationWeatherData>();


        // Using Java Wrapper for forecast.io, constructor expects API key as value
        ForecastIO fio = new ForecastIO("215f044abd31db5f25f8d7e76a3726cf");
        fio.setUnits(ForecastIO.UNITS_SI);
        fio.setExcludeURL("hourly,minutely");
        fio.getForecast("" + locationMetaData.getLocationPosition().getGeometry().getFirstPoint().getY(),
                "" + locationMetaData.getLocationPosition().getGeometry().getFirstPoint().getX());

        JSONObject weatherForLocation = new JSONObject(fio.getDaily().toString());

        JSONArray forecast = weatherForLocation.getJSONArray("data");
        for (int i = 0; i < forecast.length(); i++) {
            JSONObject forecastEntryData = forecast.getJSONObject(i);
            LocationWeatherData forecastWeatherData = new LocationWeatherData(locationMetaData, System.currentTimeMillis(), DataType.FORECAST);
            forecastWeatherData.setDate(new Date(java.util.Calendar.getInstance().getTimeInMillis()));
            forecastWeatherData.setTemperatureHigh(forecastEntryData.getDouble("temperatureMax"));
            forecastWeatherData.setTemperatureLow(forecastEntryData.getDouble("temperatureMin"));
            locationWeatherDataList.add(forecastWeatherData);
        }


        return locationWeatherDataList;

    }

    private Date parseBuildDate(String date) throws ParseException {
        return parseRFC822Date(date.substring(5, 16));
    }

    private Date parseRFC822Date(String rfc822Date) throws ParseException {
        String datePattern = "dd MMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern, Locale.US);
        return new Date(simpleDateFormat.parse(rfc822Date).getTime());
    }


}
