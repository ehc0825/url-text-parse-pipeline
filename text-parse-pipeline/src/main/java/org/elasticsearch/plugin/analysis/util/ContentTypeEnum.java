package org.elasticsearch.plugin.analysis.util;

import org.elasticsearch.plugin.analysis.util.contentType.TorUrl;
import org.elasticsearch.plugin.analysis.util.contentType.Url;

import java.util.Arrays;

public enum ContentTypeEnum {

    TOR_URL(new TorUrl()),
    URL(new Url()),
    OR_ELSE(new TorUrl());


    private AbstractContentType abstractContentType;

    ContentTypeEnum(AbstractContentType abstractContentType) {
        this.abstractContentType = abstractContentType;
    }

    public static Object parseContentByContentType(String contentType, String content) {
        return find(contentType).abstractContentType.parser(content);
    }

    private static ContentTypeEnum find(String contentType) {
        return Arrays.stream(values())
                .filter(contentTypeEnum -> contentTypeEnum.abstractContentType.parse_option.equals(contentType))
                .findAny()
                .orElse(OR_ELSE);
    }
}
