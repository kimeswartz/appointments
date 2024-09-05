package com.useo.demo.entities.page_content;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "page_content")
public class PageContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_name", nullable = false)
    private String pageName;

    @Column(name = "section_name", nullable = false)
    private String sectionName;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public PageContent(Long id, String pageName, String sectionName, String content) {
        this.id = id;
        this.pageName = pageName;
        this.sectionName = sectionName;
        this.content = content;
    }

    public PageContent() {
    }

    public Long getId() {
        return id;
    }

    public PageContent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPageName() {
        return pageName;
    }

    public PageContent setPageName(String pageName) {
        this.pageName = pageName;
        return this;
    }

    public String getSectionName() {
        return sectionName;
    }

    public PageContent setSectionName(String sectionName) {
        this.sectionName = sectionName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PageContent setContent(String content) {
        this.content = content;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PageContent setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public PageContent setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
