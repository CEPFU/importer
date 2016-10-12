package de.fu_berlin.agdb.importer.noaa;

import de.fu_berlin.agdb.importer.noaa.core.WeatherDataFileHandler;
import org.junit.Before;
import org.junit.Test;
import ucar.nc2.NetcdfFile;
import ucar.nc2.dataset.NetcdfDataset;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class NOAAImporterTest
{
    private File file;

    @Before
    public void init() throws IOException
    {
        this.file = new File("src/test/files/noaa/gfs.t00z.pgrb2.0p25.f003");
    }

    @Test
    public void testFileCanBeOpened() throws IOException
    {
        NetcdfFile netcdfFile = NetcdfDataset.open(file.getAbsolutePath());
        assertNotNull(netcdfFile);
    }

    @Test
    public void testFileCanBeHandled()
    {
        WeatherDataFileHandler handler = new WeatherDataFileHandler(file);
        assertFalse(handler.handleDataFile().isEmpty());
    }
}
