package com.useo.demo.dtos.post;

import java.time.LocalDateTime;

public class PostEntryResponseDTO {

    private Long id;
    private String title;
    private String leadHtml;
    private String mainHtml;
    private String author;
    private String slug;
    private boolean published;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostEntryResponseDTO() {
    }

    public PostEntryResponseDTO(Long id, String title, String leadHtml, String mainHtml, String author, String slug, boolean published, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.leadHtml = leadHtml;
        this.mainHtml = mainHtml;
        this.author = author;
        this.slug = slug;
        this.published = published;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}