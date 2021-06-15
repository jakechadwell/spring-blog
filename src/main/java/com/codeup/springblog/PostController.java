package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping(path = "/posts/delete/{id}")
    public String deletePost(@PathVariable Long id){
        postDao.deletePostById(id);
        return "redirect:/posts/index";
    }

    @GetMapping(path = "/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getById(id));
        return "posts/update";
    }

    @PostMapping(path = "/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post){
        Post newPost = postDao.getById(id);
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        postDao.save(newPost);
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
        User user = userDao.getById(1L);
        postDao.save(new Post(post.getTitle(), post.getBody(), user));
        return "redirect:/posts";
    }
}
