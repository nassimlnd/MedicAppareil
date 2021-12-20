package org;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Connexion {

    public static boolean connect(String type, String identifiant, String mdp) {
        FileReader fileReader;
        Scanner sc;
        boolean isCorrect = false;
        try {
            fileReader = new FileReader("comptes.txt");
            sc = new Scanner(fileReader);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String split[] = line.split(";");

                if (split[1].equals(type)) {
                    if (split[2].equals(identifiant)) {
                        if (split[3].equals(mdp)) {
                            isCorrect = true;
                        }
                        else isCorrect = false;
                    }
                    else isCorrect = false;
                }
                else isCorrect = false;
            }

            return isCorrect;
        } catch (FileNotFoundException e) {
            System.out.println("Fichier inexistant.");;
        }
        finally {
            return isCorrect;
        }


    }

}
