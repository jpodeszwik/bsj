package pl.jp.bsj.investment;

import pl.jp.bsj.domain.FundType;
import pl.jp.bsj.domain.InvestmentFund;

public class Funds {
    public static final InvestmentFund POLISH_1 = new InvestmentFund(1L, "Polish fund 1", FundType.POLISH);
    public static final InvestmentFund POLISH_2 = new InvestmentFund(2L, "Polish fund 2", FundType.POLISH);

    public static final InvestmentFund FOREIGN_1 = new InvestmentFund(3L, "Foreign fund 1", FundType.FOREIGN);
    public static final InvestmentFund FOREIGN_2 = new InvestmentFund(4L, "Foreign fund 2", FundType.FOREIGN);
    public static final InvestmentFund FOREIGN_3 = new InvestmentFund(5L, "Foreign fund 3", FundType.FOREIGN);

    public static final InvestmentFund MONETARY_1 = new InvestmentFund(6L, "Monetary fund 1", FundType.MONETARY);
}
