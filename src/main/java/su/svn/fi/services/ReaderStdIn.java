package su.svn.fi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.svn.fi.configs.YamlApplicationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

@Service
public class ReaderStdIn implements Reader
{
    private int batchSize = 2;

    private Scanner console;

    public ReaderStdIn(Scanner scanner)
    {
        console = scanner;
    }

    @Autowired
    public ReaderStdIn(YamlApplicationProperties yap)
    {
        this(new Scanner(System.in));
        batchSize = yap.getBatchSize();
    }

    @Override
    public void read(Consumer<List<String>> consumer)
    {
        while (console.hasNextLine()) {
            try (Scanner lineScanner = new Scanner(console.nextLine())) {
                List<String> list = new ArrayList<>();

                for (int i = 0; i < batchSize && lineScanner.hasNext(); i++) {
                    list.add(lineScanner.next());
                }
                consumer.accept(list);
            }
        }
    }
}
