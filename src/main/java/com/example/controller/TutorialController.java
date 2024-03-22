package com.example.controller;

import com.example.model.Tutorial;
import com.example.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {
    @Autowired
    private TutorialService tutorialService;

    @GetMapping
    public List<Tutorial> getAllTutorials(Principal principal) {
        return tutorialService.getTutorialsByUsername(principal.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tutorial createTutorial(@RequestBody Tutorial tutorial, @AuthenticationPrincipal Principal principal) {
        return tutorialService.createTutorial(tutorial, principal.getName());
    }

    @PutMapping("/{id}")
    public Tutorial updateTutorial(@PathVariable Long id, @RequestBody Tutorial tutorial, Principal principal) {
        return tutorialService.updateTutorial(id, tutorial, principal.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTutorial(@PathVariable Long id, Principal principal) {
        tutorialService.deleteTutorial(id, principal.getName());
    }
}
