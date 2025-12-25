@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventService eventService;

    public AssetLifecycleEventController(AssetLifecycleEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> logEvent(
            @PathVariable Long assetId,
            @RequestBody AssetLifecycleEvent event) {

        return ResponseEntity.ok(
                eventService.logEvent(assetId, event)
        );
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getEvents(
            @PathVariable Long assetId) {

        return ResponseEntity.ok(
                eventService.getEventsForAsset(assetId)
        );
    }
}
