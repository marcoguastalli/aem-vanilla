package com.aem.vanilla.core.models.components.htmlcomponent;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = Resource.class,
        resourceType = HtmlComponentModel.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HtmlComponentModel {
    public static final String RESOURCE_TYPE = "aemvanilla/components/htmlcomponent";

    @SlingObject
    private Resource resource;
    @ValueMapValue
    private String htmlCode;

    public String getHtmlCode() {
        return htmlCode;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(getHtmlCode());
    }

}
