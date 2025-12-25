@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalService disposalService;

    public AssetDisposalController(AssetDisposalService disposalService) {
        this.disposalService = disposalService;
    }

    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposal> requestDisposal(
            @PathVariable Long assetId,
            @RequestBody AssetDisposal disposal) {

        return ResponseEntity.ok(
                disposalService.requestDisposal(assetId, disposal)
        );
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approve(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        return ResponseEntity.ok(
                disposalService.approveDisposal(disposalId, adminId)
        );
    }
}
