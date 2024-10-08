package com.dev.pos.bo;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.custom.ProductBo;
import com.dev.pos.bo.impl.*;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {}

    public static BoFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public <T> T getBo(BoType boType) {
        switch (boType) {
            case PRODUCT:
                return (T) new ProductBoImpl();
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case USER:
                return (T) new UserBoImpl();
            case BATCH:
                return (T) new BatchBoImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailBoImpl();
            default:
                return null;
        }
    }

}
