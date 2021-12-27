package org;

import javax.security.auth.login.AccountNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                    if (split[3].equals(md5Encrypt(mdp))) {
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

    public static String md5Encrypt(String motdepasse) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(motdepasse.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashedPassword = no.toString(16);
            while (hashedPassword.length() < 32) {
                hashedPassword = "0" + hashedPassword;
            }

            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

}
