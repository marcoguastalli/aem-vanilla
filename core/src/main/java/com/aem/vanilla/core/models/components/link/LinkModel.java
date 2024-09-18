package com.aem.vanilla.core.models.components.link;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.commons.link.Link;
import com.adobe.cq.wcm.core.components.commons.link.LinkManager;
import com.adobe.cq.wcm.core.components.util.AbstractComponentImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

import static com.aem.vanilla.core.CoreConstants.DOT;
import static com.aem.vanilla.core.CoreConstants.HTML_EXTENSION;


@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {LinkModel.class, ComponentExporter.class},
        resourceType = LinkModel.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class LinkModel extends AbstractComponentImpl {

    public static final String RESOURCE_TYPE = "aemvanilla/components/link";

    @Self
    private SlingHttpServletRequest request;
    @ValueMapValue
    private String linkURL;
    @ValueMapValue
    private String linkText;

    @Self
    private LinkManager linkManager;
    protected Link link;

    @PostConstruct
    private void initModel() {
        link = this.linkManager.get(this.resource).build();
    }

    public String getLinkUrl() {
        if (null != link && link.isValid()) {
            return link.getURL();
        }
        return linkURL.concat(DOT).concat(HTML_EXTENSION);
    }

    public String getLinkText() {
        return linkText;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(getLinkText());
    }
}
