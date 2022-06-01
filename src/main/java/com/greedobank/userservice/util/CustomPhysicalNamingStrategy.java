package com.greedobank.userservice.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;


public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

  @Override
  public Identifier toPhysicalCatalogName(final Identifier identifier,
      final JdbcEnvironment jdbcEnv) {
    return identifier;
  }

  @Override
  public Identifier toPhysicalColumnName(final Identifier identifier,
      final JdbcEnvironment jdbcEnv) {
    return identifier;
  }

  @Override
  public Identifier toPhysicalSchemaName(final Identifier identifier,
      final JdbcEnvironment jdbcEnv) {
    return identifier;
  }

  @Override
  public Identifier toPhysicalSequenceName(final Identifier identifier,
      final JdbcEnvironment jdbcEnv) {
    return identifier;
  }

  @Override
  public Identifier toPhysicalTableName(final Identifier identifier,
      final JdbcEnvironment jdbcEnv) {
    return firstCharacterToLowerCase(identifier);
  }

  private Identifier firstCharacterToLowerCase(final Identifier identifier) {
    String text = identifier.getText();
    String newTExt = Character.toLowerCase(text.charAt(0)) +
        (text.length() > 1 ? text.substring(1) : "");
    return new Identifier(newTExt, false);
  }
}