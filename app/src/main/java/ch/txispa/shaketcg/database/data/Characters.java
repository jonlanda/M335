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

        Character ironMan = new Character();
        ironMan.name = "Iron Man";
        ironMan.rarity = "epic";
        ironMan.series = "Avengers";
        ironMan.worth = 46;
        ironMan.pictureLink = "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/3/35/IronMan-EndgameProfile.jpg/revision/latest/scale-to-width-down/1200?cb=20231025163251";
        characterDao.insertAll(ironMan);

        Character pussInBoots = new Character();
        pussInBoots.name = "Puss in boots";
        pussInBoots.rarity = "rare";
        pussInBoots.series = "Puss in boots";
        pussInBoots.worth = 23;
        pussInBoots.pictureLink = "https://static.wikia.nocookie.net/p__/images/8/82/PussInBoots2022.png/revision/latest?cb=20230117171759&path-prefix=protagonist";
        characterDao.insertAll(pussInBoots);

        Character batMan = new Character();
        batMan.name = "Batman";
        batMan.rarity = "epic";
        batMan.series = "Batman";
        batMan.worth = 50;
        batMan.pictureLink = "https://static.wikia.nocookie.net/p__/images/f/fd/Batman_%28Prime_Earth%29.jpg/revision/latest?cb=20230718090804&path-prefix=protagonist";
        characterDao.insertAll(batMan);

        Character jamesBond = new Character();
        jamesBond.name = "James Bond";
        jamesBond.rarity = "common";
        jamesBond.series = "James Bond";
        jamesBond.worth = 7;
        jamesBond.pictureLink = "https://m.media-amazon.com/images/M/MV5BYWQ2NzQ1NjktMzNkNS00MGY1LTgwMmMtYTllYTI5YzNmMmE0XkEyXkFqcGdeQXVyMjM4NTM5NDY@._V1_.jpg";
        characterDao.insertAll(jamesBond);

        Character gandalf = new Character();
        gandalf.name = "Gandalf";
        gandalf.rarity = "legendary";
        gandalf.series = "Herr der Ringe";
        gandalf.worth = 1000000;
        gandalf.pictureLink = "https://media2.giphy.com/media/TcdpZwYDPlWXC/giphy.gif";
        characterDao.insertAll(gandalf);

        Character ahsoka = new Character();
        ahsoka.name = "Ahsoka Tano";
        ahsoka.rarity = "rare";
        ahsoka.series = "Ahsoka";
        ahsoka.worth = 30;
        ahsoka.pictureLink = "https://lumiere-a.akamaihd.net/v1/images/who-is-ahsoka-tano-article-feature_60dc019d.jpeg?region=350,0,900,900";
        characterDao.insertAll(ahsoka);

        Character r2d2 = new Character();
        r2d2.name = "R2-D2";
        r2d2.rarity = "common";
        r2d2.series = "Star Wars";
        r2d2.worth = 4;
        r2d2.pictureLink = "https://static.wikia.nocookie.net/starwars/images/9/95/R2-D2-TROSOCE.png/revision/latest?cb=20240104043013";
        characterDao.insertAll(r2d2);

        Character vash = new Character();
        vash.name = "Vash the Stampede";
        vash.rarity = "rare";
        vash.series = "Trigun Stampede";
        vash.worth = 25;
        vash.pictureLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY_AO-dYovrSXDT6TR50gtlR1tzsApEqHJ0A&usqp=CAU";
        characterDao.insertAll(vash);
    }
}
