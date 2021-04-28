package com.carcassonne.gameserver.manager;

import com.carcassonne.gameserver.bean.Card;
import com.carcassonne.gameserver.bean.Edge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomManagerTest {

    @Test
    void canPutCard() {
        Card[][] cards = new Card[31][31];
        Card or = new Card();
        or.setBot(new Edge(1,"城",null));
        or.setLef(new Edge(2,"路","right"));
        or.setRig(new Edge(3,"路","left"));
        or.setTop(new Edge(4,"草",null));
        cards[15][15]=or;
        RoomManager roomManager=new RoomManager(cards);
        Card nc = new Card();
        nc.setBot(new Edge(1,"城",null));
        nc.setLef(new Edge(2,"路","right"));
        nc.setRig(new Edge(3,"路","left"));
        nc.setTop(new Edge(4,"草",null));
        assertEquals(true,roomManager.canPutCard(14,15,nc));
        assertEquals(true,roomManager.canPutCard(16,15,nc));
        assertEquals(false,roomManager.canPutCard(15,16,nc));
        assertEquals(false,roomManager.canPutCard(15,14,nc));
        assertEquals(false,roomManager.canPutCard(16,16,nc));
    }
}