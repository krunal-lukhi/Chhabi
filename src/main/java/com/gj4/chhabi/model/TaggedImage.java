package com.gj4.chhabi.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Set;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
@Document(indexName = "tagged_image")
public class TaggedImage extends BaseEntity implements ESEntity{
    private String imageUrl;
    private Set<String> tags;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
