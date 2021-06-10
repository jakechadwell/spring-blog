package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
    @GetMapping(path = "/posts")
    public String post(Model model) {
        ArrayList<Post> postList = new ArrayList<>();
        Post post3 = new Post("Post3", "This is yet another new post.");
        Post post2 = new Post("Post2", "This is another new post.");
        postList.add(post2);
        postList.add(post3);
        model.addAttribute("postList", postList);
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postId(@PathVariable String id, Model model) {
        Post post = new Post("Post1", "This is a new post.");
        model.addAttribute("post", post);
        model.addAttribute("id", id);
        return "views post from " + id;
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String postsCreate() {
        return "view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String newPost() {
        return "create a new post";
    }
}
