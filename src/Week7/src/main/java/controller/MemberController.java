package controller;

import domain.Lion;
import domain.Role;
import domain.Staff;
import dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest request) {
        Lion lion = memberService.createLion(request);

        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LionResponse.from(lion));
    }

    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Staff staff = memberService.createStaff(request);

        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StaffResponse.from(staff));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findMember(@PathVariable String name) {
        Role member = memberService.findMember(name);

        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (member instanceof Lion lion) {
            return ResponseEntity.ok(LionResponse.from(lion));
        }

        if (member instanceof Staff staff) {
            return ResponseEntity.ok(StaffResponse.from(staff));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(
            @PathVariable String name,
            @RequestBody LionUpdateRequest request
    ) {
        Lion lion = memberService.updateLion(name, request);

        if (lion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(LionResponse.from(lion));
    }

    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(
            @PathVariable String name,
            @RequestBody StaffUpdateRequest request
    ) {
        Staff staff = memberService.updateStaff(name, request);

        if (staff == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(StaffResponse.from(staff));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(@PathVariable String name) {
        boolean result = memberService.deleteMember(name);

        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }
}