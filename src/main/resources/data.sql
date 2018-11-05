insert into vault_region (region_name) values ('LATAM'),('UK');

insert into vault_country (country_name, region_id) values ('Argentina', 1),('Brazil', 1),('England', 2),('Scotland', 2);

insert into vault_location (street_address, postal_code, city, state_province, country_id) values ('Migueletes 1231', 'C1426BUQ', 'CABA', 'CABA', 1),('Direc 2314', 'COD4125', 'Campinas', 'Sao Paulo', 2);

insert into vault_job (job_title, min_salary, max_salary) values ('Developer', 300.00, 800.00),('Manager', 600.00, 1200.00),('QA', 300.00, 500.00),('Sales', 500.00, 1000.00);

insert into vault_employee (first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) values ('Señor', 'Manager', 'elmanager@vault.com', '1122671112', '2010-06-01', 2, 800.00, 0, null, null),('Brazil', 'Manager', 'brazil.manager@vault.com', '412341112', '2009-03-03', 2, 2000.00, 0, null, null);

insert into vault_department (department_name, manager_id, location_id) values ('Headquarters ARG', 1, 1),('Headquarters BR', 2, 2);

update vault_employee set department_id = 1 where employee_id = 1;
update vault_employee set department_id = 2 where employee_id = 2;

insert into vault_employee (first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) values ('Jean', 'Leduc', 'jean.leduc@vault.com', '1132445212', '2015-02-14', 4, 550.00, 0.15, 1, 1), ('Tabatha', 'Camargo', 'tabi@vault.com', '4312521321', '2017-01-30', 1, 1500.00, 0, 1, 2);

insert into vault_job_history(employee_id, start_date, end_date, job_id, department_id) values (1, '2010-06-01', '2014-03-31', 1, 1),(1, '2014-03-31', null, 2, 1),(2, '2009-03-03', '2012-07-23', 4, 2),(2, '2012-07-23', null, 2, 2),(3, '2015-02-14', null, 4, 1);