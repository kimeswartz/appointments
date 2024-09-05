package com.useo.demo.services.page_content;

import com.useo.demo.dtos.page_content.PageContentSaveDTO;
import com.useo.demo.dtos.page_content.PageContentResponseDTO;
import com.useo.demo.entities.page_content.PageContent;
import com.useo.demo.repositories.page_content.PageContentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageContentService {

    private final PageContentRepository pageContentRepository;

    // Constructor for injecting dependencies (PageContentRepository).
    public PageContentService(PageContentRepository pageContentRepository) {
        this.pageContentRepository = pageContentRepository;
    }

    // Updates an existing PageContent entity with new data from the PageContentSaveDTO.
    // If the entity with the given ID does not exist, an exception is thrown.
    public PageContentResponseDTO updatePageContent(Long id, PageContentSaveDTO input) {
        Optional<PageContent> optionalPageContent = pageContentRepository.findById(id);
        if (optionalPageContent.isEmpty()) {
            throw new IllegalArgumentException("PageContent not found with ID: " + id);
        }

        // Update the existing PageContent entity with new values from the DTO.
        var pageContent = optionalPageContent.get()
                .setPageName(input.getPageName())
                .setSectionName(input.getSectionName())
                .setContent(input.getContent());

        // Save the updated entity back to the repository and return it as a DTO.
        return convertToDto(pageContentRepository.save(pageContent));
    }

    public PageContentResponseDTO getPageContent(Long id) {
        Optional<PageContent> optionalPageContent = pageContentRepository.findById(id);
        if (optionalPageContent.isEmpty()) {
            throw new IllegalArgumentException("PageContent not found with ID: " + id);
        }

        return convertToDto(optionalPageContent.get());
    }

    // Converts a PageContent entity to a PageContentResponseDTO.
    // This method helps encapsulate the entity data into a format suitable for API responses.
    private PageContentResponseDTO convertToDto(PageContent pageContent) {
        PageContentResponseDTO dto = new PageContentResponseDTO();
        dto.setId(pageContent.getId());
        dto.setPageName(pageContent.getPageName());
        dto.setSectionName(pageContent.getSectionName());
        dto.setContent(pageContent.getContent());
        dto.setCreatedAt(pageContent.getCreatedAt());
        dto.setUpdatedAt(pageContent.getUpdatedAt());
        return dto;
    }
}
