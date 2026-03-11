package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.UserCardDto;
import com.wetech.backend_spring_wetech.entity.Address;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.entity.UserCard;
import com.wetech.backend_spring_wetech.exception.ResourceNotFoundException;
import com.wetech.backend_spring_wetech.repository.UserCardRepository;
import com.wetech.backend_spring_wetech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCardService {
    private final UserCardRepository userCardRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public UserCard getUserCard() {
        User user = userService.getCurrentUser();
        return userCardRepository.findByUserId(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("UserCard not found for user"));
    }

    @Transactional
    public UserCard createUserCard(UserCardDto dto) {
        User user = userService.getCurrentUser();

        if (userCardRepository.existsByUserId(user.getUserId())) {
            throw new RuntimeException("UserCard already exists for user");
        }
        UserCard uc = new UserCard();
        uc.setEmail(user.getUsername());
        uc.setUserId(user.getUserId());
        mapToUserCard(dto, uc);

        return userCardRepository.save(uc);
    }

    @Transactional
    public UserCard updateUserCard(UserCardDto dto) {
        User user = userService.getCurrentUser();
        UserCard uc = userCardRepository.findByUserId(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("UserCard not found for user"));

        mapToUserCard(dto, uc);

        return userCardRepository.save(uc);
    }

    private void mapToUserCard(UserCardDto dto, UserCard uc) {
        uc.setFullName(dto.getFullName());
        uc.setCccd(dto.getCccd());
        uc.setEmail(uc.getEmail());
        uc.setGender(dto.getGender());
        uc.setDob(dto.getDob());
        uc.setNationality(dto.getNationality());
        uc.setEthnicity(dto.getEthnicity());
        uc.setUserId(uc.getUserId());

        if (dto.getPermanentStreet() != null && dto.getPermanentWard() != null && dto.getPermanentProvince() != null) {
            Address pa = Address.builder()
                    .street(dto.getPermanentStreet())
                    .ward(dto.getPermanentWard())
                    .province(dto.getPermanentProvince())
                    .build();
            uc.setPermanentAddress(pa);
        }
        if (dto.getCurrentStreet() != null && dto.getCurrentWard() != null && dto.getCurrentProvince() != null) {
            Address ca = Address.builder()
                    .street(dto.getCurrentStreet())
                    .ward(dto.getCurrentWard())
                    .province(dto.getCurrentProvince())
                    .build();
            uc.setCurrentAddress(ca);
        }
    }
}
