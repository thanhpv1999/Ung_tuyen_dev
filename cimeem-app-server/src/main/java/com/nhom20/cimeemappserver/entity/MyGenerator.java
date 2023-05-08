package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
public class MyGenerator implements IdentifierGenerator {
  private String prefix = "CSP";
  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
    String query = "SELECT e.id FROM product e";
    Stream<String> ids = session.createQuery(query, String.class).stream();
    Long max = ids.map(o -> o.replace(prefix, "")).mapToLong(Long::parseLong).max().orElse(0L);
    return prefix + (String.format("%04d", max + 1));
  }
  @Override
  public void configure(Type type, Properties properties, 
    ServiceRegistry serviceRegistry) throws MappingException {
      prefix = properties.getProperty("prefix");
  }
}