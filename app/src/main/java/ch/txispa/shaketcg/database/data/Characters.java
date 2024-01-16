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
        yujiItadori.rarity = "epic";
        yujiItadori.series = "Jujutsu Kaisen";
        yujiItadori.worth = 60;
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
        gandalf.series = "Lord of the rings";
        gandalf.worth = 100;
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

        Character harryPotter = new Character();
        harryPotter.name = "Harry Potter";
        harryPotter.rarity = "common";
        harryPotter.series = "Harry Potter";
        harryPotter.worth = 5;
        harryPotter.pictureLink = "https://upload.wikimedia.org/wikipedia/en/d/d7/Harry_Potter_character_poster.jpg";
        characterDao.insertAll(harryPotter);

        Character blue = new Character();
        blue.name = "Blue";
        blue.rarity = "rare";
        blue.series = "Jurassic World";
        blue.worth = 25;
        blue.pictureLink = "https://static.wikia.nocookie.net/p__/images/d/dd/Fallen_kingdom_blue_the_velociraptor_v3_by_sonichedgehog2-dc9x53o.png/revision/latest?cb=20180818052013&path-prefix=protagonist";
        characterDao.insertAll(blue);

        Character indianaJones = new Character();
        indianaJones.name = "Indiana Jones";
        indianaJones.rarity = "common";
        indianaJones.series = "Indiana Jones";
        indianaJones.worth = 10;
        indianaJones.pictureLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Indianajones4.jpg/800px-Indianajones4.jpg";
        characterDao.insertAll(indianaJones);

        Character obiWan = new Character();
        obiWan.name = "Obi-Wan Kenobi";
        obiWan.rarity = "rare";
        obiWan.series = "Star Wars";
        obiWan.worth = 30;
        obiWan.pictureLink = "https://static.wikia.nocookie.net/starwars/images/2/2a/He-says-the-thing.png/revision/latest/scale-to-width-down/1200?cb=20230113022153";
        characterDao.insertAll(obiWan);

        Character jeffrey = new Character();
        jeffrey.name = "Jeffrey";
        jeffrey.rarity = "legendary";
        jeffrey.series = "Nektons - Abenteurer der Tiefe";
        jeffrey.worth = 112;
        jeffrey.pictureLink = "https://bilder.fernsehserien.de/epg/epg-archiv/2023/07/11/4d9f09a8f877434181d559aa87244466_b-w-970.jpg.jpg";
        characterDao.insertAll(jeffrey);

        Character ethanHunt = new Character();
        ethanHunt.name = "Ethan Hunt";
        ethanHunt.rarity = "epic";
        ethanHunt.series = "Mission Impossible";
        ethanHunt.worth = 50;
        ethanHunt.pictureLink = "https://static.wikia.nocookie.net/p__/images/7/78/Ethan_Hunt_%28MI-RN%29.png/revision/latest?cb=20230513140627&path-prefix=protagonist";
        characterDao.insertAll(ethanHunt);

        Character jackSparrow = new Character();
        jackSparrow.name = "Jack Sparrow";
        jackSparrow.rarity = "common";
        jackSparrow.series = "Pirates of the Caribbean";
        jackSparrow.worth = 15;
        jackSparrow.pictureLink = "https://static.wikia.nocookie.net/great-characters/images/d/d8/Jacksparrow.jpeg/revision/latest/thumbnail/width/360/height/360?cb=20190323132109";
        characterDao.insertAll(jackSparrow);

        Character johnWick = new Character();
        johnWick.name = "John Wick";
        johnWick.rarity = "rare";
        johnWick.series = "John Wick";
        johnWick.worth = 34;
        johnWick.pictureLink = "https://hips.hearstapps.com/hmg-prod/images/mh-9-22-wick-650dcf0aeb656.jpg?crop=0.447xw:0.895xh;0,0&resize=640:*";
        characterDao.insertAll(johnWick);

        Character benjiDunn = new Character();
        benjiDunn.name = "Benji Dunn";
        benjiDunn.rarity = "common";
        benjiDunn.series = "Mission Impossible";
        benjiDunn.worth = 10;
        benjiDunn.pictureLink = "https://pbs.twimg.com/profile_images/833761819776917508/ShSZmZdH_400x400.jpg";
        characterDao.insertAll(benjiDunn);

        Character lukeSkywalker = new Character();
        lukeSkywalker.name = "Luke Skywalker";
        lukeSkywalker.rarity = "common";
        lukeSkywalker.series = "Star Wars";
        lukeSkywalker.worth = 8;
        lukeSkywalker.pictureLink = "https://static.wikia.nocookie.net/starwars/images/2/20/LukeTLJ.jpg/revision/latest?cb=20230113022153";
        characterDao.insertAll(lukeSkywalker);

        Character hiccup = new Character();
        hiccup.name = "Hiccup";
        hiccup.rarity = "rare";
        hiccup.series = "How to train your dragon";
        hiccup.worth = 25;
        hiccup.pictureLink = "https://i1.sndcdn.com/avatars-H4JpjNnoqqpAMbvU-yFGcpQ-t500x500.jpg";
        characterDao.insertAll(hiccup);

        Character jakeSully = new Character();
        jakeSully.name = "Jake Sully";
        jakeSully.rarity = "common";
        jakeSully.series = "Avatar";
        jakeSully.worth = 10;
        jakeSully.pictureLink = "https://upload.wikimedia.org/wikipedia/en/thumb/a/a9/Jake_Sully.jpg/200px-Jake_Sully.jpg";
        characterDao.insertAll(jakeSully);

        Character jackWilder = new Character();
        jackWilder.name = "Jack Wilder";
        jackWilder.rarity = "common";
        jackWilder.series = "Now you see me";
        jackWilder.worth = 10;
        jackWilder.pictureLink = "https://i.quotev.com/fu7gs3gyaaaa.jpg";
        characterDao.insertAll(jackWilder);
    }
}
