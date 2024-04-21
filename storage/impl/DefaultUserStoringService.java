package storage.impl;

import enteties.User;
import enteties.impl.DefaultUser;
import storage.UserStoringService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.*;

public class DefaultUserStoringService implements UserStoringService {
    private static final Path path = Path.of("resources/userDb.csv");
    private static DefaultUserStoringService instance;

    private DefaultUserStoringService(){}

    public static DefaultUserStoringService getInstance(){
        if(instance==null){
            instance=new DefaultUserStoringService();
        }
        return instance;
    }
    @Override
    public void saveUser(User user) {
        try {
            Files.writeString(path,System.lineSeparator()+cleanOutput(user), CREATE,APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String cleanOutput(User user){
        return user.getId() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getPassword() + ","
                + user.getEmail();
    }

    @Override
    public List<User> loadUsers() {
        try(var stream=Files.lines(path)){
            return stream.filter(Objects::nonNull)
                    .filter(s->!s.isEmpty())
                    .map(s->{
                        String[] array=s.split(",");
                        return new DefaultUser(Integer.parseInt(array[0]),
                                array[1],
                                array[2],
                                array[3],
                                array[4]);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
}
