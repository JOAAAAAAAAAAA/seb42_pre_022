package com.teambj.stackoverflow.auth.service;

import com.teambj.stackoverflow.auth.PrincipalDetails;
import com.teambj.stackoverflow.domain.user.entity.User;
import com.teambj.stackoverflow.domain.user.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public OAuth2UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);


        String email = String.valueOf(oAuth2User.getAttributes().get("email"));
        String password = String.valueOf(oAuth2User.getAttributes().get("password"));

        Optional<User> findUser = userRepository.findByEmail(email);
        User user = findUser.orElseGet(() -> new User(email, password, true));

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }


}
