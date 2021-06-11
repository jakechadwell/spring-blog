package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String roll(){
        return "roll";
    }

    @GetMapping("/roll-dice/{guess}")
    public String result(@PathVariable int guess, Model model) {
        int diceRoll = (int) ((Math.random()*((6-1)+1))+1);
        if(guess==diceRoll){
            model.addAttribute("correct", true);
        }else{
            model.addAttribute("incorrect", false);
        }
        model.addAttribute("userGuess", guess);
        model.addAttribute("diceRoll", diceRoll);
        return "result";
    }
}