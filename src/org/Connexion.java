package org;

import javax.security.auth.login.AccountNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Connexion {

    public static boolean connect(String type, String identifiant, String mdp) throws AccountNotFoundException, FileNotFoundException {
        FileReader fileReader;
        Scanner sc;
        boolean isCorrect = false;

        File file = new File("comptes.txt");
        fileReader = new FileReader(file);
        sc = new Scanner(fileReader);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String split[] = line.split(";");

            if (split[1].equals(type)) {
                if (split[2].equals(identifiant)) {
                    if (split[3].equals(mdp)) {
                        isCorrect = true;
                        return isCorrect;
                    }
                    else isCorrect = false;
                }
                else isCorrect = false;
            }
            else isCorrect = false;
        }

        throw new AccountNotFoundException();

    }

}
