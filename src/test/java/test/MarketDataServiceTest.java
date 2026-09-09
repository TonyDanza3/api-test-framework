package test;

import dto.response.instruments.Instruments;
import extension.UserTokenExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import service.market.data.service.MarketDataServiceHttpRequests;

@ExtendWith(UserTokenExtension.class)
public class MarketDataServiceTest {

    @Test
    public void getInstruments(@extension.utils.UserToken String userToken) {
        Instruments instruments = MarketDataServiceHttpRequests.getInstruments(userToken);
    }

    @Test
    public void getInstrumentById() {}

    @Test
    public void addInstrument() {}

    @Test
    public void editInstrument() {}

    @Test
    public void deleteInstrument() {}
}
