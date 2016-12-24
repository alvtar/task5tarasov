package service;

import java.util.List;
import domain.*;
import exception.PersistentException;

public interface PublicationService extends Service {
    List<Publication> findAll() throws PersistentException;

    Publication findById(Integer id) throws PersistentException;

    Publication findByIssn(Integer issn) throws PersistentException;

    List<Publication> findByTitleLike(String titleДшлу) throws PersistentException;

    void save(Publication publication) throws PersistentException;

    void delete(Integer id) throws PersistentException;
}
