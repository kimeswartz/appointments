package com.useo.demo.services.post;

import com.useo.demo.dtos.post.PostEntryResponseDTO;
import com.useo.demo.dtos.post.PostEntrySaveDTO;
import com.useo.demo.entities.post.PostEntry;
import com.useo.demo.entities.user.User;
import com.useo.demo.repositories.post.PostEntryRepository;
import com.useo.demo.repositories.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {


    private final PostEntryRepository postEntryRepository;
    private final UserRepository userRepository; // Add UserRepository

    public PostService(PostEntryRepository postEntryRepository, UserRepository userRepository) {
        this.postEntryRepository = postEntryRepository;
        this.userRepository = userRepository;
    }

    // Create Operation
    public PostEntryResponseDTO createPost(PostEntrySaveDTO postEntrySaveDTO) {
        Optional<User> userOpt = userRepository.findById(postEntrySaveDTO.getUserId());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + postEntrySaveDTO.getUserId());
        }

        User user = userOpt.get();
        var postEntry = new PostEntry()
                .setTitle(postEntrySaveDTO.getTitle())
                .setLeadHtml(postEntrySaveDTO.getLeadHtml())
                .setMainHtml(postEntrySaveDTO.getMainHtml())
                .setSlug(postEntrySaveDTO.getSlug())
                .setPublished(postEntrySaveDTO.isPublished())
                .setHeaderImageUrl(postEntrySaveDTO.getHeaderImageUrl())
                .setUser(user); // Set user

        PostEntry savedPost = postEntryRepository.save(postEntry);
        return convertToResponseDto(savedPost);
    }

    // Read Operation - Find by ID
    public Optional<PostEntryResponseDTO> findById(Long id) {
        Optional<PostEntry> optionalPostEntry = postEntryRepository.findById(id);
        return optionalPostEntry.map(this::convertToResponseDto);
    }

    // Read Operation - Find by Slug
    public Optional<PostEntryResponseDTO> findBySlug(String slug) {
        Optional<PostEntry> optionalPostEntry = postEntryRepository.findBySlug(slug);
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

        PostEntry postEntry = optionalPostEntry.get();
        Optional<User> userOpt = userRepository.findById(postEntrySaveDTO.getUserId());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + postEntrySaveDTO.getUserId());
        }

        User user = userOpt.get();
        postEntry.setTitle(postEntrySaveDTO.getTitle())
                .setLeadHtml(postEntrySaveDTO.getLeadHtml())
                .setMainHtml(postEntrySaveDTO.getMainHtml())
                .setSlug(postEntrySaveDTO.getSlug())
                .setPublished(postEntrySaveDTO.isPublished())
                .setHeaderImageUrl(postEntrySaveDTO.getHeaderImageUrl())
                .setUser(user); // Set user

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
        dto.setUserId(postEntry.getUser().getId()); // Map user ID
        dto.setSlug(postEntry.getSlug());
        dto.setHeaderImageUrl(postEntry.getHeaderImageUrl());
        dto.setPublished(postEntry.isPublished());
        dto.setCreatedAt(postEntry.getCreatedAt());
        dto.setUpdatedAt(postEntry.getUpdatedAt());
        return dto;
    }
}