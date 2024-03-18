package services;

import entity.BuffItem;
import entity.ItemInterface;

import java.io.IOException;

public interface IService {
    public ItemInterface searchByName(String name) throws IOException,InterruptedException;
}
