package pt.showtracker.tvDb.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity a show.
 */
@XmlRootElement(name = "Data")
public class ShowEntity {

    private SeriesEntity series;

    public SeriesEntity getData() {
        return series;
    }

    @XmlElement(name = "Series")
    public void setData(SeriesEntity series) {
        this.series = series;
    }
}
