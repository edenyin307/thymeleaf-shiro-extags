package org.dubar.thymeleaf.extags.shiro.processor.element;

import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public abstract class AbstractElementProcessor extends AbstractElementTagProcessor {
    protected AbstractElementProcessor(String dialectPrefix, String elementName) {
        super(
                TemplateMode.HTML,
                dialectPrefix,
                elementName,
                true,
                null,
                false,
                300);
    }
}
