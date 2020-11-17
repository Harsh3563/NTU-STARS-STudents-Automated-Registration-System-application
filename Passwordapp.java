package Boundary;

import Control.PasswordMgr;
import Entity.Hashingtable;
import Entity.Password;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class PasswordApp {
    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        PasswordMgr.addPassword(new Password("C190195", "student",
                Hashingtable.hexercon(Hashingtable.hasho("Testpassword1"))));
        PasswordMgr.addPassword(new Password("TEJAS005", "student",
                Hashingtable.hexercon(Hashingtable.hasho("Testpassword2"))));
        PasswordMgr.addPassword(new Password("DODDI003", "student",
                Hashingtable.hexercon(Hashingtable.hasho("Testpassword3"))));
        PasswordMgr.addPassword(new Password("KJ004", "student",
                Hashingtable.hexercon(Hashingtable.hasho("Testpassword4"))));
        PasswordMgr.addPassword(new Password("MERVYN007", "student",
                Hashingtable.hexercon(Hashingtable.hasho("Testpassword5"))));
        PasswordMgr.addPassword(new Password("ZHEREN002", "student",
                Hashingtable.hexercon(Hashingtable.hasho("Testpassword6"))));
    }
}
