package collectionService;

import models.Member;
import java.util.*;

public class MemberCollectionservice {

    private final List<Member> members = new ArrayList<>();

    // Add a member
    public void addMember(Member member) {
        members.add(member);
    }

    // Update member details
    public void updateMember(Member member) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMember_id() == member.getMember_id()) {
                members.set(i, member);
                return;
            }
        }
    }

    // Delete a member
    public void deleteMember(int memberId) {
        members.removeIf(member -> member.getMember_id() == memberId);
    }

    // Get all members
    public List<Member> getAllMembers() {
        return members;
    }

    // Get member by ID
    public Member getMemberById(int memberId) {
        return members.stream().filter(m -> m.getMember_id() == memberId).findFirst().orElse(null);
    }
}
