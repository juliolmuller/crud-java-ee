package br.com.beibe.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class FeedItem implements Serializable {

    private final static String COLOR = "dark";
    private String icon;
    private String main;
    private String text;
    private String link;
    private String color = COLOR;

    public FeedItem() {}

    public FeedItem(String icon, String main, String text, String link, String color) {
        this.icon = icon;
        this.main = main;
        this.text = text;
        this.link = link;
        this.color = color;
    }

    public FeedItem(String icon, String main, String text, String url) {
        this(icon, main, text, url, COLOR);
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMain() {
        return this.main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
