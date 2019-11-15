package br.com.beibe.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class Hyperlink implements Serializable {

    private String label;
    private String url;
    private boolean active;

    public Hyperlink() {}

    public Hyperlink(String label, String url, boolean active) {
        this.label = label;
        this.url = url;
        this.active = active;
    }

    public Hyperlink(String label, String url) {
        this(label, url, false);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
