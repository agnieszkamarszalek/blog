package com.amarszalek.blog_server.api.controllers;

import com.amarszalek.blog_server.domain.facades.SubscriptionFacade;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private SubscriptionFacade subscriptionFacade;

    @PostMapping(consumes="application/json")
    public ResponseEntity saveSubscription(@RequestBody SubscriptionDto subscriptionDto){
        return new ResponseEntity(subscriptionFacade.saveSubscription(subscriptionDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity deleteSubscription(@PathVariable long subscriptionId){
        subscriptionFacade.deleteSubscription(subscriptionId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
