package edu.du.campaign_pyl.controller;

import edu.du.campaign_pyl.entity.Application;
import edu.du.campaign_pyl.entity.Users;
import edu.du.campaign_pyl.service.ApplicationService;
import edu.du.campaign_pyl.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/application")
public class ApplicationRestController {

    private final ApplicationService applicationService;
    private final UserService userService;

    public ApplicationRestController(ApplicationService applicationService, UserService userService) {
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @GetMapping("/users/select/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        Users users = userService.getUserByUserNo(id);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/insert")
    public ResponseEntity<String> insertApplication(@RequestBody Application application) {
        applicationService.save(application);
        return ResponseEntity.ok("신청 완료되었습니다.");
    }
}
