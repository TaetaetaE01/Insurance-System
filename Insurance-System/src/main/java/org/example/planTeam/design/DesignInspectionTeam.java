package org.example.planTeam.design;

import static org.example.planTeam.design.view.ProposalConstant.Insurance_Payment;
import static org.example.planTeam.design.view.ProposalConstant.KIND;
import static org.example.planTeam.design.view.ProposalConstant.MARKET_RESEARCH;
import static org.example.planTeam.design.view.ProposalConstant.PRODUCT_OVERVIEW;
import static org.example.planTeam.design.view.ProposalConstant.PROPOSAL_TITLE;
import static org.example.planTeam.design.view.ProposalConstant.REWARD;
import static org.example.planTeam.design.view.ProposalConstant.SALE_STRATEGY;
import static org.example.planTeam.design.view.ProposalConstant.SALE_TARGET;

import org.example.Team;
import org.example.common.dto.RequestDto;
import org.example.common.dto.ResponseDto;
import org.example.planTeam.Status;
import org.example.planTeam.design.model.DesignPlanList;
import org.example.planTeam.design.model.Proposal;
import org.example.planTeam.design.model.Proposal.ProposalBuilder;
import org.example.planTeam.design.model.ProposalList;

/**
 * @author USER
 * @version 1.0
 */
public class DesignInspectionTeam extends Team {

    private final DesignPlanList designPlanList;
    private final ProposalList proposalList;

    public DesignInspectionTeam(DesignPlanList designPlanList, ProposalList proposalList) {
        this.designPlanList = designPlanList;
        this.proposalList = proposalList;
    }

    @Override
    public ResponseDto manage(RequestDto request) {
        return null;
    }

    @Override
    public ResponseDto process(RequestDto request) {
        return null;
    }

    @Override
    public ResponseDto register(RequestDto request) {
        Proposal proposal = new ProposalBuilder().proposalId(proposalList.getSize())
            .title(request.get(PROPOSAL_TITLE)).productOverview(PRODUCT_OVERVIEW)
            .marketResearch(MARKET_RESEARCH).insurancePayment(Insurance_Payment)
            .saleStrategy(SALE_STRATEGY).saleTarget(SALE_TARGET).reward(REWARD).build();
        proposalList.add(proposal);
        ResponseDto responseDto = new ResponseDto();
        responseDto.add(Status.key(), Status.success.getStatus());
        return responseDto;
    }

    @Override
    public ResponseDto remove(RequestDto request) {
        return null;
    }

    @Override
    public ResponseDto retrieve(RequestDto request) {
        return null;
    }
}