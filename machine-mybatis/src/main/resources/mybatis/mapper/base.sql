DROP DATABASE IF EXISTS machine ;
CREATE DATABASE machine CHARACTER SET UTF8 ;
GRANT ALL PRIVILEGES ON machine.* TO machine@localhost IDENTIFIED BY 'machine';
FLUSH PRIVILEGES;
GRANT select,delete,update,create,drop ON machine.* TO machine@localhost IDENTIFIED BY "machine";