package org.dubar.thymeleaf.extags.shiro.processor.attribute;

import org.dubar.thymeleaf.extags.shiro.ShiroAgent;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

public class AuthenticatedAttributeProcessor extends AbstractAttributeProcessor {
    public AuthenticatedAttributeProcessor(String dialectPrefix){
        super(dialectPrefix,"authenticated");
    }

    @Override
    protected void doProcess(
            ITemplateContext iTemplateContext,
            IProcessableElementTag iProcessableElementTag,
            AttributeName attributeName,
            String attributeValue,
            IElementTagStructureHandler iElementTagStructureHandler) {
        boolean authed = Boolean.valueOf(attributeValue);
        if (ShiroAgent.isAuthenticated() == authed){
            iElementTagStructureHandler.removeAttribute(attributeName);
        }else{
            iElementTagStructureHandler.removeElement();
        }
    }
}
