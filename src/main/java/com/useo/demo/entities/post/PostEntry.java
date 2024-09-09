package com.useo.demo.entities.post;

import com.useo.demo.entities.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_content")
public class PostEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "lead_text", columnDefinition = "LONGTEXT", nullable = false)
    private String leadHtml;

    @Column(name = "main_body", columnDefinition = "LONGTEXT", nullable = false)
    private String mainHtml;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(nullable = false)
    private boolean published;

    @Column(name = "header_image_url")
    private String headerImageUrl;

    // Constructors
    public PostEntry() {}

    public PostEntry(Long id, String title, String leadHtml, String mainHtml, String slug, boolean published, User user, String headerImageUrl) {
        this.id = id;
        this.title = title;
        this.leadHtml = leadHtml;
        this.mainHtml = mainHtml;
        this.slug = slug;
        this.published = published;
        this.user = user;
        this.headerImageUrl = headerImageUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public PostEntry setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PostEntry setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLeadHtml() {
        return leadHtml;
    }

    public PostEntry setLeadHtml(String leadHtml) {
        this.leadHtml = leadHtml;
        return this;
    }

    public String getMainHtml() {
        return mainHtml;
    }

    public PostEntry setMainHtml(String mainHtml) {
        this.mainHtml = mainHtml;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PostEntry setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public PostEntry setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public PostEntry setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public boolean isPublished() {
        return published;
    }

    public PostEntry setPublished(boolean published) {
        this.published = published;
        return this;
    }

    public User getUser() {
        return user;
    }

    public PostEntry setUser(User user) {
        this.user = user;
        return this;
    }

    public String getHeaderImageUrl() {
        return headerImageUrl;
    }

    public PostEntry setHeaderImageUrl(String headerImageUrl) {
        this.headerImageUrl = headerImageUrl;
        return this;
    }
}
