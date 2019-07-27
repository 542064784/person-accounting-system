package com.damon.dto;

/**
 * transaction type enum
 *
 * @author Damon Chen
 * @date 2019/6/9
 */
public enum TransactionType {

    /**
     * 全部消费
     */
    All_CONSUMPTION("all consumption"),
    /**
     * 餐饮美食
     */
    FOOD_AND_DRINK("food and drink"),
    /**
     * 服饰美容
     */
    CLOTHING_BEAUTY("clothing beauty"),
    /**
     * 生活日用
     */
    DAILY_LIFE("daily life"),
    /**
     *  通讯物流
     */
    COMMUNICATION_LOGISTICS("communication logistics"),
    /**
     *  休闲娱乐
     */
    LEISURE_AND_ENTERTAINMENT("leisure and entertainment"),
    /**
     *  医疗保健
     */
    MEDICAL_INSURANCE("medical insurance"),
    /**
     *  人情往来
     */
    HUMAN_RELATIONS("human relations"),
    /**
     *  其他消费
     */
    OTHER_CONSUMPTION("other consumption"),
    /**
     *  工资
     */
    WAGE("wage"),
    /**
     *  其他收入
     */
    OTHER_INCOME("other income");

    private final String code;

    TransactionType(String code) {
        this.code = code;
    }

    public String getCode(){
        return code;
    }

}
