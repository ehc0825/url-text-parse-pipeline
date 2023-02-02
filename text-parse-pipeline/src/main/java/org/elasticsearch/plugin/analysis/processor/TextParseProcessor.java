package org.elasticsearch.plugin.analysis.processor;

import org.elasticsearch.SpecialPermission;
import org.elasticsearch.ingest.AbstractProcessor;
import org.elasticsearch.ingest.ConfigurationUtils;
import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.Processor;
import org.elasticsearch.plugin.analysis.util.ContentTypeEnum;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.function.BiConsumer;

public class TextParseProcessor extends AbstractProcessor {

    public static final String TYPE = "text-parse-pipeline";
    private String field;
    private String target_field;
    private String parse_option;

    protected TextParseProcessor(String tag, String description,String field,String target_field,String parse_option) {
        super(tag, description);
        this.field = field;
        this.target_field = target_field;
        this.parse_option = parse_option;
    }
    public static final class Factory implements Processor.Factory{
        @Override
        public Processor create(Map<String, Processor.Factory> processorFactories, String tag, String description, Map<String, Object> config) throws Exception {
            String field = ConfigurationUtils.readStringProperty(TYPE,tag,config,"field");
            String targetField = ConfigurationUtils.readStringProperty(TYPE,tag,config,"target_field");
            String parse_option = ConfigurationUtils.readStringProperty(TYPE,tag,config,"parse_option");
            return new TextParseProcessor(tag,description,field,targetField,parse_option);
        }
    }


    @Override
    public void execute(IngestDocument ingestDocument, BiConsumer<IngestDocument, Exception> handler) {
        super.execute(ingestDocument, handler);
    }

    @Override
    public IngestDocument execute(IngestDocument ingestDocument) throws Exception {
        IngestDocument document = ingestDocument;
        SecurityManager sm = System.getSecurityManager();
        if(sm != null ){
            sm.checkPermission(new SpecialPermission());
        }
        AccessController.doPrivileged((PrivilegedAction<IngestDocument>) () ->{
            try{
                setFieldValue(document);
                return document;
            }catch (Exception e){
                return document;
            }
        });
        return document;
    }


    private void setFieldValue(IngestDocument document) {
        if(target_field.isEmpty()){
            document.setFieldValue(field,"");
        }else
        {
            document.setFieldValue(field,document.getFieldValue(field,String.class));
            document.setFieldValue(target_field, ContentTypeEnum.parseContentByContentType(parse_option,document.getFieldValue(field,String.class)));
        }
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
