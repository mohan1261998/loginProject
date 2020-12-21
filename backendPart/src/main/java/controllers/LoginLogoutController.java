/**
 * Copyright (C) 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.params.PathParam;
import ninja.session.Session;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import models.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import dao.EventDao;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.io.IOUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.UserDao;

import models.UserDto;


@Singleton
public class LoginLogoutController {
    
	public long cuser;
	public static long temp;
    @Inject
    UserDao userDao;
    @Inject 
    EventDao eventDao;
   
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Login
 

public Result login(@Param("username") String username,
                            @Param("password") String password,
                            @Param("rememberMe") Boolean rememberMe,
                            Context context) {

    boolean isUserNameAndPasswordValid = userDao.isUserAndPasswordValid(username, password);
        
    if (isUserNameAndPasswordValid) {
        User user=  eventDao.currentuser(username);  
       	this.cuser=user.id;
       	temp=this.cuser;
           
        Session session = context.getSession();
        session.put("username", username);

        if (rememberMe != null && rememberMe) {
            session.setExpiryTime(5*60*60*1000L);
        }
        else {
            session.setExpiryTime(5*60*1000L);
        }
            
        context.getFlashScope().success("login.loginSuccessful");
          //  "login.loginSuccessful"
        return Results.ok().render(true);

        } else {

            // something is wrong with the input or password not found.
            context.getFlashScope().put("username", username);
            context.getFlashScope().put("rememberMe", String.valueOf(rememberMe));
            context.getFlashScope().error("login.errorLogin");

            return Results.badRequest();

        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // Logout
    ///////////////////////////////////////////////////////////////////////////
    public Result logout(Context context) {

        // remove any user dependent information
        context.getSession().clear();
        context.getFlashScope().success("login.logoutSuccessful");
        temp=0L;

        return Results.ok();

    }

    public Result forgotPassword(@Param("username") String username) {

        userDao.forgotPassword(username);

        return Results.ok().render(true);

    }

    

    
}
    
