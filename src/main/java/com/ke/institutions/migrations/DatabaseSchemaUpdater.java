package com.ke.institutions.migrations;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSchemaUpdater {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseSchemaUpdater(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void updateRoleColumnDataType() {
        String alterQuery = "ALTER TABLE users ALTER COLUMN role TYPE VARCHAR(255)";
        jdbcTemplate.execute(alterQuery);
    }
}