package com.gj4.chhabi.service;

import com.gj4.chhabi.fwk.crud.CrudService;
import com.gj4.chhabi.model.CloudStorageMetadata;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author Krunal Lukhi
 * @since 03/08/24
 */
public interface CloudStorageMetadataService extends CrudService<CloudStorageMetadata> {

    List<CloudStorageMetadata> search(Query query);
    CloudStorageMetadata findOptimalDrive(String storageProvider);
    CloudStorageMetadata findOptimalDrive();
}
