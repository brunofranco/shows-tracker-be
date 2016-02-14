package pt.showtracker.tvDb.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Entity resulting form the search for a show.
 */
@XmlRootElement(name = "Data")
public class EpisodesEntity {

    private List<EpisodeEntity> episodes;

    public List<EpisodeEntity> getEpisodes() {
        return episodes;
    }

    @XmlElement(name = "Episode")
    public void setEpisodes(List<EpisodeEntity> episodes) {
        this.episodes = episodes;
    }
}
