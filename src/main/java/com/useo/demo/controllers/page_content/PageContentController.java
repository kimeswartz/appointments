package com.useo.demo.controllers.page_content;

import com.useo.demo.dtos.page_content.PageContentSaveDTO;
import com.useo.demo.dtos.page_content.PageContentResponseDTO;
import com.useo.demo.services.page_content.PageContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/page-content")
@RestController
public class PageContentController {

    private final PageContentService pageContentService;

    public PageContentController(PageContentService pageContentService) {
        this.pageContentService = pageContentService;
    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<PageContentResponseDTO> updatePageContent(@PathVariable Long id, @RequestBody PageContentSaveDTO pageContentSaveDTO) {
        PageContentResponseDTO pageContentResponseDTO = pageContentService.updatePageContent(id, pageContentSaveDTO);
        return ResponseEntity.ok(pageContentResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageContentResponseDTO> getPageContent(@PathVariable Long id) {
        PageContentResponseDTO pageContentResponseDTO = pageContentService.getPageContent(id);
        return ResponseEntity.ok(pageContentResponseDTO);
    }
}

