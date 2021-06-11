package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    public PostController(PostRepository postDao){this.postDao = postDao;}

    @GetMapping(path = "/posts/delete/{id}")
    public String deletePost(@PathVariable Long id){
        postDao.deletePostById(id);
        return "redirect:/posts/index";
    }

    @GetMapping(path = "/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getById(id));
        return "posts/update";
    }

    @PostMapping(path = "/posts/edit/{id}")
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Post post = postDao.findById(id).get();
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping(path = "/posts")
    public String post(Model model) {
        model.addAttribute("postList", postDao.findAll());
        return "posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    public String postId(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getById(id));
        return "posts/show";
    }

    @GetMapping(path = "/posts/create")
    public String postsCreate(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String newPost(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts";
    }
}
