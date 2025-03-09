package org.example.internintelligence_portfolio.controllers.maincontroller;

import org.example.internintelligence_portfolio.dtos.achievementsdtos.AchievementCreateDto;
import org.example.internintelligence_portfolio.dtos.achievementsdtos.AchievementDto;
import org.example.internintelligence_portfolio.dtos.achievementsdtos.AchievementUpdateDto;
import org.example.internintelligence_portfolio.models.Achievement;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.services.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievement")
public class AchievementController {
    @Autowired
    private AchievementService achievementService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> createAchievement(@ModelAttribute AchievementCreateDto achievementCreateDto){
        ApiResponse response = achievementService.createAchievement(achievementCreateDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AchievementDto>> getAchievements(){
        List<AchievementDto> response = achievementService.getAchievements();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/id")
    public AchievementDto getAchievementById(Long id){
        AchievementDto achievementDetail = achievementService.findAchievementById(id);
        return achievementDetail;
    }

    @PutMapping(value = "/id", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateAchievement(@RequestParam Long id, @ModelAttribute AchievementUpdateDto achievementUpdateDto){
        ApiResponse response = achievementService.updateAchievementById(id, achievementUpdateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id")
    public ResponseEntity<ApiResponse> deleteAchievement(@RequestParam Long id){
        ApiResponse response = achievementService.deleteAchievement(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
