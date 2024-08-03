package com.gj4.chhabi;

import com.gj4.chhabi.config.IntegrationTest;
import com.gj4.chhabi.ext.google.FileBuilder;
import com.gj4.chhabi.ext.google.GoogleDriveService;
import com.gj4.chhabi.fwk.commons.UniversalConstants;
import com.gj4.chhabi.ext.google.GoogleClientFactory;
import com.gj4.chhabi.fwk.elasticsearch.ElasticSearchService;
import com.gj4.chhabi.fwk.elasticsearch.ElasticSearchServiceFactory;
import com.gj4.chhabi.fwk.mongo.MongoService;
import com.gj4.chhabi.fwk.mongo.MongoServiceFactory;
import com.gj4.chhabi.model.CloudStorageMetadata;
import com.gj4.chhabi.model.TaggedImage;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.gj4.chhabi.ext.commons.CloudStorageConstants.GoogleConstants.Fields.*;
import static com.gj4.chhabi.ext.commons.CloudStorageConstants.GoogleConstants.Fields.webViewLink;
import static com.gj4.chhabi.util.ChhabiStringUtils.joinKeys;


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
        cloudStorageMetadata.setFolderId("https://google-drive.com/");
        cloudStorageMetadata.setTotalSize(15);
        service.create(cloudStorageMetadata);
    }

    @Test
    public void testGoogleClient(){
        Drive client = clientFactory.getClient();
        Drive.Files files = client.files();
    }

    @Test
    public void uploadFileTest() throws IOException {
        File fileObj = new File("src/main/resources/credentials.json");
        Drive drive = clientFactory.getClient();
        com.google.api.services.drive.model.File file = FileBuilder.builder().name(fileObj.getName()).parent("1-Yo4Rw7fJZv2o1CpexDtdp-0VKkWpGgh").build();
//        file.setMimeType("application/json");
        FileContent fileContent = new FileContent(null, fileObj);
        com.google.api.services.drive.model.File fileOutput = drive.files().create(file, fileContent)
                .setFields(joinKeys(UniversalConstants.COMMA, id, "mimeType", size, name, webContentLink, webViewLink))
                .execute();

        System.out.println(fileOutput.getId());
        System.out.println(fileOutput.getWebViewLink());
        System.out.println(fileOutput.getWebContentLink());
    }
}
