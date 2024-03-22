package com.example.controller;

import com.example.model.Tutorial;
import com.example.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/tutorials/{id}/attributes/{attributeName}")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @GetMapping
    public Object getAttribute(@PathVariable Long id, @PathVariable String attributeName) {
        return attributeService.getAttribute(id, attributeName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tutorial createAttribute(@PathVariable Long id,
                                    @PathVariable String attributeName,
                                    @RequestBody Object attributeValue,
                                    Principal principal
    ) {
        return attributeService.createAttribute(id, attributeName, attributeValue, principal.getName());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttribute(@PathVariable Long id, @PathVariable String attributeName, Principal principal) {
        attributeService.deleteAttribute(id, attributeName, principal.getName());
    }
}
