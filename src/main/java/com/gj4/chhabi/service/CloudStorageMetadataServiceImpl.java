package com.gj4.chhabi.service;

import com.gj4.chhabi.fwk.mongo.MongoService;
import com.gj4.chhabi.fwk.mongo.MongoServiceFactory;
import com.gj4.chhabi.model.CloudStorageMetadata;
import com.gj4.chhabi.util.ChhabiCollectionUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Krunal Lukhi
 * @since 03/08/24
 */
@Service
public class CloudStorageMetadataServiceImpl implements CloudStorageMetadataService {
    private final MongoService<CloudStorageMetadata> metadataMongoService;

    public CloudStorageMetadataServiceImpl(MongoServiceFactory mongoServiceFactory) {
        this.metadataMongoService = mongoServiceFactory.lookup(CloudStorageMetadata.class);
    }

    @Override
    public CloudStorageMetadata create(CloudStorageMetadata object) {
        return metadataMongoService.create(object);
    }

    @Override
    public CloudStorageMetadata update(CloudStorageMetadata object) {
        return metadataMongoService.update(object);
    }

    @Override
    public CloudStorageMetadata find(String id) {
        return metadataMongoService.find(id);
    }

    @Override
    public void delete(String id) {
        metadataMongoService.delete(id);
    }

    @Override
    public List<CloudStorageMetadata> search(Query query) {
        return metadataMongoService.search(query);
    }

    @Override
    public CloudStorageMetadata findOptimalDrive(String storageProvider) {
        Query query = Query.query(Criteria.where(CloudStorageMetadata.Fields.provider).is(storageProvider)).with(Sort.by(CloudStorageMetadata.Fields.remainingSpace).descending());
        List<CloudStorageMetadata> search = metadataMongoService.search(query);
        return ChhabiCollectionUtil.firstElement(search);
    }

    @Override
    public CloudStorageMetadata findOptimalDrive() {
        Query query = new Query().with(Sort.by(CloudStorageMetadata.Fields.remainingSpace).descending());
        List<CloudStorageMetadata> search = metadataMongoService.search(query);
        return ChhabiCollectionUtil.firstElement(search);
    }
}
