package dao;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



import database.connection.ConnectionFactory;

public class AbstractDAO<T> {


    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> abstractVariable;

    @SuppressWarnings("unchecked")
	public AbstractDAO() 
    {
        this.abstractVariable = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    } 
    

    private String createSelectQuery(String field)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM finalprojectsd.");
        sb.append(abstractVariable.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    
    public T findById(int id) throws NoSuchMethodException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = createSelectQuery("id" + abstractVariable.getSimpleName());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, abstractVariable.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    
    public String createInsertQuery()
    {
    	StringBuilder fields = new StringBuilder("(");
    	StringBuilder values = new StringBuilder("(");
    	
    	for(Field attribute: abstractVariable.getDeclaredFields()) 
    	{
    		if(fields.length() >= 2) {
    			fields.append(", ");
    			values.append(", ");
    		}
    		fields.append(attribute.getName());
    		values.append("? ");
    	}
    	fields.append(") VALUES ");
    	
    	String query = "INSERT INTO finalprojectsd." + abstractVariable.getSimpleName() +" " + fields + values + ")";
    	
    	return query;
    }
    
    public void insert(T object) {
    	
    	String sqlQuery = this.createInsertQuery();
    	Connection connect = null;
    	PreparedStatement st = null;
    	
    	try {
    		connect = ConnectionFactory.getConnection();
			st = connect.prepareStatement(sqlQuery);
			
			Field[] attributes = abstractVariable.getDeclaredFields();
			
			for(int i = 0; i < attributes.length; i++)
			{
				
				Field currentAttribute = attributes[i];
				currentAttribute.setAccessible(true);
				@SuppressWarnings("unchecked")
				T currentObject = (T) currentAttribute.get(object);
				st.setObject(i+1, currentObject);
			}
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    	
    }
    
    public List<T> report()
    {
    	List<T> reportedList = new ArrayList<T>();
    	String sqlQuery = "SELECT * FROM finalprojectsd." + abstractVariable.getSimpleName();
    	PreparedStatement st = null;
    	ResultSet results = null;
    	
    	Connection connect = ConnectionFactory.getConnection();
    	try {
			st = connect.prepareStatement(sqlQuery);
			results = st.executeQuery();
			reportedList = createObjects(results);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
    	
    	return reportedList;
    }
    
    public String createDeleteQuery()
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append("DELETE FROM finalprojectsd.");
    	sb.append(abstractVariable.getSimpleName());
    	sb.append(" WHERE ");
    	
    	
    	Field[] attributes = abstractVariable.getDeclaredFields();
    	String idAttribute = attributes[0].getName();
    	
    	sb.append(idAttribute);
    	sb.append("= ?");
    	
    	String deleteQuery = sb.toString();
    	return deleteQuery ;
    }
    
    public void delete(T object,int id)
    {
    	
    	String sqlQuery = this.createDeleteQuery();
    	Connection connect = null;
    	PreparedStatement st = null;
    	
		connect = ConnectionFactory.getConnection();
		try 
		{
			st = connect.prepareStatement(sqlQuery);
			st.setObject(1, id);
			st.executeUpdate();
			
		} catch (SQLException e)
		
		{
			e.printStackTrace();
		}
    	
    }
    
    protected List<T> createObjects(ResultSet resultSet) throws NoSuchMethodException {
        List<T> list = new ArrayList<T>();

        try {	
            while (resultSet.next()) {
                T instance = abstractVariable.newInstance();
                for (Field field : abstractVariable.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), abstractVariable);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
}
