package de.fu_berlin.agdb.importer.payload;

import org.json.JSONObject;

import java.util.Date;

public class GridMetaData extends LocationMetaData {

    private long gridId;
    private Date fromDate;
    private Date toDate;

    private double gridLat;
    private double gridLon;

    public GridMetaData(double gridLat, double gridLon) {
        this.gridLat = gridLat;
        this.gridLon = gridLon;
    }

    public long getGridId() {
        return gridId;
    }

    public void setGridId(long gridId) {
        this.gridId = gridId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public double getGridLat() {
        return gridLat;
    }

    public void setGridLat(float gridLat) {
        this.gridLat = gridLat;
    }

    public double getGridLon() {
        return gridLon;
    }

    public void setGridLon(float gridLon) {
        this.gridLon = gridLon;
    }

    public JSONObject asJSONObject() {
        JSONObject jsonObject = super.asJSONObject();
        jsonObject.put("gridId", gridId);
        jsonObject.put("fromDate", fromDate);
        jsonObject.put("toDate", toDate);
        jsonObject.put("gridLat", gridLat);
        jsonObject.put("gridLon", gridLon);
        return jsonObject;
    }
}
