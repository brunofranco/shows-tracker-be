package pt.showtracker.task;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import org.hibernate.SessionFactory;
import pt.showtracker.core.Episode;
import pt.showtracker.jdbi.EpisodeDAO;
import pt.showtracker.tvDb.TvDbApi;
import pt.showtracker.tvDb.entity.EpisodeEntity;
import pt.showtracker.tvDb.entity.EpisodesEntity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UpdateEpisodesTask extends Task {

    private String address;
    private final EpisodeDAO episodeDAO;
    private final SessionFactory sessionFactory;
    static private UpdateEpisodesTask instance = null;

    private UpdateEpisodesTask(EpisodeDAO episodeDAO, SessionFactory sessionFactory) {
        super("updateEpisodes");
        this.episodeDAO = episodeDAO;
        this.sessionFactory = sessionFactory;
        this.address = TvDbApi.getInstance().getGET_EPISODES();
        instance = this;
    }

    public static Task create(EpisodeDAO episodeDAO, SessionFactory sessionFactory) {
        return new UpdateEpisodesTask(episodeDAO, sessionFactory);
    }

    public static UpdateEpisodesTask getInstance() {
        return instance;
    }

    private String getEpisodes(String address) throws IOException {

        byte data[];
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result;
        ZipInputStream zis;
        try {
            url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            zis = new ZipInputStream(conn.getInputStream());
            ZipEntry entry;

            while((entry = zis.getNextEntry()) != null) {
                if(!entry.getName().equals("en.xml")) continue;
                int fileSize = (int)entry.getSize();
                data = new byte[fileSize];
                String filename = entry.getName();
                int index = 0;
                int max = 0;
                while((index = zis.read(data, max, fileSize-max)) > 0) {
                    max += index;
                }
                zis.close();
                return new String(data, "UTF-8");
            }
        } catch(Exception e) {
            e.printStackTrace();

        }

        return "";
    }

    @Override
    public void execute(ImmutableMultimap<String, String> immutableMultimap, PrintWriter printWriter) throws Exception {

        String showAddress = address.replace("SERIES_ID",  String.valueOf(immutableMultimap.get("id").asList().get(0)));
        System.out.println(showAddress);
        String episodes = getEpisodes(showAddress);
        StringReader s = new StringReader(episodes);
        EpisodesEntity eps = (EpisodesEntity) JAXBContext.newInstance(EpisodesEntity.class).createUnmarshaller().unmarshal(s);

        // sorted by newest
        List<EpisodeEntity> episodesList = eps.getEpisodes()
                .stream()
                .sorted((e1, e2) -> Long.compare(e1.getId(), e2.getId()))
                .collect(Collectors.toList());

        for(EpisodeEntity e : episodesList) {
            if(this.episodeDAO.exists(e.getId())) {
                break;
            } else {
                this.episodeDAO.create(new Episode(e));
            }
        }
    }

    public void execute(long seriesId) throws IOException, JAXBException {
        String showAddress = address.replace("SERIES_ID", String.valueOf(seriesId));
        System.out.println(showAddress);
        String episodes = getEpisodes(showAddress);
        StringReader s = new StringReader(episodes);
        EpisodesEntity eps = (EpisodesEntity) JAXBContext.newInstance(EpisodesEntity.class).createUnmarshaller().unmarshal(s);

        // sorted by newest
        List<EpisodeEntity> episodesList = eps.getEpisodes()
                .stream()
                .sorted((e1, e2) -> Long.compare(e1.getId(), e2.getId()))
                .collect(Collectors.toList());

        for(EpisodeEntity e : episodesList) {
            if(this.episodeDAO.exists(e.getId())) {
                break;
            } else {
                this.episodeDAO.create(new Episode(e));
            }
        }
    }
}