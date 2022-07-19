package cz.fel.cvut.sitforms.resources;


import cz.fel.cvut.sitforms.dto.FormRequestDto;
import cz.fel.cvut.sitforms.dto.GroupRequestDto;
import cz.fel.cvut.sitforms.dto.GroupResponseDto;
import cz.fel.cvut.sitforms.service.GroupService;
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
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final AuthenticationManager authenticationManager;
    private final GroupService groupService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public GroupResponseDto createGroup(@RequestBody GroupRequestDto groupRequestDto){
        return groupService.createGroup(groupRequestDto);
    }

    @GetMapping("/assigned/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public List<GroupResponseDto> getAllAssignedGroups(@PathVariable Long userId){
        return groupService.getAllAssignedGroups(userId);
    }

    @GetMapping("/managed/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public List<GroupResponseDto> getAllManagedGroups(@PathVariable Long userId){
        return groupService.getAllManagedGroups(userId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public List<GroupResponseDto> getAllGroups(){
        return groupService.getAllGroups();
    }

    @GetMapping("/{groupId}/add/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public GroupResponseDto addUserToGroup(@PathVariable Long userId, @PathVariable Long groupId){
        return groupService.addUserToGroup(userId,groupId);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyRole('ROLE_PREMIUM_USER', 'ROLE_FREE_USER')")
    public GroupResponseDto findByName(String name) {
        return groupService.findByName(name);
    }

}
