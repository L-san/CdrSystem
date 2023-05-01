package me.sa1zer.cdrsystem.cdr.utils;

import org.instancio.Instancio;
import org.instancio.Select;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CdrGenerator {

    private static final Path CDR_FILE = Paths.get("cdr.txt");

    public static void generate() {
        try (FileWriter fw = new FileWriter(CDR_FILE.toFile())) {
            List<CdrInstance> cdr = getCdrInstanceList(10);
            cdr.forEach(x -> {
                try {
                    fw.write(x.toString() + "\n");
                } catch (IOException e) {
                    System.err.println("Ошибка записи");
                }
            });
        } catch (IOException e) {
            System.err.println("Файл не найден");
            e.printStackTrace();
        }

    }

    private static List<CdrInstance> getCdrInstanceList(int size) {
        List<CdrInstance> cdr = Instancio.ofList(CdrInstance.class).size(size)
                .generate(Select.field(CdrInstance::getCallType), gen -> gen.ints().min(1).max(2).as(x->"0"+x.toString()))
                .generate(Select.field(CdrInstance::getPhoneNumber), gen -> gen.text().pattern("7#d#d#d#d#d#d#d#d#d#d"))
                .generate(Select.field(CdrInstance::getEndCallDate), gen -> gen.temporal().date().past())
                .create();

        cdr.forEach(x -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(x.getEndCallDate());
            cal.add(Calendar.HOUR, -12);
            Date hourBack = cal.getTime();
            x.setStartCallDate(Instancio.of(CdrInstance.class).generate(Select.field(CdrInstance::getStartCallDate),
                    gen -> gen.temporal().date().past().range(hourBack, x.getEndCallDate())).create().getStartCallDate());
        });
        return cdr;
    }

}
