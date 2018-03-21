package com.woowahan.goosgbtstudy.hoonji;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class AuctionSniperEndToEndTest {
    private final FakeAuctionServer fakeAuctionServer = new FakeAuctionServer("item-54321");
    private final ApplicationRunner applicationRunner = new ApplicationRunner();

    @Test
    public void sniperJoinsAuctionUntilAuctionCloses() throws Exception {
        fakeAuctionServer.startSellingItem();
        applicationRunner.startBiddingIn(fakeAuctionServer);
        fakeAuctionServer.hasReceivedJoinRequestFromSniper();
        fakeAuctionServer.announceClosed();
        applicationRunner.showsSniperHasLostAuction();
    }

    @AfterEach
    public void stopAuction() {
        fakeAuctionServer.stop();

    }

    @AfterEach
    public void stopApplication() {
        applicationRunner.stop();
    }
}
