package services.csmarket;

import entity.BuffItem;
import entity.CsMarketItem;
import services.IService;

import java.io.IOException;

public class CsMarketService implements IService {
    @Override
    public CsMarketItem searchByName(String name) throws IOException, InterruptedException {

        return new CsMarketItem();
    }
}
