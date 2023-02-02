package org.elasticsearch.plugin.analysis.util.contentType;

import org.elasticsearch.plugin.analysis.util.AbstractContentType;
import org.elasticsearch.plugin.analysis.util.textParser.UrlParser;

public class TorUrl extends AbstractContentType {

    public TorUrl(){
        this.parse_option = "tor_url";
    }

    @Override
    public Object parser(String field_value) {
        return UrlParser.extractTorUrl(field_value);
    }
}
