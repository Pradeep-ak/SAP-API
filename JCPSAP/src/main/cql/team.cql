CREATE KEYSPACE IF NOT EXISTS selfhelp WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

DROP TABLE selfhelp.team; 
 
CREATE TABLE IF NOT EXISTS selfhelp.team (
	id text,
    first_name text,
    last_name text,
    phone_nb text,
    email text,
    location text,
    change_date timestamp,
    PRIMARY KEY (id)
);