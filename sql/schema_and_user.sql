CREATE TABLESPACE "SANDBOX" DATAFILE '/u01/app/oracle/oradata/XE/SANDBOX.dbf' SIZE 100M AUTOEXTEND ON;

DROP USER "SANDBOX" CASCADE;

CREATE USER "SANDBOX" IDENTIFIED BY welcome1 DEFAULT TABLESPACE "SANDBOX";

GRANT "CONNECT" TO "SANDBOX";

GRANT "RESOURCE" TO "SANDBOX";

