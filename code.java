import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.*;
import org.apache.commons.httpclient.methods.*;

import java.io.*;

public class DeployWarFile {

    public static void main(String[] args) throws Exception {
        String tomcatUrl = "http://localhost:8080/manager/text"; // replace with your Tomcat server URL
        String warFilePath = "path/to/sample.war"; // replace with the path to your WAR file
        String username = "admin"; // replace with your Tomcat server admin username
        String password = "password"; // replace with your Tomcat server admin password

        // create the HTTP client
        HttpClient client = new HttpClient();
        client.getParams().setAuthenticationPreemptive(true);
        Credentials defaultcreds = new UsernamePasswordCredentials(username, password);
        client.getState().setCredentials(AuthScope.ANY, defaultcreds);

        // create the HTTP POST request
        PostMethod post = new PostMethod(tomcatUrl);
        Part[] parts = {
            new StringPart("path", "/sample"),
            new FilePart("warfile", new File(warFilePath))
        };
        post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
        int status = client.executeMethod(post);

        // print the response
        System.out.println(post.getResponseBodyAsString());
    }

}

