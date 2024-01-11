package ch.txispa.shaketcg.database.data;

import ch.txispa.shaketcg.database.dao.CharacterDao;
import ch.txispa.shaketcg.database.entity.Character;

public class Characters {
    private CharacterDao characterDao;
    public Characters(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }
    public void defaultData() {
        Character frieren = new Character();
        frieren.name = "Frieren";
        frieren.rarity = "Legendary";
        frieren.series = "Souso no Frieren";
        frieren.worth = 120;
        frieren.pictureLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2-_vBREWs7VpnAQmMvrfbVeD4Qg-43ksVQg&usqp=CAU";
        characterDao.insertAll(frieren);

        Character yujiItadori = new Character();
        yujiItadori.name = "Yuji Itadori";
        yujiItadori.rarity = "Legendary";
        yujiItadori.series = "Jujutsu Kaisen";
        yujiItadori.worth = 110;
        yujiItadori.pictureLink = "https://cdn.anisearch.de/images/character/cover/97/97602_400.webp";
        characterDao.insertAll(yujiItadori);

        Character keiMiyama = new Character();
        keiMiyama.name = "Kei Miyama";
        keiMiyama.rarity = "common";
        keiMiyama.series = "Hokuhokusei ni Kumo to Ike";
        keiMiyama.worth = 5;
        keiMiyama.pictureLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVNLjEo_V9tvghpZHIm-_oreSiqZTOHmuo8g&usqp=CAU";
        characterDao.insertAll(keiMiyama);
    }
}
