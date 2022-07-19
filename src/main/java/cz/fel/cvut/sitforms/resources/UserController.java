package cz.fel.cvut.sitforms.resources;

import cz.fel.cvut.sitforms.dto.AuthRequestDto;
import cz.fel.cvut.sitforms.dto.TokenResponseDto;
import cz.fel.cvut.sitforms.dto.UserRequestDto;
import cz.fel.cvut.sitforms.dto.UserResponseDto;
import cz.fel.cvut.sitforms.model.UserEntity;
import cz.fel.cvut.sitforms.repository.UserRepository;
import cz.fel.cvut.sitforms.security.TokenProvider;
import cz.fel.cvut.sitforms.service.impl.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/users")
public class UserController {

    private final String HEADER_STRING = "Authorization";

    private final String TOKEN_PREFIX = "Bearer";

    private AuthenticationManager authenticationManager;

    private TokenProvider tokenProvider;

    private UserServiceImpl userService;
    private UserRepository userRepository;

    @Operation(
            summary = "Finds all users",
            operationId = "getUsers",
            description = "Returns all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users returned")
    })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponseDto> findAll(){
        return userService.findUsers();
    }

    @Operation(
            summary = "Login user and return jwt token",
            operationId = "login",
            description = "Returns token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token returned")
    })
    @PostMapping("/login")
    public TokenResponseDto generateToken(@RequestBody AuthRequestDto loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity byUsername = userRepository.findByUsername(loginUser.getUsername());
        final String token = tokenProvider.generateToken(authentication, byUsername);
        return new TokenResponseDto(token);
    }

    @Operation(
            summary = "Registration user and return jwt token",
            operationId = "login",
            description = "Returns token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token returned")
    })
    @PostMapping("/registration")
    public TokenResponseDto createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }

    @Operation(
            summary = "Refresh token jwt token",
            operationId = "refresh",
            description = "Refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token returned")
    })
    @GetMapping("/refresh")
    public TokenResponseDto refreshToken(HttpServletRequest request, HttpServletResponse response){
        String header = request.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        String token = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, "");
            try {
                username = tokenProvider.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                log.error("An error occurred while fetching Username from Token", e);
            } catch (ExpiredJwtException e) {
                log.warn("The token has expired", e);
            } catch (SignatureException e) {
                log.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            log.warn("Couldn't find bearer string, header will be ignored");
        }
        if (username != null) {

            UserDetails userDetails = userService.loadUserByUsername(username);
            if (tokenProvider.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthenticationToken(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                UserEntity byUsername = userRepository.findByUsername(username);
                log.info("authenticated user with email " + username + ", setting security context");
                token = tokenProvider.generateToken(authentication, byUsername);
            }
        }
        return new TokenResponseDto(token);
    }
    @GetMapping("/{email}")
    public UserResponseDto findByEmail(String email){
        return userService.findByEmail(email);
    }

    @GetMapping("/test")
    public String test(){
      return "ok";
    }

}
