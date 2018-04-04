package org.dubar.thymeleaf.extags.shiro.processor.attribute;

import org.dubar.thymeleaf.extags.shiro.ShiroAgent;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

public class UserAttributeProcessor extends AbstractAttributeProcessor{
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String USER_PROPERTY_ATTRIBUTE_NAME = "property";
    private static final String USER_ATTRIBUTE_LACKED = "lacked";
    public UserAttributeProcessor(String dialectPrefix){
        super(dialectPrefix,USER_ATTRIBUTE_NAME);
    }

    @Override
    protected void doProcess(
            ITemplateContext context,
            IProcessableElementTag tag,
            AttributeName attributeName,
            String attributeValue,
            IElementTagStructureHandler structureHandler) {
        String[] users = attributeValue == null ? new String[0] : attributeValue.split(";");
        String propName = tag.getAttributeValue(USER_PROPERTY_ATTRIBUTE_NAME);
        String lackedValue = tag.getAttributeValue(USER_ATTRIBUTE_LACKED);
        boolean lacked = lackedValue == null || lackedValue.isEmpty() ? false : Boolean.valueOf(lackedValue);
        try {
            if (!ShiroAgent.UserMatched(users,propName,lacked)){
                structureHandler.removeElement();
            } else {
                structureHandler.removeAttribute(attributeName);
                structureHandler.removeAttribute(USER_PROPERTY_ATTRIBUTE_NAME);
                structureHandler.removeAttribute(USER_ATTRIBUTE_LACKED);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
