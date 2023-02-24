package com.productuer.testcase.controller;

import com.productuer.testcase.auth.TokenManager;
import com.productuer.testcase.inputs.LoginInput;
import com.productuer.testcase.inputs.UserInput;
import com.productuer.testcase.result.Result;
import com.productuer.testcase.service.CustomUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Slf4j
@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private CustomUserService customUserService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @MutationMapping
    @PreAuthorize("isAnonymous()")
    @Transactional
    public Result<String> login(@Argument @Valid LoginInput loginInput){
            if(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInput.getUsername(),loginInput.getPassword())) !=null){
                String token = tokenManager.generateToken(loginInput.getUsername());
                String userId = tokenManager.parseUserIdFromToken(token);

                logger.info(userId +" user login");

                return new Result<>("Success", true, token);
            }
            else{
                return new Result<>("Failure", false, null);
            }

    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public boolean logout(@Argument String token){

        if(tokenManager.tokenValidate(token)){
            String userId = tokenManager.parseUserIdFromToken(token);
            tokenManager.logout(token);

            logger.info(userId +" user logout");
            return true;
        }else{
            return false;
        }
    }

    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public Result<String> signUp(@Argument @Valid UserInput userInput){
        Result<String> result = customUserService.signUp(userInput);

        return new Result<>(result.getMessage(), result.isStatus(), result.getResult());
    }
}
