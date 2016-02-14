package pt.showtracker.task;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Optional;

public class TaskDispatcher {
    static Optional<TaskDispatcher> instance = Optional.empty();

    private String host;
    private String port;

    public TaskDispatcher() {
    }

    private TaskDispatcher(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public static void create(String host, String port) {
        if (!instance.isPresent()) {
            instance = Optional.of(new TaskDispatcher(host, port));
        }
    }

    public static TaskDispatcher getInstance() {
        return instance.orElse(new TaskDispatcher());
    }

    public void scheduleTask(String taskName, long externalId) {
        switch (taskName) {
            case "downloadEpisodes":
                callTask(taskName, externalId);
                break;
        }
    }

    private void callTask(String taskName, long externalId) {
        URL url;
        HttpURLConnection conn;
        try {
            url = new URL("http://"+this.host + ":" + this.port + "/tasks/" + taskName + "?id=" + externalId);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}