package org.dubar.thymeleaf.extags.shiro.processor.element;

import org.dubar.thymeleaf.extags.shiro.ShiroAgent;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

public class PrincipalElementProcessor extends AbstractElementProcessor{
    private static final String PRINCIPAL_PROPERTY_ATTRIBUTE_NAME = "property";
    private static final String PRINCIPAL_ELEMENT_NAME = "principal";
    public PrincipalElementProcessor(String dialectPrefix){
        super(dialectPrefix,PRINCIPAL_ELEMENT_NAME);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
        String propertyName = tag.getAttributeValue(PRINCIPAL_PROPERTY_ATTRIBUTE_NAME);
        String authInfo = null;
        try {
            authInfo = ShiroAgent.getPrincipalInfo(propertyName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        structureHandler.replaceWith(authInfo,false);
    }
}
