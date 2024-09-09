package com.useo.demo.dtos.post;

public class PostEntrySaveDTO {

    private String title;
    private String leadHtml;
    private String mainHtml;
    private Long userId;
    private String slug;
    private boolean published;
    private String headerImageUrl;

    public PostEntrySaveDTO() {}

    public PostEntrySaveDTO(String title, String leadHtml, String mainHtml, Long userId, String slug, boolean published, String headerImageUrl) {
        this.title = title;
        this.leadHtml = leadHtml;
        this.mainHtml = mainHtml;
        this.userId = userId;
        this.slug = slug;
        this.published = published;
        this.headerImageUrl = headerImageUrl;
    }

    // Getters and Setters
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getHeaderImageUrl() {
        return headerImageUrl;
    }

    public void setHeaderImageUrl(String headerImageUrl) {
        this.headerImageUrl = headerImageUrl;
    }
}