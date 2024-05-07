package org.hivemq.edge.framework.models;

import java.util.Date;

public class CurrentTimeApiResponse {

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    private String abbreviation;
    private String client_ip;
    private Date datetime;
    private int day_of_week;
    private int day_of_year;
    private boolean dst;
    private Date dst_from;
    private int dst_offset;
    private Date dst_until;
    private int raw_offset;
    private String timezone;
    private int unixtime;
    private Date utc_datetime;
    private String utc_offset;
    private int week_number;
}
