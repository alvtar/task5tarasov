package dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import dao.Dao;
import dao.PublicationDao;

import dao.SubscriptionDao;
import dao.Transaction;

import dao.UserDao;
import exception.PersistentException;


public class TransactionImpl implements Transaction {
	private static Logger logger = Logger.getLogger(TransactionImpl.class);

	private static Map<Class<? extends Dao<?>>, Class<? extends BaseDaoImpl>> classes = new ConcurrentHashMap<>();
	static {
		classes.put(UserDao.class, UserDaoImpl.class);
		classes.put(PublicationDao.class, PublicationDaoImpl.class);
		classes.put(SubscriptionDao.class, SubscriptionDaoImpl.class);
	}

	private Connection connection;

	public TransactionImpl(Connection connection) {
		this.connection = connection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException {
		Class<? extends BaseDaoImpl> value = classes.get(key);
		if(value != null) {
			try {
				BaseDaoImpl dao = value.newInstance();
				dao.setConnection(connection);
				return (Type)dao;
			} catch(InstantiationException | IllegalAccessException e) {
				logger.error("It is impossible to create data access object", e);
				throw new PersistentException(e);
			}
		}
		return null;
	}

	@Override
	public void commit() throws PersistentException {
		try {
			connection.commit();
		} catch(SQLException e) {
			logger.error("It is impossible to commit transaction", e);
			throw new PersistentException(e);
		}
	}

	@Override
	public void rollback() throws PersistentException {
		try {
			connection.rollback();
		} catch(SQLException e) {
			logger.error("It is impossible to rollback transaction", e);
			throw new PersistentException(e);
		}
	}
}
