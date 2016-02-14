package pt.showtracker.tvDb.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Entity resulting form the search for a show.
 */
@XmlRootElement(name = "Data")
public class SearchEntity {

    private List<SeriesEntity> series;

    public List<SeriesEntity> getData() {
        return series;
    }

    @XmlElement(name = "Series")
    public void setData(List<SeriesEntity> series) {
        this.series = series;
    }
}
