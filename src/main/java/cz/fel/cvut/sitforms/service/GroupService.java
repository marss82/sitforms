package cz.fel.cvut.sitforms.service;

import cz.fel.cvut.sitforms.dto.*;

import java.util.List;

public interface GroupService {
    GroupResponseDto createGroup(GroupRequestDto groupRequestDto);

    List<GroupResponseDto>  getAllAssignedGroups(Long UserId);

    List<GroupResponseDto>  getAllManagedGroups(Long UserId);

    List<GroupResponseDto>  getAllGroups();

    GroupResponseDto addUserToGroup(Long userId, Long groupID);

    List<GroupResponseDto> getGroupsByCategory(Long categoryId);

    List<GroupResponseDto> getGroupsByName(String name);

    GroupResponseDto findByName(String name);

}
