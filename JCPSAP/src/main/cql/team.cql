CREATE KEYSPACE IF NOT EXISTS selfhelp WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;
 
CREATE TABLE IF NOT EXISTS selfhelp.team (
    first_name text,
    last_name text,
    phone_nb text,
    email text,
    location text,
    change_date timestamp,
    PRIMARY KEY (email)
);