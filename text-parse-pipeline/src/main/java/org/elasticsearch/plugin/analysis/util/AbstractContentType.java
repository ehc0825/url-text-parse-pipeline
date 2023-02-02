package org.elasticsearch.plugin.analysis.util;

public abstract class AbstractContentType {

    public String parse_option;

    public abstract Object parser(String field_value);
}
