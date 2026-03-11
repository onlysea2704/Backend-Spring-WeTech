package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.UserCardDto;
import com.wetech.backend_spring_wetech.entity.UserCard;
import com.wetech.backend_spring_wetech.service.UserCardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/users/my-card")
public class UserCardController {

    private final UserCardService userCardService;

    public UserCardController(UserCardService userCardService) {
        this.userCardService = userCardService;
    }

    @GetMapping("/get")
    public ResponseEntity<UserCard> getUserCard() {
        UserCard dto = userCardService.getUserCard();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<UserCard> createUserCard(@Valid @RequestBody UserCardDto dto) {
        UserCard created = userCardService.createUserCard(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PostMapping("/update")
    public ResponseEntity<UserCard> updateUserCard(@Valid @RequestBody UserCardDto dto) {
        UserCard updated = userCardService.updateUserCard(dto);
        return ResponseEntity.ok(updated);
    }
}

