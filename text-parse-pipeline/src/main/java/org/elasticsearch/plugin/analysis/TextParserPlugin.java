package org.elasticsearch.plugin.analysis;

import org.elasticsearch.plugin.analysis.processor.TextParseProcessor;
import org.elasticsearch.plugins.IngestPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.ingest.Processor.Factory;
import static org.elasticsearch.ingest.Processor.Parameters;

public class TextParserPlugin extends Plugin implements IngestPlugin {

    @Override
    public Map<String, Factory> getProcessors(Parameters parameters) {
        Map<String, Factory> processors = new HashMap<>();
        processors.put(TextParseProcessor.TYPE, new TextParseProcessor.Factory());
        return processors;
    }

}


