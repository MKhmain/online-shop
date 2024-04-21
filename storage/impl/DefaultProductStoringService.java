package storage.impl;

import enteties.Product;
import enteties.impl.DefaultProduct;
import storage.ProductStoringService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultProductStoringService implements ProductStoringService {
    private static final Path path = Path.of("resources/productsDb.csv");
    private static DefaultProductStoringService instance;

    public static DefaultProductStoringService getInstance(){
        if(instance==null){
            instance=new DefaultProductStoringService();
        }
        return instance;
    }
    private DefaultProductStoringService(){}
    @Override
    public List<Product> loadProducts() {
        try (var stream = Files.lines(path)) {
            return stream.filter(Objects::nonNull)
                    .filter(s->!s.isEmpty())
                    .map(p->{
                        String[] products=p.split(",");
                        System.out.println(Arrays.toString(products));
                        return new DefaultProduct(Integer.parseInt(products[0]),
                                products[1],
                                products[2],
                                Double.parseDouble(products[3]));
                    })
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
}
