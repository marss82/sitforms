package cz.fel.cvut.sitforms.service.impl;


import cz.fel.cvut.sitforms.dto.*;
import cz.fel.cvut.sitforms.mapper.FormMapper;
import cz.fel.cvut.sitforms.mapper.GroupMapper;
import cz.fel.cvut.sitforms.model.GroupEntity;
import cz.fel.cvut.sitforms.model.UserEntity;
import cz.fel.cvut.sitforms.repository.*;
import cz.fel.cvut.sitforms.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;


    @Override
    public GroupResponseDto createGroup(GroupRequestDto groupRequestDto) {
        Long author = groupRequestDto.getAuthorId();

        UserEntity authorEntity = userRepository.findById(author).orElseThrow(() -> new EntityNotFoundException("User not found"));
        GroupEntity groupEntity = groupMapper.toEntity(groupRequestDto);
        groupEntity.setAuthor(authorEntity);

        List<UserGroupRequestDto> userEntityList = groupRequestDto.getUserEntityList();
        if (groupRequestDto.getUserEntityList() != null ) {
            for (UserGroupRequestDto x : userEntityList) {
                UserEntity newUser = userRepository.findById(x.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
                groupEntity.getUserEntityList().add(newUser);
                newUser.getAssignedGroups().add(groupEntity);
            }
        }

        GroupEntity save = groupRepository.save(groupEntity);

        return groupMapper.toResponse(save);
    }

    @Override
    public List<GroupResponseDto> getAllAssignedGroups(Long userId) {
        UserEntity newUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<GroupResponseDto> result = new ArrayList<>();
        newUser.getAssignedGroups().forEach(groupEntity -> {
            GroupResponseDto newGroupResponse = groupMapper.toResponse(groupEntity);
            result.add(newGroupResponse);
        });
        return result;
    }

    @Override
    public List<GroupResponseDto> getAllManagedGroups(Long userId) {
        UserEntity newUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<GroupResponseDto> result = new ArrayList<>();
        newUser.getCreatedGroups().forEach(groupEntity -> {
            GroupResponseDto newGroupResponse = groupMapper.toResponse(groupEntity);
            result.add(newGroupResponse);
        });
        return result;
    }

    @Override
    public List<GroupResponseDto> getAllGroups() {
        return groupRepository.findAll().stream().map(groupMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public GroupResponseDto findByName(String name) {
        GroupEntity group = groupRepository.findByName(name);
        return groupMapper.toResponse(group);
    }


    @Override
    public GroupResponseDto addUserToGroup(Long userId, Long groupID) {
        UserEntity myUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        GroupEntity myGroup = groupRepository.findById(groupID).orElseThrow(() -> new EntityNotFoundException("Group not found"));

        myGroup.getUserEntityList().add(myUser);
        groupRepository.save(myGroup);
        return groupMapper.toResponse(myGroup);
    }

    @Override
    public List<GroupResponseDto> getGroupsByCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<GroupResponseDto> getGroupsByName(String name) {
        return null;
    }
}
