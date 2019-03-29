package com.amarszalek.blog_server.domain.facades;

import com.amarszalek.blog_server.domain.exceptions.EntityAlreadyExist;
import com.amarszalek.blog_server.domain.exceptions.EntityCouldNotBeFoundException;
import com.amarszalek.blog_server.domain.exceptions.EntityNotCreatedException;
import com.amarszalek.blog_server.domain.models.Subscription;
import com.amarszalek.blog_server.domain.repositories.SubscriptionRepository;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionDto;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionIdDto;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.modelmapper.ModelMapper;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SubscriptionFacade {
    SubscriptionRepository subscriptionRepository;
    ModelMapper modelMapper;

    public SubscriptionIdDto saveSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscription = modelMapper.map(subscriptionDto, Subscription.class);
        if (subscriptionAlreadyExists(subscription)) {
            throw new EntityAlreadyExist("Subscription with user id " + subscriptionDto.getUserId()
                    + " and author user name " + subscriptionDto.getAuthorUserName() + " already exists.");
        }
        try {
            Subscription savedSubscription = subscriptionRepository.save(subscription);
            SubscriptionIdDto subscriptionIdDto = new SubscriptionIdDto();
            subscriptionIdDto.setSubscriptionId(savedSubscription.getId());
            return subscriptionIdDto;
        } catch (ConstraintViolationException e) {
            Object[] objects = e.getConstraintViolations().toArray();
            String exceptionMessage = ((ConstraintViolationImpl) objects[0]).getMessage();
            throw new EntityNotCreatedException(exceptionMessage);
        } catch (Exception e) {
            throw new EntityNotCreatedException("Subscription not created");
        }
    }

    private boolean subscriptionAlreadyExists(Subscription subscription) {
        return subscriptionRepository
                .findByUserIdAndAndAuthorUserName(subscription.getUserId(), subscription.getAuthorUserName()).isPresent();
    }

    public void deleteSubscription(long subscriptionId) {
        try {
            subscriptionRepository.deleteById(subscriptionId);
        } catch (Exception e) {
            throw new EntityCouldNotBeFoundException("Entity wasn't deleted because it doesn't exist.");
        }
    }

    List<String> findAllSubscribed(String authorUserName) {
        List<String> subscribedUserId = new ArrayList<>();
        Optional<List<Subscription>> subscriptionListOptional = subscriptionRepository.findByAuthorUserName(authorUserName);
        if (subscriptionListOptional.isPresent()) {
            List<Subscription> subscriptionList = subscriptionListOptional.get();
            subscribedUserId = subscriptionList.stream()
                    .map(Subscription::getEmailAddress)
                    .collect(Collectors.toList());
        }
        return subscribedUserId;
    }
}
