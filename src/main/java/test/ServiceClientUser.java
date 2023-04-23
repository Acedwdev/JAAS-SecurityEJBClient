
package test;

import co.com.acedwdev.sms.domain.User;
import co.com.acedwdev.sms.service.RemoteServiceUser;
import com.sun.enterprise.security.ee.auth.login.ProgrammaticLogin;
import java.util.List;
import javax.naming.*;


public class ServiceClientUser {
    public static void main(String[] args) {
        System.out.println("Starting EJB from Client");
        
        String authFile = "login.conf";
        System.setProperty("java.security.auth.login.config", authFile);
        ProgrammaticLogin programmaticLogin = new ProgrammaticLogin();
        programmaticLogin.login("admin", "admin".toCharArray());
        
        try {
        Context jndi = new InitialContext();
        RemoteServiceUser serviceUser = (RemoteServiceUser) jndi.lookup("java:global/sms-jakartaee-web/ImplServiceUser!co.com.acedwdev.sms.service.RemoteServiceUser");
        
        List<User> users = serviceUser.userList();
            
            for(User u : users){
                System.out.println("user:" + u);
            }
            System.out.println("\nEnd EJB from client");
        } catch (NamingException ex) {
           ex.printStackTrace(System.out);
        }
    }
}
