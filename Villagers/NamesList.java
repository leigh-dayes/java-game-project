package Villagers;

import java.util.Random;

public class NamesList {
    private String[] names = {
        "Alba",
        "Anabel",
        "Argenta",
        "Beatrice",
        "Celestina",
        "Gisela",
        "Gratiana",
        "Gregoria",
        "Guinevere",
        "Gwenllian",
        "Honora",
        "Idalia",
        "Laria",
        "Meliora",
        "Melanie",
        "Joanna",
        "Mirabel",
        "Pacifica",
        "Petra",
        "Regina",
        "Savia",
        "Sigrid",
        "Viola",
        "Winifred",
        "Alaric",
        "Alban",
        "Aldo",
        "Ambrose",
        "Archibald",
        "August",
        "Baldwin",
        "Balthasar",
        "Barnabas",
        "Bartholomew",
        "Benedict",
        "Cassian",
        "Castellan",
        "Chapman",
        "Crispian",
        "Declan",
        "Dunstan",
        "Edmund",
        "Everad",
        "Ferdinand",
        "Frost",
        "Gavin",
        "Giles",
        "Godfrey",
        "Godric",
        "Hugh",
        "Ivo",
        "Iver",
        "Leopold",
        "Lucian",
        "Maxim",
        "Milo",
        "Neville",
        "Otto",
        "Rudolf",
        "Sebastian",
        "Severin",
        "Tobias"
    };
    private String firstname;
    private String lastname;

    public NamesList() {
        newName();
    }
    public String getFirstName() {
        return firstname;
    }
    public String getLastName() {
        return lastname;
    }
    public void newName() {
        Random rand = new Random();
        int namesLength = names.length;
        firstname = names[rand.nextInt(namesLength+1)];
        lastname = names[rand.nextInt(namesLength+1)];
    }
}
