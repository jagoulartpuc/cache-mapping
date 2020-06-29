package orgarq.domain;

import java.util.Map;

public class AssCacheLine {

    private String tag;
    private Map<String, String> words;

    public AssCacheLine() {

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Map<String, String> getWords() {
        return words;
    }

    public void setWords(Map<String, String> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "AssCacheLine{" +
                "tag='" + tag + '\'' +
                ", words=" + words +
                '}';
    }
}
