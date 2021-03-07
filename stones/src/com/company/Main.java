package com.company;

import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.enteties.Precious;
import com.company.repositories.PreciousRepository;
import com.company.repositories.interfaces.IPreciousRepository;

public class Main {

    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IPreciousRepository preciousRepository = new PreciousRepository(db);

        StonesFrontEnd app = new StonesFrontEnd(preciousRepository);
        app.start();
    }
}
