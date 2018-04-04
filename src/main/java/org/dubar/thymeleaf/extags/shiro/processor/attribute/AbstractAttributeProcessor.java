package org.dubar.thymeleaf.extags.shiro.processor.attribute;

import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public abstract class AbstractAttributeProcessor extends AbstractAttributeTagProcessor {
    protected AbstractAttributeProcessor(String dialectPrefix, String attributeName) {
        super(
                TemplateMode.HTML,
                dialectPrefix,
                null,
                false,
                attributeName,
                true,
                300,
                true);
    }
}
