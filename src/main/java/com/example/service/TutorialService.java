package com.example.service;

import com.example.model.Tutorial;
import com.example.reposiotory.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {
    @Autowired
    private TutorialRepository tutorialRepository;

    public List<Tutorial> getTutorialsByUsername(String username) {
        return tutorialRepository.findByUsername(username);
    }

    public Tutorial createTutorial(Tutorial tutorial, String userName) {
        tutorial.setUsername(userName);
        return tutorialRepository.save(tutorial);
    }

    public Tutorial updateTutorial(Long id, Tutorial tutorial, String userName) {
        Tutorial existingTutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutorial not found"));

        if (!existingTutorial.getUsername().equals(userName)) {
            throw new RuntimeException("User is not authorized to update this tutorial");
        }
        existingTutorial.setTitle(tutorial.getTitle());
        existingTutorial.setDescription(tutorial.getDescription());
        return tutorialRepository.save(existingTutorial);
    }


    public void deleteTutorial(Long id, String userName) {
        Tutorial existingTutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutorial not found"));

        if (!existingTutorial.getUsername().equals(userName)) {
            throw new RuntimeException("User is not authorized to delete this tutorial");
        }
        tutorialRepository.deleteById(id);
    }

}
