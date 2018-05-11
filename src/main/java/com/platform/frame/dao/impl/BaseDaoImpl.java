package com.platform.frame.dao.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.platform.authorization.model.LogonModel;
import com.platform.frame.config.CONFIG;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.dao.domain.Identifiable;
import com.platform.frame.exception.DaoException;
import com.platform.frame.log.SqlLogger;
import com.platform.frame.log.log4j.DaoConstants;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.util.UUIDUtils;
import com.platform.frame.util.UserSessionUtil;
import com.platform.frame.util.constants.SqlId;


/**
 * 基础Dao接口实现类，实现该类的子类必须设置泛型类型
 */
public abstract class BaseDaoImpl<T extends Identifiable> implements BaseDao<T> {
	@Autowired(required = true)
	protected SqlSession sqlSessionTemplate;
	
	private SqlLogger sqlLogger;
	
	private LogonModel userModel;
	
	public static final String SQLNAME_SEPARATOR = ".";
	
	public static final String DATABASE_SEPARATOR = "_";

	/**
	 * @fields sqlNamespace SqlMapping命名空间 
	 */
	private String sqlNamespace = getDefaultSqlNamespace();

	/**
	 * 获取泛型类型的实体对象类全名
	 * 
	 * @return
	 */
	protected String getDefaultSqlNamespace() {
		Class<?> genericClass = BeanUtils.getGenericClass(this.getClass());
		return genericClass == null ? null : genericClass.getName();
	}

	/**
	 * 获取SqlMapping命名空间 
	 * 
	 * @return SqlMapping命名空间 
	 */
	public String getSqlNamespace() {
		return sqlNamespace;
	}

	/**
	 * 设置SqlMapping命名空间。 以改变默认的SqlMapping命名空间，
	 * 不能滥用此方法随意改变SqlMapping命名空间。 
	 * 
	 * @param sqlNamespace SqlMapping命名空间 
	 */
	public void setSqlNamespace(String sqlNamespace) {
		this.sqlNamespace = sqlNamespace;
	}

	/**
	 * 将SqlMapping命名空间与给定的SqlMapping名组合在一起。
	 * @param sqlName SqlMapping名 
	 * @return 组合了SqlMapping命名空间后的完整SqlMapping名 
	 */
	protected String getSqlNameByDatabase(String sqlName) {
		return sqlNamespace + SQLNAME_SEPARATOR + sqlName + DATABASE_SEPARATOR + CONFIG.DATABASE;
	}
	
	protected String getSqlName(String sqlName) {
		return sqlNamespace + SQLNAME_SEPARATOR + sqlName;
	}

	/**
	 * 生成主键值。 默认使用{@link UUIDUtils#create()}方法
	 * 如果需要生成主键，需要由子类重写此方法根据需要的方式生成主键值。 
	 * @param entity 要持久化的对象 
	 */
	protected String generateId() {
		return UUIDUtils.create();
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectOne(java.io.Serializable)
	 */
	@Override
	public <V extends T> V selectOne(T query) {
		Assert.notNull(query);
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询一条记录出错！语句：%s", getSqlName(SqlId.SQL_SELECT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectById(java.io.Serializable)
	 */
	@Override
	public <V extends T> V selectById(Integer id) {
		Assert.notNull(id);
		try {
			return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_BY_ID), id);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s", getSqlName(SqlId.SQL_SELECT_BY_ID)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectList(java.io.Serializable)
	 */
	@Override
	public <V extends T> List<V> selectList(T query) {
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s", getSqlName(SqlId.SQL_SELECT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectAll()
	 */
	@Override
	public <V extends T> List<V> selectAll() {
		try {
			return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT));
		} catch (Exception e) {
			throw new DaoException(String.format("查询所有对象列表出错！语句：%s", getSqlName(SqlId.SQL_SELECT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectMap(java.io.Serializable, java.lang.String)
	 */
	@Override
	public <K, V extends T> Map<K, V> selectMap(T query, String mapKey) {
		Assert.notNull(mapKey, "[mapKey] - must not be null!");
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectMap(getSqlName(SqlId.SQL_SELECT), params, mapKey);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象Map时出错！语句：%s", getSqlName(SqlId.SQL_SELECT)), e);
		}
	}

	/**
	 * 设置分页
	 * @param pageInfo 分页信息
	 * @return SQL分页参数对象
	 */
	protected RowBounds getRowBounds(Pageable pageable) {
		RowBounds bounds = RowBounds.DEFAULT;
		if (null != pageable) {
			bounds = new RowBounds(pageable.getOffset(), pageable.getPageSize());
		}
		return bounds;
	}

	/**
	 * 获取分页查询参数
	 * @param query 查询对象
	 * @param pageable 分页对象
	 * @return Map 查询参数
	 */
	protected Map<String, Object> getParams(T query, Pageable pageable) {
		Map<String, Object> params = BeanUtils.toMap(query, getRowBounds(pageable));
		if (pageable != null && pageable.getSort() != null) {
			String sorting = pageable.getSort().toString();
			params.put("sorting", sorting.replace(":", ""));
		}
		return params;
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectList(com.viathink.core.dao.domain.Identifiable, org.springframework.data.domain.Pageable)
	 */
	@Override
	public <V extends T> List<V> selectList(T query, Pageable pageable) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT), getParams(query, pageable));
		} catch (Exception e) {
			throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(SqlId.SQL_SELECT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectPageList(com.viathink.core.dao.domain.Identifiable, org.springframework.data.domain.Pageable)
	 */
	@Override
	public <V extends T> Page<V> selectPageList(T query, Pageable pageable) {
		try {
			List<V> contentList = sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT),
					getParams(query, pageable));
			return new PageImpl<V>(contentList, pageable, this.selectCount(query));
		} catch (Exception e) {
			throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(SqlId.SQL_SELECT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectMap(com.viathink.core.dao.domain.Identifiable, java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public <K, V extends T> Map<K, V> selectMap(T query, String mapKey, Pageable pageable) {
		try {
			return sqlSessionTemplate.selectMap(getSqlName(SqlId.SQL_SELECT), getParams(query, pageable), mapKey);
		} catch (Exception e) {
			throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(SqlId.SQL_SELECT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectCount()
	 */
	@Override
	public Long selectCount() {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_COUNT));
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(SqlId.SQL_SELECT_COUNT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectCount(java.io.Serializable)
	 */
	@Override
	public Long selectCount(T query) {
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_COUNT), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(SqlId.SQL_SELECT_COUNT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#insert(java.io.Serializable)
	 */
	@Override
	public void insert(T entity) {
		Assert.notNull(entity);
		try {
			//TODO 
//			if (StringUtils.isBlank(entity.getId()))
//				entity.setId(generateId());
			sqlSessionTemplate.insert(getSqlName(SqlId.SQL_INSERT), entity);
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s", getSqlName(SqlId.SQL_INSERT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(T query) {
		Assert.notNull(query);
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.delete(getSqlName(SqlId.SQL_DELETE), params);
		} catch (Exception e) {
			throw new DaoException(String.format("删除对象出错！语句：%s", getSqlName(SqlId.SQL_DELETE)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#deleteById(java.io.Serializable)
	 */
	@Override
	public int deleteById(Integer id) {
		Assert.notNull(id);
		try {
			return sqlSessionTemplate.delete(getSqlName(SqlId.SQL_DELETE_BY_ID), id);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID删除对象出错！语句：%s", getSqlName(SqlId.SQL_DELETE_BY_ID)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#deleteAll()
	 */
	@Override
	public int deleteAll() {
		try {
			return sqlSessionTemplate.delete(getSqlName(SqlId.SQL_DELETE));
		} catch (Exception e) {
			throw new DaoException(String.format("删除所有对象出错！语句：%s", getSqlName(SqlId.SQL_DELETE)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#updateById(java.io.Serializable)
	 */
	@Override
	public int updateById(T entity) {
		Assert.notNull(entity);
		try {
			return sqlSessionTemplate.update(getSqlName(SqlId.SQL_UPDATE_BY_ID), entity);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID更新对象出错！语句：%s", getSqlName(SqlId.SQL_UPDATE_BY_ID)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#updateByIdSelective(java.io.Serializable)
	 */
	@Override
	@Transactional
	public int updateByIdSelective(T entity) {
		Assert.notNull(entity);
		try {
			return sqlSessionTemplate.update(getSqlName(SqlId.SQL_UPDATE_BY_ID_SELECTIVE), entity);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID更新对象某些属性出错！语句：%s", getSqlName(SqlId.SQL_UPDATE_BY_ID_SELECTIVE)),
					e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#deleteByIdInBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void deleteByIdInBatch(List<Integer> idList) {
		if (idList == null || idList.isEmpty())
			return;
		for (Integer id : idList) {
			this.deleteById(id);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#updateInBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void updateInBatch(List<T> entityList) {
		if (entityList == null || entityList.isEmpty())
			return;
		for (T entity : entityList) {
			this.updateByIdSelective(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#insertInBatch(java.util.List)
	 */
	@Override
	public void insertInBatch(List<T> entityList) {
		if (entityList == null || entityList.isEmpty())
			return;
		for (T entity : entityList) {
			this.insert(entity);
		}
	}
	
	private void log(String sql, Date starttime, Date endtime, String success, String type) {
		
		if (userModel == null) 
			userModel = UserSessionUtil.getUser();
		
		if(userModel == null)
			return;
			
		sqlLogger = new SqlLogger();
		sqlLogger.log(userModel.getUsercode(), sql, userModel.getRemoteIP(), userModel.getMacAddress(), starttime, endtime, success, type);
	}
	
	public void delete(String statement, Object parameter) {
		
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			sqlSessionTemplate.delete(getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			throw new DaoException(String.format("删除语句出错！语句：%s", getSqlName(statement)), e);
		}
		finally {

			log(getSql(statement), stime, new Date(System.currentTimeMillis()), success, DaoConstants.excuteType);
		}
	}
	
	public void delete(String statement, Object parameter, boolean markDBType) {
		
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			sqlSessionTemplate.delete(markDBType ? getSqlNameByDatabase(statement) : getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			
		}
		finally {
			
			log(getSql(statement, markDBType), stime, new Date(System.currentTimeMillis()), success, DaoConstants.excuteType);
		}
	}
	
	public void update(String statement, Object parameter) {
		
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			sqlSessionTemplate.update(getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			throw new DaoException(String.format("更新语句出错！语句：%s", getSqlName(statement)), e);
		}
		finally {

			log(getSql(statement), stime, new Date(System.currentTimeMillis()), success, DaoConstants.excuteType);
		}
	}
	
	public void update(String statement, Object parameter, boolean markDBType) {
		
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			sqlSessionTemplate.update(markDBType ? getSqlNameByDatabase(statement) : getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			
		}
		finally {
			
			log(getSql(statement, markDBType), stime, new Date(System.currentTimeMillis()), success, DaoConstants.excuteType);
		}
	}
	
	public void insert(String statement, Object parameter) {
		
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			sqlSessionTemplate.insert(getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			throw new DaoException(String.format("插入语句出错！语句：%s", getSqlName(statement)), e);
		}
		finally {

			log(getSql(statement), stime, new Date(System.currentTimeMillis()), success, DaoConstants.excuteType);
		}
	}
	
	public void insert(String statement, Object parameter, boolean markDBType) {
		
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			sqlSessionTemplate.insert(markDBType ? getSqlNameByDatabase(statement) : getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			throw new DaoException(String.format("插入语句出错！语句：%s", getSqlNameByDatabase(statement)), e);
		}
		finally {
			
			log(getSql(statement, markDBType), stime, new Date(System.currentTimeMillis()), success, DaoConstants.excuteType);
		}
	}
	
	public <T> T selectOne(String statement, Object parameter) {
		
		T t  = null;
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			t = sqlSessionTemplate.selectOne(getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			throw new DaoException(String.format("查询语句出错！语句：%s", getSqlName(statement)), e);
		}
		finally {

			log(getSql(statement), stime, new Date(System.currentTimeMillis()), success, DaoConstants.queryType);
		}
		return t;
	}
	public <T> T selectOne(String statement, Object parameter, boolean markDBType) {
		
		T t  = null;
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			t = sqlSessionTemplate.selectOne(markDBType ? getSqlNameByDatabase(statement) : getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			throw new DaoException(String.format("查询语句出错！语句：%s", markDBType ? getSqlNameByDatabase(statement) : getSqlName(statement)), e);
		}
		finally {
			
			log(getSql(statement, markDBType), stime, new Date(System.currentTimeMillis()), success, DaoConstants.queryType);
		}
		return t;
	}
	
	public <E> List<E> selectList(String statement, Object parameter) {
		List<E> list = null;
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {

			list = sqlSessionTemplate.selectList(getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象出错！语句：%s", getSqlName(SqlId.SQL_SELECT)), e);
		}
		finally {
			
			log(getSql(statement), stime, new Date(System.currentTimeMillis()), success, DaoConstants.queryType);
		}
		return list;
	}
	
	public <E> List<E> selectList(String statement, Object parameter, boolean markDBType) {
		List<E> list = null;
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			
			list = sqlSessionTemplate.selectList(markDBType ? getSqlNameByDatabase(statement) : getSqlName(statement), parameter);
			success = DaoConstants.success;
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象出错！语句：%s", markDBType ? getSqlNameByDatabase(statement) : getSqlName(statement)), e);
		}
		finally {
			log(getSql(statement, markDBType), stime, new Date(System.currentTimeMillis()), success, DaoConstants.queryType);
		}
		return list;
	}
	
	public <E> List<E> selectList(String statement) {
		List<E> list = null;
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {

			list = sqlSessionTemplate.selectList(getSqlName(statement));
			success = DaoConstants.success;
		} catch (Exception e) {
			
		}
		finally {
			
			log(getSql(statement), stime, new Date(System.currentTimeMillis()), success, DaoConstants.queryType);
		}
		return list;
	}
	
	/**
	 * 
	 * @param statement
	 * @param markDBType 是否开启区分数据库
	 * @return
	 */
	public <E> List<E> selectList(String statement, boolean markDBType) {
		List<E> list = null;
		Date stime = new Date(System.currentTimeMillis());
		String success = DaoConstants.faild;
		try {
			
			list = sqlSessionTemplate.selectList(markDBType ? getSqlNameByDatabase(statement) : getSqlName(statement));
			success = DaoConstants.success;
		} catch (Exception e) {
			
		}
		finally {
			
			log(getSql(statement, markDBType), stime, new Date(System.currentTimeMillis()), success, DaoConstants.queryType);
		}
		return list;
	}
	
	
	
	public String getSql(String statement) {
		MappedStatement m = sqlSessionTemplate.getConfiguration().getMappedStatement(getSqlName(statement));
		SqlSource ss = m.getSqlSource();
		BoundSql b = ss.getBoundSql(null);
		String sql = b.getSql();
		sql = sql.replaceAll("\n", "");
		sql = sql.replaceAll("\t", "");
		if (sql!=null && sql.length()>1000) {
			sql.substring(0, 1000);
		}
		return sql;
	}
	public String getSql(String statement, boolean markDBType) {
		MappedStatement m = sqlSessionTemplate.getConfiguration().getMappedStatement(markDBType ? getSqlNameByDatabase(statement) : getSqlName(statement));
		SqlSource ss = m.getSqlSource();
		BoundSql b = ss.getBoundSql(null);
		String sql = b.getSql();
		sql = sql.replaceAll("\n", "");
		sql = sql.replaceAll("\t", "");
		if (sql!=null && sql.length()>1000) {
			sql.substring(0, 1000);
		}
		return sql;
	}

}
