package org.elasticsearch.plugin.analysis.util.contentType;

import org.elasticsearch.plugin.analysis.util.AbstractContentType;

public class OrElse extends AbstractContentType {

    public OrElse() {
        this.parse_option = "or_else";
    }

    @Override
    public Object parser(String field_value) {
        return field_value;
    }
}
