package orgarq.domain;


import java.util.Map;

public class CacheLine {

    private String tag;
    private Map<String, String> words;
    private String line;
    private boolean isValid;

    public CacheLine() {
        isValid = false;
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

    public void setLine(String line) {
        this.line = line;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "CacheLine{" +
                "tag='" + tag + '\'' +
                ", words=" + words +
                ", line='" + line + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}
