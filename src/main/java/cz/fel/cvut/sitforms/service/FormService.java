package cz.fel.cvut.sitforms.service;

import cz.fel.cvut.sitforms.dto.FormRequestDto;
import cz.fel.cvut.sitforms.dto.FormResponseDto;
import cz.fel.cvut.sitforms.dto.QuestionsResponseDto;
import cz.fel.cvut.sitforms.dto.StatisticsResponseDto;
import cz.fel.cvut.sitforms.dto.fillingPrivateFormDto.AnswersResponseDto;
import cz.fel.cvut.sitforms.dto.fillingPrivateFormDto.SendFormDto;
import cz.fel.cvut.sitforms.dto.fillingPublicFormDto.PublicFormDto;

import java.util.List;

public interface FormService {

    FormResponseDto createForm(FormRequestDto formRequestDto);

    void fillPublicForm(PublicFormDto publicFormDto);

    FormResponseDto getForm(Long formId);

    void fillPrivateForm(SendFormDto sendFormDto);

    StatisticsResponseDto getFormStatistics(Long formId, Long userId);

    List<FormResponseDto> getManagedForms(Long authorId);

    QuestionsResponseDto getFormQuestions(Long formId);

    void increaseViewsCount(Long formId);

    AnswersResponseDto getFormAnswers(Long formId, Long userId);

    List<FormResponseDto> getFormsByName(String formName);
    List<FormResponseDto> getPrivateForms(Long usedId);

    List<FormResponseDto> getAllPublicForms();




}
