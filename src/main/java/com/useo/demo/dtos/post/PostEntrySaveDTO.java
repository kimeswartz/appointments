package com.useo.demo.dtos.post;

public class PostEntrySaveDTO {

    private String title;
    private String leadHtml;
    private String mainHtml;
    private String author;
    private String slug;
    private boolean published;

    public PostEntrySaveDTO() {
    }

    public PostEntrySaveDTO(String title, String leadHtml, String mainHtml, String author, String slug, boolean published) {
        this.title = title;
        this.leadHtml = leadHtml;
        this.mainHtml = mainHtml;
        this.author = author;
        this.slug = slug;
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeadHtml() {
        return leadHtml;
    }

    public void setLeadHtml(String leadHtml) {
        this.leadHtml = leadHtml;
    }

    public String getMainHtml() {
        return mainHtml;
    }

    public void setMainHtml(String mainHtml) {
        this.mainHtml = mainHtml;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}