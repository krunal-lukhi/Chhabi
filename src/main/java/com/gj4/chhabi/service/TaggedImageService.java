package com.gj4.chhabi.service;

import com.gj4.chhabi.fwk.elasticsearch.ElasticSearchService;
import com.gj4.chhabi.fwk.elasticsearch.ElasticSearchServiceFactory;
import com.gj4.chhabi.fwk.upload.UploadRequest;
import com.gj4.chhabi.fwk.upload.UploadResponse;
import com.gj4.chhabi.model.TaggedImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
@Service
public class TaggedImageService {
    private final ElasticSearchService<TaggedImage> elasticSearchService;
    private final SmartCloudStorageService smartCloudStorageService;

    public TaggedImageService(ElasticSearchServiceFactory elasticSearchServiceFactory, SmartCloudStorageService smartCloudStorageService) {
        this.smartCloudStorageService = smartCloudStorageService;
        this.elasticSearchService = elasticSearchServiceFactory.lookup(TaggedImage.class);
    }

    public void processImage(Set<String> tags, MultipartFile file) {
        UploadRequest uploadReqeust = convertToUploadReqeust(file);
        UploadResponse uploadResponse = smartCloudStorageService.upload(uploadReqeust);
        TaggedImage taggedImage = buildTaggedImage(tags, uploadResponse);
        elasticSearchService.create(taggedImage);
    }

    public List<TaggedImage> getAllImages() {
        return elasticSearchService.findAll();
    }

    private TaggedImage buildTaggedImage(Set<String> tags, UploadResponse uploadResponse) {
        TaggedImage image = new TaggedImage();
        image.setImageUrl(uploadResponse.getUrl());
        image.setImageId(uploadResponse.getIdentifier());
        image.setTags(tags);
        return image;
    }

    /***********************************************************************************************************
     *                                           PRIVATE METHODS                                                                            *
     ***********************************************************************************************************/

    private UploadRequest convertToUploadReqeust(MultipartFile file) {
        try {
            UploadRequest uploadRequest = new UploadRequest();
            uploadRequest.setFileName(file.getName());
            uploadRequest.setInputStream(file.getInputStream());
            return uploadRequest;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
