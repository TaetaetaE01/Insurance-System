package org.example.rewardSupportTeam.view;

import org.example.common.DepartmentView;
import org.example.common.dto.RequestDto;
import org.example.common.dto.ResponseDto;
import org.example.planTeam.Status;

public class RewardSupportView extends DepartmentView {

    public static final String ACCIDENT_CONTENT = "content";
    public static final String ACCIDENT_NAME = "name";
    public static final String CLAIMINSURANCE_ACCOUNT = "account";
    public static final String CLAIMINSURANCE_ADDRESS = "address";
    public static final String CLAIMINSURANCE_PHONENUMBER = "phoneNumber";
    public static final String CLAIMINSURANCE_RESIDENTNUMBER = "residentNumber";
    public static final String CLAIMINSURANCE_SUPPORTINGFILE = "supportingFile";


    public void intro() {
        println("안녕하세요. 보상 지원팀입니다.");
    }

    public RequestDto submitAccident() {
        RequestDto requestDto = new RequestDto();
        println("면/부책 판단을 위한 '사건 정보'를 입력해주세요.");
        print("사고 내용 : ");
        requestDto.add(ACCIDENT_CONTENT, writeString());
        print("고객 이름 : ");
        requestDto.add(ACCIDENT_NAME, writeString());

        println("면/부책 판단을 위한 '보험금 청구정보'를 입력해주세요.");
        print("계좌 : ");
        requestDto.add(CLAIMINSURANCE_ACCOUNT, writeString());
        print("주소 : ");
        requestDto.add(CLAIMINSURANCE_ADDRESS, writeString());
        print("전화번호 : ");
        requestDto.add(CLAIMINSURANCE_PHONENUMBER, writeString());
        print("주민번호 : ");
        requestDto.add(CLAIMINSURANCE_RESIDENTNUMBER, writeString());
        print("증빙 서류 : ");
        requestDto.add(CLAIMINSURANCE_SUPPORTINGFILE, writeString());
        return requestDto;
    }

    public void completeSubmitAccident(ResponseDto responseDto) {
        if (responseDto.get(Status.key()).equals(Status.SUCCESS.getStatus())) {
            println("성공적으로 사고가 접수되었습니다.");
        }
        println("사고 접수에 실패하였습니다. 다시 시도해주세요.");
    }

    public RequestDto judgeFault() {
        RequestDto requestDto = new RequestDto();
        return requestDto;
    }

    public ResponseDto completeJudgeFault(ResponseDto responseDto) {
        return responseDto;
    }

}