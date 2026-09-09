package test;

import dto.request.instruments.InstrumentRequestBody;
import dto.response.instruments.InstrumentResponseBody;
import extension.AdminTokenExtension;
import extension.RandomStringExtension;
import extension.UserTokenExtension;
import extension.utils.AdminToken;
import extension.utils.RandomString;
import extension.utils.UserToken;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import service.market.data.service.MarketDataServiceHttpRequests;
import service.market.data.service.MarketDataServicePostgresRequests;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({UserTokenExtension.class, RandomStringExtension.class, AdminTokenExtension.class})
public class MarketDataServiceTest {

    @Test
    public void getInstruments(@extension.utils.UserToken String userToken) {
        List<InstrumentResponseBody> expectedInstruments = MarketDataServicePostgresRequests.getInstruments().items();
        List<InstrumentResponseBody> actualInstruments = MarketDataServiceHttpRequests.getInstruments(userToken).items();
        assertThat(expectedInstruments).containsExactlyInAnyOrderElementsOf(actualInstruments);
    }

    @Test
    public void getInstrumentById(@UserToken String userToken) {
        InstrumentResponseBody expectedInstrument = MarketDataServicePostgresRequests.getInstruments()
                .items().getFirst();
        InstrumentResponseBody actualInstrument = MarketDataServiceHttpRequests.getInstrumentById(expectedInstrument.id(), userToken);
        assertThat(expectedInstrument.equals(actualInstrument)).isTrue();
    }

    @Test
    public void addInstrument(@AdminToken String adminToken, @RandomString String postfix) {
        InstrumentRequestBody instrument = new InstrumentRequestBody("Sym" + postfix, "Tesla" + postfix, "STOCK");
        InstrumentResponseBody addedInstrument = MarketDataServiceHttpRequests.addInstrument(instrument, adminToken);
        Assertions.assertEquals(addedInstrument, MarketDataServiceHttpRequests.getInstrumentById(addedInstrument.id(), adminToken));
        Assertions.assertEquals(addedInstrument, MarketDataServicePostgresRequests.getInstrumentById(addedInstrument.id()));
    }

    @Test
    public void editInstrument() {
    }

    @Test
    public void deleteInstrument() {
    }
}
