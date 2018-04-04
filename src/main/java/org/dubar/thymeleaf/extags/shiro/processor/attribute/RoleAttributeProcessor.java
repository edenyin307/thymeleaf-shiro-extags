package org.dubar.thymeleaf.extags.shiro.processor.attribute;

import org.dubar.thymeleaf.extags.shiro.MatchMode;
import org.dubar.thymeleaf.extags.shiro.ShiroAgent;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

public class RoleAttributeProcessor extends AbstractAttributeProcessor{
    private static final String ROLE_MATCH_MODE_ATTRIBUTE_NAME = "mode";
    private static final String ROLE_ATTRIBUTE_NAME = "role";
    public RoleAttributeProcessor(String dialectPrefix){
        super(dialectPrefix, ROLE_ATTRIBUTE_NAME);
    }

    @Override
    protected void doProcess(
            ITemplateContext context,
            IProcessableElementTag tag,
            AttributeName attributeName,
            String attributeValue,
            IElementTagStructureHandler structureHandler) {
        String[] roles = attributeValue == null ? new String[0] : attributeValue.split(";");
        MatchMode mode = MatchMode.fromString(tag.getAttributeValue(ROLE_MATCH_MODE_ATTRIBUTE_NAME));
        if (!ShiroAgent.RoleMatched(roles,mode)){
            structureHandler.removeElement();
        } else {
            structureHandler.removeAttribute(attributeName);
            structureHandler.removeAttribute(ROLE_MATCH_MODE_ATTRIBUTE_NAME);
        }
    }
}
