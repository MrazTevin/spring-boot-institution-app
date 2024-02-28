#!/bin/sh

# Wait for PostgreSQL to be ready
/wait-for-it.sh school_repository:5432 --timeout=30 --strict -- echo "PostgreSQL is up"

# Start the Spring Boot application
exec java -jar institutions-0.0.1-SNAPSHOT.jar

