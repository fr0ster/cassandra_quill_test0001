// create udt
CREATE TYPE IF NOT EXISTS mystiko.signature (
  signer TEXT,
  comment TEXT,
  timestamp BIGINT,
);

// create table
CREATE TABLE IF NOT EXISTS mystiko.document (
  id TIMEUUID,
  company TEXT,
  name TEXT,
  tags SET<TEXT>,
  signatures SET<frozen <signature>>,
  status TEXT,
  timestamp TIMESTAMP,
  PRIMARY KEY(company, id)
);