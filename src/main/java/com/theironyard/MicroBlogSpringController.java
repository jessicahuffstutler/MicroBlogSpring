package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jessicahuffstutler on 11/9/15.
 */
@Controller
public class MicroBlogSpringController {
    @Autowired
    MicroBlogRepository messages;

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("messages", messages.findAll()); //we call findAll() because it's a list of things instead of the "messages" repository above.
        return "home";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        return "redirect:/";
    }


    @RequestMapping("add-message")
    public String addMessage(String message) {
        Message messageText = new Message();
        messageText.message = message;
        messages.save(messageText);
        return "redirect:/";
    }

    @RequestMapping("delete-message")
    public String deleteMessage(Integer id) {
        //Message messageText = messages.findOne(id);
        messages.delete(id);
        return "redirect:/";
    }

    @RequestMapping("edit-message")
    public String editMessage(Integer id, String message) {
        Message messageText = messages.findOne(id);
        messageText.message = message;
        if (message == "") {

        } else
        messages.save(messageText);
        return "redirect:/";
    }
}

//when deleting all data from database/pressing the delete button, it does not renumber
//database never reuses/repeats the same ID
//to view a 1,2,3,etc for "id" create a different variable to store that.
//when editing, it moves the edited line to the bottom, doesn't renumber, so the messages are out of order
//order by (asc/desc) would correct the numbering issue when editing.
