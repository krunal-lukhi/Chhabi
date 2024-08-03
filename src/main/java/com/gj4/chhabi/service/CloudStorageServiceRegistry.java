package com.gj4.chhabi.service;

import com.gj4.chhabi.ext.commons.CloudStorageService;
import com.gj4.chhabi.fwk.commons.SpringServiceRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

/**
 * @author Krunal Lukhi
 * @since 03/08/24
 */
@Service
public class CloudStorageServiceRegistry extends SpringServiceRegistry<String, CloudStorageService> {
    public CloudStorageServiceRegistry(List<CloudStorageService> services) {
        super(services, CloudStorageService::storageProvider);
    }

    @Override
    public CloudStorageService defaultValue() {
        return null;
    }
}
