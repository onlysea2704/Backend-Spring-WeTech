package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.UserCardDto;
import com.wetech.backend_spring_wetech.entity.Address;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.entity.UserCard;
import com.wetech.backend_spring_wetech.repository.AddressRepository;
import com.wetech.backend_spring_wetech.repository.UserCardRepository;
import com.wetech.backend_spring_wetech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCardService {
    private final UserCardRepository userCardRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final AddressRepository addressRepository;

    public List<UserCard> getUserCard() {
        User user = userService.getCurrentUser();
        return userCardRepository.findByUserId(user.getUserId());
    }

    @Transactional
    public UserCard createUserCard(UserCardDto dto) {
        User user = userService.getCurrentUser();
        UserCard uc = new UserCard();
        uc.setEmail(user.getUsername());
        uc.setUserId(user.getUserId());
        mapToUserCard(dto, uc);

        return userCardRepository.save(uc);
    }

    @Transactional
    public UserCard updateUserCard(UserCardDto dto) {
        User user = userService.getCurrentUser();
        UserCard uc = userCardRepository.findByIdAndUserId(dto.getId(), user.getUserId());

        if (uc == null) {
            throw new RuntimeException("UserCard not found or does not belong to the user");
        }
        Long permanentAddressId = uc.getPermanentAddress() != null ? uc.getPermanentAddress().getId() : null;
        Long currentAddressId = uc.getCurrentAddress() != null ? uc.getCurrentAddress().getId() : null;
        if (permanentAddressId != null) {
            addressRepository.deleteById(permanentAddressId);
        }
        if (currentAddressId != null) {
            addressRepository.deleteById(currentAddressId);
        }

        mapToUserCard(dto, uc);

        return userCardRepository.save(uc);
    }

    @Transactional
    public void deleteUserCard(Long id) {
        User user = userService.getCurrentUser();
        UserCard uc = userCardRepository.findByIdAndUserId(id, user.getUserId());

        if (uc == null) {
            throw new RuntimeException("UserCard not found or does not belong to the user");
        }

        // Delete associated addresses if they exist
        if (uc.getPermanentAddress() != null) {
            addressRepository.deleteById(uc.getPermanentAddress().getId());
        }
        if (uc.getCurrentAddress() != null) {
            addressRepository.deleteById(uc.getCurrentAddress().getId());
        }

        userCardRepository.deleteById(id);
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
        } else {
            uc.setPermanentAddress(null);
        }

        if (dto.getCurrentStreet() != null && dto.getCurrentWard() != null && dto.getCurrentProvince() != null) {
            Address ca = Address.builder()
                    .street(dto.getCurrentStreet())
                    .ward(dto.getCurrentWard())
                    .province(dto.getCurrentProvince())
                    .build();
            uc.setCurrentAddress(ca);
        } else {
            uc.setCurrentAddress(null);
        }
    }
}
