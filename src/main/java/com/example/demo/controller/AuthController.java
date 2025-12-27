@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepo;

    public AuthController(AuthenticationManager authManager,
                          JwtUtil jwtUtil,
                          UserService userService,
                          UserRepository userRepo) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.userRepo = userRepo;
    }

    // ✅ REGISTER (TEST EXPECTED FORMAT)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {

        // create user
        User user = userService.register(req.getEmail(), req.getPassword(), req.getName());

        Set<String> roles = user.getRoles()
                .stream()
                .map(r -> r.getName())
                .collect(Collectors.toSet());

        return ResponseEntity.ok(
                Map.of(
                        "id", user.getId(),
                        "email", user.getEmail(),
                        "roles", roles
                )
        );
    }

    // ✅ LOGIN (DO NOT CHANGE SIGNATURE)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<String> roles = user.getRoles()
                .stream()
                .map(r -> r.getName())
                .collect(Collectors.toSet());

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getId(),
                roles
        );

        return ResponseEntity.ok(
                new AuthResponse(token, user.getId(), user.getEmail(), roles)
        );
    }
}
