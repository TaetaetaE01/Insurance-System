package org.example.rewardSupportTeam;

import org.example.Team;
import org.example.common.dto.RequestDto;
import org.example.common.dto.ResponseDto;
import org.example.contract.Contract;
import org.example.insurance.InsuranceChargeCustomerApplyList;
import org.example.planTeam.Status;
import org.example.rewardSupportTeam.model.Accident;
import org.example.rewardSupportTeam.model.AccidentList;
import org.example.rewardSupportTeam.model.ClaimInsurance;
import org.example.rewardSupportTeam.model.litigationInfoList;
import org.example.user.Customer;

import java.util.List;

import static org.example.rewardSupportTeam.view.RewardSupportView.*;

/**
 * @author USER
 * @version 1.0
 */
public class RewardSupportTeam extends Team {

    private final AccidentList accidentList;
    private final litigationInfoList litigationInfoList;
    private final InsuranceChargeCustomerApplyList insuranceChargeCustomerApplyList;

    private static boolean judgmentResult = false;

    public RewardSupportTeam(AccidentList accidentList, litigationInfoList litigationInfoList, InsuranceChargeCustomerApplyList insuranceChargeCustomerApplyList) {
        this.accidentList = accidentList;
        this.litigationInfoList = litigationInfoList;
        this.insuranceChargeCustomerApplyList = insuranceChargeCustomerApplyList;
    }

    public void finalize() throws Throwable {

    }

    @Override
    public ResponseDto manage(RequestDto request) {
        return null;
    }

    @Override
    public ResponseDto process(RequestDto request) {
        ResponseDto responseDto = new ResponseDto();
        if (request.get(JUDGE_ANSWER).equals("Y") || request.get(JUDGE_ANSWER).equals("y")) {
            // 면부책을 판단한다
            // list에 정보가 다 있고 계약에서 어떤 보험에 가입했는지에 따라 면부책 판단을 내려주면 될 듯
//            Customer applyUser = insuranceChargeCustomerApplyList.findFirst();
//
//            if (applyUser == null) {
//                responseDto.add(Status.getKey(), Status.FAIL.getStatus());
//                return responseDto;
//            }
//            List<Contract> tempList = applyUser.getContractList().getContracts();
            Accident accident = accidentList.read(1);
            if (accident == null) {
                judgmentResult = false;
                responseDto.add(Status.getKey(), Status.FAIL.getStatus());
                return responseDto;
            }
            judgmentResult = true;
            responseDto.add(Status.getKey(), Status.SUCCESS.getStatus());
        } else if (request.get(JUDGE_ANSWER).equals("N") || request.get(JUDGE_ANSWER).equals("n")) {
            responseDto.add(Status.getKey(), Status.FAIL.getStatus());
        } else {
            responseDto.add(Status.getKey(), Status.INPUT_INVALID.getStatus());
        }
        return responseDto;
    }

    @Override
    public ResponseDto register(RequestDto request) {
        ResponseDto responseDto = new ResponseDto();

        if (!insuranceChargeCustomerApplyList.isEmpty()) {
            Accident accident = new Accident();
            accident.setContent(request.get(ACCIDENT_CONTENT));
            accident.setCustomerName(request.get(ACCIDENT_NAME));

            ClaimInsurance claimInsurance = new ClaimInsurance();
            claimInsurance.setAccount(request.get(CLAIMINSURANCE_ACCOUNT));
            claimInsurance.setAddress(request.get(CLAIMINSURANCE_ADDRESS));
            claimInsurance.setPhoneNumber(request.get(CLAIMINSURANCE_PHONENUMBER));
            claimInsurance.setResidentNumber(request.get(CLAIMINSURANCE_RESIDENTNUMBER));
            claimInsurance.setSupportingFile(request.get(CLAIMINSURANCE_SUPPORTINGFILE));

            accident.setClaimInsurance(claimInsurance);

            if (!accidentList.add(accident)) {
                responseDto.add(Status.getKey(), Status.FAIL.getStatus());
                return responseDto;
            }
            responseDto.add(Status.getKey(), Status.SUCCESS.getStatus());
            return responseDto;
        }
        responseDto.add(Status.getKey(), Status.EMPTY.getStatus());
        return responseDto;
    }


    @Override
    public ResponseDto remove(RequestDto request) {
        return null;
    }

    @Override
    public ResponseDto retrieve(RequestDto request) {
        ResponseDto responseDto = new ResponseDto();
        if (judgmentResult) {
            responseDto.add(Status.getKey(), Status.SUCCESS.getStatus());
            return responseDto;
        }
        responseDto.add(Status.getKey(), Status.FAIL.getStatus());
        return responseDto;
    }
}