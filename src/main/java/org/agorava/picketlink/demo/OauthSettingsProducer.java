package org.agorava.picketlink.demo;

import org.agorava.api.oauth.application.OAuthAppSettings;
import org.agorava.api.oauth.application.OAuthAppSettingsBuilder;
import org.agorava.api.oauth.application.OAuthApplication;
import org.agorava.api.oauth.application.Param;
import org.agorava.linkedin.LinkedIn;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

;

/**
 * Configures Agorava with the needed producers
 *
 * @author <a href="mailto:gegastaldi@gmail.com">George Gastaldi</a>
 */
public class OauthSettingsProducer {

    @ApplicationScoped
    @Produces
    @LinkedIn
    @OAuthApplication(params = {@Param(name = OAuthAppSettingsBuilder.PREFIX, value = "linkedin")})
    protected OAuthAppSettings linkedInSettings;


}
