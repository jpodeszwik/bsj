package pl.jp.bsj.domain;

import lombok.Value;

@Value
public class InvestmentFund {
    private final Long id;
    private final String name;
    private final FundType fundType;
}
