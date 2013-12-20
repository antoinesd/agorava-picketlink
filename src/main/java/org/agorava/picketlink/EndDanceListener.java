package org.agorava.picketlink;

import org.agorava.api.event.OAuthComplete;
import org.agorava.linkedin.LinkedIn;
import org.picketlink.Identity;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * @author Antoine Sabot-Durand
 */
public class EndDanceListener {

    @Inject
    Identity identity;

    protected void listenEndDance(@Observes @LinkedIn OAuthComplete complete) {

        Identity.AuthenticationResult result = identity.login();
        System.out.println("RESULT: " + result);

    }
}
