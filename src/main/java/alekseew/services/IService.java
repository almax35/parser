package alekseew.services;

import alekseew.entity.ItemInterface;

import java.io.IOException;

public interface IService {
    ItemInterface searchByName(String name) throws IOException, InterruptedException;
}
