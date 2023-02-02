package org.elasticsearch.plugin.analysis.util.contentType;

import org.elasticsearch.plugin.analysis.util.AbstractContentType;
import org.elasticsearch.plugin.analysis.util.textParser.UrlParser;

public class Url extends AbstractContentType {

    public Url() {
        this.parse_option = "url";
    }
    @Override
    public Object parser(String field_value) {
        return UrlParser.extractUrl(field_value);
    }
}
