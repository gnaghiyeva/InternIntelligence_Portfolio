package org.example.internintelligence_portfolio.controllers.maincontroller;

import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillCreateDto;
import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillDto;
import org.example.internintelligence_portfolio.dtos.skillsdtos.SkillUpdateDto;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> createSkill(@ModelAttribute SkillCreateDto skillCreateDto){
        ApiResponse response = skillService.addSkill(skillCreateDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SkillDto>> getSkills(){
        List<SkillDto> response = skillService.getAllSkills();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/id")
    public SkillDto getSkillById(@RequestParam int id){
        SkillDto skillDetail = skillService.getSkillById(id);
        return skillDetail;
    }

    @PutMapping(value = "/id",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<ApiResponse> updateSkill(@RequestParam Integer id, @ModelAttribute SkillUpdateDto skillUpdateDto){
        ApiResponse response = skillService.updateSkill(id, skillUpdateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id")
    public ResponseEntity<ApiResponse> deleteSkill(@RequestParam Integer id){
        ApiResponse response = skillService.deleteSkill(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
