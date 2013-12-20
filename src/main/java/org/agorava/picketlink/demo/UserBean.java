/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.agorava.picketlink.demo;

import org.picketlink.Identity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserBean implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Inject
   Identity identity;

   public void login()
   {
      if (!identity.isLoggedIn())
      {
         System.out.println("Authentication Result: " + identity.login());
      }
      else
      {
         System.out.println("AGENT: " + identity.getAccount());
      }
   }
}
