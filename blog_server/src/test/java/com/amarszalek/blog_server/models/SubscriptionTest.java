package com.amarszalek.blog_server.models;

import com.amarszalek.blog_server.domain.models.Subscription;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class SubscriptionTest {
    private static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void  whenEmptyUserIdThenOneConstraintViolation() {
        //given
        Subscription subscriptionWithEmptyUserId = new Subscription();
        subscriptionWithEmptyUserId.setAuthorUserName("aga");
        //when
        Set<ConstraintViolation<Subscription>> emptyUserIdViolation =
                validator.validate(subscriptionWithEmptyUserId);
        //then
        Assert.assertEquals(emptyUserIdViolation.size(), 1);
    }

    @Test
    public void  whenEmptyAuthorUserNameThenOneConstraintViolation() {
        //given
        Subscription subscriptionWithEmptyUserId = new Subscription();
        subscriptionWithEmptyUserId.setUserId("1");
        //when
        Set<ConstraintViolation<Subscription>> emptyUserIdViolation =
                validator.validate(subscriptionWithEmptyUserId);
        //then
        Assert.assertEquals(emptyUserIdViolation.size(), 1);
    }
}