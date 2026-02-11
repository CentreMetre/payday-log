#!/bin/bash

echo $PWD

# Drop all objects in the db.
echo "Dropping tables"
docker exec -i payday-log-dev psql -U postgres -d paydaylog_old -t -c "DROP SCHEMA public CASCADE; CREATE SCHEMA public;"

# Restore the db.
echo "Restoring database"
# --no-owner instead of --role=postgres because it gives not warning/errors
docker exec -i payday-log-dev pg_restore -U postgres -d paydaylog_old --no-owner < "$HOME/Backup/psql/Payday-backup-latest.dump"

# Rename the conflicting challenges table.
echo "Renaming challenges to challenges_old"
# Dont need this, have sql file for it that also creates new schema
# docker exec -i payday-log-dev psql -U postgres -d paydaylog_old -c "ALTER TABLE challenges RENAME TO challenges_old"

docker exec -i payday-log-dev psql -U postgres -d paydaylog_old < "create-new-db-schema.sql"
