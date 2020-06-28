package orgarq.domain;


import java.util.Map;

public class CacheLine {

    private String tag;
    private Map<String, String> words;
    private String line;
    private String s;
    private boolean isValid = false;

    public CacheLine() {
    }

    public CacheLine(String tag, Map<String, String> words, String line, String s) {
        this.tag = tag;
        this.words = words;
        this.line = line;
        this.s = s;
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
