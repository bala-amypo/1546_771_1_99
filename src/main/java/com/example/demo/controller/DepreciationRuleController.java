@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {

    private final DepreciationRuleService ruleService;

    public DepreciationRuleController(DepreciationRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<DepreciationRule> create(@RequestBody DepreciationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<DepreciationRule>> getAll() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
