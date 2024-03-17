package services;

import entity.BuffItem;

import java.io.IOException;

public interface IService {
    public BuffItem searchByName(String name) throws IOException,InterruptedException;
}
