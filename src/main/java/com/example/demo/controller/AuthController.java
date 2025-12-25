@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Map<String, String> req) {
        User user = userService.register(
                req.get("email"),
                req.get("password"),
                req.get("name")
        );
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> req) {

        User user = userService.findByEmail(req.get("email"));

        if (!passwordEncoder.matches(req.get("password"), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Set<String> roles = new HashSet<>();
        user.getRoles().forEach(r -> roles.add(r.getName()));

        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), roles);

        Map<String, Object> resp = new HashMap<>();
        resp.put("token", token);
        resp.put("user", user);

        return ResponseEntity.ok(resp);
    }
}
