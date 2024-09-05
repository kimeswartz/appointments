package com.useo.demo.repositories.page_content;

import com.useo.demo.entities.page_content.PageContent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface PageContentRepository extends CrudRepository <PageContent, Long> {
        Optional<PageContent> findByPageNameAndSectionName(String pageName, String sectionName);
    }