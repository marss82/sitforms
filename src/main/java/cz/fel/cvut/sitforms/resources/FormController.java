package cz.fel.cvut.sitforms.resources;

import cz.fel.cvut.sitforms.dto.FormRequestDto;
import cz.fel.cvut.sitforms.dto.FormResponseDto;
import cz.fel.cvut.sitforms.dto.QuestionsResponseDto;
import cz.fel.cvut.sitforms.dto.StatisticsResponseDto;
import cz.fel.cvut.sitforms.dto.fillingPrivateFormDto.AnswersResponseDto;
import cz.fel.cvut.sitforms.dto.fillingPrivateFormDto.SendFormDto;
import cz.fel.cvut.sitforms.dto.fillingPublicFormDto.PublicFormDto;
import cz.fel.cvut.sitforms.service.FormService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/forms")
public class FormController {
    private final AuthenticationManager authenticationManager;
    private final FormService formService;
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public FormResponseDto createForm(@RequestBody FormRequestDto formRequestDto) {
        return formService.createForm(formRequestDto);
    }

    @GetMapping("/private/{userId}")
    public List<FormResponseDto> getPrivateForms(@PathVariable Long userId){
        return formService.getPrivateForms(userId);
    }

    @GetMapping("/public")
    public List<FormResponseDto> getPublicForms(){
        return formService.getAllPublicForms();
    }


    @GetMapping({"{formId}"})
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public FormResponseDto getForm(@PathVariable Long formId) {
        return formService.getForm(formId);
    }

    @PostMapping("/fill/private")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public void fillPrivateForm(@RequestBody SendFormDto sendFormDto) {
        formService.fillPrivateForm(sendFormDto);
    }

    @PostMapping("/fill/public")
    public void fillPublicForm(@RequestBody PublicFormDto publicFormDto) {
        formService.fillPublicForm(publicFormDto);
    }

    @GetMapping("/{formId}/statistics/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public StatisticsResponseDto getFormStatistics(@PathVariable Long formId, @PathVariable Long userId) {
        return formService.getFormStatistics(formId, userId);
    }

    @GetMapping("/{formId}/questions")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public QuestionsResponseDto getFormQuestions(@PathVariable Long formId) {
        return formService.getFormQuestions(formId);
    }

    @GetMapping("/{formId}/answers/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public AnswersResponseDto getFormAnswers(@PathVariable Long formId, @PathVariable Long userId) {
        return formService.getFormAnswers(formId, userId);
    }

    @GetMapping("/managed/{authorId}")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public List<FormResponseDto> getManagedForms(@PathVariable Long authorId) {
        return formService.getManagedForms(authorId);
    }

    @GetMapping("/{formId}/statistics/views")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public void increaseViewsCount(@PathVariable Long formId) {
        formService.increaseViewsCount(formId);
    }


}
