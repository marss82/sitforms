package cz.fel.cvut.sitforms.service.impl;

import cz.fel.cvut.sitforms.dto.*;
import cz.fel.cvut.sitforms.dto.fillingPrivateFormDto.AnswerQuestionRequestDto;
import cz.fel.cvut.sitforms.dto.fillingPrivateFormDto.AnswerVariantRequestDto;
import cz.fel.cvut.sitforms.dto.fillingPrivateFormDto.AnswersResponseDto;
import cz.fel.cvut.sitforms.dto.fillingPrivateFormDto.SendFormDto;
import cz.fel.cvut.sitforms.dto.fillingPublicFormDto.PublicFormDto;
import cz.fel.cvut.sitforms.mapper.FormMapper;
import cz.fel.cvut.sitforms.mapper.UserMapper;
import cz.fel.cvut.sitforms.model.*;
import cz.fel.cvut.sitforms.repository.*;
import cz.fel.cvut.sitforms.service.FormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final VariantRepository variantRepository;
    private final FormMapper formMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final SubmitFormRepository submitFormRepository;

    private final QuestionAnswerRepository questionAnswerRepository;

    private final VariantAnswerRepository variantAnswerRepository;
    
    private final UserFormFilledRepository userFormFilledRepository;

    @Override
    public FormResponseDto getForm(Long formId) {
        final FormEntity formEntity = formRepository.findById(formId).orElseThrow(() -> new EntityNotFoundException(("Form not found")));
        List<UserFormsFilled> allByForm = userFormFilledRepository.findAllByForm(formEntity);
        FormResponseDto formResponseDto = formMapper.toResponse(formEntity);
        List<UserResponseDto> formUsers = new ArrayList<>();
        for (UserFormsFilled user : allByForm) {
            Long id = user.getUser().getId();
            UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User doesnt exist"));
            UserResponseDto userResponseDto = userMapper.toResponse(userEntity);
            formUsers.add(userResponseDto);
        }
        formResponseDto.setFormUsers(formUsers);
        return formResponseDto;
    }
    @Override
    public FormResponseDto createForm(FormRequestDto formRequestDto) {
        FormEntity formEntity = formMapper.toEntity(formRequestDto);
        Long authorId = formRequestDto.getAuthorId();
        UserEntity author = userRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("User doesnt exist"));
        formEntity.setUser(author);
        formEntity.getQuestionEntities().forEach(q -> {
            q.setFormEntity(formEntity);
            q.getVariantEntityList().forEach(variantEntity -> variantEntity.setQuestion(q));
        });
        List<FormUserRequestDto> formUsers = formRequestDto.getAssignedUsers();
        if (formUsers != null) {
            for (FormUserRequestDto userDto : formUsers) {
                log.info("User dto {}", userDto);
                UserEntity user = userRepository.findById(userDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

                UserFormsFilled userFormsFilled = new UserFormsFilled();

                userFormsFilled.setUser(user);
                userFormsFilled.setForm(formEntity);
                userFormsFilled.setFilled(false);

                userFormFilledRepository.save(userFormsFilled);
            }
        }
        FormEntity save = formRepository.save(formEntity);
        return formMapper.toResponse(save);
    }

    @Override
    public void fillPublicForm(PublicFormDto publicFormDto) {
        Long formId = publicFormDto.getFormId();
        final FormEntity formEntity = formRepository.findById(formId).orElseThrow(() -> new EntityNotFoundException(("Form not found")));
        publicFormDto.getAnswers().forEach(answer -> {
            final QuestionEntity questionEntity = questionRepository.findById(answer.getChosenQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException(("Question not found")));
            if (!formEntity.getQuestionEntities().contains(questionEntity)){
                throw new RuntimeException("Form doesnt contains such question!");
            }
            List<AnswerVariantRequestDto> chosenVariants = answer.getChosenVariants();
            chosenVariants.forEach(variant -> {
                final VariantEntity variantEntity = variantRepository.findById(variant.getChosenVariantId())
                        .orElseThrow(() -> new EntityNotFoundException("Variant doesnt exist"));
                variantEntity.setClickNumbers(variantEntity.getClickNumbers() + 1);
                variantRepository.save(variantEntity);
            });
            questionEntity.setTotalClickNumbers(questionEntity.getTotalClickNumbers() + chosenVariants.size());
            questionRepository.save(questionEntity);
        });
    }

    @Override
    @Transactional
    public void fillPrivateForm(SendFormDto sendFormDto) {
        Long formId = sendFormDto.getFormId();
        Long userId = sendFormDto.getUserId();
        List<AnswerQuestionRequestDto> questionAnswers = sendFormDto.getQuestionAnswers();

        final FormEntity formEntity = formRepository.findById(formId).orElseThrow(() -> new EntityNotFoundException(("Form not found")));
        final UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(("User not found")));

        SubmitForm submitForm = new SubmitForm();
        submitForm.setFormEntity(formEntity);
        submitForm.setSender(user);

        Set<Long> userVariants = new HashSet<>();

        for (AnswerQuestionRequestDto questionAnswer : questionAnswers) {
            List<AnswerVariantRequestDto> chosenVariants = questionAnswer.getChosenVariants();
            chosenVariants.forEach(chosenVariant -> {
                userVariants.add(chosenVariant.getChosenVariantId());
            });
        }

        SubmitForm submitFormSaved = submitFormRepository.save(submitForm);

        questionAnswers.forEach(answerQuestionRequestDto -> {
            QuestionAnswer questionAnswer = new QuestionAnswer();
            final QuestionEntity questionEntity = questionRepository.findById(answerQuestionRequestDto.getChosenQuestionId()).orElseThrow(() -> new EntityNotFoundException(("Question not found")));

            List<VariantEntity> variantEntityList = questionEntity.getVariantEntityList();
            questionAnswer.setName(questionEntity.getName());
            questionAnswer.setQuestionType(questionEntity.getQuestionType());
            questionAnswer.setSubmitForm(submitFormSaved);
            QuestionAnswer savedQuestionAnswer = questionAnswerRepository.save(questionAnswer);
            variantEntityList.stream().forEach(variantEntity -> {
                final VariantEntity variant = variantRepository.findById(variantEntity.getId()).orElseThrow(() -> new EntityNotFoundException(("User not found")));
                VariantAnswer variantAnswer = new VariantAnswer();
                variantAnswer.setName(variant.getName());
                variantAnswer.setAnsweredVariant(userVariants.contains(variant.getId()));
                variantAnswer.setQuestionAnswer(savedQuestionAnswer);
                VariantAnswer savedVariantAnswer = variantAnswerRepository.save(variantAnswer);
                questionAnswer.getVariantAnswers().add(savedVariantAnswer);
            });
            submitFormSaved.getQuestionAnswers().add(questionAnswer);
        });
    }

    @Override
    public List<FormResponseDto> getPrivateForms(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<UserFormsFilled> allByUser = userFormFilledRepository.findAllByUser(user);
        List<FormResponseDto> formResponseDtos = allByUser.stream().map(userFormsFilled -> {
            FormEntity form = userFormsFilled.getForm();
            FormResponseDto formResponseDto = formMapper.toResponse(form);
            return formResponseDto;
        }).collect(Collectors.toList());
        return formResponseDtos;
        // get all usersFilled
        //all user filled for each if
        // form repo find by id
        // form mapper to response
        // in for each add new list {form responce Dto}

    }


    @Override
    public List<FormResponseDto> getAllPublicForms() {
        List<FormEntity> forms = formRepository.findByFormType(FormType.PUBLIC);
        List<FormResponseDto> formResponses = new ArrayList<>();
        for(FormEntity form : forms){
            formResponses.add(formMapper.toResponse(form));
        }
        return formResponses;
    }



    @Override
    public StatisticsResponseDto getFormStatistics(Long formId, Long userId) {
        final FormEntity formEntity = formRepository.findById(formId).orElseThrow(() -> new EntityNotFoundException(("Form not found")));
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!formEntity.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("User is not form's author");
        }
        return formMapper.toStatisticsResponse(formEntity);
    }

    @Override
    public List<FormResponseDto> getManagedForms(Long authorId) {
        List<FormEntity> byUserId = formRepository.findByUserId(authorId);
        return byUserId.stream().map(formMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public QuestionsResponseDto getFormQuestions(Long formId) {
        final FormEntity formEntity = formRepository.findById(formId).orElseThrow(() -> new EntityNotFoundException(("Form not found")));
        return formMapper.toQuestions(formEntity);
    }

    @Override
    @Transactional
    public void increaseViewsCount(Long formId) {
        log.info("Increasing views count of form {}", formId);
        final FormEntity formEntity = formRepository.findById(formId).orElseThrow(() -> new EntityNotFoundException(("Form not found")));
        formEntity.setViewsCount(formEntity.getViewsCount() + 1);
    }

    @Override
    public AnswersResponseDto getFormAnswers(Long formId, Long userId) {

        // get submit form by id

        return null;
    }

    @Override
    public List<FormResponseDto> getFormsByName(String formName) {
        return null;
    }


}
