package com.gj4.chhabi.rest;

import com.gj4.chhabi.service.TaggedImageService;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
@RestController
@RequestMapping(path = "/image")
public class ImageController {

    private final TaggedImageService taggedImageService;

    public ImageController(TaggedImageService taggedImageService) {
        this.taggedImageService = taggedImageService;
    }

    @PostMapping(path = "/upload")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("tags") Set<String> tags) {
        taggedImageService.processImage(tags, file);
    }

}
