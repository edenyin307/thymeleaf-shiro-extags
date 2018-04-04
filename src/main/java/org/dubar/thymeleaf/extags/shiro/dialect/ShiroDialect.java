package org.dubar.thymeleaf.extags.shiro.dialect;

import org.dubar.thymeleaf.extags.shiro.processor.attribute.AuthenticatedAttributeProcessor;
import org.dubar.thymeleaf.extags.shiro.processor.attribute.PermissionAttributeProcessor;
import org.dubar.thymeleaf.extags.shiro.processor.attribute.RoleAttributeProcessor;
import org.dubar.thymeleaf.extags.shiro.processor.attribute.UserAttributeProcessor;
import org.dubar.thymeleaf.extags.shiro.processor.element.PrincipalElementProcessor;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.LinkedHashSet;
import java.util.Set;

public class ShiroDialect extends AbstractProcessorDialect {
    private static final String NAME = "Shiro";
    private static final String PREFIX = "sh";
    public ShiroDialect(){
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        LinkedHashSet<IProcessor> processors = new LinkedHashSet<IProcessor>();

        processors.add(new AuthenticatedAttributeProcessor(dialectPrefix));
        processors.add(new PermissionAttributeProcessor(dialectPrefix));
        processors.add(new RoleAttributeProcessor(dialectPrefix));
        processors.add(new UserAttributeProcessor(dialectPrefix));

        processors.add(new PrincipalElementProcessor(dialectPrefix));

        return processors;
    }
}
