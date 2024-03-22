package com.example.service;

import com.example.model.Tutorial;
import com.example.reposiotory.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {

    @Autowired
    private TutorialRepository tutorialRepository;

    public Object getAttribute(Long id, String attributeName) {
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutorial not found"));
        return tutorial.getAttributes().get(attributeName);
    }

    public Tutorial createAttribute(Long id, String attributeName, Object attributeValue, String userName) {
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutorial not found"));

        if (!tutorial.getUsername().equals(userName)) {
            throw new RuntimeException("User is not authorized to update this tutorial");
        }
        tutorial.getAttributes().put(attributeName, attributeValue);
        return tutorialRepository.save(tutorial);
    }

    public Tutorial deleteAttribute(Long id, String attributeName, String userName) {
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutorial not found"));

        if (!tutorial.getUsername().equals(userName)) {
            throw new RuntimeException("User is not authorized to delete this tutorial");
        }
        tutorial.getAttributes().remove(attributeName);
        return tutorialRepository.save(tutorial);
    }
}
