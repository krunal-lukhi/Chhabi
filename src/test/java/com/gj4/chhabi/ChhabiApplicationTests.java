package com.gj4.chhabi;

import com.gj4.chhabi.config.IntegrationTest;
import com.gj4.chhabi.ext.google.GoogleClientFactory;
import com.gj4.chhabi.fwk.elasticsearch.ElasticSearchService;
import com.gj4.chhabi.fwk.elasticsearch.ElasticSearchServiceFactory;
import com.gj4.chhabi.fwk.mongo.MongoService;
import com.gj4.chhabi.fwk.mongo.MongoServiceFactory;
import com.gj4.chhabi.model.CloudStorageMetadata;
import com.gj4.chhabi.model.TaggedImage;
import com.google.api.services.drive.Drive;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


class ChhabiApplicationTests extends IntegrationTest {

    @Autowired
    private ElasticSearchServiceFactory elasticSearchServiceFactory;
    @Autowired
    private MongoServiceFactory mongoServiceFactory;
    @Autowired
    private GoogleClientFactory clientFactory;
    @Test
    void contextLoads() {
        ElasticSearchService<TaggedImage> lookup = elasticSearchServiceFactory.lookup(TaggedImage.class);
        TaggedImage taggedImage = new TaggedImage();
        Set<String> tags = new HashSet<>();
        tags.add("Krunal");
        taggedImage.setTags(tags);
        taggedImage.setImageUrl("https://local-maa-j-chhe.com");
        lookup.create(taggedImage);
    }

    @Test
    void mongoServiceTest(){
        MongoService<CloudStorageMetadata> service = mongoServiceFactory.lookup(CloudStorageMetadata.class);
        CloudStorageMetadata cloudStorageMetadata = new CloudStorageMetadata();
        cloudStorageMetadata.setUrl("https://google-drive.com/");
        cloudStorageMetadata.setSize(15);
        service.create(cloudStorageMetadata);
    }

    @Test
    public void testGoogleClient(){
        Drive client = clientFactory.getClient();
        Drive.Files files = client.files();
    }
}
