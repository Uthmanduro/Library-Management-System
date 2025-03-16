package DAOTest;

import models.Member;
import java.util.*;
import services.MemberService;

public class TestMember {
    public static void main(String[] args) {
        List<Member> memberList = new ArrayList<>();
        MemberService memberService = new MemberService();


         memberService.addMember(new Member(1, "max", "max@moniepoint.com", "09012345677"));
         memberService.addMember(new Member(3, "John", "john@gmail.com", "09012565677"));


        memberList = memberService.getAllMembers();
        if (!memberList.isEmpty()) {
            for (Member member : memberList) {
                System.out.println(member.toString());
            }
        }

        // update member details and save to database
        memberList.get(0).setName("samuel");
        memberList.get(0).setEmail("samuel@gmail.com");
        memberList.get(0).setPhone("0812345678");
        memberService.updateMember(memberList.get(0));


        // get the updated member details from the database
        Member updatedMember = memberService.getMemberById(memberList.get(0).getMember_id());
        System.out.println("Updated member details");
        System.out.println(updatedMember.toString());

        // delete a member from the database
        System.out.println("Length of members before deletion is: " + memberList.size());
//        memberService.deleteMember(memberList.get(0).getMember_id());
        memberList = memberService.getAllMembers();
        System.out.println("Length of members After deletion is: " + memberList.size());

    }
}
