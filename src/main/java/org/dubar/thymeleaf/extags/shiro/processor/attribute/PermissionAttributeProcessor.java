package org.dubar.thymeleaf.extags.shiro.processor.attribute;

import org.dubar.thymeleaf.extags.shiro.MatchMode;
import org.dubar.thymeleaf.extags.shiro.ShiroAgent;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

public class PermissionAttributeProcessor extends AbstractAttributeProcessor {
    private static final String PERMISSION_MATCH_MODE_ATTRIBUTE_NAME = "mode";
    private static final String PERMISSION_ATTRIBUTE_NAME = "permission";
    public PermissionAttributeProcessor(String dialectPrefix){
        super(dialectPrefix,PERMISSION_ATTRIBUTE_NAME);
    }

    @Override
    protected void doProcess(
            ITemplateContext iTemplateContext,
            IProcessableElementTag iProcessableElementTag,
            AttributeName attributeName,
            String attributeValue,
            IElementTagStructureHandler iElementTagStructureHandler) {
        String[] permissions = attributeValue == null ? new String[0] : attributeValue.split(";");
        MatchMode mode = MatchMode.fromString(iProcessableElementTag.getAttributeValue(PERMISSION_MATCH_MODE_ATTRIBUTE_NAME));
        if (!ShiroAgent.PermissionMatched(permissions,mode)){
            iElementTagStructureHandler.removeElement();
        }else{
            iElementTagStructureHandler.removeAttribute(attributeName);
            iElementTagStructureHandler.removeAttribute(PERMISSION_MATCH_MODE_ATTRIBUTE_NAME);
        }
    }
}
