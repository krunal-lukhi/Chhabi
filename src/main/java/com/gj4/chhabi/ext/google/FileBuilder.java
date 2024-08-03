package com.gj4.chhabi.ext.google;

import com.gj4.chhabi.ext.commons.MimeType;
import com.google.api.services.drive.model.File;

import java.util.Collections;

/**
 * @author TedaMeda
 * @since 7/31/2024
 */
public class FileBuilder {
    private final File file;
    private FileBuilder() {
        file = new File();
    }
    public static FileBuilder builder() {
        return new FileBuilder();
    }

    public FileBuilder name(String name){
        file.setName(name);
        return this;
    }

    public FileBuilder parent(String parent) {
        file.setParents(Collections.singletonList(parent));
        return this;
    }

    public FileBuilder mimeType(MimeType mimeType){
        file.setMimeType(mimeType.getType());
        return this;
    }

    public File build(){
        return this.file;
    }
}
