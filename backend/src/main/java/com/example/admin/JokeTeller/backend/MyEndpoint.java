/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.admin.JokeTeller.backend;

import com.example.JokeProvider;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.JokeTeller.admin.example.com",
                ownerName = "backend.JokeTeller.admin.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "retrieveJokes")
    public MyBean retrieveJokes() {

        MyBean response = new MyBean();
        JokeProvider jokeProvider=new JokeProvider();
        String jokes=jokeProvider.getJoke();
        response.setData("Hi, "+jokes);

        return response;
    }

}
