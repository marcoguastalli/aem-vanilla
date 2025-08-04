package com.aem.vanilla.core.models.components.link;

import com.adobe.cq.wcm.core.components.commons.link.LinkManager;
import com.aem.vanilla.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith({MockitoExtension.class, AemContextExtension.class})
class LinkModelTest {

    AemContext aemContext = AppAemContext.newAemContext(ResourceResolverType.JCR_MOCK);

    @BeforeEach
    void init() {
        aemContext.addModelsForClasses(LinkModel.class, LinkManager.class);
        aemContext.load().json("/com/aem/vanilla/core/models/components/link/link.json", "/content");
    }

    @Test
    void testModel() {
        // given
        final Resource resource = aemContext.resourceResolver().getResource("/content/aemvanilla/us/en/jcr:content/root/container/container/link");
        assertThat(resource, notNullValue());
        assertThat(resource.getResourceType(), is(LinkModel.RESOURCE_TYPE));
        aemContext.currentResource(resource);
        final LinkModel model = aemContext.request().adaptTo(LinkModel.class);
        // then
        assertThat(model, notNullValue());
        assertThat(model.getClass().getName(), equalTo(LinkModel.class.getName()));
        assertThat(model.getLinkURL(), is("/content/aemvanilla/language-masters/en"));
        assertThat(model.getLinkText(), is("reload"));
        assertThat(model.isEmpty(), is(Boolean.FALSE));
    }

}
