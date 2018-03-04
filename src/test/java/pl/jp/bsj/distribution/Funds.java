package pl.jp.bsj.distribution;

import pl.jp.bsj.domain.FundType;
import pl.jp.bsj.domain.InvestmentFund;

public class Funds {
    static final InvestmentFund POLISH_1 = new InvestmentFund(1L, "Polish fund 1", FundType.POLISH);
    static final InvestmentFund POLISH_2 = new InvestmentFund(2L, "Polish fund 2", FundType.POLISH);

    static final InvestmentFund FOREIGN_1 = new InvestmentFund(3L, "Foreign fund 1", FundType.FOREIGN);

    static final InvestmentFund MONETARY_1  = new InvestmentFund(4L, "Monetary fund 1", FundType.MONETARY);
}
