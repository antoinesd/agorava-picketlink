/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.agorava.picketlink;


import org.agorava.api.oauth.OAuthSession;
import org.agorava.api.service.OAuthLifeCycleService;
import org.agorava.spi.UserProfile;
import org.apache.deltaspike.servlet.api.Web;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.credential.Credentials.Status;
import org.picketlink.idm.model.basic.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ApplicationScoped
@PicketLink
public class AgoravaAuthenticator extends BaseAuthenticator
{

   @Inject
   DefaultLoginCredentials credentials;


   @Inject
   @Web
   Instance<HttpServletResponse> response;


    @Inject
    OAuthLifeCycleService lifeCycleService;

   // @Inject
   // private transient ProfileService profileService;

   @Override
   public void authenticate()
   {

      if (lifeCycleService.getCurrentSession().isConnected())
      {


          OAuthSession session =lifeCycleService.getCurrentSession();
         UserProfile userProfile = session.getUserProfile();
         credentials.setCredential(session.getAccessToken());
         setStatus(AuthenticationStatus.SUCCESS);

//         LinkedInProfileFull userProfileFull = profileService.getUserProfileFull();
         User user = new User(userProfile.getId());
         user.setFirstName(userProfile.getFullName());
         setAccount(user);
      }
      else
      {

         String authorizationUrl = lifeCycleService.startDanceFor("LinkedIn");
         try
         {
            response.get().sendRedirect(authorizationUrl);
         }
         catch (IOException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         credentials.setStatus(Status.IN_PROGRESS);
         setStatus(AuthenticationStatus.DEFERRED);
      }
   }
}
