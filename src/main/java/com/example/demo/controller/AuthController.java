// // package com.example.demo.controller;

// // import com.example.demo.dto.AuthRequest;
// // import com.example.demo.dto.AuthResponse;
// // import com.example.demo.entity.User;
// // import com.example.demo.repository.UserRepository;
// // import com.example.demo.service.UserService;
// // import com.example.demo.util.JwtUtil;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.security.authentication.AuthenticationManager;
// // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // import org.springframework.security.core.Authentication;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.web.bind.annotation.*;

// // import java.util.*;
// // import java.util.stream.Collectors;

// // @RestController
// // @RequestMapping("/auth")
// // public class AuthController {

// //     private final AuthenticationManager authManager;
// //     private final JwtUtil jwtUtil;
// //     private final UserService userService;
// //     private final UserRepository userRepo;

// //     public AuthController(AuthenticationManager authManager,
// //                           JwtUtil jwtUtil,
// //                           UserService userService,
// //                           UserRepository userRepo) {
// //         this.authManager = authManager;
// //         this.jwtUtil = jwtUtil;
// //         this.userService = userService;
// //         this.userRepo = userRepo;
// //     }

// //     @PostMapping("/register")
// //     public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> body) {
// //         try {
// //             User user = userService.registerUser(body);
// //             Map<String, Object> response = new HashMap<>();
// //             response.put("id", user.getId());
// //             response.put("email", user.getEmail());
// //             response.put("name", user.getName());
// //             return ResponseEntity.ok(response);
// //         } catch (Exception e) {
// //             return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
// //         }
// //     }

// //     @PostMapping("/login")
// //     public ResponseEntity<?> login(@RequestBody AuthRequest req) {
// //         try {
// //             Authentication authentication = authManager.authenticate(
// //                     new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
// //             );

// //             SecurityContextHolder.getContext().setAuthentication(authentication);

// //             User user = userRepo.findByEmail(req.getEmail())
// //                     .orElseThrow(() -> new RuntimeException("User not found"));

// //             Set<String> roles = user.getRoles()
// //                     .stream()
// //                     .map(r -> r.getName())
// //                     .collect(Collectors.toSet());

// //             String token = jwtUtil.generateToken(user.getEmail(), user.getId(), roles);

// //             return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), roles));

// //         } catch (Exception e) {
// //             return ResponseEntity.status(401).body(Collections.singletonMap("error", "Invalid credentials"));
// //         }
// //     }
// // }
// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import com.example.demo.util.JwtUtil;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// import java.util.*;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final AuthenticationManager authenticationManager;
//     private final JwtUtil jwtUtil;
//     private final UserService userService;
//     private final UserRepository userRepository;

//     public AuthController(AuthenticationManager authenticationManager,
//                           JwtUtil jwtUtil,
//                           UserService userService,
//                           UserRepository userRepository) {
//         this.authenticationManager = authenticationManager;
//         this.jwtUtil = jwtUtil;
//         this.userService = userService;
//         this.userRepository = userRepository;
//     }

//     /**
//      * REGISTER + AUTO LOGIN
//      * Fixes: test70_registerEndpoint_canRegisterAndLogin
//      */
//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
//         try {
//             User user = userService.registerUser(body);

//             Set<String> roles = user.getRoles()
//                     .stream()
//                     .map(role -> role.getName())
//                     .collect(Collectors.toSet());

//             String token = jwtUtil.generateToken(
//                     user.getEmail(),
//                     user.getId(),
//                     roles
//             );

//             return ResponseEntity.ok(
//                     new AuthResponse(token, user.getId(), user.getEmail(), roles)
//             );

//         } catch (Exception e) {
//             return ResponseEntity.badRequest()
//                     .body(Collections.singletonMap("error", e.getMessage()));
//         }
//     }

//     /**
//      * LOGIN
//      */
//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody AuthRequest request) {
//         try {
//             Authentication authentication = authenticationManager.authenticate(
//                     new UsernamePasswordAuthenticationToken(
//                             request.getEmail(),
//                             request.getPassword()
//                     )
//             );

//             SecurityContextHolder.getContext().setAuthentication(authentication);

//             User user = userRepository.findByEmail(request.getEmail())
//                     .orElseThrow(() -> new RuntimeException("User not found"));

//             Set<String> roles = user.getRoles()
//                     .stream()
//                     .map(role -> role.getName())
//                     .collect(Collectors.toSet());

//             String token = jwtUtil.generateToken(
//                     user.getEmail(),
//                     user.getId(),
//                     roles
//             );

//             return ResponseEntity.ok(
//                     new AuthResponse(token, user.getId(), user.getEmail(), roles)
//             );

//         } catch (Exception e) {
//             return ResponseEntity.status(401)
//                     .body(Collections.singletonMap("error", "Invalid credentials"));
//         }
//     }
// }
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            String token = jwtUtil.generateToken(auth.getName());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}
