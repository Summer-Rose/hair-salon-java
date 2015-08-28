CREATE TABLE stylists (
  stylist_id serial PRIMARY KEY,
  stylist_name varchar
);

CREATE TABLE clients (
  id serial PRIMARY KEY,
  client_name varchar,
  stylist_id int
);
