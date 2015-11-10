package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by jessicahuffstutler on 11/9/15.
 */
//Create a controller class next to the main application class
@Controller //this is how spring finds this controller
public class MicroBlogSpringController {
    //Create an ArrayList<Message> in your controller to store submitted messages
    ArrayList<Message> messages = new ArrayList();

    //In your controller, create a route for /
    @RequestMapping("/")
    //It should take the model and the request as arguments
    public String home(Model model, HttpServletRequest request) {
        //It should read the username from the session and add it to the model
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username"); //casting because of the way the get attribute works;
        // the username is null when we initially land on this page.
        model.addAttribute("username", username);
        model.addAttribute("messages", messages);
        //It should return the home template
        return "home";
    }

    //In your controller, create a route for /login
    @RequestMapping("/login")
    //It should take the request and the username as arguments
    public String login(HttpServletRequest request, String username) { //what you provide in the "name" attribute in html should match this username exacty
//      //It should save the username to the session
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        //It should return a redirect to /
        return "redirect:/";
    }

    //In your controller, create a route for /add-message
    @RequestMapping("/add-message")
    //It should take the message text as an argument
    public String addMessage(@RequestParam("message") String text) { //if the "name" attribute in html said "text" (in the add message form) instead of message, we wouldnt need "@RequestParam("message")"
        int id = messages.size() + 1;
        //It should create a Message object and add it to the arraylist (for the id, do something like messages.size() + 1)
        Message message = new Message(text, id); //could have (instead of "id" -> "message.size() + 1" and we could remove the line above.
        messages.add(message);
        //It should return a redirect to /
        return "redirect:/";
    }

    //In your controller, create a route for /delete-message
    @RequestMapping("/delete-message")
    //It should take the message id as an argument (the type should be Integer)
    public String deleteMessage(Integer id) {
        //It should remove the message with the given id (do something like messages.remove(id - 1))
        messages.remove(id - 1);
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).id = i + 1;
        }
        //below is an alternative to my for loop above:
        //int newID = 1;
        //for (Message message : messages) {
        //message id = newId;
        //newId++:

        //It should return a redirect to /
        return "redirect:/";
    }
}
