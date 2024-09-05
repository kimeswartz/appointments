package com.useo.demo.controllers.post;

import com.useo.demo.dtos.post.PostEntryResponseDTO;
import com.useo.demo.dtos.post.PostEntrySaveDTO;
import com.useo.demo.services.post.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<PostEntryResponseDTO> createPost(@RequestBody PostEntrySaveDTO postEntrySaveDTO) {
        PostEntryResponseDTO postEntryResponseDTO = postService.createPost(postEntrySaveDTO);
        return ResponseEntity.ok(postEntryResponseDTO);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<PostEntryResponseDTO> updatePost(@PathVariable Long id, @RequestBody PostEntrySaveDTO postEntrySaveDTO) {
        PostEntryResponseDTO postEntryResponseDTO = postService.updatePost(id, postEntrySaveDTO);
        return ResponseEntity.ok(postEntryResponseDTO);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<PostEntryResponseDTO>> getAllPosts() {
        List<PostEntryResponseDTO> postEntryResponseDTOs = postService.findAll();
        return ResponseEntity.ok(postEntryResponseDTOs);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<PostEntryResponseDTO> getPostById(@PathVariable Long id) {
        return postService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}