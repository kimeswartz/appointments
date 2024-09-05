package com.useo.demo.bootstrap;

import com.useo.demo.entities.page_content.PageContent;
import com.useo.demo.repositories.page_content.PageContentRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public class PageContentSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final PageContentRepository pageContentRepository;

    public PageContentSeeder(@NonNull PageContentRepository pageContentRepository) {
        this.pageContentRepository = pageContentRepository;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        this.initializePageContent();
    }

    private void initializePageContent() {
        // Check if there is any existing PageContent, and if not, seed the database
        if (pageContentRepository.count() == 0) {
            PageContent introductionSection = new PageContent()
                    .setPageName("about-us")
                    .setSectionName("Introduction")
                    .setContent("Welcome to our company. We are committed to providing the best services to our clients.");

            PageContent missionSection = new PageContent()
                    .setPageName("about-us")
                    .setSectionName("Our Mission")
                    .setContent("Our mission is to innovate and lead in the industry, delivering top-notch solutions to our customers.");

            pageContentRepository.save(introductionSection);
            pageContentRepository.save(missionSection);
        }
    }
}
