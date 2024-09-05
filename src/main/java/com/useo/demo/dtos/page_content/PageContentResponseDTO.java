package com.useo.demo.dtos.page_content;

import java.time.LocalDateTime;

public class PageContentResponseDTO {

    private Long id;
    private String pageName;
    private String sectionName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PageContentResponseDTO() {
    }

    public PageContentResponseDTO(Long id, String pageName, String sectionName, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.pageName = pageName;
        this.sectionName = sectionName;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
