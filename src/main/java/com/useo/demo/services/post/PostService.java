package com.useo.demo.services.post;

import com.useo.demo.dtos.post.PostEntryResponseDTO;
import com.useo.demo.dtos.post.PostEntrySaveDTO;
import com.useo.demo.entities.post.PostEntry;
import com.useo.demo.repositories.post.PostEntryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostEntryRepository postEntryRepository;

    public PostService(PostEntryRepository postEntryRepository) {
        this.postEntryRepository = postEntryRepository;
    }

    // Create Operation
    public PostEntryResponseDTO createPost(PostEntrySaveDTO postEntrySaveDTO) {
        var postEntry = new PostEntry()
                .setTitle(postEntrySaveDTO.getTitle())
                .setLeadHtml(postEntrySaveDTO.getLeadHtml())
                .setMainHtml(postEntrySaveDTO.getMainHtml())
                .setAuthor(postEntrySaveDTO.getAuthor())
                .setSlug(postEntrySaveDTO.getSlug())
                .setPublished(postEntrySaveDTO.isPublished());

        PostEntry savedPost = postEntryRepository.save(postEntry);
        return convertToResponseDto(savedPost);
    }

    // Read Operation - Find by ID
    public Optional<PostEntryResponseDTO> findById(Long id) {
        Optional<PostEntry> optionalPostEntry = postEntryRepository.findById(id);
        return optionalPostEntry.map(this::convertToResponseDto);
    }

    // Read Operation - Find All
    public List<PostEntryResponseDTO> findAll() {
        List<PostEntry> postEntries = (List<PostEntry>) postEntryRepository.findAll();
        return postEntries.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    // Update Operation
    public PostEntryResponseDTO updatePost(Long id, PostEntrySaveDTO postEntrySaveDTO) {
        Optional<PostEntry> optionalPostEntry = postEntryRepository.findById(id);
        if (optionalPostEntry.isEmpty()) {
            throw new IllegalArgumentException("Post not found with ID: " + id);
        }

        PostEntry postEntry = optionalPostEntry.get()
                .setTitle(postEntrySaveDTO.getTitle())
                .setLeadHtml(postEntrySaveDTO.getLeadHtml())
                .setMainHtml(postEntrySaveDTO.getMainHtml())
                .setAuthor(postEntrySaveDTO.getAuthor())
                .setSlug(postEntrySaveDTO.getSlug())
                .setPublished(postEntrySaveDTO.isPublished());

        PostEntry updatedPost = postEntryRepository.save(postEntry);
        return convertToResponseDto(updatedPost);
    }

    // Delete Operation
    public void deletePost(Long id) {
        if (!postEntryRepository.existsById(id)) {
            throw new IllegalArgumentException("Post not found with ID: " + id);
        }
        postEntryRepository.deleteById(id);
    }

    // Converts PostEntry entity to PostEntryResponseDTO
    private PostEntryResponseDTO convertToResponseDto(PostEntry postEntry) {
        PostEntryResponseDTO dto = new PostEntryResponseDTO();
        dto.setId(postEntry.getId());
        dto.setTitle(postEntry.getTitle());
        dto.setLeadHtml(postEntry.getLeadHtml());
        dto.setMainHtml(postEntry.getMainHtml());
        dto.setAuthor(postEntry.getAuthor());
        dto.setSlug(postEntry.getSlug());
        dto.setPublished(postEntry.isPublished());
        dto.setCreatedAt(postEntry.getCreatedAt());
        dto.setUpdatedAt(postEntry.getUpdatedAt());
        return dto;
    }
}