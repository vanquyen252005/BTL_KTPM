package com.example.loan_service.map;

import com.example.loan_service.dto.LoanRequest;
import com.example.loan_service.dto.LoanResponse;
import com.example.loan_service.entity.Loan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-07T20:11:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class LoanMapperImpl implements LoanMapper {

    @Override
    public Loan toEntity(LoanRequest request) {
        if ( request == null ) {
            return null;
        }

        Loan.LoanBuilder loan = Loan.builder();

        loan.borrowerId( request.getBorrowerId() );
        loan.loanAmount( request.getLoanAmount() );
        loan.interestRate( request.getInterestRate() );
        loan.durationMonths( request.getDurationMonths() );

        return loan.build();
    }

    @Override
    public LoanResponse toResponse(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        LoanResponse loanResponse = new LoanResponse();

        loanResponse.setLoanId( loan.getLoanId() );
        loanResponse.setLoanNumber( loan.getLoanNumber() );
        loanResponse.setBorrowerId( loan.getBorrowerId() );
        loanResponse.setLoanAmount( loan.getLoanAmount() );
        loanResponse.setInterestRate( loan.getInterestRate() );
        loanResponse.setDurationMonths( loan.getDurationMonths() );
        loanResponse.setLoanStatus( loan.getLoanStatus() );
        loanResponse.setCreatedDate( loan.getCreatedDate() );
        loanResponse.setDisbursementDate( loan.getDisbursementDate() );

        return loanResponse;
    }
}
